package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import models.TicketOrder;
import models.Transaction;
import models.User;

public class OrderController {
    static DatabaseHandler conn = new DatabaseHandler();

    // Insert order into table Orders
    public int insertNewTicketOrder(User user, TicketOrder orders, int concertId) {
        try {
            conn.connect();
            TransactionController tc = new TransactionController();
            Transaction transaction = new Transaction(
                    UUID.randomUUID().toString(),
                    user.getUsername(),
                    null,
                    Timestamp.valueOf(LocalDateTime.now()),
                    orders.getTicket().getPrice() * orders.getQuantity(),
                    "BOOKED");

            if (tc.createTicketTransaction(transaction) == 1) {
                // Insert into ticketorders table
                String insertQuery = "INSERT INTO ticketorders (TicketID, TransactionID, Quantity) VALUES (?, ?, ?)";
                PreparedStatement insertStmt = conn.con.prepareStatement(insertQuery);
                insertStmt.setInt(1, concertId);
                insertStmt.setString(2, transaction.getId());
                insertStmt.setInt(3, orders.getQuantity());
                insertStmt.executeUpdate();

                // Update tickets table
                String updateQuery = "UPDATE tickets SET Stock = Stock - ? WHERE ID = ?";
                PreparedStatement updateStmt = conn.con.prepareStatement(updateQuery);
                updateStmt.setInt(1, orders.getQuantity());
                updateStmt.setInt(2, concertId);
                updateStmt.executeUpdate();
                return 1;
            } else {
                return -1;
            }

        } catch (SQLException e) {
            if (e.getMessage().contains("Quantity")) {
                return 2;
            } else {
                e.printStackTrace();
                return -1;
            }
        } finally {
            conn.disconnect(); // Close the database connection
        }
    }

    public int getTicketIdByTransactionId(String transactionId) {
        try {
            conn.connect();

            String query = "SELECT TicketID FROM ticketorders WHERE TransactionID = ?";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, transactionId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("TicketID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect(); // Close the database connection
        }
        return -1;
    }
}
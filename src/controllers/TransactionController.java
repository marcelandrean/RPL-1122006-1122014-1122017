package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Transaction;

public class TransactionController {
    static DatabaseHandler conn = new DatabaseHandler();

    public ArrayList<Transaction> getAllTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            conn.connect();
            String query = "SELECT * FROM transactions";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(rs.getString("ID"));
                transaction.setUsername(rs.getString("Username"));
                transaction.setTransactionDate(rs.getTimestamp("Transaction_Date"));
                transaction.setTotalPrice(rs.getDouble("Total_Price"));

                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect(); // Close the database connection
        }
        return (transactions);
    }

    public int createTicketTransaction(Transaction transaction) {
        try {
            conn.connect();
            String query = "INSERT INTO transactions (ID, Username, TransactionDate, TotalPrice) VALUES(?,?,?,?)";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, transaction.getId());
            stmt.setString(2, transaction.getUsername());
            stmt.setTimestamp(3, transaction.getTransactionDate());
            stmt.setDouble(4, transaction.getTotalPrice());

            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            if (e.getMessage().contains("ID")) {
                return 2;
            } else {
                e.printStackTrace();
                return -1;
            }
        } finally {
            conn.disconnect(); // Close the database connection
        }
    }

    public void updateTransaction() {
        // Add code to update an existing transaction
    }

    public void deleteTransaction() {
        // Add code to delete a transaction
    }

    public Transaction getTransactionById(String id) {
        Transaction transaction = null;
        try {
            conn.connect();
            String query = "SELECT * FROM transactions WHERE ID = ?";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                transaction = new Transaction();
                transaction.setId(rs.getString("ID"));
                transaction.setUsername(rs.getString("Username"));
                transaction.setTransactionDate(rs.getTimestamp("TransactionDate"));
                transaction.setTotalPrice(rs.getDouble("TotalPrice"));
                transaction.setStatus(rs.getString("Status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect(); // Close the database connection
        }
        return transaction;
    }

    // Add more methods as needed

}
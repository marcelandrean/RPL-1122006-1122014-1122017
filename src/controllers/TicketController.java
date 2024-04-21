package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Ticket;

public class TicketController {

    static DatabaseHandler conn = new DatabaseHandler();

    // Get all from table Ticket
    public ArrayList<Ticket> getAllTickets() {
        ArrayList<Ticket> tickets = new ArrayList<>();
        try {
            conn.connect();
            String query = "SELECT * FROM tickets";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("ID"));
                ticket.setConcertId(rs.getInt("ConcertID"));
                ticket.setCategory(rs.getString("Category"));
                ticket.setPrice(rs.getInt("Price"));
                ticket.setDescription(rs.getString("Description"));
                ticket.setStock(rs.getInt("Stock"));

                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect(); // Close the database connection
        }
        return (tickets);
    }

    // Insert ticket into table Tickets
    public boolean insertNewTicket(Ticket ticket) {
        try {
            conn.connect();
            String query = "INSERT INTO tickets VALUES(?,?,?,?,?,?)";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, ticket.getId());
            stmt.setInt(2, ticket.getConcertId());
            stmt.setString(3, ticket.getCategory());
            stmt.setDouble(4, ticket.getPrice());
            stmt.setString(5, ticket.getDescription());
            stmt.setInt(6, ticket.getStock());

            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        } finally {
            conn.disconnect(); // Close the database connection
        }
    }
}
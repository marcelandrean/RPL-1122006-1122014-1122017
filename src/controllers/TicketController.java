package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Ticket;

public class TicketController {

    static DatabaseHandler conn = new DatabaseHandler();

    // Get all tickets from table Tickets
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
                ticket.setPrice(rs.getDouble("Price"));
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

    // Get all tickets by ConcertID from the table Ticket
    public ArrayList<Ticket> getAllTicketsByConcertId(int concertId) {
        ArrayList<Ticket> tickets = new ArrayList<>();
        try {
            conn.connect();
            String query = "SELECT * FROM tickets WHERE ConcertID=?";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, concertId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("ID"));
                ticket.setConcertId(rs.getInt("ConcertID"));
                ticket.setCategory(rs.getString("Category"));
                ticket.setPrice(rs.getDouble("Price"));
                ticket.setStock(rs.getInt("Stock"));

                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect(); // Close the database connection
        }
        return tickets;
    }

    // Get a single ticket by ID from the Tickets table
    public Ticket getTicketById(int ticketId) {
        Ticket ticket = null;
        try {
            conn.connect();
            String query = "SELECT * FROM tickets WHERE ID = ?";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, ticketId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ticket = new Ticket();
                ticket.setId(rs.getInt("ID"));
                ticket.setConcertId(rs.getInt("ConcertID"));
                ticket.setCategory(rs.getString("Category"));
                ticket.setPrice(rs.getDouble("Price"));
                ticket.setStock(rs.getInt("Stock"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect(); // Close the database connection
        }
        return ticket;
    }

    // Get ticket by Transaction ID
    public Ticket getTicketByTransactionId(String transactionId) {
        Ticket ticket = null;
        try {
            conn.connect();
            int ticketId = new OrderController().getTicketIdByTransactionId(transactionId);

            String query = "SELECT * FROM tickets WHERE ID = ?";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, ticketId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ticket = new Ticket();
                ticket.setId(rs.getInt("ID"));
                ticket.setConcertId(rs.getInt("ConcertID"));
                ticket.setCategory(rs.getString("Category"));
                ticket.setPrice(rs.getDouble("Price"));
                ticket.setStock(rs.getInt("Stock"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect(); // Close the database connection
        }
        return ticket;
    }

    // Insert ticket into table Tickets
    public boolean insertNewTicket(Ticket ticket) {
        try {
            conn.connect();
            String query = "INSERT INTO tickets VALUES(?,?,?,?,?)";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, ticket.getId());
            stmt.setInt(2, ticket.getConcertId());
            stmt.setString(3, ticket.getCategory());
            stmt.setDouble(4, ticket.getPrice());
            stmt.setInt(5, ticket.getStock());

            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        } finally {
            conn.disconnect(); // Close the database connection
        }
    }

    // Update ticket in table Tickets
    public boolean updateTicket(Ticket ticket, int oldId) {
        try {
            conn.connect();
            String query = "UPDATE tickets SET ID = ?, "
                    + "ConcertID = ?, "
                    + "Category = ?, "
                    + "Price = ?, "
                    + "Stock = ?, "
                    + "WHERE ID = ?";
            PreparedStatement statement = conn.con.prepareStatement(query);
            statement.setInt(1, ticket.getId());
            statement.setInt(2, ticket.getConcertId());
            statement.setString(3, ticket.getCategory());
            statement.setDouble(4, ticket.getPrice());
            statement.setInt(5, ticket.getStock());
            statement.setInt(6, oldId);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect();
        }
    }

    public boolean updateTickets(ArrayList<Ticket> tickets, ArrayList<Integer> oldIds) {
        boolean success = true;
        for (int i = 0; i < tickets.size(); i++) {
            Ticket ticket = tickets.get(i);
            int oldId = oldIds.get(i);
            boolean updated = updateTicket(ticket, oldId);
            if (!updated) {
                success = false;
                break;
            }
        }
        return success;
    }
}
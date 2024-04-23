package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import models.Concert;

public class ConcertController {

    static DatabaseHandler conn = new DatabaseHandler();

    // Get all upcoming events from table Concerts
    public ArrayList<Concert> getAllConcerts() {
        ArrayList<Concert> concerts = new ArrayList<>();
        try {
            conn.connect();
            String query = "SELECT * FROM concerts WHERE Date > NOW() ORDER BY Date ASC";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Concert concert = new Concert();
                concert.setId(rs.getInt("ID"));
                concert.setName(rs.getString("Name"));
                concert.setArtist(rs.getString("Artist"));
                concert.setLocation(rs.getString("Location"));
                concert.setDate(rs.getTimestamp("Date"));
                concert.setImagePath(rs.getString("ImagePath"));

                concerts.add(concert);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect(); // Close the database connection
        }
        return (concerts);
    }

    // Get concert by ID in table Concerts
    public Concert getConcertById(int concertId) {
        Concert concert = new Concert();
        try {
            conn.connect();
            String query = "SELECT * FROM concerts WHERE ID=?";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, concertId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                concert.setId(rs.getInt("ID"));
                concert.setName(rs.getString("Name"));
                concert.setArtist(rs.getString("Artist"));
                concert.setLocation(rs.getString("Location"));
                concert.setDate(rs.getTimestamp("Date"));
                concert.setImagePath(rs.getString("ImagePath"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect(); // Close the database connection
        }
        return concert;
    }

    public Concert getConcertByTicketId(int ticketId) {
        Concert concert = new Concert();
        try {
            conn.connect();
            String query = "SELECT c.* FROM concerts c JOIN tickets t ON c.ID = ?";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, ticketId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                concert.setId(rs.getInt("ID"));
                concert.setName(rs.getString("Name"));
                concert.setArtist(rs.getString("Artist"));
                concert.setLocation(rs.getString("Location"));
                concert.setDate(rs.getTimestamp("Date"));
                concert.setImagePath(rs.getString("ImagePath"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect(); // Close the database connection
        }
        return concert;
    }

    // Insert concert into table Concerts
    public boolean insertNewConcert(Concert concert) {
        try {
            conn.connect();
            String query = "INSERT INTO concerts VALUES(?,?,?,?)";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, concert.getId());
            stmt.setString(2, concert.getName());
            stmt.setString(3, concert.getLocation());
            stmt.setTimestamp(4, concert.getDate());

            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        } finally {
            conn.disconnect(); // Close the database connection
        }
    }

    // Update concert in table Concerts
    public boolean updateConcert(Concert concert, int oldId) {
        try {
            conn.connect();
            String query = "UPDATE concerts SET ID = ?, "
                    + "Name = ?, "
                    + "Artist = ?, "
                    + "Date = ?, "
                    + "Location = ?, "
                    + "ImagePath = ? "
                    + "WHERE ID = ?";
            PreparedStatement statement = conn.con.prepareStatement(query);
            statement.setInt(1, concert.getId());
            statement.setString(2, concert.getName());
            statement.setString(3, concert.getArtist());
            statement.setTimestamp(4, concert.getDate());
            statement.setString(5, concert.getLocation());
            statement.setString(6, concert.getImagePath());
            statement.setInt(7, oldId);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect();
        }
    }

    // Update many concerts (ArrayList) in table Concerts
    public boolean updateConcerts(ArrayList<Concert> concerts, ArrayList<Integer> oldIds) {
        boolean success = true;
        for (int i = 0; i < concerts.size(); i++) {
            Concert concert = concerts.get(i);
            int oldId = oldIds.get(i);
            boolean updated = updateConcert(concert, oldId);
            if (!updated) {
                success = false;
                break;
            }
        }
        return success;
    }
}

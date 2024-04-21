package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import models.Concert;

public class ConcertController {
    
    static DatabaseHandler conn = new DatabaseHandler();

    // Get all from table Concerts
    public ArrayList<Concert> getAllConcerts(){
         ArrayList<Concert> concerts = new ArrayList<>();
        try {
            conn.connect();
            String query = "SELECT * FROM concerts";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Concert concert = new Concert();
                concert.setId(rs.getInt("ID"));
                concert.setName(rs.getString("Name"));
                concert.setLocation(rs.getString("Location"));
                concert.setDate(rs.getTimestamp("Date"));

                concerts.add(concert);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect(); // Close the database connection
        }
        return (concerts);
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
}

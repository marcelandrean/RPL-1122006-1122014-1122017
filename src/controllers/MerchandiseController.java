package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Merchandise;

public class MerchandiseController {

    static DatabaseHandler conn = new DatabaseHandler();

    // Get all from table Ticket
    public ArrayList<Merchandise> getAllMerchandises(){
         ArrayList<Merchandise> merchandises = new ArrayList<>();
        try {
            conn.connect();
            String query = "SELECT * FROM merchandises";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Merchandise merchandise = new Merchandise();
                merchandise.setId(rs.getString("ID"));
                merchandise.setName(rs.getString("name"));
                merchandise.setPrice(rs.getInt("Category"));
                merchandise.setStock(rs.getInt("Stock"));

                merchandises.add(merchandise);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect(); // Close the database connection
        }
        return (merchandises);
    }
}

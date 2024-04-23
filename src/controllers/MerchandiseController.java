package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Merchandise;

public class MerchandiseController {

    static DatabaseHandler conn = new DatabaseHandler();

    // Get all from table Merchandise
    public ArrayList<Merchandise> getAllMerchandise(){
         ArrayList<Merchandise> merchandises = new ArrayList<>();
        try {
            conn.connect();
            String query = "SELECT * FROM merch";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Merchandise merch = new Merchandise();
                merch.setId(rs.getInt("ID"));
                merch.setName(rs.getString("name"));
                merch.setPrice(rs.getInt("Category"));
                merch.setStock(rs.getInt("Stock"));

                merchandises.add(merch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect(); // Close the database connection
        }
        return (merchandises);
    }

    // Update merch in table Merchandise
    public boolean updateMerch(Merchandise merch, int oldId) {
        try {
            conn.connect();
            String query = "UPDATE tickets SET ID = ?, "
                    + "Name = ?, "
                    + "Price = ?, "
                    + "Stock = ?, "
                    + "WHERE ID = ?";
            PreparedStatement statement = conn.con.prepareStatement(query);
            statement.setInt(1, merch.getId());
            statement.setString(2, merch.getName());
            statement.setDouble(3, merch.getPrice());
            statement.setInt(4, merch.getStock());
            statement.setInt(5, oldId);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect();
        }
    }

    public boolean updateMerchandise(ArrayList<Merchandise> tickets, ArrayList<Integer> oldIds) {
        boolean success = true;
        for (int i = 0; i < tickets.size(); i++) {
            Merchandise merch = tickets.get(i);
            int oldId = oldIds.get(i);
            boolean updated = updateMerch(merch, oldId);
            if (!updated) {
                success = false;
                break;
            }
        }
        return success;
    }
}

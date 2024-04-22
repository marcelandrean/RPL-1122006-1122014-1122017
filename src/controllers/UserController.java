package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Customer;
import models.User;
import models.UserType;

public class UserController {

    static DatabaseHandler conn = new DatabaseHandler();

    // Get all customers from table Users
    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            conn.connect();
            String query = "SELECT * FROM users WHERE UserType = 'CUSTOMER'";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setUsername(rs.getString("Username"));
                customer.setFullname(rs.getString("FullName"));
                customer.setEmail(rs.getString("Email"));
                customer.setPassword(rs.getString("Password"));
                customer.setPhoneNumber(rs.getString("PhoneNumber"));
                customer.setUserType(UserType.valueOf(rs.getString("UserType")));

                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect(); // Close the database connection
        }
        return (customers);
    }

    // Get user by username/email & password
    public User getUser(String credential, String password) {
        User user = null;
        try {
            conn.connect();
            String query = "SELECT * FROM users WHERE (username=? OR email=?) AND password=?";
            PreparedStatement pstmt = conn.con.prepareStatement(query);
            pstmt.setString(1, credential);
            pstmt.setString(2, credential);
            pstmt.setString(3, password);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setUsername(rs.getString("Username"));
                user.setFullname(rs.getString("FullName"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setPhoneNumber(rs.getString("PhoneNumber"));
                user.setUserType(UserType.valueOf(rs.getString("UserType")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect(); // Close the database connection
        }
        return user;
    }

    // Insert customer into table Users
    public boolean insertNewCustomer(Customer customers) {
        try {
            conn.connect();
            String query = "INSERT INTO users VALUES(?,?,?,?,?,?)";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, customers.getUsername());
            stmt.setString(2, customers.getFullName());
            stmt.setString(3, customers.getEmail());
            stmt.setString(4, customers.getPassword());
            stmt.setString(5, customers.getPhoneNumber());
            stmt.setString(6, customers.getUserType().name());

            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        } finally {
            conn.disconnect(); // Close the database connection
        }
    }

    // Update user in table Users
    public boolean updateUser(User user, String oldUsername) {
        try {
            conn.connect();
            String query = "UPDATE users SET Username='" + oldUsername + "', "
                    + "Username='" + user.getUsername() + "', "
                    + "FullName='" + user.getFullName() + "', "
                    + "Email='" + user.getEmail() + "', "
                    + "Password='" + user.getPassword() + "', "
                    + "PhoneNumber='" + user.getPhoneNumber() + "' "
                    + "WHERE Username='" + oldUsername + "'";
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        } finally {
            conn.disconnect(); // Close the database connection
        }
    }

    // Delete user from table Users
    public static boolean deleteUser(String username) {
        try {
            conn.connect();
            String query = "DELETE FROM users WHERE Username='" + username + "'";
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        } finally {
            conn.disconnect(); // Close the database connection
        }
    }
}

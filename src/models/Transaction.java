package models;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Transaction {

    private String id;
    private String username;
    private ArrayList<Order> orders;
    private Timestamp transactionDate;
    private double totalPrice;
    
    public TransactionInterface state;

    public Transaction() {
    }

    public Transaction(String id, String username, ArrayList<Order> orders, Timestamp transactionDate,
            double totalPrice, String status) {
        this.id = id;
        this.username = username;
        this.orders = orders;
        this.transactionDate = transactionDate;
        this.totalPrice = totalPrice;
        this.status = status;
        this.state = new BookedState();
    }

    public void updateStatus() {
        state.update(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionInterface getState() {
        return state;
    }

    public void setState(TransactionInterface state) {
        this.state = state;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;
}

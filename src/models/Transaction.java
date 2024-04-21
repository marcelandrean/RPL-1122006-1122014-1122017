package models;

import java.util.ArrayList;

public class Transaction {
    
    private String id;
    private User user;
    private ArrayList<Order> order;
    public Transaction(String id, User user, ArrayList<Order> order) {
        this.id = id;
        this.user = user;
        this.order = order;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public ArrayList<Order> getOrder() {
        return order;
    }
    public void setOrder(ArrayList<Order> order) {
        this.order = order;
    }

    
}

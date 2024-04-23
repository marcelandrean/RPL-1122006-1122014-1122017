package models;

public abstract class Order {

    private int quantity;

    public Order() {
    }

    public Order(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}



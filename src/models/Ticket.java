package models;

public class Ticket {

    private int id;
    private int concertId;
    private String category;
    private double price;
    private String description;
    private int stock;

    public Ticket() {}

    public Ticket(int id, int concertId, String category, double price, String description, int stock) {
        this.id = id;
        this.concertId = concertId;
        this.category = category;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConcertId() {
        return concertId;
    }

    public void setConcertId(int concertId) {
        this.concertId = concertId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

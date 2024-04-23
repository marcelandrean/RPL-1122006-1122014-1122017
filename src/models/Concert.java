package models;

import java.sql.Timestamp;

public class Concert {
    private int id;
    private String name;
    private String artist;
    private String location;
    private Timestamp date;
    private String imagePath;
    private Ticket[] tickets;

    public Concert() {
    }

    public Concert(int id, String name, String artist, String location, Timestamp date, String imagePath,
            Ticket[] tickets) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.location = location;
        this.date = date;
        this.imagePath = imagePath;
        this.tickets = tickets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Ticket[] getTickets() {
        return tickets;
    }

    public void setTickets(Ticket[] tickets) {
        this.tickets = tickets;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}

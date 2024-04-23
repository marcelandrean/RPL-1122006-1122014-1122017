package models;

public class TicketOrder extends Order {

    private Ticket ticket;

    public TicketOrder() {
    }

    public TicketOrder(int quantity, Ticket ticket) {
        super(quantity);
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

}

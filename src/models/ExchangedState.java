package models;

public class ExchangedState implements TransactionInterface {
    
    @Override
    public void update(Transaction transaction) {
        System.out.println("Ticket already exchanged");
    }
}

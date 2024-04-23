package models;

public class BookedState implements TransactionInterface {

    @Override
    public void update(Transaction transaction) {
        transaction.state = new ExchangedState();
    }
}

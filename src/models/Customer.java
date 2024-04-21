package models;

import java.util.ArrayList;

public class Customer extends User {

    private ArrayList<Transaction> transactionList;

    public Customer() {}

    public Customer(String username, String fullname, String email, String password, String phoneNumber, UserType userType,
            ArrayList<Transaction> transactionList) {
        super(username, fullname, email, password, phoneNumber, userType);
        this.transactionList = transactionList;
    }

    public ArrayList<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(ArrayList<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}

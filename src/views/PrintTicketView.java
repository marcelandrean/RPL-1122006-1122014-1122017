package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import controllers.ConcertController;
import controllers.TicketController;
import controllers.TransactionController;
import models.Concert;
import models.Ticket;
import models.Transaction;

public class PrintTicketView {

    private JFrame frame;
    private GridBagConstraints gbc;
    private ViewHelper helper;

    public PrintTicketView() {

        frame = new JFrame();
        frame.setTitle("HB Promotions - Print Ticket");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 10); // padding

        helper = new ViewHelper();

        // Input username or email
        helper.createLabel(gbc, formPanel, "Transaction ID:", 0, 0);
        helper.setInputConstraints(gbc, 1);
        JTextField transIdField = new JTextField(20);
        formPanel.add(transIdField, gbc);

        // Button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton backButton = new JButton("Back");
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonPanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new MainMenuCustomer();
            }
        });

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonPanel.add(confirmButton);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Transaction transaction = new TransactionController().getTransactionById(transIdField.getText());
                Ticket ticket = new TicketController().getTicketByTransactionId(transaction.getId());
                Concert concert = new ConcertController().getConcertByTicketId(ticket.getId());

                frame.dispose();
                showTicket(concert, transaction);
            }
        });

        formPanel.add(buttonPanel, gbc);

        frame.add(formPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void showTicket(Concert concert, Transaction transaction) {

        frame = new JFrame();
        frame.setTitle("HB Promotions - Login");
        frame.setSize(1000, 365);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Create a JLabel to hold the image
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/img/ticket.jpg"));
        JLabel background = new JLabel(imageIcon);

        // Set the bounds of the background label to cover the entire frame
        background.setBounds(0, 0, frame.getWidth(), frame.getHeight());

        // Add the background label to the frame
        frame.add(background);

        // Add text labels
        JLabel titleLabel = new JLabel(concert.getName());
        titleLabel.setBounds(20, 20, 1000, 30); // Adjust position and size as needed
        titleLabel.setForeground(Color.WHITE); // Set font color to white
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26)); // Set font size and style
        background.add(titleLabel);

        JLabel artistLabel = new JLabel("Artist: " + concert.getArtist());
        artistLabel.setBounds(20, 50, 1000, 30); // Adjust position and size as needed
        artistLabel.setForeground(Color.WHITE); // Set font color to white
        artistLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set font size and style
        background.add(artistLabel);

        JLabel venueLabel = new JLabel("Venue: " + concert.getLocation());
        venueLabel.setBounds(20, 80, 1000, 30); // Adjust position and size as needed
        venueLabel.setForeground(Color.WHITE); // Set font color to white
        venueLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set font size and style
        background.add(venueLabel);

        JLabel dateLabel = new JLabel("Date: " + concert.getDate().toString());
        dateLabel.setBounds(20, 110, 1000, 30); // Adjust position and size as needed
        dateLabel.setForeground(Color.WHITE); // Set font color to white
        dateLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set font size and style
        background.add(dateLabel);

        JLabel transactionIdLabel = new JLabel(transaction.getId().toUpperCase());
        transactionIdLabel.setBounds(20, 180, 1000, 30); // Adjust position and size as needed
        transactionIdLabel.setForeground(Color.WHITE); // Set font color to white
        transactionIdLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set font size and style
        background.add(transactionIdLabel);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new MainMenuCustomer();
            }
        });

        JButton exchangeButton = new JButton("Exchange");
        exchangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int choice = JOptionPane.showConfirmDialog(null, "Confirm exchange?", "Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    // Update transaction status to "EXCHANGED"
                    TransactionController tc = new TransactionController();
                    transaction.updateStatus();
                    tc.updateTransactionStatus(transaction.getId());

                    // Display a success message
                    JOptionPane.showMessageDialog(null, "Transaction successfully exchanged.");

                    // Go back to the main menu or perform any other action
                    frame.dispose();
                    new MainMenuCustomer();
                }
            }
        });
        background.add(exchangeButton);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new PrintTicketView();
    }
}

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
                // Concert concert = new ConcertController().getConcertByTransactionId(transaction.getId());

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
        JLabel titleLabel = new JLabel("Concert Title");
        titleLabel.setBounds(20, 20, 200, 30); // Adjust position and size as needed
        titleLabel.setForeground(Color.WHITE); // Set font color to white
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Set font size and style
        background.add(titleLabel);

        JLabel descriptionLabel = new JLabel("Concert Description");
        descriptionLabel.setBounds(20, 50, 300, 30); // Adjust position and size as needed
        descriptionLabel.setForeground(Color.WHITE); // Set font color to white
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font size and style
        background.add(descriptionLabel);

        JLabel dateLabel = new JLabel("Concert Date");
        dateLabel.setBounds(20, 80, 200, 30); // Adjust position and size as needed
        dateLabel.setForeground(Color.WHITE); // Set font color to white
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font size and style
        background.add(dateLabel);

        // Add other text labels as needed

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new PrintTicketView();
    }
}

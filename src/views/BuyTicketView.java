package views;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controllers.ConcertController;
import controllers.OrderController;
import controllers.TicketController;
import models.Concert;
import models.Ticket;
import models.TicketOrder;
import models.User;
import models.UserSessionManager;

public class BuyTicketView {
    /*
     * 1. Get all concert (check all schedule)
     * 2. Choose concert
     * 3. Get all ticket from that concert
     * 4. Choose Ticket
     * 5. Check ticket stocks
     * 6. Pass User & Ticket & Quantity to buyTicket func
     */

    private User user;
    private JFrame frame;
    private GridBagConstraints gbc;
    private ViewHelper helper;
    private ConcertController cc;
    private TicketController tc;

    public BuyTicketView() {
        frame = new JFrame();
        frame.setTitle("HB Promotions - Concert Schedules");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS)); // Use BoxLayout here

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());

        helper = new ViewHelper();
        cc = new ConcertController();

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 10); // padding

        // Label title
        JLabel title = new JLabel("Upcoming Schedules");
        title.setBorder(new EmptyBorder(10, 10, 10, 10));
        title.setFont(new Font(Font.DIALOG, Font.BOLD, 35));
        titlePanel.add(title);

        // Get all concerts
        ArrayList<Concert> concerts = cc.getAllConcerts();

        int y = 1;
        for (Concert concert : concerts) {
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridy = y;
            gbc.gridwidth = 2;
            helper.createLabel(gbc, contentPanel, concert.getName(), 0, y++);

            gbc.gridwidth = 1;
            helper.createLabel(gbc, contentPanel, "Artists", 0, y);
            helper.createLabel(gbc, contentPanel, ": " + concert.getArtist(), 1, y++);

            Timestamp timestamp = concert.getDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

            helper.createLabel(gbc, contentPanel, "Day/Date", 0, y);
            helper.createLabel(gbc, contentPanel, ": " + dateFormat.format(new Date(timestamp.getTime())), 1, y++);

            helper.createLabel(gbc, contentPanel, "Time", 0, y);
            helper.createLabel(gbc, contentPanel, ": " + timeFormat.format(new Date(timestamp.getTime())), 1, y++);

            helper.createLabel(gbc, contentPanel, "Venue", 0, y);
            helper.createLabel(gbc, contentPanel, ": " + concert.getLocation(), 1, y++);

            gbc.gridx = 0;
            gbc.gridy = y++;
            JButton buyTicketButton = new JButton("Buy Ticket");
            buyTicketButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            buyTicketButton.setActionCommand(String.valueOf(concert.getId()));
            contentPanel.add(buyTicketButton, gbc);

            helper.createLabel(gbc, contentPanel, "\n\n", 0, y++);

            buyTicketButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    String concertId = ae.getActionCommand();
                    System.out.println("ConcertID: " + concertId);
                    frame.dispose();
                    new BuyTicketView(Integer.parseInt(concertId));
                }
            });
        }

        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new MainMenuCustomer();
            }
        });

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(backButton, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public BuyTicketView(int concertId) {
        frame = new JFrame();
        frame.setTitle("HB Promotions - Buy Ticket");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS)); // Use BoxLayout here

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());

        helper = new ViewHelper();
        cc = new ConcertController();
        tc = new TicketController();

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 10); // padding

        Concert concert = cc.getConcertById(concertId);
        ArrayList<Ticket> tickets = tc.getAllTicketsByConcertId(concertId);

        // Label title
        JLabel title = new JLabel(concert.getName());
        title.setBorder(new EmptyBorder(10, 10, 10, 10));
        title.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        titlePanel.add(title);

        Map<String, String> categoryMap = new HashMap<>();

        JComboBox<String> categoryComboBox = new JComboBox<>();
        for (Ticket ticket : tickets) {
            String categoryAndPrice = ticket.getCategory() + " - Rp" + formatPrice(ticket.getPrice());
            categoryComboBox.addItem(categoryAndPrice);
            categoryMap.put(categoryAndPrice, ticket.getId() + "-" + ticket.getConcertId());
        }

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        JLabel imageLabel = new JLabel();

        // Load the image
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(concert.getImagePath()));
        Image originalImage = originalIcon.getImage();

        // Calculate the new width while preserving aspect ratio
        int originalWidth = originalImage.getWidth(null);
        int originalHeight = originalImage.getHeight(null);
        int newHeight = 600;
        int newWidth = (int) ((double) originalWidth / originalHeight * newHeight);

        // Resize the image
        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Set the scaled image to the label
        imageLabel.setIcon(scaledIcon);
        contentPanel.add(imageLabel, gbc);

        gbc.gridwidth = 1;
        helper.createLabel(gbc, contentPanel, "Select Ticket:", 0, 1);

        gbc.gridy = 2;
        contentPanel.add(categoryComboBox, gbc);

        helper.createLabel(gbc, contentPanel, "Quantity:", 1, 1);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextField quantityField = new JTextField(2);
        contentPanel.add(quantityField, gbc);

        // Buy button
        JButton buyButton = new JButton("Buy ticket");
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Confirm purchase?",
                        "Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    String selectedCategoryAndPrice = (String) categoryComboBox.getSelectedItem();
                    String[] parts = selectedCategoryAndPrice.split("-");
                    int selectedTicketId = Integer.parseInt(parts[0]); // Extract ticket ID
                    int selectedConcertId = Integer.parseInt(parts[1]); // Extract concert ID
                    System.out.println("Selected ticket ID: " + selectedTicketId);

                    int quantity = Integer.parseInt(quantityField.getText());

                    user = UserSessionManager.getInstance().getUser();

                    Ticket ticket = tc.getTicketById(selectedTicketId);
                    TicketOrder ticketOrder = new TicketOrder(quantity, ticket);

                    OrderController oc = new OrderController();
                    int result = oc.insertNewTicketOrder(user, ticketOrder, selectedConcertId);

                    switch (result) {
                        case 1:
                            helper.infoResponse("Ticket order placed successfully.");
                            frame.dispose();
                            new BuyTicketView();
                            break;
                        case 2:
                            helper.errorResponse("Invalid quantity.");
                            break;
                        default:
                            helper.errorResponse("Failed to place ticket order.");
                            break;
                    }
                } else {
                    return;
                }
            }
        });
        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.NONE;
        contentPanel.add(buyButton, gbc);

        // Back button
        JButton backButton = new JButton("Cancel");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new BuyTicketView();
            }
        });
        gbc.gridx = 3;
        contentPanel.add(backButton, gbc);

        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(contentPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private String formatPrice(double price) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format((int) price);
    }

    public static void main(String[] args) {
        new BuyTicketView();
    }
}
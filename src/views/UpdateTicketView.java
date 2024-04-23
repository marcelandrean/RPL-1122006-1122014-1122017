package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import controllers.TicketController;
import models.Ticket;

public class UpdateTicketView {

    private JFrame frame;
    private JTable table;
    private TicketController tc;

    public UpdateTicketView() {
        frame = new JFrame();
        frame.setTitle("Update Tickets");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Initialize the ticket controller
        tc = new TicketController();

        // Retrieve all tickets from the controller
        ArrayList<Ticket> tickets = tc.getAllTickets();

        // Convert the tickets list to a 2D array for the table model
        Object[][] data = new Object[tickets.size()][5];
        for (int i = 0; i < tickets.size(); i++) {
            Ticket ticket = tickets.get(i);
            data[i][0] = ticket.getId();
            data[i][1] = ticket.getConcertId();
            data[i][2] = ticket.getCategory();
            data[i][3] = ticket.getPrice();
            data[i][4] = ticket.getStock();
        }

        // Column names
        String[] columnNames = { "ID", "Concert ID", "Category", "Price", "Stock" };

        // Create the table model with data and column names
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // Create the JTable with the model
        table = new JTable(model);

        // Set up the frame
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Create buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainMenuAdmin();
            }
        });
        buttonPanel.add(backButton);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the table model and number of rows
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int rowCount = model.getRowCount();

                // Create lists to hold the modified tickets and their old IDs
                ArrayList<Ticket> modifiedTickets = new ArrayList<>();
                ArrayList<Integer> oldIds = new ArrayList<>();

                // Iterate over each row of the table
                for (int i = 0; i < rowCount; i++) {
                    // Get data from each column
                    int id = (int) model.getValueAt(i, 0);
                    int concertId = (int) model.getValueAt(i, 1);
                    String category = (String) model.getValueAt(i, 2);
                    double price = (double) model.getValueAt(i, 3);
                    int stock = (int) model.getValueAt(i, 4);

                    // Create a new Ticket object with the modified data
                    Ticket ticket = new Ticket(id, concertId, category, price, stock);
                    // Add the modified ticket to the ArrayList
                    modifiedTickets.add(ticket);
                    // Add the old ID to the list
                    oldIds.add(id);
                }

                // Call the updateTickets method in the TicketController to update all tickets
                boolean success = tc.updateTickets(modifiedTickets, oldIds);
                if (success) {
                    new ViewHelper().infoResponse("Tickets updated successfully!");
                } else {
                    new ViewHelper().errorResponse("Failed to update tickets!");
                }
            }
        });
        buttonPanel.add(updateButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new UpdateTicketView();
    }
}

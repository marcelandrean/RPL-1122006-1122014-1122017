package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;

import controllers.ConcertController;
import models.Concert;

public class UpdateConcertView {

    private JFrame frame;
    private JTable table;
    private ConcertController cc;

    public UpdateConcertView() {
        frame = new JFrame();
        frame.setTitle("HB Promotions - Update Concerts");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Initialize the concert controller
        cc = new ConcertController();

        // Retrieve all concerts from the controller
        ArrayList<Concert> concerts = cc.getAllConcerts();

        // Convert the concerts list to a 2D array for the table model
        Object[][] data = new Object[concerts.size()][6];
        for (int i = 0; i < concerts.size(); i++) {
            Concert concert = concerts.get(i);
            data[i][0] = concert.getId();
            data[i][1] = concert.getName();
            data[i][2] = concert.getArtist();
            data[i][3] = concert.getLocation();
            data[i][4] = concert.getDate();
            data[i][5] = concert.getImagePath();
        }

        // Column names
        String[] columnNames = { "ID", "Name", "Artist", "Location", "Date", "ImagePath" };

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

                // Create lists to hold the modified concerts and their old IDs
                ArrayList<Concert> modifiedConcerts = new ArrayList<>();
                ArrayList<Integer> oldIds = new ArrayList<>();

                // Iterate over each row of the table
                for (int i = 0; i < rowCount; i++) {
                    // Get data from each column
                    int id = (int) model.getValueAt(i, 0);
                    String name = (String) model.getValueAt(i, 1);
                    String artist = (String) model.getValueAt(i, 2);
                    String location = (String) model.getValueAt(i, 3);
                    Timestamp date = (Timestamp) model.getValueAt(i, 4);
                    String imagePath = (String) model.getValueAt(i, 5);

                    // Create a new Concert object with the modified data
                    Concert concert = new Concert(id, name, artist, location, date, imagePath, null);
                    // Add the modified concert to the ArrayList
                    modifiedConcerts.add(concert);
                    // Add the old ID to the list
                    oldIds.add(id);
                }

                boolean success = cc.updateConcerts(modifiedConcerts, oldIds);
                if (success) {
                    new ViewHelper().infoResponse("Concerts updated succesfully!");
                } else {
                    new ViewHelper().errorResponse("Failed to update concerts!");
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
        new UpdateConcertView();
    }
}

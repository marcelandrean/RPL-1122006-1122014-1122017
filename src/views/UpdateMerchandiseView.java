package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import controllers.MerchandiseController;
import models.Merchandise;

public class UpdateMerchandiseView {

    private JFrame frame;
    private JTable table;
    private MerchandiseController mc;

    public UpdateMerchandiseView() {
        frame = new JFrame();
        frame.setTitle("HB Promotions - Update Merchandise");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Initialize the merchandise controller
        mc = new MerchandiseController();

        // Retrieve all merchandise items from the controller
        ArrayList<Merchandise> merchandiseList = mc.getAllMerchandise();

        // Convert the merchandise list to a 2D array for the table model
        Object[][] data = new Object[merchandiseList.size()][4];
        for (int i = 0; i < merchandiseList.size(); i++) {
            Merchandise merchandise = merchandiseList.get(i);
            data[i][0] = merchandise.getId();
            data[i][1] = merchandise.getName();
            data[i][2] = merchandise.getPrice();
            data[i][3] = merchandise.getStock();
        }

        // Column names
        String[] columnNames = { "ID", "Name", "Price", "Stock" };

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

                // Create lists to hold the modified merchandise and their old IDs
                ArrayList<Merchandise> modifiedMerchandiseList = new ArrayList<>();
                ArrayList<Integer> oldIds = new ArrayList<>();

                // Iterate over each row of the table
                for (int i = 0; i < rowCount; i++) {
                    // Get data from each column
                    int id = (int) model.getValueAt(i, 0);
                    String name = (String) model.getValueAt(i, 1);
                    double price = (double) model.getValueAt(i, 2);
                    int stock = (int) model.getValueAt(i, 3);

                    // Create a new Merchandise object with the modified data
                    Merchandise merchandise = new Merchandise(id, name, price, stock);
                    // Add the modified merchandise to the ArrayList
                    modifiedMerchandiseList.add(merchandise);
                    // Add the old ID to the list
                    oldIds.add(id);
                }

                boolean success = mc.updateMerchandise(modifiedMerchandiseList, oldIds);
                if (success) {
                    new ViewHelper().infoResponse("Merchandise updated successfully!");
                } else {
                    new ViewHelper().errorResponse("Failed to update merchandise!");
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
        new UpdateMerchandiseView();
    }
}

package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controllers.MerchandiseController;
import models.Merchandise;

public class BuyMerchView {

    private JFrame frame;
    private JPanel panel;
    private GridBagConstraints gbc;
    private MerchandiseController controller;

    public BuyMerchView() {
        frame = new JFrame();
        frame.setTitle("HB Promotions - Merchandise");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 10); // padding

        controller = new MerchandiseController(); // Assuming you have this class

        // Get all merchandise
        ArrayList<Merchandise> merchandiseList = controller.getAllMerchandise();

        // Create buttons for each merchandise
        for (Merchandise merchandise : merchandiseList) {
            JButton merchandiseButton = new JButton(merchandise.getName());
            merchandiseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Perform action when merchandise button is clicked
                    // You can implement merchandise buying functionality here
                    System.out.println("Buy merchandise: " + merchandise.getName());
                }
            });

            // Add the button to the panel using gbc
            panel.add(merchandiseButton, gbc);
        }

        // Add the panel to a scroll pane
        JScrollPane scrollPane = new JScrollPane(panel);
        frame.add(scrollPane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new BuyMerchView();
    }
}

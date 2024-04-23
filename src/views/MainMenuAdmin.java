package views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import models.UserSessionManager;

public class MainMenuAdmin {

    private JFrame frame;
    private GridBagConstraints gbc;

    public MainMenuAdmin() {

        frame = new JFrame();
        frame.setTitle("HB Promotions - Main Menu Admin");
        frame.setSize(850, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel title = new JLabel("Main Menu - Admin");
        title.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        contentPanel.add(title, gbc);

        Font buttonFont = new Font(Font.DIALOG, Font.PLAIN, 16);

        JButton updateConcert = new JButton("Update Concert Schedule");
        updateConcert.setFont(buttonFont);
        updateConcert.setPreferredSize(new Dimension(240, 50));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        contentPanel.add(updateConcert, gbc);

        JButton updateTicket = new JButton("Update Ticket");
        updateTicket.setFont(buttonFont);
        updateTicket.setPreferredSize(new Dimension(240, 50));
        gbc.gridx = 1;
        contentPanel.add(updateTicket, gbc);

        JButton updateMerchandise = new JButton("Update Merchandise");
        updateMerchandise.setFont(buttonFont);
        updateMerchandise.setPreferredSize(new Dimension(240, 50));
        gbc.gridx = 2;
        contentPanel.add(updateMerchandise, gbc);

        JButton checkTicketSold = new JButton("Check Ticket Sold");
        checkTicketSold.setFont(buttonFont);
        checkTicketSold.setPreferredSize(new Dimension(240, 50));
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(checkTicketSold, gbc);

        JButton checkCustHistory = new JButton("Check Customer History");
        checkCustHistory.setFont(buttonFont);
        checkCustHistory.setPreferredSize(new Dimension(240, 50));
        gbc.gridx = 1;
        contentPanel.add(checkCustHistory, gbc);

        JButton printTicket = new JButton("Print Ticket");
        printTicket.setFont(buttonFont);
        printTicket.setPreferredSize(new Dimension(240, 50));
        gbc.gridx = 2;
        contentPanel.add(printTicket, gbc);

        JButton logout = new JButton("Logout");
        logout.setFont(buttonFont);
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(logout, gbc);

        updateConcert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new UpdateConcertView();
            }
        });

        updateTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new UpdateTicketView();   
            }
        });

        updateMerchandise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new UpdateMerchandiseView();
            }
        });

        checkTicketSold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                // new CheckTicketView();
            }
        });

        checkCustHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                // new CheckHistoryView();
            }
        });

        printTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new PrintTicketView();
            }
        });

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                UserSessionManager.getInstance().reset();
                frame.dispose();
                new WelcomeScreen();
            }
        });

        frame.add(contentPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenuAdmin();
    }
}

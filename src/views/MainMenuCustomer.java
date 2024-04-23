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

public class MainMenuCustomer {

    private JFrame frame;
    private GridBagConstraints gbc;

    public MainMenuCustomer() {

        frame = new JFrame();
        frame.setTitle("HB Promotions - Main Menu");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel title = new JLabel("Main Menu");
        title.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentPanel.add(title, gbc);

        Font buttonFont = new Font(Font.DIALOG, Font.PLAIN, 16);

        JButton buyTicket = new JButton("Buy Ticket");
        buyTicket.setFont(buttonFont);
        buyTicket.setPreferredSize(new Dimension(180, 50));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        contentPanel.add(buyTicket, gbc);

        JButton buyMerchandise = new JButton("Buy Merchandise");
        buyMerchandise.setFont(buttonFont);
        buyMerchandise.setPreferredSize(new Dimension(180, 50));
        gbc.gridx = 1;
        contentPanel.add(buyMerchandise, gbc);

        JButton checkHistory = new JButton("Transaction History");
        checkHistory.setFont(buttonFont);
        checkHistory.setPreferredSize(new Dimension(180, 50));
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(checkHistory, gbc);

        JButton printTicket = new JButton("Print Ticket");
        printTicket.setFont(buttonFont);
        printTicket.setPreferredSize(new Dimension(180, 50));
        gbc.gridx = 1;
        contentPanel.add(printTicket, gbc);

        JButton logout = new JButton("Logout");
        logout.setFont(buttonFont);
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(logout, gbc);

        buyTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new BuyTicketView();
            }
        });

        buyMerchandise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new BuyMerchView();
            }
        });

        checkHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new TransactionHistoryView();
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
        new MainMenuCustomer();
    }
}

package views;

import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.UserController;
import models.User;
import models.UserSessionManager;

public class LoginView {

    private JFrame frame;
    private GridBagConstraints gbc;
    private ViewHelper helper;

    public LoginView() {

        frame = new JFrame();
        frame.setTitle("HB Promotions - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 10); // padding

        helper = new ViewHelper();

        // Input username or email
        helper.createLabel(gbc, formPanel, "Username/Email", 0, 0);
        helper.setInputConstraints(gbc, 1);
        JTextField credentialField = new JTextField(20);
        formPanel.add(credentialField, gbc);

        // Input password
        helper.createLabel(gbc, formPanel, "Password", 0, 1);
        helper.setInputConstraints(gbc, 1);
        JPasswordField passwordField = new JPasswordField(20);
        formPanel.add(passwordField, gbc);

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
                new WelcomeScreen();
            }
        });

        JButton loginButton = new JButton("Login");
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonPanel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                User user = new UserController().getUser(credentialField.getText(), passwordField.getText());

                UserSessionManager.getInstance().setUser(user);

                if (user == null) {
                    helper.errorResponse("Wrong username, email or password!");
                    return;
                } else {
                    helper.infoResponse("Login succesful!");
                    frame.dispose();
                    if (user.getUserType().name().equals("CUSTOMER")) {
                        new MainMenuCustomer();
                    } else if (user.getUserType().name().equals("ADMIN")) {
                        new MainMenuAdmin();
                    }
                }
            }
        });

        formPanel.add(buttonPanel, gbc);

        frame.add(formPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginView();
    }
}

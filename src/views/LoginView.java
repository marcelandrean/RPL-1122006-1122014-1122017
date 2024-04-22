package views;

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
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controllers.UserController;
import models.User;
import models.UserSessionManager;

public class LoginView {

    private JFrame frame;
    private JPanel formPanel;
    private GridBagConstraints gbc;
    private ViewHelper helper;

    public LoginView() {

        frame = new JFrame();
        frame.setTitle("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 10); // padding

        helper = new ViewHelper();

        // Input username or email
        helper.createLabel(gbc, formPanel, "Username/Email", 0);
        helper.setInputConstraints(gbc);
        JTextField credentialField = new JTextField(20);
        formPanel.add(credentialField, gbc);

        // Input password
        helper.createLabel(gbc, formPanel, "Password", 1);
        helper.setInputConstraints(gbc);
        JPasswordField passwordField = new JPasswordField(20);
        formPanel.add(passwordField, gbc);

        // Button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton backButton = new JButton("Back");
        buttonPanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new WelcomeScreen();
            }
        });

        JButton loginButton = new JButton("Login");
        buttonPanel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                User user = new UserController().getUser(credentialField.getText(), passwordField.getText());

                UserSessionManager.getInstance().setUser(user);

                frame.dispose();
                if (user == null) {
                    helper.errorResponse("Wrong username, email or password!");
                } else {
                    new MainMenu();
                }
            }
        });

        formPanel.add(buttonPanel, gbc);

        // Scroll pane + frame view
        JScrollPane scrollPane = new JScrollPane(formPanel);
        frame.add(scrollPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

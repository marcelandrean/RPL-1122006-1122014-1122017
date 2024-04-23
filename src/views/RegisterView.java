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
import models.Customer;
import models.UserType;

public class RegisterView {

    private JFrame frame;
    private GridBagConstraints gbc;
    private ViewHelper helper;

    public RegisterView() {

        frame = new JFrame();
        frame.setTitle("HB Promotions - Register");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 10); // padding

        helper = new ViewHelper();

        // Input username
        helper.createLabel(gbc, formPanel, "Username", 0, 0);
        helper.setInputConstraints(gbc, 1);
        JTextField usernameField = new JTextField(20);
        formPanel.add(usernameField, gbc);

        // Input full name
        helper.createLabel(gbc, formPanel, "Full Name", 0, 1);
        helper.setInputConstraints(gbc, 1);
        JTextField fullNameField = new JTextField(20);
        formPanel.add(fullNameField, gbc);

        // Input email
        helper.createLabel(gbc, formPanel, "Email", 0, 2);
        helper.setInputConstraints(gbc, 1);
        JTextField emailField = new JTextField(20);
        formPanel.add(emailField, gbc);

        // Input password
        helper.createLabel(gbc, formPanel, "Password", 0, 3);
        helper.setInputConstraints(gbc, 1);
        JPasswordField passwordField = new JPasswordField(20);
        formPanel.add(passwordField, gbc);

        // Re-type password for verification
        helper.createLabel(gbc, formPanel, "Re-type Password", 0, 4);
        helper.setInputConstraints(gbc, 1);
        JPasswordField retypePassField = new JPasswordField(20);
        formPanel.add(retypePassField, gbc);

        // Input phone number
        helper.createLabel(gbc, formPanel, "Phone Number", 0, 5);
        helper.setInputConstraints(gbc, 1);
        JTextField phoneNumberField = new JTextField(20);
        formPanel.add(phoneNumberField, gbc);

        // Button
        gbc.gridx = 0;
        gbc.gridy = 6;
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

        JButton registerButton = new JButton("Register");
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonPanel.add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // Mendapatkan nilai dari setiap field
                String username = usernameField.getText();
                String fullName = fullNameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String retypePassword = new String(retypePassField.getPassword());
                String phoneNumber = phoneNumberField.getText();

                // Memeriksa apakah ada kolom yang kosong
                if (username.isEmpty() || fullName.isEmpty() || email.isEmpty() || password.isEmpty()
                        || retypePassword.isEmpty() || phoneNumber.isEmpty()) {
                    helper.warningResponse("All fields required!");
                    return;
                }

                // Memeriksa apakah password sesuai dengan pengulangan password
                if (!password.equals(retypePassword)) {
                    helper.warningResponse("Password does not match!");
                    return;
                }

                Customer customer = new Customer(
                        username,
                        fullName,
                        email,
                        password,
                        phoneNumber,
                        UserType.CUSTOMER,
                        null);

                int condition = new UserController().insertNewCustomer(customer);
                switch (condition) {
                    case 1:
                        helper.infoResponse("Registration successful!");
                        frame.dispose();
                        new LoginView();
                        break;
                    case 2:
                        helper.warningResponse("Username already in use!");
                        usernameField.requestFocus();
                        break;
                    case 3:
                        helper.warningResponse("Email already in use!");
                        emailField.requestFocus();
                        break;
                    default:
                        helper.errorResponse("An error occurred during registration!");
                        break;
                }
            }
        });

        formPanel.add(buttonPanel, gbc);

        frame.add(formPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

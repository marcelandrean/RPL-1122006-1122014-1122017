package views;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomeScreen {

    private JFrame frame;
    private GridBagConstraints gbc;

    public WelcomeScreen() {

        frame = new JFrame("HB Promotions - Welcome Screen");
        frame.setSize(600, 380);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();

        // Label title
        JLabel title = new JLabel("HB Promotions");
        title.setFont(new Font(Font.DIALOG, Font.BOLD, 35));
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 25, 10);
        panel.add(title, gbc);

        Font buttonFont = new Font(Font.DIALOG, Font.PLAIN, 20);

        // Button Login
        JButton login = new JButton("Login");
        login.setFont(buttonFont);
        login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.setPreferredSize(new Dimension(150, 50));
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(login, gbc);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new LoginView();
            }
        });

        // Button Register
        JButton register = new JButton("Register");
        register.setFont(buttonFont);
        register.setCursor(new Cursor(Cursor.HAND_CURSOR));
        register.setPreferredSize(new Dimension(150, 50));
        gbc.gridy = 2;
        panel.add(register, gbc);
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new RegisterView();
            }
        });

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

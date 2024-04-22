package views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen {

    JFrame frame = new JFrame("JAM Cinema");

    public WelcomeScreen() {
        frame.setSize(600,380);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font("Serif", Font.PLAIN, 20);

         // label title
        JLabel title = new JLabel("JAM Cinema");
        title.setBounds(190, 5, 500, 60);
        title.setFont(new Font("Serif", Font.BOLD, 35));

        JButton login = new JButton("Login");
        login.setBounds(200, 100, 180, 50);
        login.setFont(font);
        login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new LoginView();
            }
        });
        JButton register = new JButton("Register");
        register.setBounds(200, 160, 180, 50);
        register.setFont(font);
        register.setCursor(new Cursor(Cursor.HAND_CURSOR));
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new RegisterView();
            }
        });
        frame.add(title);
        frame.add(login);
        frame.add(register);
        frame.setLayout(null);

        frame.setVisible(true);
    }
}

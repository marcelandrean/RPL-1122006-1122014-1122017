package views;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class RegisterView {

    public RegisterView() {

        JFrame frame = new JFrame("Register Form");
        frame.setSize(600, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font font = new Font("Serif", Font.PLAIN, 20);

        // label title
        JLabel title = new JLabel("Register");
        title.setBounds(225, 5, 500, 60);
        title.setFont(new Font("Serif", Font.BOLD, 35));

        frame.setVisible(true);
    }
}

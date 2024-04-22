package views;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import models.User;

public class RegisterView {

    public RegisterView() {

        JFrame frame = new JFrame("Register Form");
        frame.setSize(600, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);



        frame.setVisible(true);
    }
}

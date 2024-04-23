package views;

import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ViewHelper {

    public void infoResponse(String text) {
        JOptionPane.showMessageDialog(null, text, "HB Promotions", JOptionPane.INFORMATION_MESSAGE);
    }

    public void warningResponse(String text) {
        JOptionPane.showMessageDialog(null, text, "HB Promotions", JOptionPane.WARNING_MESSAGE);
    }

    public void errorResponse(String text) {
        JOptionPane.showMessageDialog(null, text, "HB Promotions", JOptionPane.ERROR_MESSAGE);
    }

    public void createLabel(GridBagConstraints gbc, JPanel formPanel, String text, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.weightx = 0.0;
        JLabel label = new JLabel(text);
        formPanel.add(label, gbc);
    }

    public void setInputConstraints(GridBagConstraints gbc, int x) {
        gbc.gridx = x;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
    }
}

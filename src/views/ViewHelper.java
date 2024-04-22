package views;

import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ViewHelper {

    public void notifyResponse(String text) {
        JOptionPane.showMessageDialog(null, text, "Almarson Concert", JOptionPane.INFORMATION_MESSAGE);
    }

    public void warningResponse(String text) {
        JOptionPane.showMessageDialog(null, text, "Almarson Concert", JOptionPane.WARNING_MESSAGE);
    }

    public void errorResponse(String text) {
        JOptionPane.showMessageDialog(null, text, "Almarson Concert", JOptionPane.ERROR_MESSAGE);
    }

    public void createLabel(GridBagConstraints gbc, JPanel formPanel, String text, int y) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.weightx = 0.0;
        JLabel label = new JLabel(text);
        formPanel.add(label, gbc);
    }

    public void setInputConstraints(GridBagConstraints gbc) {
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
    }
}
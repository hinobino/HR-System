package view;

import interface_adapter.time_off.TimeOffRequestController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeOffRequestView extends JPanel {
    private TimeOffRequestController controller;

    private final JButton submitRequestButton;
    private final JTextField startDateField;
    private final JTextField endDateField;
    private final JButton backButton;

    public TimeOffRequestView() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        startDateField = new JTextField(10);
        endDateField = new JTextField(10);
        startDateField.setPreferredSize(new Dimension(100, 25)); // Set smaller preferred size
        endDateField.setPreferredSize(new Dimension(100, 25)); // Set smaller preferred size
        submitRequestButton = new JButton("Submit Request");
        backButton = new JButton("Back");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Start Date (yyyy-mm-dd):"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(startDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("End Date (yyyy-mm-dd):"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(endDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(submitRequestButton, gbc);

        gbc.gridy = 3;
        add(backButton, gbc);

        // Add listener for the buttons
        submitRequestButton.addActionListener(new SubmitRequestActionListener());
        backButton.addActionListener(new BackButtonActionListener());
    }

    public String getViewName() {
        return "timeOffRequestView";
    }

    public void setTimeOffRequestController(TimeOffRequestController controller) {
        this.controller = controller;
    }

    private class SubmitRequestActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String startDate = startDateField.getText();
            String endDate = endDateField.getText();

            if (controller != null) {
                controller.submitRequest(startDate, endDate);
            } else {
                JOptionPane.showMessageDialog(TimeOffRequestView.this, "Controller not set for TimeOffRequestView.");
            }
        }
    }

    private class BackButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Assuming there's a CardLayout managing the views
            Container parent = TimeOffRequestView.this.getParent();
            if (parent != null && parent.getLayout() instanceof CardLayout) {
                CardLayout cardLayout = (CardLayout) parent.getLayout();
                cardLayout.show(parent, "employee"); // Switch back to the "employee" view
            }
        }
    }
}

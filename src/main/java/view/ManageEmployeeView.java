package view;

import interface_adapter.manage_employee.ManageEmployeeController;
import interface_adapter.manage_employee.ManageEmployeeState;
import interface_adapter.manage_employee.ManageEmployeeViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ManageEmployeeView extends JPanel implements PropertyChangeListener {

    private final String viewName = "manage employee";
    private final ManageEmployeeViewModel manageEmployeeViewModel;

    private final JLabel userIDLabel;
    private final JLabel statusLabel;
    private final JLabel payLabel;
    private final JLabel hoursWorkedLabel;
    private final JLabel employmentPeriodLabel;

    private final JButton changeStatus;
    private final JButton changePay;
    private final JButton backButton;

    private ManageEmployeeController manageEmployeeController;

    public ManageEmployeeView(ManageEmployeeViewModel manageEmployeeViewModel) {
        this.manageEmployeeViewModel = manageEmployeeViewModel;
        this.manageEmployeeViewModel.addPropertyChangeListener(this);

        // Title
        final JLabel title = new JLabel(ManageEmployeeViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 24));

        // UserID label
        userIDLabel = new JLabel("");
        userIDLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Employment period label
        employmentPeriodLabel = new JLabel("");
        employmentPeriodLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel for buttons
        final JPanel buttons = new JPanel();
        buttons.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;

        // Status label and button
        statusLabel = new JLabel("");
        buttons.add(statusLabel, gbc);

        gbc.gridx++;
        changeStatus = new JButton(ManageEmployeeViewModel.CHANGE_STATUS);
        changeStatus.setEnabled(false);
        buttons.add(changeStatus, gbc);

        // Pay label and button
        gbc.gridx = 0;
        gbc.gridy++;
        payLabel = new JLabel("");
        buttons.add(payLabel, gbc);

        gbc.gridx++;
        changePay = new JButton(ManageEmployeeViewModel.CHANGE_PAY);
        buttons.add(changePay, gbc);

        // Hour worked label
        gbc.gridx = 0;
        gbc.gridy++;
        hoursWorkedLabel = new JLabel("");
        buttons.add(hoursWorkedLabel, gbc);

        // Back button
        gbc.gridx = 0;
        gbc.gridy++;
        backButton = new JButton(ManageEmployeeViewModel.BACK_BUTTON);
        buttons.add(backButton, gbc);

        changeStatus.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int choice = JOptionPane.showConfirmDialog(
                                ManageEmployeeView.this,
                                "Are you sure you want to change this employee's status?",
                                "Warning", JOptionPane.YES_NO_OPTION);
                        if(choice == JOptionPane.YES_OPTION) {
                            final ManageEmployeeState currentState = manageEmployeeViewModel.getState();
                            manageEmployeeController.changeStatus(currentState.getEmployee());
                        }
                    }
                }
        );

        changePay.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String newPayRate = JOptionPane.showInputDialog(
                        ManageEmployeeView.this,
                        "Enter new pay rate: ",
                        "Change Pay",
                        JOptionPane.QUESTION_MESSAGE);
                    final ManageEmployeeState currentState = manageEmployeeViewModel.getState();
                    manageEmployeeController.changePay(currentState.getEmployee(), newPayRate);
                }
            }
        );

        backButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    manageEmployeeController.switchToEmployeeListView();
                }
            }
        );

        // Format the Manage Employee View
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(userIDLabel);
        this.add(employmentPeriodLabel);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final ManageEmployeeState state = (ManageEmployeeState) evt.getNewValue();
            userIDLabel.setText(ManageEmployeeViewModel.USERID_LABEL + state.getUserId() + ".");
            employmentPeriodLabel.setText(state.getEmploymentPeriod());
            statusLabel.setText(ManageEmployeeViewModel.STATUS_LABEL + state.getStaus());
            if (state.getEmployee().getPassword() != "") {
                changeStatus.setEnabled(true);
            }
            payLabel.setText(ManageEmployeeViewModel.PAY_LABEL + state.getPay());
            hoursWorkedLabel.setText(ManageEmployeeViewModel.HOURS_WORKED_LABEL + state.getHoursWorked());
            if(state.getPayError()) {
                JOptionPane.showMessageDialog(
                        ManageEmployeeView.this,
                        "Please enter a valid pay rate (In the form XX.XX)",
                        "Error", JOptionPane.ERROR_MESSAGE);
                state.togglePayError();
            }
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setManageEmployeeController(ManageEmployeeController manageEmployeeController) {
        this.manageEmployeeController = manageEmployeeController;
    }
}

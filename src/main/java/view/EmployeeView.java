package view;

import interface_adapter.logged_in.EmployeeViewModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.ManagerViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the user is logged into the program.
 */
public class EmployeeView extends JPanel implements PropertyChangeListener {

    private final String viewName = "employee";
    private final EmployeeViewModel employeeViewModel;

    private final JLabel welcomeLabel;

    private final JButton schedule;
    private final JButton shifts;
    private final JButton requestLeave;

    public EmployeeView(EmployeeViewModel employeeViewModel) {
        this.employeeViewModel = employeeViewModel;
        this.employeeViewModel.addPropertyChangeListener(this);

        // Title
        final JLabel title = new JLabel(EmployeeViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Welcome message
        welcomeLabel = new JLabel("");
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel for buttons
        final JPanel buttons = new JPanel();
        buttons.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        schedule = new JButton(EmployeeViewModel.SCHEDULE_LABEL);
        buttons.add(schedule, gbc);

        gbc.gridy++;
        shifts = new JButton(EmployeeViewModel.SHIFTS_LABEL);
        buttons.add(shifts, gbc);

        gbc.gridy++;
        requestLeave = new JButton(EmployeeViewModel.REQUEST_LABEL);
        buttons.add(requestLeave, gbc);

        // TODO: Implement these action listeners.
        schedule.addActionListener(e -> {});
        shifts.addActionListener(e -> {});
        requestLeave.addActionListener(e -> {});

        // Format the whole EmployeeView
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title, gbc);
        this.add(welcomeLabel, gbc);
        this.add(buttons, gbc);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            welcomeLabel.setText(EmployeeViewModel.WELCOME_LABEL + state.getUserID() + ".");
        }
//        else if (evt.getPropertyName().equals("password")) {
//            final LoggedInState state = (LoggedInState) evt.getNewValue();
//            JOptionPane.showMessageDialog(null, "password updated for " + state.getUserID());
//        }

    }

    public String getViewName() {
        return viewName;
    }
}
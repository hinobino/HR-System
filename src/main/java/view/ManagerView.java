package view;

import entity.Manager;
import interface_adapter.logged_in.ManagerController;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.ManagerViewModel;
import interface_adapter.logout.LogoutController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the user is logged into the program.
 */
public class ManagerView extends JPanel implements PropertyChangeListener {

    private final String viewName = "logged in";
    private final ManagerViewModel managerViewModel;
    private LogoutController logoutController;

    private final JLabel welcomeLabel;

    private final JButton schedule;
    private final JButton setShift;
    private final JButton employees;
    private final JButton requests;
    private final JButton createEmployee;
    private final JButton logOut;

    private ManagerController managerController;

    public ManagerView(ManagerViewModel managerViewModel) {
        this.managerViewModel = managerViewModel;
        this.managerViewModel.addPropertyChangeListener(this);

        // Title
        final JLabel title = new JLabel(ManagerViewModel.TITLE_LABEL);
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

        createEmployee = new JButton(ManagerViewModel.CREATE_EMPLOYEE_LABEL);
        buttons.add(createEmployee, gbc);

        gbc.gridy++;
        schedule = new JButton(ManagerViewModel.SCHEDULE_LABEL);
        buttons.add(schedule, gbc);

        gbc.gridy++;
        setShift = new JButton(ManagerViewModel.SET_SHIFT_LABEL);
        buttons.add(setShift, gbc);

        gbc.gridy++;
        employees = new JButton(ManagerViewModel.EMPLOYEES_LABEL);
        buttons.add(employees, gbc);

        gbc.gridy++;
        requests = new JButton(ManagerViewModel.REQUESTS_LABEL);
        buttons.add(requests, gbc);

        gbc.gridy++;
        logOut = new JButton("Log Out");
        buttons.add(logOut, gbc);

        createEmployee.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        managerController.switchToCreateEmployeeView();
                    }
                }
        );

        logOut.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final LoggedInState currentState = managerViewModel.getState();
                        logoutController.execute(currentState.getUserID());
                    }
                }
        );

        // TODO: Implement these action listeners.
        schedule.addActionListener(e -> {});
        requests.addActionListener(e -> {});

        setShift.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        managerController.switchToScheduleShiftView();
                    }
                }
        );

        employees.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        managerController.switchToEmployeeListView();
                    }
                }
        );

        // Format the whole ManagerView
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(welcomeLabel);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            welcomeLabel.setText(ManagerViewModel.WELCOME_LABEL + state.getUserID() + ".");
        }
        else if (evt.getPropertyName().equals("log out")) {
//            JOptionPane.showMessageDialog(ManagerView.this, "You have logged out successfully.");
        }
//        // Leaving this code here for reference if we want to add pop-up messages
//        else if (evt.getPropertyName().equals("password")) {
//            final LoggedInState state = (LoggedInState) evt.getNewValue();
//            JOptionPane.showMessageDialog(null, "password updated for " + state.getUserID());
//        }

    }

    public String getViewName() {
        return viewName;
    }

    public void setManagerController(ManagerController managerController) {
        this.managerController = managerController;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }
}

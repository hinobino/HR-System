package view;

import interface_adapter.logged_in.EmployeeViewModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;

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

    private final JLabel userID;

    public EmployeeView(EmployeeViewModel employeeViewModel) {
        this.employeeViewModel = employeeViewModel;
        this.employeeViewModel.addPropertyChangeListener(this);

        // TODO: use the LoggedInViewModel class to name the following buttons/labels.

        final JLabel title = new JLabel("EMPLOYEE Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel userIDInfo = new JLabel("Currently logged in: ");
        userID = new JLabel();


//        final JPanel buttons = new JPanel();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(userIDInfo);
        this.add(userID);
//        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            userID.setText(state.getUserID());
        }
        else if (evt.getPropertyName().equals("password")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUserID());
        }

    }

    public String getViewName() {
        return viewName;
    }
}

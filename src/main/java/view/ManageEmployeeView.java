package view;

import interface_adapter.manage_employee.ManageEmployeeController;
import interface_adapter.manage_employee.ManageEmployeeState;
import interface_adapter.manage_employee.ManageEmployeeViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ManageEmployeeView extends JPanel implements PropertyChangeListener {

    private final String viewName = "manage employee";
    private final ManageEmployeeViewModel manageEmployeeViewModel;

    private final JLabel userIDLabel;

    private ManageEmployeeController manageEmployeeController;

    public ManageEmployeeView(ManageEmployeeViewModel manageEmployeeViewModel) {
        this.manageEmployeeViewModel = manageEmployeeViewModel;
        this.manageEmployeeViewModel.addPropertyChangeListener(this);

        // Title
        final JLabel title = new JLabel(viewName);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // UserID label
        userIDLabel = new JLabel("");
        userIDLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Format the Manage Employee View
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(userIDLabel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final ManageEmployeeState state = (ManageEmployeeState) evt.getNewValue();
            userIDLabel.setText(ManageEmployeeViewModel.USERID_LABEL + state.getUserId() + ".");
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setManagerController(ManageEmployeeController manageEmployeeController) {
        this.manageEmployeeController = manageEmployeeController;
    }
}

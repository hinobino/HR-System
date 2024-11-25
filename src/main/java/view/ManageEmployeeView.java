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

    private final JButton backButton;

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

        // Status label
        statusLabel = new JLabel("");
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel for buttons
        final JPanel buttons = new JPanel();
        buttons.setLayout(new GridBagLayout());

        backButton = new JButton(ManageEmployeeViewModel.BACK_BUTTON);
        buttons.add(backButton);

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
        this.add(statusLabel);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final ManageEmployeeState state = (ManageEmployeeState) evt.getNewValue();
            userIDLabel.setText(ManageEmployeeViewModel.USERID_LABEL + state.getUserId() + ".");
            statusLabel.setText(ManageEmployeeViewModel.STATUS_LABEL + state.getStaus());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setManageEmployeeController(ManageEmployeeController manageEmployeeController) {
        this.manageEmployeeController = manageEmployeeController;
    }
}

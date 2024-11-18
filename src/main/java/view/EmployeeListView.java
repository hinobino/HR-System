package view;

import interface_adapter.create_employee.CreateEmployeeState;
import interface_adapter.employee_list.EmployeeListController;
import interface_adapter.employee_list.EmployeeListState;
import interface_adapter.employee_list.EmployeeListViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View where the manager can see a list of employees
 */
public class EmployeeListView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "employee list";
    private final EmployeeListViewModel employeeListViewModel;

    private EmployeeListController employeeListController;

    public EmployeeListView(EmployeeListViewModel employeeListViewModel) {
        this.employeeListViewModel = employeeListViewModel;
        this.employeeListViewModel.addPropertyChangeListener(this);

        // Title
        final JLabel title = new JLabel(employeeListViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Format the whole Employee List View
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final CreateEmployeeState state = (CreateEmployeeState) evt.getNewValue();
    }

    public String getViewName() {
        return viewName;
    }

    public void setEmployeeListController(EmployeeListController employeeListController) {
        this.employeeListController = employeeListController;
    }

}


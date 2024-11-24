package view;

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
    private Object[][] data;
    private final JButton createEmployee;
    private final JButton backButton;

    private EmployeeListController employeeListController;

    public EmployeeListView(EmployeeListViewModel employeeListViewModel) {
        this.employeeListViewModel = employeeListViewModel;
        this.employeeListViewModel.addPropertyChangeListener(this);

        // Title
        final JLabel title = new JLabel(employeeListViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Panel for table
        data = employeeListViewModel.getState().getEmployeeList();
        JTable table = new JTable(data, employeeListViewModel.columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(100, 100));
        table.setFillsViewportHeight(true);

        // Panel for buttons
        final JPanel buttons = new JPanel();
        buttons.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;

        createEmployee = new JButton(EmployeeListViewModel.CREATE_EMPLOYEE_LABEL);
        buttons.add(createEmployee);

        gbc.gridx = 1;
        backButton = new JButton(EmployeeListViewModel.BACK_BUTTON_LABEL);
        buttons.add(backButton, gbc);

        backButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        employeeListController.switchToManagerView();
                    }
                }
        );

        // Format the whole Employee List View
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
        this.add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            System.out.println("hello");
            final EmployeeListState state = (EmployeeListState) evt.getNewValue();
            employeeListController.createEmployeeList();
            data = state.getEmployeeList();
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setEmployeeListController(EmployeeListController employeeListController) {
        this.employeeListController = employeeListController;
    }

}


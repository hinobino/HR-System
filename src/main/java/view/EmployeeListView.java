package view;

import interface_adapter.employee_list.EmployeeListController;
import interface_adapter.employee_list.EmployeeListState;
import interface_adapter.employee_list.EmployeeListViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private final JTable table;
    private final JScrollPane scrollPane;
    private final JButton manageEmployee;
    private final JButton createEmployee;
    private final JButton backButton;

    private EmployeeListController employeeListController;

    public EmployeeListView(EmployeeListViewModel employeeListViewModel) {
        this.employeeListViewModel = employeeListViewModel;
        this.employeeListViewModel.addPropertyChangeListener(this);

        // Title
        final JLabel title = new JLabel(EmployeeListViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Panel for table
        table = new JTable(new Object[1][2], EmployeeListViewModel.COLUMN_NAMES) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(100, 100));
        table.setFillsViewportHeight(true);

        // Panel for buttons
        final JPanel buttons = new JPanel();
        buttons.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 1;
        gbc.gridy = 0;

        manageEmployee = new JButton(EmployeeListViewModel.MANAGE_EMPLOYEE_LABEL);
        buttons.add(manageEmployee, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        createEmployee = new JButton(EmployeeListViewModel.CREATE_EMPLOYEE_LABEL);
        buttons.add(createEmployee,gbc);

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
        this.add(scrollPane);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final EmployeeListState state = (EmployeeListState) evt.getNewValue();
            table.setModel(new DefaultTableModel(state.getEmployeeList(), EmployeeListViewModel.COLUMN_NAMES) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setEmployeeListController(EmployeeListController employeeListController) {
        this.employeeListController = employeeListController;
    }

}


package view;

import interface_adapter.employee_list.EmployeeListController;
import interface_adapter.employee_list.EmployeeListState;
import interface_adapter.employee_list.EmployeeListViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View where the manager can see a list of employees
 */
public class EmployeeListView extends JPanel implements PropertyChangeListener, MouseListener, ActionListener {

    private final String viewName = "employee list";
    private final EmployeeListViewModel employeeListViewModel;
    private final JTable table;
    private final JScrollPane scrollPane;
    private final JButton createEmployee;
    private final JButton backButton;

    private EmployeeListController employeeListController;

    public EmployeeListView(EmployeeListViewModel employeeListViewModel) {
        this.employeeListViewModel = employeeListViewModel;
        this.employeeListViewModel.addPropertyChangeListener(this);

        // Title
        final JLabel title = new JLabel(EmployeeListViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 24));

        //Panel for table
        table = new JTable(new Object[1][3], EmployeeListViewModel.COLUMN_NAMES) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.getTableHeader().setReorderingAllowed(false);
        table.addMouseListener(this);
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(100, 100));
        table.setFillsViewportHeight(true);

        // Panel for buttons
        final JPanel buttons = new JPanel();
        buttons.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;

        createEmployee = new JButton(EmployeeListViewModel.CREATE_EMPLOYEE_LABEL);
        buttons.add(createEmployee,gbc);

        gbc.gridx = 1;
        backButton = new JButton(EmployeeListViewModel.BACK_BUTTON_LABEL);
        buttons.add(backButton, gbc);

        createEmployee.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        employeeListController.switchToCreateEmployeeView();
                    }
                }
        );

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
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final EmployeeListState state = (EmployeeListState) evt.getNewValue();
            table.setModel(new DefaultTableModel(state.getEmployeeList(), EmployeeListViewModel.COLUMN_NAMES) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
            table.getColumnModel().getColumn(1).setCellRenderer(new CustomTableCellRenderer());
            table.getColumnModel().getColumn(2).setCellRenderer(new CustomTableCellRenderer());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int column = table.getColumnModel().getColumnIndexAtX(e.getX());
        int row = e.getY()/table.getRowHeight();

        if(row < table.getRowCount() && row >= 0 && column == 2) {
            Object value = table.getValueAt(row, column);
            if(value instanceof JButton) {
                JButton button = (JButton) value;
                button.addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                final EmployeeListState currentState = employeeListViewModel.getState();
                                employeeListController.selectEmployee(currentState.getEmployee((String) table.getValueAt(row, 0)));
                            }
                        });
                button.doClick();
            }
        }
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public void actionPerformed(ActionEvent e) {}


    public String getViewName() {
        return viewName;
    }

    public void setEmployeeListController(EmployeeListController employeeListController) {
        this.employeeListController = employeeListController;
    }

}


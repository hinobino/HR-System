package view;

import entity.Shift;
import interface_adapter.manage_shifts.ManageShiftsController;
import interface_adapter.manage_shifts.ManageShiftsState;
import interface_adapter.manage_shifts.ManageShiftsViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ManageShiftsView extends JPanel implements PropertyChangeListener {

    private final String viewName = "manage shifts";
    private final ManageShiftsViewModel manageShiftsViewModel;
    private ManageShiftsController manageShiftsController;

    private final JTable table;
    private final JScrollPane scrollPane;
    private final JButton deleteShiftButton;
    private final JButton backButton;

    public ManageShiftsView(ManageShiftsViewModel manageShiftsViewModel) {
        this.manageShiftsViewModel = manageShiftsViewModel;
        this.manageShiftsViewModel.addPropertyChangeListener(this);

        // Title
        final JLabel title = new JLabel(ManageShiftsViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Calibri", Font.BOLD, 24));

        // Table
        table = new JTable(new String[1][4], ManageShiftsViewModel.TABLE_HEADERS) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        table.setFillsViewportHeight(true);

        // Buttons
        deleteShiftButton = new JButton(ManageShiftsViewModel.DELETE_SHIFT_BUTTON_LABEL);
        backButton = new JButton(ManageShiftsViewModel.BACK_BUTTON_LABEL);

        final JPanel buttons = new JPanel();
        buttons.add(deleteShiftButton);
        buttons.add(backButton);

        deleteShiftButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final int row = table.getSelectedRow();
                        if (row == -1) {
                            JOptionPane.showMessageDialog(ManageShiftsView.this,
                                    "Please select a shift.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            final ManageShiftsState manageShiftsState = manageShiftsViewModel.getState();
                            Shift shift = manageShiftsState.getShiftByID((String) table.getValueAt(row, 1));
                            manageShiftsController.execute(shift);
                        }
                    }
                }
        );

        backButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        manageShiftsController.switchToManagerView();
                    }
                }
        );

        // View Layout
        this.setLayout(new GridBagLayout());
        final GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        this.add(title, c);
        c.gridy++;
        this.add(scrollPane, c);
        c.gridy++;
        this.add(buttons, c);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final ManageShiftsState manageShiftsState = (ManageShiftsState) evt.getNewValue();
            updateTabelData(manageShiftsState);
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setManageShiftsController(ManageShiftsController controller) {
        this.manageShiftsController = controller;
    }

    public void updateTabelData(ManageShiftsState manageShiftsState) {
        List<Shift> shiftList = manageShiftsState.getShifts();
        String[][] tableData = new String[shiftList.size()][5];
        int i = 0;
        for (Shift shift : shiftList) {
            String employeeID = shift.getEmployee().getUserID();
            String shiftID = shift.getID();
            String shiftDate = shift.getDay().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String shiftStart = shift.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm"));
            String shiftEnd = shift.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm"));

            tableData[i] = new String[]{employeeID, shiftID, shiftDate, shiftStart, shiftEnd};
            i++;
        }

        table.setModel(new DefaultTableModel(tableData, ManageShiftsViewModel.TABLE_HEADERS) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        });
    }

}

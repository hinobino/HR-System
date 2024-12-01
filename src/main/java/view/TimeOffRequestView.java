package view;

import interface_adapter.time_off.TimeOffRequestController;
import interface_adapter.time_off.TimeOffRequestViewModel;
import java.time.LocalDate;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TimeOffRequestView extends JPanel implements PropertyChangeListener {
    private final TimeOffRequestViewModel timeOffRequestViewModel;
    private final JTable requestTable;
    private final JTextField startDateField;
    private final JTextField endDateField;
    private TimeOffRequestController timeOffRequestController;

    public TimeOffRequestView(TimeOffRequestViewModel timeOffRequestViewModel) {
        this.timeOffRequestViewModel = timeOffRequestViewModel;
        this.timeOffRequestViewModel.addPropertyChangeListener(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Time Off Request");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);

        startDateField = new JTextField(10);
        endDateField = new JTextField(10);
        JButton submitButton = new JButton("Submit Request");
        submitButton.addActionListener(new SubmitRequestActionListener());

        add(new JLabel("Start Date (yyyy-mm-dd):"));
        add(startDateField);
        add(new JLabel("End Date (yyyy-mm-dd):"));
        add(endDateField);
        add(submitButton);

        requestTable = new JTable(new DefaultTableModel(new Object[][]{}, new String[]{"Request ID", "Start Date", "End Date", "Status"}));
        add(new JScrollPane(requestTable));
    }

    public void setTimeOffRequestController(TimeOffRequestController controller) {
        this.timeOffRequestController = controller;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("requestList".equals(evt.getPropertyName())) {
            updateRequestTable();
        }
    }

    private void updateRequestTable() {
        DefaultTableModel model = (DefaultTableModel) requestTable.getModel();
        model.setRowCount(0);
        for (entity.TimeOffRequest request : timeOffRequestViewModel.getRequestList()) {
            model.addRow(new Object[]{request.getRequestId(), request.getStartDate(), request.getEndDate(), request.isApproved() ? "Approved" : "Pending"});
        }
    }

    private class SubmitRequestActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String startDate = startDateField.getText();
            String endDate = endDateField.getText();
            timeOffRequestController.submitRequest("EMPLOYEE_ID", LocalDate.parse(startDate), LocalDate.parse(endDate));
        }
    }
}
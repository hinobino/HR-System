package view;

import entity.TimeOffRequest;
import interface_adapter.manager_time_off.ManagerTimeOffRequestController;
import use_case.manager_time_off.ManagerTimeOffRequestOutputBoundary;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * The View for the Manager to handle employee time-off requests.
 */
public class ManagerTimeOffRequestView extends JPanel implements PropertyChangeListener, ManagerTimeOffRequestOutputBoundary {
    private final JTable requestTable;
    private ManagerTimeOffRequestController managerTimeOffRequestController;

    public ManagerTimeOffRequestView() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Time Off Requests");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        requestTable = new JTable(new DefaultTableModel(new Object[][]{},
                new String[]{"Request ID", "Employee ID", "Start Date", "End Date", "Status"}));
        JScrollPane scrollPane = new JScrollPane(requestTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        JButton approveButton = new JButton("Approve");
        JButton rejectButton = new JButton("Reject");

        approveButton.addActionListener(e -> {
            int selectedRow = requestTable.getSelectedRow();
            if (selectedRow != -1) {
                String requestId = (String) requestTable.getValueAt(selectedRow, 0);
                managerTimeOffRequestController.approveRequest(requestId);
            } else {
                JOptionPane.showMessageDialog(ManagerTimeOffRequestView.this,
                        "Please select a request to approve.");
            }
        });

        rejectButton.addActionListener(e -> {
            int selectedRow = requestTable.getSelectedRow();
            if (selectedRow != -1) {
                String requestId = (String) requestTable.getValueAt(selectedRow, 0);
                managerTimeOffRequestController.rejectRequest(requestId);
            } else {
                JOptionPane.showMessageDialog(ManagerTimeOffRequestView.this,
                        "Please select a request to reject.");
            }
        });

        buttonsPanel.add(approveButton);
        buttonsPanel.add(rejectButton);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    public void setManagerTimeOffRequestController(ManagerTimeOffRequestController controller) {
        this.managerTimeOffRequestController = controller;
        controller.loadAllRequestsForManager();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("allRequests".equals(evt.getPropertyName())) {
            updateRequestTable((List<TimeOffRequest>) evt.getNewValue());
        }
    }

    private void updateRequestTable(List<TimeOffRequest> requests) {
        DefaultTableModel model = (DefaultTableModel) requestTable.getModel();
        model.setRowCount(0); // Clear the table first
        for (TimeOffRequest request : requests) {
            model.addRow(new Object[]{
                    request.getRequestId(),
                    request.getEmployeeId(),
                    request.getStartDate(),
                    request.getEndDate(),
                    request.getStatus() // Assuming getStatus() is in TimeOffRequest
            });
        }
    }

    @Override
    public void presentAllRequests(List<TimeOffRequest> requests) {
        updateRequestTable(requests);
    }

    @Override
    public void requestApproved(TimeOffRequest request) {
        // Update the specific request to show it is approved
        JOptionPane.showMessageDialog(this, "Request with ID: " + request.getRequestId() + " has been approved.");
        managerTimeOffRequestController.loadAllRequestsForManager(); // Refresh the request list
    }

    @Override
    public void requestDenied(TimeOffRequest request) {
        // Update the specific request to show it is denied
        JOptionPane.showMessageDialog(this, "Request with ID: " + request.getRequestId() + " has been denied.");
        managerTimeOffRequestController.loadAllRequestsForManager(); // Refresh the request list
    }

    @Override
    public void requestSubmitted(TimeOffRequest request) {
        // Display a confirmation message when a request is submitted
        JOptionPane.showMessageDialog(this, "Request with ID: " + request.getRequestId() + " has been submitted successfully.");
        managerTimeOffRequestController.loadAllRequestsForManager(); // Refresh the request list
    }
}

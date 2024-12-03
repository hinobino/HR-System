package entity;

import java.time.LocalDate;

public class TimeOffRequest {
    private String requestId;
    private String employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    public TimeOffRequest(String requestId, String employeeId, LocalDate startDate, LocalDate endDate, String status) {
        this.requestId = requestId;
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Add this method to set the approval status of the request
    public void setApproved(boolean isApproved) {
        if (isApproved) {
            this.status = "Approved";
        } else {
            this.status = "Rejected";
        }
    }

    // Add this method to determine if the request is approved
    public boolean isApproved() {
        return "Approved".equalsIgnoreCase(status);
    }
}

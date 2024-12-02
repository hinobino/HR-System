package entity;

import java.time.LocalDate;

public class TimeOffRequest {
    private final String requestId;
    private final String employeeId;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final LocalDate requestDate;
    private boolean approved;

    public TimeOffRequest(String requestId, String employeeId, LocalDate requestDate, LocalDate startDate, LocalDate endDate) {
        this.requestId = requestId;
        this.employeeId = employeeId;
        this.requestDate = requestDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approved = false;
    }

    // Getters
    public String getRequestId() { return requestId; }
    public String getEmployeeId() { return employeeId; }
    public LocalDate getRequestDate() { return requestDate; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public boolean isApproved() { return approved; }

    // Setters
    public void setApproved(boolean approved) { this.approved = approved; }
}
package use_case.time_off_request;

import java.time.LocalDate;

public class TimeOffRequestInputData {
    private final String employeeId;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public TimeOffRequestInputData(String employeeId, LocalDate startDate, LocalDate endDate) {
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getEmployeeId() { return employeeId; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
}
package use_case.manager_time_off;

/**
 * Input Data for ManagerTimeOffRequest Use Case.
 */
public class ManagerTimeOffRequestInputData {
    private final String requestId;

    public ManagerTimeOffRequestInputData(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }
}

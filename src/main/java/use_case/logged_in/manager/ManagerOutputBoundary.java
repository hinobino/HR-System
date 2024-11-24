package use_case.logged_in.manager;

public interface ManagerOutputBoundary {
    void switchToCreateEmployeeView();

    void switchToEmployeeListView();

    void switchToScheduleShiftView();
}

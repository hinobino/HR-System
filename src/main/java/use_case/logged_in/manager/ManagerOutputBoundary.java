package use_case.logged_in.manager;

import entity.Manager;

public interface ManagerOutputBoundary {
    void switchToCreateEmployeeView();

    void switchToEmployeeListView(ManagerOutputData managerOutputData);

    void switchToScheduleShiftView(ManagerOutputData managerOutputData);

    void switchToManageShiftsView(ManagerOutputData outputData);

    void openScheduleView(ManagerOutputData managerOutputData);
}

package interface_adapter.logged_in;

import use_case.logged_in.manager.ManagerInputBoundary;

public class ManagerController {

    private final ManagerInputBoundary managerInputBoundary;

    public ManagerController(ManagerInputBoundary mangerInputBoundary) {
        this.managerInputBoundary = mangerInputBoundary;
    }

    public void switchToCreateEmployeeView() {
        managerInputBoundary.switchToCreateEmployeeView();
    }

    public void switchToEmployeeListView() {
        managerInputBoundary.switchToEmployeeListView();
    }

    public void switchToScheduleShiftView() {
        managerInputBoundary.switchToScheduleShiftView();
    }

    public void switchToManageShiftsView() {
        managerInputBoundary.switchToManageShiftsView();
    }
}

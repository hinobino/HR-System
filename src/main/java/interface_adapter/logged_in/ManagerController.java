package interface_adapter.logged_in;

import use_case.logged_in.employee.EmployeeInputData;
import use_case.logged_in.manager.ManagerInputBoundary;
import use_case.logged_in.manager.ManagerInputData;

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

    public void openScheduleView(LoggedInState loggedInState) {
        final ManagerInputData managerInputData = new ManagerInputData(loggedInState);
        managerInputBoundary.openScheduleView(managerInputData);
    }

}

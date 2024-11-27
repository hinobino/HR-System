package interface_adapter.logged_in;

import use_case.logged_in.manager.ManagerInputBoundary;
import use_case.logged_in.manager.ManagerInputData;

public class ManagerController {

    private final ManagerInputBoundary managerInputBoundary;

    public ManagerController(ManagerInputBoundary mangerInputBoundary) {
        this.managerInputBoundary = mangerInputBoundary;
    }

    /**
     * Executes the switch to Create Employee View Use Case.
     */
    public void switchToCreateEmployeeView() { managerInputBoundary.switchToCreateEmployeeView(); }

    /**
     * Executes the switch to Employee List View Use Case.
     */
    public void switchToEmployeeListView() {
        managerInputBoundary.switchToEmployeeListView();
    }

    /**
     * Executes the switch to Schedule Shift View Use Case.
     */
    public void switchToScheduleShiftView() {
        managerInputBoundary.switchToScheduleShiftView();
    }

    /**
     * Executes the open Schedule View Use Case.
     *
     * @param loggedInState the state of the current logged in view
     */
    public void openScheduleView(LoggedInState loggedInState) {
        final ManagerInputData managerInputData = new ManagerInputData(loggedInState);
        managerInputBoundary.openScheduleView(managerInputData);
    }

}

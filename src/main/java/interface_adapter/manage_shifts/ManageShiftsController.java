package interface_adapter.manage_shifts;

import entity.Shift;
import use_case.manage_shifts.ManageShiftsInputBoundary;
import use_case.manage_shifts.ManageShiftsInputData;

public class ManageShiftsController {

    private final ManageShiftsInputBoundary manageShiftsInteractor;

    public ManageShiftsController(ManageShiftsInputBoundary manageShiftsInteractor) {
        this.manageShiftsInteractor = manageShiftsInteractor;
    }

    public void execute(Shift shift) {
        final ManageShiftsInputData manageShiftsInputData = new ManageShiftsInputData(shift);
        manageShiftsInteractor.execute(manageShiftsInputData);
    }

    public void switchToManagerView() {
        manageShiftsInteractor.switchToManagerView();
    }

}

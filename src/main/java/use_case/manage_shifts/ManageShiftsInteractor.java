package use_case.manage_shifts;

import entity.Employee;
import entity.Shift;
import interface_adapter.manage_shifts.ManageShiftsPresenter;

import java.time.LocalDate;

public class ManageShiftsInteractor implements ManageShiftsInputBoundary {
    private final ManageShiftsUserDataAccessInterface userDataAccessObject;
    private final ManageShiftsOutputBoundary manageShiftsPresenter;

    public ManageShiftsInteractor(ManageShiftsUserDataAccessInterface userDataAccessObject,
                                  ManageShiftsOutputBoundary manageShiftsPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.manageShiftsPresenter = manageShiftsPresenter;
    }

    @Override
    public void execute(ManageShiftsInputData manageShiftsInputData) {
        Shift shiftToDelete = manageShiftsInputData.getShift();
        LocalDate shiftDate = shiftToDelete.getDay();
        Employee shiftEmployee = shiftToDelete.getEmployee();

        userDataAccessObject.getWorkdayByDate(shiftDate).removeShift(shiftToDelete);
        shiftEmployee.removeShift(shiftToDelete);
        manageShiftsPresenter.prepareSuccessView();
    }

    @Override
    public void switchToManagerView() {
        manageShiftsPresenter.switchToManagerView();
    }
}

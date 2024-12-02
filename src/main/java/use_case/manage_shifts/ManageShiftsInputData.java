package use_case.manage_shifts;

import entity.Shift;

public class ManageShiftsInputData {

    private final Shift shift;

    public ManageShiftsInputData(Shift shift) {
        this.shift = shift;
    }

    public Shift getShift() {
        return shift;
    }

}

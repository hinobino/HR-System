package use_case.manage_shifts;

public class ManageShiftsOutputData {

    private final boolean useCaseFailed;

    public ManageShiftsOutputData(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

}

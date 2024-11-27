package interface_adapter.manage_shifts;

import interface_adapter.ViewModel;

public class ManageShiftsViewModel extends ViewModel<ManageShiftsState> {

    public static final String TITLE_LABEL = "Manage Shifts";
    public static final String[] TABLE_HEADERS = {"Employee ID", "Shift ID", "Date", "Starts At", "Ends At"};
    public static final String DELETE_SHIFT_BUTTON_LABEL = "Delete Shift";
    public static final String BACK_BUTTON_LABEL = "Back";

    public ManageShiftsViewModel() {
        super("manage shifts");
        this.setState(new ManageShiftsState());
    }

}

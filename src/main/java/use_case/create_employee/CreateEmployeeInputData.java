package use_case.create_employee;

import view.CreateEmployeeView;

/**
 * Input data for create employee use case.
 */
public class CreateEmployeeInputData {

    private final String newUserID;
    private final CreateEmployeeView view;

    public CreateEmployeeInputData(String newUserID, CreateEmployeeView view) {
        this.newUserID = newUserID;
        this.view = view;
    }

    public String getNewUserID() {
        return newUserID;
    }

    public CreateEmployeeView getView() { return view; }
}

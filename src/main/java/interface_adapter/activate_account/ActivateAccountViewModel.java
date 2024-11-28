package interface_adapter.activate_account;

import interface_adapter.ViewModel;

/**
 * View Model for the Activate Account View.
 */
public class ActivateAccountViewModel extends ViewModel<ActivateAccountState> {

    public static final String TITLE_LABEL = "Activate Account";
    public static final String USERID_LABEL = "Enter User ID";
    public static final String PASSWORD_LABEL = "Choose Password";
    public static final String REPEAT_PASSWORD_LABEL = "Confirm Password";

    public static final String ACTIVATE_ACCOUNT_BUTTON_LABEL = "Activate Account";
    public static final String TO_WELCOME_BUTTON_LABEL = "Back to Welcome Page";

    public ActivateAccountViewModel() {
        // this creates a view model with "activate account" as the title
        super("activate account");

        // this makes sure the state uses the SignupState we created
        setState(new ActivateAccountState());
    }

}

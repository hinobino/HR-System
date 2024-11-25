package data_access;

import entity.*;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.activate_account.ActivateAccountUserDataAccessInterface;
import use_case.create_employee.CreateEmployeeUserDataAccessInterface;
import use_case.employee_list.EmployeeListUserDataAccessInterface;
import use_case.logged_in.manager.ManagerUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.schedule_shift.ScheduleShiftUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * The DAO for user data.
 */
public class DBUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ActivateAccountUserDataAccessInterface,
        CreateEmployeeUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        EmployeeListUserDataAccessInterface,
        ScheduleShiftUserDataAccessInterface,
        ManagerUserDataAccessInterface {

    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String MESSAGE = "message";
    private final UserFactory userFactory;

    public DBUserDataAccessObject(UserFactory userFactory) {
        this.userFactory = userFactory;
        // No need to do anything to reinitialize a user list! The data is the cloud that may be miles away.
    }

    @Override
    public User get(String username) {
        // Make an API call to get the user object.
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", username))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                final JSONObject userJSONObject = responseBody.getJSONObject("user");
                final String name = userJSONObject.getString(USERNAME);
                final String password = userJSONObject.getString(PASSWORD);

                return userFactory.create(name, password);
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void setCurrentUserID(String name) {
    }

    /**
     * Returns a list of all the registered employee objects.
     *
     * @return a Map of type Employee, containing all registered employees.
     */
    @Override
    public Map<String, Employee> getEmployees() {
        return Map.of();
    }

    /**
     * Returns a list of all the registered employee objects.
     *
     * @return a Map of type Employee, containing all registered employees.
     */
    @Override
    public Map<String, Employee> getEmployees(String managerID) {
    }

    /**
     * Save a given Shift in its Employee's shift instance variable.
     *
     * @param newShift the Shift to save.
     */
    @Override
    public void save(Shift newShift) {

    }

    @Override
    public boolean existsByName(String username) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/checkIfUserExists?username=%s", username))
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            return responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE;
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Check if a workday taking place on the given day exists or not.
     *
     * @param day the date of the workday as a LocalDate object
     * @return true if a workday exists on the given date already.
     */
    @Override
    public boolean workdayExists(LocalDate day) {
        return false;
    }

    /**
     * Find the workday with the given date.
     *
     * @param day the date of the workday as a LocalDate object.
     * @return the Workday taking place on the given date or null if no such workday exists.
     */
    @Override
    public Workday getWorkdayByDate(LocalDate day) {
        return null;
    }

    /**
     * Add the given shift to the given Workday.
     *
     * @param newShift the shift.
     * @param workday  the workday.
     */
    @Override
    public void addShiftToWorkday(Shift newShift, Workday workday) {

    }

    @Override
    public void save(User user) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // POST METHOD
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, user.getUserID());
        requestBody.put(PASSWORD, user.getPassword());
        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/user")
                .method("POST", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // success!
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void activateUser(String userID, String password) {

    }

    @Override
    public String getCurrentUserID() {
        return null;
    }
}

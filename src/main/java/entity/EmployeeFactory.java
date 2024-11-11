package entity;

/**
 * Factory for creating new employees.
 */
public class EmployeeFactory implements UserFactory {

    @Override
    public Employee create(String userID, String password) {
        return new Employee(userID, password);
    }
}

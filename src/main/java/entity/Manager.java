package entity;

public class Manager extends User {

    public Manager(String userID, String password) {
        super(userID, password);
    }

    @Override
    public String getUserID() {
        return userID;
    }

    @Override
    public String getPassword() { return password; }

}

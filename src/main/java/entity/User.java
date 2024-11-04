package entity;

public abstract class User {
    String userid;
    String password;

    public User(String password, String userid) {
        this.password = password;
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}

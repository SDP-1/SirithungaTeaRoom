package module;

import java.util.Date;

public class User {
    private String UserName;
    private String password;
    private boolean owner;
    private String name;
    private String Nic;
    private String dob;

    public User(String userName, String password, boolean owner, String name, String nic, String dob) {
        UserName = userName;
        this.password = password;
        this.owner = owner;
        this.name = name;
        Nic = nic;
        this.dob = dob;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return Nic;
    }

    public void setNic(String nic) {
        Nic = nic;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}

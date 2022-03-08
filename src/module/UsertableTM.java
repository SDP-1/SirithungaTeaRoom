package module;

public class UsertableTM {
    private int no;
    private String fullName;
    private String userName;
    private String owner;

    public UsertableTM(int no, String fullName, String userName, String owner) {
        this.no = no;
        this.fullName = fullName;
        this.userName = userName;
        this.owner = owner;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}

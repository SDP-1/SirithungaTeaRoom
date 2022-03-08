package module;

public class Customer {

    private int cid;
    private String name;
    private String contactNo1;
    private String contactNo2;
    private String description;

    public Customer(String name, String contactNo1, String contactNo2, String description) {
        this.name = name;
        this.contactNo1 = contactNo1;
        this.contactNo2 = contactNo2;
        this.description = description;
    }

    public Customer(int cid, String name, String contactNo1, String contactNo2, String description) {
        this.cid = cid;
        this.name = name;
        this.contactNo1 = contactNo1;
        this.contactNo2 = contactNo2;
        this.description = description;
    }

    public Customer() {
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo1() {
        return contactNo1;
    }

    public void setContactNo1(String contactNo1) {
        this.contactNo1 = contactNo1;
    }

    public String getContactNo2() {
        return contactNo2;
    }

    public void setContactNo2(String contactNo2) {
        this.contactNo2 = contactNo2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cid=" + cid +
                ", name='" + name + '\'' +
                ", contactNo1='" + contactNo1 + '\'' +
                ", contactNo2='" + contactNo2 + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

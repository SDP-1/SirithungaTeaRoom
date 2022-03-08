package module;

public class CustomerTM {
    private int no;
    private int cid;
    private String name;
    private String desc;

    public CustomerTM(int no, int cid, String name, String desc) {
        this.no = no;
        this.cid = cid;
        this.name = name;
        this.desc = desc;
    }

    public CustomerTM() {
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "CustomerTM{" +
                "no=" + no +
                ", cid=" + cid +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}

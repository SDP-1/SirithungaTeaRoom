package module;

public class ManyItemTopUp {
    private int code;
    private int code2;
    private String name;
    private String printName;

    public ManyItemTopUp(int code, int code2, String name, String printName) {
        this.code = code;
        this.code2 = code2;
        this.name = name;
        this.printName = printName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode2() {
        return code2;
    }

    public void setCode2(int code2) {
        this.code2 = code2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrintName() {
        return printName;
    }

    public void setPrintName(String printName) {
        this.printName = printName;
    }
}

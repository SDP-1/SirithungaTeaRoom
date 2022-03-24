package module;

public class SalesQtyTM {
    private int code1;
    private int code2;

    private String printName;
    private Double qty;

    public SalesQtyTM() {
    }

    public SalesQtyTM(int code1, int code2, String printName, Double qty) {
        this.code1 = code1;
        this.code2 = code2;
        this.printName = printName;
        this.qty = qty;
    }

    public int getCode1() {
        return code1;
    }

    public void setCode1(int code1) {
        this.code1 = code1;
    }

    public int getCode2() {
        return code2;
    }

    public void setCode2(int code2) {
        this.code2 = code2;
    }

    public String getPrintName() {
        return printName;
    }

    public void setPrintName(String printName) {
        this.printName = printName;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "SalesQtyTM{" +
                "code1=" + code1 +
                ", code2=" + code2 +
                ", printName='" + printName + '\'' +
                ", qty=" + qty +
                '}';
    }
}

package module;

public class OrderDeatalsTM {
    private int no;
    private int code1;
    private int code2;
    private String description;
    private double qty;
    private double salePrice;
    private double nextAmount;

    public OrderDeatalsTM() {
    }

    public OrderDeatalsTM(int no, int code1, int code2, String description, double qty, double salePrice, double nextAmount) {
        this.no = no;
        this.code1 = code1;
        this.code2 = code2;
        this.description = description;
        this.qty = qty;
        this.salePrice = salePrice;
        this.nextAmount = nextAmount;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getNextAmount() {
        return nextAmount;
    }

    public void setNextAmount(double nextAmount) {
        this.nextAmount = nextAmount;
    }

    @Override
    public String toString() {
        return "OrderDeatalsTM{" +
                "no=" + no +
                ", code1=" + code1 +
                ", code2=" + code2 +
                ", description='" + description + '\'' +
                ", qty=" + qty +
                ", salePrice=" + salePrice +
                ", nextAmount=" + nextAmount +
                '}';
    }
}

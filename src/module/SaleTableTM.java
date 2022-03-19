package module;

public class SaleTableTM {

    private Integer no;
    private int itemCode;
    private String description;
    private double qty;
    private double markPrice;
    private double price;
    private double nextAmount;
    private int code2;
    private boolean decimal;
    private double buyingPrice;

    public SaleTableTM(int no, int itemCode, String description, double qty, double markPrice, double price, double nextAmount, int code2, boolean decimal,double buyingPrice) {
        this.no = no;
        this.itemCode = itemCode;
        this.description = description;
        this.qty = qty;
        this.markPrice = markPrice;
        this.price = price;
        this.nextAmount = nextAmount;
        this.code2 = code2;
        this.decimal = decimal;
        this.buyingPrice = buyingPrice;
    }

    public boolean isDecimal() {
        return decimal;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public void setDecimal(boolean decimal) {
        this.decimal = decimal;
    }

    public int getCode2() {
        return code2;
    }

    public void setCode2(int code2) {
        this.code2 = code2;
    }

    public SaleTableTM(int no, int itemCode, String description, double qty, double markPrice, double price, double nextAmount, int code2) {
        this.no = no;
        this.itemCode = itemCode;
        this.description = description;
        this.qty = qty;
        this.markPrice = markPrice;
        this.price = price;
        this.nextAmount = nextAmount;
        this.code2 = code2;
    }

    public SaleTableTM(int no, int itemCode, String description, double qty, double markPrice, double price, double nextAmount) {
        this.no = no;
        this.itemCode = itemCode;
        this.description = description;
        this.qty = qty;
        this.markPrice = markPrice;
        this.price = price;
        this.nextAmount = nextAmount;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
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

    public double getMarkPrice() {
        return markPrice;
    }

    public void setMarkPrice(double markPrice) {
        this.markPrice = markPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getNextAmount() {
        return nextAmount;
    }

    public void setNextAmount(double nextAmount) {
        this.nextAmount = nextAmount;
    }
}

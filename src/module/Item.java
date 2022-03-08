package module;

public class Item {
    private double code;
    private int code2;
    private String name;
    private String printName;
    private String barCode;
    private String Supplier;
    private double stock;
    private boolean decimal;

    private double priceOfBuying;
    private double markPrice;
    private double retailPriceRatio;
    private double wholeSalePriceRatio;
    private double retailPrice;
    private double wholeSalePrice;

    public Item(double code,int code2, String name, String printName, String barCode, String supplier, double stock, boolean decimal, double priceOfBuying, double markPrice, double retailPriceRatio, double wholeSalePriceRatio, double retailPrice, double wholeSalePrice) {
        this.code = code;
        this.code2 = code2;
        this.name = name;
        this.printName = printName;
        this.barCode = barCode;
        Supplier = supplier;
        this.stock = stock;
        this.decimal = decimal;
        this.priceOfBuying = priceOfBuying;
        this.markPrice = markPrice;
        this.retailPriceRatio = retailPriceRatio;
        this.wholeSalePriceRatio = wholeSalePriceRatio;
        this.retailPrice = retailPrice;
        this.wholeSalePrice = wholeSalePrice;
    }

    public int getCode2() {
        return code2;
    }

    public void setCode2(int code2) {
        this.code2 = code2;
    }

    public double getCode() {
        return code;
    }

    public void setCode(double code) {
        this.code = code;
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

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getSupplier() {
        return Supplier;
    }

    public void setSupplier(String supplier) {
        Supplier = supplier;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public boolean isDecimal() {
        return decimal;
    }

    public void setDecimal(boolean decimal) {
        this.decimal = decimal;
    }

    public double getPriceOfBuying() {
        return priceOfBuying;
    }

    public void setPriceOfBuying(double priceOfBuying) {
        this.priceOfBuying = priceOfBuying;
    }

    public double getMarkPrice() {
        return markPrice;
    }

    public void setMarkPrice(double markPrice) {
        this.markPrice = markPrice;
    }

    public double getRetailPriceRatio() {
        return retailPriceRatio;
    }

    public void setRetailPriceRatio(double retailPriceRatio) {
        this.retailPriceRatio = retailPriceRatio;
    }

    public double getWholeSalePriceRatio() {
        return wholeSalePriceRatio;
    }

    public void setWholeSalePriceRatio(double wholeSalePriceRatio) {
        this.wholeSalePriceRatio = wholeSalePriceRatio;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public double getWholeSalePrice() {
        return wholeSalePrice;
    }

    public void setWholeSalePrice(double wholeSalePrice) {
        this.wholeSalePrice = wholeSalePrice;
    }
}

package module;

import javafx.scene.control.CheckBox;

public class StockItem {
    private int code1;
    private int code2;
    private String barcode;
    private String name;
    private double stock;
    private double markPrice;
    private String suplier;
    private CheckBox checkBox;

    public StockItem(int code1, int code2, String barcode, String name, double stock, double markPrice, String suplier, CheckBox checkBox) {
        this.code1 = code1;
        this.code2 = code2;
        this.barcode = barcode;
        this.name = name;
        this.stock = stock;
        this.markPrice = markPrice;
        this.suplier = suplier;
        this.checkBox = checkBox;
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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public double getMarkPrice() {
        return markPrice;
    }

    public void setMarkPrice(double markPrice) {
        this.markPrice = markPrice;
    }

    public String getSuplier() {
        return suplier;
    }

    public void setSuplier(String suplier) {
        this.suplier = suplier;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public boolean isSelect(){
       return this.checkBox.isSelected();
    }

    public void setSelected(boolean b){
        this.checkBox.setSelected(b);
    }

    @Override
    public String toString() {
        return "StockItem{" +
                "code1=" + code1 +
                ", code2=" + code2 +
                ", barcode='" + barcode + '\'' +
                ", name='" + name + '\'' +
                ", stock=" + stock +
                ", markPrice=" + markPrice +
                ", suplier='" + suplier + '\'' +
                ", checkBox=" + checkBox +
                '}';
    }
}

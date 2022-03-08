package module;

import javafx.scene.control.CheckBox;

public class StockSelectItem {
    private int no;
    int code1;
    int code2;
    private String name;
    private double stock;
    private CheckBox checkBox;

    public StockSelectItem(int no, int code1, int code2, String name, double stock, CheckBox checkBox) {
        this.no = no;
        this.code1 = code1;
        this.code2 = code2;
        this.name = name;
        this.stock = stock;
        this.checkBox = checkBox;
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

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public boolean isSelect(){
        return this.checkBox.isSelected();
    }

    @Override
    public String toString() {
        return "StockSelectItem{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", stock=" + stock +
                ", checkBox=" + checkBox +
                '}';
    }
}

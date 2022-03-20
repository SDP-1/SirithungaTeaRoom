package module;

import javafx.scene.control.CheckBox;

public class StockItemPrint {
    private Integer no;
    private String name;
    private String stock;

    public StockItemPrint(Integer no) {
        this.no = no;
    }

    public StockItemPrint(Integer no, String name, String stock) {
        this.no = no;
        this.name = name;
        this.stock = stock;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "StockItemPrint{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", stock='" + stock + '\'' +
                '}';
    }
}

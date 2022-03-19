package module;

public class BillStructure {

    private int no;
    private String description;
    private String qty;
    private String price;
    private String nextAmount;

    public BillStructure() {
    }

    public BillStructure(int no, String description, String qty, String price, String nextAmount) {
        this.no = no;
        this.description = description;
        this.qty = qty;
        this.price = price;
        this.nextAmount = nextAmount;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNextAmount() {
        return nextAmount;
    }

    public void setNextAmount(String nextAmount) {
        this.nextAmount = nextAmount;
    }

    @Override
    public String toString() {
        return "BillStructure{" +
                "no=" + no +
                ", description='" + description + '\'' +
                ", qty='" + qty + '\'' +
                ", price='" + price + '\'' +
                ", nextAmount='" + nextAmount + '\'' +
                '}';
    }
}

package module;

public class SaleFormLabelDataOrder {
    private int invoiceNo;
    private String date;
    private String time;
    private String customer;
    private double totalCost;
    private int noOfItem;

    public SaleFormLabelDataOrder() {
    }

    public SaleFormLabelDataOrder(int invoiceNo, String date, String time, String customer, double totalCost, int noOfItem) {
        this.invoiceNo = invoiceNo;
        this.date = date;
        this.time = time;
        this.customer = customer;
        this.totalCost = totalCost;
        this.noOfItem = noOfItem;
    }

    public int getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(int invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getNoOfItem() {
        return noOfItem;
    }

    public void setNoOfItem(int noOfItem) {
        this.noOfItem = noOfItem;
    }

    @Override
    public String toString() {
        return "SaleFormLabelDataOrder{" +
                "invoiceNo=" + invoiceNo +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", customer='" + customer + '\'' +
                ", totalCost=" + totalCost +
                ", noOfItem=" + noOfItem +
                '}';
    }
}

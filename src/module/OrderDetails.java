package module;

public class OrderDetails {
    private int invoiceNo;
    private String date;
    private String time;
    private String customerName;
    private boolean retail;
    private int noOfItem;
    private double fullCost;
    private double cash;
    private double balance;
    private boolean onLoan;
    private double discount;

    public OrderDetails() {
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public OrderDetails(int invoiceNo, String date, String time, String customerName, boolean retail, int noOfItem, double fullCost, double cash, double balance, boolean onLoan, double discount) {
        this.invoiceNo = invoiceNo;
        this.date = date;
        this.time = time;
        this.customerName = customerName;
        this.retail = retail;
        this.noOfItem = noOfItem;
        this.fullCost = fullCost;
        this.cash = cash;
        this.balance = balance;
        this.onLoan = onLoan;
        this.discount = discount;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public boolean isRetail() {
        return retail;
    }

    public void setRetail(boolean retail) {
        this.retail = retail;
    }

    public int getNoOfItem() {
        return noOfItem;
    }

    public void setNoOfItem(int noOfItem) {
        this.noOfItem = noOfItem;
    }

    public double getFullCost() {
        return fullCost;
    }

    public void setFullCost(double fullCost) {
        this.fullCost = fullCost;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isOnLoan() {
        return onLoan;
    }

    public void setOnLoan(boolean onLoan) {
        this.onLoan = onLoan;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "invoiceNo=" + invoiceNo +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", customerName='" + customerName + '\'' +
                ", retail=" + retail +
                ", noOfItem=" + noOfItem +
                ", fullCost=" + fullCost +
                ", cash=" + cash +
                ", balance=" + balance +
                ", onLoan=" + onLoan +
                ", discount=" + discount +
                '}';
    }
}

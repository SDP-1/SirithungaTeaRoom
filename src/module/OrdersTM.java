package module;

public class OrdersTM {
    private int no;
    private int invoiceNo;
    private String date;
    private String time;
    private String customerName;
    private String onLoan;

    public OrdersTM() {
    }

    public OrdersTM(int no, int invoiceNo, String date, String time, String customerName, String onLoan) {
        this.no = no;
        this.invoiceNo = invoiceNo;
        this.date = date;
        this.time = time;
        this.customerName = customerName;
        this.onLoan = onLoan;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
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

    public String getOnLoan() {
        return onLoan;
    }

    public void setOnLoan(String onLoan) {
        this.onLoan = onLoan;
    }

    @Override
    public String toString() {
        return "OrdersTM{" +
                "no=" + no +
                ", invoiceNo=" + invoiceNo +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", customerName='" + customerName + '\'' +
                ", onLoan='" + onLoan + '\'' +
                '}';
    }
}

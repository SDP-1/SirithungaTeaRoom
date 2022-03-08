package module;

public class DeleteReq {

    private int invoiceNo;
    private String date;
    private String time;

    public DeleteReq() {
    }

    public DeleteReq(int invoiceNo, String date, String time) {
        this.invoiceNo = invoiceNo;
        this.date = date;
        this.time = time;
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

    @Override
    public String toString() {
        return "DeleteReq{" +
                "invoiceNo=" + invoiceNo +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}

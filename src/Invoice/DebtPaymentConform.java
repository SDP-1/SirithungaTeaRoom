package Invoice;

import javafx.collections.ObservableList;
import module.OrderDeatalsTM;
import module.OrderDetails;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DebtPaymentConform implements Printable {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private ObservableList<OrderDeatalsTM> list;
    private OrderDetails orderDetails;

    public DebtPaymentConform() {
    }

    public DebtPaymentConform(OrderDetails orderDetails, ObservableList<OrderDeatalsTM> list) {
        this.list = list;
        this.orderDetails = orderDetails;
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {

//        int r= itemName.size();
//        ImageIcon icon=new ImageIcon("C:UsersccsDocumentsNetBeansProjectsvideo TestPOSInvoicesrcposinvoicemylogo.jpg");
        int result = NO_SUCH_PAGE;
        if (pageIndex == 0) {

            Graphics2D g2d = (Graphics2D) graphics;
            double width = pageFormat.getImageableWidth();
            g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());


            //  FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));

            try {
                int y = 20;
                int yShift = 10;
                int headerRectHeight = 15;
                // int headerRectHeighta=40;

                g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
//                g2d.drawImage(icon.getImage(), 50, 20, 90, 30, rootPane);y+=yShift+30;
                g2d.setFont(new Font("Monospaced", Font.BOLD, 10));
                g2d.drawString("---------------------------------------", 12, y);
                y += yShift;
                g2d.drawString("         SIRITHUNGA GROCERY     ", 12, y);
                y += yShift;
                g2d.drawString("       Nalagasdeniya,Hikkaduwa   ", 12, y);
                y += yShift;
                g2d.drawString("          Tel - +94912276011       ", 12, y);
                y += yShift;
                g2d.drawString("---------------------------------------", 12, y);
                y += headerRectHeight;
                g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));

                DateFormat dtf1 = new SimpleDateFormat("yyyy/MM/dd");
                DateFormat dtf2 = new SimpleDateFormat("hh:mm aa");
                Date date = new Date();

                g2d.drawString(" Date       - " + dtf1.format(date), 10, y);
                y += yShift;
                g2d.drawString(" Time       - " + dtf2.format(date), 10, y);
                y += yShift;

                g2d.drawString("----------------------------------------", 10, y);
                y += yShift;
                g2d.setFont(new Font("Monospaced", Font.BOLD, 9));
                g2d.drawString("    DEBT PAYMENT CONFIRMATION REPORT", 10, y);
                y += yShift;
                g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
                g2d.drawString("----------------------------------------", 10, y);
                y += yShift;

                g2d.drawString(" Invoice No    - " + orderDetails.getInvoiceNo(), 10, y);
                y += yShift;
                g2d.drawString(" Invoice Date  - " + orderDetails.getDate(), 10, y);
                y += yShift;
                g2d.drawString(" Invoice Time  - " + orderDetails.getTime(), 10, y);
                y += yShift;
                if (!(orderDetails.getCustomerName().isEmpty() || orderDetails.getCustomerName().equals(" "))) {
                    g2d.drawString(" Customer      - " + orderDetails.getCustomerName(), 10, y);
                    y += yShift;
                }
                y += yShift;

                g2d.drawString(" Total Amount       -  " + df.format(orderDetails.getFullCost()) + "   ", 10, y);
                y += yShift;
                g2d.drawString(" Amount of arrears  -  " + df.format(-orderDetails.getBalance()) + "   ", 10, y);
                y += yShift;
                y += yShift;

                g2d.setFont(new Font("Monospaced", Font.BOLD, 9));
                g2d.drawString(" * Invoice " + orderDetails.getInvoiceNo() + ",", 10, y);
                y += yShift;
                g2d.drawString("   arrears paid Rs." + df.format(-orderDetails.getBalance()), 10, y);
                y += yShift;
                g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));


                g2d.drawString("***************************************", 10, y);
                y += yShift;
                g2d.drawString("          THANK YOU COME AGAIN       ", 10, y);
                y += yShift;
                g2d.drawString("***************************************", 10, y);
                y += 5;

                g2d.setFont(new Font("Monospaced", Font.PLAIN, 7));
                g2d.drawString("SOLUTION BY:Sehan Devinda", 63, y);
                y += 7;
                g2d.drawString("CONTACT: +94772829780", 72, y);
                y += 5;

            } catch (Exception e) {
                e.printStackTrace();
            }

            result = PAGE_EXISTS;
        }
        return result;
    }
}

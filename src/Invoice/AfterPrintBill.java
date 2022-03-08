package Invoice;

import javafx.collections.ObservableList;
import module.OrderDeatalsTM;
import module.OrderDetails;
import module.SaleTableTM;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;

public class AfterPrintBill implements Printable{

    private ObservableList<OrderDeatalsTM> list;
    private OrderDetails orderDetails;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public AfterPrintBill() {
    }

        public AfterPrintBill(OrderDetails orderDetails ,ObservableList<OrderDeatalsTM> list) {
        this.list = list;
        this.orderDetails = orderDetails;
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException
    {

//        int r= itemName.size();
//        ImageIcon icon=new ImageIcon("C:UsersccsDocumentsNetBeansProjectsvideo TestPOSInvoicesrcposinvoicemylogo.jpg");
        int result = NO_SUCH_PAGE;
        if (pageIndex == 0) {

            Graphics2D g2d = (Graphics2D) graphics;
            double width = pageFormat.getImageableWidth();
            g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY());



            //  FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));

            try{
                int y=20;
                int yShift = 10;
                int headerRectHeight=15;
                // int headerRectHeighta=40;

                g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
//                g2d.drawImage(icon.getImage(), 50, 20, 90, 30, rootPane);y+=yShift+30;
                g2d.setFont(new Font("Monospaced",Font.BOLD,10));
                g2d.drawString("---------------------------------------",12,y);y+=yShift;
                g2d.drawString("         SIRITHUNGA GROCERY     ",12,y);y+=yShift;
                g2d.drawString("       Nalagasdeniya,Hikkaduwa   ",12,y);y+=yShift;
                g2d.drawString("          Tel - +94912276011       ",12,y);y+=yShift;
                g2d.drawString("---------------------------------------",12,y);y+=headerRectHeight;

                g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
                g2d.drawString(" Invoice No - " +orderDetails.getInvoiceNo(),10,y);y+=yShift;
                if(!(orderDetails.getCustomerName().isEmpty() || orderDetails.getCustomerName().equals(" "))){
                    g2d.drawString(" Customer   - " +orderDetails.getCustomerName(),10,y);y+=yShift;
                }
                g2d.drawString(" Date       - " +orderDetails.getDate(),10,y);y+=yShift;
                g2d.drawString(" Time       - " +orderDetails.getTime(),10,y);y+=yShift;
                g2d.drawString("----------------------------------------",10,y);y+=headerRectHeight;
                g2d.drawString("        QTY       Price       Amount",10,y);y+=yShift;
                g2d.drawString("----------------------------------------",10,y);y+=headerRectHeight;

                for(int i=0; i<list.size(); i++)
                {
                    OrderDeatalsTM tm = list.get(i);
                    g2d.drawString(" "+tm.getNo()+" "+tm.getDescription(),10,y);y+=yShift;
                    g2d.drawString("      "+df.format(tm.getQty()),20,y);
                    g2d.drawString("      "+df.format(tm.getSalePrice()),75,y);
                    g2d.drawString("      "+df.format(tm.getNextAmount()),140,y);
                    y+=yShift;
                }

                g2d.drawString("---------------------------------------",10,y);y+=yShift;
                g2d.drawString(" QTY          :  "+df.format(orderDetails.getNoOfItem())+"   ",10,y);y+=yShift;
                g2d.drawString(" Paid statment:  "+String.valueOf(orderDetails.isOnLoan()?"Loan":"Paid")+"   ",10,y);y+=yShift;
                y+=yShift;

                g2d.drawString(" Total amount :  "+df.format(orderDetails.getFullCost())+"   ",10,y);y+=yShift;
//                g2d.drawString("-------------------------------------",10,y);y+=yShift;
                if(orderDetails.getDiscount()>0) {
                    g2d.setFont(new Font("Monospaced", Font.BOLD, 9));
                    g2d.drawString(" Discount     :  " + df.format(orderDetails.getDiscount()) + "   ", 10, y);
                    y += 20;
                    g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
                }

                g2d.drawString(" Cash         :  "+df.format(orderDetails.getCash())+"   ",10,y);y+=yShift;
//                g2d.drawString("-------------------------------------",10,y);y+=yShift;
                g2d.drawString(" Balance      :  "+df.format(orderDetails.getBalance())+"   ",10,y);y+=yShift;

                g2d.drawString("***************************************",10,y);y+=yShift;
                g2d.drawString("          THANK YOU COME AGAIN       ",10,y);y+=yShift;
                g2d.drawString("***************************************",10,y);y+=5;

                g2d.setFont(new Font("Monospaced",Font.PLAIN,7));
                g2d.drawString("SOLUTION BY:Sehan Devinda", 63, y);y+=7;
                g2d.drawString("CONTACT: +94772829780",72,y);y+=5;

            }
            catch(Exception e){
                e.printStackTrace();
            }

            result = PAGE_EXISTS;
        }
        return result;
    }

}

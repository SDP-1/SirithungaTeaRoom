//package contoller.mainTask;
//
//import Invoice.Bill;
//import Query.OrderDetailTableQuery;
//import Query.OrderTableQuery;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import module.OrderDeatalsTM;
//import module.OrderDetails;
//import module.SaleTableTM;
//
//import java.awt.print.PageFormat;
//import java.awt.print.Paper;
//import java.awt.print.PrinterException;
//import java.awt.print.PrinterJob;
//import java.sql.SQLException;
//
//public class Demo {
//    public static ObservableList<OrderDeatalsTM> list;
//    public static OrderDetails orderDetails= null;
//    public static void main(String[]args){
//        try {
//            orderDetails = OrderTableQuery.getOrderDeatil(1023);
//            list = OrderDetailTableQuery.getOrderDeatils(1023);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        double bHeight = Double.valueOf(list.size());
////                    JOptionPane.showMessageDialog(rootPane, bHeight);
//
//        PrinterJob pj = PrinterJob.getPrinterJob();
//        pj.setPrintable(new Bill(orderDetails,list),getPageFormat(pj,bHeight));
//        try {
//            pj.print();
//
//        }
//        catch (PrinterException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public static PageFormat getPageFormat(PrinterJob pj, double bHeight)
//    {
//
//        PageFormat pf = pj.defaultPage();
//        Paper paper = pf.getPaper();
//
//        double bodyHeight = bHeight;
//        double headerHeight = 5.0;
//        double footerHeight = 5.0;
//        double width = cm_to_pp(8);
//        double height = cm_to_pp(headerHeight+bodyHeight+footerHeight);
//        paper.setSize(width, height);
//        paper.setImageableArea(0,10,width,height - cm_to_pp(1));
//
//        pf.setOrientation(PageFormat.PORTRAIT);
//        pf.setPaper(paper);
//
//        return pf;
//    }
//
//    protected static double cm_to_pp(double cm)
//    {
//        return toPPI(cm * 0.393600787);
//    }
//
//    protected static double toPPI(double inch)
//    {
//        return inch * 72d;
//    }
//}

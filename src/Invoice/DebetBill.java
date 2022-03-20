package Invoice;

import contoller.LoginPageFormContoller;
import javafx.collections.ObservableList;
import module.BillStructure;
import module.OrderDetails;
import module.SaleTableTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;

public class DebetBill {
    private final DecimalFormat df = new DecimalFormat("0.00");

    public void print(OrderDetails orderDetails){
        LinkedHashMap map = new LinkedHashMap<>();
        map.put("Date", new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()));
        map.put("Time",new SimpleDateFormat("hh:mm:ss aa").format(Calendar.getInstance().getTime()));
        map.put("invoiceNo",String.valueOf(orderDetails.getInvoiceNo()));
        map.put("invoiceDate",orderDetails.getDate());
        map.put("invoiceTime",orderDetails.getTime());
        map.put("netAmount",df.format(orderDetails.getFullCost()));
        map.put("amountOfArrers",df.format(orderDetails.getFullCost()-orderDetails.getCash()));

        try {
            JasperReport compileReport = (JasperReport) JRLoader.loadObject(LoginPageFormContoller.class.getResource("../Invoice/DebtPayment.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, map ,new JREmptyDataSource());
            JasperViewer.viewReport(jasperPrint,false);
//            JasperPrintManager.printReport(jasperPrint,false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}

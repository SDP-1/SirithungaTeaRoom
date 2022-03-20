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
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MainBill {
    private final DecimalFormat df = new DecimalFormat("0.00");

    public void print(OrderDetails orderDetails , ObservableList<SaleTableTM> list){
        //-------------Start of print Bill--------------------------------------

        LinkedHashMap map = new LinkedHashMap<>();
        map.put("invoceNo",orderDetails.getInvoiceNo());
        map.put("date",orderDetails.getDate());
        map.put("time",orderDetails.getTime());
        map.put("tiinvoceNome",String.valueOf(orderDetails.getInvoiceNo()));
        map.put("Cashiyer",orderDetails.getCashiyer());

        map.put("QTY",orderDetails.getNoOfItem());
        map.put("PaidStatment",orderDetails.isOnLoan()?"LOAN" : "paid");
        map.put("TotalAmount",df.format(orderDetails.getFullCost()));
        map.put("Discount",df.format(orderDetails.getDiscount()));
        map.put("Cash",df.format(orderDetails.getCash()));
        map.put("Balance",df.format(orderDetails.getBalance()));

        ArrayList<BillStructure> detils = new ArrayList<>();
        for (SaleTableTM tm : list){
            detils.add(new BillStructure(
                    tm.getNo(),
                    tm.getDescription(),
                    df.format(tm.getQty()),
                    tm.getPrice() < tm.getMarkPrice()? "*"+df.format(tm.getPrice()) : df.format(tm.getPrice()),
                    df.format(tm.getNextAmount())
            ));
        }

        try {
            JasperReport compileReport = (JasperReport) JRLoader.loadObject(LoginPageFormContoller.class.getResource("../Invoice/mainBillWithDescount.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, map, new JRBeanCollectionDataSource(detils));
                        JasperViewer.viewReport(jasperPrint,false);
//            JasperPrintManager.printReport(jasperPrint,false);
        } catch (JRException e) {
            e.printStackTrace();
        }

        //-------------------end of print bill--------------------------------
    }
}

package Invoice;

import contoller.LoginPageFormContoller;
import javafx.collections.ObservableList;
import module.StockItemPrint;
import module.StockSelectItem;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;

public class StockManageBill {
    public static String username;
    private final DecimalFormat df = new DecimalFormat("0.00");

    public void print(ObservableList<StockSelectItem> list) {
        //-------------Start of print Bill--------------------------------------

        LinkedHashMap map = new LinkedHashMap<>();
        map.put("date", new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()));
        map.put("time", new SimpleDateFormat("hh:mm:ss aa").format(Calendar.getInstance().getTime()));
        map.put("Cashiyer", username);

        ArrayList<StockItemPrint> stockItemPrints = new ArrayList<>();
        int i = 1;
        for (StockSelectItem item : list) {
            stockItemPrints.add(new StockItemPrint(
                    i++,
                    item.getName(),
                    df.format(item.getStock())
            ));
        }

        try {
            JasperReport compileReport = (JasperReport) JRLoader.loadObject(LoginPageFormContoller.class.getResource("../Invoice/stockManage.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, map, new JRBeanCollectionDataSource(stockItemPrints));
            JasperViewer.viewReport(jasperPrint, false);
//            JasperPrintManager.printReport(jasperPrint,false);
        } catch (JRException e) {
            e.printStackTrace();
        }

        //-------------------end of print bill--------------------------------
    }
}

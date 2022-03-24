package Query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import module.*;
import org.omg.CosNaming.IstringHelper;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.util.*;

public class OrderTableQuery {
    private static LinkedHashMap<String , Integer> monthNumber = new LinkedHashMap();


    public static int getNewInvoiceNo() throws SQLException, ClassNotFoundException {

//        ResultSet resultSet = CrudUtil.excecute("SELECT invoiceNo FROM `order`;");
//        ArrayList<Integer> ar = new ArrayList();
//        while (resultSet.next()){
//            ar.add(resultSet.getInt(1));
//        }
//        for (int i = 1000; i < Integer.MAX_VALUE; i++) {
//            if(!ar.contains(i)) return i;
//        }
//        return -1;

        ResultSet resultSet = CrudUtil.excecute("SELECT invoiceNo FROM `order` ORDER BY invoiceNo DESC LIMIT 1;");
        if(resultSet.next()){
            return resultSet.getInt(1)+1;
        }
        return 1000;
    }

    public static boolean saveOrder(OrderDetails od) throws SQLException, ClassNotFoundException {
        boolean b = CrudUtil.excecute("INSERT INTO `order` VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)",
                od.getInvoiceNo(),
                od.getDate(),
                od.getTime(),
                od.getCustomerName(),
                od.isRetail(),
                od.getNoOfItem(),
                od.getFullCost(),
                od.getCash(),
                od.getBalance(),
                od.isOnLoan(),
                od.getDiscount(),
                od.getTotalBingPrice(),
                od.getCashiyer()
        );
        setUpper();
        return b;
    }

    public static  ArrayList<SaleFormLabelDataOrder> getLastBillsDataForLable() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.excecute("SELECT invoiceNo,date,time,customerName,fullCost,noOfItem FROM `order` ORDER BY  invoiceNo DESC LIMIT 5;" );
        ArrayList<SaleFormLabelDataOrder> list = new ArrayList<>();
        while (resultSet.next()){
            list.add(new SaleFormLabelDataOrder(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getInt(6)
            ));
        }
        return list;
    }

     public static boolean deleteOrder(int invoiceNo) throws SQLException, ClassNotFoundException {
        boolean delete = false;
         ResultSet resultSet = CrudUtil.excecute("SELECT code1,code2,qty FROM orderdetail WHERE invoiceNo=?", invoiceNo);
         while (resultSet.next()){
             ItemTableQuery.updateQty(-resultSet.getInt(3) , resultSet.getInt(1) ,resultSet.getInt(2));
         }
        boolean isdelete = CrudUtil.excecute("DELETE FROM orderdetail WHERE invoiceNo=?",invoiceNo);
        if(isdelete) {
            delete = CrudUtil.excecute("DELETE FROM `order` WHERE invoiceNo=?", invoiceNo);
            boolean have = DeleteReqBillTableQuery.isReqHave(invoiceNo);
            if(have){
                DeleteReqBillTableQuery.reqDelete(invoiceNo);
            }
        }
        return delete;
     }

    public static ObservableList<OrdersTM> getLastTodayOrders() throws SQLException, ClassNotFoundException {
        ObservableList<OrdersTM> list = FXCollections.observableArrayList();
        ResultSet resultSet  =CrudUtil.excecute("SELECT invoiceNo,date,time,customerName,Onloan  FROM `order` WHERE date > now() - INTERVAL 1 day ORDER BY invoiceNo DESC;");
        int i=1;
        while (resultSet.next()){
            list.add(new OrdersTM(
                    i++,
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getBoolean(5)? "*" :""
            ));
        }
        return list;
    }

     public static ObservableList<OrdersTM> getLast7DaysOrders() throws SQLException, ClassNotFoundException {
        ObservableList<OrdersTM> list = FXCollections.observableArrayList();
        ResultSet resultSet  =CrudUtil.excecute("SELECT invoiceNo,date,time,customerName,Onloan  FROM `order` WHERE date > now() - INTERVAL 7 day ORDER BY invoiceNo DESC;");
        int i=1;
        while (resultSet.next()){
            list.add(new OrdersTM(
                    i++,
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getBoolean(5)? "*" :""
            ));
        }
        return list;
     }

    public static ObservableList<OrdersTM> getLast30DaysOrders() throws SQLException, ClassNotFoundException {
        ObservableList<OrdersTM> list = FXCollections.observableArrayList();
        ResultSet resultSet  =CrudUtil.excecute("SELECT invoiceNo,date,time,customerName,Onloan  FROM `order` WHERE date > now() - INTERVAL 30 day ORDER BY invoiceNo DESC;");
        int i=1;
        while (resultSet.next()){
            list.add(new OrdersTM(
                    i++,
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getBoolean(5)? "*" :""
            ));
        }
        return list;
    }

    public static ObservableList<OrdersTM> getLastAllLoanOrders() throws SQLException, ClassNotFoundException {
        ObservableList<OrdersTM> list = FXCollections.observableArrayList();
        ResultSet resultSet  =CrudUtil.excecute("SELECT invoiceNo,date,time,customerName,Onloan  FROM `order`WHERE Onloan=true ORDER BY invoiceNo DESC;");
        int i=1;
        while (resultSet.next()){
            list.add(new OrdersTM(
                    i++,
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getBoolean(5)? "*" :""
            ));
        }
        return list;
    }

    public static ObservableList<OrdersTM> getLast3MonthsOrders() throws SQLException, ClassNotFoundException {
        ObservableList<OrdersTM> list = FXCollections.observableArrayList();
        ResultSet resultSet  =CrudUtil.excecute("SELECT invoiceNo,date,time,customerName,Onloan  FROM `order` WHERE date >= now()-interval 3 month ORDER BY invoiceNo DESC;");
        int i=1;
        while (resultSet.next()){
            list.add(new OrdersTM(
                    i++,
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getBoolean(5)? "*" :""
            ));
        }
        return list;
    }

    public static OrderDetails getOrderDeatil(int invoiceNo) throws SQLException, ClassNotFoundException {
        ResultSet resultSet  = CrudUtil.excecute("SELECT * FROM `order` WHERE invoiceNo=?",invoiceNo);
        if(resultSet.next()){
            return new OrderDetails(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getBoolean(5),
                    resultSet.getInt(6),
                    resultSet.getDouble(7),
                    resultSet.getDouble(8),
                    resultSet.getDouble(9),
                    resultSet.getBoolean(10),
                    resultSet.getDouble(11),
                    resultSet.getDouble(12),
                    resultSet.getString(13)
            );
        }
        return null;
    }

    public static void setLoanPaid(int invoiceNo) throws SQLException, ClassNotFoundException {
        CrudUtil.excecute("UPDATE `order` SET cash=fullCost,balance=0,Onloan=false WHERE invoiceNo=?;",invoiceNo);
    }

    public static void setUpper(){
        try {
            CrudUtil.excecute("UPDATE `order` SET customerName = UPPER(customerName);");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<OrdersTM> getDeleteReqDeatils(){
        ObservableList<OrdersTM> list = FXCollections.observableArrayList();

        ArrayList<Integer> indexs = DeleteReqBillTableQuery.getDeleteReqBills();
        int i=1;
        for (int x : indexs){
            try {
            ResultSet resultSet = CrudUtil.excecute("SELECT invoiceNo,date,time,customerName,Onloan  FROM `order` WHERE invoiceNo=? ORDER BY invoiceNo DESC;",x);
                if (resultSet.next()){
                    list.add(new OrdersTM(
                            i++,
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getBoolean(5)? "*" :""
                    ));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static boolean isOrderHave(int index){
        boolean ishave = false;
        try {
            ResultSet resultSet = CrudUtil.excecute("SELECT COUNT(invoiceNo) FROM `order` WHERE invoiceNo=?",index);
            if(resultSet.next()){
                if(resultSet.getInt(1)==1) ishave=true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ishave;
    }

    public static int getTotalOrderCount(){
        int count =0;
        try {
            ResultSet resultSet = CrudUtil.excecute("SELECT COUNT(invoiceNo) FROM `order`");

            if(resultSet.next()){
            count =resultSet.getInt(1);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
            return count;
    }

    public static ObservableList<Integer> getSystemHaveYears(){
        ObservableList<Integer> years =FXCollections.observableArrayList();

        try {
            ResultSet resultSet = CrudUtil.excecute("SELECT DISTINCT year(date) FROM `order` ORDER BY year(date) DESC ");
            while (resultSet.next()){
                int x =resultSet.getInt(1);
                years.add(resultSet.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return years;
    }

    public static ObservableList<String> getSelectedYearMonths(int year){
        ObservableList<String> months =FXCollections.observableArrayList();
        months.add("");

        try {
            ResultSet resultSet = CrudUtil.excecute("SELECT DISTINCT MONTHNAME(date) FROM `order` WHERE year(date)=?",year);

            while (resultSet.next()){
                months.add(resultSet.getString(1));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return months;
    }

    public static ObservableList<String> getOrdersAvbledates(String month,String year){
        ObservableList<String> dates =FXCollections.observableArrayList();
        dates.add("");

        try {
            ResultSet resultSet = CrudUtil.excecute("SELECT DISTINCT DATE_FORMAT(date,'%d') FROM `order` WHERE MONTHNAME(date)=? AND year(date)=?",
                    month,
                    year
            );

            while (resultSet.next()){
                dates.add(resultSet.getString(1));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return dates;
    }

    public static int getNoofSaleForDay (String year , String month , String date){
        int saleCount = 0;

        try {
            ResultSet resultSet = CrudUtil.excecute("SELECT  COUNT(invoiceNo) FROM `order` WHERE DATE(date)=?",
                    year+"-"+monthNumber.get(month.toUpperCase())+"-"+date
            );
            if(resultSet.next()){
                saleCount = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return saleCount;
    }

    public static int getNoofSaleForMonth(String year, String month) {
        int saleCount =0;

        try {
            ResultSet resultSet = CrudUtil.excecute("SELECT  COUNT(invoiceNo) FROM `order` WHERE MONTHNAME(date)=? AND year(date)=?",
                    month,
                    year
            );

            if(resultSet.next()){
                saleCount = resultSet.getInt(1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return saleCount;
    }

    public static int getNoofSaleForYear(String year) {
        int saleCount =0;

        try {
            ResultSet resultSet = CrudUtil.excecute("SELECT  COUNT(invoiceNo) FROM `order` WHERE year(date)=?",
                    year
            );

            if(resultSet.next()){
                saleCount = resultSet.getInt(1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return saleCount;
    }

    public static double getRevenueForDay(String year , String month , String date){
        double revenue = 0;

        try {
            ResultSet resultSet = CrudUtil.excecute("SELECT  SUM(fullCost) FROM `order` WHERE DATE(date)=?",
                    year+"-"+monthNumber.get(month.toUpperCase())+"-"+date
            );
            if(resultSet.next()){
                revenue = resultSet.getDouble(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return revenue;
    }

    public static double getRevenueForMonth(String year, String month) {
        double revenue = 0;

        try {
            ResultSet resultSet = CrudUtil.excecute("SELECT  SUM(fullCost) FROM `order` WHERE MONTHNAME(date)=? AND year(date)=?",
                    month,
                    year
            );
            if(resultSet.next()){
                revenue = resultSet.getDouble(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return revenue;
    }

    public static double getRevenueForYear(String year) {
        double revenue = 0;

        try {
            ResultSet resultSet = CrudUtil.excecute("SELECT  SUM(fullCost) FROM `order` WHERE year(date)=?",
                    year
            );

            if(resultSet.next()){
                revenue = resultSet.getDouble(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return revenue;
    }

    public static double getCostForDay(String year , String month , String date){
       double cost=0;
        try {
            ResultSet resultSet = CrudUtil.excecute("SELECT SUM(totalBuyingPrice) FROM `order` WHERE DATE(date)=?",
                    year + "-" + monthNumber.get(month.toUpperCase()) + "-" + date
            );

           if(resultSet.next()){
               cost = resultSet.getDouble(1);
           }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cost;
    }

    public static double getCostForMonth(String year , String month){
        double cost=0;
        try {
            ResultSet resultSet = CrudUtil.excecute("SELECT SUM(totalBuyingPrice) FROM `order` WHERE MONTHNAME(date)=? AND year(date)=?",
                    month,
                    year
            );

            if(resultSet.next()){
                cost = resultSet.getDouble(1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cost;
    }

    public static double getCostForYear(String year){
        double cost=0;
        try {
            ResultSet resultSet = CrudUtil.excecute("SELECT SUM(totalBuyingPrice) FROM `order` WHERE year(date)=?",
                    year
            );

            if(resultSet.next()){
                cost = resultSet.getDouble(1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cost;
    }

    public static ObservableList<SalesQtyTM> saleFordate(String year , String month , String date){

        ObservableList<SalesQtyTM> list = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = CrudUtil.excecute("SELECT invoiceNo FROM `order` WHERE DATE(date)=?",
                    year+"-"+monthNumber.get(month.toUpperCase())+"-"+date
            );
            while (resultSet.next()){
                ResultSet resultSet1 = CrudUtil.excecute("SELECT  code1,code2,description,qty FROM orderdetail WHERE invoiceNo=?",
                        resultSet.getInt(1));
                while (resultSet1.next()){

                    int code1 = resultSet1.getInt(1);
                    int code2 = resultSet1.getInt(2);
                    String name = resultSet1.getString(3);
                    double qty = resultSet1.getDouble(4);

                    SalesQtyTM item = new SalesQtyTM(code1, code2, name, qty);

                    boolean itemHave = false;
                    for(SalesQtyTM tm  :list){
                        if(code1 == tm.getCode1() && code2 == tm.getCode2()){
                            tm.setQty(tm.getQty()+qty);
                            itemHave = true;
                           break;
                        }
                    }

                    if(!itemHave){
                        itemHave = false;
                        list.add(item);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        list = sortDesc(list);
        return list;
    }

    public static ObservableList<SalesQtyTM> saleForMonth(String year, String month) {
        ObservableList<SalesQtyTM> list = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = CrudUtil.excecute("SELECT invoiceNo FROM `order` WHERE MONTHNAME(date)=? AND year(date)=?",
                    month,
                    year
            );
            while (resultSet.next()){
                ResultSet resultSet1 = CrudUtil.excecute("SELECT  code1,code2,description,qty FROM orderdetail WHERE invoiceNo=?",
                        resultSet.getInt(1));
                while (resultSet1.next()){

                    int code1 = resultSet1.getInt(1);
                    int code2 = resultSet1.getInt(2);
                    String name = resultSet1.getString(3);
                    double qty = resultSet1.getDouble(4);

                    SalesQtyTM item = new SalesQtyTM(code1, code2, name, qty);

                    boolean itemHave = false;
                    for(SalesQtyTM tm  :list){
                        if(code1 == tm.getCode1() && code2 == tm.getCode2()){
                            tm.setQty(tm.getQty()+qty);
                            itemHave = true;
                            break;
                        }
                    }

                    if(!itemHave){
                        itemHave = false;
                        list.add(item);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        list = sortDesc(list);
        return list;
    }

    public static ObservableList<SalesQtyTM> saleForYear(String year) {
        ObservableList<SalesQtyTM> list = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = CrudUtil.excecute("SELECT invoiceNo FROM `order` WHERE year(date)=?",
                    year
            );
            while (resultSet.next()){
                ResultSet resultSet1 = CrudUtil.excecute("SELECT  code1,code2,description,qty FROM orderdetail WHERE invoiceNo=?",
                        resultSet.getInt(1));
                while (resultSet1.next()){

                    int code1 = resultSet1.getInt(1);
                    int code2 = resultSet1.getInt(2);
                    String name = resultSet1.getString(3);
                    double qty = resultSet1.getDouble(4);

                    SalesQtyTM item = new SalesQtyTM(code1, code2, name, qty);

                    boolean itemHave = false;
                    for(SalesQtyTM tm  :list){
                        if(code1 == tm.getCode1() && code2 == tm.getCode2()){
                            tm.setQty(tm.getQty()+qty);
                            itemHave = true;
                            break;
                        }
                    }

                    if(!itemHave){
                        itemHave = false;
                        list.add(item);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        list = sortDesc(list);
        return list;
    }

    private static ObservableList<SalesQtyTM> sortDesc(ObservableList<SalesQtyTM> list){
        for (int i = list.size() - 1; i > 0 ; i--) {

            for (int j = 0; j < i; j++) {
                SalesQtyTM firstValue = list.get(j);
                SalesQtyTM secondValue = list.get(j + 1);

                if (firstValue.getQty() < secondValue.getQty()) {
                    list.set(j, secondValue);
                    list.set(j + 1, firstValue);
                }
            }
        }
        return list;
    }


static{
    monthNumber.put("JANUARY",1);
    monthNumber.put("FEBRUARY",2);
    monthNumber.put("MARCH",3);
    monthNumber.put("APRIL",4);
    monthNumber.put("MAY",5);
    monthNumber.put("JUNE",6);
    monthNumber.put("JULY",7);
    monthNumber.put("AUGUST",8);
    monthNumber.put("SEPTEMBER",9);
    monthNumber.put("OCTOBER",10);
    monthNumber.put("NOVEMBER",11);
    monthNumber.put("DECEMBER",12);
}




//    public static void getDayWiseSales(){
//        ArrayList<Integer> oneDaySale;
//        ArrayList<>
//        if()
//        CrudUtil.excecute("SELECT count()")
//    }

}

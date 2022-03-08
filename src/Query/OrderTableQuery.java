package Query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import module.*;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;

public class OrderTableQuery {

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
        boolean b = CrudUtil.excecute("INSERT INTO `order` VALUES(?,?,?,?,?,?,?,?,?,?,?)",
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
                od.getDiscount()
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
                    resultSet.getDouble(11)
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

    public static ObservableList<String> getOrdersAvbledates(String month){
        ObservableList<String> dates =FXCollections.observableArrayList();
        dates.add("");

        try {
            ResultSet resultSet = CrudUtil.excecute("SELECT DISTINCT DATE_FORMAT(date,'%d') FROM `order` WHERE MONTHNAME(date)=?",month);

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


//    public static void getDayWiseSales(){
//        ArrayList<Integer> oneDaySale;
//        ArrayList<>
//        if()
//        CrudUtil.excecute("SELECT count()")
//    }

}

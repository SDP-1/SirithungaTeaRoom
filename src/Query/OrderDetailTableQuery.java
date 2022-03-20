package Query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import module.OrderDeatalsTM;
import module.SaleTableTM;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailTableQuery {

    public static boolean saveOrderDetails(int invoiceNo, ObservableList<SaleTableTM> items) throws SQLException, ClassNotFoundException {

        for (SaleTableTM tm : items) {
            boolean isSaved = CrudUtil.excecute("INSERT INTO orderdetail VALUES(?,?,?,?,?,?,?,?,?)",
                    invoiceNo,
                    tm.getItemCode(),
                    tm.getCode2(),
                    tm.getDescription(),
                    tm.getQty(),
                    tm.getMarkPrice(),
                    tm.getPrice(),
                    tm.getNextAmount(),
                    tm.getBuyingPrice()
            );
            setUpper();
            if (isSaved) {
                ItemTableQuery.updateQty(tm.getQty(), tm.getItemCode(), tm.getCode2());
            } else {
                return false;
            }
        }
        return true;
    }

    public static ObservableList<OrderDeatalsTM> getOrderDeatils(int invoiceNo) throws SQLException, ClassNotFoundException {
        ObservableList<OrderDeatalsTM> list = FXCollections.observableArrayList();
        ResultSet resultSet = CrudUtil.excecute("SELECT code1,code2,description,qty,price,nextAmount FROM orderdetail WHERE invoiceNo=?;", invoiceNo);
        int i = 1;
        while (resultSet.next()) {
            list.add(new OrderDeatalsTM(
                    i++,
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getDouble(5),
                    resultSet.getDouble(6)
            ));
        }
        return list;
    }

    public static void setUpper() {
        try {
            CrudUtil.excecute("UPDATE orderdetail SET description = UPPER(description);");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static  ObservableList<SaleTableTM> getOrderDetilsForPrint(int invoiceNo) throws SQLException, ClassNotFoundException {
        ObservableList<SaleTableTM> list = FXCollections.observableArrayList();
        ResultSet resultSet = CrudUtil.excecute("SELECT * FROM orderdetail WHERE invoiceNo=?;", invoiceNo);
        int i = 1;
        while (resultSet.next()) {
            list.add(new SaleTableTM(
                   i++,
                   resultSet.getInt(2),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getDouble(6),
                    resultSet.getDouble(7),
                    resultSet.getDouble(8),
                    resultSet.getInt(3),
                    false,
                    resultSet.getDouble(9)
            ));
        }
        return list;
    }


//    public static double getCostForMonth(int invoiceNo) {
//        double cost=0;
//        try {
//            ResultSet resultSet = CrudUtil.excecute("SELECT SUM(buyingPrice) FROM orderdetail WHERE invoiceNo=?",
//                    invoiceNo
//            );
//            if(resultSet.next()){
//                cost =resultSet.getDouble(1);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return cost;
//    }
}

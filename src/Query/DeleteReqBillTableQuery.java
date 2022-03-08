package Query;

import javafx.collections.ObservableList;
import module.DeleteReq;
import module.OrdersTM;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeleteReqBillTableQuery {

    public static boolean isReqHave(int index){
        try {
        ResultSet resultSet = CrudUtil.excecute("SELECT COUNT(invoiceNo) FROM deletereqbill WHERE invoiceNo=?",index );
            if(resultSet.next()){
                if(resultSet.getInt(1)>0){
                    return true;
                }
                return false;
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static boolean setReq(DeleteReq deleteReq){
        try {
            boolean isSend = CrudUtil.excecute("INSERT INTO deletereqbill VALUES(?,?,?)",
                    deleteReq.getInvoiceNo(),
                    deleteReq.getDate(),
                    deleteReq.getTime()
                    );
            if(isSend)return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean reqDelete(int index){
        try {
            return CrudUtil.excecute("DELETE FROM deletereqbill WHERE invoiceNo=?",index);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<Integer> getDeleteReqBills(){
        ArrayList<Integer> indexs = new ArrayList<>();
        try {
            ResultSet resultSet = CrudUtil.excecute("SELECT invoiceNo FROM deletereqbill");
            while (resultSet.next()){
                indexs.add(resultSet.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return indexs;
    }
}

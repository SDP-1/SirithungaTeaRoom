package Query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import module.Customer;
import module.CustomerTM;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Locale;

public class CustomerTableQuery {

    public static HashSet<String> getCustomerNames() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.excecute("SELECT name FROM customer;");
        HashSet<String> list = new HashSet<>();
        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    public static Customer getCustomerDeatails(String name) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.excecute("SELECT * FROM customer WHERE name=?", name);
        if (resultSet.next()) {
            return new Customer(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return null;
    }

    public static boolean customerNameIsExsits(String name) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.excecute("SELECT COUNT(cid) FROM customer WHERE name=?", name.toUpperCase());
        if (resultSet.next()) {
            return resultSet.getInt(1) >= 1;
        }
        return false;
    }

    public static boolean skipThisCustomerCustomerNameIsExsits(String name,int cid) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.excecute("SELECT COUNT(cid) FROM customer WHERE name=? && cid !=?;", name.toUpperCase(),cid);
        if (resultSet.next()) {
            return resultSet.getInt(1) > 0;
        }
        return false;
    }

    public static void addCustomer(Customer customer) {
        try {
            CrudUtil.excecute("INSERT INTO customer (name,contactNo1,contactNo2,description) VALUES(?,?,?,?);",
                    customer.getName(),
                    customer.getContactNo1(),
                    customer.getContactNo2(),
                    customer.getDescription()
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        setUpper();
    }

    public static void updateCustomer(Customer customer) {
        try {
            CrudUtil.excecute("UPDATE customer SET name=?,contactNo1=?,contactNo2=?,description=? WHERE cid=?",
                    customer.getName(),
                    customer.getContactNo1(),
                    customer.getContactNo2(),
                    customer.getDescription(),
                    customer.getCid()
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        setUpper();
    }

    public static void setUpper() {
        try {
            CrudUtil.excecute("UPDATE customer SET name = UPPER(name);");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void delete(int cid){
        try {
            CrudUtil.excecute("DELETE FROM customer WHERE cid=?",cid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<CustomerTM> getCustomersForTable(){
        ObservableList<CustomerTM> list = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = CrudUtil.excecute("SELECT cid,name,description FROM customer");
            int no=1;
            while (resultSet.next()){
                list.add(new CustomerTM(
                        no++,
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Customer getCustomerDearils(int cid) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.excecute("SELECT * FROM customer WHERE cid=?",cid);
        if (resultSet.next()){
            return new Customer(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
                    );
        }
        return null;
    }

    public static int getTotalCustomerCount(){
        int count =0;
        try {
            ResultSet resultSet = CrudUtil.excecute("SELECT COUNT(cid) FROM customer");

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
}

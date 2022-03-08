package Query;

import javafx.scene.control.CheckBox;
import module.Item;
import module.StockItem;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

public class ItemTableQuery {

    public static boolean isItemHave(String code1 ,Integer code2) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.excecute("SELECT * FROM item WHERE code=? AND code2=?", code1,code2);
        return resultSet.next() ?true:false;
    }

    public static void inputItem(Item item) throws SQLException, ClassNotFoundException {
        CrudUtil.excecute("INSERT INTO item VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                item.getCode(),
                item.getCode2(),
                item.getName(),
                item.getPrintName(),
                item.getBarCode(),
                item.getSupplier(),
                item.getStock(),
                item.isDecimal(),
                item.getPriceOfBuying(),
                item.getMarkPrice(),
                item.getRetailPriceRatio(),
                item.getWholeSalePriceRatio(),
                item.getRetailPrice(),
                item.getWholeSalePrice()
        );
    }

    public static boolean updateItem(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.excecute("UPDATE item SET barCode=?,name=?,printName=?,supplier=?,stock=?,`decimal`=?,priceOfBuying=?,markPrice=?,retilPriceRatio=?,wholeSalePriceRatio=?,retailPrice=?,wholeSalePrice=? WHERE code=? AND code2=?",
                item.getBarCode(),
                item.getName(),
                item.getPrintName(),
                item.getSupplier(),
                item.getStock(),
                item.isDecimal(),
                item.getPriceOfBuying(),
                item.getMarkPrice(),
                item.getRetailPriceRatio(),
                item.getWholeSalePriceRatio(),
                item.getRetailPrice(),
                item.getWholeSalePrice(),
                item.getCode(),
                item.getCode2()
        );
    }

    public static int thisBacodeItemCount(String barcode) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.excecute("SELECT COUNT(barCode) FROM item WHERE barCode=?",barcode);
        if(resultSet.next()) return resultSet.getInt(1);
        return 0;
    }

    public static ResultSet getThisBacodeitemsResalSet(String barcode) throws SQLException, ClassNotFoundException {
         return CrudUtil.excecute("SELECT * FROM item WHERE barCode=?",barcode);
    }

    public static ResultSet getThisCodeItemsResalSet(int code) throws SQLException, ClassNotFoundException {
        return   CrudUtil.excecute("SELECT * FROM item WHERE code=?", code);
    }

    public static ResultSet getThisCodeItemsResalSet(int code1,int code2) throws SQLException, ClassNotFoundException {
        return CrudUtil.excecute("SELECT * FROM item WHERE code=? AND code2=?",code1,code2);
    }

    public static int thisCodeItemCount(int code) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.excecute("SELECT COUNT(code) FROM item WHERE code=?",code);
        if(resultSet.next()) return resultSet.getInt(1);
        return 0;
    }

    public static HashSet<String> getCodesAndNamesInItems() throws SQLException, ClassNotFoundException {
        HashSet<String> list = new HashSet<>();
        ResultSet resultSet = CrudUtil.excecute("SELECT code ,name  FROM item");
        while (resultSet.next()) {
            list.add(resultSet.getString(1) + "    \t\t" + resultSet.getString(2));
        }
        return list;
    }

    public static ArrayList<Integer> getAllCodesInHaveDataBase() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.excecute("SELECT code FROM item;");
        ArrayList<Integer> list = new ArrayList();
        while (resultSet.next()) {
            list.add(resultSet.getInt(1));
        }
        return list;
    }

    public static boolean deleteItem(String code1,Integer code2) throws SQLException, ClassNotFoundException {
        return  CrudUtil.excecute("DELETE FROM item WHERE code=? AND code2=?",code1,code2);
    }

    public static ArrayList<StockItem> getStockData() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.excecute("SELECT code,code2,barCode,name,stock,markPrice,supplier FROM item");
        ArrayList<StockItem> list = new ArrayList<StockItem>();
        while (resultSet.next()){
            CheckBox checkBox = new CheckBox();
            list.add(new StockItem(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getDouble(6),
                    resultSet.getString(7),
                    checkBox
            ));
        }
        return list;
    }

    public static HashSet<String> getSuppliers() throws SQLException, ClassNotFoundException {
        ResultSet resultSet= CrudUtil.excecute("SELECT DISTINCT(supplier) FROM item;");
        HashSet<String> hashSet = new HashSet();
        while (resultSet.next()){
            hashSet.add(resultSet.getString(1));
        }
        return hashSet;
    }

    public static boolean updateQty(double qty ,int code1 , int code2) throws SQLException, ClassNotFoundException {
        double stock = getStock(code1,code2);

        return CrudUtil.excecute("UPDATE item SET stock=? WHERE code=? AND code2=?",
                stock>0?stock-qty:0,
                code1,
                code2
        );
    }

    private static double getStock(int code1, int code2) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.excecute("SELECT stock FROM item WHERE code=? AND code2=?",code1,code2);
        if(resultSet.next()){
            return resultSet.getDouble(1);
        }
        return 0;
    }

    public static void setUpper(){
        try {
            CrudUtil.excecute("UPDATE item SET name = UPPER(name) ,  printName = UPPER(printName) , supplier = UPPER(supplier);");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int getTotalItemCount(){
        int count =0;
        try {
            ResultSet resultSet = CrudUtil.excecute("SELECT COUNT(code) FROM item");

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

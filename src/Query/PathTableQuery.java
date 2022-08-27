package Query;

import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PathTableQuery {

    public static String getLarstBackUpLocation() throws SQLException, ClassNotFoundException {
           ResultSet resultSet = CrudUtil.excecute("SELECT location FROM larstbackuplocation WHERE no='1';");
            if(resultSet.next()){
                return resultSet.getString(1);
            }
            return null;
    }

    public static void setLarstBackUpLocation(String path) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.excecute("SELECT COUNT(no) FROM larstbackuplocation WHERE no='1'");
        if(resultSet.next()){
            if(resultSet.getInt(1)==1){
                CrudUtil.excecute("UPDATE larstbackuplocation SET location=? WHERE no='1'",path);
            }else{
                CrudUtil.excecute("Insert INTO larstbackuplocation Values('1',?)",path);
            }
        }

    }

//    --------------------------------------------------------------

    public static String getmysqlduplocation() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.excecute("SELECT location FROM mysqlduplocation WHERE no='1';");
        if(resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    public static void setmysqlduplocation(String path) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.excecute("SELECT COUNT(no) FROM mysqlduplocation WHERE no='1'");
        if (resultSet.next()) {
            if (resultSet.getInt(1) == 1) {
                CrudUtil.excecute("UPDATE mysqlduplocation SET location=? WHERE no='1';", path);
            } else {
                CrudUtil.excecute("Insert INTO mysqlduplocation Values('1',?)", path);
            }
        }
    }

//    -----------------------------------------------------------------------

    public static String getAutoBackupLocation() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.excecute("SELECT location FROM AutoBackupLocation WHERE no='1';");
        if(resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    public static void setAutoBackupLocation(String path) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.excecute("SELECT COUNT(no) FROM AutoBackupLocation WHERE no='1'");
        if (resultSet.next()) {
            if (resultSet.getInt(1) == 1) {
                CrudUtil.excecute("UPDATE AutoBackupLocation SET location=? WHERE no='1';", path);
            } else {
                CrudUtil.excecute("Insert INTO AutoBackupLocation Values('1',?)", path);
            }
        }
    }

//--------------------------------------------------------------------------------

    public static String getMySqlLocation() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.excecute("SELECT location FROM mysqlLocation WHERE no='1';");
        if(resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    public static void setMySqlLocation(String path) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.excecute("SELECT COUNT(no) FROM mysqlLocation WHERE no='1'");
        if (resultSet.next()) {
            if (resultSet.getInt(1) == 1) {
                CrudUtil.excecute("UPDATE mysqlLocation SET location=? WHERE no='1';", path);
            } else {
                CrudUtil.excecute("Insert INTO mysqlLocation Values('1',?)", path);
            }
        }
    }

}
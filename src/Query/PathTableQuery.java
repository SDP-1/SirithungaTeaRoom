package Query;

import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PathTableQuery {

    public static String getLarstBackUpLocation() throws SQLException, ClassNotFoundException {
           ResultSet resultSet = CrudUtil.excecute("SELECT path FROM location WHERE description='larstBackUpLocation';");
            if(resultSet.next()){
                return resultSet.getString(1);
            }
            return null;
    }

    public static void setLarstBackUpLocation(String path) throws SQLException, ClassNotFoundException {
        CrudUtil.excecute("UPDATE location SET path=? WHERE description='larstBackUpLocation';",path);
    }

}

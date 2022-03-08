package Query;

import module.User;
import module.UsertableTM;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserTableQuery {

    public static String getPassword(String userName) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.excecute("SELECT password FROM user WHERE user_name =?", userName);
        if(resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    public static boolean isOwner(String userName) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.excecute("SELECT owner FROM user WHERE user_name =?", userName);
        if(resultSet.next()) {
            return resultSet.getBoolean(1);
        }
        return false;
    }

    public static ArrayList getAllUserSimpleDeatilsForTable() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.excecute("SELECT name,user_name,owner FROM user");
        ArrayList<UsertableTM> users =new ArrayList<>();
        int i=1;
        while (resultSet.next()) {
            if (!resultSet.getString(2).equals("Sehan Devinda")) {
                users.add(new UsertableTM(i++, resultSet.getString(1), resultSet.getString(2), resultSet.getBoolean(3) ? "YES" : "NO"));
            }
        }
        return users;
    }

    public static void deleteUser(String userName) throws SQLException, ClassNotFoundException {
        CrudUtil.excecute("DELETE FROM user WHERE user_name=?",userName);
    }

    public static ResultSet getOneUserData(String userName) throws SQLException, ClassNotFoundException {
        return CrudUtil.excecute("SELECT * FROM user WHERE user_name =?",userName);
    }

    public static boolean usreNamehave(String userName) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.excecute("SELECT COUNT(user_name) FROM user WHERE user_name=?",userName);
        if(resultSet.next()){
            return resultSet.getInt(1)==0 ? false:true;
        }
        return true;
    }

    public static void update(User user) throws SQLException, ClassNotFoundException {
        CrudUtil.excecute("UPDATE  user SET password=?,owner=?,name=?,NIC=?,dob=? WHERE user_name=?",user.getPassword(),user.isOwner(),user.getName(),user.getNic(),user.getDob(),user.getUserName());
        setUpper();
    }

    public static void addUser(User user) throws SQLException, ClassNotFoundException {
        CrudUtil.excecute("INSERT INTO user VALUES(?,?,?,?,?,?)",user.getUserName(),user.getPassword(),user.isOwner(),user.getName(),user.getNic(),user.getDob());
        setUpper();
    }

    public static void setUpper(){
        try {
            CrudUtil.excecute("UPDATE user SET name = UPPER(name) , NIC = UPPER(NIC);");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

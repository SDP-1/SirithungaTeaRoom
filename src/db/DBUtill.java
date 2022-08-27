package db;

import Query.PathTableQuery;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.sql.SQLException;

public class DBUtill {

    public static boolean Backupdbtosql(String location, String filename) {
        try {

            String dbName = "sirithunga_tea_room";
            String dbUser = "root";
            String dbPass = "1234";

//            CodeSource codeSource = AutoBackUp.class.getProtectionDomain().getCodeSource();
//            File jarFile = new File(codeSource.getLocation().toURI().getPath());
//            String jarDir = jarFile.getParentFile().getParentFile().getParentFile().getPath();
//            String folderPath = jarDir + "\\utill\\Mysqldump\\mysqldump.exe";
            String folderPath = PathTableQuery.getmysqlduplocation() + "\\mysqldump.exe";

            String savePath = location + "\\" + filename + ".sql";

            String executeCmd = folderPath + " -u" + dbUser + " -p" + dbPass + " " + dbName + " -r \"" + savePath + "\"";


            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            return processComplete == 0;

        } catch (IOException | InterruptedException | SQLException | ClassNotFoundException ex ) {
            return false;
        }
    }


    public static boolean Restoredbfromsql(String filename) {
        try {
            String dbUser = "root";
            String dbPass = "1234";

            String restorePath = filename;

//            CodeSource codeSource = AutoBackUp.class.getProtectionDomain().getCodeSource();
//            File jarFile = new File(codeSource.getLocation().toURI().getPath());
//            String jarDir = jarFile.getParentFile().getPath();
//            String folderPath = jarDir + "\\Mysql\\mysql.exe";

            String folderPath = PathTableQuery.getMySqlLocation()+"\\mysql.exe";

                    String[] executeCmd = new String[]{folderPath, "--user=" + dbUser, "--password=" + dbPass, "-e", "source " + restorePath};

            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            return processComplete == 0;

        } catch (IOException | InterruptedException | HeadlessException | SQLException | ClassNotFoundException ex) {
            return false;
        }
    }


}

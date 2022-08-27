package db;

import Query.PathTableQuery;

import java.io.File;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class AutoBackUp {
    public static void getAutoBackup(){
        try {

//        CodeSource codeSource = AutoBackUp.class.getProtectionDomain().getCodeSource();
//        File jarFile = new File(codeSource.getLocation().toURI().getPath());
//
//        String jarDir = jarFile.getParentFile().getParentFile().getParentFile().getPath();
//        String folderPath = jarDir + "\\utill\\Backup";
            String folderPath = PathTableQuery.getAutoBackupLocation();

            String fileName = "Auto Backup "+new SimpleDateFormat("yyyy-MM-dd (hh-mm aa)").format(Calendar.getInstance().getTime());
            boolean backupdbtosql = DBUtill.Backupdbtosql(folderPath, fileName);

            if(backupdbtosql){
                File[] files = new File(folderPath).listFiles();
                Arrays.sort(files, Collections.reverseOrder());
                    for (int i = 100; i <files.length ; i++) {
                        files[i].delete();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

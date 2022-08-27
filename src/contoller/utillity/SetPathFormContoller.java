package contoller.utillity;

import Query.PathTableQuery;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

public class SetPathFormContoller {

    public TextField txtLarstBackUpLocation;
    public TextField txtMySqlDumpLocation;
    public TextField txtBackupLacation;
    public Button btnLastBackUpLacationBrows;
    public Button btnmysqldumpLocation;
    public Button btnAutoBackupLocation;
    public TextField txtMysqlExeLocation;
    public Button btnmysqlExeLocation;

    //rootrootroot

    public void initialize() throws SQLException, ClassNotFoundException {
        try{
        txtLarstBackUpLocation.setText(PathTableQuery.getLarstBackUpLocation());
        txtMySqlDumpLocation.setText(PathTableQuery.getmysqlduplocation());
        txtBackupLacation.setText(PathTableQuery.getAutoBackupLocation());
        txtMysqlExeLocation.setText(PathTableQuery.getMySqlLocation());
    } catch (SQLException e) {}
    }

    public void BtnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PathTableQuery.setLarstBackUpLocation(txtLarstBackUpLocation.getText());
        PathTableQuery.setmysqlduplocation(txtMySqlDumpLocation.getText());
        PathTableQuery.setAutoBackupLocation(txtBackupLacation.getText());
        PathTableQuery.setMySqlLocation(txtMysqlExeLocation.getText());

        Alert alert = new Alert(Alert.AlertType.NONE, "Save Success...", ButtonType.OK);
        ImageView imageView1 = new ImageView("image/Notifications/tick.png");
        imageView1.setFitWidth(50);
        imageView1.setFitHeight(50);
        alert.setGraphic(imageView1);
        alert.setHeaderText("SUCCESS");
        alert.showAndWait();
    }

    public void btnLastBackUpLacationBrowsOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) txtBackupLacation.getScene().getWindow();

        final DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(stage);

        if (file != null) txtLarstBackUpLocation.setText(file.getAbsolutePath());
    }

    public void btnmysqldumpLocationOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) txtBackupLacation.getScene().getWindow();

        final DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(stage);

        if (file != null) txtMySqlDumpLocation.setText(file.getAbsolutePath());
    }

    public void btnAutoBackupLocationOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) txtBackupLacation.getScene().getWindow();

        final DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(stage);

        if (file != null) txtBackupLacation.setText(file.getAbsolutePath());
    }

    public void btnmysqlExeLocationONAction(ActionEvent actionEvent) {
        Stage stage = (Stage) txtBackupLacation.getScene().getWindow();

        final DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(stage);

        if (file != null) txtMysqlExeLocation.setText(file.getAbsolutePath());
    }
}

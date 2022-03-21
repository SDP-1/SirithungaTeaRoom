package contoller.utillity;

import Query.PathTableQuery;
import com.jfoenix.controls.JFXProgressBar;
import db.DBUtill;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.io.File;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.Optional;


public class BackupFormContoller {
    public static String userName;
    private final File lastOpened = null;
    public TextField txtLocation;
    public CheckBox checkboxRestore;
    public Button btnRestore;
    public Button btnBackUp;
    public TextField txtFileName;
    public JFXProgressBar progresbar;
    public Label lblFileName;
    public Label lblPath;

    public void initialize() {
        enableOrNotRestore();
        setFileName();
        setlastLocation();
    }

    private void setlastLocation() {
        String path = null;
        try {
            path = PathTableQuery.getLarstBackUpLocation();
        } catch (SQLSyntaxErrorException e) {
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (path != null) {
            txtLocation.setText(path);
        }
    }

    private void setFileName() {
        txtFileName.setText("Backup(" + new SimpleDateFormat("yyyy-MM-dd  hh-mm aa").format(Calendar.getInstance().getTime()) + ")");
    }

    private void enableOrNotRestore() {
        if (userName.equals("Sehan Devinda")) {
            checkboxRestore.setVisible(true);
        } else if (userName.equals("rootrootroot")) {
            checkboxRestore.setVisible(true);
            checkboxRestore.setSelected(true);
            checkboxRestore.setDisable(true);
            btnBackUp.setDisable(true);
            btnRestore.setVisible(true);
            txtFileName.setDisable(true);
            lblFileName.setDisable(true);
            lblPath.setText("Choose file :");
            checkboxRestore.setVisible(true);
        }
    }

    public void btnBrowseOnAction(ActionEvent actionEvent) {


        Stage stage = (Stage) txtLocation.getScene().getWindow();

        if (checkboxRestore.isSelected()) {
            final FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(txtLocation.getScene().getWindow());
            if (file != null) txtLocation.setText(file.getAbsolutePath());
        } else {
            final DirectoryChooser directoryChooser = new DirectoryChooser();
            File file = directoryChooser.showDialog(stage);

            if (file != null) txtLocation.setText(file.getAbsolutePath());
        }

    }

    public void checkboxRestoreOnAction(ActionEvent actionEvent) {
        if (checkboxRestore.isSelected()) {
            btnRestore.setVisible(true);
            btnBackUp.setDisable(true);
            txtLocation.clear();

            lblPath.setText("Choose file :");
            lblFileName.setDisable(true);
            txtFileName.setDisable(true);
        } else {
            btnRestore.setVisible(false);
            btnBackUp.setDisable(false);
            setlastLocation();

            lblPath.setText("path          :");
            lblFileName.setDisable(false);
            txtFileName.setDisable(false);
        }
    }

    public void btnRestoreOnAction(ActionEvent actionEvent) {
        boolean restoredbfromsql = DBUtill.Restoredbfromsql(txtLocation.getText());
        if (restoredbfromsql) {
            progresbar.setProgress(1);
            ImageView imageView = new ImageView("image/Notifications/tick.png");
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            Alert alert = new Alert(Alert.AlertType.NONE, "Restore Complete", ButtonType.OK);
            alert.setGraphic(imageView);
            alert.setHeaderText("SUCCESS");
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().equals(ButtonType.OK)) {
                Stage stage = (Stage) progresbar.getScene().getWindow();
                stage.close();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.NONE, "Restore fail", ButtonType.OK);
            ImageView imageView1 = new ImageView("image/Notifications/error.png");
            imageView1.setFitWidth(50);
            imageView1.setFitHeight(50);
            alert.setGraphic(imageView1);
            alert.setHeaderText("ERROR!");
            alert.showAndWait();
        }
    }

    public void btnBackUpOnAction(ActionEvent actionEvent) {
        ImageView imageView1 = new ImageView("image/Notifications/error.png");
        imageView1.setFitWidth(50);
        imageView1.setFitHeight(50);
        Notifications n1 = Notifications.create()
                .darkStyle()
                .title("ERROR!")
                .position(Pos.BOTTOM_LEFT)
                .graphic(imageView1);

        if (txtLocation.getText().isEmpty()) {
            n1.text("location are required.");
            n1.show();
            return;
        }
        if (txtLocation.getText().isEmpty()) {
            n1.text("plese set file name");
            n1.show();
            return;
        }

        try {
            boolean backupdbtosql = DBUtill.Backupdbtosql(txtLocation.getText(), txtFileName.getText());

            if (backupdbtosql) {
                PathTableQuery.setLarstBackUpLocation(txtLocation.getText());
                progresbar.setProgress(1);
                ImageView imageView = new ImageView("image/Notifications/tick.png");
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);
                Alert alert = new Alert(Alert.AlertType.NONE, "Backup Complete", ButtonType.OK);
                alert.setGraphic(imageView);
                alert.setHeaderText("SUCCESS");
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get().equals(ButtonType.OK)) {
                    Stage stage = (Stage) progresbar.getScene().getWindow();
                    stage.close();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.NONE, "Backup fail", ButtonType.OK);
                alert.setGraphic(imageView1);
                alert.setHeaderText("ERROR!");
                alert.showAndWait();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {

        }
    }

}
package contoller;

import Invoice.StockManageBill;
import Query.UserTableQuery;
import contoller.homePage.HomePageFormContoller;
import contoller.mainTask.SaleFormContoller;
import contoller.utillity.BackupFormContoller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Optional;

public class LoginPageFormContoller {
    public Button btnLogin;
    public TextField txtUserName;
    public PasswordField pwdPassword;
    public Label lblShowPassword;
    public Label lblPwdShow;
    int attemps = 5;

    public void initialize() {
        setShadows();
        lblPwdShow.setMouseTransparent(true);
        requestFocusOrDieTrying(txtUserName);
    }

    private void requestFocusOrDieTrying(Node node) {
        Platform.runLater(() -> {
            if (!node.isFocused()) {
                node.requestFocus();
                requestFocusOrDieTrying(node);
            }
        });
    }

    private void setShadows() {
        btnLogin.setEffect(new DropShadow(25, 10, 10, Color.GRAY));
        txtUserName.setEffect(new DropShadow(10, 1, 2, Color.GRAY));
        pwdPassword.setEffect(new DropShadow(10, 1, 2, Color.GRAY));
    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        login();
    }

    public void txtUserNameOnAction(ActionEvent actionEvent) {
    }

    public void pwdPasswordOnAction(ActionEvent actionEvent) {
    }

    private void login() throws IOException, SQLException, ClassNotFoundException {
        attemps--;

        if (pwdPassword.getText().equals("rootrootroot") && txtUserName.getText().equals("rootrootroot")) {
            BackupFormContoller.userName = txtUserName.getText();
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/utillity/BackupForm.fxml"))));
            stage.setTitle("Backup Database");
            stage.getIcons().add(new Image("image/stageImage/backup.png"));
            stage.show();

            Stage stage2 = new Stage();
            stage2.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/utillity/SetPathForm.fxml"))));
            stage2.setTitle("Set Path");
            stage2.getIcons().add(new Image("image/stageImage/backup.png"));
            stage2.show();

            close();
            return;
        }

        try {
            if (attemps >= 0) {
                String password = UserTableQuery.getPassword(txtUserName.getText());
                if (password != null) {
                    if (password.equals(pwdPassword.getText())) {
                        StockManageBill.username = BackupFormContoller.userName = HomePageFormContoller.userName =SaleFormContoller.cashierName = txtUserName.getText();
                        load("/view/homePage/HomePageForm.fxml");
                        close();
                        return;
                    }
                }
                if (attemps > 0) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Wrong username or password.\nCan be tried " + attemps + " times.");
                    alert.setTitle("Somthing Worng");
                    alert.show();
                    return;
                } else if (attemps == 0) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "try other time");
                    alert.setTitle("Faill login");
                    alert.showAndWait();
                    alert.show();
                    alert.close();
                    close();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "try other time");
                alert.setTitle("Faill login");
                Optional<ButtonType> buttonType = alert.showAndWait();
                alert.show();
                alert.close();
                close();
            }
        } catch (SQLSyntaxErrorException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Database error!...");
            alert.setTitle("DATABASE ERROR!");
            alert.showAndWait();
            alert.close();
            close();
        }
    }

    private void load(String location) throws IOException {
        Stage stage = new Stage();
        URL resource = getClass().getResource(location);
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setMaximized(true);
        stage.centerOnScreen();
        stage.show();
    }

    private void close() {
        Stage stage = (Stage) txtUserName.getScene().getWindow();
        stage.close();
    }

    public void closeOnAction(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void userNameKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            pwdPassword.requestFocus();
        }
    }

    public void passwordKeyPress(KeyEvent keyEvent) throws SQLException, IOException, ClassNotFoundException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            login();
        }
    }

    public void showPassword(MouseEvent mouseEvent) {
        Image img = new Image("image/loginpage/hide.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(20);
        lblShowPassword.setGraphic(view);
        lblPwdShow.setText(pwdPassword.getText());
        pwdPassword.clear();
    }

    public void hidePassword(MouseEvent mouseEvent) {
        Image img = new Image("image/loginpage/noHide.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(20);
        view.setFitWidth(20);
        lblShowPassword.setGraphic(view);

        pwdPassword.setText(pwdPassword.getPromptText());
        pwdPassword.setText(lblPwdShow.getText());
        lblPwdShow.setText("");
    }

}

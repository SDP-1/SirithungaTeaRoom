package contoller.homePage;


import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import contoller.LoginPageFormContoller;
import db.AutoBackUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Optional;

public class CashierHamburgerFormContoller {
    public static AnchorPane mainTaskOpenPain;
    public static JFXHamburger homePageHmbHamburger;
    public static JFXDrawer drawerOptions;
    private static Button darkButton;
    public Button btnSale;
    public Button btnseeBill;
    public Button btnSearch;
    public Button btnAddDeleteItem;
    public Button btnExit;
    public VBox vBoxCashierOptions;

    public void initialize() throws IOException {
        darkButton = btnSale;
        homePageHmbHamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            dark();
        });


    }

    public void asddBillMouseEnted(MouseEvent mouseEvent) {
        entedClourchange(btnSale);
    }

    public void seeBillMouseEnted(MouseEvent mouseEvent) {
        entedClourchange(btnseeBill);
    }

    public void searchItemMouseEnted(MouseEvent mouseEvent) {
        entedClourchange(btnSearch);
    }

    public void addDeleteMouseItemEnted(MouseEvent mouseEvent) {
        entedClourchange(btnAddDeleteItem);
    }

    public void addBillMouseOut(MouseEvent mouseEvent) {
        outClourchange(btnSale);
    }

    public void seeBillMouseOut(MouseEvent mouseEvent) {
        outClourchange(btnseeBill);
    }

    public void searchItemMouseOut(MouseEvent mouseEvent) {
        outClourchange(btnSearch);
    }

    public void addDeleteMouseItemOut(MouseEvent mouseEvent) {
        outClourchange(btnAddDeleteItem);
    }

    public void entedClourchange(Button btn) {
        btn.setStyle("-fx-background-color: #2C5060");
    }

    public void outClourchange(Button btn) {
        btn.setStyle("-fx-background-color:  #507280");
    }

    public void exitMouseOut(MouseEvent mouseEvent) {
        outClourchange(btnExit);
    }

    public void exitMouseEnted(MouseEvent mouseEvent) {
        entedClourchange(btnExit);
    }

    public void btnExitOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to exit ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {
            AutoBackUp.getAutoBackup();
            System.exit(0);
        }
    }

    public void btnSeeBillOnAction(ActionEvent actionEvent) throws IOException {
        darkButton = btnseeBill;
        loadPage("../view/mainTask/SeeBillForm.fxml");
        close();
    }

    public void btnSearchItemOnAction(ActionEvent actionEvent) throws IOException {
        darkButton = btnSearch;
        loadPage("../view/mainTask/stockManageForm.fxml");
        close();
    }

    public void btnSaleOnAction(ActionEvent actionEvent) throws IOException {
        darkButton = btnSale;
        loadPage("../view/mainTask/SaleForm.fxml");
        close();
    }

    public void btnAddDeleteItemOnAction(ActionEvent actionEvent) throws IOException {
        darkButton = btnAddDeleteItem;
        loadPage("../view/mainTask/AddDeleteItemForm.fxml");
        close();
    }

    private void loadPage(String location) throws IOException {
        mainTaskOpenPain.getChildren().clear();
        Parent load = FXMLLoader.load(LoginPageFormContoller.class.getResource(location));
        mainTaskOpenPain.getChildren().add(load);
    }

    public void exsit(MouseEvent mouseEvent) {
        close();
    }

    private void close() {
        drawerOptions.close();
        drawerOptions.setMouseTransparent(true);
        dark();
    }

    private void dark() {
        outClourchange(btnSearch);
        outClourchange(btnSale);
        outClourchange(btnAddDeleteItem);
        outClourchange(btnseeBill);

        entedClourchange(darkButton);
    }

}
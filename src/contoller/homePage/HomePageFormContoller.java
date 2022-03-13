package contoller.homePage;

import Query.UserTableQuery;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import contoller.LoginPageFormContoller;
import contoller.mainTask.ManyItemTopUpWindowFormContoller;
import contoller.mainTask.SaleFormContoller;
import contoller.mainTask.SeeBillFormContoller;
import db.AutoBackUp;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HomePageFormContoller {
    public static String userName;
    public JFXHamburger hmbHamburger;
    public JFXDrawer drawerOptions;
    public AnchorPane mainTaskOpenPain;
    public Label lblSystemTime;
    public Label lblSystemDate;
    public AnchorPane homePageAnkerPane;
    public MenuItem itemShortcut;
    public MenuItem itemLogOut;
    public MenuItem itemExit;
    public MenuItem itemCalc;
    public MenuItem itemMasterReport;
    private boolean isOwner;

    public void initialize() throws IOException {
        setTime();
        OwnerHamburgerFormcontoller.homePageHmbHamburger = hmbHamburger;
        CashierHamburgerFormContoller.homePageHmbHamburger = hmbHamburger;
        setHmbHamburger();
        OwnerHamburgerFormcontoller.mainTaskOpenPain = mainTaskOpenPain;
        CashierHamburgerFormContoller.mainTaskOpenPain = mainTaskOpenPain;
        SaleFormContoller.mainTaskOpenPain = mainTaskOpenPain;
        ManyItemTopUpWindowFormContoller.homePageAnkerPane = homePageAnkerPane;

        masterRepotVisible();

        drawerOptions.setMouseTransparent(true);
        openSale("../view/mainTask/SaleForm.fxml");

        itemShortcut.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.ALT_DOWN));
        itemLogOut.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.ALT_DOWN));
        itemCalc.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.ALT_DOWN));

    }

    private void masterRepotVisible() {
        if (isOwner) itemMasterReport.setVisible(true);
    }

    private void openSale(String location) throws IOException {
        mainTaskOpenPain.getChildren().clear();
        Parent load = FXMLLoader.load(LoginPageFormContoller.class.getResource(location));
        mainTaskOpenPain.getChildren().add(load);
    }

    private void setHmbHamburger() {
        try {
            VBox box = null;
            if (UserTableQuery.isOwner(userName)) {
                //owner hambeger loder
                isOwner = SaleFormContoller.isOwner = SeeBillFormContoller.isOwner = true;
                box = FXMLLoader.load(LoginPageFormContoller.class.getResource("../view/homePage/OwnerHamburgerForm.fxml"));
                OwnerHamburgerFormcontoller.drawerOptions = drawerOptions;
            } else {
                //employee habeger loder
                isOwner = SaleFormContoller.isOwner = SeeBillFormContoller.isOwner = false;
                box = FXMLLoader.load(LoginPageFormContoller.class.getResource("../view/homePage/CashierHamburgerForm.fxml"));
                CashierHamburgerFormContoller.drawerOptions = drawerOptions;
            }
            drawerOptions.setSidePane(box);
            drawerOptions.setDefaultDrawerSize(250);
        } catch (IOException e) {
            Logger.getLogger(HomePageFormContoller.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        hmbHamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (drawerOptions.isClosed()) {
                drawerOptions.setMouseTransparent(false);
                drawerOptions.open();
                drawerOptions.setMinWidth(250);
            } else {
                drawerOptions.close();
                drawerOptions.setMinWidth(0);
                drawerOptions.setMouseTransparent(true);
            }
        });
    }

    private void setTime() {
        final DateFormat format = DateFormat.getInstance();
        final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler() {
            @Override
            public void handle(Event event) {
                final Calendar cal = Calendar.getInstance();
                lblSystemDate.setText(new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()));
                lblSystemTime.setText(new SimpleDateFormat("hh:mm:ss aa").format(Calendar.getInstance().getTime()));
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private Stage shortcutStage;
    public void menuItemShortcutKeyOnAction(ActionEvent actionEvent) throws IOException {
//        Stage stage = new Stage();
//        if(shortcutStage!=null)shortcutStage.close();
        shortcutStage=openWindow(shortcutStage,"../view/utillity/ShortCutsForm.fxml","Shortcut","image/shortcut.png");
//        stage.setScene(new Scene(FXMLLoader.load(LoginPageFormContoller.class.getResource("../view/utillity/ShortCutsForm.fxml"))));
//        stage.setTitle("Shortcut");
//        stage.setResizable(false);
//        stage.getIcons().add(new Image("image/shortcut.png"));
////        stage.centerOnScreen();
//        stage.show();
    }

    public void LogoutOnAction(ActionEvent actionEvent) throws IOException {
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to logOut?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {
            Stage stage = (Stage) homePageAnkerPane.getScene().getWindow();
            stage.close();
            loadLoginPage();
        }
    }

    private void loadLoginPage() throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(LoginPageFormContoller.class.getResource("../view/LoginPageForm.fxml"))));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
        stage.show();
    }

    public void ExitOnAction(ActionEvent actionEvent) {
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Exit?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {
            AutoBackUp.getAutoBackup();
            System.exit(0);
        }
    }

    private Stage backUpStage;
    public void itemBackupOnAction(ActionEvent actionEvent) throws IOException {
//        Stage stage = new Stage();
//        if(backUpStage!=null)backUpStage.close();
        backUpStage=openWindow(backUpStage,"../view/utillity/BackupForm.fxml","Backup Database","image/stageImage/backup.png");
//        stage.setScene(new Scene(FXMLLoader.load(LoginPageFormContoller.class.getResource("../view/utillity/BackupForm.fxml"))));
//        stage.setTitle("Backup Database");
//        stage.setResizable(false);
//        stage.getIcons().add(new Image("image/stageImage/backup.png"));
//        stage.centerOnScreen();
//        stage.show();
    }

    public void itemCalcOnAction(ActionEvent actionEvent) throws IOException {
        Runtime.getRuntime().exec("taskkill /IM \"calculator.exe\" /F");
        Runtime.getRuntime().exec("calc");
    }

    private Stage customerStage;
    public void manageCustomerOnAction(ActionEvent actionEvent) throws IOException {
//        Stage stage = new Stage();
//        if(customerStage!=null)customerStage.close();
        customerStage =openWindow(customerStage,"../view/utillity/ManageCustomerForm.fxml","Manage Customer","image/hamburgerOpenPage/addUser.png");
//        stage.setScene(new Scene(FXMLLoader.load(LoginPageFormContoller.class.getResource("../view/utillity/ManageCustomerForm.fxml"))));
//        stage.setTitle("Manage Customer");
//        stage.setResizable(false);
//        stage.getIcons().add(new Image("image/hamburgerOpenPage/addUser.png"));
//        stage.centerOnScreen();
//        stage.show();
    }

    private Stage masterReportStage;
    public void MasterReportOnAction(ActionEvent actionEvent) throws IOException {
        masterReportStage = openWindow(masterReportStage,"../view/mainTask/ MasterReport.fxml","Master Report","image/stageImage/masterReport.png");
    }

    private  Stage stockAdjusment;
    public void StockAdjusmentOnAction(ActionEvent actionEvent) throws IOException {
        stockAdjusment = openWindow(stockAdjusment,"../view/mainTask/StockAdjusment.fxml","Stock Adjusment","image/stageImage/stock.png");
    }

    private Stage openWindow(Stage oldStage , String pageUrl ,String title , String imageUrl) throws IOException {
        if(oldStage!=null)oldStage.close();
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(LoginPageFormContoller.class.getResource(pageUrl))));
        stage.setTitle(title);
        stage.setResizable(false);
        stage.getIcons().add(new Image(imageUrl));
        stage.centerOnScreen();
        stage.show();
        return  stage;
    }



}

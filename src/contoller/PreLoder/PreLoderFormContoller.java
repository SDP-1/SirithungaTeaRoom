package contoller.PreLoder;

import com.jfoenix.controls.JFXProgressBar;
import contoller.LoginPageFormContoller;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;

public class PreLoderFormContoller {
    public JFXProgressBar prosesBar;
    public Label lblPresentage;
    public static Stage preloderStage;

    public void initialize(){
        prosesBar.setStyle("-fx-progress-color: blue");
        proces();
    }

    private void proces(){


        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws InterruptedException {
                loadClasses();
                for (int i = 0; i <= 100; i++) {
                    updateProgress(i, 100);
                    Thread.sleep(30);
                }
                return null;
            }
        };

        prosesBar.progressProperty().bind(task.progressProperty());

        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();

        prosesBar.progressProperty().addListener((observable, oldValue, newValue) ->{
            if(oldValue!=newValue){
                int presentage = (int)Math.round((Double)newValue*100);
                lblPresentage.setText(presentage+"%");

                //set Your task for this
                if(presentage==100){
                    Stage stage = new Stage();
                    try {
                        stage.setScene(new Scene(FXMLLoader.load(LoginPageFormContoller.class.getResource("/view/LoginPageForm.fxml"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.centerOnScreen();
                    preloderStage.close();
                    stage.show();
                }
            }
        });
    }

    private void loadClasses() {
        ArrayList<String> classes = new ArrayList<>();
        classes.add("contoller.homePage.CashierHamburgerFormContoller");
        classes.add("contoller.homePage.HomePageFormContoller");
        classes.add("contoller.homePage.OwnerHamburgerFormcontoller");
        classes.add("contoller.mainTask.AddDeleteItemFormContoller");
        classes.add("contoller.mainTask.AddUserFormContoller");
        classes.add("contoller.mainTask.ManyItemTopUpWindowFormContoller");
        classes.add("contoller.mainTask.MasterReportContoller");
        classes.add("contoller.mainTask.SaleFormContoller");
        classes.add("contoller.mainTask.SeeBillFormContoller");
        classes.add("contoller.mainTask.ShowLastBillDeatailsFormContoller");
        classes.add("contoller.mainTask.StockManageFormContoller");
        classes.add("contoller.mainTask.ManyItemTopUpWindowFormContoller");
        classes.add("contoller.mainTask.StockAdjusmentTopUpWindowContoller");
        classes.add("contoller.utillity.BackupFormContoller");
        classes.add("contoller.utillity.ManageCustomerFormContoller");
        classes.add("contoller.utillity.ShortCutsFormContoller");
        classes.add("contoller.LoginPageFormContoller");
        classes.add("db.AutoBackUp");
        classes.add("db.DBConnection");
        classes.add("db.DBUtill");
        classes.add("Invoice.MainBill");
        classes.add("Invoice.DebetBill");
        classes.add("Invoice.StockManageBill");
        classes.add("module.BillStructure");
        classes.add("module.Customer");
        classes.add("module.CustomerTM");
        classes.add("module.DeleteReq");
        classes.add("module.Item");
        classes.add("module.ManyItemTopUp");
        classes.add("module.OrderDeatalsTM");
        classes.add("module.OrderDetails");
        classes.add("module.OrdersTM");
        classes.add("module.SaleFormLabelDataOrder");
        classes.add("module.SaleTableTM");
        classes.add("module.StockItemPrint");
        classes.add("module.StockItem");
        classes.add("module.StockSelectItem");
        classes.add("module.User");
        classes.add("module.UsertableTM");
        classes.add("Query.CustomerTableQuery");
        classes.add("Query.DeleteReqBillTableQuery");
        classes.add("Query.ItemTableQuery");
        classes.add("Query.OrderDetailTableQuery");
        classes.add("Query.OrderTableQuery");
        classes.add("Query.PathTableQuery");
        classes.add("Query.UserTableQuery");
        classes.add("util.CrudUtil");

        try {
            ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
            for (String s : classes) {
//                System.out.println(s);
                Class.forName(s);
                systemClassLoader.loadClass(s);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

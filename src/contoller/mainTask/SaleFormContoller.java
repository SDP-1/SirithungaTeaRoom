package contoller.mainTask;

import Invoice.MainBill;
import Query.CustomerTableQuery;
import Query.ItemTableQuery;
import Query.OrderDetailTableQuery;
import Query.OrderTableQuery;
import com.jfoenix.controls.JFXButton;
import contoller.LoginPageFormContoller;
import contoller.utillity.ManageCustomerFormContoller;
import db.DBConnection;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import module.ManyItemTopUp;
import module.OrderDetails;
import module.SaleFormLabelDataOrder;
import module.SaleTableTM;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Optional;


public class SaleFormContoller {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final ObservableList<SaleTableTM> list = FXCollections.observableArrayList();
    public static String cashierName;
    public static AnchorPane mainTaskOpenPain;
    public static TableView tblItem;
    public static boolean isOwner;
    private static int qty = 0;
    private static double totalAmount = 0;
    private static AutoCompletionBinding stringAutoCompletionBinding;
    public Label lblInvoiceNumber;
    public Label lblCashierName;
    public TextField txtBarCode;
    public TextField txtQty;
    public TextField txtMarkPrice;
    public TextField txtPrintName;
    public TextField txtWholesalePrice;
    public TextField txtRetailPrice;
    public TableColumn clmNumber;
    public TableColumn clmItemCode;
    public TableColumn clmDescription;
    public TableColumn clmQty;
    public TableColumn clmMarkPrice;
    public TableColumn clmPrice;
    public TableColumn clmNextAmount;
    public TableView tblSale;
    public Label txtTotalAmount;
    public Label txtNoOfItem;
    public CheckBox chbShowItemList;
    public Label lblNowBill;
    public TextField txtCustomerName;
    public Label upLableWhosale2;
    public Label UpLableWholesale;
    public Label upLableretail1;
    public Label upLableWholesale;
    public Label uplableRetil2;
    public JFXButton btnChangPrice;
    public TextField txtCash;
    public AnchorPane saleRAnkeyPane;
    public Label txtBalance;
    public Label lblLastBill1;
    public Label lblLastBill2;
    public Label lblLastBill3;
    public Label lblLastBill4;
    public Label lblLastBill5;
    public Label lblSelectInvoiceNo;
    public Label lblSaleType;
    public JFXButton btnChangeSaleType;
    public CheckBox chbSaleTypeChangEnShuwer;
    public Label lblDeleteDetect;
    AutoCompletionBinding<String> bindingCustomer;
    private SaleTableTM saleTableTMData;
    private int lable1;
    private int lable2;
    private int lable3;
    private int lable4;
    private int lable5;

//    protected static double cm_to_pp(double cm) {
//        return toPPI(cm * 0.393600787);
//    }
    protected static double cm_to_pp(double cm) {
        return toPPI(cm *  0.393600787);
    }

    protected static double toPPI(double inch) {
        return inch * 73d;
    }

    public void initialize() {
        lblCashierName.setText(cashierName);
        requestFocusOrDieTrying(txtBarCode);
        lblSaleType.setText("Sale-R");
        btnChangeSaleType.setDisable(true);
        advanceClear();
        getBacodeAndFillItemDeatils();
        setTableDeatils();
        searchItemGetCode();
        nowBillLableSet();
        setShortcuts();
        balanceUpdate();
        lastBillLableDataSet();
        deleteDitect();
        customerSugesens();
    }

    private void balanceUpdate() {
        txtCash.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != oldValue) {
                try {
                    txtBalance.setText(String.valueOf(Double.parseDouble(txtCash.getText()) - Double.parseDouble(txtTotalAmount.getText())));
                } catch (NumberFormatException e) {
                    txtBalance.setText("0");
                }
            }
        });
        txtTotalAmount.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != oldValue) {
                try {
                    txtBalance.setText(String.valueOf(Double.parseDouble(txtCash.getText()) - Double.parseDouble(txtTotalAmount.getText())));
                } catch (NumberFormatException e) {
                    txtBalance.setText("0");
                }
            }
        });
    }

    private void setShortcuts() {
        tblSale.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.DELETE) {
                    tableAddItemDelete();
                }
            }
        });
    }

    private void nowBillLableSet() {
        lblNowBill.setText("Invoice- " + lblInvoiceNumber.getText() + "\t\t\t\t" + txtCustomerName.getText() + "\nNo of item- " + txtNoOfItem.getText() + "\nTotal Amount- " + txtTotalAmount.getText());
        txtTotalAmount.textProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != newValue) {
                lblNowBill.setText("Invoice- " + lblInvoiceNumber.getText() + "\t\t\t\t" + txtCustomerName.getText() + "\nNo of item- " + txtNoOfItem.getText() + "\nTotal Amount- " + txtTotalAmount.getText());
            }
        });
        txtCustomerName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != newValue) {
                lblNowBill.setText("Invoice- " + lblInvoiceNumber.getText() + "\t\t\t\t" + txtCustomerName.getText() + "\nNo of item- " + txtNoOfItem.getText() + "\nTotal Amount- " + txtTotalAmount.getText());
            }
        });
    }

    private void lastBillLableDataSet() {
        try {
            ArrayList<SaleFormLabelDataOrder> list = OrderTableQuery.getLastBillsDataForLable();

            for (int i = 0; i < list.size(); i++) {
                switch (i) {
                    case 0: {
                        lblLastBill1.setText(list.get(i).getDate() + "\t\t\t\t" + list.get(i).getTime() + "\nInvoice- " + list.get(i).getInvoiceNo() + "\t\t\t\t" + list.get(i).getCustomer() + "\nTotal Cost- " + list.get(i).getTotalCost() + "\t\t\tNo of Item- " + list.get(i).getNoOfItem());
                        lable1 = list.get(i).getInvoiceNo();
                    }
                    break;
                    case 1: {
                        lblLastBill2.setText(list.get(i).getDate() + "\t\t\t\t" + list.get(i).getTime() + "\nInvoice- " + list.get(i).getInvoiceNo() + "\t\t\t\t" + list.get(i).getCustomer() + "\nTotal Cost- " + list.get(i).getTotalCost() + "\t\t\tNo of Item- " + list.get(i).getNoOfItem());
                        lable2 = list.get(i).getInvoiceNo();
                    }
                    break;
                    case 2: {
                        lblLastBill3.setText(list.get(i).getDate() + "\t\t\t\t" + list.get(i).getTime() + "\nInvoice- " + list.get(i).getInvoiceNo() + "\t\t\t\t" + list.get(i).getCustomer() + "\nTotal Cost- " + list.get(i).getTotalCost() + "\t\t\tNo of Item- " + list.get(i).getNoOfItem());
                        lable3 = list.get(i).getInvoiceNo();
                    }
                    break;
                    case 3: {
                        lblLastBill4.setText(list.get(i).getDate() + "\t\t\t\t" + list.get(i).getTime() + "\nInvoice- " + list.get(i).getInvoiceNo() + "\t\t\t\t" + list.get(i).getCustomer() + "\nTotal Cost- " + list.get(i).getTotalCost() + "\t\t\tNo of Item- " + list.get(i).getNoOfItem());
                        lable4 = list.get(i).getInvoiceNo();
                    }
                    break;
                    case 4: {
                        lblLastBill5.setText(list.get(i).getDate() + "\t\t\t\t" + list.get(i).getTime() + "\nInvoice- " + list.get(i).getInvoiceNo() + "\t\t\t\t" + list.get(i).getCustomer() + "\nTotal Cost- " + list.get(i).getTotalCost() + "\t\t\tNo of Item- " + list.get(i).getNoOfItem());
                        lable5 = list.get(i).getInvoiceNo();
                    }
                    break;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void searchItemGetCode() {
        txtBarCode.textProperty().addListener((observable, oldValue, newValue) -> {
            String s = newValue;
            String[] split = s.split("    ");
            if (oldValue != newValue) {
                txtBarCode.setText(split[0]);
            }
        });
    }

    private void setTableDeatils() {
        clmNumber.setCellValueFactory(new PropertyValueFactory<>("no"));
        clmItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        clmDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        clmQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        clmMarkPrice.setCellValueFactory(new PropertyValueFactory<>("markPrice"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        clmNextAmount.setCellValueFactory(new PropertyValueFactory<>("nextAmount"));
        clmMarkPrice.setStyle("-fx-alignment: CENTER-RIGHT;");
        clmPrice.setStyle("-fx-alignment: CENTER-RIGHT;");
        clmNextAmount.setStyle("-fx-alignment: CENTER-RIGHT;");
        clmQty.setStyle("-fx-alignment: CENTER;");
        tblSale.setStyle("-fx-font-size: 15pt;");
    }

    private void getBacodeAndFillItemDeatils() {
        txtBarCode.textProperty().addListener((observable, oldValue, newValue) -> {
            if (txtBarCode.getText().length() == 13) {
                try {
                    int count = ItemTableQuery.thisBacodeItemCount(txtBarCode.getText());

                    ResultSet resultSet = ItemTableQuery.getThisBacodeitemsResalSet(txtBarCode.getText());
                    if (count == 1) {
                        fill(resultSet);
                    } else if (count > 1) {
                        load(resultSet);
                    } else if (count == 0) {
                        ImageView imageView1 = new ImageView("image/Notifications/error.png");
                        imageView1.setFitWidth(50);
                        imageView1.setFitHeight(50);
                        Notifications n1 = Notifications.create()
                                .darkStyle()
                                .title("ERROR!")
                                .text("This barcode code is not in the system.")
                                .position(Pos.BOTTOM_LEFT)
                                .graphic(imageView1);
                        n1.show();
                        return;
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                } catch (IOException e) {
                    e.printStackTrace();
                }
                requestFocusOrDieTrying(txtQty);
                txtQty.setText("1");
                txtQty.selectAll();
            }
        });
    }

    private void fill(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            txtMarkPrice.setText(resultSet.getString(10));
            txtRetailPrice.setText(resultSet.getString(13));
            txtWholesalePrice.setText(resultSet.getString(14));
            txtPrintName.setText(resultSet.getString(4));

            saleTableTMData = new SaleTableTM(
                    list.size() + 1,
                    resultSet.getInt(1),
                    resultSet.getString(4),
                    0,
                    resultSet.getDouble(10),
                    resultSet.getDouble(13),
                    0,
                    resultSet.getInt(2),
                    resultSet.getBoolean(8),
                    resultSet.getDouble(9)
            );
        }
    }

    private void requestFocusOrDieTrying(Node node) {
        Platform.runLater(() -> {
            if (!node.isFocused()) {
                node.requestFocus();
                requestFocusOrDieTrying(node);
            }
        });
    }

    private void load(String location) throws IOException {
        mainTaskOpenPain.getChildren().clear();
        Parent load = FXMLLoader.load(LoginPageFormContoller.class.getResource(location));
        mainTaskOpenPain.getChildren().add(load);
    }

    public void txtQtyOnAction(ActionEvent actionEvent) {
        ImageView imageView1 = new ImageView("image/Notifications/error.png");
        imageView1.setFitWidth(50);
        imageView1.setFitHeight(50);
        Notifications n1 = Notifications.create()
                .darkStyle()
                .title("ERROR!")
                .position(Pos.BOTTOM_LEFT)
                .graphic(imageView1);
        if (txtBarCode.getText().isEmpty()) {
            n1.text("Please enter barcode.");
            n1.show();
            return;
        }
        try {
            if (Double.parseDouble(txtQty.getText()) > 1000 || Double.parseDouble(txtQty.getText()) <= 0) {
                n1.text("Rechack Qty...");
                n1.show();
                return;
            }
        } catch (NumberFormatException e) {
            n1.text("Rechack Qty...");
            n1.show();
            return;
        }
        if (saleTableTMData.isDecimal()) {
            qty++;
        } else {
            try {
                qty += Integer.parseInt(txtQty.getText());
            } catch (NumberFormatException e) {
                n1.text("Rechack Qty...");
                n1.show();
                return;
            }
        }
        if (txtRetailPrice.isDisable()) {
            totalAmount += Double.parseDouble(txtQty.getText()) * Double.parseDouble(txtWholesalePrice.getText());
        } else {
            try {
                totalAmount += Double.parseDouble(txtQty.getText()) * Double.parseDouble(txtRetailPrice.getText());
            } catch (NumberFormatException e) {
            }
        }
        tableDatafill();
        txtBarCode.requestFocus();
        txtQty.clear();
        txtBarCode.clear();
        primaryClear();
    }

    public void load(ResultSet resultSet) throws IOException, SQLException, ClassNotFoundException {

        Stage stage = new Stage();
        ManyItemTopUpWindowFormContoller.resultSet = resultSet;
        stage.setScene(new Scene(FXMLLoader.load(LoginPageFormContoller.class.getResource("../view/mainTask/ManyItemTopUpWindowForm.fxml"))));
        stage.setTitle("SELECT ITEM");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();


        ManyItemTopUp select = (ManyItemTopUp) tblItem.getSelectionModel().getSelectedItem();
        ResultSet resultSet1 = ItemTableQuery.getThisCodeItemsResalSet(select.getCode(), select.getCode2());
        fill(resultSet1);

        tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            ManyItemTopUp select2 = (ManyItemTopUp) tblItem.getSelectionModel().getSelectedItem();
            ResultSet resultSet2 = null;
            try {
                resultSet2 = ItemTableQuery.getThisCodeItemsResalSet(select2.getCode(), select2.getCode2());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                fill(resultSet2);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });


    }

    public void codeClick(MouseEvent mouseEvent) {
        if (chbShowItemList.isSelected()) {
            if (!txtBarCode.getText().isEmpty()) {
                ImageView imageView1 = new ImageView("image/Notifications/information.png");
                imageView1.setFitWidth(50);
                imageView1.setFitHeight(50);
                Notifications n1 = Notifications.create()
                        .darkStyle()
                        .title("Information")
                        .text("Remove the tick mark to clear.\n\tOR\nPress Clear")
                        .position(Pos.BOTTOM_LEFT)
                        .graphic(imageView1);
                n1.show();
            }
        } else {
            primaryClear();
        }
    }

    private void primaryClear() {
        txtBarCode.clear();
        txtQty.clear();
        txtMarkPrice.clear();
        txtRetailPrice.clear();
        txtWholesalePrice.clear();
        txtPrintName.clear();
        txtBarCode.requestFocus();
        saleTableTMData = null;

        if (lblSaleType.getText().equals("Sale-R")) {
            upLableWholesale.setDisable(true);
            upLableWhosale2.setDisable(true);
            txtWholesalePrice.setDisable(true);

            txtRetailPrice.setDisable(false);
            upLableretail1.setDisable(false);
            uplableRetil2.setDisable(false);

            btnChangPrice.setText("-->");
        } else {
            upLableWholesale.setDisable(false);
            upLableWhosale2.setDisable(false);
            txtWholesalePrice.setDisable(false);

            txtRetailPrice.setDisable(true);
            upLableretail1.setDisable(true);
            uplableRetil2.setDisable(true);

            btnChangPrice.setText("<--");
        }

        chbSaleTypeChangEnShuwer.setSelected(false);

        lblSelectInvoiceNo.setText("--");
    }

    private void tableDatafill() {
        try {
            saleTableTMData.setQty(Double.parseDouble(txtQty.getText()));
            saleTableTMData.setMarkPrice(Double.parseDouble((df.format(Double.parseDouble(txtMarkPrice.getText())))));
            saleTableTMData.setPrice(Double.parseDouble(df.format(Double.parseDouble(txtRetailPrice.isDisable() ? txtWholesalePrice.getText() : txtRetailPrice.getText()))));
            saleTableTMData.setNextAmount(txtRetailPrice.isDisable() ? Double.parseDouble(df.format(Double.parseDouble(txtWholesalePrice.getText()) * Double.parseDouble(txtQty.getText()))) : Double.parseDouble(df.format(Double.parseDouble(txtRetailPrice.getText()) * Double.parseDouble(txtQty.getText()))));
            saleTableTMData.setDescription(txtPrintName.getText());

            for (SaleTableTM tm : list) {
                if (saleTableTMData.getItemCode() == tm.getItemCode() && saleTableTMData.getCode2() == tm.getCode2()) {
                    tm.setQty(tm.getQty() + saleTableTMData.getQty());
                    tm.setNextAmount(tm.getNextAmount() + saleTableTMData.getNextAmount());
                    txtNoOfItem.setText(String.valueOf(qty));
                    txtTotalAmount.setText(String.valueOf(df.format(totalAmount)));
                    tblSale.refresh();
                    return;
                }
            }
            list.add(saleTableTMData);
            tblSale.setItems(list);
            txtNoOfItem.setText(String.valueOf(qty));
            txtTotalAmount.setText(String.valueOf(df.format(totalAmount)));
        } catch (NumberFormatException e) {
            return;
        }

    }

    private void advanceClear() {
        try {
            chbShowItemList.setSelected(false);
             if(stringAutoCompletionBinding!=null)stringAutoCompletionBinding.dispose();

            lblInvoiceNumber.setText(String.valueOf(OrderTableQuery.getNewInvoiceNo()));
            primaryClear();
            txtNoOfItem.setText("");
            txtTotalAmount.setText("");
            txtCash.clear();
            txtBalance.setText("");
            qty = 0;
            totalAmount = 0;
            list.clear();
            txtCustomerName.clear();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        askAndFullClear();
    }

    private void askAndFullClear() {
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "New order?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {
            advanceClear();
//            ImageView imageView1 = new ImageView("image/Notifications/tick.png");
//            imageView1.setFitWidth(50);
//            imageView1.setFitHeight(50);
//            Notifications n1 = Notifications.create()
//                    .darkStyle()
//                    .title("SUCCESS")
//                    .text("Clear...")
//                    .position(Pos.BOTTOM_RIGHT)
//                    .graphic(imageView1);
//            n1.show();
        }
    }

    public void isOnShowItemList(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        HashSet<String> list = new HashSet<>();
        if (chbShowItemList.isSelected()) {
            list.clear();
            list = ItemTableQuery.getCodesAndNamesInItems();
            stringAutoCompletionBinding = TextFields.bindAutoCompletion(txtBarCode, SuggestionProvider.create(list));
            stringAutoCompletionBinding.setVisibleRowCount(20);
            stringAutoCompletionBinding.setMinWidth(600);
            requestFocusOrDieTrying(txtBarCode);
        } else {
            stringAutoCompletionBinding.dispose();
            requestFocusOrDieTrying(txtBarCode);
        }
    }

    private void acseptOnlyNumbers(KeyEvent keyEvent, TextField t) {
        if (!(Character.isDigit(keyEvent.getCharacter().charAt(0)) || keyEvent.getCharacter().charAt(0) == '.' || keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.BACK_SPACE))) {
            keyEvent.consume();
        }
        if (keyEvent.getCharacter().charAt(0) == '.') {
            boolean have = false;
            for (int i = 0; i < t.getText().length(); i++) {
                if (t.getText().charAt(i) == '.') {
                    have = true;
                    break;
                }
            }
            if (have) {
                keyEvent.consume();
            }
        }
    }

    public void btnPrimaryClearOnAction(ActionEvent actionEvent) {
        primaryClear();
    }

    public void txtCodeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ImageView imageView1 = new ImageView("image/Notifications/error.png");
        imageView1.setFitWidth(50);
        imageView1.setFitHeight(50);
        Notifications n1 = Notifications.create()
                .darkStyle()
                .title("ERROR!")
                .text("This item code is not in the system.")
                .position(Pos.BOTTOM_LEFT)
                .graphic(imageView1);
        try {
            if (Integer.parseInt(txtBarCode.getText()) < Integer.MAX_VALUE) {
                int count = ItemTableQuery.thisCodeItemCount(Integer.parseInt(txtBarCode.getText()));
                ResultSet resultSet = ItemTableQuery.getThisCodeItemsResalSet(Integer.parseInt(txtBarCode.getText()));
                if (count == 1) {
                    fill(resultSet);
                    txtQty.requestFocus();
                    txtQty.setText("1");
                    txtQty.selectAll();
                } else if (count > 1) {
                    load(resultSet);
                    txtQty.setText("1");
                } else if (count == 0) {
                    n1.show();
                    return;
                }
            }
        } catch (NumberFormatException e) {
            n1.show();
            return;
        } catch (IOException e) {
        }
        requestFocusOrDieTrying(txtQty);
        txtQty.selectAll();
    }

    public void tableDataDeleteOnAction(ActionEvent actionEvent) {
        tableAddItemDelete();
    }

    private void tableAddItemDelete() {
        ImageView imageView1 = new ImageView("image/Notifications/error.png");
        imageView1.setFitWidth(50);
        imageView1.setFitHeight(50);
        Notifications n1 = Notifications.create()
                .darkStyle()
                .title("ERROR!")
                .position(Pos.BOTTOM_LEFT)
                .graphic(imageView1);
        SaleTableTM selectDeleteItem = (SaleTableTM) tblSale.getSelectionModel().getSelectedItem();
        if (selectDeleteItem == null) {
            n1.text("Not selected row");
            n1.show();
            return;
        }
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Delete row number " + selectDeleteItem.getNo() + " ?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {
            if (selectDeleteItem.isDecimal()) {
                qty--;
            } else {
                qty -= selectDeleteItem.getQty();
            }
            txtNoOfItem.setText(String.valueOf(qty));
            totalAmount -= selectDeleteItem.getNextAmount();
            txtTotalAmount.setText(String.valueOf(totalAmount));
            list.remove(selectDeleteItem);
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setNo(i + 1);
            }
            tblSale.setItems(list);
            requestFocusOrDieTrying(txtBarCode);
        }
    }

    public void codeKeyType(KeyEvent keyEvent) {
        if (!chbShowItemList.isSelected()) {
            if (!(Character.isDigit(keyEvent.getCharacter().charAt(0)) || keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.BACK_SPACE))) {
                keyEvent.consume();
            }
        }
    }

    public void continueOtherPriceOnAction(ActionEvent actionEvent) {

        if (btnChangPrice.getText().equals("-->")) {
            upLableWholesale.setDisable(false);
            upLableWhosale2.setDisable(false);
            txtWholesalePrice.setDisable(false);

            txtRetailPrice.setDisable(true);
            upLableretail1.setDisable(true);
            uplableRetil2.setDisable(true);

            btnChangPrice.setText("<--");

            txtWholesalePrice.requestFocus();
            txtWholesalePrice.selectAll();
        } else {
            upLableWholesale.setDisable(true);
            upLableWhosale2.setDisable(true);
            txtWholesalePrice.setDisable(true);

            txtRetailPrice.setDisable(false);
            upLableretail1.setDisable(false);
            uplableRetil2.setDisable(false);

            btnChangPrice.setText("-->");

            txtRetailPrice.requestFocus();
            txtRetailPrice.selectAll();
        }

    }

    public void txtMarkPriceOnAction(ActionEvent actionEvent) {
        if (txtRetailPrice.isDisable()) {
            txtWholesalePrice.requestFocus();
            txtWholesalePrice.selectAll();
        } else {
            txtRetailPrice.requestFocus();
            txtRetailPrice.selectAll();
        }
    }

    public void txtRetailPriceOnAction(ActionEvent actionEvent) {
        txtPrintName.requestFocus();
        txtPrintName.selectAll();
    }

    public void printNameOnAction(ActionEvent actionEvent) {
        txtQty.requestFocus();
        txtQty.selectAll();
    }

    public void txtWhosalePriceOnAction(ActionEvent actionEvent) {
        txtPrintName.requestFocus();
        txtPrintName.selectAll();
    }

    public void whosaleKeyType(KeyEvent keyEvent) {
        acseptOnlyNumbers(keyEvent, txtWholesalePrice);
    }

    public void retailKeyType(KeyEvent keyEvent) {
        acseptOnlyNumbers(keyEvent, txtRetailPrice);
    }

    public void MarkPriceKeyType(KeyEvent keyEvent) {
        acseptOnlyNumbers(keyEvent, txtMarkPrice);
    }

    public void qtyKeyType(KeyEvent keyEvent) {
        acseptOnlyNumbers(keyEvent, txtQty);
    }

    public void cashKeyType(KeyEvent keyEvent) {
        acseptOnlyNumbers(keyEvent, txtCash);
    }

    public void saleRAnkerPaneKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.F9) {
            askAndFullClear();
        } else if (keyEvent.getCode() == KeyCode.INSERT) {
            txtCash.requestFocus();
            txtCash.selectAll();
        }
    }

    //-------------------------------------------------------------------Satrt--------------------------------------------------------------------------------------------------------

    public void bacodeOnKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.F9) {
            askAndFullClear();
        } else if (keyEvent.getCode() == KeyCode.INSERT) {
            txtCash.requestFocus();
            txtCash.selectAll();
        }
    }

    public void btnPrintOnAction(ActionEvent actionEvent) throws SQLException {
        try {
            if (Double.parseDouble(txtCash.getText()) >= 0) {
                setOrder();
            }
        } catch (NumberFormatException e) {
            ImageView imageView1 = new ImageView("image/Notifications/error.png");
            imageView1.setFitWidth(50);
            imageView1.setFitHeight(50);
            Notifications n1 = Notifications.create()
                    .darkStyle()
                    .title("ERROR!")
                    .position(Pos.BOTTOM_LEFT)
                    .text("Cash empty.")
                    .graphic(imageView1);
            n1.show();
        }
    }

    private void setOrder() throws SQLException {
        ImageView imageView1 = new ImageView("image/Notifications/error.png");
        imageView1.setFitWidth(50);
        imageView1.setFitHeight(50);
        Notifications n1 = Notifications.create()
                .darkStyle()
                .title("ERROR!")
                .position(Pos.BOTTOM_LEFT)
                .graphic(imageView1);

        if (list.isEmpty()) {
            n1.text("Order is empty.");
            n1.show();
            return;
        }

        if (Double.parseDouble(txtBalance.getText()) < 0) {
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "A loan bill?", ButtonType.NO, ButtonType.YES).showAndWait();
            if (buttonType.get().equals(ButtonType.NO)) {
                txtCash.requestFocus();
                txtCash.selectAll();
                return;
            }
        }

        Connection connection = null;
        OrderDetails orderDetails = null;
        try {
            connection = DBConnection.getInstace().getConection();
            connection.setAutoCommit(false);
            orderDetails = new OrderDetails(
                    Integer.parseInt(lblInvoiceNumber.getText()),
                    new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()),
                    new SimpleDateFormat("hh:mm:ss aa").format(Calendar.getInstance().getTime()),
                    txtCustomerName.getText().isEmpty() ? "" : txtCustomerName.getText(),
                    getSaleType(),
                    Integer.parseInt(txtNoOfItem.getText()),
                    Double.parseDouble(txtTotalAmount.getText()),
                    Double.parseDouble(txtCash.getText()),
                    Double.parseDouble(txtBalance.getText()),
                    Double.parseDouble(txtTotalAmount.getText()) > Double.parseDouble(txtCash.getText()),
                    discount(),
                    getTotalBuyingPrice(),
                    lblCashierName.getText()
            );
            boolean isOrderSave = OrderTableQuery.saveOrder(orderDetails);
            if (isOrderSave) {
                boolean isDeatilsSave = OrderDetailTableQuery.saveOrderDetails(Integer.parseInt(lblInvoiceNumber.getText()), list);
                if (isDeatilsSave) {
                    connection.commit();

                    double bHeight = Double.valueOf(list.size());
//                    JOptionPane.showMessageDialog(rootPane, bHeight);

                    PrinterJob pj = PrinterJob.getPrinterJob();
                    pj.setPrintable(new MainBill(orderDetails, list), getPageFormat(pj, bHeight));
                    try {
                        pj.print();

                    } catch (PrinterException ex) {
                        ex.printStackTrace();
                    }


                    ImageView imageView = new ImageView("image/Notifications/tick.png");
                    imageView.setFitWidth(50);
                    imageView.setFitHeight(50);
                    Notifications n = Notifications.create()
                            .darkStyle()
                            .title("SUCCESS")
                            .position(Pos.BOTTOM_RIGHT)
                            .text("The bill was sent to the printer")
                            .graphic(imageView);
                    n.show();

                    lastBillLableDataSet();

                    Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "New Order?", ButtonType.YES, ButtonType.NO).showAndWait();
                    if (buttonType.get().equals(ButtonType.YES)) {
                        advanceClear();
                    }
                } else {
                    connection.rollback();
                    n1.text("The bill was sent to the printer fail");
                    n1.show();
                }
            } else {
                connection.rollback();
                n1.text("The bill was sent to the printer fail");
                n1.show();
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            try {
                OrderTableQuery.deleteOrder(Integer.parseInt(lblInvoiceNumber.getText()));
                setOrder();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
    }



    //-------------------------------------------------------------------END------------------------------------------------------------------------------
    private boolean getSaleType() {
        String saletype = lblSaleType.getText();
        return saletype.charAt(saletype.length()-1)=='R';
    }

private double getTotalBuyingPrice(){
        double totalBingPrice=0;

        for(SaleTableTM x:list){
            totalBingPrice += x.getBuyingPrice() * x.getQty();
        }

        return totalBingPrice;
}

    public PageFormat getPageFormat(PrinterJob pj, double bHeight) {

        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();

        double bodyHeight = bHeight;
        double headerHeight = 5.0;
        double footerHeight = 5.0;
        double width = cm_to_pp(8);
        double height = cm_to_pp(headerHeight + bodyHeight + footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(0, 10, width, height - cm_to_pp(1));

        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(paper);

        return pf;
    }

    public void cashOnkeypress(KeyEvent keyEvent) throws SQLException {
        try {
            if (keyEvent.getCode().equals(KeyCode.PRINTSCREEN) && Double.parseDouble(txtCash.getText()) >= 0) {
                setOrder();
            }
        } catch (NumberFormatException e) {
            ImageView imageView1 = new ImageView("image/Notifications/error.png");
            imageView1.setFitWidth(50);
            imageView1.setFitHeight(50);
            Notifications n1 = Notifications.create()
                    .darkStyle()
                    .title("ERROR!")
                    .position(Pos.BOTTOM_LEFT)
                    .text("Cash empty.")
                    .graphic(imageView1);
            n1.show();
        }
    }

    private double discount() {
        double discount = 0;
        double totalMarksPrices=0;
        double totalSaleprice=0;
        for (SaleTableTM tm : list) {
//            discount += (tm.getMarkPrice() - tm.getPrice()) * tm.getQty();
            totalMarksPrices += tm.getMarkPrice()*tm.getQty();
            totalSaleprice += tm.getNextAmount();
        }
        discount = totalMarksPrices - totalSaleprice;
        return discount;
    }

    public void lblLastBill1OnMouseClick(MouseEvent mouseEvent) {

        lblSelectInvoiceNo.setText(lable1 == 0 ? "--" : String.valueOf(lable1));
    }

    public void lblLastBill2OnMouseClick(MouseEvent mouseEvent) {
        lblSelectInvoiceNo.setText(lable2 == 0 ? "--" : String.valueOf(lable2));
    }

    public void lblLastBill3OnMouseClick(MouseEvent mouseEvent) {
        lblSelectInvoiceNo.setText(lable3 == 0 ? "--" : String.valueOf(lable3));
    }

    public void lblLastBill4OnMouseClick(MouseEvent mouseEvent) {
        lblSelectInvoiceNo.setText(lable4 == 0 ? "--" : String.valueOf(lable4));
    }

    public void lblLastBill5OnMouseClick(MouseEvent mouseEvent) {
        lblSelectInvoiceNo.setText(lable5 == 0 ? "--" : String.valueOf(lable5));
    }

    public void btnChangeSaleTypeOnAction(ActionEvent actionEvent) {
        if (btnChangeSaleType.getText().equalsIgnoreCase("Whole Sale")) {
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Continue to wholesale?", ButtonType.NO, ButtonType.YES).showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {
                btnChangeSaleType.setText("Retail Sale");
                lblSaleType.setText("Sale-W");
                advanceClear();
            }
        } else {
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Continue to RetailSale?", ButtonType.NO, ButtonType.YES).showAndWait();
            if ((buttonType.get().equals(ButtonType.YES))) {
                btnChangeSaleType.setText("Whole Sale");
                lblSaleType.setText("Sale-R");
                advanceClear();
            }
        }
        chbSaleTypeChangEnShuwer.setSelected(false);
        btnChangeSaleType.setDisable(true);
    }

    public void chbSaleTypeChangEnShuwerOnAction(ActionEvent actionEvent) {
        btnChangeSaleType.setDisable(!chbSaleTypeChangEnShuwer.isSelected());
    }

    public void menuPrintOnAction(ActionEvent actionEvent) {
        try {
            if (Double.parseDouble(txtCash.getText()) >= 0) {
                setOrder();
            }
        } catch (NumberFormatException | SQLException e) {
            ImageView imageView1 = new ImageView("image/Notifications/error.png");
            imageView1.setFitWidth(50);
            imageView1.setFitHeight(50);
            Notifications n1 = Notifications.create()
                    .darkStyle()
                    .title("ERROR!")
                    .position(Pos.BOTTOM_LEFT)
                    .text("Cash empty.")
                    .graphic(imageView1);
            n1.show();
        }
    }

    public void menuSaveCopyAndPrint(ActionEvent actionEvent) {
    }

    private void deleteDitect() {
        lblDeleteDetect.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != oldValue) {
                lastBillLableDataSet();
                lblSelectInvoiceNo.setText("--");
                try {
                    lblInvoiceNumber.setText(String.valueOf(OrderTableQuery.getNewInvoiceNo()));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void btnLastBillShowOnAction(ActionEvent actionEvent) {
        try {
            int invoiceNo = Integer.parseInt(lblSelectInvoiceNo.getText());
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Show Bill No- " + lblSelectInvoiceNo.getText() + " ?", ButtonType.YES, ButtonType.NO).showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {
                ShowLastBillDeatailsFormContoller.tempLbl = lblDeleteDetect;
                ShowLastBillDeatailsFormContoller.isOwner = isOwner;
                Stage stage = new Stage();
                ShowLastBillDeatailsFormContoller.invoiceNo = invoiceNo;
                stage.setScene(new Scene(FXMLLoader.load(LoginPageFormContoller.class.getResource("../view/mainTask/ShowLastBillDeatailsForm.fxml"))));
                stage.setResizable(false);
                stage.getIcons().add(new Image("image/hamburgerOpenPage/seeBill.png"));
                stage.centerOnScreen();
                stage.setTitle("Invoice No- " + invoiceNo + " Details");
                stage.show();
            }
        } catch (NumberFormatException | IOException e) {
        }
    }

    private void customerSugesens() {
        ManageCustomerFormContoller.saleFormSearchCustomer = txtCustomerName;
        ManageCustomerFormContoller.bindingCustomer = bindingCustomer;
        HashSet<String> list;
        try {

            list = CustomerTableQuery.getCustomerNames();
            bindingCustomer = TextFields.bindAutoCompletion(txtCustomerName, SuggestionProvider.create(list));
            bindingCustomer.setVisibleRowCount(15);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
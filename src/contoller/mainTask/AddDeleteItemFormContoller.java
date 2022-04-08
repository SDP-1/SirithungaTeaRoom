package contoller.mainTask;

import Query.ItemTableQuery;
import com.jfoenix.controls.JFXButton;
import contoller.LoginPageFormContoller;
import db.AutoBackUp;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import module.Item;
import module.ManyItemTopUp;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import util.CrudUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

public class AddDeleteItemFormContoller {
    public static AutoCompletionBinding<String> stringAutoCompletionBinding;
    public static TableView tblItem;
    public TextField txtItemCode;
    public RadioButton rdbNo;
    public ToggleGroup decimal;
    public TextField txtItemName;
    public TextField txtPrintName;
    public TextField txtBarCode;
    public TextField txtSupplier;
    public TextField txtStock;
    public CheckBox chcbAdvanceSearch;
    public ComboBox<Integer> cmbxCode2;
    public TextField txtPriceOfBuying;
    public TextField txtMarkPrice;
    public TextField txtRetailPrice;
    public TextField txtWholesalePrice;
    public TextField txtRetailRatio;
    public TextField txtWholesalePriceRaito;
    public CheckBox chcbRetailPriceRatioEnable;
    public CheckBox chcbWholesalePriceRatioEnable;
    public JFXButton btnAutoGeanarate;
    public JFXButton btnDelete;
    public RadioButton rdbYes;
    public JFXButton btnSave;
    public Label lblRatioPrice;
    public Label lblWholesalePrice;
    public TextField txtPlusQty;
    public Label lblPlusQty;
    private double oldStock;

    //-------------------------Save------And---------Update-----------------------------

    public void initialize() {

        for (int i = 0; i < 5; i++) {
            cmbxCode2.getItems().add(i);
        }
        cmbxCode2.getSelectionModel().select(0);
        clear();
        requestFocusOrDieTrying(txtBarCode);

        cmbxCode2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != newValue) {
                try {
                    if (ItemTableQuery.isItemHave(txtItemCode.getText(), cmbxCode2.getValue())) {
                        btnSave.setText("Update");
                    } else {
                        btnSave.setText("Save");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });


        txtItemCode.textProperty().addListener((observable, oldValue, newValue) -> {
            if (chcbAdvanceSearch.isSelected()) {
                String s = newValue;
                String[] split = s.split("    ");
                if (oldValue != newValue) {
                    txtItemCode.setText(split[0]);
                }
            } else {
            }
        });


        lablePriceUpdate();

        txtBarCode.textProperty().addListener((observable, oldValue, newValue) -> {
            if (txtBarCode.getText().length() >= 13 && !txtItemCode.isDisable()) {
                try {
                    find();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        lblRatioPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=oldValue){
                txtRetailPrice.setText(lblRatioPrice.getText());
            }
        });

        lblWholesalePrice.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=oldValue){
                txtWholesalePrice.setText(lblWholesalePrice.getText());
            }
        });

        supplierSuggestions();

        qtyUpdate();
    }

    private void qtyUpdate() {
        txtPlusQty.setOnKeyReleased(event -> {
            try {
                if (txtPlusQty.getText().isEmpty() || Double.parseDouble(txtPlusQty.getText()) == 0) {
                    txtStock.setText(String.valueOf(oldStock));
                    btnSave.setDisable(false);
                } else {
                    txtStock.setText(String.valueOf(oldStock + Double.parseDouble(txtPlusQty.getText())));
                    btnSave.setDisable(false);
                }
                if(Double.parseDouble(txtStock.getText())<0){
                    btnSave.setDisable(true);
                }

            }catch (NumberFormatException e){
                btnSave.setDisable(true);
                return;
            }
        });
    }

    private void requestFocusOrDieTrying(Node node) {
        Platform.runLater(() -> {
            if (!node.isFocused()) {
                node.requestFocus();
                requestFocusOrDieTrying(node);
            }
        });
    }

    //---------------------Lable Price Update-----------------------------------------------------
    private void lablePriceUpdate() {
        txtRetailRatio.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("") || Double.parseDouble(newValue) == 0) {
                lblRatioPrice.setText("");
            } else {
                try {
                    DecimalFormat df = new DecimalFormat("0.00");
                    double price = Double.parseDouble(txtMarkPrice.getText()) - ((Double.parseDouble(txtMarkPrice.getText()) * Double.parseDouble(txtRetailRatio.getText())) / 100);
                    lblRatioPrice.setText(df.format(price));
                } catch (NumberFormatException e) {
                }
            }
        });

        txtMarkPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if ((newValue.equals("") || Double.parseDouble(newValue) == 0)) {
                    lblRatioPrice.setText("");
                    lblWholesalePrice.setText("");
                } else if (Double.parseDouble(txtRetailRatio.getText()) == 0) {
                    lblRatioPrice.setText("");
                } else if (Double.parseDouble(txtRetailRatio.getText()) == 0) {
                    lblWholesalePrice.setText("");
                } else {
                    try {
                        DecimalFormat df = new DecimalFormat("0.00");
                        double price = Double.parseDouble(txtMarkPrice.getText()) - ((Double.parseDouble(txtMarkPrice.getText()) * Double.parseDouble(txtRetailRatio.getText())) / 100);
                        lblRatioPrice.setText(df.format(price));
                        double price2 = Double.parseDouble(txtMarkPrice.getText()) - ((Double.parseDouble(txtMarkPrice.getText()) * Double.parseDouble(txtWholesalePriceRaito.getText())) / 100);
                        lblWholesalePrice.setText(df.format(price2));
                    } catch (NumberFormatException e) {
                    }
                }
            } catch (NumberFormatException e) {
            }
        });

        txtWholesalePriceRaito.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("") || Double.parseDouble(newValue) == 0) {
                lblWholesalePrice.setText("");
            } else {
                try {
                    DecimalFormat df = new DecimalFormat("0.00");
                    double price = Double.parseDouble(txtMarkPrice.getText()) - ((Double.parseDouble(txtMarkPrice.getText()) * Double.parseDouble(txtWholesalePriceRaito.getText())) / 100);
                    lblWholesalePrice.setText(df.format(price));
                } catch (NumberFormatException e) {
                }
            }
        });
    }
    //------------------------Save-------------------------------------------------------------------

    public void btnClear(ActionEvent actionEvent) {
        clear();
    }

    public void chcbRetailPriceRatioEnableOnAction(ActionEvent actionEvent) {
        if (chcbRetailPriceRatioEnable.isSelected()) {
            txtRetailRatio.setDisable(false);
            txtRetailPrice.setDisable(true);
            txtRetailPrice.clear();

        } else {
            txtRetailRatio.setDisable(true);
            txtRetailPrice.setDisable(false);
            txtRetailRatio.clear();
        }
    }
    //---------------------------------------

    public void chcbWholesalePriceRatioEnableOnAction(ActionEvent actionEvent) {
        if (chcbWholesalePriceRatioEnable.isSelected()) {
            txtWholesalePriceRaito.setDisable(false);
            txtWholesalePrice.setDisable(true);
            txtWholesalePrice.clear();
        } else {
            txtWholesalePriceRaito.setDisable(true);
            txtWholesalePrice.setDisable(false);
            txtWholesalePriceRaito.clear();
        }
    }

    public void saveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (btnSave.getText().equals("Save")) {
            save();
        } else {
            update();
        }
    }

    private void save() {
        ImageView imageView = new ImageView("image/Notifications/error.png");
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        Notifications n = Notifications.create()
                .title("ERROR!")
                .darkStyle()
                .position(Pos.BOTTOM_LEFT)
                .graphic(imageView);

        try {
            Integer.parseInt(txtItemCode.getText());
        } catch (NumberFormatException e) {
            n.text("Item code is invalided!");
            n.show();
            return;
        }


        Item item = new Item(
                Integer.parseInt(txtItemCode.getText()),
                Integer.parseInt(cmbxCode2.getValue().toString()),
                txtItemName.getText(),
                txtPrintName.getText(),
                txtBarCode.getText(),
                txtSupplier.getText(),
                txtStock.getText().equals("") ? 0 : Double.parseDouble(txtStock.getText()),
                !rdbNo.isSelected(),
                txtPriceOfBuying.getText().equals("") ? 0 : Double.parseDouble(txtPriceOfBuying.getText()),
                txtMarkPrice.getText().equals("") ? 0 : Double.parseDouble(txtMarkPrice.getText()),
                txtRetailRatio.getText().equals("") ? 0 : Double.parseDouble(txtRetailRatio.getText()),
                txtWholesalePriceRaito.getText().equals("") ? 0 : Double.parseDouble(txtWholesalePriceRaito.getText()),
                txtRetailPrice.getText().equals("") ? 0: Double.parseDouble(txtRetailPrice.getText()),
                txtWholesalePrice.getText().equals("") ?0: Double.parseDouble(txtWholesalePrice.getText())
        );

        if (!(noSpaseLength(txtItemName) > 0)) {
            n.text("Item name can't empty");
            n.show();
            return;
        }

        if (!(noSpaseLength(txtPrintName) > 0)) {
            n.text("Print name can't empty");
            n.show();
            return;
        }

        if (item.getRetailPriceRatio() > 100 || item.getRetailPriceRatio() < 0 || item.getWholeSalePriceRatio() > 100 || item.getWholeSalePriceRatio() < 0) {
            n.text("Ratio between 0 - 100 only");
            n.show();
            return;
        }

        if (item.getRetailPrice() < 0 || item.getWholeSalePrice() < 0 || item.getPriceOfBuying() < 0 || item.getMarkPrice() < 0) {
            n.text("Prices cannot be less than zero");
            n.show();
            return;
        }

        if (!item.isDecimal()) {
            if ((int) item.getStock() != item.getStock()) {
                n.text("Plese set DECIML --> YES\n\tOR\nStock set round Number");
                n.show();
                return;
            }
        }

        try {
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Save?", ButtonType.YES, ButtonType.NO).showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {
                ItemTableQuery.inputItem(item);
            } else {
                return;
            }

        } catch (SQLException | ClassNotFoundException e1) {
            n.text("Code- " + txtItemCode.getText() + " and Code2- " + cmbxCode2.getValue() + " already exist!");
            n.show();
            return;
        }

        ImageView imageView1 = new ImageView("image/Notifications/tick.png");
        imageView1.setFitWidth(50);
        imageView1.setFitHeight(50);
        Notifications n1 = Notifications.create()
                .darkStyle()
                .title("INFORMATION")
                .text("Save successful...")
                .position(Pos.BOTTOM_RIGHT)
                .graphic(imageView1);
        n1.show();
        clear();
//        AutoBackUp.getAutoBackup();
        ItemTableQuery.setUpper();
        if(chcbAdvanceSearch.isSelected()){
            stringAutoCompletionBinding.dispose();
                OnAdvanceSearch();
        }
    }

    //-------------------update------------------------------
    private void update() throws SQLException, ClassNotFoundException {
        ImageView imageView = new ImageView("image/Notifications/error.png");
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        Notifications n = Notifications.create()
                .title("ERROR!")
                .darkStyle()
                .position(Pos.BOTTOM_LEFT)
                .graphic(imageView);

        try {
            Integer.parseInt(txtItemCode.getText());
        } catch (NumberFormatException e) {
            n.text("Item code is invalided!");
            n.show();
            return;
        }


        Item item = new Item(
                Integer.parseInt(txtItemCode.getText()),
                Integer.parseInt(cmbxCode2.getValue().toString()),
                txtItemName.getText(),
                txtPrintName.getText(),
                txtBarCode.getText(),
                txtSupplier.getText(),
                txtStock.getText().equals("") ? 0 : Double.parseDouble(txtStock.getText()),
                !rdbNo.isSelected(),
                txtPriceOfBuying.getText().equals("") ? 0 : Double.parseDouble(txtPriceOfBuying.getText()),
                txtMarkPrice.getText().equals("") ? 0 : Double.parseDouble(txtMarkPrice.getText()),
                txtRetailRatio.getText().equals("") ? 0 : Double.parseDouble(txtRetailRatio.getText()),
                txtWholesalePriceRaito.getText().equals("") ? 0 : Double.parseDouble(txtWholesalePriceRaito.getText()),
                txtRetailPrice.getText().equals("") ? 0: Double.parseDouble(txtRetailPrice.getText()),
                txtWholesalePrice.getText().equals("") ?0: Double.parseDouble(txtWholesalePrice.getText())
        );

        if (!(noSpaseLength(txtItemName) > 0)) {
            n.text("Item name can't empty");
            n.show();
            return;
        }

        if (!(noSpaseLength(txtPrintName) > 0)) {
            n.text("Print name can't empty");
            n.show();
            return;
        }

        if (item.getRetailPriceRatio() > 100 || item.getRetailPriceRatio() < 0 || item.getWholeSalePriceRatio() > 100 || item.getWholeSalePriceRatio() < 0) {
            n.text("Ratio between 0 - 100 only");
            n.show();
            return;
        }

        if (item.getRetailPrice() < 0 || item.getWholeSalePrice() < 0 || item.getPriceOfBuying() < 0 || item.getMarkPrice() < 0) {
            n.text("Prices cannot be less than zero");
            n.show();
            return;
        }

        if (!item.isDecimal()) {
            if ((int) item.getStock() != item.getStock()) {
                n.text("Plese set DECIML --> YES\n\tOR\nStock set round Number");
                n.show();
                return;
            }
        }


        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Update?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {
            if (ItemTableQuery.updateItem(item)) {
                ImageView imageView1 = new ImageView("image/Notifications/tick.png");
                imageView1.setFitWidth(50);
                imageView1.setFitHeight(50);
                Notifications n1 = Notifications.create()
                        .darkStyle()
                        .title("INFORMATION")
                        .text("Update successful...")
                        .position(Pos.BOTTOM_RIGHT)
                        .graphic(imageView1);
                n1.show();
                clear();
                ItemTableQuery.setUpper();
//                AutoBackUp.getAutoBackup();
            }
        }

        if(chcbAdvanceSearch.isSelected()){
            stringAutoCompletionBinding.dispose();
            OnAdvanceSearch();
        }
    }

    private void clear() {
        txtItemCode.clear();
        txtItemName.clear();
        txtPrintName.clear();
        txtBarCode.clear();
        txtSupplier.clear();
        txtStock.clear();
        txtPriceOfBuying.clear();
        txtMarkPrice.clear();
        txtRetailPrice.clear();
        txtRetailRatio.clear();
        txtWholesalePrice.clear();
        txtWholesalePriceRaito.clear();

        chcbRetailPriceRatioEnable.setSelected(false);
        chcbWholesalePriceRatioEnable.setSelected(false);
        cmbxCode2.getSelectionModel().select(0);
        rdbNo.setSelected(true);
        txtWholesalePriceRaito.setDisable(true);
        txtRetailRatio.setDisable(true);

        txtItemCode.setDisable(false);
        btnAutoGeanarate.setDisable(false);
        btnDelete.setDisable(true);

        lblPlusQty.setVisible(false);
        txtPlusQty.clear();
        txtPlusQty.setVisible(false);

//        chcbAdvanceSearch.setSelected(false);
        try {
            if (!chcbAdvanceSearch.isSelected()){
                stringAutoCompletionBinding.dispose();
        }
        } catch (NullPointerException e) {}

        txtRetailPrice.setDisable(false);
        txtWholesalePrice.setDisable(false);
        txtBarCode.requestFocus();
        txtItemName.textProperty().unbind();
        btnSave.setText("Save");

        supplierSuggestions();
    }

    public void findOnAction(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException, InterruptedException {
        if (txtBarCode.getText().isEmpty()) {
            barcodeEmptyMessage();
            return;
        }
        find();
    }

    private void barcodeEmptyMessage() {
        ImageView imageView = new ImageView("image/Notifications/error.png");
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        Notifications n = Notifications.create()
                .title("ERROR!")
                .darkStyle()
                .position(Pos.BOTTOM_LEFT)
                .text("Barcode is Empty...")
                .graphic(imageView);
        n.show();
    }

    //----------------bar code-------------------
    public void barCodeKeyPress(KeyEvent keyEvent) throws SQLException, ClassNotFoundException, IOException, InterruptedException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            if (txtBarCode.getText().isEmpty()) {
                barcodeEmptyMessage();
                return;
            }
            int itemCount = ItemTableQuery.thisBacodeItemCount(txtBarCode.getText());
            ResultSet resultSet = ItemTableQuery.getThisBacodeitemsResalSet(txtBarCode.getText());
            if (itemCount > 1) {
                load(resultSet);
            } else if (itemCount == 1) {
                fill(resultSet);
                txtItemCode.setDisable(true);
                btnAutoGeanarate.setDisable(true);
                btnDelete.setDisable(false);
                btnSave.setText("Update");
                enterKey(keyEvent, txtPriceOfBuying);
            } else if (itemCount == 0) {
                enterKey(keyEvent, txtItemCode);
            }

        }
    }

    //------------------find--------------------
    private void find() throws SQLException, ClassNotFoundException, IOException, InterruptedException {
        int count = ItemTableQuery.thisBacodeItemCount(txtBarCode.getText());
        ResultSet resultSet = ItemTableQuery.getThisBacodeitemsResalSet(txtBarCode.getText());
        if (count > 1) {
            load(resultSet);
        } else if (count == 1) {
            fill(resultSet);
            txtItemCode.setDisable(true);
            btnAutoGeanarate.setDisable(true);
            btnDelete.setDisable(false);
            txtPriceOfBuying.requestFocus();
            btnSave.setText("Update");

            lblPlusQty.setVisible(true);
            txtPlusQty.setVisible(true);
        } else if (count == 0) {
            txtItemCode.requestFocus();
        }
    }

    //--------------------Fill------------------------
    private boolean isDecimal;

    private void fill(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            txtItemCode.setText(resultSet.getString(1));
            txtItemName.setText(resultSet.getString(3));
            txtPrintName.setText(resultSet.getString(4));
            txtBarCode.setText(resultSet.getString(5));
            txtSupplier.setText(resultSet.getString(6));
            txtStock.setText(resultSet.getString(7));
            txtPriceOfBuying.setText(resultSet.getString(9));
            txtMarkPrice.setText(resultSet.getString(10));
            txtRetailPrice.setText(resultSet.getString(13));
            txtRetailRatio.setText(resultSet.getString(11));
            txtWholesalePrice.setText(resultSet.getString(14));
            txtWholesalePriceRaito.setText(resultSet.getString(12));

            //chcbAdvanceSearch.setSelected(false);
            chcbRetailPriceRatioEnable.setSelected(resultSet.getInt(11) > 0);
            chcbWholesalePriceRatioEnable.setSelected(resultSet.getInt(12) > 0);
            cmbxCode2.getSelectionModel().select(resultSet.getInt(2));
            rdbYes.setSelected(resultSet.getBoolean(8));
            txtWholesalePriceRaito.setDisable(resultSet.getInt(12) <= 0);
            txtRetailRatio.setDisable(resultSet.getInt(11) <= 0);
            txtRetailPrice.setDisable(!txtRetailRatio.isDisable());
            txtWholesalePrice.setDisable(!txtWholesalePriceRaito.isDisable());

            isDecimal = resultSet.getBoolean(8);
            oldStock = resultSet.getDouble(7);
        }
    }

    //----------------------------------------------
    public void itemCodeEnterKeyPress(KeyEvent keyEvent) throws InterruptedException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            try {
                int code = Integer.parseInt(txtItemCode.getText());
                int count = ItemTableQuery.thisCodeItemCount(code);

                ResultSet resultSet = ItemTableQuery.getThisCodeItemsResalSet(code);
                if (count == 1) {
                    fill(resultSet);
                    txtItemCode.setDisable(true);
                    btnAutoGeanarate.setDisable(true);
                    btnDelete.setDisable(false);
                    btnSave.setText("Update");
                    txtPriceOfBuying.requestFocus();

                    lblPlusQty.setVisible(true);
                    txtPlusQty.setVisible(true);
                } else if (count > 1) {
                    load(resultSet);
                } else {
                    txtItemName.requestFocus();
                }


            } catch (NumberFormatException e) {
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //  enterKey(keyEvent, txtItemName);
        }
    }

    public void itemNameKeyPress(KeyEvent keyEvent) {
        enterKey(keyEvent, txtPrintName);
    }

    public void printNameKeyPress(KeyEvent keyEvent) {
        enterKey(keyEvent, txtSupplier);
    }

    public void supplierKeyPress(KeyEvent keyEvent) {
        enterKey(keyEvent, txtStock);
    }

    public void stockKeyPress(KeyEvent keyEvent) {
        enterKey(keyEvent, txtPriceOfBuying);
    }

    public void priceOfBuyingKeyPress(KeyEvent keyEvent) {
        enterKey(keyEvent, txtMarkPrice);
    }

    public void markPriceKeyPress(KeyEvent keyEvent) {
        if (txtRetailPrice.isDisable()) {
            enterKey(keyEvent, txtRetailRatio);
        } else {
            enterKey(keyEvent, txtRetailPrice);
        }
    }

    public void retailKeyPress(KeyEvent keyEvent) {
        if (txtWholesalePrice.isDisable()) {
            enterKey(keyEvent, txtWholesalePriceRaito);
        } else {
            enterKey(keyEvent, txtWholesalePrice);
        }
    }

    public void wholesaleKeyPress(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            if (btnSave.getText().equals("Save")) {
                save();
            } else {
                update();
            }
        }
    }

    public void retailRatioKeyPress(KeyEvent keyEvent) {
        if (txtWholesalePrice.isDisable()) {
            enterKey(keyEvent, txtWholesalePriceRaito);
        } else {
            enterKey(keyEvent, txtWholesalePrice);
        }
    }

    public void wholesaleRatioKeyPress(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            if (btnSave.getText().equals("Save")) {
                save();
            } else {
                update();
            }
        }
    }

    private void enterKey(KeyEvent keyEvent, TextField t) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            t.requestFocus();
        }
    }

    public void load(ResultSet resultSet) throws IOException, SQLException, ClassNotFoundException {
        btnAutoGeanarate.setDisable(true);
        txtItemCode.setDisable(true);
        btnDelete.setDisable(false);

        btnSave.setText("Update");
        lblPlusQty.setVisible(true);
        txtPlusQty.setVisible(true);

        Stage stage = new Stage();
        ManyItemTopUpWindowFormContoller.resultSet = resultSet;
        stage.setScene(new Scene(FXMLLoader.load(LoginPageFormContoller.class.getResource("../view/mainTask/ManyItemTopUpWindowForm.fxml"))));
        stage.setTitle("SELECT ITEM");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();


        ManyItemTopUp select = (ManyItemTopUp) tblItem.getSelectionModel().getSelectedItem();
        ResultSet resultSet1 = CrudUtil.excecute("SELECT * FROM item WHERE code=? AND code2=?",
                select.getCode(),
                select.getCode2()
        );
        fill(resultSet1);

        tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            ManyItemTopUp select2 = (ManyItemTopUp) tblItem.getSelectionModel().getSelectedItem();
            ResultSet resultSet2 = null;
            try {
                resultSet2 = CrudUtil.excecute("SELECT * FROM item WHERE code=? AND code2=?",
                        select2.getCode(),
                        select2.getCode2()
                );
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

    public void isOnAdvanceSearchOnAction(ActionEvent actionEvent) {
        OnAdvanceSearch();
    }

    private void OnAdvanceSearch() {
        HashSet<String> list = new HashSet<>();
        if (chcbAdvanceSearch.isSelected()) {
            list.clear();
            try {
                list = ItemTableQuery.getCodesAndNamesInItems();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            stringAutoCompletionBinding = TextFields.bindAutoCompletion(txtItemCode, SuggestionProvider.create(list));

            stringAutoCompletionBinding.setVisibleRowCount(20);
            stringAutoCompletionBinding.setMinWidth(600);
        } else {
            if (!txtItemCode.isDisable()) {
                txtItemCode.clear();
            }
            stringAutoCompletionBinding.dispose();
        }
    }

    public void btnAutoGenarateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ArrayList<Integer> list = ItemTableQuery.getAllCodesInHaveDataBase();
        for (int i = 100; i < Integer.MAX_VALUE; i++) {
            if (!list.contains(i)) {
                txtItemCode.setText(String.valueOf(i));
                return;
            }
        }
    }

    private int noSpaseLength(TextField f) {
        int count = 0;
        for (int i = 0; i < f.getText().length(); i++) {
            if (f.getText().charAt(i) != ' ') count++;
        }
        return count;
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

    public void stockKeyType(KeyEvent keyEvent) {
        acseptOnlyNumbers(keyEvent, txtStock);
    }


    public void buingKeyType(KeyEvent keyEvent) {
        acseptOnlyNumbers(keyEvent, txtPriceOfBuying);
    }

    public void markPriceKeyType(KeyEvent keyEvent) {
        acseptOnlyNumbers(keyEvent, txtMarkPrice);
    }

    public void retilePriceKeyType(KeyEvent keyEvent) {
        acseptOnlyNumbers(keyEvent, txtRetailPrice);
    }

    public void eholeSalePriceKeyType(KeyEvent keyEvent) {
        acseptOnlyNumbers(keyEvent, txtWholesalePrice);
    }

    public void retailratioKeyType(KeyEvent keyEvent) {
        acseptOnlyNumbers(keyEvent, txtRetailRatio);
    }

    public void whosaleRatioKeyType(KeyEvent keyEvent) {
        acseptOnlyNumbers(keyEvent, txtWholesalePriceRaito);
    }

    public void barcodeClick(MouseEvent mouseEvent) {
        clear();
    }


    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        delete();
    }

    public void txtPrintNameOnAction(ActionEvent actionEvent) {
        if (txtItemName.getText().isEmpty()) {
            txtItemName.textProperty().bind(txtPrintName.textProperty());
        }
    }

    public void itemnitemNameMouseClick(MouseEvent mouseEvent) {
        txtItemName.textProperty().unbind();
    }

    public void delete() throws SQLException, ClassNotFoundException {
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Delete?", ButtonType.YES, ButtonType.NO).showAndWait();

        if (buttonType.get().equals(ButtonType.YES)) {
            if (ItemTableQuery.deleteItem(txtItemCode.getText(), cmbxCode2.getValue())) {
                ImageView imageView1 = new ImageView("image/Notifications/tick.png");
                imageView1.setFitWidth(50);
                imageView1.setFitHeight(50);
                Notifications n1 = Notifications.create()
                        .darkStyle()
                        .title("INFORMATION")
                        .text("Delete successful...")
                        .position(Pos.BOTTOM_RIGHT)
                        .graphic(imageView1);
                n1.show();
                clear();
//                AutoBackUp.getAutoBackup();
            } else {
                ImageView imageView = new ImageView("image/Notifications/error.png");
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);

                Notifications n = Notifications.create()
                        .title("ERROR!")
                        .darkStyle()
                        .position(Pos.BOTTOM_LEFT)
                        .text("Item not found.\nChack item codes")
                        .graphic(imageView);
                n.show();
            }
        }

    }

    public void ItemCodeKeyPress(KeyEvent keyEvent) {
        if (!chcbAdvanceSearch.isSelected()) {
            if (!(Character.isDigit(keyEvent.getCharacter().charAt(0)) || keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.BACK_SPACE))) {
                keyEvent.consume();
            }
        }
    }

    private void supplierSuggestions() {
        HashSet<String> list;
        try {

            list = ItemTableQuery.getSuppliers();
            AutoCompletionBinding<String> binding = TextFields.bindAutoCompletion(txtSupplier, SuggestionProvider.create(list));
            binding.setVisibleRowCount(8);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void txtPlusQtyKeyType(KeyEvent event) {
        if (isDecimal) {
            if (!(Character.isDigit(event.getCharacter().charAt(0)) || event.getCharacter().charAt(0) == '-' || event.getCharacter().charAt(0) == '.' || event.getCode().equals(KeyCode.ENTER) || event.getCode().equals(KeyCode.BACK_SPACE))) {
                event.consume();
            }
            if (event.getCharacter().charAt(0) == '.') {
                boolean have = false;
                for (int i = 0; i < txtPlusQty.getText().length(); i++) {
                    if (txtPlusQty.getText().charAt(i) == '.') {
                        have = true;
                        break;
                    }
                }
                if (have) {
                    event.consume();
                }
            }
        }else{
            if (!(Character.isDigit(event.getCharacter().charAt(0)) || event.getCharacter().charAt(0) == '-' || event.getCode().equals(KeyCode.ENTER) || event.getCode().equals(KeyCode.BACK_SPACE))) {
                event.consume();
            }
        }
    }
}
package contoller.mainTask;

import Query.ItemTableQuery;
import com.jfoenix.controls.JFXButton;
import contoller.LoginPageFormContoller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import module.Item;
import module.ManyItemTopUp;
import org.controlsfx.control.Notifications;
import util.CrudUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockAdjusmentController {
    public static TableView tblItem;
    public TextField txtNewQty;
    public TextField txtBarcode;
    public JFXButton btnSave;
    public Label lblName;
    public Label lblPrintname;
    public Label lblBuyingPrice;
    public Label lblretailPrice;
    public Label lblWholesalePrice;
    public Label lblOldQty;
    public Label lblCode1;
    public Label lblCode2;
    public AnchorPane ankerPane;
    private boolean isDecimal;
    private double qty;

    public void initialize() {
        requestFocusOrDieTrying(txtBarcode);
        txtNewQty.setDisable(true);
        btnSave.setDisable(true);

        barcodeEnterDetect();
        barcodeClickDetect();
        qtyUpdate();

        txtBarcode.getParent().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.DELETE) {
                    clear();
                }
            }
        });
    }


    private void successMessage(String text) {
        ImageView imageView1 = new ImageView("image/Notifications/tick.png");
        imageView1.setFitWidth(50);
        imageView1.setFitHeight(50);
        Notifications n1 = Notifications.create()
                .darkStyle()
                .title("SUCCESS")
                .position(Pos.BOTTOM_RIGHT)
                .text(text)
                .graphic(imageView1);
        n1.show();
    }

    private void qtyUpdate() {
        txtNewQty.setOnKeyReleased(event -> {
            try {
            if (txtNewQty.getText().isEmpty() || Double.parseDouble(txtNewQty.getText()) == 0) {
                lblOldQty.setText(String.valueOf(qty));
                btnSave.setDisable(true);
            } else {
                lblOldQty.setText(String.valueOf(qty + Double.parseDouble(txtNewQty.getText())));
                btnSave.setDisable(false);
            }
                if(Double.parseDouble(lblOldQty.getText())<0){
                    btnSave.setDisable(true);
                }
                
            }catch (NumberFormatException e){
                btnSave.setDisable(true);
                return;
            }
        });
    }

    private void barcodeClickDetect() {
        txtBarcode.setOnMouseClicked(event -> {
            clear();
        });
    }

    private void clear() {
        txtBarcode.clear();
        txtNewQty.clear();

        lblBuyingPrice.setText("");
        lblretailPrice.setText("");
        lblWholesalePrice.setText("");
        lblName.setText("");
        lblPrintname.setText("");
        lblOldQty.setText("");
        lblCode1.setText("");
        lblCode2.setText("");

        btnSave.setDisable(true);
        txtNewQty.setDisable(true);
        requestFocusOrDieTrying(txtBarcode);
    }

    private void barcodeEnterDetect() {
        txtBarcode.textProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != newValue) {
                if (txtBarcode.getText().length() >= 13) {
                    try {
                        ArrayList<Item> itemDeatils = ItemTableQuery.getItemDeatils(txtBarcode.getText());
                        if (itemDeatils.size() == 1) {
                            fill(itemDeatils.get(0));
                            requestFocusOrDieTrying(txtNewQty);
                            txtNewQty.setDisable(false);
                        } else {
                            boolean load = load(itemDeatils);
                            if (!load) {
                                txtNewQty.setDisable(true);
                            } else {
                                requestFocusOrDieTrying(txtNewQty);
                                txtNewQty.setDisable(false);
                            }
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
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

    private void errorMessage(String text) {
        ImageView imageView1 = new ImageView("image/Notifications/error.png");
        imageView1.setFitWidth(50);
        imageView1.setFitHeight(50);
        Notifications n1 = Notifications.create()
                .darkStyle()
                .title("ERROR!")
                .position(Pos.BOTTOM_LEFT)
                .text(text)
                .graphic(imageView1);
        n1.show();
    }

    public boolean load(ArrayList<Item> items) throws IOException, SQLException, ClassNotFoundException {
        StockAdjusmentTopUpWindowContoller.ankerpane = ankerPane;

        Stage stage = new Stage();
        StockAdjusmentTopUpWindowContoller.itemList = items;
        stage.setScene(new Scene(FXMLLoader.load(LoginPageFormContoller.class.getResource("../view/mainTask/StockAdjusmentTopUpWindowForm.fxml"))));
        stage.setTitle("SELECT ITEM");
        stage.initStyle(StageStyle.UNDECORATED);


        ManyItemTopUp select = (ManyItemTopUp) tblItem.getSelectionModel().getSelectedItem();
        try {
            ResultSet resultSet = CrudUtil.excecute("SELECT * FROM item WHERE code=? AND code2=?",
                    select.getCode(),
                    select.getCode2()
            );
            if (resultSet.next()) {
                fill(new Item(
                        resultSet.getDouble(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getDouble(7),
                        resultSet.getBoolean(8),
                        resultSet.getDouble(9),
                        resultSet.getDouble(10),
                        resultSet.getDouble(11),
                        resultSet.getDouble(12),
                        resultSet.getDouble(13),
                        resultSet.getDouble(14)
                ));
            }
        } catch (NullPointerException e) {
            stage.close();
            errorMessage("This bar code does not exist in the system.");
            return false;
        }

        tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            ManyItemTopUp select2 = (ManyItemTopUp) tblItem.getSelectionModel().getSelectedItem();
            ResultSet resultSet1 = null;

            try {
                resultSet1 = CrudUtil.excecute("SELECT * FROM item WHERE code=? AND code2=?",
                        select2.getCode(),
                        select2.getCode2()
                );
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {
                if (resultSet1.next()) {
                    fill(new Item(
                            resultSet1.getDouble(1),
                            resultSet1.getInt(2),
                            resultSet1.getString(3),
                            resultSet1.getString(4),
                            resultSet1.getString(5),
                            resultSet1.getString(6),
                            resultSet1.getDouble(7),
                            resultSet1.getBoolean(8),
                            resultSet1.getDouble(9),
                            resultSet1.getDouble(10),
                            resultSet1.getDouble(11),
                            resultSet1.getDouble(12),
                            resultSet1.getDouble(13),
                            resultSet1.getDouble(14)
                    ));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        requestFocusOrDieTrying(txtNewQty);
        stage.show();
        return true;
    }

    private void fill(Item item) {
        isDecimal = item.isDecimal();
        qty = item.getStock();

        lblOldQty.setText(String.valueOf(item.getStock()));
        lblCode1.setText(String.valueOf((int) item.getCode()));
        lblCode2.setText(String.valueOf(item.getCode2()));
        lblName.setText(item.getName());
        lblPrintname.setText(item.getPrintName());
        lblBuyingPrice.setText(String.valueOf(item.getPriceOfBuying()));
        lblretailPrice.setText(String.valueOf(item.getRetailPrice()));
        lblWholesalePrice.setText(String.valueOf(item.getWholeSalePrice()));
    }

    public void txtQtyKeyType(KeyEvent event) {
        if (isDecimal) {
            if (!(Character.isDigit(event.getCharacter().charAt(0)) || event.getCharacter().charAt(0) == '-' || event.getCharacter().charAt(0) == '.' || event.getCode().equals(KeyCode.ENTER) || event.getCode().equals(KeyCode.BACK_SPACE))) {
                event.consume();
            }
            if (event.getCharacter().charAt(0) == '.') {
                boolean have = false;
                for (int i = 0; i < txtNewQty.getText().length(); i++) {
                    if (txtNewQty.getText().charAt(i) == '.') {
                        have = true;
                        break;
                    }
                }
                if (have) {
                    event.consume();
                }
            }
        } else {
            if (!(Character.isDigit(event.getCharacter().charAt(0)) || event.getCharacter().charAt(0) == '-' || event.getCode().equals(KeyCode.ENTER) || event.getCode().equals(KeyCode.BACK_SPACE))) {
                event.consume();
            }
        }
    }

    public void txtBarcodeKeyType(KeyEvent event) {
        if (!(Character.isDigit(event.getCharacter().charAt(0)) || event.getCode().equals(KeyCode.ENTER) || event.getCode().equals(KeyCode.BACK_SPACE))) {
            event.consume();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        try {
            boolean isSave = ItemTableQuery.updateQty(-(Double.parseDouble(txtNewQty.getText())), Integer.parseInt(lblCode1.getText()), Integer.parseInt(lblCode2.getText()));
            if (isSave) {
                successMessage("Qty updated.");
                clear();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}

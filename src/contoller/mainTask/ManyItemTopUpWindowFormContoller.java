package contoller.mainTask;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import module.ManyItemTopUp;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManyItemTopUpWindowFormContoller {

    public static ResultSet resultSet;
    public static AnchorPane homePageAnkerPane;
    public TableColumn clmCode2;
    public TableColumn clmCode;
    public TableView tblItem;
    public TableColumn clmName;
    public TableColumn clmPrintName;
    public Label lblCode;
    public Label lblCode2;
    public Label lblBarCode;
    public Label lblName;
    public Label lblPrintName;
    public Label lblRetilPrice;
    public Label lblWholesalePrice;
    public Label lblretilRato;
    public Label lblWholesaleRatio;
    public Label lblMarkPrice;

    public void initialize() throws SQLException {
        homePageAnkerPane.setDisable(true);
        clmCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        clmCode2.setCellValueFactory(new PropertyValueFactory<>("code2"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmPrintName.setCellValueFactory(new PropertyValueFactory<>("printName"));
        tblItem.setStyle("-fx-font-size: 14pt;");

        tableDataLoad();
        lableDataLode();
        AddDeleteItemFormContoller.tblItem = tblItem;
        SaleFormContoller.tblItem = tblItem;
    }

    private void lableDataLode() {
        tblItem.getSelectionModel().select(0);
        ManyItemTopUp select = (ManyItemTopUp) tblItem.getSelectionModel().getSelectedItem();
        lablefill(select.getCode(), select.getCode2());

        tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != oldValue) {
                ManyItemTopUp value = (ManyItemTopUp) newValue;
                lablefill(value.getCode(), value.getCode2());
            }
        });
    }

    private void lablefill(int code1, int code2) {
        try {
            ResultSet resultSet = CrudUtil.excecute("SELECT * FROM item WHERE code=? AND code2=?", code1, code2);
            if (resultSet.next()) {
                lblCode.setText(resultSet.getString(1));
                lblCode2.setText(resultSet.getString(2));
                lblBarCode.setText(resultSet.getString(5));
                lblName.setText(resultSet.getString(3));
                lblPrintName.setText(resultSet.getString(4));
                lblRetilPrice.setText(resultSet.getString(13));
                lblWholesalePrice.setText(resultSet.getString(14));
                lblretilRato.setText(resultSet.getString(11));
                lblWholesaleRatio.setText(resultSet.getString(12));
                lblMarkPrice.setText(resultSet.getString(10));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void tableDataLoad() throws SQLException {
        ObservableList<ManyItemTopUp> observableList = FXCollections.observableArrayList();

        while (resultSet.next()) {
            observableList.add(new ManyItemTopUp(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }

        tblItem.setItems(observableList);
    }

    public void tableKeyPress(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            Stage stage = (Stage) tblItem.getScene().getWindow();
            homePageAnkerPane.setDisable(false);
            stage.close();
        }
    }
}
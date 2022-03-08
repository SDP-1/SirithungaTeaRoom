package contoller.mainTask;

import Query.ItemTableQuery;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import module.StockItem;
import module.StockSelectItem;
import org.controlsfx.control.Notifications;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class StockManageFormContoller {

    public TextField txtSearch;
    public TableView tblItems;
    public TableColumn clmCode1;
    public TableColumn clmcode2;
    public TableColumn clmBarcode;
    public TableColumn clmName;
    public TableColumn clmStock;
    public TableColumn clmMarkPrice;
    public TableColumn clmSuplier;
    public TableColumn clmTick;
    public TableView tblSelctItems;
    public TableColumn clmNoT2;
    public TableColumn clmNameT2;
    public TableColumn clmStockT2;
    public TableColumn clmTickT2;
    public Button btnSelectAll;
    private ArrayList<StockItem> stockItems = null;
    private final ArrayList<StockSelectItem> selectItems = new ArrayList<>();

    public void initialize() {
        setTableCloumns();
        loadTableData();
        requestFocusOrDieTrying(txtSearch);
    }

    private void requestFocusOrDieTrying(Node node) {
        Platform.runLater(() -> {
            if (!node.isFocused()) {
                node.requestFocus();
                requestFocusOrDieTrying(node);
            }
        });
    }

    private void setTableCloumns() {
        clmCode1.setCellValueFactory(new PropertyValueFactory<>("code1"));
        clmcode2.setCellValueFactory(new PropertyValueFactory<>("code2"));
        clmBarcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        clmMarkPrice.setCellValueFactory(new PropertyValueFactory<>("markPrice"));
        clmSuplier.setCellValueFactory(new PropertyValueFactory<>("suplier"));
        clmTick.setCellValueFactory(new PropertyValueFactory<>("checkBox"));

        clmStock.setStyle("-fx-alignment: CENTER-RIGHT;");
        clmMarkPrice.setStyle("-fx-alignment: CENTER-RIGHT;");
        clmTick.setStyle("-fx-alignment: CENTER;");
        tblItems.setStyle("-fx-font-size: 15pt;");

        clmNoT2.setCellValueFactory(new PropertyValueFactory<>("no"));
        clmNameT2.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmStockT2.setCellValueFactory(new PropertyValueFactory<>("stock"));
        clmTickT2.setCellValueFactory(new PropertyValueFactory<>("checkBox"));

        clmStockT2.setStyle("-fx-alignment: CENTER-RIGHT;");
        clmTickT2.setStyle("-fx-alignment: CENTER;");
        tblSelctItems.setStyle("-fx-font-size: 15pt;");
    }

    public void btnRefashOnAction(ActionEvent actionEvent) {
        refresh();
    }

    private void refresh() {
        if (selectItems.size() >= 200) {
            ImageView imageView1 = new ImageView("image/Notifications/error.png");
            imageView1.setFitWidth(50);
            imageView1.setFitHeight(50);
            Notifications n1 = Notifications.create()
                    .darkStyle()
                    .title("ERROR!")
                    .text("200 limit are full.")
                    .position(Pos.BOTTOM_LEFT)
                    .graphic(imageView1);
            n1.show();
            return;
        }

        selectItems.clear();
        int no = 1;
        for (int i = 0; i < stockItems.size(); i++) {
            if (stockItems.get(i).isSelect()) {

                selectItems.add(new StockSelectItem(
                        no++,
                        stockItems.get(i).getCode1(),
                        stockItems.get(i).getCode2(),
                        stockItems.get(i).getName(),
                        stockItems.get(i).getStock(),
                        new CheckBox()
                ));
            }
        }
        ObservableList observableList = FXCollections.observableArrayList(selectItems);
        tblSelctItems.setItems(observableList);
    }

    public void btnSelectAllOnAction(ActionEvent actionEvent) {
        ImageView imageView1 = new ImageView("image/Notifications/error.png");
        imageView1.setFitWidth(50);
        imageView1.setFitHeight(50);
        Notifications n1 = Notifications.create()
                .darkStyle()
                .title("ERROR!")
                .position(Pos.BOTTOM_LEFT)
                .graphic(imageView1);

        ObservableList<StockItem> items = tblItems.getItems();

        if (items.size() > 200) {
            n1.text("limited 200 items");
            n1.show();
            return;
        }
        for (StockItem s : items) {
            s.setSelected(true);
        }
    }

    public void btnDeselectAllOnAction(ActionEvent actionEvent) {
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Deselect All?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {
            ObservableList<StockItem> items = tblItems.getItems();
            for (StockItem s : items) {
                s.setSelected(false);
            }
        }
    }

    private void loadTableData() {
        try {
            stockItems = ItemTableQuery.getStockData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ObservableList list = FXCollections.observableArrayList(stockItems);
        tblItems.setItems(list);

        //----------tbl search codes----!!!---------------------------

        FilteredList<StockItem> filteredList = new FilteredList<>(list, b -> true);
        txtSearch.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredList.setPredicate(StockItem -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowecase = newValue.toLowerCase();

                if (StockItem.getBarcode().toLowerCase().indexOf(lowecase) != -1) {
                    return true;
                } else if (StockItem.getName().toLowerCase().indexOf(lowecase) != -1) {
                    return true;
                } else return StockItem.getSuplier().toLowerCase().indexOf(lowecase) != -1;
            });
        }));

        SortedList<StockItem> sortedlist = new SortedList<>(filteredList);
        sortedlist.comparatorProperty().bind(tblItems.comparatorProperty());
        tblItems.setItems(sortedlist);

        //-------------------tbl search code END!----------------
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
        ArrayList<StockSelectItem> list = new ArrayList<>();
        for (StockSelectItem s : selectItems) {
            if (s.isSelect()) {
                list.add(s);
            }
        }
        for (StockItem s : stockItems) {
            if (s.isSelect()) {
                for (StockSelectItem s1 : list) {
                    if (s.getCode1() == s1.getCode1() && s.getCode2() == s1.getCode2()) {
                        s.setSelected(false);
                    }
                }
            }
        }
        refresh();
    }

    public void paneKeyPressOnAction(KeyEvent keyEvent) {
        txtSearch.requestFocus();
    }
}

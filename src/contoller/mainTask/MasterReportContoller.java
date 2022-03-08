package contoller.mainTask;

import Query.CustomerTableQuery;
import Query.ItemTableQuery;
import Query.OrderTableQuery;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MasterReportContoller {


    public Pane pane1;
    public Pane pane2;
    public Pane pane3;
    public Pane pane4;
    public Pane paneTotal1;
    public Pane paneTotal2;
    public Pane paneTotal3;
    public Pane paneDate;
    public Label lblTotalOrders;
    public Label lblTotalItems;
    public Label lblTotalCustomers;
    public JFXComboBox cmbxYear;
    public JFXComboBox cmbxMonth;
    public JFXComboBox cmbxdate;

    public void initialize() {
        setTextSizes();
        setShadows();
        setData();
    }

    private void setTextSizes() {
        cmbxYear.setStyle("-fx-font-size: 18");
        cmbxMonth.setStyle("-fx-font-size: 18");
        cmbxdate.setStyle("-fx-font-size: 18");
    }

    private void setShadows() {
        pane1.setEffect(new DropShadow(25, 10, 10, Color.GRAY));
        pane2.setEffect(new DropShadow(25, 10, 10, Color.GRAY));
        pane3.setEffect(new DropShadow(25, 10, 10, Color.GRAY));
        pane4.setEffect(new DropShadow(25, 10, 10, Color.GRAY));

        paneTotal1.setEffect(new DropShadow(25, 10, 10, Color.GRAY));
        paneTotal2.setEffect(new DropShadow(25, 10, 10, Color.GRAY));
        paneTotal3.setEffect(new DropShadow(25, 10, 10, Color.GRAY));


//        pane1.setEffect(new DropShadow(25, 10, 10, Color.GRAY));
//        pane2.setEffect(new DropShadow(10, 1, 2, Color.GRAY));
    }

    private void setData() {
        lblTotalOrders.setText(String.valueOf(OrderTableQuery.getTotalOrderCount()));
        lblTotalItems.setText(String.valueOf(ItemTableQuery.getTotalItemCount()));
        lblTotalCustomers.setText(String.valueOf(CustomerTableQuery.getTotalCustomerCount()));

        cmbxYear.setItems(OrderTableQuery.getSystemHaveYears());
        cmbxYear.getSelectionModel().select(0);

        ObservableList<String> selectedYearMonths = OrderTableQuery.getSelectedYearMonths(Integer.parseInt(cmbxYear.getSelectionModel().getSelectedItem().toString()));
        cmbxMonth.setItems(selectedYearMonths);
        cmbxMonth.getSelectionModel().select(selectedYearMonths.size() - 1);
        cmbxYear.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != newValue) {
                ObservableList<String> list = OrderTableQuery.getSelectedYearMonths(Integer.parseInt(cmbxYear.getSelectionModel().getSelectedItem().toString()));
                cmbxMonth.setItems(list);
                cmbxMonth.getSelectionModel().select(list.size() - 1);
            }
        });

        ObservableList<String> dates = OrderTableQuery.getOrdersAvbledates(cmbxMonth.getSelectionModel().getSelectedItem().toString());
        cmbxdate.setItems(dates);
        cmbxdate.getSelectionModel().select(dates.size() - 1);
        cmbxMonth.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != oldValue) {
                ObservableList<String> list = OrderTableQuery.getOrdersAvbledates(cmbxMonth.getSelectionModel().getSelectedItem().toString());
                cmbxdate.setItems(list);
                cmbxdate.getSelectionModel().select(list.size() - 1);
            }
        });

    }


}

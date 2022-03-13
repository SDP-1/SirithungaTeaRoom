package contoller.mainTask;

import Query.CustomerTableQuery;
import Query.ItemTableQuery;
import Query.OrderTableQuery;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

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
    public Label lblNoOfSales;
    public Label lblTitel;
    public Label lblTotalRevenue;
    public Label lblLastUpdateTime;
    public Label lblTotalCost;
    public Label lblTotalProfite;

    public void initialize() {
        setTextSizes();
        setShadows();
        setData();
        opentimeDataLoad();
    }

    private void opentimeDataLoad() {
        dataLoad();
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
            try {
                if (oldValue != newValue) {
                    ObservableList<String> list = OrderTableQuery.getSelectedYearMonths(Integer.parseInt(cmbxYear.getSelectionModel().getSelectedItem().toString()));
                    cmbxMonth.setItems(list);
                    cmbxMonth.getSelectionModel().select(list.size() - 1);
                }
            } catch (NullPointerException e) {
            }
        });

        ObservableList<String> dates = OrderTableQuery.getOrdersAvbledates(cmbxMonth.getSelectionModel().getSelectedItem().toString(), cmbxYear.getSelectionModel().getSelectedItem().toString());
        cmbxdate.setItems(dates);
        cmbxdate.getSelectionModel().select(dates.size() - 1);
        cmbxMonth.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (newValue != oldValue) {
                    ObservableList<String> list = OrderTableQuery.getOrdersAvbledates(cmbxMonth.getSelectionModel().getSelectedItem().toString(), cmbxYear.getSelectionModel().getSelectedItem().toString());
                    cmbxdate.setItems(list);
                    cmbxdate.getSelectionModel().select(list.size() - 1);
                }
            } catch (NullPointerException e) {
            }
        });

    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        dataLoad();
    }



    private void dataLoad() {
        String year = cmbxYear.getSelectionModel().getSelectedItem().toString();
        String month = null;
        String date = null;

        if (!cmbxMonth.getSelectionModel().getSelectedItem().toString().equals("")) {
            month = cmbxMonth.getSelectionModel().getSelectedItem().toString();
        }

        if (!cmbxdate.getSelectionModel().getSelectedItem().toString().equals("")) {
            date = cmbxdate.getSelectionModel().getSelectedItem().toString();
        }

//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd  hh.mm.ss aa");
        String format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss aa").format(Calendar.getInstance().getTime());
        lblLastUpdateTime.setText(format);

        titleUpdate(year, month, date);
        saleCountGenarate(year, month, date);
        revenueGenarate(year, month, date);
        costGenarate(year, month, date);
        profitGenarate();
    }

    private void titleUpdate(String year, String month, String date) {
        if(month != null && date != null){
            lblTitel.setText(year+"-"+month+"-"+date+"\tsales summary");
        }else if(month!=null && date ==null){
            lblTitel.setText(year+" "+cmbxMonth.getSelectionModel().getSelectedItem().toString()+"\tsales summary");
        }else {
            lblTitel.setText(year+"\tsales summary");
        }
    }

    private void saleCountGenarate(String year, String month, String date) {

        if (month != null && date != null) {
            int noOfSaleForDay = OrderTableQuery.getNoofSaleForDay(year, month, date);
            lblNoOfSales.setText(String.valueOf(noOfSaleForDay));
        }else if(month!=null && date ==null){
            int noOfSaleForMonth = OrderTableQuery.getNoofSaleForMonth(year, month);
            lblNoOfSales.setText(String.valueOf(noOfSaleForMonth));
        }else{
            int noOfSaleForYear = OrderTableQuery.getNoofSaleForYear(year);
            lblNoOfSales.setText(String.valueOf(noOfSaleForYear));
        }

    }

    private void revenueGenarate(String year, String month, String date) {
        DecimalFormat df = new DecimalFormat("#.00");

        if (month != null && date != null) {
            double revenue = OrderTableQuery.getRevenueForDay(year, month, date);
            lblTotalRevenue.setText(df.format(revenue));
        }else if(month!=null && date ==null){
            double revenue = OrderTableQuery.getRevenueForMonth(year, month);
            lblTotalRevenue.setText(df.format(revenue));
        }else{
            double revenue = OrderTableQuery.getRevenueForYear(year);
            lblTotalRevenue.setText(df.format(revenue));
        }

    }

    private void costGenarate(String year, String month, String date) {
        DecimalFormat df = new DecimalFormat("#0.00");

        if (month != null && date != null) {
            double cost = OrderTableQuery.getCostForDay(year, month,date);
            lblTotalCost.setText(df.format(cost));
        }else if(month!=null && date ==null){
            double cost = OrderTableQuery.getCostForMonth(year, month);
            lblTotalCost.setText(df.format(cost));
        }else{
            double cost = OrderTableQuery.getCostForYear(year);
            lblTotalCost.setText(df.format(cost));
        }

    }

    private void profitGenarate() {
            DecimalFormat df = new DecimalFormat("#0.00");
            double profit = Double.parseDouble(lblTotalRevenue.getText()) - Double.parseDouble(lblTotalCost.getText());
            lblTotalProfite.setText(df.format(profit));
    }

}

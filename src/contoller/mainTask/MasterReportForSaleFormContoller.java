package contoller.mainTask;

import Query.CustomerTableQuery;
import Query.ItemTableQuery;
import Query.OrderTableQuery;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import module.SalesQtyTM;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MasterReportForSaleFormContoller {
    public JFXComboBox cmbxYear;
    public JFXComboBox cmbxMonth;
    public JFXComboBox cmbxdate;
    public Label lblLastUpdateTime;
    public Label lblTitel;
    public TableView tblsales;
    public TableColumn clmCode1;
    public TableColumn clmCode2;
    public TableColumn clmSaveName;
    public TableColumn clmPrintName;
    public TableColumn clmSaleQty;

    public void initialize() {
        setTextSizes();
        setData();
        opentimeDataLoad();
        setTableData();
    }

    private void setTableData() {
       clmCode1.setCellValueFactory(new PropertyValueFactory<>("code1"));
        clmCode2.setCellValueFactory(new PropertyValueFactory<>("code2"));
        clmPrintName.setCellValueFactory(new PropertyValueFactory<>("printName"));
        clmSaleQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        clmCode1.setStyle("-fx-alignment: CENTER;");
        clmCode2.setStyle("-fx-alignment: CENTER;");
        clmSaleQty.setStyle("-fx-alignment: CENTER-RIGHT;");
        tblsales.setStyle("-fx-font-size: 15pt;");
    }

    private void opentimeDataLoad() {
        dataLoad();
    }

    private void setTextSizes() {
        cmbxYear.setStyle("-fx-font-size: 18");
        cmbxMonth.setStyle("-fx-font-size: 18");
        cmbxdate.setStyle("-fx-font-size: 18");
    }


    private void setData() {

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

        String format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss aa").format(Calendar.getInstance().getTime());
        lblLastUpdateTime.setText(format);

        titleUpdate(year, month, date);
        revenueGenarate(year, month, date);
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


    private void revenueGenarate(String year, String month, String date) {
        DecimalFormat df = new DecimalFormat("#.00");

        if (month != null && date != null) {
            ObservableList<SalesQtyTM> list= OrderTableQuery.saleFordate(year, month, date);
            tblsales.setItems(list);
        }else if(month!=null && date ==null){
            ObservableList<SalesQtyTM> list = OrderTableQuery.saleForMonth(year, month);
            tblsales.setItems(list);
        }else{
            ObservableList<SalesQtyTM> list = OrderTableQuery.saleForYear(year);
            tblsales.setItems(list);
        }

    }

}

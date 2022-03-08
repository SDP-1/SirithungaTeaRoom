package contoller.mainTask;

import Invoice.AfterPrintBill;
import Invoice.DebtPaymentConform;
import Query.DeleteReqBillTableQuery;
import Query.OrderDetailTableQuery;
import Query.OrderTableQuery;
import db.AutoBackUp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import module.OrderDeatalsTM;
import module.OrderDetails;
import module.OrdersTM;
import org.controlsfx.control.Notifications;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.SQLException;
import java.util.Optional;

public class SeeBillFormContoller {
    public static boolean isOwner;
    public TableColumn clmT2No;
    public TableColumn clmT2Code1;
    public TableColumn clmT2Code2;
    public TableColumn clmT2Description;
    public TableColumn clmT2Qty;
    public TableColumn clmT2NextAmount;
    public TableView tblOrdes;
    public TableColumn clmT1No;
    public TableColumn clmT1InvoiceNo;
    public TableColumn clmT1Date;
    public TableColumn clmT1Time;
    public TableColumn clmT1Customer;
    public ComboBox<String> cmbxSelectCatagary;
    public TextField txtSearch;
    public TableColumn clmT2SalePrice;
    public TableView tblOrderDeatils;
    public TableColumn clmT1IsLone;
    public Label lblInvoiceNo;
    public Label lblCustomerName;
    public Label lblSaleType;
    public Label lblDate;
    public Label lblTime;
    public Label lblPaymentStatement;
    public Label lblFullCost;
    public Label lblDescount;
    public Label lblCash;
    public Label lblBalance;
    public Label lblNoOfItems;
    public Button btnSetPaid;
    public CheckBox chbAdvanceSearch;
    public Button btnOrderDeleteButton;
    public TextField txtDeleteOrderInvoiceNumber;
    public Button btnPrint;
    public Button btnDeleteReqCancel;
//    ObservableList<OrdersTM> list;
    private int invoiceNo;

    protected static double cm_to_pp(double cm) {
        return toPPI(cm * 0.393600787);
    }

    protected static double toPPI(double inch) {
        return inch * 72d;
    }

    public void initialize() {
        setComboboxData();
        setTableDeatils();
        txtSearchTableDataLoad();
        tableCelectItemCathandDeatilsFill();
        if (isOwner) {
            txtDeleteOrderInvoiceNumber.setVisible(true);
            btnOrderDeleteButton.setVisible(true);
            btnOrderDeleteButton.setDisable(true);

            txtDeleteOrderInvoiceNumber.textProperty().addListener((observable, oldValue, newValue) -> {
                if (oldValue != newValue) {
                    setOptionButtonsDefault();
                }
            });
        }
        try {
            tblOrdes.setItems(OrderTableQuery.getLastTodayOrders());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void tableCelectItemCathandDeatilsFill() {
        tblOrdes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (newValue != oldValue) {
                    OrdersTM selectedItem = (OrdersTM) newValue;
                    fillOrderDetails(selectedItem.getInvoiceNo());
                    invoiceNo = selectedItem.getInvoiceNo();
                }
            } catch (NullPointerException e) {
            }
        });
    }

    private void fillOrderDetails(int invoiceNo) {
        try {
            tblOrderDeatils.setItems(OrderDetailTableQuery.getOrderDeatils(invoiceNo));

            OrderDetails detail = OrderTableQuery.getOrderDeatil(invoiceNo);

            lblInvoiceNo.setText(String.valueOf(detail.getInvoiceNo()));
            lblDate.setText(detail.getDate());
            lblTime.setText(detail.getTime());
            lblCustomerName.setText(detail.getCustomerName());
            lblPaymentStatement.setText(detail.isOnLoan() ? "Loan" : "Paid");
            lblSaleType.setText(detail.isRetail() ? "Retail" : "Wholesale");
            lblNoOfItems.setText(String.valueOf(detail.getNoOfItem()));
            lblCash.setText(String.valueOf(detail.getCash()));
            lblFullCost.setText(String.valueOf(detail.getFullCost()));
            lblBalance.setText(String.valueOf(detail.getBalance()));
            lblDescount.setText(String.valueOf(detail.getDiscount()));

            btnPrint.setVisible(true);

            if (detail.isOnLoan()) {
                btnSetPaid.setVisible(true);
                lblPaymentStatement.setTextFill(Color.web("#cc0707"));
            } else {
                btnSetPaid.setVisible(false);
                lblPaymentStatement.setTextFill(Color.web("#000000"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void txtSearchTableDataLoad() {
        try {
            tblOrdes.setItems(OrderTableQuery.getLastTodayOrders());
            cmbxSelectCatagary.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    if (newValue != oldValue) {
                        clear();
                        int x = (int) newValue;
                        ObservableList<OrdersTM> ob = FXCollections.observableArrayList();
                        tblOrdes.setItems(ob);
                        switch (x) {
                            case 0:
                                tblOrdes.setItems(OrderTableQuery.getLastTodayOrders());
                                break;
                            case 1:
                                tblOrdes.setItems(OrderTableQuery.getLast7DaysOrders());
                                break;
                            case 2:
                                tblOrdes.setItems(OrderTableQuery.getLast30DaysOrders());
                                break;
                            case 3:
                                tblOrdes.setItems(OrderTableQuery.getLastAllLoanOrders());
                                break;
                            case 4:
                                tblOrdes.setItems(OrderTableQuery.getDeleteReqDeatils());
                                break;
                            case 5:
                                tblOrdes.setItems(OrderTableQuery.getLast3MonthsOrders());
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();

                }
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
    }

    private void setComboboxData() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Today Bills");
        list.add("Last 7 Days Bills");
        list.add("Last 30 Days Bills");
        list.add("loan Bills");
        list.add("Delete req Bills");
        list.add("Last 3 Month Bills");

        cmbxSelectCatagary.setItems(list);
        cmbxSelectCatagary.getSelectionModel().select(0);

        cmbxSelectCatagary.setStyle("-fx-font-size: 15pt;");
    }

    private void setTableDeatils() {
        clmT1No.setCellValueFactory(new PropertyValueFactory<>("no"));
        clmT1InvoiceNo.setCellValueFactory(new PropertyValueFactory<>("invoiceNo"));
        clmT1Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmT1Time.setCellValueFactory(new PropertyValueFactory<>("time"));
        clmT1Customer.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        clmT1IsLone.setCellValueFactory(new PropertyValueFactory<>("onLoan"));

        clmT1Date.setStyle("-fx-alignment: CENTER;");
        clmT1Time.setStyle("-fx-alignment: CENTER;");
        clmT1InvoiceNo.setStyle("-fx-alignment: CENTER;");
        clmT1IsLone.setStyle("-fx-alignment: CENTER;");
        tblOrdes.setStyle("-fx-font-size: 15pt;");

        clmT2No.setCellValueFactory(new PropertyValueFactory<>("no"));
        clmT2Code1.setCellValueFactory(new PropertyValueFactory<>("code1"));
        clmT2Code2.setCellValueFactory(new PropertyValueFactory<>("code2"));
        clmT2Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        clmT2Qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        clmT2SalePrice.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
        clmT2NextAmount.setCellValueFactory(new PropertyValueFactory<>("nextAmount"));

        clmT2Qty.setStyle("-fx-alignment: CENTER;");
        clmT2SalePrice.setStyle("-fx-alignment: CENTER-RIGHT;");
        clmT2NextAmount.setStyle("-fx-alignment: CENTER-RIGHT;");
        tblOrderDeatils.setStyle("-fx-font-size: 15pt;");
    }

    public void btnSetPaidOnAction(ActionEvent actionEvent) {
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Paid Rs: " + -Double.parseDouble(lblBalance.getText()) + " ?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {
            try {
                OrderTableQuery.setLoanPaid(Integer.parseInt(lblInvoiceNo.getText()));
                fillOrderDetails(Integer.parseInt(lblInvoiceNo.getText()));

                int x = cmbxSelectCatagary.getSelectionModel().getSelectedIndex();
                switch (x) {
                    case 0:
                        tblOrdes.setItems(OrderTableQuery.getLastTodayOrders());
                        break;
                    case 1:
                        tblOrdes.setItems(OrderTableQuery.getLast7DaysOrders());
                        break;
                    case 2:
                        tblOrdes.setItems(OrderTableQuery.getLast30DaysOrders());
                        break;
                    case 3:
                        tblOrdes.setItems(OrderTableQuery.getLastAllLoanOrders());
                        break;
                    case 4:
                        tblOrdes.setItems(OrderTableQuery.getDeleteReqDeatils());
                        break;
                    case 5:
                        tblOrdes.setItems(OrderTableQuery.getLast3MonthsOrders());
                }

                ImageView imageView = new ImageView("image/Notifications/tick.png");
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);
                Notifications n = Notifications.create()
                        .darkStyle()
                        .title("SUCCESS")
                        .position(Pos.BOTTOM_RIGHT)
                        .text("Ok, Bill set Paid.")
                        .graphic(imageView);
                n.show();

                AutoBackUp.getAutoBackup();                                                         //get Auto BAckUp

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
    }

    private void setSearch() {
        //----------tbl search codes----!!!---------------------------
        ObservableList list=null;
        try {
            list = OrderTableQuery.getLast3MonthsOrders();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        FilteredList<OrdersTM> filteredList = new FilteredList<>(list, b -> true);
        txtSearch.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredList.setPredicate(OrdersTM -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowecase = newValue.toLowerCase();

                if (String.valueOf(OrdersTM.getInvoiceNo()).toLowerCase().indexOf(lowecase) != -1) {
                    return true;
                } else if (OrdersTM.getDate().toLowerCase().indexOf(lowecase) != -1) {
                    return true;
                } else
                    return OrdersTM.getCustomerName().toLowerCase().indexOf(lowecase) != -1;
            });
        }));

        SortedList<OrdersTM> sortedlist = new SortedList<>(filteredList);
        sortedlist.comparatorProperty().bind(tblOrdes.comparatorProperty());
        tblOrdes.setItems(sortedlist);

        //-------------------tbl search code END!----------------
    }

    public void chbAdvanceSearchOnAction(ActionEvent actionEvent) {
        try {
            if (chbAdvanceSearch.isSelected()) {
                if (cmbxSelectCatagary.getSelectionModel().getSelectedIndex() != 5) {
                    cmbxSelectCatagary.getSelectionModel().select(5);
                }
                setSearch();
                txtSearch.setDisable(false);
                cmbxSelectCatagary.setDisable(true);

            } else {
                txtSearch.setDisable(true);
                cmbxSelectCatagary.setDisable(false);
                txtSearch.clear();
                tblOrdes.setItems(OrderTableQuery.getLastTodayOrders());
                cmbxSelectCatagary.getSelectionModel().select(0);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnOrderDeleteButtonOnAction(ActionEvent actionEvent) {
        if (!txtDeleteOrderInvoiceNumber.getText().isEmpty()) {
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Invoice No " + txtDeleteOrderInvoiceNumber.getText() + " Delete?", ButtonType.YES, ButtonType.NO).showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {
                try {
                    boolean delete = OrderTableQuery.deleteOrder(Integer.parseInt(txtDeleteOrderInvoiceNumber.getText()));
                    if (delete) {
                        setOptionButtonsDefault();
                        ImageView imageView1 = new ImageView("image/Notifications/tick.png");
                        imageView1.setFitWidth(50);
                        imageView1.setFitHeight(50);
                        Notifications n1 = Notifications.create()
                                .darkStyle()
                                .title("SUCCESS")
                                .position(Pos.BOTTOM_RIGHT)
                                .text("Invoice " + txtDeleteOrderInvoiceNumber.getText() + " Deleted")
                                .graphic(imageView1);
                        n1.show();

                        AutoBackUp.getAutoBackup();                                                           //get Auto Backup

                        int x = cmbxSelectCatagary.getSelectionModel().getSelectedIndex();
                        switch (x) {
                            case 0:
                                tblOrdes.setItems(OrderTableQuery.getLastTodayOrders());
                                break;
                            case 1:
                                tblOrdes.setItems(OrderTableQuery.getLast7DaysOrders());
                                break;
                            case 2:
                                tblOrdes.setItems(OrderTableQuery.getLast30DaysOrders());
                                break;
                            case 3:
                                tblOrdes.setItems(OrderTableQuery.getLastAllLoanOrders());
                                break;
                            case 4:
                                tblOrdes.setItems(OrderTableQuery.getDeleteReqDeatils());
                                break;
                            case 5:
                                tblOrdes.setItems(OrderTableQuery.getLast3MonthsOrders());
                        }

                        clear();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            txtDeleteOrderInvoiceNumber.clear();
        }
    }

    //---------------------------------START-------------------------------------------

    public void txtDeleteInvoiceNumberOnKeyType(KeyEvent keyEvent) {
        if (!(Character.isDigit(keyEvent.getCharacter().charAt(0)) || keyEvent.getCode().equals(KeyCode.BACK_SPACE))) {
            keyEvent.consume();
        }
    }

    public void btnPrintOnAction(ActionEvent actionEvent) {
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Print Bill?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {
            try {
                ObservableList<OrderDeatalsTM> orderDeatils = OrderDetailTableQuery.getOrderDeatils(invoiceNo);
                OrderDetails orderDetails = OrderTableQuery.getOrderDeatil(invoiceNo);

                double bHeight = Double.valueOf(orderDeatils.size());

                PrinterJob pj = PrinterJob.getPrinterJob();
                pj.setPrintable(new AfterPrintBill(orderDetails, orderDeatils), getPageFormat(pj, bHeight));

                pj.print();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
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

    //---------------------------------END--------------------------------------------

    private void clear() {
        lblInvoiceNo.setText("--");
        lblDate.setText("--");
        lblTime.setText("--");
        lblCustomerName.setText("--");
        lblPaymentStatement.setText("--");
        lblSaleType.setText("--");
        lblNoOfItems.setText("--");
        lblCash.setText("--");
        lblFullCost.setText("--");
        lblBalance.setText("--");
        lblDescount.setText("--");
        tblOrderDeatils.setItems(null);
        btnSetPaid.setVisible(false);
        btnPrint.setVisible(false);
    }

    public void SetPaindAndPrintConfirmationReportOnAction(ActionEvent actionEvent) {
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Paid Rs: " + -Double.parseDouble(lblBalance.getText()) + " ?\nPrint Confirmation Report?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {
            try {
//            ---------------------------------
                ObservableList<OrderDeatalsTM> orderDeatils = OrderDetailTableQuery.getOrderDeatils(invoiceNo);
                OrderDetails orderDetails = OrderTableQuery.getOrderDeatil(invoiceNo);

                double bHeight = Double.valueOf(orderDeatils.size());

                PrinterJob pj = PrinterJob.getPrinterJob();
                pj.setPrintable(new DebtPaymentConform(orderDetails, orderDeatils), getPageFormat(pj, bHeight));

                pj.print();
//                ------------------------------------------

                OrderTableQuery.setLoanPaid(Integer.parseInt(lblInvoiceNo.getText()));
                fillOrderDetails(Integer.parseInt(lblInvoiceNo.getText()));

                int x = cmbxSelectCatagary.getSelectionModel().getSelectedIndex();
                switch (x) {
                    case 0:
                        tblOrdes.setItems(OrderTableQuery.getLastTodayOrders());
                        break;
                    case 1:
                        tblOrdes.setItems(OrderTableQuery.getLast7DaysOrders());
                        break;
                    case 2:
                        tblOrdes.setItems(OrderTableQuery.getLast30DaysOrders());
                        break;
                    case 3:
                        tblOrdes.setItems(OrderTableQuery.getLastAllLoanOrders());
                        break;
                    case 4:
                        tblOrdes.setItems(OrderTableQuery.getDeleteReqDeatils());
                        break;
                    case 5:
                        tblOrdes.setItems(OrderTableQuery.getLast3MonthsOrders());
                }

                ImageView imageView = new ImageView("image/Notifications/tick.png");
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);
                Notifications n = Notifications.create()
                        .darkStyle()
                        .title("SUCCESS")
                        .position(Pos.BOTTOM_RIGHT)
                        .text("Ok, Bill set Paid.")
                        .graphic(imageView);
                n.show();

                AutoBackUp.getAutoBackup();                                                     //get Auto BackUp

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }

    public void btnDeleteReqCancelOnAction(ActionEvent actionEvent) {
        try {
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "invoice No " + Integer.parseInt(txtDeleteOrderInvoiceNumber.getText()) + " Delete request cancel?", ButtonType.YES, ButtonType.NO).showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {
                boolean req = DeleteReqBillTableQuery.reqDelete(Integer.parseInt(txtDeleteOrderInvoiceNumber.getText()));
                if (req) {
                    ImageView imageView1 = new ImageView("image/Notifications/tick.png");
                    imageView1.setFitWidth(50);
                    imageView1.setFitHeight(50);
                    Notifications n1 = Notifications.create()
                            .darkStyle()
                            .title("SUCCESS")
                            .position(Pos.BOTTOM_RIGHT)
                            .text("Invoice " + lblInvoiceNo.getText() + " Delet request canceled.")
                            .graphic(imageView1);
                    n1.show();
                    try {
                        int x = cmbxSelectCatagary.getSelectionModel().getSelectedIndex();
                        switch (x) {
                            case 0:
                                tblOrdes.setItems(OrderTableQuery.getLastTodayOrders());
                                break;
                            case 1:
                                tblOrdes.setItems(OrderTableQuery.getLast7DaysOrders());
                                break;
                            case 2:
                                tblOrdes.setItems(OrderTableQuery.getLast30DaysOrders());
                                break;
                            case 3:
                                tblOrdes.setItems(OrderTableQuery.getLastAllLoanOrders());
                                break;
                            case 4:
                                tblOrdes.setItems(OrderTableQuery.getDeleteReqDeatils());
                                break;
                            case 5:
                                tblOrdes.setItems(OrderTableQuery.getLast3MonthsOrders());
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    clear();
                }
            }
            setOptionButtonsDefault();
            txtDeleteOrderInvoiceNumber.clear();
        }catch (NumberFormatException e){}
    }

    public void txtDeleteOrderInvoiceNumberOnAction(ActionEvent actionEvent) {
        if (OrderTableQuery.isOrderHave(Integer.parseInt(txtDeleteOrderInvoiceNumber.getText()))) {
            btnOrderDeleteButton.setDisable(false);
            if (DeleteReqBillTableQuery.isReqHave(Integer.parseInt(txtDeleteOrderInvoiceNumber.getText()))) {
                btnDeleteReqCancel.setVisible(true);
            }
        }
    }

    private void setOptionButtonsDefault() {
        btnDeleteReqCancel.setVisible(false);
        btnOrderDeleteButton.setDisable(true);
    }
}
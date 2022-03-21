package contoller.mainTask;

import Invoice.DebetBill;
import Invoice.MainBill;
import Query.DeleteReqBillTableQuery;
import Query.OrderDetailTableQuery;
import Query.OrderTableQuery;
import db.AutoBackUp;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import module.DeleteReq;
import module.OrderDeatalsTM;
import module.OrderDetails;
import org.controlsfx.control.Notifications;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

public class ShowLastBillDeatailsFormContoller {

    public static int invoiceNo;
    public static boolean isOwner;
    public static Label tempLbl;
    public TableColumn clmT2Description;
    public TableColumn clmT2Code2;
    public TableView tblOrderDeatils;
    public TableColumn clmT2No;
    public TableColumn clmT2Code1;
    public TableColumn clmT2Qty;
    public TableColumn clmT2SalePrice;
    public TableColumn clmT2NextAmount;
    public Label lblInvoiceNo;
    public Label lblSaleType;
    public Label lblCustomerName;
    public Label lblDate;
    public Label lblTime;
    public Label lblPaymentStatement;
    public Label lblFullCost;
    public Label lblDescount;
    public Label lblCash;
    public Label lblBalance;
    public Label lblNoOfItems;
    public Button btnSetPaid;
    public Button btnOrderDeleteButton;
    public Button btnPrint;
    public Label lblCashierName;


    public void initialize() {

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

        fillOrderDetails(invoiceNo);

        deleteButtonSet();
    }

    private void deleteButtonSet() {
        if (isOwner) {
            btnOrderDeleteButton.setVisible(true);
        } else {
            if (DeleteReqBillTableQuery.isReqHave(Integer.parseInt(lblInvoiceNo.getText()))) {
                btnOrderDeleteButton.setText("Req Cancel");
                btnOrderDeleteButton.setVisible(true);
                btnOrderDeleteButton.setStyle("-fx-background-color:  #4ED964");
            } else {
                btnOrderDeleteButton.setText("Delete Req");
                btnOrderDeleteButton.setVisible(true);
            }
        }
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
            lblCashierName.setText(detail.getCashiyer());

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

    public void btnSetPaidOnAction(ActionEvent actionEvent) {
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Paid Rs: " + -Double.parseDouble(lblBalance.getText()) + " ?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {
            try {
                OrderTableQuery.setLoanPaid(Integer.parseInt(lblInvoiceNo.getText()));
                fillOrderDetails(Integer.parseInt(lblInvoiceNo.getText()));
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
            }
        }
    }

    //---------------------------------START--------------------------------------------

    public void btnOrderDeleteButtonOnAction(ActionEvent actionEvent) {

        if (btnOrderDeleteButton.getText().equalsIgnoreCase("Delete")) {
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Invoice No " + lblInvoiceNo.getText() + " Delete?", ButtonType.YES, ButtonType.NO).showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {
                try {
                    boolean delete = OrderTableQuery.deleteOrder(Integer.parseInt(lblInvoiceNo.getText()));
                    if (delete) {
                        Stage stage = (Stage) btnOrderDeleteButton.getScene().getWindow();
                        stage.close();

                        ImageView imageView1 = new ImageView("image/Notifications/tick.png");
                        imageView1.setFitWidth(50);
                        imageView1.setFitHeight(50);
                        Notifications n1 = Notifications.create()
                                .darkStyle()
                                .title("SUCCESS")
                                .position(Pos.BOTTOM_RIGHT)
                                .text("Invoice " + lblInvoiceNo.getText() + " Deleted")
                                .graphic(imageView1);
                        n1.show();

                        AutoBackUp.getAutoBackup();                                                     //get Auto BackUp


                        if (tempLbl.getText().equals("1")) {
                            tempLbl.setText("0");
                        } else {
                            tempLbl.setText("1");
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } else if (btnOrderDeleteButton.getText().equalsIgnoreCase("Delete Req")) {
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Send request for invoice No " + lblInvoiceNo.getText() + " Delete?", ButtonType.YES, ButtonType.NO).showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {
                boolean req = DeleteReqBillTableQuery.setReq(new DeleteReq(
                        Integer.parseInt(lblInvoiceNo.getText()),
                        new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()),
                        new SimpleDateFormat("hh:mm:ss aa").format(Calendar.getInstance().getTime())
                ));
                if (req) {
                    Stage stage = (Stage) btnOrderDeleteButton.getScene().getWindow();
                    stage.close();

                    ImageView imageView1 = new ImageView("image/Notifications/tick.png");
                    imageView1.setFitWidth(50);
                    imageView1.setFitHeight(50);
                    Notifications n1 = Notifications.create()
                            .darkStyle()
                            .title("SUCCESS")
                            .position(Pos.BOTTOM_RIGHT)
                            .text("Invoice " + lblInvoiceNo.getText() + " Delet request send ok.")
                            .graphic(imageView1);
                    n1.show();

                    AutoBackUp.getAutoBackup();                                                     //get Auto BackUp

                    if (tempLbl.getText().equals("1")) {
                        tempLbl.setText("0");
                    } else {
                        tempLbl.setText("1");
                    }
                }
            }
        } else if (btnOrderDeleteButton.getText().equalsIgnoreCase("Req Cancel")) {
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "invoice No " + lblInvoiceNo.getText() + " Delete request cancel?", ButtonType.YES, ButtonType.NO).showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {
                boolean req = DeleteReqBillTableQuery.reqDelete(Integer.parseInt(lblInvoiceNo.getText()));
                if (req) {
                    Stage stage = (Stage) btnOrderDeleteButton.getScene().getWindow();
                    stage.close();

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

                    AutoBackUp.getAutoBackup();                                                     //get Auto BackUp

                    if (tempLbl.getText().equals("1")) {
                        tempLbl.setText("0");
                    } else {
                        tempLbl.setText("1");
                    }
                }
            }
        }

    }

    public void btnPrintOnAction(ActionEvent actionEvent) {
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Print Bill?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {
            try {
                OrderDetails orderDetails = OrderTableQuery.getOrderDeatil(Integer.parseInt(lblInvoiceNo.getText()));

                new MainBill().print(orderDetails,OrderDetailTableQuery.getOrderDetilsForPrint(Integer.parseInt(lblInvoiceNo.getText())));          //---------------------------------------------------Print bill-----------------------------------------

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    //---------------------------------END--------------------------------------------

    public void SetPaindAndPrintConfirmationReportOnAction(ActionEvent actionEvent) {
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Paid Rs: " + -Double.parseDouble(lblBalance.getText()) + " ?\nPrint Confirmation Report?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {
            try {

                OrderDetails orderDetails = OrderTableQuery.getOrderDeatil(Integer.parseInt(lblInvoiceNo.getText()));
                new DebetBill().print(orderDetails);                //---------------------------------------------------Print bill-----------------------------------------

                OrderTableQuery.setLoanPaid(Integer.parseInt(lblInvoiceNo.getText()));
                fillOrderDetails(Integer.parseInt(lblInvoiceNo.getText()));

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
            }
        }
    }

}

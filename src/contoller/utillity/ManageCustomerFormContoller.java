package contoller.utillity;

import Query.CustomerTableQuery;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import module.Customer;
import module.CustomerTM;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.regex.Pattern;

public class ManageCustomerFormContoller {
    public static TextField saleFormSearchCustomer;
    public static AutoCompletionBinding<String> bindingCustomer;
    private static AutoCompletionBinding<String> binding;
    public Label lblCustomerName;
    public TextArea txtDescription;
    public TextField txtCustomerName;
    public TextField txtContactNo1;
    public TextField txtContactNo2;
    public Button btnDelete;
    public Button btnSave;
    public Button btnClear;
    public Button btnFind;
    public Label lblContactNo1;
    public Label lblContactNo2;
    public TableView tblCustomers;
    public TableColumn clmNo;
    public TableColumn clmName;
    public TableColumn clmDescription;
    int cid;
    Pattern c5 = Pattern.compile("^0[0-9]{9}$");

    public void initialize() {
        setTableCloumns();
        requestFocusOrDieTrying(txtCustomerName);
        fillTable();
        setCustomerNames();
        deactiveButton();
        lableErrormasageSet();
        clearLables();
        enterKeyConsume();
    }

    private void enterKeyConsume() {
        txtCustomerName.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                txtContactNo1.requestFocus();
            }
        });

        txtContactNo1.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                txtContactNo2.requestFocus();
            }
        });

        txtContactNo2.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                txtDescription.requestFocus();
            }
        });
    }

    private void fillTable() {
        tblCustomers.setItems(CustomerTableQuery.getCustomersForTable());
    }

    public void setTableCloumns() {
        clmNo.setCellValueFactory(new PropertyValueFactory<>("no"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmDescription.setCellValueFactory(new PropertyValueFactory<>("desc"));

        clmNo.setStyle("-fx-alignment: CENTER;");
        tblCustomers.setStyle("-fx-font-size: 13pt;");
    }

    private void clearLables() {
        lblContactNo1.setGraphic(null);
        lblContactNo2.setGraphic(null);
        lblCustomerName.setGraphic(null);
    }

    private void lableErrormasageSet() {

        txtContactNo1.textProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != newValue) {
                try {
                    if (!txtContactNo1.getText().isEmpty()) {
                        if (!(c5.matcher(txtContactNo1.getText()).matches() && noSpaseLength(txtContactNo1) == 10)) {
                            Image img = new Image("image/problam.png");
                            ImageView view = new ImageView(img);
                            view.setFitHeight(20);
                            view.setFitWidth(20);
                            lblContactNo1.setGraphic(view);
                        } else {
                            Image img = new Image("Image/ok.png");
                            ImageView view = new ImageView(img);
                            view.setFitHeight(20);
                            view.setFitWidth(20);
                            lblContactNo1.setGraphic(view);
                        }
                    }
                    if (txtContactNo1.getText().isEmpty()) lblContactNo1.setGraphic(null);
                } catch (NullPointerException e) {
                }
            }
        });

        txtContactNo2.textProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != newValue) {
                try {
                    if (!(txtContactNo2.getText().isEmpty())) {
                        if (!(c5.matcher(txtContactNo2.getText()).matches() && noSpaseLength(txtContactNo2) == 10)) {
                            Image img = new Image("image/problam.png");
                            ImageView view = new ImageView(img);
                            view.setFitHeight(20);
                            view.setFitWidth(20);
                            lblContactNo2.setGraphic(view);
                        } else {
                            Image img = new Image("Image/ok.png");
                            ImageView view = new ImageView(img);
                            view.setFitHeight(20);
                            view.setFitWidth(20);
                            lblContactNo2.setGraphic(view);
                        }
                    }

                    if (txtContactNo2.getText().isEmpty()) lblContactNo2.setGraphic(null);
                } catch (NullPointerException e) {
                }
            }
        });

        txtCustomerName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != newValue) {
                try {
                    if (!txtCustomerName.getText().isEmpty()) {
                        if (!(noSpaseLength(txtCustomerName) >= 5)) {
                            Image img = new Image("image/problam.png");
                            ImageView view = new ImageView(img);
                            view.setFitHeight(20);
                            view.setFitWidth(20);
                            lblCustomerName.setGraphic(view);
                        } else {
                            Image img = new Image("Image/ok.png");
                            ImageView view = new ImageView(img);
                            view.setFitHeight(20);
                            view.setFitWidth(20);
                            lblCustomerName.setGraphic(view);
                        }
                    }
                    if (txtCustomerName.getText().isEmpty()) lblCustomerName.setGraphic(null);
                } catch (NullPointerException e) {
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

    private void setCustomerNames() {
        HashSet<String> list;
        try {
            binding.dispose();
        } catch (NullPointerException e) {
        }
        try {
            list = CustomerTableQuery.getCustomerNames();
            binding = TextFields.bindAutoCompletion(txtCustomerName, SuggestionProvider.create(list));
            binding.setVisibleRowCount(15);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnFindOnAction(ActionEvent actionEvent) {
        try {
            Customer customer = CustomerTableQuery.getCustomerDeatails(txtCustomerName.getText());
            if (customer != null) {
                cid = customer.getCid();
                txtContactNo1.setText(customer.getContactNo1());
                txtContactNo2.setText(customer.getContactNo2());
                txtDescription.setText(customer.getDescription());

                activeButtons();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void deactiveButton() {
        btnDelete.setVisible(false);
        btnSave.setText("Save");
    }

    private void activeButtons() {
        btnDelete.setVisible(true);
        btnSave.setText("Update");
    }

    private void clear() {
        txtCustomerName.clear();
        txtContactNo1.clear();
        txtContactNo2.clear();
        txtDescription.clear();
        deactiveButton();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Clear?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {
            clear();
        }
    }

    public void SaveOnAction(ActionEvent actionEvent) {
        try {
            if (btnSave.getText().equals("Save")) {
                save();
            } else {
                update();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void save() throws SQLException, ClassNotFoundException {
        ImageView imageView1 = new ImageView("image/Notifications/error.png");
        imageView1.setFitWidth(50);
        imageView1.setFitHeight(50);
        Notifications n1 = Notifications.create()
                .darkStyle()
                .title("ERROR!")
                .position(Pos.BOTTOM_LEFT)
                .graphic(imageView1);
        try {

            if (txtCustomerName.getText().isEmpty()) {
                n1.text("Customer name can't empty.");
                n1.show();
                return;
            } else if (txtDescription.getText().isEmpty()) {
                n1.text("Customer Description can't empty.");
                n1.show();
                return;
            } else if (!(noSpaseLength(txtCustomerName) >= 5)) {
                n1.text("Customer name must be at least 5 characters long.");
                n1.show();
                return;
            } else if (!(txtContactNo1.getText().isEmpty())) {
                if (!(c5.matcher(txtContactNo1.getText()).matches() && noSpaseLength(txtContactNo1) == 10)) {
                    n1.text("Contact no1 Error!");
                    n1.show();
                    return;
                }
            } else if (!(txtContactNo2.getText().isEmpty())) {
                if (!(c5.matcher(txtContactNo2.getText()).matches() && noSpaseLength(txtContactNo2) == 10)) {
                    n1.text("Contact no2 Error!");
                    n1.show();
                    return;
                }
            } else if (CustomerTableQuery.customerNameIsExsits(txtCustomerName.getText())) {
                n1.text("This Customer name already exists.");
                n1.show();
                return;
            }

        } catch (NullPointerException e) {
        }

        CustomerTableQuery.addCustomer(new Customer(
                txtCustomerName.getText(),
                txtContactNo1.getText(),
                txtContactNo2.getText(),
                txtDescription.getText()
        ));

        ImageView imageView = new ImageView("image/ok.png");
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        Notifications n = Notifications.create()
                .darkStyle()
                .title("SUCCESS")
                .text("Customer save successful.")
                .position(Pos.BOTTOM_RIGHT)
                .graphic(imageView);
        n.show();
        setCustomerNames();
        fillTable();
        clear();
        setSaleFormCustomerSugesens();
    }

    private void update() throws SQLException, ClassNotFoundException {
        ImageView imageView1 = new ImageView("image/Notifications/error.png");
        imageView1.setFitWidth(50);
        imageView1.setFitHeight(50);
        Notifications n1 = Notifications.create()
                .darkStyle()
                .title("ERROR!")
                .position(Pos.BOTTOM_LEFT)
                .graphic(imageView1);
        try {
            if (txtCustomerName.getText().isEmpty()) {
                n1.text("Customer name can't empty.");
                n1.show();
                return;
            } else if (txtDescription.getText().isEmpty()) {
                n1.text("Customer Description can't empty.");
                n1.show();
                return;
            } else if (!(noSpaseLength(txtCustomerName) >= 5)) {
                n1.text("Customer name must be at least 5 characters long.");
                n1.show();
                return;
            } else if (!(txtContactNo1.getText().isEmpty())) {
                if (!(c5.matcher(txtContactNo1.getText()).matches() && noSpaseLength(txtContactNo1) == 10)) {
                    n1.text("Contact no1 Error!");
                    n1.show();
                    return;
                }
            } else if (!(txtContactNo2.getText().isEmpty())) {
                if (!(c5.matcher(txtContactNo2.getText()).matches() && noSpaseLength(txtContactNo2) == 10)) {
                    n1.text("Contact no2 Error!");
                    n1.show();
                    return;
                }
            } else if (CustomerTableQuery.skipThisCustomerCustomerNameIsExsits(txtCustomerName.getText(), cid)) {
                n1.text("This Customer name already exists.");
                n1.show();
                return;
            }
        } catch (NullPointerException e) {
        }

        CustomerTableQuery.updateCustomer(new Customer(
                cid,
                txtCustomerName.getText(),
                txtContactNo1.getText(),
                txtContactNo2.getText(),
                txtDescription.getText()
        ));

        ImageView imageView = new ImageView("image/ok.png");
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        Notifications n = Notifications.create()
                .darkStyle()
                .title("SUCCESS")
                .text("Customer update successful.")
                .position(Pos.BOTTOM_RIGHT)
                .graphic(imageView);
        n.show();
        setCustomerNames();
        fillTable();
        clear();
        setSaleFormCustomerSugesens();
    }

    private int noSpaseLength(TextField f) {
        int count = 0;
        for (int i = 0; i < f.getText().length(); i++) {
            if (f.getText().charAt(i) != ' ') count++;
        }
        return count;
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Delete customer - " + txtCustomerName.getText() + "?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {
            CustomerTableQuery.delete(cid);

            ImageView imageView1 = new ImageView("image/Notifications/error.png");
            imageView1.setFitWidth(50);
            imageView1.setFitHeight(50);
            Notifications n1 = Notifications.create()
                    .darkStyle()
                    .title("SUCCESS")
                    .text("Customer delete successful.")
                    .position(Pos.BOTTOM_RIGHT)
                    .graphic(imageView1);
            n1.show();
            setCustomerNames();
            fillTable();
            clear();
        }
        setSaleFormCustomerSugesens();
    }

    public void itemShowSelectCustomerDeatils(ActionEvent actionEvent) {
        try {
            clear();
            CustomerTM selectedItem = (CustomerTM) tblCustomers.getSelectionModel().getSelectedItem();
            cid = selectedItem.getCid();
            fillDeatils(selectedItem.getCid());
        } catch (NullPointerException e) {
        }
    }

    private void fillDeatils(int cid) {
        Customer customer = null;
        try {
            customer = CustomerTableQuery.getCustomerDearils(cid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (customer != null) {
            txtCustomerName.setText(customer.getName());
            txtContactNo1.setText(customer.getContactNo1());
            txtContactNo2.setText(customer.getContactNo2());
            txtDescription.setText(customer.getDescription());

            activeButtons();
        }
    }

    private void setSaleFormCustomerSugesens() {
        saleFormSearchCustomer.clear();
        HashSet<String> list;
        try {
            bindingCustomer.dispose();
        } catch (NullPointerException e) {
        }

        try {

            list = CustomerTableQuery.getCustomerNames();
            bindingCustomer = TextFields.bindAutoCompletion(saleFormSearchCustomer, SuggestionProvider.create(list));
            bindingCustomer.setVisibleRowCount(15);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void contactNoKeyType(KeyEvent keyEvent) {
        if (!(Character.isDigit(keyEvent.getCharacter().charAt(0)) || keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.BACK_SPACE))) {
            keyEvent.consume();
        }
    }
}

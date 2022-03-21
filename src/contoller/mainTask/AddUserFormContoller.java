package contoller.mainTask;

import Query.UserTableQuery;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;
import module.User;
import module.UsertableTM;
import org.controlsfx.control.Notifications;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class AddUserFormContoller {

    private final Pattern c1 = Pattern.compile("^[a-zA-Z\\s]*$");
    private final Pattern c2 = Pattern.compile("[0-9]*");
    private final Pattern c3 = Pattern.compile("[0-9]*[Vv]");
    public RadioButton rdbNo;
    public ToggleGroup owner;
    public RadioButton rdbYes;
    public TableColumn clmNo;
    public TableColumn clmFullName;
    public TableColumn clmUserName;
    public TableColumn clmOwnerOrNot;
    public TableView tblUser;
    public TextField txtUserName;
    public TextField txtNic;
    public TextField txtFullName;
    public PasswordField pwdRetypePasword;
    public PasswordField pwdPassword;
    public JFXButton btnSave;
    public JFXDatePicker dobDatePiker;
    public Label lblFullnameChack;
    public Label lblNicChack;
    public Label lblUserNameChack;
    public Label lblpasswordChack;

    public void initialize() {
        settableCloumns();
        fillUserTable();
        setDatapikerStile();
        WorkErrorLable();
    }

    private void WorkErrorLable() {
        txtFullName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != newValue) {
                if (!(c1.matcher(txtFullName.getText()).matches() && noSpaseLength(txtFullName) > 4)) {
                    Image img = new Image("image/problam.png");
                    ImageView view = new ImageView(img);
                    view.setFitHeight(20);
                    view.setFitWidth(20);
                    lblFullnameChack.setGraphic(view);
                } else {
                    Image img = new Image("image/ok.png");
                    ImageView view = new ImageView(img);
                    view.setFitHeight(20);
                    view.setFitWidth(20);
                    lblFullnameChack.setGraphic(view);
                }
            }
        });

        txtNic.textProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != newValue) {
                if (!((c2.matcher(txtNic.getText()).matches() && noSpaseLength(txtNic) == 12) || (c3.matcher(txtNic.getText()).matches() && noSpaseLength(txtNic) == 10))) {
                    Image img = new Image("Image/problam.png");
                    ImageView view = new ImageView(img);
                    view.setFitHeight(20);
                    view.setFitWidth(20);
                    lblNicChack.setGraphic(view);
                } else {
                    Image img = new Image("Image/ok.png");
                    ImageView view = new ImageView(img);
                    view.setFitHeight(20);
                    view.setFitWidth(20);
                    lblNicChack.setGraphic(view);
                }
            }
        });

        txtUserName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != newValue) {
                if (!(noSpaseLength(txtUserName) > 4)) {
                    Image img = new Image("image/problam.png");
                    ImageView view = new ImageView(img);
                    view.setFitHeight(20);
                    view.setFitWidth(20);
                    lblUserNameChack.setGraphic(view);
                } else {
                    Image img = new Image("image/ok.png");
                    ImageView view = new ImageView(img);
                    view.setFitHeight(20);
                    view.setFitWidth(20);
                    lblUserNameChack.setGraphic(view);
                }
            }
        });

        pwdPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != newValue) {
                if (!(noSpaseLength(pwdPassword) > 4)) {
                    Image img = new Image("image/problam.png");
                    ImageView view = new ImageView(img);
                    view.setFitHeight(20);
                    view.setFitWidth(20);
                    lblpasswordChack.setGraphic(view);
                } else {
                    Image img = new Image("image/ok.png");
                    ImageView view = new ImageView(img);
                    view.setFitHeight(20);
                    view.setFitWidth(20);
                    lblpasswordChack.setGraphic(view);
                }
            }
        });
    }

    private void setDatapikerStile() {
        String pattern = "yyyy-MM-dd";

        dobDatePiker.setPromptText(pattern.toLowerCase());

        dobDatePiker.setConverter(new StringConverter<LocalDate>() {
            final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        dobDatePiker.setStyle("-fx-font-size: 18;");
    }

    private void fillUserTable() {
        ArrayList allUserSimpleDeatilsForTable = null;
        try {
            allUserSimpleDeatilsForTable = UserTableQuery.getAllUserSimpleDeatilsForTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList users = FXCollections.observableArrayList(allUserSimpleDeatilsForTable);

        tblUser.setItems(users);
    }

    private void settableCloumns() {
        clmNo.setCellValueFactory(new PropertyValueFactory<>("no"));
        clmFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        clmUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        clmOwnerOrNot.setCellValueFactory(new PropertyValueFactory<>("owner"));

        clmOwnerOrNot.setStyle("-fx-alignment: CENTER;");
        tblUser.setStyle("-fx-font-size: 15pt;");
    }

    public void fillOnAction(ActionEvent actionEvent) {
        fillData();
    }

    private void fillData() {
        clear();
        btnSave.setText("Update");
        txtUserName.setDisable(true);
        String userName = ((UsertableTM) tblUser.getSelectionModel().getSelectedItem()).getUserName();
        try {
            ResultSet resultSet = UserTableQuery.getOneUserData(userName);
            if (resultSet.next()) {
                txtFullName.setText(resultSet.getString(4));
                txtNic.setText(resultSet.getString(5));
                if (resultSet.getString(6) != null) {
                    dobDatePiker.setValue(LocalDate.parse(resultSet.getString(6)));
                }
                txtUserName.setText(resultSet.getString(1));
                pwdPassword.setText(resultSet.getString(2));
                pwdRetypePasword.setText(resultSet.getString(2));
                if (resultSet.getBoolean(3)) {
                    rdbYes.setSelected(true);
                } else {
                    rdbNo.setSelected(true);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        delete();
    }

    private void delete() {
        ImageView imageView1 = new ImageView("image/Notifications/error.png");
        imageView1.setFitWidth(50);
        imageView1.setFitHeight(50);
        Notifications n1 = Notifications.create()
                .darkStyle()
                .title("ERROR!")
                .position(Pos.BOTTOM_LEFT)
                .graphic(imageView1);
        UsertableTM selectedItem = (UsertableTM) tblUser.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            n1.text("Not selected user");
            n1.show();
            return;
        }
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Delete user " + selectedItem.getUserName() + " ?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {

            ObservableList<UsertableTM> items = tblUser.getItems();
            int count = 0;
            for (UsertableTM t : items) {
                if (t.getOwner().equals("YES")) count++;
            }
            if (count == 1 && selectedItem.getOwner().equals("YES")) {
                n1.text("Can't delete last Owner account");
                n1.show();
                return;
            }

            try {
                UserTableQuery.deleteUser(selectedItem.getUserName());
                fillUserTable();
                clear();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void ClerarOnAction(ActionEvent actionEvent) {
        clear();
    }

    private void clear() {
        txtFullName.clear();
        txtNic.clear();
        dobDatePiker.setValue(null);
        txtUserName.clear();
        pwdPassword.clear();
        pwdRetypePasword.clear();
        rdbNo.setSelected(true);
        rdbYes.setSelected(false);
        btnSave.setText("Save");
        txtUserName.setDisable(false);
        lableClear();
    }

    private void lableClear() {
        lblFullnameChack.setGraphic(null);
        lblNicChack.setGraphic(null);
        lblUserNameChack.setGraphic(null);
        lblpasswordChack.setGraphic(null);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        saveOrUpdate();
    }

//---------------Save or Update------------------------------------------------

    private void saveOrUpdate() {
        ImageView imageView1 = new ImageView("image/Notifications/error.png");
        imageView1.setFitWidth(50);
        imageView1.setFitHeight(50);
        Notifications n1 = Notifications.create()
                .darkStyle()
                .title("ERROR!")
                .position(Pos.BOTTOM_LEFT)
                .graphic(imageView1);

        if (txtFullName.getText().isEmpty()) {
            n1.text("Full Name can't empty.");
            n1.show();
            return;
        } else if (noSpaseLength(txtFullName) < 5) {
            n1.text("Name must be at least 5 characters long.");
            n1.show();
            return;
        } else if (!c1.matcher(txtFullName.getText()).matches()) {
            n1.text("Rechack name.");
            n1.show();
            return;
        }

        if (dobDatePiker.getValue() == null) {
            n1.text("Dob can't empty.");
            n1.show();
            return;
        }

        if (txtNic.getText().isEmpty()) {
            n1.text("NIC can't empty.");
            n1.show();
            return;
        } else if (!((c2.matcher(txtNic.getText()).matches() && noSpaseLength(txtNic) == 12) || (c3.matcher(txtNic.getText()).matches() && noSpaseLength(txtNic) == 10))) {
            n1.text("Invalide NIC.");
            n1.show();
            return;
        }

        if (txtUserName.getText().isEmpty()) {
            n1.text("Username can't empty.");
            n1.show();
            return;
        } else if (noSpaseLength(txtUserName) < 5) {
            n1.text("Username must be at least 5 characters long.");
            n1.show();
            return;
        }

        if (pwdPassword.getText().isEmpty()) {
            n1.text("Password can't empty.");
            n1.show();
            return;
        } else if (noSpaseLength(pwdPassword) < 5) {
            n1.text("Password must be at least 5 characters long.");
            n1.show();
            return;
        }

        if (!pwdRetypePasword.getText().equals(pwdPassword.getText())) {
            n1.text("Passwords not match.");
            n1.show();
            return;
        }

        if (btnSave.getText().equals("Save")) {

            User user = new User(txtUserName.getText(), pwdPassword.getText(), rdbYes.isSelected(), txtFullName.getText(), txtNic.getText(), dobDatePiker.getValue().toString());

            //-----Hadele root 2 account----------
            //------rootrootroot :- not database it can be restore backup only
            //-----Sehan Devinda  :- with database it can hadele all(include restore backup)
            if (user.getUserName().equals("rootrootroot") || user.getUserName().equals("Sehan Devinda")) {
                n1.text("This username canot use.");
                n1.show();
                return;
            }
            try {
                if (UserTableQuery.usreNamehave(user.getUserName())) {
                    n1.text("This username already exist.");
                    n1.show();
                    return;
                }

                Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Save?", ButtonType.YES, ButtonType.NO).showAndWait();
                if (buttonType.get().equals(ButtonType.NO)) return;

                UserTableQuery.addUser(user);

                ImageView imageView = new ImageView("image/Notifications/tick.png");
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);
                Notifications n = Notifications.create()
                        .darkStyle()
                        .title("SUCCESS")
                        .text("Save success.")
                        .position(Pos.BOTTOM_RIGHT)
                        .graphic(imageView);
                n.show();
                clear();
                fillUserTable();
//                AutoBackUp.getAutoBackup();
                return;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {

            User user = new User(txtUserName.getText(), pwdPassword.getText(), rdbYes.isSelected(), txtFullName.getText(), txtNic.getText(), dobDatePiker.getValue().toString());
            try {
                ArrayList<UsertableTM> items = UserTableQuery.getAllUserSimpleDeatilsForTable();
                int count = 0;
                for (UsertableTM t : items) {
                    if (t.getOwner().equals("YES")) count++;
                }
                if (count == 1 && rdbNo.isSelected()) {
                    n1.text("Please create new owner account and try again.");
                    n1.show();
                    return;
                }

                Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Update?", ButtonType.YES, ButtonType.NO).showAndWait();
                if (buttonType.get().equals(ButtonType.NO)) return;

                UserTableQuery.update(user);

                ImageView imageView = new ImageView("image/Notifications/tick.png");
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);
                Notifications n = Notifications.create()
                        .darkStyle()
                        .title("SUCCESS")
                        .text("Update success.")
                        .position(Pos.BOTTOM_RIGHT)
                        .graphic(imageView);
                n.show();
                clear();
                fillUserTable();
//                AutoBackUp.getAutoBackup();
                return;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
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

    public void fullnameKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            dobDatePiker.show();
            dobDatePiker.requestFocus();
        }
    }

    public void nicKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            txtUserName.requestFocus();
            txtUserName.selectAll();
        }
    }

    public void userNameKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            pwdPassword.requestFocus();
            pwdPassword.selectAll();
        }
    }

    public void passwordKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            pwdRetypePasword.requestFocus();
            pwdRetypePasword.selectAll();
        }
    }

    public void dobKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            txtNic.requestFocus();
            txtNic.selectAll();
        }
    }
}

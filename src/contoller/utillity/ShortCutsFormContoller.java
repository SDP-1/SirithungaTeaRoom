package contoller.utillity;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ShortCutsFormContoller {
    public Button btnOk;

    public void btnOkOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.close();
    }
}

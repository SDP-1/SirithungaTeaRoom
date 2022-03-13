package contoller.mainTask;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class StockAdjusmentController {
    public TextField txtNewQty;
    public TextField txtBarcode;
    public JFXButton btnSave;
    public Label lblName;
    public Label lblPrintname;
    public Label lblBuyingPrice;
    public Label lblretailPrice;
    public Label lblWholesalePrice;

    public void initialize(){
        requestFocusOrDieTrying(txtBarcode);
    }

    private void requestFocusOrDieTrying(Node node) {
        Platform.runLater(() -> {
            if (!node.isFocused()) {
                node.requestFocus();
                requestFocusOrDieTrying(node);
            }
        });
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

    }
}

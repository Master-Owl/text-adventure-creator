package controllers.dialog;

import controllers.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ConfirmDialog {
    private static boolean confirmed = false;

    public ConfirmDialog(){}
    public ConfirmDialog(String alertTitle, String alertMessage, String okText, String cancelText){
        title = alertTitle;
        message = alertMessage;
        okButtonText = okText;
        cancelButtonText = cancelText;
    }

    @FXML
    void closeDialog(ActionEvent event) {
        MainController.CloseActiveWindow(event);
        title = "";
        message = "";
        okButtonText = "";
        cancelButtonText = "";
    }

    @FXML
    public void initialize() {
        dialogTitle.setText(title);
        dialogMessage.setText(message);
        okButton.setText(okButtonText);
        cancelButton.setText(cancelButtonText);
    }

    @FXML
    private Label dialogMessage;
    private static String message;

    @FXML
    private Label dialogTitle;
    private static String title;

    @FXML
    private Button okButton;
    private static String okButtonText;

    @FXML
    private Button cancelButton;
    private static String cancelButtonText;

    @FXML
    public void cancel(ActionEvent event) {
        confirmed = false;
        MainController.CloseActiveWindow(event);
    }

    @FXML
    public void confirmSubmit(ActionEvent event){
        confirmed = true;
        MainController.CloseActiveWindow(event);
    }

    public boolean showAndWait() {
        Parent root;
        try {
            root = FXMLLoader.load(NewProjectDialog.class
                    .getResource("../../gui/fxml/confirm-dialog.fxml"));
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }

        Stage window = new Stage();
        window.initStyle(StageStyle.UTILITY);
        window.initModality(Modality.APPLICATION_MODAL);

        window.setScene(new Scene(root));
        window.showAndWait();
        return confirmed;
    }
}

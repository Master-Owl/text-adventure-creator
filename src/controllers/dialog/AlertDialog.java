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

public class AlertDialog {

    public AlertDialog() {}

    public AlertDialog(String alertTitle, String alertMessage){
        title = alertTitle;
        message = alertMessage;
    }

    @FXML
    private Label alertMessage;
    private static String message;

    @FXML
    private Label alertTitle;
    private static String title;

    @FXML
    void closeDialog(ActionEvent event) {
        MainController.CloseActiveWindow(event);
        title = "";
        message = "";
    }

    @FXML
    public void initialize() {
        alertTitle.setText(title);
        alertMessage.setText(message);
    }

    public void show() {
        Parent root;
        try {
            root = FXMLLoader.load(NewProjectDialog.class
                    .getResource("../../gui/fxml/alert-dialog.fxml"));
        } catch (Exception e) {
            System.err.println(e);
            return;
        }

        Stage window = new Stage();
        window.initStyle(StageStyle.UTILITY);
        window.initModality(Modality.APPLICATION_MODAL);
        window.getIcons().add(new Image("file:../../gui/images/alert.png"));

        window.setScene(new Scene(root));
        window.show();
    }
}

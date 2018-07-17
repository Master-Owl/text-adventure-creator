package controllers.dialog;

import controllers.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Model;

public class NewProjectDialog {

    @FXML
    private TextField newProjectNameField;


    @FXML
    void closeDialog(ActionEvent event) {
        MainController.CloseActiveWindow(event);
    }

    @FXML
    void createNewProject(ActionEvent event) {
        if (newProjectNameField.getText().equals("")) {
            AlertDialog dialog = new AlertDialog("Oops!", "You need to name your project.");
            dialog.show();
            return;
        }
        closeDialog(event);
        Model.instance.setProjectName(newProjectNameField.getText());
        // TODO: initialize singleton & create new save file
        MainController.instance.displayDefaultScene();
    }

    public void show() {
        Parent root;
        try {
            root = FXMLLoader.load(NewProjectDialog.class
                    .getResource("../../gui/fxml/new-project-dialog.fxml"));
        } catch (Exception e) {
            System.err.println(e);
            return;
        }

        Stage window = new Stage();
        window.initStyle(StageStyle.UTILITY);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Create New Project");

        window.setScene(new Scene(root));
        window.show();
    }
}

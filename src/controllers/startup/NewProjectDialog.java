package controllers.startup;

import controllers.MainController;
import controllers.MainLayoutController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NewProjectDialog {

    private String newProjectName;
    private String creatorName;

    public String getProjectName() {
        return newProjectName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    @FXML
    private TextField newProjectNameField;

    @FXML
    private TextField creatorNameField;


    @FXML
    void closeDialog(ActionEvent event) {
        newProjectName = newProjectNameField.getText();
        creatorName = creatorNameField.getText();
        MainController.CloseActiveWindow(event);
    }

    @FXML
    void createNewProject(ActionEvent event) {
        closeDialog(event);
        // TODO: initialize singleton & create new save file
        MainController.instance.displayDefaultScene();
    }

    public void show() {
        Parent root;
        try {
            root = FXMLLoader.load(NewProjectDialog.class
                    .getResource("../../gui/fxml/new-project-dialog.fxml"));
        } catch (Exception e) {
            System.out.println(e);
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

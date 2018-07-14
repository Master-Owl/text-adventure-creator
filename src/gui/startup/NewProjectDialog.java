package gui.startup;

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

    @FXML
    private TextField newProjectNameField;

    @FXML
    private TextField creatorNameField;


    @FXML
    void closeDialog(ActionEvent event) {
        newProjectName = newProjectNameField.getText();
        creatorName = creatorNameField.getText();
        Main.CloseActiveWindow(event);
    }

    @FXML
    void createNewProject(ActionEvent event) {
        closeDialog(event);
    }

    public void show() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/new-project-dialog.fxml"));

        Stage window = new Stage();
        window.initStyle(StageStyle.UTILITY);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Create New Project");

        window.setScene(new Scene(root));
        window.showAndWait();
    }
}

package controllers.startup;

import controllers.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;

public class StartScreenController {

    private NewProjectDialog newProjectDialog = new NewProjectDialog();

    @FXML
    void newProjectDialog(ActionEvent event) throws Exception {
        newProjectDialog.show();
    }

    @FXML
    void openProjectDialog(ActionEvent event) {
        ExtensionFilter filter = new ExtensionFilter("Text Adventure Project", ".tap");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Project");
        fileChooser.setSelectedExtensionFilter(filter);
        File file = fileChooser.showOpenDialog(MainController.GetActiveWindow(event));
        if (file != null) {
            System.out.println("File wasn't null.");
        }

    }

    @FXML
    void exitApplication(ActionEvent event) {
        MainController.CloseActiveWindow(event);
    }

    public static Scene getScene() throws Exception {
        Parent root = FXMLLoader.load(StartScreenController.class
                .getResource("../../gui/fxml/start-screen.fxml"));
        return new Scene(root);
    }
}

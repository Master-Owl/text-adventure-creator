package gui.startup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;

public class StartScreenController {

    @FXML
    void newProjectDialog(ActionEvent event) throws Exception {
        NewProjectDialog dialog = new NewProjectDialog();
        dialog.show();
    }

    @FXML
    void openProjectDialog(ActionEvent event) {
        ExtensionFilter filter = new ExtensionFilter("Text Adventure Project", ".tap");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Project");
        fileChooser.setSelectedExtensionFilter(filter);
        File file = fileChooser.showOpenDialog(Main.GetActiveWindow(event));
        if (file != null) {
            System.out.println("File wasn't null.");
        }
    }

    @FXML
    void exitApplication(ActionEvent event) {
        Main.CloseActiveWindow(event);
    }

    public void show(Stage window) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/start-screen.fxml"));
        window.setTitle("Text Adventure Creator");
        window.setScene(new Scene(root));
        window.show();
    }
}

package controllers.startup;

import controllers.MainController;
import controllers.dialog.NewProjectDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Model;

import java.io.File;

public class StartScreenController {

    private NewProjectDialog newProjectDialog = new NewProjectDialog();

    @FXML
    private ListView recentProjectsList;

    @FXML
    public void initialize() {
        ObservableList<String> projectNames = FXCollections.observableArrayList();
        projectNames.addAll(Model.GetProjectFileNames());
        recentProjectsList.setItems(projectNames);
        recentProjectsList.setOnMouseClicked(click -> {
            if (click.getClickCount() == 2) {
                String projectName = (String)recentProjectsList
                        .getSelectionModel().getSelectedItem();
                Model.LoadProject(projectName);
                System.out.println("Back to the handler");
            }
        });
    }

    @FXML
    void newProjectDialog(ActionEvent event) throws Exception {
        newProjectDialog.show();
    }

    @FXML
    void openProjectDialog(ActionEvent event) {
        ExtensionFilter filter = new ExtensionFilter("Text Adventure Project", "*.tap");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Project");
        fileChooser.setSelectedExtensionFilter(filter);
        File file = fileChooser.showOpenDialog(MainController.GetActiveWindow(event));
        if (file != null) {
            Model.LoadProject(file.getAbsolutePath());
            MainController.instance.displayDefaultScene();
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

package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MainController extends VBox {

    public static MainController instance = new MainController();

    private MainController() {}

    private Stage mainWindow;
    private Scene mainScene = null;
    private Pane scenePane;
    private MenuBar menuBar;
    private MainMenu menu;
    private boolean isDisplayingMainScene = false;

    public void setMainWindow(Stage window) {
        mainWindow = window;
    }

    public void setWindowTitle(String title) {
        mainWindow.setTitle(title);
    }

    public void displayDefaultScene() {
        try {
            displayScene(MainLayoutController.getScene());
            MainLayoutController.controller.initLabels();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void displayScene(Scene scene) {
        try {
            if (!isDisplayingMainScene) {
                setScene(getMainScene());
                if (scenePane == null) {
                    scenePane = (Pane) mainScene.lookup("#scenePane");
                }
                if (menu == null) {
                    menu = new MainMenu();
                    menu.initMenu();
                    menuBar = (MenuBar) mainScene.lookup("#menuBar");
                    menuBar.getMenus().clear();
                    menuBar.getMenus().addAll(menu.getMenu());
                }
                isDisplayingMainScene = true;
            }
            scenePane.getChildren().clear();
            scenePane.getChildren().add(scene.getRoot());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void setScene(Scene scene) {
        isDisplayingMainScene = false;
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    public static void CloseActiveWindow(ActionEvent event) {
        Stage window = (Stage) GetActiveWindow(event);
        window.close();
    }

    public static Window GetActiveWindow(ActionEvent event) {
        Node node = (Node) event.getSource();
        return node.getScene().getWindow();
    }

    private Scene getMainScene() throws Exception {
        if (mainScene == null) {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("../gui/fxml/main-scene.fxml"));
            fxml.setRoot(this);
            fxml.setController(this);
            fxml.load();
            mainScene = new Scene(this);
        }
        return mainScene;
    }
}

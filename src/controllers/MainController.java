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

import java.util.Stack;

public class MainController extends VBox {

    public static MainController instance = new MainController();

    private MainController() {}

    private Stage mainWindow;
    private Scene mainScene = null;
    private Pane scenePane;
    private MenuBar menuBar;
    private MainMenu menu;
    private Stack<Scene> sceneHistory = new Stack<>();
    private boolean isDisplayingMainWindow = false;

    // Window Functions ======================================================
    public void setMainWindow(Stage window) {
        mainWindow = window;
    }
    public void setWindowTitle(String title) {
        mainWindow.setTitle(title);
    }
    public static void CloseActiveWindow(ActionEvent event) {
        Stage window = (Stage) GetActiveWindow(event);
        window.close();
    }
    public static Window GetActiveWindow(ActionEvent event) {
        Node node = (Node) event.getSource();
        return node.getScene().getWindow();
    }
    // Window Functions ======================================================


    // Scene Functions =======================================================
    public void displayDefaultScene() {
        try {
            displayScene(MainLayoutController.getScene());
            MainLayoutController.controller.initPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void displayScene(Scene scene) {
        try {
            if (!isDisplayingMainWindow) {
                setScene(getMainScene());
                if (scenePane == null) {
                    scenePane = (Pane) mainScene.lookup("#scenePane");
                }
                if (menu == null) {
                    menu = new MainMenu();
                    menu.initMenu(mainWindow);
                    menuBar = (MenuBar) mainScene.lookup("#menuBar");
                    menuBar.getMenus().clear();
                    menuBar.getMenus().addAll(menu.getMenu());
                }
                isDisplayingMainWindow = true;
            }
            scenePane.getChildren().clear();
            scenePane.getChildren().add(scene.getRoot());
            sceneHistory.push(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setScene(Scene scene) {
        isDisplayingMainWindow = false;
        mainWindow.setScene(scene);
        mainWindow.show();
    }
    public Scene displayPreviousScene() {
        if (sceneHistory.size() == 0) {
            displayDefaultScene();
            return sceneHistory.peek(); // Returns current scene
        }
        sceneHistory.pop(); // Removes current scene from stack
        Scene previous = sceneHistory.peek();
        scenePane.getChildren().clear();
        scenePane.getChildren().addAll(previous.getRoot());
        return previous;
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
    // Scene Functions =======================================================

}

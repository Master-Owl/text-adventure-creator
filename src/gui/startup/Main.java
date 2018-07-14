package gui.startup;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Main extends Application {

    @Override
    public void start(Stage window) throws Exception{
        StartScreenController startScreenController = new StartScreenController();
        startScreenController.show(window);
    }

    public static void CloseActiveWindow(ActionEvent event) {
        Stage window = (Stage)GetActiveWindow(event);
        window.close();
    }

    public static Window GetActiveWindow(ActionEvent event) {
        Node node = (Node)event.getSource();
        return node.getScene().getWindow();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

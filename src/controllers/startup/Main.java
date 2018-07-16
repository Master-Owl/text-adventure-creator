package controllers.startup;

import controllers.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage window) throws Exception {
        MainController.instance.setMainWindow(window);
        MainController.instance.setWindowTitle("Text Adventure Creator");
        MainController.instance.setScene(StartScreenController.getScene());
    }


    public static void main(String[] args) {
        launch(args);
    }
}

package controllers;

import controllers.startup.StartScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainLayoutController {

    @FXML
    private Button createNewButton;

    @FXML
    private Label roomCountLabel;

    @FXML
    private Label npcCountLabel;

    @FXML
    private Label projectName;

    @FXML
    private Button viewRoomsButton;

    @FXML
    private Button viewItemsButton;

    @FXML
    private Button viewNpcsButton;

    @FXML
    private Label itemCountLabel;

    @FXML
    void displayRooms(ActionEvent event) {

    }

    @FXML
    void displayItems(ActionEvent event) {

    }

    @FXML
    void displayNpcs(ActionEvent event) {

    }

    @FXML
    void openCreateNewDialog(ActionEvent event) {

    }

    public static Scene getScene() throws Exception {
        Parent root = FXMLLoader.load(MainLayoutController.class
                .getResource("../gui/fxml/main-page-layout.fxml"));
        return new Scene(root);
    }

}

package controllers;

import dataobjects.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class MainLayoutController {

    public static MainLayoutController controller;
    private ObservableList<Room> rooms = FXCollections.observableArrayList();

    public void initLabels() {
        roomCountLabel.setText("Room Count:  " + rooms.size());
    }

    @FXML
    private ListView viewingList;

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
        viewingList.getItems().clear();
        rooms.clear();

        Room r = new Room();
        r.setRoomName("A dummy room value");
        rooms.add(r);
        viewingList.setItems(rooms);
        viewingList.setCellFactory((Callback<ListView<Room>, ListCell<Room>>) list -> new Room());
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
        FXMLLoader loader = new FXMLLoader(MainLayoutController.class
                .getResource("../gui/fxml/main-page-layout.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        controller = loader.getController();
        return new Scene(root);
    }

}

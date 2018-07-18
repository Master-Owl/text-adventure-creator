package controllers;

import controllers.rooms.RoomEditController;
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
import model.Model;

public class MainLayoutController {

    public static MainLayoutController controller;
    private ObservableList<Room> rooms = FXCollections.observableArrayList();

    public void initLabels() {
        projectName.setText(Model.instance.getProjectName());
        roomCountLabel.setText("Room Count:  " + Model.instance.getRooms().size());
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
        rooms.clear();
        rooms.addAll(Model.instance.getRooms());

        viewingList.getItems().clear();
        viewingList.setItems(rooms);
        viewingList.setCellFactory((Callback<ListView<Room>, ListCell<Room>>) list -> new Room());
        viewingList.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                //Use ListView's getSelected Item
                Room selectedRoom = (Room)viewingList.getSelectionModel().getSelectedItem();
                if (selectedRoom != null) {
                    try {
                        MainController.instance.displayScene(RoomEditController.getScene());
                        RoomEditController.currentInstance.setRoomValues(selectedRoom);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @FXML
    void displayItems(ActionEvent event) {
        viewingList.getItems().clear();
    }

    @FXML
    void displayNpcs(ActionEvent event) {
        viewingList.getItems().clear();
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

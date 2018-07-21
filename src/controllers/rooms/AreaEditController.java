package controllers.rooms;

import controllers.MainController;
import dataobjects.Area;
import dataobjects.Exit;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;

public class AreaEditController {

    public static AreaEditController currentInstance;
    private boolean editingExistingRoom;
    private Area area;

    @FXML
    public void initialize() {
        currentInstance = this;
        editingExistingRoom = false;
        areaNameField.requestFocus();
    }

    public void setArea(Area area) {
        if (area == null) return;
        this.area = area;

        areaNameField.setText("Edit Area: " +area.getAreaName());

        ObservableList<Room> rooms = FXCollections.observableArrayList();
        rooms.addAll(area.getRooms());
        roomList.setItems(rooms);
        roomList.setCellFactory(list -> new Room());
        roomList.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                Room selectedRoom = roomList.getSelectionModel().getSelectedItem();
                if (selectedRoom != null) {
                    try {
                        if (selectedRoom.getArea() == null)
                            selectedRoom.setArea(area);
                        MainController.instance.displayScene(RoomEditController.getScene());
                        RoomEditController.currentInstance.setRoom(selectedRoom);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        areaNameField.requestFocus();
    }

    @FXML
    private TilePane roomTilePane;

    @FXML
    private Label areaPageLabel;

    @FXML
    private TextField areaNameField;

    @FXML
    private ListView<Room> roomList;


    @FXML
    void cancel(ActionEvent event) {
        MainController.instance.displayPreviousScene();
    }

    @FXML
    void saveArea(ActionEvent event) {
        if (area == null) {
            area = new Area(areaNameField.getText());
        } else {
            area.setAreaName(areaNameField.getText());
        }

        area.setRooms(roomList.getItems());
        MainController.instance.displayPreviousScene();
    }

    @FXML
    void deleteArea(ActionEvent event) {
        // TODO: this here function
    }

    @FXML
    void addNewRoom(ActionEvent event) {
        try {
            MainController.instance.displayScene(RoomEditController.getScene());
            if (area == null)
                area = new Area(areaNameField.getText());
            RoomEditController.currentInstance.setRoomArea(area);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Scene getScene() throws Exception {
        Parent root = FXMLLoader.load(AreaEditController.class
                .getResource("../../gui/fxml/area-edit-layout.fxml"));
        return new Scene(root);
    }
}
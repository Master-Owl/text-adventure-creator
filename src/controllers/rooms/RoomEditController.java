package controllers.rooms;

import controllers.MainController;
import controllers.dialog.AlertDialog;
import controllers.dialog.ConfirmDialog;
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
import javafx.scene.control.*;
import model.Model;

public class RoomEditController {

    public static RoomEditController currentInstance;
    private boolean editingExistingRoom = false;
    private Room room;

    @FXML
    public void initialize() {
        System.out.println("Init room page");
        currentInstance = this;
        editingExistingRoom = false;

        ObservableList<Area> areas =  FXCollections.observableArrayList();
        areas.setAll(Model.instance.getAreas());
        areaChoiceBox.setItems(areas);

        roomNameField.requestFocus();
    }

    @FXML
    private ChoiceBox<Area> areaChoiceBox;

    @FXML
    private ListView<?> enemyList;

    @FXML
    private Button addExitButton;

    @FXML
    private ListView<Exit> exitList;

    @FXML
    private Label roomPageLabel;

    @FXML
    private Button addNpcButton;

    @FXML
    private Button addEnemyButton;

    @FXML
    private TextField roomNameField;

    @FXML
    private Button cancelButton;

    @FXML
    private CheckBox makeAnotherCheckbox;

    @FXML
    private ListView<?> itemList;

    @FXML
    private Button saveButton;

    @FXML
    private ListView<?> npcList;

    @FXML
    private TextArea roomDescriptionArea;

    @FXML
    private Button addItemButton;

    @FXML
    private Button deleteRoomButton;

    @FXML
    void cancel(ActionEvent event) {
        Scene previous = MainController.instance.displayPreviousScene();
        if (previous.lookup("#areaPageLabel") != null && room != null)
            AreaEditController.currentInstance.setArea(room.getArea());
    }

    @FXML
    void saveRoom(ActionEvent event) {
        if (!checkForErrors()) return;
        if (!editingExistingRoom) {
            room = new Room(roomNameField.getText(),
                    roomDescriptionArea.getText());
        }
        else {
            room.setRoomName(roomNameField.getText());
            room.setRoomDescription(roomDescriptionArea.getText());
        }

        room.setArea(areaChoiceBox.getValue());
        room.setItems(itemList.getItems());
        room.setNpcs(npcList.getItems());
        room.setEnemies(enemyList.getItems());
        room.setExits(exitList.getItems());
        // Room is saved automatically b/c it's registered
        //  with the model in its constructor, then passed by
        //  reference around the project

        if (room.getArea() != null)
            room.getArea().addRoom(room);

        clearFields();
        if (!makeAnotherCheckbox.isSelected()) {
            cancel(event);
        }
    }

    @FXML
    void addItemToRoom(ActionEvent event) {

    }

    @FXML
    void addNpcToRoom(ActionEvent event) {

    }

    @FXML
    void addEnemyToRoom(ActionEvent event) {

    }

    @FXML
    void addExitToRoom(ActionEvent event) {
        try {
            MainController.instance.displayScene(ExitEditController.getScene());
            if (room == null) {
                room = new Room(roomNameField.getText(), roomDescriptionArea.getText());
            }
            ExitEditController.currentInstance.setFromRoom(room);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deleteRoom() {
        ConfirmDialog dialog = new ConfirmDialog("Delete Room",
                "Are you sure you want to delete this room?",
                "Yep", "No");

        if (dialog.showAndWait()) {
            Model.instance.removeRoom(room.roomId());
            MainController.instance.displayPreviousScene();
        }
    }

    public static Scene getScene() throws Exception {
        Parent root = FXMLLoader.load(RoomEditController.class
                .getResource("../../gui/fxml/room-edit-layout.fxml"));
        return new Scene(root);
    }

    public void clearFields() {
        roomNameField.setText("");
        roomDescriptionArea.setText("");
        itemList.getItems().clear();
        enemyList.getItems().clear();
        npcList.getItems().clear();
        exitList.getItems().clear();
        deleteRoomButton.setVisible(false);

        roomNameField.requestFocus();
    }

    public void setRoom(Room room) {
        if (room == null) return;
        this.room = room;
        editingExistingRoom = true;
        deleteRoomButton.setVisible(true);

        Area area = room.getArea();
        if (area != null) {
            areaChoiceBox.setValue(area);
        }

        roomPageLabel.setText("Edit Room: " + room.getRoomName());
        roomNameField.setText(room.getRoomName());
        roomDescriptionArea.setText(room.getRoomDescription());

        // Set Exits
        ObservableList<Exit> exits = FXCollections.observableArrayList();
        exits.addAll(room.getExits().values());
        exitList.setItems(exits);
        exitList.setCellFactory(list -> new Exit());
        exitList.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                //Use ListView's getSelected Item
                Exit selectedExit = exitList.getSelectionModel().getSelectedItem();
                if (selectedExit != null) {
                    try {
                        MainController.instance.displayScene(ExitEditController.getScene());
                        ExitEditController.currentInstance.setExit(selectedExit);
                        ExitEditController.currentInstance.setFromRoom(room);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void setRoomArea(Area area) {
        System.out.println("Set room area");
        areaChoiceBox.setValue(area);
    }

    private boolean checkForErrors() {
        if (roomNameField.getText().equals("")) {
            AlertDialog dialog = new AlertDialog("Doh!",
                    "You forgot to name the room!");
            dialog.show();
            return false;
        }
        if (roomDescriptionArea.getText().equals("")) {
            AlertDialog dialog = new AlertDialog("Doh!",
                    "You forgot to give the room a description!");
            dialog.show();
            return false;
        }
        return true;
    }
}

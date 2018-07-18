package controllers.rooms;

import controllers.MainController;
import controllers.MainLayoutController;
import controllers.dialog.AlertDialog;
import controllers.dialog.ConfirmDialog;
import dataobjects.Exit;
import dataobjects.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import model.Model;

public class RoomEditController {

    public static RoomEditController currentInstance;
    private boolean editingExistingRoom = false;
    private Room room;

    @FXML
    public void initialize() {
        currentInstance = this;
        editingExistingRoom = false;
        roomNameField.requestFocus();
    }

    @FXML
    private ListView<?> enemyList;

    @FXML
    private TitledPane addExitButton;

    @FXML
    private ListView<?> containerItemList;

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
        MainController.instance.displayDefaultScene();
    }

    @FXML
    void saveRoom(ActionEvent event) {
        if (!checkForErrors()) return;
        if (!editingExistingRoom)
            room = new Room(roomNameField.getText(),
                    roomDescriptionArea.getText());

        room.setItems(itemList.getItems());
        room.setNpcs(npcList.getItems());
        room.setEnemies(enemyList.getItems());
        room.setExits(exitList.getItems());

        if (editingExistingRoom)
            Model.instance.updateRoom(room.roomId(), room);
        else
            Model.instance.addRoom(room);

        clearFields();
        if (!makeAnotherCheckbox.isSelected()) {
            MainController.instance.displayDefaultScene();
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

    }

    @FXML
    void deleteRoom() {
        ConfirmDialog dialog = new ConfirmDialog("Delete Room",
                "Are you sure you want to delete this room?",
                "Yep", "No");

        if (dialog.showAndWait()) {
            Model.instance.removeRoom(room.roomId());
            MainController.instance.displayDefaultScene();
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

    public void setRoomValues(Room room) {
        this.room = room;
        editingExistingRoom = true;
        deleteRoomButton.setVisible(true);

        roomPageLabel.setText("Edit Room: " + room.getRoomName());
        roomNameField.setText(room.getRoomName());
        roomDescriptionArea.setText(room.getRoomDescription());
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

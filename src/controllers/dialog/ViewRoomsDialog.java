package controllers.dialog;

import controllers.MainController;
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
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Model;

import java.util.Collection;

public class ViewRoomsDialog {

    private static Stage window;

    public ViewRoomsDialog() {}

    @FXML
    public void initialize() {
        ObservableList<Room> rooms = FXCollections.observableArrayList();
        rooms.addAll(Model.instance.getRooms());
        roomList.setItems(rooms);
        roomList.setCellFactory(list -> new Room());
        roomList.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                Room selectedRoom = roomList.getSelectionModel().getSelectedItem();
                if (selectedRoom != null) {
                    try {
                        okButton.fireEvent(new ActionEvent()); // Close active window
                        MainController.instance.displayScene(RoomEditController.getScene());
                        RoomEditController.currentInstance.setRoom(selectedRoom);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        okButton.setOnAction(MainController::CloseActiveWindow);
    }

    @FXML
    private ListView<Room> roomList;

    @FXML
    private Button okButton;

    public void show() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass()
                    .getResource("../../gui/fxml/view-rooms-dialog.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        Stage dialog = new Stage();
        window = dialog;

        dialog.initStyle(StageStyle.UTILITY);
        dialog.setScene(new Scene(root));
        dialog.show();
    }
}

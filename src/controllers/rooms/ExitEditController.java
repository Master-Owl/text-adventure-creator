package controllers.rooms;

import controllers.MainController;
import controllers.dialog.AlertDialog;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Model;

public class ExitEditController {

    public static ExitEditController currentInstance;
    private Room fromRoom;
    private Exit exit;

    @FXML
    public void initialize() {
        currentInstance = this;

        ObservableList<Room> roomChoices = FXCollections.observableArrayList();
        roomChoices.addAll(Model.instance.getRooms());
        destinationRoomChoice.setItems(roomChoices);

        keywordField.requestFocus();
    }

    public void setExit(Exit exit) {
        if (exit == null) return;
        this.exit = exit;

        exitPageLabel.setText("Edit Exit");
        keywordField.setText(exit.getKeyword());
        descriptionField.setText(exit.getExitDescription());
        destinationRoomChoice.setValue(Model.instance.getRoom(exit.getToRoomId()));

        hiddenCheckbox.setSelected(exit.isHidden());
//        requireItemCheckbox.setSelected();
//        requireSkillCheckbox.setSelected();
    }

    public void setFromRoom(Room fromRoom) {
        this.fromRoom = fromRoom;
        destinationRoomChoice.getItems().remove(fromRoom);
    }

    @FXML
    private ChoiceBox<?> requiredSkillChoice;

    @FXML
    private CheckBox hiddenCheckbox;

    @FXML
    private ChoiceBox<?> requiredItemChoice;

    @FXML
    private TextField keywordField;

    @FXML
    private CheckBox requireItemCheckbox;

    @FXML
    private TextArea descriptionField;

    @FXML
    private ChoiceBox<Room> destinationRoomChoice;

    @FXML
    private CheckBox requireSkillCheckbox;

    @FXML
    private Button saveButton;

    @FXML
    private Label exitPageLabel;

    @FXML
    void cancel(ActionEvent event) {
        try {
            Scene previous = MainController.instance.displayPreviousScene();
            if (previous.lookup("#roomPageLabel") != null) {
                RoomEditController.currentInstance.setRoom(fromRoom);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void save(ActionEvent event) {
        if (!checkForErrors()) return;
        if (exit == null) {
            exit = new Exit(keywordField.getText(), destinationRoomChoice.getValue(), fromRoom);
        }
        else {
            exit.setKeyword(keywordField.getText());
            exit.setToRoom(destinationRoomChoice.getValue());
        }

        exit.setExitDescription(descriptionField.getText());
        exit.setHidden(hiddenCheckbox.isSelected());
        fromRoom.addExit(exit.getKeyword(), exit);

        cancel(event);
    }

    private boolean checkForErrors() {
        if (keywordField.getText().equals("")) {
            AlertDialog dialog = new AlertDialog("Pssst...",
                    "You need to give your exit a keyword!");
            dialog.show();
            return false;
        }
        if (destinationRoomChoice.getValue() == null) {
            AlertDialog dialog = new AlertDialog("Pssst...",
                    "You need to give your exit a destination!");
            dialog.show();
            return false;
        }
        return true;
    }

    public static Scene getScene() throws Exception {
        Parent root = FXMLLoader.load(ExitEditController.class
                .getResource("../../gui/fxml/exit-edit-layout.fxml"));
        return new Scene(root);
    }

}

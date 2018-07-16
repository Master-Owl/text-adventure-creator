package controllers.rooms;

import controllers.MainController;
import controllers.MainLayoutController;
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

public class RoomEditController {

    @FXML
    private ListView<?> enemyList;

    @FXML
    private TitledPane addExitButton;

    @FXML
    private ListView<?> containerItemList;

    @FXML
    private ListView<?> exitList;

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
    void cancel(ActionEvent event) {
        MainController.instance.displayDefaultScene();
    }

    @FXML
    void saveRoom(ActionEvent event) {

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

    public static Scene getScene() throws Exception {
        Parent root = FXMLLoader.load(RoomEditController.class
                .getResource("../../gui/fxml/room-edit-layout.fxml"));
        return new Scene(root);
    }

}
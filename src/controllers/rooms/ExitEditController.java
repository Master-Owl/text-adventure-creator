package controllers.rooms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ExitEditController {

    @FXML
    private Button cancelButton;

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
    private ChoiceBox<?> destinationRoomChoice;

    @FXML
    private CheckBox requireSkillCheckbox;

    @FXML
    private Button saveButton;

    @FXML
    private Label exitPageLabel;

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {

    }

}

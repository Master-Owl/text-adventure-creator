package controllers;

import controllers.rooms.AreaEditController;
import dataobjects.Area;
import dataobjects.items.BaseItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import model.Model;

public class MainLayoutController {

    public static MainLayoutController currentInstance;
    private ObservableList<Area> areas = FXCollections.observableArrayList();
    private ObservableList<BaseItem> items = FXCollections.observableArrayList();

    public void initPage() {
        projectName.setText(Model.instance.getProjectName());
        areaCountLabel.setText("Area Count: " + Model.instance.getAreas().size());
        itemCountLabel.setText("Item Count: " + Model.instance.getItems().size());

        areas.clear();
        areas.addAll(Model.instance.getAreas());
        areasList.setItems(areas);
        areasList.setCellFactory(list -> new Area());
        areasList.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                Area selectedArea = areasList.getSelectionModel().getSelectedItem();
                if (selectedArea != null) {
                    try {
                        MainController.instance.displayScene(AreaEditController.getScene());
                        AreaEditController.currentInstance.setArea(selectedArea);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        items.clear();
        items.addAll(Model.instance.getItems());
        itemsList.setItems(items);
        itemsList.setCellFactory(list -> new BaseItem());
        itemsList.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                BaseItem selectedItem = itemsList.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    try {
                        // TODO: Make ItemEditController and hook it up here
//                        MainController.instance.displayScene(RoomEditController.getScene());
//                        RoomEditController.currentInstance.setRoom(selectedItem);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        saveButton.setVisible(Model.instance.hasBeenSaved());
    }

    @FXML
    private Label npcCountLabel;

    @FXML
    private ListView<Area> areasList;

    @FXML
    private Button editProjectNameButton;

    @FXML
    private Button createAreaButton;

    @FXML
    private Label itemCountLabel;

    @FXML
    private Button createNpcButton;

    @FXML
    private TextField editProjectNameField;

    @FXML
    private ListView<?> npcsList;

    @FXML
    private ListView<BaseItem> itemsList;

    @FXML
    private Button createItemButton;

    @FXML
    private Label areaCountLabel;

    @FXML
    private Label projectName;

    @FXML
    private Button saveButton;

    @FXML
    void createNewArea(ActionEvent event) {
        try {
            MainController.instance.displayScene(AreaEditController.getScene());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void createNewItem(ActionEvent event) {

    }

    @FXML
    void createNewNpc(ActionEvent event) {

    }

    @FXML
    void saveProject(ActionEvent event) {
        Model.SaveProject();
    }

    @FXML
    void saveProjectOpaque(MouseEvent event) {
        saveButton.setOpacity(1);
    }

    @FXML
    void saveProjectTransparent(MouseEvent event) {
        saveButton.setOpacity(0.65);
    }

    @FXML
    void editProjectName(ActionEvent event) {
        projectName.setVisible(false);
        editProjectNameButton.setVisible(false);
        editProjectNameField.setText(projectName.getText());
        editProjectNameField.setVisible(true);
        editProjectNameField.setDisable(false);
        editProjectNameField.requestFocus();
        editProjectNameField.setOnKeyPressed(e -> {
            if(e.getCode().equals(KeyCode.ENTER)) {
                editProjectNameField.setVisible(false);
                editProjectNameField.setDisable(true);
                projectName.setText(editProjectNameField.getText());
                projectName.setVisible(true);
                editProjectNameButton.setVisible(true);
                Model.instance.setProjectName(editProjectNameField.getText());
            }
        });
    }

    public static Scene getScene() throws Exception {
        FXMLLoader loader = new FXMLLoader(MainLayoutController.class
                .getResource("../gui/fxml/main-page-layout.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        currentInstance = loader.getController();
        return new Scene(root);
    }

}

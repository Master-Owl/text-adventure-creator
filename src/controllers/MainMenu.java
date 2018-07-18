package controllers;

import controllers.rooms.RoomEditController;
import dataobjects.Room;
import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainMenu {

    private Stage window;
    private Menu fileMenu;
    private Menu createMenu;
    private Menu helpMenu;

    public List<Menu> getMenu() {
        List<Menu> menuList =  new ArrayList<>();
        menuList.add(fileMenu);
        menuList.add(createMenu);
        menuList.add(helpMenu);
        return menuList;
    }

    private MenuItem newProject;
    private MenuItem openProject;
    private MenuItem saveProject;
    private MenuItem saveProjectAs;
    private MenuItem preferences;
    private MenuItem createRoom;
    private MenuItem createNPC;
    private MenuItem helpInfo;
    private MenuItem createItem;

    // TODO: set up all the onAction events
    public void initMenu(Stage mainWindow) {
        window = mainWindow;
        fileMenu = new Menu("File");

        newProject = new MenuItem("New Project");
        newProject.setOnAction(this::newProjectDialog);

        openProject = new MenuItem("Open Project");
        openProject.setOnAction(this::openProjectDialog);

        saveProject = new MenuItem("Save Project");
        saveProject.setOnAction(this::saveProject);

        saveProjectAs = new MenuItem("Save Project As...");
        saveProjectAs.setOnAction(this::saveProjectAs);

        preferences = new MenuItem("Preferences");

        fileMenu.getItems().add(newProject);
        fileMenu.getItems().add(openProject);
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(saveProject);
        fileMenu.getItems().add(saveProjectAs);
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(preferences);


        createMenu = new Menu("Create");

        createRoom = new MenuItem("Room");
        createRoom.setOnAction(this::createRoom);

        createItem = new MenuItem("Item");

        createNPC = new MenuItem("NPC");

        createMenu.getItems().add(createRoom);
        createMenu.getItems().add(createItem);
        createMenu.getItems().add(createNPC);


        helpMenu = new Menu("Help");

        helpInfo = new MenuItem("About Text Adventure Maker");

        helpMenu.getItems().add(helpInfo);
    }

    void newProjectDialog(ActionEvent event) {
        System.out.println("So ya wanna make a new project, eh?");
    }

    void openProjectDialog(ActionEvent event) {
        System.out.println("So ya wanna open an ol' project, eh?");
    }

    void saveProject(ActionEvent event) {
        Model.SaveProject();
    }

    void saveProjectAs(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Text Adventure Project", "*.tap");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(window);

        if (file != null) {
            Model.instance.setSaveFilePath(file.getName());
            System.out.println("Path: " + file.getAbsolutePath());
            Model.SaveProject(file.getAbsolutePath());
        }
    }

    void createRoom(ActionEvent event) {
        try {
            MainController.instance.displayScene(RoomEditController.getScene());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

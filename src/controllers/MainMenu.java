package controllers;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainMenu {

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
    public void initMenu() {

        fileMenu = new Menu("File");

        newProject = new MenuItem("New Project");
        newProject.setOnAction(this::newProjectDialog);

        openProject = new MenuItem("Open Project");
        openProject.setOnAction(this::openProjectDialog);

        saveProject = new MenuItem("Save Project");

        saveProjectAs = new MenuItem("Save Project As...");

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
}
package dataobjects;

import javafx.scene.control.ListCell;
import model.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Room extends ListCell<Room> implements Serializable {
    private static final long serialVersionUID = 7325252892066585821L;

    private String roomName;
    private String roomDescription;
    private int roomId;
    private static int idCounter = -1;

    private HashMap<String, Exit> exits = new HashMap<>();
    private ArrayList<?> items = new ArrayList<>();
    private ArrayList<?> npcs = new ArrayList<>();
    private ArrayList<?> enemies = new ArrayList<>();

    public Room(){}
    public Room(String name, String description) {
        if (idCounter == -1)
            idCounter = Model.instance.getRoomIdCounter();
        roomName = name;
        roomDescription = description;
        roomId = idCounter++;
        Model.instance.addRoom(this);
    }

    public void addExit(String keyword, Exit exit) {
        exits.put(keyword, exit);
    }
    public Exit getExit(String keyword) {
        Exit exit = null;
        if (exits.containsKey(keyword))
            exit = exits.get(keyword);
        return exit;
    }

    public int roomId() { return roomId; }

    public String getRoomName() {
        return roomName;
    }
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomDescription() {
        return roomDescription;
    }
    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public void setExits(List<Exit> allExits) {
        exits.clear();
        for (Exit exit : allExits) {
            exits.put(exit.getKeyword(), exit);
        }
    }
    public void setItems(List<?> allItems){}
    public void setNpcs(List<?> allNpcs){}
    public void setEnemies(List<?> allEnemies){}

    public HashMap<String, Exit> getExits() {
        return exits;
    }

    public ArrayList<?> getItems() {
        return items;
    }

    public ArrayList<?> getNpcs() {
        return npcs;
    }

    public ArrayList<?> getEnemies() {
        return enemies;
    }

    // For the list cell display
    @Override
    protected void updateItem(Room room, boolean empty) {
        super.updateItem(room, empty);
        String displayText = null;

        if (room != null && !empty) {
            displayText = room.toString();
        }

        this.setText(displayText);
        this.setGraphic(null);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!obj.getClass().equals(this.getClass())) return false;
        return this.roomId == ((Room)obj).roomId;
    }

    @Override
    public String toString() {
        return this.roomName;
    }
}

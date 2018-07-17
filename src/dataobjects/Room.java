package dataobjects;

import javafx.scene.control.ListCell;
import model.Model;

import java.io.Serializable;
import java.util.HashMap;

public class Room extends ListCell<Room> implements Serializable {

    private String roomName;
    private String roomDescription;
    private int roomId;
    private static int idCounter = -1;

    private HashMap<String, Exit> exits = new HashMap<>();

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

    @Override
    protected void updateItem(Room room, boolean empty) {
        super.updateItem(room, empty);
        String displayText = null;

        if (room != null && !empty) {
            displayText = room.getRoomName();
        }

        this.setText(displayText);
        this.setGraphic(null);
    }

    @Override
    public boolean equals(Object obj) {
        if (!obj.getClass().equals(this.getClass())) return false;
        return this.roomId == ((Room)obj).roomId;
    }
}

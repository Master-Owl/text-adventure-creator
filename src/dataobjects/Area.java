package dataobjects;

import javafx.scene.control.ListCell;

import java.io.Serializable;
import java.util.ArrayList;

public class Area extends ListCell<Area> implements Serializable {
    private static final long serialVersionUID = 2624796104215123411L;

    private String areaName;
    private ArrayList<Room> rooms = new ArrayList<>();

    public Area(){}
    public Area(String name) {
        areaName = name;
    }

    public ArrayList<Room> getRooms() { return rooms; }
    public Room getRoom(int index) {
        if (rooms.size() > index)
            return rooms.get(index);
        return null;
    }
    public Room getRoomById(int roomId) {
        Room room = null;
        for(Room currentRoom : rooms) {
            if (currentRoom.roomId() == roomId) {
                room = currentRoom;
                break;
            }
        }
        return room;
    }
    public void addRoom(Room room) {
        if (!rooms.contains(room))
            rooms.add(room);
    }

    @Override
    protected void updateItem(Area area, boolean empty) {
        super.updateItem(area, empty);
        String displayText = null;

        if (area != null && !empty) {
            displayText = area.areaName + " (" + rooms.size() + ")";
        }

        this.setText(displayText);
    }

    @Override
    public String toString() {
        return areaName;
    }
}

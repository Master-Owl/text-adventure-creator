package dataobjects;

import javafx.scene.control.ListCell;
import model.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Area extends ListCell<Area> implements Serializable {
    private static final long serialVersionUID = 2624796104215123411L;

    private String areaName;
    private ArrayList<Room> rooms = new ArrayList<>();

    public Area(){}
    public Area(String name) {
        areaName = name;
        Model.instance.addArea(this);
    }

    public void setAreaName(String name) { areaName = name; }
    public String getAreaName() { return areaName; }

    public void addRoom(Room room) {
        if (!rooms.contains(room))
            rooms.add(room);
    }
    public void setRooms(Collection<Room> rooms) {
        this.rooms = new ArrayList<>(rooms);
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

    @Override
    protected void updateItem(Area area, boolean empty) {
        super.updateItem(area, empty);
        String displayText = null;

        if (area != null && !empty) {
            displayText = area.areaName + " (" + area.rooms.size() + ")";
        }

        this.setText(displayText);
    }

    @Override
    public String toString() {
        return areaName;
    }
}

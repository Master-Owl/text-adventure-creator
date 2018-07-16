package dataobjects;

import javafx.scene.control.ListCell;

public class Room extends ListCell<Room> {

    private String roomName;
    private String roomDescription;

    public Room(){}

    public Room(String name, String description) {
        roomName = name;
        roomDescription = description;
    }

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
}

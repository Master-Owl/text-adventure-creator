package dataobjects;

public class Room {

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
}

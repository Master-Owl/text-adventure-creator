package dataobjects;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Model;

import java.io.File;
import java.io.Serializable;

public class Exit extends ListCell<Exit> implements Serializable {
    private static final long serialVersionUID = 4820114285382968863L;

    private String keyword = "";
    private String exitDescription;
    private int toRoomId = -1;
    private int fromRoomId = -1;
    private boolean hidden = false;
    private Directions direction;

    public Exit() {}

    public Exit(String keyword, Room toRoom, Room fromRoom) {
        this.keyword = keyword;
        this.toRoomId = toRoom.roomId();
        this.fromRoomId = fromRoom.roomId();

        switch(keyword.toLowerCase()) {
            case "north":
                this.direction = Directions.NORTH;
                break;
            case "south":
                this.direction = Directions.SOUTH;
                break;
            case "east":
                this.direction = Directions.EAST;
                break;
            case "west":
                this.direction = Directions.WEST;
                break;
            default: this.direction = null;
        }
    }

    public String getKeyword() { return keyword; }
    public int getToRoomId() { return toRoomId; }
    public int getFromRoomId() { return fromRoomId; }

    public String getExitDescription() {
        return exitDescription;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public void setExitDescription(String exitDescription) {
        this.exitDescription = exitDescription;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setToRoom(Room toRoom) {
        this.toRoomId = toRoom.roomId();
    }

    @Override
    protected void updateItem(Exit exit, boolean empty) {
        super.updateItem(exit, empty);
        String displayText = null;

        if (exit != null && !empty) {
            displayText = exit.toString();
        }

        this.setGraphic(null);
        this.setText(displayText);
    }

    @Override
    public String toString() {
       return this.keyword + (this.hidden ? " (hidden)" : "") +
               " -> " + Model.instance.getRoom(this.toRoomId).getRoomName();
    }
}

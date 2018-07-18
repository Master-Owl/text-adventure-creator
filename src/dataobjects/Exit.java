package dataobjects;

import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import model.Model;

import java.io.Serializable;

public class Exit extends ListCell<Exit> implements Serializable {
    private static final long serialVersionUID = 4820114285382968863L;

    private String keyword = "";
    private int toRoomId = -1;
    private Directions direction;

    public Exit() {}

    public Exit(String keyword, Room toRoom) {
        this.keyword = keyword;
        this.toRoomId = toRoom.roomId();

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


    @Override
    protected void updateItem(Exit exit, boolean empty) {
        super.updateItem(exit, empty);
        String displayText = null;
        ImageView icon = null;

        if (exit != null && !empty) {
            displayText = exit.keyword + ": " +
                    Model.instance.getRoom(exit.toRoomId).getRoomName();
            if (exit.direction != null)
                icon = new ImageView(exit.direction.getIconPath());
        }

        this.setText(displayText);
        this.setGraphic(icon);
    }
}

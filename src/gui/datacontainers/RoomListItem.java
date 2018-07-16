package gui.datacontainers;

import dataobjects.Room;
import javafx.scene.control.ListCell;

public class RoomListItem extends ListCell<Room> {
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

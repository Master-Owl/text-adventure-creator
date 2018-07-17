package dataobjects;

import java.io.Serializable;

public class Exit implements Serializable {

    private String keyword = "";
    private int toRoomId = -1;

    public Exit() {}

    public Exit(String keyword, Room toRoom) {
        this.keyword = keyword;
        this.toRoomId = toRoom.roomId();
    }

    public String getKeyword() { return keyword; }
    public int getToRoomId() { return toRoomId; }
}

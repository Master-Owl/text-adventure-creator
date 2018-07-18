package dataobjects;

public enum Directions {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    @Override
    public String toString() {
        switch(this) {
            case NORTH: return "north";
            case SOUTH: return "south";
            case EAST: return "east";
            case WEST: return "west";
        }
        return "undefined";
    }

    public String getIconPath() {
        String path = "../gui/images/arrow-";
        switch(this) {
            case NORTH: return path + "up.png";
            case SOUTH: return path + "down.png";
            case EAST: return path + "right.png";
            case WEST: return path + "left.png";
        }
        return "";
    }
}

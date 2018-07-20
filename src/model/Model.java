package model;

import dataobjects.Area;
import dataobjects.Room;
import dataobjects.items.BaseItem;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

// The class that stores all current project information
public class Model implements Serializable {
    private static final long serialVersionUID = -8233812232699579588L;

    public static Model instance = new Model();
    public static String projectsDirectory = "projects/";

    private Model(){}
    private TreeMap<Integer, Room> rooms = new TreeMap<>();
    private TreeMap<Integer, BaseItem> items = new TreeMap<>();
    private ArrayList<Area> areas = new ArrayList<>();

    private int roomIdCounter = 0;
    private int itemIdCounter = 0;

    private String projectName;
    private String saveFilePath = null;


    // Project Metadata =========================================================
    public static void LoadProject(String filepath) {
        instance = ReadWrite.ReadFile(filepath);
        System.out.println("Loaded!");
    }
    public boolean hasBeenSaved() { return instance.saveFilePath != null; }
    public static void SaveProject() {
        if (instance.saveFilePath == null) {
            instance.saveFilePath = "~/Desktop/" + instance.projectName;
        }
        ReadWrite.WriteToFile(instance, instance.saveFilePath);
        System.out.println("Saved to " + instance.saveFilePath);
    }
    public static void SaveProject(String filepath) {
        instance.saveFilePath = filepath;
        SaveProject();
    }
    public static String[] GetProjectFileNames() {
        File folder = new File(ReadWrite.BaseDirectory + projectsDirectory);
        File[] fileList = folder.listFiles();
        if (fileList != null) {
            String[] fileNames = new String[fileList.length];
            for (int i = 0; i < fileList.length; ++i) {
                fileNames[i] = fileList[i].getName();
            }
            return fileNames;
        }
        else {
            return new String[0];
        }
    }
    public void setProjectName(String name) { projectName = name; }
    public String getProjectName() { return projectName; }
    public void setSaveFilePath(String name) { saveFilePath = name; }
    public String getSaveFilePath() { return saveFilePath; }
    // Project Metadata =========================================================


    // Rooms ====================================================================
    public int getRoomIdCounter() { return roomIdCounter; }
    public void addRoom(Room room) {
        rooms.put(room.roomId(), room);
        roomIdCounter++;
    }
    public void removeRoom(int roomId) {
        rooms.remove(roomId);
    }
    public Room getRoom(int roomId) {
        Room room = null;
        if (rooms.containsKey(roomId))
            room = rooms.get(roomId);
        return room;
    }
    public Collection<Room> getRooms() {
        return rooms.values();
    }
    // Rooms ====================================================================


    // Areas ====================================================================
    public void addArea(Area area) {
        areas.add(area);
    }
    public Area getArea(int index) {
        if (areas.size() > index)
            return areas.get(index);
        return null;
    }
    public ArrayList<Area> getAreas() { return areas; }
    // Areas ====================================================================


    // Items ====================================================================
    public int getItemIdCounter() { return itemIdCounter; }
    public void addItem(BaseItem item) {
        items.put(item.itemId(), item);
        itemIdCounter++;
    }
    public void removeItem(int itemId) { items.remove(itemId); }
    public BaseItem getItem(int itemId) {
        BaseItem item = null;
        if (rooms.containsKey(itemId))
            item = items.get(itemId);
        return item;
    }
    public Collection<BaseItem> getItems() { return items.values(); }
    // Items ====================================================================
}

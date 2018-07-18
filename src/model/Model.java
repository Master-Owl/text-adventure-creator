package model;

import dataobjects.Room;

import java.io.File;
import java.io.Serializable;
import java.util.Collection;
import java.util.TreeMap;

// The class that stores all current project information
public class Model implements Serializable {
    private static final long serialVersionUID = -8233812232699579588L;

    public static Model instance = new Model();
    public static String projectsDirectory = "projects/";

    private Model(){}
    private TreeMap<Integer, Room> rooms = new TreeMap<>();

    private String projectName;
    private String saveFilePath = null;
    private int roomIdCounter = 0;

    public static void LoadProject(String filepath) {
        instance = ReadWrite.ReadFile(filepath);
        System.out.println("Loaded!");
    }

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

    public int getRoomIdCounter() { return roomIdCounter; }
    public void addRoom(Room room) {
        rooms.put(room.roomId(), room);
        roomIdCounter++;
    }
    public void updateRoom(int roomId, Room room) {
        rooms.remove(roomId);
        rooms.put(roomId, room);
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

}

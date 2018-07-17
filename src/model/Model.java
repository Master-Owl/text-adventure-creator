package model;

import dataobjects.Room;

import java.io.File;
import java.io.Serializable;
import java.util.TreeMap;

public class Model implements Serializable {

    public static Model instance = new Model();
    public static String projectsDirectory = "projects/";

    private Model(){}
    private TreeMap<Integer, Room> rooms = new TreeMap<>();

    private String projectName;
    private String saveFileName;
    private int roomIdCounter = 0;

    public static void LoadProject(String filepath) {
        instance = ReadWrite.ReadFile(filepath);
        System.out.println("Loaded!");
    }

    public static void SaveProject() {
        ReadWrite.WriteToFile(instance,
                ReadWrite.BaseDirectory + projectsDirectory + instance.getProjectName());
        System.out.println("Saved!");
    }

    public static void SaveProject(String filepath) {
        ReadWrite.WriteToFile(instance, filepath);
        System.out.println("Saved to " + filepath);
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
    public void setSaveFileName(String name) { saveFileName = name; }
    public String getSaveFileName() { return saveFileName; }

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


}

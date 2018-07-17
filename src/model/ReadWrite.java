package model;

import java.io.*;

public class ReadWrite {

    public static String BaseDirectory = "./";

    public static void WriteToFile(Serializable object, String filepath) {
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;

        try {
            System.out.println("Filepath: " + filepath);
            fout = new FileOutputStream(filepath);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(object);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fout != null) {
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static <T> T ReadFile(String filepath) {
        T object = null;

        FileInputStream fin = null;
        ObjectInputStream ois = null;

        try {
            fin = new FileInputStream(filepath);
            ois = new ObjectInputStream(fin);
            object = (T) ois.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return object;
    }
}

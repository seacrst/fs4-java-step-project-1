package dev.flight_app.database;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public interface DB<I, E> {
    default void createDir(){
        File dir = new File("./DB");
        boolean created = dir.mkdir();
        if (created) {
            System.out.println("Directory created!");
        } else {
            System.out.println("Failed to create directory!");
        }
    }
    default void createFile(File file){
        File dir = new File("./DB");
        if (!dir.exists()) createDir();
        try{
            boolean success = file.createNewFile();
            if (success) {
                System.out.println("File created successfully!");
            } else {
                System.out.println("File already exists.");
            }
        }catch (IOException ex){
            System.out.println("An error occurred: " + ex.getMessage());
        }
    }
    default boolean write(Map<I, E> e, File file){
            if (!file.exists()) createFile(file);
            try (FileOutputStream fos = new FileOutputStream(file);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)){
                 oos.writeObject(e);
                 return true;
            } catch (IOException ex) {
                System.out.println("Error saving data: " + ex.getMessage());
                return false;
            }
    }
    default Map<I, E> read(File file){
        Map<I, E> list = new HashMap<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            list = (Map<I, E>) in.readObject();
            if(list == null){
                list = new HashMap<>();
            }
            System.out.println("Дані успішно завантажені!");
            return list;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error loading data: " + ex.getMessage());
        }
        return list;
    }
    boolean write(Map<I, E> e);
    Map<I, E> read();
}

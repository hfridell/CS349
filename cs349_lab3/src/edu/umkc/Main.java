package edu.umkc;

import javax.imageio.IIOException;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // Part A
        String[] treeTypes = {"Ash", "Maple", "Oak", "Apple", "Pine", "Poplar", "Hawthorn"};
        Forest f = new Forest();
        // Grow random trees
        for (int i = 0; i < 5; ++i) {
            f.growTree(treeTypes[new Random().nextInt(treeTypes.length)]);
        }
        for (Tree t : f) {
            System.out.println("Tree name: " + t.treeName);
        }

        System.out.println();
        // Part B
        for (int i = 0; i < 100; ++i) {
            try {
                ExceptionDemo.f();
            } catch (Exception e) {

            }

        }
        ExceptionDemo.printExceptionCount(System.out);

        System.out.println();
        // Part C
        PersistentStorage persist = new PersistentStorage("1 + ", "2");
        try {
            // Provide file gave ClassNotFoundException
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Lab3Object2.ser.txt"));
            oos.writeObject(persist);
            oos.close();

            persist = null;

            FileInputStream file = new FileInputStream("Lab3Object2.ser.txt");
            ObjectInputStream objInputStream = new ObjectInputStream(file);

            persist = (PersistentStorage) objInputStream.readObject();
            System.out.println("Instance Secret: " + persist.getInstanceSecret());
            System.out.println("Computed Secret: " + persist.getComputedSecret());

        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }


}

package com.bibtextingcompany.bibtexting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * Class for serializing and deserializing Objects of any serializable type
 * into/from a file.
 *
 * @author Jussi Viinikka
 */
public final class FileIO {

    private FileIO() {
    }

    /**
     * Method deserializes a file into an object. If the file doesn´t exist the
     * method calls saveObjectIntoFile() to create the file. Makes sense at
     * least in the Tilepuzzle implementation.
     *
     * @param o Object into which the file should be deserialized
     * @param fileName Name of the file from which the object should be
     * deserialized
     * @return The Object
     */
    public static Object loadFileIntoObject(Object o, String fileName) {
        ObjectInputStream objectInputStream = null;
        if (!new File(fileName).isFile()) {
            saveObjectIntoFile(o, fileName);
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);
            o = objectInputStream.readObject();
            fileInputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return o;
    }

    /**
     * Method for serializing an Object given as a parameter into a file the
     * name of which is given as a parameter
     *
     * @param o Object to be serialized
     * @param fileName Name of the file into which the object should be
     * serialized
     */
    public static void saveObjectIntoFile(Object o, String fileName) {
        ObjectOutputStream objectOutputStream = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(o);
            fileOutputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.flush();
                    objectOutputStream.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Clears out the contents of a file with the given filename.
     * 
     * @param filename Name of the file which content will be cleared
     */
    public static void clearFile(String filename) {
        ObjectOutputStream objectOutputStream = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.reset();
            fileOutputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

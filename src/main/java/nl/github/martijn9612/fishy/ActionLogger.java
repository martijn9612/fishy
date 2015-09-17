package nl.github.martijn9612.fishy;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by martijn on 17-9-15.
 */
public class ActionLogger {
    private static FileWriter fileWriter;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat();
    private int logFileNumber = 1;

    ActionLogger() {
        boolean fileExists;
        try {
            fileWriter = new FileWriter("log.txt", false);
        } catch (FileAlreadyExistsException FileExistsException) {
            System.out.println("log.txt already exists!");
            fileExists = true;
            while (fileExists) {
                try {
                    fileWriter = new FileWriter("log(" + logFileNumber + ").txt", false);
                    fileExists = false;
                } catch (FileAlreadyExistsException fileExistsException) {
                    System.out.println("log(" + logFileNumber + ").txt already exists!");
                    logFileNumber++;
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void logLine(String text, String className) {
        try {
            fileWriter.write("[ " + getTimeStamp() + "] - " + className + " - " + text + "\n");
        } catch (IOException e) {
            System.out.println("Unable to log to file!");
            e.printStackTrace();
        }
    }

    public void logLine(String text, String className, boolean isError) {
        try {
            if (isError) {
                fileWriter.write("[ERROR!][ " + getTimeStamp() + "] - " + className + " - " + text + "\n");
            } else {
                logLine(text, className);
            }
        } catch (IOException e) {
            System.out.println("Unable to log to file!");
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getTimeStamp() {
        return dateFormat.format(new Date());
    }
}

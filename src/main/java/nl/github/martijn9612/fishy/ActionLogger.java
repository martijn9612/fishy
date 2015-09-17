package nl.github.martijn9612.fishy;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by martijn on 17-9-15.
 */
public class ActionLogger {
    private static FileWriter fileWriter;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat();

    ActionLogger() {
        try {
            fileWriter = new FileWriter("log.txt", false);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void logLine(String text, String className) {
        try {
            fileWriter.write("[ " + getTimeStamp() + "] - " + text + " - " + className + "\n");
        } catch (IOException e) {
            System.out.println("Unable to log to file!");
            e.printStackTrace();
        }
    }

    public void logLine(String text, String className, boolean isError) {
        try {
            if (isError) {
                fileWriter.write("[ERROR!][ " + getTimeStamp() + "] - " + text + " - " + className + "\n");
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

package nl.github.martijn9612.fishy;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class logs to a file to observe the behaviour of a program.
 */
public class ActionLogger {
    private static FileWriter fileWriter;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * Instantiates a new FileWriter to log to a file, each time overwriting the old file.
     */
    ActionLogger() {
        try {
            fileWriter = new FileWriter("log.txt", false);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Logs one line to file to log in.
     * @param text Text to log
     * @param className Class the log is called in
     */
    public void logLine(String text, String className) {
        try {
            fileWriter.write("[ " + getTimeStamp() + "] - " + text + " - " + className + "\n");
        } catch (IOException e) {
            System.out.println("Unable to log to file!");
            e.printStackTrace();
        }
    }

    /**
     * Logs one line to file to log in.
     * @param text Text to log
     * @param className Class the log is called in
     * @param isError Adds additional error text if set to true
     */
    public void logLine(String text, String className, boolean isError) {
        try {
            if (isError) {
                fileWriter.write("[ERROR!][ " + getTimeStamp() + "] - " + text
                        + " - " + className + "\n");
            } else {
                logLine(text, className);
            }
        } catch (IOException e) {
            System.out.println("Unable to log to file!");
            e.printStackTrace();
        }
    }

    /**
     * Closes the filewriter so that the lines are written, otherwise all the logs are lost.
     */
    public void close() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the current time to specify the moment the log was made.
     * @return String with the current time
     */
    private String getTimeStamp() {
        return dateFormat.format(new Date());
    }
}

package nl.github.martijn9612.fishy.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import nl.github.martijn9612.fishy.Main;

/**
 * This class logs to a file to observe the behaviour of a program.
 */
public class ActionLogger {
    public static final boolean APPEND = true;
    private FileWriter fileWriter;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * Instantiates a new FileWriter to log to a file, each time overwriting the old file.
     */
    public ActionLogger() {
        try {
            fileWriter = new FileWriter("log.txt", false);
            fileWriter.close();
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
		logLine(text, className, false);
	}

    /**
     * Logs one line to file to log in.
     * @param text Text to log
     * @param className Class the log is called in
     * @param isError Adds additional error text if set to true
     */
	public void logLine(String text, String className, boolean isError) {
		try {
            fileWriter = new FileWriter("log.txt", APPEND);
            String logMessage = "[ " + getTimeStamp() + "] - " + text + " - " + className + "\n";
			if (isError) {
				logMessage = "[ERROR!]" + logMessage;
			}
			if (Main.DEBUG_MODE) {
				System.out.print(logMessage);
			}
			fileWriter.write(logMessage);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Unable to log to file!");
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

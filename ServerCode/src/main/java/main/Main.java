package main;

import server.Server;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


/*
 * Main class to start the server on port 45000 and listen for incoming connections from clients
 */
public class Main {

    /**
     * static logger
     */
    public static final Logger logger = Logger.getLogger("serverLog");
    /**
     * Level of severity to be logged
     */
    public static Level logLevel;
    /**
     * File path in which the log will be written on disk
     */
    public static String logPath = "./log/" + LocalDateTime.now().getYear() + "-" + LocalDateTime.now().getMonth() + "-serverLog.log";

    public static void main(String[] args) {
    	Server server = new Server(45000);
        server.startServer();
    }

    /**
     * static method to create the log file on the disk
     */
    public static void createLogFile() {
        // create log file if it doesn't exist
        File f = new File(logPath);
        if (!f.exists()) {
            try {
                File firstDirectory = new File("./log/");
                if (!firstDirectory.exists())
                    firstDirectory.mkdir();
                f.createNewFile();
            } catch (IOException e) {
                System.err.println("Log file with path " + logPath + " was not created. Logs won't be written on disk.");
            }
        }
        try {
            FileHandler fileHandler = new FileHandler(logPath, true);
            //Adds the file handle
            logger.addHandler(fileHandler);
            //use a simple formatter
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.setLevel(logLevel);
            logger.log(Level.INFO, "Log registered in " + logPath);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Exception thrown", e.getMessage());
            e.printStackTrace();
        }
    }
}

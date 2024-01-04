package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.print.attribute.standard.Media;

import static main.Main.createLogFile;
import static main.Main.logger;


/*
 * Server class to handle the server side of the application 
 *  
 */
public class Server {
    // Server port
    private int serverPort;

    private static Server serverInstance  = null;

    private ArrayList<Music> storedSongs = new ArrayList<>(50);
    private ArrayList<ClientConnected> storedCLients = new ArrayList<>(50);


    /*
     * Private constructor for the server class to prevent multiple instances
     */
    private Server() {
    }

    /*
     * Public constructor for the server class to set the server port
     * @param serverPort the port on which the server will listen for incoming connections
     */
    public Server(int serverPort) {
        this.serverPort = serverPort;
    }

    /*
     * Method to get the server instance and create one if it doesn't exist
     * @return the server instance
     */
    public static Server getServer(){
        if (serverInstance == null) {
            serverInstance = new Server();
        }
        return serverInstance;
    }

    /*
     * Method to start the server and listen for incoming connections
     */
    public void startServer() {

        // ServerSocket for listening to incoming connections
        ServerSocket serverSocket;

        // check if the log file exists and create it if it doesn't
        createLogFile();


        try {
            logger.log(Level.INFO, "Server is starting on port " + serverPort);
            serverSocket = new ServerSocket(serverPort);
            logger.log(Level.INFO,"Server is now listening for incoming connections...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                // Creste a new ClientConnected object to store the client's information

                new Thread(new ServerClientInteractions(clientSocket)).start();
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            System.exit(1);
        }
    }

    /*
     * Getter for the stored songs
     * @return the stored songs
     */
    public ArrayList<Music> getStoredSongs() {
        return storedSongs;
    }

    /*
     * Getter for the stored clients
     * @return the stored clients
     */
    public ArrayList<ClientConnected> getStoredClients() {
        return storedCLients;
    }


}

package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;

import javax.print.attribute.standard.Media;


/*
 * Server class to handle the server side of the application
 * 
 */
public class Server {
    // Server port
    private int serverPort;

    private static Server serverInstance  = null;

    private ArrayList<Music> storedSongs = new ArrayList<>(50);

    /*
     * Private constructor for the server class to prevent multiple instances
     */
    private Server() {
    }

    /*
     * Public constructor for the server class to set the server port
     */
    public Server(int serverPort) {
        this.serverPort = serverPort;
    }

    /*
     * Method to get the server instance and create one if it doesn't exist
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

        try {
            System.out.println("Server is starting on port " + serverPort);
            serverSocket = new ServerSocket(serverPort);
            System.out.println("Server is now listening for incoming connections...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ServerClientInteractions(clientSocket)).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Getter for the stored songs
     * @return the stored songs
     */
    public ArrayList<Music> getStoredSongs() {
        return storedSongs;
    }

}

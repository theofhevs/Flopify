package PeerToPeer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;

import javax.print.attribute.standard.Media;

import client.Music;


/*
 * Server class to handle the server side of the application
 * 
 */
public class ClientConnection {
    // Server port
    private int listeningPort;

    private static ClientConnection ClientConnectionInstance  = null;

    private ArrayList<Music> storedSongs = new ArrayList<>(50);

    /*
     * Private constructor for the server class to prevent multiple instances
     */
    private ClientConnection() {
    }

    /*
     * Public constructor for the server class to set the server port
     */
    private ClientConnection(int serverPort) {
        this.listeningPort = serverPort;
    }

    /*
     * Method to get the server instance and create one if it doesn't exist
     */
    public static ClientConnection getClientConnection(int serverPort){
        if (ClientConnectionInstance == null) {
            ClientConnectionInstance = new ClientConnection(serverPort);
        }
        return ClientConnectionInstance;
    }

    /*
     * Method to start the server and listen for incoming connections
     */
    public void startServer() {

        // ServerSocket for listening to incoming connections
        ServerSocket listeningSocket;

        try {
            System.out.println("Server is starting on port " + listeningPort);
            listeningSocket = new ServerSocket(listeningPort);
            System.out.println("Server is now listening for incoming connections...");

            while (true) {
                Socket clientSocket = listeningSocket.accept();
                new Thread(new ClientInteractions(clientSocket)).start();
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

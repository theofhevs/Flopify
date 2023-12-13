package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;


public class Server {
    // Server port
    private int serverPort;

    private static Server serverInstance  = null;

    // Private constructor to prevent instantiation
    private Server() {
    }

    public Server(int serverPort) {
        this.serverPort = serverPort;
    }

    // singleton Pattern
    public static Server getServer(){
        if (serverInstance == null) {
            serverInstance = new Server();
        }
        return serverInstance;
    }

    // Method to start the server
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


}

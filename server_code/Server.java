package server_code;

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
                handleClientConnection(clientSocket);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void menu(PrintWriter pOut, BufferedReader bufferIn, Socket clientSocket) throws Exception{
        pOut.println("Commands available:\n1\tList available songs\n2\tShare a song\n3\tDisconnect and close");
        int command = Integer.parseInt(bufferIn.readLine());
        switch (command) {
            case 1:
                listSongs(pOut, bufferIn, clientSocket);
                break;
            case 2:
                clientSharesMedia(pOut, bufferIn, clientSocket);
                break;
            case 3:
                disconnect(clientSocket);
        }
    }
    private void disconnect(Socket clientSocket) throws IOException {
        // Close the client connection when done
        clientSocket.close();
        System.out.println("Client disconnected: " + clientSocket.getInetAddress());
    }

    private void clientSharesMedia(PrintWriter pOut, BufferedReader bufferIn, Socket clientSocket) throws Exception {
        System.out.println("client shares media");

        menu(pOut, bufferIn, clientSocket);
    }

    private void listSongs(PrintWriter pOut, BufferedReader bufferIn, Socket clientSocket) throws Exception {

        System.out.println("list songs");
        
        menu(pOut, bufferIn, clientSocket);
    }

    // Method to handle a client connection
    private void handleClientConnection(Socket clientSocket) {
        try {
            System.out.println("Client connected: " + clientSocket.getInetAddress());
            // buffIn is used to read data from the client
            BufferedReader buffIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // pOut is used to send data to the client
            PrintWriter pOut = new PrintWriter(clientSocket.getOutputStream(), true);

            // Perform server-side logic as needed
            menu(pOut, buffIn, clientSocket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Example usage: Create a Server instance and start it
        Server server = new Server(45000);
        server.startServer();
    }
}

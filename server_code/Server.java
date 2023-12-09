package server_code;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    // Server port
    private int serverPort;

    // ServerSocket for listening to incoming connections
    private ServerSocket serverSocket;

    public Server(int serverPort) {
        this.serverPort = serverPort;
    }

    // Method to start the server
    public void startServer() {
        try {
            System.out.println("Server is starting on port " + serverPort);
            serverSocket = new ServerSocket(serverPort);
            System.out.println("Server is now listening for incoming connections...");

            // Accept incoming connections
            while (true) {
                Socket clientSocket = serverSocket.accept();
                handleClientConnection(clientSocket);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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

            // Close the client connection when done
            clientSocket.close();
            System.out.println("Client disconnected: " + clientSocket.getInetAddress());

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

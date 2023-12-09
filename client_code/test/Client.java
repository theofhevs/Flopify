package client_code.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    // Port of the server
    private int serverPort;

    // IP of the client
    private InetAddress ipAddress;

    //Name of the Server
    private String serverName;

    // client Socket
    private Socket clientSocket;


    // Client constructor in the case that the client specify the server's port
    public Client(String serverName, int serverPort, InetAddress localAddress) {
        this.serverName = serverName;
        this.serverPort = serverPort;
        ipAddress = localAddress;
    }


    // Client constructor in the case that we use the basic server's port
    public Client(String serverName, InetAddress localAddress) {
        this.serverName = serverName;
        this.serverPort = 45000;
        ipAddress = localAddress;


    }

    // method to connect the client to the server
    public void connectToServer() throws Exception {
        System.out.println("connexion to the server " + serverName + " on port " + serverPort);
        clientSocket = new Socket(serverName, serverPort);
        System.out.println("connexion Succeded");
        // buffIn is used to read the data that are received by the server
        BufferedReader buffIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        // pOut is used to send data to the server
        PrintWriter pOut = new PrintWriter(clientSocket.getOutputStream(), true);

    }

    public static void main(String[] args) {
        try {
            // Exemple d'utilisation du Client avec un port spécifié
            //Client client1 = new Client("localhost", 45001, InetAddress.getLocalHost());
            //client1.connectToServer();

            // Exemple d'utilisation du Client avec le port par défaut (45000)
            Client client2 = new Client("localhost", InetAddress.getLocalHost());
            client2.connectToServer();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}









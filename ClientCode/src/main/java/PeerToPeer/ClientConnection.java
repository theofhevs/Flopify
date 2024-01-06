package PeerToPeer;
import java.net.*;

/*
 * Server class to handle the server side of the application
 * 
 */
public class ClientConnection {
    // Server port
    private int listeningPort;

    private static ClientConnection ClientConnectionInstance  = null;


    /*
     * Private constructor for the server class to prevent multiple instances
     */
    private ClientConnection() {
    }

    /*
     * Public constructor for the server class to set the server port
     * @param serverPort : port of the server
     */
    private ClientConnection(int serverPort) {
        this.listeningPort = serverPort;
    }

    /*
     * Method to get the server instance and create one if it doesn't exist
     * @param serverPort : port of the server
     * @return the server instance
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
            listeningSocket = new ServerSocket(listeningPort);

            while (true) {
                Socket clientSocket = listeningSocket.accept();
                new Thread(new ClientInteractions(clientSocket)).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

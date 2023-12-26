package PeerToPeer;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientConnection implements Runnable{
    /**
     * Ip address of the server
     */
    // recup le port du client qui est stocké dans la arraylist Music
    private  int port;

    private InetAddress localAddress;
    private String musicPath;

    public ClientConnection(InetAddress localAddress,String musicPath,int port) {
        this.port = port;
        this.musicPath = musicPath;
        this.localAddress = localAddress;
    }

    @Override
    public void run() {
        // ServerSocket for listening to incoming connections
        ServerSocket serverSocket;

        try {
            System.out.println("Establishing a connection with " + localAddress);
            serverSocket = new ServerSocket(port, 5, localAddress);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientInteractions(clientSocket,musicPath)).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}

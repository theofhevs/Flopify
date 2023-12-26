package PeerToPeer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.Buffer;

public class ClientInteractions implements Runnable {
    
    private Socket clientSocket;
    private String musicPath;

    public ClientInteractions(Socket clientSocket, String musicPath) {
        this.clientSocket = clientSocket;
        this.musicPath = musicPath;
    }

    @Override
    public void run() {
        try {
            System.out.println("Establishing a connection with " + clientSocket.getPort());
            // buffIn is used to read data from the client
            BufferedReader buffIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            stream(buffIn);

        } catch (Exception e) {
            
        }
    }
    public void stream(BufferedReader buffIn){


        
    }
    
}

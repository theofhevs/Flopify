package PeerToPeer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.Buffer;

public class ClientInteractions implements Runnable {
    
    private Socket clientSocket;
    public ClientInteractions(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            // buffIn is used to read data from the client
            BufferedReader buffIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            stream(buffIn);

        } catch (Exception e) {
            
        }
    }
    public void stream(BufferedReader buffIn){


        
    }
    
}

package PeerToPeer;

import java.io.*;
import java.net.Socket;
import java.nio.Buffer;

/*
 * Class to handle the interactions between the client and the server
 */
public class ClientInteractions implements Runnable {

    private Socket clientSocket;
    private String musicPath;

    /*
     * Constructor of the class
     * @param clientSocket : socket of the client
     */
    public ClientInteractions(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    /*
     * Method that will run the thread and handle the interactions between the client and the server
     */
    @Override
    public void run() {
        try {
            System.out.println("Establishing a connection with " + clientSocket.getPort());

            // get music path
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String musicPath = in.readLine();

            this.musicPath = musicPath;

            stream();

        } catch (Exception e) {

        }
    }

    public void stream() {
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(musicPath));
            DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
            OutputStream outStream = clientSocket.getOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead;
            

            // Wait 1 second to be sure that the client is ready to receive the music
            Thread.sleep(1000);

            // Send the music to the client
            while ((bytesRead = bis.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            outStream.flush();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

        }

    }

}

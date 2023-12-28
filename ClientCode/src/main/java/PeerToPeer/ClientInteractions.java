package PeerToPeer;

import java.io.*;
import java.net.Socket;
import java.nio.Buffer;

public class ClientInteractions implements Runnable {

    private Socket clientSocket;
    private String musicPath;

    public ClientInteractions(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

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
        // envoi du stream

        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(musicPath));
            DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
            OutputStream outStream = clientSocket.getOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead;
            

            // Attendre un court instant pour permettre au client de se préparer à recevoir
            // les données audio
            Thread.sleep(1000);

            // Envoyer le contenu du fichier
            while ((bytesRead = bis.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            outStream.flush();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

        }

    }

}

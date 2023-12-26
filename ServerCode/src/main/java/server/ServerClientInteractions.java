package server;
import commands.ClientSharesMediaCommand;
import commands.Command;
import commands.DisconnectCommand;
import commands.ListSongs;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/*
 * Class to handle the interactions between the server and the client and the server-side logic
 *
 */
public class ServerClientInteractions implements Runnable{
    private final Socket clientSocket;

    private Server server;

    /*
     * Constructor for the server client interactions
     * @param clientSocket the client socket
     */
    public ServerClientInteractions(Socket clientSocket) {
        this.clientSocket = clientSocket;
        server = Server.getServer();
    }

    /* 
     * Run method to handle the client connection and start the server-side logic
     */
    @Override
    public void run() {
        handleClientConnection(clientSocket);
    }


    /*
     * Menu method to handle the client commands 
     * @param pOut the output stream to the client
     * @param bufferIn the input stream from the client
     * @param clientSocket the client socket
     */
    public void menu(PrintWriter pOut, BufferedReader bufferIn, Socket clientSocket) throws Exception{
        pOut.println("Commands available:\n1\tList available songs\n2\tShare a song\n3\tDisconnect and close");
        int number = Integer.parseInt(bufferIn.readLine());
        //create a array for the Command pattern
        Command command = null;
        switch (number) {
            case 1:
                command = new ListSongs(pOut, bufferIn, clientSocket,server);
                break;
            case 2:
                command = new ClientSharesMediaCommand(pOut, bufferIn, clientSocket, server);
                break;
            case 3:
                command = new DisconnectCommand(pOut, bufferIn, clientSocket, server);
                break;
                // --------------------- Send Audio ---------------------
            /*case 4:
                sendMusic("C:\\Users\\theof\\Desktop\\ryanGosling.mp3", clientSocket);
                break;*/
                // --------------------- Send Audio ---------------------
        }
        command.execute(this);
    }

    // --------------------- Send Audio ---------------------

   /* public void sendMusic(String musicFilePath, Socket clientSocket) {
        try (
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(musicFilePath));
                DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
                OutputStream outStream = clientSocket.getOutputStream()) {

            byte[] buffer = new byte[4096];
            int bytesRead;

            // Envoyer le nom du fichier
            String fileName = new File(musicFilePath).getName();
            dos.writeUTF(fileName);
            dos.writeUTF("Playing music");

            // Attendre un court instant pour permettre au client de se préparer à recevoir les données audio
            Thread.sleep(1000);

            // Envoyer le contenu du fichier
            while ((bytesRead = bis.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            outStream.flush();


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }*/
    // --------------------- Send Audio ---------------------






    /*
     * Method to handle the client connection and start the server-side logic
     * @param clientSocket the client socket
     */
    private void handleClientConnection(Socket clientSocket) {
        try {
            System.out.println("Client connected: " + clientSocket.getInetAddress() + " : " + clientSocket.getPort());

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
}



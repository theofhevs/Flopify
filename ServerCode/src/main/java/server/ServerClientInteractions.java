package server;

import commands.*;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.UUID;

import static main.Main.logger;

/*
 * Class to handle the interactions between the server and the client and the server-side logic
 *
 */
public class ServerClientInteractions implements Runnable {
    private final Socket clientSocket;

    private Server server;

    private  ClientConnected clientConnected;


    public ClientConnected getClientConnected() {
        return clientConnected;
    }

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
    public void menu(PrintWriter pOut, BufferedReader bufferIn, Socket clientSocket) throws Exception {
        pOut.println("=====Commands available=====\n1\tList available songs\n2\tShare a song\n3\tDisconnect and close\n4\tWho is connected?");
        int number = Integer.parseInt(bufferIn.readLine());
        //create a array for the Command pattern
        Command command = null;
        switch (number) {
            case 1:
                command = new ListSongs(pOut, bufferIn, clientSocket, server);
                break;
            case 2:
                command = new ClientSharesMediaCommand(pOut, bufferIn, clientSocket, server);
                break;
            case 3:
                command = new DisconnectCommand(pOut, bufferIn, clientSocket, server);
                break;
            case 4:
                command = new ListClients(pOut, bufferIn, clientSocket, server);
                break;

        }
        command.execute(this);
    }


    /*
     * Method to handle the client connection and start the server-side logic
     * @param clientSocket the client socket
     */
    private void handleClientConnection(Socket clientSocket) {
        try {
            logger.log(Level.INFO, "Client  " + clientSocket.getInetAddress().toString() + " : " + clientSocket.getPort() + " is connected with the server");

            // buffIn is used to read data from the client
            BufferedReader buffIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // pOut is used to send data to the client
            PrintWriter pOut = new PrintWriter(clientSocket.getOutputStream(), true);


            int initialPort = Integer.parseInt(buffIn.readLine());

            this.clientConnected = new ClientConnected(clientSocket.getInetAddress().toString().replace("/",""), clientSocket.getPort(), initialPort,clientSocket);
            String clientName = UUID. randomUUID().toString();
            clientConnected.setClientName(clientName);
            server.getStoredClients().add(clientConnected);

            // Perform server-side logic as needed
            menu(pOut, buffIn, clientSocket);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Connection with " + clientSocket.getInetAddress().toString() + " lost");
            server.getStoredClients().remove(clientConnected);
            logger.log(Level.INFO, "Client  " + clientSocket.getInetAddress().toString() + " : " + clientSocket.getPort() + " is removed from the server list");
        }
    }


}



package commands;

import server.Server;
import server.ServerClientInteractions;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * Command to list all the clients connected to the server
 */
public class ListClients implements  Command{
    private ServerClientInteractions serverClientInteractions;
    private PrintWriter pOut;
    private BufferedReader buffIn;
    private Socket clientSocket;
    private Server server;


    /*
     * Constructor for the command to list all the clients
     * @param pOut the output stream to the client
     * @param bufferIn the input stream from the client
     * @param clientSocket the socket of the client
     * @param server the server to execute the command on
     */
    public ListClients(PrintWriter pOut, BufferedReader buffIn, Socket clientSocket, Server server) {
        this.pOut = pOut;
        this.buffIn = buffIn;
        this.clientSocket = clientSocket;
        this.server = server;
    }


    public void execute(ServerClientInteractions serverClientInteractions) {
        this.serverClientInteractions = serverClientInteractions;

        pOut.println(server.getStoredClients().size());


        //print all informations of the array list of songs
        for (int i = 0; i < server.getStoredClients().size(); i++) {
            pOut.println(server.getStoredClients().get(i).getClientName());

        }

        try {
            serverClientInteractions.menu(pOut, buffIn, clientSocket);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}

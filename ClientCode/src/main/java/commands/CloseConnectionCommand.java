package commands;

import java.io.BufferedReader;
import java.io.PrintWriter;

import client.Client;

/**
 * Command to close the connection with the server
 *
 */
public class CloseConnectionCommand implements Command {
    private final PrintWriter pOut;
    private final BufferedReader buffIn;
    private Client client;


    public CloseConnectionCommand(PrintWriter pOut, BufferedReader buffIn) {
        this.pOut = pOut;
        this.buffIn = buffIn;
    }
    
    /*
     * Method that will close the connection with the server
     * @param client : client that will close the connection
     */
    @Override
    public void execute(Client client) {
        this.client = client;
        pOut.println(client.getIpAddress());
        
        System.exit(0); //used to terminate the client "server" thread as well
    }
}
package commands;

import client.Client;

import java.io.BufferedReader;
import java.io.PrintWriter;

/*
 * Command to display the clients connected to the server
 */
public class DisplayClients implements Command{

    private final PrintWriter pOut;
    private final BufferedReader buffIn;
    private Client client;

    /*
     * Constructor of the DisplayClients class
     * @param pOut : output stream of the client
     * @param buffIn : input stream of the client
     */
    public DisplayClients(PrintWriter pOut, BufferedReader buffIn) {
        this.pOut = pOut;
        this.buffIn = buffIn;
    }


    /*
     * Method that will execute the command on the client and display the clients connected to the server
     * @param client : client that will execute the command
     *
     */
    @Override
    public void execute(Client client) {
        this.client = client;


        try {
            int nbClients = Integer.parseInt(buffIn.readLine());
            System.out.println("There are " + nbClients + " clients connected to the server");
            for (int i = 0; i < nbClients; i++) {
                System.out.println("Client " + (i + 1) + ":");
                System.out.println("IP address: " + buffIn.readLine());
                System.out.println("Port: " + buffIn.readLine());
            }

            client.menu(buffIn, pOut);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

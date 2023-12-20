package commands;

import client.Client;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * Command to display available medias
 *
 */
public class DisplayAvailableSongsCommand implements Command {
    private final PrintWriter pOut;
    private final BufferedReader buffIn;
    private Client client;

    /*
     * Constructor of the command that will set the input and output streams of the client
     * @param pOut : output stream of the client
     * @param buffIn : input stream of the client
     */
    public DisplayAvailableSongsCommand(PrintWriter pOut, BufferedReader buffIn) {
        this.pOut = pOut;
        this.buffIn = buffIn;
    }

    /*
     * Method that will execute the command on the client and display the available medias
     * @param client : client that will execute the command
     */
    @Override
    public void execute(Client client) {
        this.client = client;
        try {
            System.out.println();
            int numberOfSongs = Integer.parseInt(buffIn.readLine());
            
            // If there is no available music, display a message and call the menu
            if(numberOfSongs == 0){
                System.out.println("There is no available music");
                client.menu(buffIn, pOut); // Call the menu after executing the command
                return;
            }
            for (int i = 1; i <= numberOfSongs; i++) {
                String musicName = buffIn.readLine();
                String ipAddress = buffIn.readLine();

                System.out.println(i + ": " + musicName + ", IP: " + ipAddress);
            }
            client.menu(buffIn, pOut); // Call the menu after executing the command
        } catch (Exception e) {
        }
    }
}

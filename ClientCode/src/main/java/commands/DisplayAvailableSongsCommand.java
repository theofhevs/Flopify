package commands;

import PeerToPeer.ClientConnection;
import client.Client;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * Command to display available medias
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
            if (numberOfSongs == 0) {
                System.out.println("There is no available music");
                client.menu(buffIn, pOut); // Call the menu after executing the command
                return;
            }

            for (int i = 1; i <= numberOfSongs; i++) {
                String musicName = buffIn.readLine();
                String port = buffIn.readLine();
                System.out.println(i + ": " + musicName + ", Port: " + port);
            }

            System.out.println(buffIn.readLine());
            Scanner sc = new Scanner(System.in);
            char c = sc.next().charAt(0);

            while (c != 'n' && c != 'y') {
                System.out.print("You must write y or n : ");
                c = sc.next().charAt(0);
            };

            pOut.println(c);
            if (c == 'n') {
                client.menu(buffIn, pOut); // Call the menu after executing the command
                return;
            }


            System.out.println(buffIn.readLine());
            int songNumber = sc.nextInt();

            while ( songNumber > numberOfSongs || songNumber < 0) {
                System.out.print("Please write a number between : 1 and "+numberOfSongs+" : ");
                songNumber = sc.nextInt();
            };
            pOut.println(songNumber);


            String musicPath = buffIn.readLine();
            int portToConnect = Integer.parseInt(buffIn.readLine());
            System.out.println("Music path : "+musicPath);
            System.out.println("Port to connect : "+portToConnect);

            ClientConnection clientConnection = new ClientConnection(InetAddress.getLocalHost(),musicPath,portToConnect);
            clientConnection.run();



        } catch (Exception e) {
        }
    }
}

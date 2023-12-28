package commands;

import PeerToPeer.ClientConnection;
import client.Client;
import javazoom.jl.player.Player;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
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
            System.out.println("Port to connect : " + portToConnect); //TODO: USE THIS PORT TO CONNECT TO THE LISTENING SERVER    


            // connect to the listening server to stream the musics
            Socket listeningSocket = new Socket("127.0.0.1",40001); //TODO: change the port to use the correct one
            // TODO: the port to connect is not the right port is the port given for the connection between the client and the server not the listening server port
            // create the sender to send information to the server
            PrintWriter out = new PrintWriter(listeningSocket.getOutputStream(), true);
            //send the path for the musics to stream
            out.println(musicPath);

            //TIPS => attendre une info du serveur pour etre sur

          
            // Lisen the stream of the listening server
            InputStream is = listeningSocket.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            try {
                //create the player 
                Player player = new Player(bis);
                //play the stream
                player.play();

            } catch (Exception e) {
                e.printStackTrace();
            }

          


        } catch (Exception e) {
        }
    }
}

package commands;

import server.Server;
import server.ServerClientInteractions;

import javax.swing.table.TableRowSorter;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * Command to list the songs on the server
 *
 */
public class ListSongs implements Command{
    private ServerClientInteractions serverClientInteractions;
    private  PrintWriter pOut;
    private  BufferedReader buffIn;
    private  Socket clientSocket;
    private Server server;
    

    /*
     * Constructor for the command to list songs
     * @param pOut the output stream to the client
     * @param bufferIn the input stream from the client
     */
    public ListSongs(PrintWriter pOut, BufferedReader bufferIn, Socket clientSocket, Server server) {
        this.pOut = pOut;
        this.buffIn = bufferIn;
        this.clientSocket = clientSocket;
        this.server = server;
    }

    /*
     * Execute the command on the server to list songs
     * @param serverClientInteractions the server to execute the command on
     */
    @Override
    public void execute(ServerClientInteractions serverClientInteractions) {
        this.serverClientInteractions = serverClientInteractions;

        System.out.println("Client lists songs");
        try {
            pOut.println(server.getStoredSongs().size());

            //print all informations of the array list of songs
            for (int i = 0; i < server.getStoredSongs().size(); i++) {
                pOut.println(server.getStoredSongs().get(i).getMusicName());
                pOut.println(server.getStoredSongs().get(i).getPort());
            }

            if (server.getStoredSongs().size() == 0){
                serverClientInteractions.menu(pOut, buffIn, clientSocket); // Call the menu after executing the command
            }
            else {
                pOut.println("Would you like to stream a song? (y/n) : ");

            }


            char ClientAnswer = buffIn.readLine().charAt(0);
            if (ClientAnswer == 'n') {
                serverClientInteractions.menu(pOut, buffIn, clientSocket); // Call the menu after executing the command
            }

            else if (ClientAnswer == 'y') {
                pOut.println("Enter the number of the song you want to stream : ");
                int songNumber = Integer.parseInt(buffIn.readLine());
                for (int i = 0; i < server.getStoredSongs().size(); i++) {
                    if ( i+1 == songNumber) {
                        pOut.println(server.getStoredSongs().get(i).getmusicPath());
                        pOut.println(server.getStoredSongs().get(i).getInitialPort());
                    }

                }

            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

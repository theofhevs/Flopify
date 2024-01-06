package commands;

import server.ClientConnected;
import server.Music;
import server.Server;
import server.ServerClientInteractions;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;

import static main.Main.logger;

/*
 * Command to disconnect the client from the server
 */
public class DisconnectCommand implements Command {
    private Socket clientSocket;
    private Server server;
    private BufferedReader buffIn;

    /*
     * Constructor for the command to disconnect the client
     * @param pOut the output stream to the client
     * @param bufferIn the input stream from the client
     * @param clientSocket the socket of the client
     * @param server the server to execute the command on
     */
    public DisconnectCommand(PrintWriter pOut, BufferedReader buffIn, Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
        //this.pOut = pOut;
        this.buffIn = buffIn;
    }

    /*
     * Execute the command on the server to disconnect the client
     * @param serverClientInteractions the server to execute the command on
     */
    @Override
    public void execute(ServerClientInteractions serverClientInteractions) {
        try {
            int portToCompare = Integer.parseInt(buffIn.readLine());
            removeSongs(portToCompare);
            removeClients(portToCompare);
            logger.log(Level.INFO, "Client  " + clientSocket.getInetAddress() + " : " + clientSocket.getPort() + " disconnects");
            System.out.println();
        } catch (Exception e) {
            logger.log(Level.WARNING, "The Client  " + clientSocket.getInetAddress() + " : " + clientSocket.getPort() + " lost connection");
        }
    }


    /*
     * Method to remove the songs of the client from the server
     * @param portToCompare the port of the client to compare with
     */
    private void removeSongs(int portToCompare) {
        // Temporary array to store the songs to be removed
        ArrayList<Music> storedSongs = server.getStoredSongs();
        ArrayList<Music> songsToRemove = new ArrayList<>();

        // Delete all songs of the client based on the ip address
        for (int i = 0; i < storedSongs.size(); i++) {
            if (storedSongs.get(i).getInitialPort() == (portToCompare)) {
                songsToRemove.add(storedSongs.get(i));
                storedSongs.remove(i);
                i--; // Adjust the index to account for the removed element
            }
        }
        logger.log(Level.INFO, "the music shared by the client  " + clientSocket.getInetAddress() + " : " + clientSocket.getPort() + " has been removed");

    }

    // Method to remove the client from the server
    private void removeClients(int portToCompare) {
        try {

            // Delete all songs of the client based on the ip address
            ArrayList<ClientConnected> storedClients = server.getStoredClients();
            ArrayList<ClientConnected> clientsToRemove = new ArrayList<>();

            for (int i = 0; i < storedClients.size(); i++) {
                if (storedClients.get(i).getInitialPort() == (portToCompare)) {
                    clientsToRemove.add(storedClients.get(i));
                    storedClients.remove(i);
                    i--; // Adjust the index to account for the removed element
                }
            }
            logger.log(Level.INFO, "the client  " + clientSocket.getInetAddress() + " : " + clientSocket.getPort() + " has been removed");
        } catch (Exception e) {
            logger.log(Level.WARNING, "the client  " + clientSocket.getInetAddress() + " : " + clientSocket.getPort() + " can not be removed");
        }
    }
}

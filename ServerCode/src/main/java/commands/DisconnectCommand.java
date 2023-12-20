package commands;

import server.Music;
import server.Server;
import server.ServerClientInteractions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

/*
 * Command to disconnect the client from the server
 */
public class DisconnectCommand implements Command {
    private ServerClientInteractions serverClientInteractions;

    private Socket clientSocket;
    private Server server;
    
    private PrintWriter pOut;
    private BufferedReader buffIn;

    public DisconnectCommand(PrintWriter pOut, BufferedReader buffIn, Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
        this.pOut = pOut;
        this.buffIn = buffIn;
    }

    /*
     * Execute the command on the server to disconnect the client
     * @param serverClientInteractions the server to execute the command on
     */
    @Override
    public void execute(ServerClientInteractions serverClientInteractions) {
        this.serverClientInteractions = serverClientInteractions;
        try { 
            removeSongs(buffIn, clientSocket.getInetAddress());
            System.out.println("Client " + clientSocket.getInetAddress() + " disconnects");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    

    private void removeSongs(BufferedReader buffIn, InetAddress ipAddress) {
        try {
            String ipToCompare = buffIn.readLine();

            // Delete all songs of the client based on the ip address
            ArrayList<Music> storedSongs = server.getStoredSongs();
            ArrayList<Music> songsToRemove = new ArrayList<>();

            for (int i = 0; i < storedSongs.size(); i++) {
            if (storedSongs.get(i).getIpAddress().equals(ipToCompare)) {
                songsToRemove.add(storedSongs.get(i));
                storedSongs.remove(i);
                i--; // Adjust the index to account for the removed element
            }
        }
            // Print the remaining songs
        for (Music music : storedSongs) {
            System.out.println("Remaining Song: " + music.getIpAddress());
        }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

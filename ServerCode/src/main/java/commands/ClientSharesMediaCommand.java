package commands;

import server.Music;
import server.Server;
import server.ServerClientInteractions;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;

import static main.Main.logger;

/*
 * Command to handle the client sharing media with the server
 *
 */
public class ClientSharesMediaCommand implements Command{
    private ServerClientInteractions serverClientInteractions;

    private PrintWriter pOut;
    private BufferedReader buffIn;

    private Socket clientSocket;
    private Server server;

    /*
     * Constructor for the command to share media
     * @param pOut the output stream to the client
     * @param bufferIn the input stream from the client
     * @param clientSocket the socket of the client
     * @param server the server to execute the command on
     */
    public ClientSharesMediaCommand(PrintWriter pOut, BufferedReader bufferIn, Socket clientSocket, Server server) {
        this.pOut = pOut;
        this.buffIn = bufferIn;
        this.clientSocket = clientSocket;
        this.server = server;
    }



    /*
     * Execute the command on the server to share media
     * @param serverClientInteractions the server to execute the command on
     */
    @Override
    public void execute(ServerClientInteractions serverClientInteractions) {
        this.serverClientInteractions = serverClientInteractions;
        try {
            pOut.println("Enter the full path of the song you want to share");
            
            String path = buffIn.readLine();
            String songName = buffIn.readLine();
            String ipAddress = buffIn.readLine();
            String port = buffIn.readLine();
            int initialPort = Integer.parseInt(buffIn.readLine());
            Music music = new Music(path, songName, ipAddress, port,initialPort,serverClientInteractions.getClientConnected());
            logger.log(Level.INFO, "Client " + clientSocket.getInetAddress() + " : " + clientSocket.getPort()+ " shared "+ songName);

            System.out.println();
            server.getStoredSongs().add(music);
            serverClientInteractions.menu(pOut, buffIn, clientSocket); // Call the menu after executing the command
        } catch (Exception e) {
            logger.log(Level.WARNING, "The music shared by  the client : " + clientSocket.getInetAddress() + " : " + clientSocket.getPort()+ " can not be added to the server");

        }
    }
    
}

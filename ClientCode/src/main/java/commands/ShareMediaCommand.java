package commands;

import client.Client;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Command to share a media with the server
 *
 */
public class ShareMediaCommand implements Command {
    private final PrintWriter pOut;
    private final BufferedReader buffIn;

    private  Socket clientSocket;

    private Client client;

    /*
     * Constructor of the command that will set the input and output streams of the client
     * @param pOut : output stream of the client
     * @param buffIn : input stream of the client
     */
    public ShareMediaCommand(PrintWriter pOut, BufferedReader buffIn, Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.pOut = pOut;
        this.buffIn = buffIn;
    }

    /*
     * Method that will execute the command on the client and share a media with the server
     * @param client : client that will execute the command
     *
     */
    @Override
    public void execute(Client client) {
        this.client = client;
        System.out.println("Executing ShareMediaCommand");
        try {
            System.out.println(buffIn.readLine());

            Scanner sc = new Scanner(System.in);
            String musicPath;

            boolean isValid;
            do{
                isValid = true;
                System.out.println("Please enter the path of the music you want to share (must be a .wav file): ");
                musicPath = sc.nextLine();
                // Check if the file exists at the musicPath
                if(!new File(musicPath).exists()){
                    System.out.println("The file does not exist");
                    isValid = false;
                    return;
                }
                // Check if the file is a .wav file
                if(!musicPath.endsWith(".mp3")){
                    System.out.println("The file must be a .wav file");
                    isValid = false;
                }
            }while(!isValid);

            pOut.println(musicPath);
            pOut.println(musicPath.substring(musicPath.lastIndexOf("\\")+1));
            pOut.println(client.getIpAddress());
            pOut.println(clientSocket.getLocalPort()); //TODO : send listening port
            
            client.menu(buffIn, pOut); // Call the menu after executing the command
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

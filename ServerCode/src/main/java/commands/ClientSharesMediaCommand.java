package commands;

import server.ServerClientInteractions;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSharesMediaCommand implements Command{
    private ServerClientInteractions serverClientInteractions;

    private PrintWriter pOut;
    private BufferedReader buffIn;

    private Socket clientSocket;

public ClientSharesMediaCommand(PrintWriter pOut, BufferedReader bufferIn, Socket clientSocket) {
        this.pOut = pOut;
        this.buffIn = bufferIn;
        this.clientSocket = clientSocket;
    }



    @Override
    public void execute(ServerClientInteractions serverClientInteractions) {
        this.serverClientInteractions = serverClientInteractions;
        System.out.println("client shares media");
        try {
            serverClientInteractions.menu(pOut, buffIn, clientSocket); // Call the menu after executing the command
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}

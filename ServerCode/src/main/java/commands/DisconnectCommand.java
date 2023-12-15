package commands;

import server.ServerClientInteractions;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class DisconnectCommand implements Command {
    private ServerClientInteractions serverClientInteractions;

    private Socket clientSocket;

    public DisconnectCommand(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void execute(ServerClientInteractions serverClientInteractions) {
        this.serverClientInteractions = serverClientInteractions;
        System.out.println("client disconnects");
        try {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

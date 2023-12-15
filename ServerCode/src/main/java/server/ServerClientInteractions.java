package server;
import commands.ClientSharesMediaCommand;
import commands.Command;
import commands.DisconnectCommand;
import commands.ListSongs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerClientInteractions implements Runnable{
    private final Socket clientSocket;

    private Server server;

    public ServerClientInteractions(Socket clientSocket) {
        this.clientSocket = clientSocket;
        server = Server.getServer();
    }

    @Override
    public void run() {
        handleClientConnection(clientSocket);
    }


    public void menu(PrintWriter pOut, BufferedReader bufferIn, Socket clientSocket) throws Exception{
        pOut.println("Commands available:\n1\tList available songs\n2\tShare a song\n3\tDisconnect and close");
        int number = Integer.parseInt(bufferIn.readLine());

        Command command = null;
        switch (number) {
            case 1:
                 command = new ListSongs(pOut, bufferIn, clientSocket);
                break;
            case 2:
                command = new ClientSharesMediaCommand(pOut, bufferIn, clientSocket);
                break;
            case 3:
                command = new DisconnectCommand(clientSocket);
        }

        command.execute(this);
    }

    // Method to handle a client connection
    private void handleClientConnection(Socket clientSocket) {
        try {
            System.out.println("Client connected: " + clientSocket.getInetAddress());
            // buffIn is used to read data from the client
            BufferedReader buffIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // pOut is used to send data to the client
            PrintWriter pOut = new PrintWriter(clientSocket.getOutputStream(), true);

            // Perform server-side logic as needed
            menu(pOut, buffIn, clientSocket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



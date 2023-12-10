package server_code;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class ServerClientInteractions {
    private final Socket clientSocket;

    private Server server;

    public ServerClientInteractions(Socket clientSocket) {
        this.clientSocket = clientSocket;
        server = Server.getServer();
    }
}
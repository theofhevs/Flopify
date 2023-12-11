package server_code;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class ServerClientInteractions implements Runnable{
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




    private void menu(PrintWriter pOut, BufferedReader bufferIn, Socket clientSocket) throws Exception{
        pOut.println("Commands available:\n1\tList available songs\n2\tShare a song\n3\tDisconnect and close");
        int command = Integer.parseInt(bufferIn.readLine());
        switch (command) {
            case 1:
                listSongs(pOut, bufferIn, clientSocket);
                break;
            case 2:
                clientSharesMedia(pOut, bufferIn, clientSocket);
                break;
            case 3:
                disconnect(clientSocket);
        }
    }
    private void disconnect(Socket clientSocket) throws IOException {
        // Close the client connection when done
        clientSocket.close();
        System.out.println("Client disconnected: " + clientSocket.getInetAddress());
    }

    private void clientSharesMedia(PrintWriter pOut, BufferedReader bufferIn, Socket clientSocket) throws Exception {
        System.out.println("client shares media");

        menu(pOut, bufferIn, clientSocket);
    }

    private void listSongs(PrintWriter pOut, BufferedReader bufferIn, Socket clientSocket) throws Exception {

        System.out.println("list songs");

        menu(pOut, bufferIn, clientSocket);
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
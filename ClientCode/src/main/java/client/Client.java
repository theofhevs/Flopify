package client;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import PeerToPeer.ClientConnection;
import commands.*;


/**
 * Client class that will connect to the server and send commands to it using
 * the Command pattern
 */
public class Client {
    Scanner scanner = new Scanner(System.in);
    // Port of the server
    private int serverPort;

    // IP of the client
    private InetAddress ipAddress;

    // Name of the Server
    private String serverName;

    // client Socket
    private Socket clientSocket;

    private int initialPort;

    public int getInitialPort() {
        return initialPort;
    }

    /*
     * Getter of the ipAddress attribute
     */
    public InetAddress getIpAddress() {
        return ipAddress;
    }

    /*
     * Constructor of the client class in the case that the client doesn't specify
     * the server's port
     *
     * @param serverName : name of the server
     *
     * @param localAddress : IP of the client
     */
    public Client(String serverName, InetAddress localAddress) {
        this.serverName = serverName;
        this.serverPort = 45000;
        ipAddress = localAddress;
        initialPort = getAvailablePort();

        Thread server = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    ClientConnection listeningServer = ClientConnection.getClientConnection(initialPort);
                    listeningServer.startServer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        server.start();

    }

    private int getAvailablePort() {
        for (int port = 45001; port < 65535; port++) {
            if (!isPortInUse(port)) {
                return port;
            }
        }

        return -1;
    }

    private boolean isPortInUse(int port) {
        try {
            new ServerSocket(port).close();
            return false;
        } catch (IOException e) {
            return true;
        }
    }

    /*
     * Method that will connect the client to the server and create the socket and
     * the input and output streams
     *
     */
    public void connectToServer() throws Exception {
        System.out.println("connexion to the server " + serverName + " on port " + serverPort);
        clientSocket = new Socket(serverName, serverPort);
        System.out.println("connexion Succeded");

        // buffIn is used to read the data that are received by the server
        BufferedReader buffIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        // pOut is used to send data to the server
        PrintWriter pOut = new PrintWriter(clientSocket.getOutputStream(), true);
        pOut.println(getInitialPort());

        menu(buffIn, pOut);
    }

    /*
     * Method that will display the menu to the user and ask him to enter a command
     * number between 1 and 4
     *
     * @param buffIn : BufferedReader used to read the data that are received by the
     * server
     *
     * @param pOut : PrintWriter used to send data to the server
     */
    public void menu(BufferedReader buffIn, PrintWriter pOut) {
        try {
            System.out.println();
            for (int i = 0; i < 5; i++)
                System.out.println(buffIn.readLine());

            int number;
            do {
                System.out.println();
                System.out.print("Please enter a command number (1-4): ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    System.out.print("Please enter a command number (1-4): ");
                    scanner.next();
                }
                number = scanner.nextInt();
            } while (number < 1 || number > 4);

            pOut.println(number);
            Command command;
            switch (number) {
                case 1:
                    command = new DisplayAvailableSongsCommand(pOut, buffIn);
                    break;
                case 2:
                    command = new ShareMediaCommand(pOut, buffIn, clientSocket);
                    break;
                case 3:
                    command = new CloseConnectionCommand(pOut, buffIn);
                    break;

                    case 4:
                    command = new DisplayClients(pOut, buffIn);
                    break;

                default:
                    return;
            }
            command.execute(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

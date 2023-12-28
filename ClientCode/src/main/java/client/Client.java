package client;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

import PeerToPeer.ClientConnection;
import commands.CloseConnectionCommand;
import commands.Command;
import commands.DisplayAvailableSongsCommand;
import commands.ShareMediaCommand;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

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
        menu(buffIn, pOut);
    }

    public void connectToClient() throws Exception {

    }

    /*
     * Method that will display the menu to the user and ask him to enter a command
     * number between 1 and 3
     *
     * @param buffIn : BufferedReader used to read the data that are received by the
     * server
     *
     * @param pOut : PrintWriter used to send data to the server
     */
    public void menu(BufferedReader buffIn, PrintWriter pOut) {
        try {
            System.out.println();
            for (int i = 0; i < 4; i++)
                System.out.println(buffIn.readLine());

            int number;
            do {
                number = commandInput("Please enter a command number (1-3): ");
                // changé condition car ne prend pas en compte les lettres
            } while (number < 1 || number > 3);
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
                // ------------------------------ playAudio (TEST)
                // --------------------------------
                /*
                 * case 4 :
                 * playAudio(clientSocket.getInputStream());
                 */
                // ------------------------------ playAudio (TEST)
                // --------------------------------
                default:
                    return;
            }
            command.execute(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // ------------------------------ playAudio (TEST)
    // --------------------------------
    /*
     * public void playAudio(InputStream inputStream) {
     * try (BufferedInputStream bufferedInputStream = new
     * BufferedInputStream(inputStream)) {
     *
     * Player player = new Player(bufferedInputStream);
     * player.play();
     *
     * } catch (JavaLayerException e) {
     * e.printStackTrace();
     * } catch (IOException e) {
     * e.printStackTrace();
     * }
     * }
     */
    // ------------------------------ playAudio (TEST)
    // --------------------------------

    /*
     * Method that will ask the user to enter a number and return it
     *
     * @param message : message that will be displayed to the user
     */
    private int commandInput(String message) {
        int number = 0;
        boolean isInt;

        // ask the user to enter a number while the input is not an integer
        do {
            System.out.println(message);
            isInt = true;

            try {
                number = scanner.nextInt();
            } catch (InputMismatchException e) {
                isInt = false;
            }
        } while (!isInt);
        return number;
    }
}

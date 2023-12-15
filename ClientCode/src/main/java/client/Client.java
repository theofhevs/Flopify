package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

import commands.CloseConnectionCommand;
import commands.Command;
import commands.DisplayAvailableMediasCommand;
import commands.ShareMediaCommand;

public class Client {
    Scanner scanner = new Scanner(System.in);
    // Port of the server
    private int serverPort;

    // IP of the client
    private InetAddress ipAddress;

    //Name of the Server
    private String serverName;

    // client Socket
    private Socket clientSocket;


    // Client constructor in the case that the client specify the server's port
    public Client(String serverName, int serverPort, InetAddress localAddress) {
        this.serverName = serverName;
        this.serverPort = serverPort;
        ipAddress = localAddress;
    }


    // Client constructor in the case that we use the basic server's port
    public Client(String serverName, InetAddress localAddress) {
        this.serverName = serverName;
        this.serverPort = 45000;
        ipAddress = localAddress;
    }

    // method to connect the client to the server
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


    // method menu to display the menu of the client using a switch case. THe user will choose the action he wants to do by entering a number
    public void menu(BufferedReader buffIn, PrintWriter pOut) {
        try {
            for (int i = 0; i < 4; i++)
                System.out.println(buffIn.readLine());

            int number;
            do {
                number = commandInput("Please enter a command number (1-3): ");
            } while (number < 1 || number > 4);
            pOut.println(number);

            Command command;
            switch (number) {
                case 1:
                    command = new DisplayAvailableMediasCommand(pOut, buffIn);
                    break;
                case 2:
                    command = new ShareMediaCommand(pOut, buffIn);
                    break;
                case 3:
                    command = new CloseConnectionCommand();
                    System.exit(0); //used to terminate the client "server" thread as well
                    break;
                default:
                    return;
            }
            command.execute(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int commandInput(String message) {
        int number = 0;
        boolean isInt;

        //ask the user to enter a number while the input is not an integer
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

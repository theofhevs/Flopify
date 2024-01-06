package main;

import client.Client;
import java.net.InetAddress;


/**
 * Main class for the client that will connect to the server
 */
public class Main {
    public static void main(String[] args) {
        try {
            Client client2 = new Client("localhost", InetAddress.getLocalHost());
            client2.connectToServer();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package main;

import java.net.InetAddress;
import java.net.UnknownHostException;

import client.Client;

public class secondClient {
    public static void main(String[] args) throws Exception {
        Client client2 = new Client("localhost", InetAddress.getLocalHost());
        client2.connectToServer();


    }
}

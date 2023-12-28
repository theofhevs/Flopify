package main;

import client.Client;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class test {
    public static void main(String[] args) throws Exception {

        Client client2 = new Client("localhost", InetAddress.getLocalHost());
        client2.connectToServer();

    }
}

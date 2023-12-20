package main;

import server.Server;

/*
 * Main class to start the server on port 45000 and listen for incoming connections from clients
 *
 */
public class Main {
    public static void main(String[] args) {
        Server server = new Server(45000);
        server.startServer();
    }
}

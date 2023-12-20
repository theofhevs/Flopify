package main;
import java.net.InetAddress;
import client.Client;

/**
 * Main class for the client that will connect to the server
 */
public class Main {
    public static void main(String[] args) {
        try {
            // Exemple d'utilisation du Client avec un port spécifié
            //Client client1 = new Client("localhost", 45001, InetAddress.getLocalHost());
            //client1.connectToServer();

            // Exemple d'utilisation du Client avec le port par défaut (45000)
            Client client2 = new Client("localhost", InetAddress.getLocalHost());
            client2.connectToServer();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package server;

import java.net.Socket;

/*
 * Class to store the clients connected to the server
 */
public class ClientConnected {

    private String ipAddress;
    private int port;
    private int initialPort;
    private String clientName;

    /*
     * Constructor for the class to store the clients connected to the server
     * @param ipAddress the ip address of the client
     * @param port the port of the client
     * @param initialPort the initial port of the client
     */
    public ClientConnected(String ipAddress, int port, int initialPort, Socket clientSocket) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.initialPort = initialPort;
    }


    /*
     * Get the ip address of the client
     * @return the ip address of the client
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /*
     * Get the port of the client
     * @return the port of the client
     */
    public int getPort() {
        return port;
    }

    /*
     * Get the initial port of the client
     * @return the initial port of the client
     */
    public int getInitialPort() {
        return initialPort;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        if (this.clientName == null) {
            this.clientName = clientName;

        }
    }
}

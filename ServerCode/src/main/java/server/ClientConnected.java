package server;

public class ClientConnected {

    private String ipAddress;
    private int port;
    private int initialPort;



    public ClientConnected(String ipAddress,int port,int initialPort) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.initialPort = initialPort;
    }


    public String getIpAddress() {
        return ipAddress;
    }

    public int getPort() {
        return port;
    }

    public int getInitialPort() {
        return initialPort;
    }

}

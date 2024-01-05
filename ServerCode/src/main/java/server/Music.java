package server;

public class Music {
    private String musicPath;
    private String musicName;
    private String ipAddress;
    private String port;

    private int initialPort;

    private ClientConnected owner;


    /*
     * Constructor for the class to store the music shared by the client
     * @param musicPath the path of the music
     * @param musicName the name of the music
     * @param ipAddress the ip address of the client
     * @param port the port of the client
     * @param initialPort the initial port of the client
     * 
     */
    public Music(String musicPath, String musicName, String ipAddress, String port, int initialPort, ClientConnected owner) {
        this.owner = owner;
        this.musicPath = musicPath;
        this.musicName = musicName;
        this.ipAddress = ipAddress;
        this.port = port;
        this.initialPort = initialPort;
    }

    public String getOwnerID() {
        return owner.getClientName();
    }

    /*
     * Get the path of the music
     * @return the path of the music
     */
    public int getInitialPort() {
        return initialPort;
    }

    /*
     * Get the path of the music
     * @return the path of the music
     */
    public String getmusicPath() {
        return musicPath;
    }

    /*
     * Get the name of the music
     * @return the name of the music
     */
    public String getMusicName() {
        return musicName;
    }

    /*
     * Get the ip address of the client
     * @return the ip address of the client
     */
    public String getIpAddress() {
        return owner.getIpAddress();
    }

    /*
     * Get the port of the client
     * @return the port of the client
     */
    public String getPort() {
        return port;
    }


    /*
     * Method to print the music shared by the client
     * @return the music shared by the client as a string
     */
    @Override
    public String toString() {
        return "Music:" +
                ", musicPath='" + musicPath + '\'' +
                "musicName='" + musicName + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", port=" + port;
    }
}

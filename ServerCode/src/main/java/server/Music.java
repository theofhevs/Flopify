package server;

public class Music {
    private String musicPath;
    private String musicName;
    private String ipAddress;
    private String port;

    private int initialPort;

    public Music(String musicPath, String musicName, String ipAddress, String port, int initialPort) {
        this.musicPath = musicPath;
        this.musicName = musicName;
        this.ipAddress = ipAddress;
        this.port = port;
        this.initialPort = initialPort;
    }
    public int getInitialPort() {
        return initialPort;
    }

    public String getmusicPath() {
        return musicPath;
    }

    public String getMusicName() {
        return musicName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getPort() {
        return port;
    }


    @Override
    public String toString() {
        return "Music:" +
                ", musicPath='" + musicPath + '\'' +
                "musicName='" + musicName + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", port=" + port;
    }
}

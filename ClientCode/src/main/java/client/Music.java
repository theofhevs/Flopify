package client;

public class Music {
    private String musicName;
    private String musicPath;
    private String ipAddress;
    private String port;

    public Music(String musicName, String musicPath, String ipAddress, String port) {
        this.musicName = musicName;
        this.musicPath = musicPath;
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public String getmusicName() {
        return musicName;
    }

    public String getmusicPath() {
        return musicPath;
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
                "musicName='" + musicName + '\'' +
                ", musicPath='" + musicPath + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", port=" + port;
    }
}

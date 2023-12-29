package commands;

import PeerToPeer.ClientConnection;
import client.Client;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;



/**
 * Command to display available medias
 */
public class DisplayAvailableSongsCommand implements Command {
    private final PrintWriter pOut;
    private final BufferedReader buffIn;
    private Client client;

    /*
     * Constructor of the command that will set the input and output streams of the client
     * @param pOut : output stream of the client
     * @param buffIn : input stream of the client
     */
    public DisplayAvailableSongsCommand(PrintWriter pOut, BufferedReader buffIn) {
        this.pOut = pOut;
        this.buffIn = buffIn;
    }

    /*
     * Method that will execute the command on the client and display the available medias
     * @param client : client that will execute the command
     */
    @Override
    public void execute(Client client) {
        this.client = client;


        try {
            System.out.println();
            int numberOfSongs = Integer.parseInt(buffIn.readLine());

            // If there is no available music, display a message and call the menu
            if (numberOfSongs == 0) {
                System.out.println("There is no available music");
                client.menu(buffIn, pOut); // Call the menu after executing the command
                return;
            }

            for (int i = 1; i <= numberOfSongs; i++) {
                String musicName = buffIn.readLine();
                String port = buffIn.readLine();
                System.out.println(i + ": " + musicName + ", Port: " + port);
            }

            System.out.println(buffIn.readLine());
            Scanner sc = new Scanner(System.in);
            char c = sc.next().charAt(0);

            while (c != 'n' && c != 'y') {
                System.out.print("You must write y or n : ");
                c = sc.next().charAt(0);
            }
            ;

            pOut.println(c);
            if (c == 'n') {
                client.menu(buffIn, pOut); // Call the menu after executing the command
                return;
            }

            System.out.println(buffIn.readLine());
            int songNumber = sc.nextInt();

            while (songNumber > numberOfSongs || songNumber < 0) {
                System.out.print("Please write a number between : 1 and " + numberOfSongs + " : ");
                songNumber = sc.nextInt();
            }
            ;
            pOut.println(songNumber);


            String musicPath = buffIn.readLine();
            int portToConnect = Integer.parseInt(buffIn.readLine());
            System.out.println("Music path : " + musicPath);
            System.out.println("Port to connect : " + portToConnect);

            var is = getInputStreamFromServer(portToConnect, musicPath, "127.0.0.1");
            BufferedInputStream bis = new BufferedInputStream(is);

            // create the player to play the music
            Player player = null;
            try {
                player = new Player(bis);


            } catch (JavaLayerException e) {
                throw new RuntimeException(e);
            }

            var userInputRunnable = new userInputThread(player, is, client, pOut, buffIn);
            var myThread = new Thread(userInputRunnable);
            myThread.start();
            player.play();
            myThread.join();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (JavaLayerException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedInputStream getInputStreamFromServer(int portToConnect, String musicPath, String serverAdress) throws IOException {
        // connect to the listening server to stream the musics
        Socket listeningSocket = new Socket(serverAdress, portToConnect);
        // create the sender to send information to the server
        PrintWriter out = new PrintWriter(listeningSocket.getOutputStream(), true);
        //send the path for the musics to stream
        out.println(musicPath);

        // Lisen the stream of the listening server
        BufferedInputStream is = new BufferedInputStream(listeningSocket.getInputStream());

        return is;
    }

}


class userInputThread implements Runnable {
    private boolean isMusicStopped = false;
    private int stopped = 0;
    private int total = 0;

    private BufferedInputStream is;

    private BufferedInputStream bufferedStream2;

    private Player player;

    private Client client;
    private PrintWriter pOut;
    private BufferedReader buffIn;

    private ByteArrayInputStream byteArrayInputStream;


    public userInputThread(Player player, BufferedInputStream is, Client client, PrintWriter pOut, BufferedReader buffIn) throws IOException {
        this.player = player;
        this.is = is;
        this.total = is.available();
        this.client = client;
        this.pOut = pOut;
        this.buffIn = buffIn;
    }


    @Override
    public void run() {
        try {
            listenForUserInput();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JavaLayerException e) {
            throw new RuntimeException(e);
        }

    }


    public void listenForUserInput() throws IOException, JavaLayerException {

        Scanner sc = new Scanner(System.in);

        char c;
        do {

            System.out.println("Press 'S' to stop the music, 'P' to pause or resume the music");
            c = sc.next().charAt(0);
            switch (c) {
                case 's':
                    stopMusic();
                    break;
                case 'p':
                    if (isMusicStopped) {
                        resumeMusic();
                    } else {
                        pauseMusic();
                    }
                    break;
                default:
                    System.out.println("You can only enter the letter 'S'. Try again.");
                    break;
            }



        } while (c != 's' || c != 'S');


    }


    private void stopMusic(){
        System.out.println("Music stopped");


        player.close();
        pOut.println("done");
        client.menu(buffIn, pOut);
    }


    private void pauseMusic() throws IOException {
        System.out.println("Music paused");
        isMusicStopped = true;

        // methode to stop the music
        byte[] buffer = new byte[is.available()];
        is.mark(buffer.length);
        is.read(buffer);
        byteArrayInputStream = new ByteArrayInputStream(buffer);

        //transfer data to another stream
        bufferedStream2 = new BufferedInputStream(byteArrayInputStream);
        player.close();

    }

    private void resumeMusic() throws JavaLayerException {
        System.out.println("Music resumed");
        isMusicStopped = false;
        byteArrayInputStream.reset();
        player = new Player(byteArrayInputStream);
        is = new BufferedInputStream(bufferedStream2);
        player.play();

    }

}
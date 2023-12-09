package server_code;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SimpleServer {

    public static void main(String[] args) {

        try{
            ServerSocket server = new ServerSocket(45000);
            System.out.println("server_code.Server is listening");
            Socket exchange = server.accept();
            System.out.println("We got a connection");
            //input
            InputStream inStream = exchange.getInputStream(); // recupérer les données
            InputStreamReader reader = new InputStreamReader(inStream); // convertir les données en string
            BufferedReader buffin = new BufferedReader(reader); // lire les données et les stocker dans un buffer

            //Output
            OutputStream outStream = exchange.getOutputStream();
            PrintWriter out = new PrintWriter(outStream, true);
            //message du client affiché sur le serveur
            String line = buffin.readLine();
            System.out.println(line);
            //message écrit par le serveur envoyé au client
            Scanner sc = new Scanner(System.in);
            System.out.println("Your message : ");
            String message = sc.nextLine();
            out.println(message);


            // receive a pdf file
            DataInputStream dis = new DataInputStream(exchange.getInputStream());
            FileOutputStream fos = new FileOutputStream("chemin du fichier");
            byte[] buffer = new byte[4096];
            int bytesRead;
            while((bytesRead = dis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }



        }catch(IOException e){
            throw new RuntimeException(e);
        }

    }
}
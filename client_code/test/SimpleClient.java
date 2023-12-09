package client_code.test;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient {

    public static void main(String[] args) {

        try{
            Socket client = new Socket("127.0.0.1", 45000);

            System.out.println("client_code.test.Client connecté !");
            //input
            InputStream inStream = client.getInputStream();
            InputStreamReader reader = new InputStreamReader(inStream);
            BufferedReader buffin = new BufferedReader(reader);

            //output
            OutputStream outStream = client.getOutputStream();
            PrintWriter out = new PrintWriter(outStream, true);



            Scanner sc = new Scanner(System.in);
            //message écrit par le client envoyé au serveur
            System.out.println("Your message : ");
            String message = sc.nextLine();
            out.println(message);
            //message du serveur affiché sur le client
            String line = buffin.readLine();
            System.out.println(line);



            // send a pdf
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            FileInputStream fis = new FileInputStream("chemin du fichier");
            byte[] buffer = new byte[4096];
            int read;
            while((read = fis.read(buffer)) != -1) {
                dos.write(buffer, 0, read);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package commands;

import client.Client;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class DisplayAvailableMediasCommand implements Command {
    private final PrintWriter pOut;
    private final BufferedReader buffIn;

    private Client client;

    public DisplayAvailableMediasCommand(PrintWriter pOut, BufferedReader buffIn) {
        this.pOut = pOut;
        this.buffIn = buffIn;
    }

    @Override
    public void execute(Client client) {
        this.client = client;
        System.out.println("Executing DisplayAvailableMediasCommand");
        client.menu(buffIn, pOut); // Call the menu after executing the command
    }
}

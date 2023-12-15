package commands;

import client.Client;

public class CloseConnectionCommand implements Command {
    private Client client;

    @Override
    public void execute(Client client) {
        this.client = client;
        System.out.println("Executing CloseConnectionCommand");
        client.menu(null, null); // Call the menu after executing the command
    }
}
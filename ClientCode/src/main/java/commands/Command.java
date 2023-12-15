package commands;

import client.Client;

public interface Command {
    public void execute(Client client);

    default void setClient(Client client) {
    }
}

package commands;

import client.Client;

/**
 * Interface for the commands used by the client
 *
 */
public interface Command {
    /*
     * Method that will execute the command on the client
     * @param client : client that will execute the command
     */
    public void execute(Client client);

    /*
     * Method that will set the client that will execute the command
     * @param client : client that will execute the command
     */
    default void setClient(Client client) {
    }
}

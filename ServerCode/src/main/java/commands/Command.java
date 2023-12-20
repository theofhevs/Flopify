package commands;

import server.ServerClientInteractions;

/*
 * Command interface for the command pattern used in the server to handle client requests
 * The execute method is called by the server to execute the command
 */
public interface Command {
    /*
     * Execute the command on the server
     * @param serverClientInteractions the server to execute the command on
     *
     */
    public void execute(ServerClientInteractions serverClientInteractions);

    /*
     * Set the server to execute the command on
     * @param serverClientInteractions the server to execute the command on
     */
    default void setServer(ServerClientInteractions serverClientInteractions) {
    }
}

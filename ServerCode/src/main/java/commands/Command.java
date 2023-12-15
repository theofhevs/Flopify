package commands;

import server.ServerClientInteractions;

public interface Command {
    public void execute(ServerClientInteractions serverClientInteractions);

    default void setServer(ServerClientInteractions serverClientInteractions) {
    }
}

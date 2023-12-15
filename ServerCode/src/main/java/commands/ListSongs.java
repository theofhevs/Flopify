package commands;

import server.ServerClientInteractions;

public class ListSongs implements Command{
    private ServerClientInteractions serverClientInteractions;

    @Override
    public void execute(ServerClientInteractions serverClientInteractions) {
        this.serverClientInteractions = serverClientInteractions;
        System.out.println("client lists songs");
    }
}

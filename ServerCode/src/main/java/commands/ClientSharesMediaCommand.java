package commands;

import server.ServerClientInteractions;

public class ClientSharesMediaCommand implements Command{
    private ServerClientInteractions serverClientInteractions;

    @Override
    public void execute(ServerClientInteractions serverClientInteractions) {
        this.serverClientInteractions = serverClientInteractions;
        System.out.println("client shares media");
    }
    
}

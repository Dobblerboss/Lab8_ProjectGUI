import java.util.Map;

public class HelpCommand extends  HelpCommandUnex implements ExecuteCommand {
    @Override
    public String execute() {
        String[] all = {""};
        MainServer.commandList.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(x -> all[0]+=x.getKey()+" - "+x.getValue() +"\n");

        return all[0];
    }
}

import java.util.Map;

public class PrintAscendingCommand extends PrintAscendingCommandUnex implements ExecuteCommand {
    @Override
    public String execute() {
        String[] all={""};
        MainServer.flats.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(x -> all[0]+=x.getValue());
        return all[0];
    }
}

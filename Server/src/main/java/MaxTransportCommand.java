
public class MaxTransportCommand extends MaxTransportCommandUnex implements ExecuteCommand {
    @Override
    public String execute() {
        String[] all = new String[1];
        MainServer.flats.values().stream()
                .max(((o1, o2) -> Integer.compare(o1.getTransport().compareTo(o2.getTransport()), 0)))
                .ifPresent(x -> all[0] = x.toString());
        return all[0];
    }
}

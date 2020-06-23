
public class ShowCommand extends ShowCommandUnex implements ExecuteCommand {
    @Override
    public String execute() {
        String[] all = {""};
        MainServer.flats.entrySet().stream()
                .forEach(x -> all[0] += "================\nКлюч "+x.getKey()+" "+((Flat)x.getValue()).toString() +"Владелец: "+MainServer.relation.get(x.getKey())+"\n");
        all[0]+="================";
        return all[0];
    }
}

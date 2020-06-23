import java.util.LinkedHashMap;
import java.util.Map;

public class ReplaceIfGreaterCommand extends ReplaceIfGreaterCommandUnex {
    public ReplaceIfGreaterCommand(ReplaceIfGreaterCommandUnex commandUnex){
        this.user = commandUnex.user;
        this.newFlat = commandUnex.newFlat;
        this.key = commandUnex.key;
    }
    public LinkedHashMap<Integer, Flat> execute() {
        Map.Entry<Integer,Flat> MainFlat = MainServer.flats.entrySet().stream()
                .filter(x -> MainServer.relation.get(x.getKey()).equals(user))
                .filter(x -> x.getKey().equals(key))
                .findFirst()
                .get();
        if (newFlat.compareTo(MainFlat.getValue())>0){
            MainServer.flats.put(MainFlat.getKey(),newFlat);
        }
        return (LinkedHashMap<Integer, Flat>) MainServer.flats.clone();
    }
}

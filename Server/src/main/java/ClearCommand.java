import java.util.LinkedHashMap;

public class ClearCommand extends ClearCommandUnex {
    ClearCommand(ClearCommandUnex e){
        this.user = e.user;
    }

    public LinkedHashMap<Integer, Flat> execute(){
        LinkedHashMap<Integer,Flat> newMap = new LinkedHashMap<>();
        MainServer.flats
                .entrySet()
                .stream()
                .filter(x -> !MainServer.relation.get(x.getKey()).equals(user))
                .forEach(x -> newMap.put(x.getKey(),x.getValue()));
        MainServer.flats
                .entrySet()
                .stream()
                .filter(x -> MainServer.relation.get(x.getKey()).equals(user))
                .forEach(x -> MainServer.relation.remove(x.getKey()));
        MainServer.flats = newMap;

        return (LinkedHashMap<Integer, Flat>) MainServer.flats.clone();
    }

}

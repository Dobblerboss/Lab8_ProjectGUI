import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class UpdateIdCommand extends UpdateIdCommandUnex {
    public UpdateIdCommand(UpdateIdCommandUnex commandUnex){
        this.id = commandUnex.id;
        this.newFlat = commandUnex.newFlat;
        this.user = commandUnex.user;
        this.newKey = commandUnex.newKey;
    }
    public LinkedHashMap<Integer, Flat> execute(){
        AtomicInteger key = new AtomicInteger();
        AtomicBoolean yes = new AtomicBoolean(false);
        MainServer.flats.entrySet().stream()
                .filter(x -> id==x.getValue().getId())
                .findFirst()
                .ifPresent(x -> { key.set(x.getKey());  yes.set(true); });
        if(yes.get()){

            int keyI = key.get();
            if(!MainServer.relation.get(keyI).equals(user)){
                return (LinkedHashMap<Integer, Flat>) MainServer.flats.clone();
            }
            MainServer.flats.remove(keyI);
            MainServer.relation.remove(keyI);
            MainServer.flats.put(newKey, newFlat);
            MainServer.relation.put(newKey, user);

        }
        return (LinkedHashMap<Integer, Flat>) MainServer.flats.clone();
    }
}

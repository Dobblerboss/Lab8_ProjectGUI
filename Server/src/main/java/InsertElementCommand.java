import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class InsertElementCommand extends InsertElementCommandUnex{
    public InsertElementCommand(InsertElementCommandUnex commandUnex){
        this.user = commandUnex.user;
        this.key = commandUnex.key;
        this.newFlat = commandUnex.newFlat;
    }
    public LinkedHashMap<Integer, Flat> execute(){
        int size = MainServer.flats.size();
        try {
            if(!MainServer.relation.get(key).equals(user)){
                return setUsr();
            }
        } catch (NullPointerException e){
            // дальше
        }
        newFlat.setCreationDate();
        AtomicReference<Long> maxId = new AtomicReference(1);
        MainServer.flats.values()
                .stream()
                .max(Comparator.comparing(Flat::getId))
                .ifPresent(x -> maxId.set(x.getId()));
        long id = maxId.get()+1;

        newFlat.setId(id);
        MainServer.flats.put(key, newFlat);
        if (MainServer.flats.size() != size) {
            MainServer.relation.put(key, user);
        }
        return setUsr();
    }
    private LinkedHashMap<Integer, Flat> setUsr(){
        LinkedHashMap<Integer,Flat> sending;
        sending = (LinkedHashMap<Integer, Flat>) MainServer.flats.clone();
        sending.entrySet().stream().forEach(x -> x.getValue().setUser(MainServer.relation.get(x.getKey())));
        return sending;
    }
}

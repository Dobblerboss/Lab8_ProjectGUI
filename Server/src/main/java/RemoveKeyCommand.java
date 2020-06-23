import java.util.LinkedHashMap;

public class RemoveKeyCommand extends RemoveKeyCommandUnex  {
    public RemoveKeyCommand(RemoveKeyCommandUnex e){
        this.key = e.key;
        this.user = e.user;
    }
    public LinkedHashMap<Integer, Flat> execute(){
        int lengthPrev = MainServer.flats.size();
        LinkedHashMap<Integer,Flat> newFlat = new LinkedHashMap<>();
        try {
            if(!MainServer.relation.get(key).equals(user)){
                return (LinkedHashMap<Integer, Flat>) MainServer.flats.clone();
            }
        } catch (NullPointerException e){
            // дальше
        }

        MainServer.flats.entrySet()
                .stream()
                .filter(x -> x.getKey()!=key)
                .forEach((k)->newFlat.put(k.getKey(),k.getValue()));

        if(newFlat.size()==lengthPrev){
            return (LinkedHashMap<Integer, Flat>) MainServer.flats.clone();
        }else {
            MainServer.relation.remove(key);
            MainServer.flats = newFlat;

            return (LinkedHashMap<Integer, Flat>) MainServer.flats.clone();
        }
    }

}

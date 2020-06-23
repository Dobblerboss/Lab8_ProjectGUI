import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class PleaseSendAllCommand extends PleaseSendAllCommandUnex {
    public LinkedHashMap<Integer, Flat> execute(){
        LinkedHashMap<Integer,Flat> sending;
        sending = (LinkedHashMap<Integer, Flat>) MainServer.flats.clone();
        sending.entrySet().stream().forEach(x -> x.getValue().setUser(MainServer.relation.get(x.getKey())));
        return sending;
    }
}

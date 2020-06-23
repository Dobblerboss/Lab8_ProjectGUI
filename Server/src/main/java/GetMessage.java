
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

public class GetMessage implements Runnable {
    ByteBuffer intBuf;
    ByteBuffer buffer;
    SocketAddress remoteAdd;
    GetMessage(ByteBuffer buffer, ByteBuffer intBuf, SocketAddress remoteAdd){
        this.buffer = buffer;
        this.remoteAdd = remoteAdd;
        this.intBuf = intBuf;
    }

    @Override
    public void run() {
        int limits = buffer.limit();
        byte bytes[] = new byte[limits];
        buffer.get(bytes, 0, limits);
        Command gotCommand = null;
        MainServer.LOGGER.log(Level.INFO, "Принял пакет с коммандой");
        try {
            gotCommand = (Command) Serializer.deserialize(bytes);
        } catch (IOException | ClassNotFoundException e) {
            //System.out.print("ва");
        }
        RefactorUnexecutableClass<Command> refactoring = new RefactorUnexecutableClass<>(remoteAdd, gotCommand, intBuf,buffer);
        ExecutorService cache = Executors.newCachedThreadPool();
        cache.submit(refactoring);
    }
}

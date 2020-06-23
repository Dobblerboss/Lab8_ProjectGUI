import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.logging.Level;

public class SendAllCommand extends Thread{
    ByteBuffer intBuf;
    ByteBuffer buffer;
    LinkedHashMap<Integer, Flat> msg;
    SendAllCommand(LinkedHashMap<Integer, Flat> msg, ByteBuffer intBuf, ByteBuffer buffer){
        this.msg = msg;
        this.intBuf = intBuf;
        this.buffer = buffer;
    }

    @Override
    public void run() {
            MainServer.LOGGER.log(Level.INFO, "Отправка ответа");
            MainServer.allRemoteAdd.stream().forEach(x ->{
                try {
                    buffer.clear();
                    buffer = ByteBuffer.wrap(Serializer.serialize(msg));
                    intBuf = ByteBuffer.wrap(Serializer.serialize(buffer.capacity()));

                    MainServer.server.send(intBuf, x);
                    MainServer.server.send(buffer, x);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
    }
}

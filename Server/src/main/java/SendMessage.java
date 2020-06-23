import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

public class SendMessage extends Thread {
    ByteBuffer intBuf;
    ByteBuffer buffer;
    SocketAddress remoteAdd;
    String msg;
    SendMessage(SocketAddress add, String msg, ByteBuffer intBuf, ByteBuffer buffer){
        remoteAdd = add;
        this.msg = msg;
        this.intBuf = intBuf;
        this.buffer = buffer;

    }
    @Override
    public void run() {
        buffer.clear();
        buffer = ByteBuffer.wrap(msg.getBytes());
        try {
            intBuf = ByteBuffer.wrap(Serializer.serialize(buffer.capacity()));
            MainServer.LOGGER.log(Level.INFO, "Отправка ответа");
            MainServer.server.send(intBuf, remoteAdd);
            MainServer.server.send(buffer,remoteAdd);
            MainServer.allRemoteAdd.add(remoteAdd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

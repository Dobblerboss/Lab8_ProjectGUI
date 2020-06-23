

import java.io.EOFException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.LinkedHashMap;

public class InputMessage extends Thread {
    static DatagramChannel channel = null;
    static CheckingConnection checkingConnection;
    InputMessage() throws IOException {
        channel = DatagramChannel.open();
        channel.bind(null);
    }
    @Override
    public void run() {
        while (true){
            try {
                ByteBuffer intBuf = ByteBuffer.allocate(256);
                channel.receive(intBuf);
                if(checkingConnection.isAlive()) checkingConnection.stop();
                intBuf.flip();
                int limits = intBuf.limit();
                byte[] bytes = new byte[limits];
                intBuf.get(bytes, 0, limits);
                //Object a = Serializer.deserialize(bytes);
                int capacity = 0;
                try {
                    capacity = (int) Serializer.deserialize(bytes);
                } catch (EOFException e){
                    e.printStackTrace();
                    System.out.println("Тут что?");
                    for(byte a: bytes){
                        System.out.print(a);
                    }
                    System.out.println(new String(bytes));
                    return;
                }
                ByteBuffer buffer = ByteBuffer.allocate(capacity);
                channel.receive(buffer);
                buffer.flip();
                int limits2 = buffer.limit();
                bytes = new byte[limits2];
                buffer.get(bytes, 0, limits2);
                Object msgOb = null;
                try {
                    msgOb = Serializer.deserialize(bytes);
                } catch (EOFException e1){
                    msgOb = new String(bytes);
                }
                String serverAnswer;
                if (msgOb instanceof String) {
                    String msg = (String) msgOb;
                    switch (msg) {
                        case "1":
                            serverAnswer = MainClient.stats.getString("Name is taken");
                            break;
                        case "4":
                            serverAnswer = MainClient.stats.getString("No user with this name");
                            break;
                        case "7":
                            serverAnswer = MainClient.stats.getString("Incorrect password");
                            break;
                        default:
                            MainClient.mainWindow = new MainWindow();
                            MainClient.mainWindow.setVisible(true);
                            PleaseSendAllCommandUnex pleaseSendAllCommandUnex = new PleaseSendAllCommandUnex();
                            pleaseSendAllCommandUnex.checkCom(new String[]{""});
                            pleaseSendAllCommandUnex.user = MainClient.username;
                            try {
                                //System.out.println("Вжух");
                                InputMessage.send(Serializer.serialize(pleaseSendAllCommandUnex));
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            } catch (ClassNotFoundException ex) {
                                ex.printStackTrace();
                            }
                            continue;
                    }
                    ServerErrorAnswer app = new ServerErrorAnswer(serverAnswer);
                    app.setVisible(true);
                } else if (msgOb instanceof LinkedHashMap<?, ?>) {
                    LinkedHashMap<Integer, Flat> msg = (LinkedHashMap<Integer, Flat>) msgOb;
                    MainClient.addAndColorize(msg);
                    MainClient.mainWindow.refresh();
                }

                buffer.clear();
                intBuf.clear();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public static void send(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteBuffer buf = ByteBuffer.wrap(bytes);
        ByteBuffer intBuf = ByteBuffer.wrap(Serializer.serialize(buf.capacity()));
        InetSocketAddress address = new InetSocketAddress("localhost",Console.port);
        checkingConnection = new CheckingConnection();
        checkingConnection.start();
        channel.send(intBuf,address);
        channel.send(buf,address);
        //channel.close();
        //System.out.println("Отправилось "+ Serializer.deserialize(bytes));
    }
}

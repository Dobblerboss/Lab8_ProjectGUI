import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class MainServer {
    public static String pathname;
    public static HashMap<String,String> loginPass = new HashMap<>();
    public static HashMap<Integer, String> relation = new HashMap<>();
    public static HashMap<String, String> commandList = new HashMap();
    public static LinkedHashMap<Integer, Flat> flats = new LinkedHashMap<>();
    public static Logger LOGGER;
    public static DatagramChannel server;
    static int port;
    public static LinkedHashSet<SocketAddress> allRemoteAdd = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException {
        try(FileInputStream ins = new FileInputStream("log.config")) {
            LogManager.getLogManager().readConfiguration(ins);
            LOGGER = Logger.getLogger(MainServer.class.getName());
            LOGGER.log(Level.INFO, "Начало работы сервера");
        }catch (FileNotFoundException e){
            System.out.println("Файл конфига не найден. Выход");
            System.exit(0);
        } catch (AccessDeniedException e){
            System.out.println("Проверьте свои права ^v^. Выходим в окно");
            System.exit(0);
        }

        commandList.put("help ", " вывести справку по доступным командам");//
        commandList.put("info ", " вывести в стандартный поток вывода информацию о коллекции");//
        commandList.put("show ", " вывести в стандартный поток вывода все элементы коллекции в строковом представлении");//
        commandList.put("remove_key \"ключ\" ", " удалить элемент из коллекции по его ключу");//
        commandList.put("insert \"ключ\" {элемент} ", " добавить элемент с заданным ключом");//
        commandList.put("update_id \"id элемента\" {элемент} ", " обновить значение элемента коллекции, id которого равен заданному");//
        commandList.put("clear ", " очистить коллекцию");//
        commandList.put("execute_script \"имя файла\" ", " считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        commandList.put("exit ", " завершить программу (без сохранения в файл)");//
        commandList.put("remove_lower {элемент} ", " удалить из коллекции все элементы, меньшие, чем заданный");
        commandList.put("replace_if_greater \"ключ\" {элемент} ", " заменить значение по ключу, если новое значение больше старого");
        commandList.put("replace_if_lower \"ключ\" {элемент} ", " заменить значение по ключу, если новое значение меньше старого");
        commandList.put("sum_of_number_of_rooms ", " вывести сумму значений поля numberOfRooms для всех элементов коллекции");//
        commandList.put("max_by_transport ", " вывести любой объект из коллекции значение поля transport которого является максимальным");//
        commandList.put("print_ascending ", " вывести элементы коллекции в порядке возрастания");//
        try{
            LoadSaveSQL.loading();
            binding();
            System.out.println("Сервер готов");
            //GetAndSendMessage getAndSendMessage = new GetAndSendMessage();
            InputString inputString = new InputString();
            inputString.start();
            ForkJoinPool fork = (ForkJoinPool) Executors.newWorkStealingPool();
            try {
                server = DatagramChannel.open();
            } catch (IOException e) {
                e.printStackTrace();
            }
            InetSocketAddress iAdd = new InetSocketAddress("localhost", port);
            try {
                server.bind(iAdd);
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (true) {
                ByteBuffer intBuf = ByteBuffer.allocate(256);
                //receive buffer from client.
                SocketAddress remoteAdd = null;
                try {
                    MainServer.server.receive(intBuf);
                } catch (IOException e) {
                }
                intBuf.flip();
                int limitsInt = intBuf.limit();
                byte bytesInt[] = new byte[limitsInt];
                intBuf.get(bytesInt, 0, limitsInt);
                int capacity = 0;
                try {
                    capacity = (int) Serializer.deserialize(bytesInt);
                } catch (IOException | ClassNotFoundException e) {
                    //System.out.print("За");
                }
                ByteBuffer buffer = ByteBuffer.allocate(capacity);
                try {
                    remoteAdd = MainServer.server.receive(buffer);
                } catch (IOException e) {
                    //System.out.print("кры");
                }


                //change mode of buffer
                buffer.flip();
                GetMessage getMessage = new GetMessage(buffer,intBuf,remoteAdd);
                fork.submit(getMessage);
            }
        } catch (NoSuchElementException | IOException e){
            System.out.println("Выхожу из программы");
            LOGGER.log(Level.OFF, "Экстренный выход из программы");
            System.exit(0);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    private static void binding() throws IOException {
        System.out.println("Введите порт");
        try {
            port = Integer.parseInt(new Scanner(System.in).nextLine());
            //GetAndSendMessage.port = port;
        } catch (NumberFormatException e){
            System.out.println("Формат неправильный");
            binding();
            return;
        }

        try {
            server = DatagramChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InetSocketAddress iAdd = new InetSocketAddress("localhost", port);
        try {
            server.bind(iAdd);
        } catch (BindException e) {
            server.close();
            binding();
            return;
        }
        server.close();

    }
}

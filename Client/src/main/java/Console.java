import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.NoSuchElementException;
import java.util.Scanner;



public class Console {
    public static Scanner sc;
    public String[] splitCommand;
    public static int port;
    public static String username;

    public Console() throws IOException, ClassNotFoundException {

        sc = new Scanner(System.in);
    }

    public Console(String filename) throws FileNotFoundException {
        File file = new File(filename);
        sc = new Scanner(file);
    }

    public void authorization() throws IOException, ClassNotFoundException {
        boolean newOrNot = false;
        while (true){
            System.out.println("Вы хотите войти как новый пользватель? (Да/Нет)");
            switch (sc.nextLine()){
                case "Да":
                case "да":
                case "yes":
                    newOrNot = true;
                    break;
                case "Нет":
                case "нет":
                case "no":
                    newOrNot = false;
                    break;
                default:
                    continue;
            }
            break;
        }
        while (true){
            System.out.println("Введите логин (желательно, без пробелов)");
            LoginSendCommandUnex loginSendCommandUnex = new LoginSendCommandUnex();
            if(loginSendCommandUnex.checkCom(sc.nextLine().split(" "))){
                loginSendCommandUnex.newOrNot=newOrNot;
            }
            String result = sending(Serializer.serialize(loginSendCommandUnex));
            if(result.equals("2") || result.equals("3")){
                username = loginSendCommandUnex.user;
                break;
            }
        }
        while (true){
            System.out.println("Введите пароль (желательно, без пробелов)");
            PasswordSendUnex passwordSendUnex = new PasswordSendUnex();
            if(passwordSendUnex.checkCom(sc.nextLine().split(" "))){
                passwordSendUnex.newOrNot=newOrNot;
                passwordSendUnex.user = username;
            }
            String result = sending(Serializer.serialize(passwordSendUnex));
            if(result.equals("6") || result.equals("5")){
                break;
            }
        }


    }

    public void writeCommand() throws IOException, ClassNotFoundException {
        if(sc.equals(System.in)){
            ExecuteScript.AllScripts.clear();
        }
        System.out.println("Введите команду: ");

        String command = sc.nextLine();
        splitCommand = command.split(" ");
        String[] splitCommand2 = stringWithoutCom();
        Command sendingCom = null;
        switch (splitCommand[0]) {
            case "replace_if_lower"://
                sendingCom = new ReplaceIfLowerCommandUnex();
                break;
            case "replace_if_greater"://
                sendingCom = new ReplaceIfGreaterCommandUnex();
                break;
            case "remove_lower"://
                sendingCom = new RemoveLowerCommandUnex();
                break;
            case "print_ascending"://
                sendingCom = new PrintAscendingCommandUnex();
                break;
            case "max_by_transport"://
                sendingCom = new MaxTransportCommandUnex();
                break;
            case "update_id"://
                sendingCom = new UpdateIdCommandUnex();
                break;
            case "insert"://
                sendingCom = new InsertElementCommandUnex();
                break;
            case "execute_script":
                Scanner lastScanner = sc;
                ExecuteScript executeScript = new ExecuteScript();
                executeScript.execute(splitCommand2[0]);
                sc = lastScanner;
                return;
            case "sum_of_number_of_rooms"://
                sendingCom = new SumOfNumberOfRoomsCommandUnex();
                break;
            case "exit"://
                throw new NoSuchElementException();
            case "help"://
                sendingCom = new HelpCommandUnex();
                break;
            case "info"://
                sendingCom= new InfoCommandUnex();
                break;
            case "show"://
                sendingCom = new ShowCommandUnex();
                break;
            case "clear"://
                sendingCom = new ClearCommandUnex();
                break;
            case "remove_key"://
                sendingCom = new RemoveKeyCommandUnex();
                break;
            default:
                System.out.println("Таких команд я не понимаю, жалкий человек");
                return;
        }
        if(sendingCom.checkCom(splitCommand2)){
            sendingCom.user = username;
            byte[] bytes = Serializer.serialize(sendingCom);
            sending(bytes);
        }
    }
    private String[] stringWithoutCom(){
        String[] b = new String[splitCommand.length-1];
        for (int i = 0; i < splitCommand.length-1; i++) {
            b[i] = splitCommand[i+1];
        }
        return b;
    }
    public static String sending(byte[] bytes) throws IOException, ClassNotFoundException {

        DatagramChannel channel = null;
        channel = DatagramChannel.open();
        channel.bind(null);
        ByteBuffer buf = ByteBuffer.wrap(bytes);
        ByteBuffer intBuf = ByteBuffer.wrap(Serializer.serialize(buf.capacity()));
        InetSocketAddress address = new InetSocketAddress("localhost",port);
        CheckingConnection checkingConnection = new CheckingConnection();
        checkingConnection.start();
        channel.send(intBuf,address);
        channel.send(buf,address);
        System.out.println("Отправка и ожидание ответа");
        buf.clear();


        intBuf = ByteBuffer.allocate(256);

        channel.receive(intBuf);

        intBuf.flip();
        int limits = intBuf.limit();
        bytes = new byte[limits];
        intBuf.get(bytes, 0, limits);
        int capacity = (int) Serializer.deserialize(bytes);
        ByteBuffer buffer = ByteBuffer.allocate(capacity);
        channel.receive(buffer);
        checkingConnection.stop();
        System.out.println("Команда выполнена. Ответ сервера:");
        buffer.flip();
        int limits2 = buffer.limit();
        bytes = new byte[limits2];
        buffer.get(bytes, 0, limits2);
        switch (new String(bytes)){
            case "1":
                System.out.println("Уже есть пользователь с таким именем");
                break;
            case "2":
                System.out.println("Новый пользователь запомнен");
                break;
            case "3":
                System.out.println("Пользователь обнаружен");
                break;
            case "4":
                System.out.println("Нет пользователя с таким именем");
                break;
            case "5":
                System.out.println("Пользователь успешно авторизировался");
                break;
            case "6":
                System.out.println("Авторизация успешна, добро пожаловать");
                break;
            case "7":
                System.out.println("Пароль не подходит");
                break;
            default:
                System.out.println(new String(bytes));
        }
        buffer.clear();
        intBuf.clear();


        return new String(bytes);
    }
}

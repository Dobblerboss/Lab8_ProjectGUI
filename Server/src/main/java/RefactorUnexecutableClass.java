import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;

public class RefactorUnexecutableClass<T extends Command> implements Runnable {
    ByteBuffer intBuf;
    ByteBuffer buffer;
    SocketAddress remoteAdd;
    T thisCommandF;
    RefactorUnexecutableClass(SocketAddress remoteAdd, T thisCommandF, ByteBuffer intBuf, ByteBuffer buffer){
        this.remoteAdd = remoteAdd;
        this.thisCommandF = thisCommandF;
        this.buffer = buffer;
        this.intBuf = intBuf;
    }

    public Object executeCommand(T command) throws NoSuchAlgorithmException {
        String spilt = command.getClass().toString().split(" ")[1];
        switch (spilt){
            case "PasswordSendUnex":
                PasswordSendCommand passwordSendCommand = new PasswordSendCommand((PasswordSendUnex) command);
                MainServer.LOGGER.log(Level.INFO, "Конец авторизации юзера " + passwordSendCommand.user);
                return passwordSendCommand.execute();
            case "LoginSendCommandUnex":
                LoginSendCommand loginSendCommand = new LoginSendCommand((LoginSendCommandUnex) command);
                MainServer.LOGGER.log(Level.INFO, "Авторизация юзера " + loginSendCommand.user);
                return loginSendCommand.execute();
            case "LoginPassSendCommandUnex":
                LoginPassSendCommand loginPassSendCommand = new LoginPassSendCommand((LoginPassSendCommandUnex)command);
                MainServer.LOGGER.log(Level.INFO, "Авторизация юзера " + loginPassSendCommand.user);
                return loginPassSendCommand.execute();
            case "ClearCommandUnex"://
                ClearCommand clearCommand = new ClearCommand((ClearCommandUnex) command);
                MainServer.LOGGER.log(Level.INFO, "Выполнение команды clear");
                return clearCommand.execute();
            case "MaxTransportCommandUnex"://
                MaxTransportCommand maxTransportCommand = new MaxTransportCommand();
                MainServer.LOGGER.log(Level.INFO, "Выполнение команды max_by_transport");
                return maxTransportCommand.execute();
            case "RemoveKeyCommandUnex"://
                RemoveKeyCommand removeKeyCommand = new RemoveKeyCommand((RemoveKeyCommandUnex) command);
                MainServer.LOGGER.log(Level.INFO, "Выполнение команды remove_key");
                return removeKeyCommand.execute();
            case "InsertElementCommandUnex"://
                InsertElementCommand insertElementCommand = new InsertElementCommand((InsertElementCommandUnex)command);
                MainServer.LOGGER.log(Level.INFO, "Выполнение команды insert");
                return insertElementCommand.execute();
            case "UpdateIdCommandUnex"://
                UpdateIdCommand updateIdCommand = new UpdateIdCommand((UpdateIdCommandUnex)command);
                MainServer.LOGGER.log(Level.INFO, "Выполнение команды update_id");
                return updateIdCommand.execute();
            case "RemoveLowerCommandUnex"://
                RemoveLowerCommand removeLowerCommand = new RemoveLowerCommand((RemoveLowerCommandUnex)command);
                MainServer.LOGGER.log(Level.INFO, "Выполнение команды remove_lower");
                return removeLowerCommand.execute();
            case "ReplaceIfLowerCommandUnex":
                ReplaceIfLowerCommand replaceIfLowerCommand = new ReplaceIfLowerCommand((ReplaceIfLowerCommandUnex)command);
                MainServer.LOGGER.log(Level.INFO, "Выполнение команды replace_if_lower");
                return replaceIfLowerCommand.execute();
            case "ReplaceIfGreaterCommandUnex":
                ReplaceIfGreaterCommand replaceIfGreaterCommand = new ReplaceIfGreaterCommand((ReplaceIfGreaterCommandUnex)command);
                MainServer.LOGGER.log(Level.INFO, "Выполнение команды replace_if_greater");
                return replaceIfGreaterCommand.execute();
            case "PleaseSendAllCommandUnex":
                PleaseSendAllCommand pleaseSendAllCommand = new PleaseSendAllCommand();
                return pleaseSendAllCommand.execute();
            default:
                MainServer.LOGGER.log(Level.WARNING, "Всё плохо");
                return "Error";
        }
    }

    @Override
    public void run() {
        Object msgOb = null;
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            msgOb = executeCommand(thisCommandF);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        ExecutorService thread = Executors.newSingleThreadExecutor();
        if(msgOb instanceof String){
            String msg = (String) msgOb;
            SendMessage sendMessage = new SendMessage(remoteAdd, msg, intBuf, buffer);
            thread.submit(sendMessage);
        } else if(msgOb instanceof LinkedHashMap<?,?>){
            LinkedHashMap<Integer,Flat> msg = (LinkedHashMap<Integer, Flat>) msgOb;
            SendAllCommand sendAllCommand = new SendAllCommand(msg, intBuf, buffer);
            thread.submit(sendAllCommand);
        }

    }
}

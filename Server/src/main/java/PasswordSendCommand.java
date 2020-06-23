import java.security.NoSuchAlgorithmException;

public class PasswordSendCommand extends PasswordSendUnex implements ExecuteCommand {
    public PasswordSendCommand(PasswordSendUnex com) throws NoSuchAlgorithmException {
        user = com.user;
        password = Sha.hash256(com.password);
        newOrNot = com.newOrNot;
    }
    @Override
    public String execute() {
        if(newOrNot){
            MainServer.loginPass.put(user, password);
            return "5"; //"Пользователь успешно авторизировался"
        } else {
            String pass = MainServer.loginPass.get(user);
            if(pass.equals(password)){
                return "6"; //"Авторизация успешна, добро пожаловать"
            } else{
                return "7"; //"Пароль не подходит"
            }
        }
    }
}

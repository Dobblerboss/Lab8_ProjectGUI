import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicBoolean;

public class LoginPassSendCommand extends LoginPassSendCommandUnex implements ExecuteCommand {
    public LoginPassSendCommand(LoginPassSendCommandUnex com) throws NoSuchAlgorithmException {
        user = com.user;
        password = Sha.hash256(com.password);
        newOrNot = com.newOrNot;
    }
    @Override
    public String execute() {
        AtomicBoolean exist = new AtomicBoolean(false);
        MainServer.loginPass
                .entrySet()
                .stream()
                .forEach(x -> {
                    if(x.getKey().equals(user)){
                        exist.set(true);
                    }
                });
        if(newOrNot){
            if(exist.get()){
                return "1"; //"Уже есть пользователь с таким именем"
            } else {
                MainServer.loginPass.put(user, password);
                return "5"; //"Пользователь успешно авторизировался"
            }
        }else {
            if(exist.get()){
                String pass = MainServer.loginPass.get(user);
                if(pass.equals(password)){
                    return "6"; //"Авторизация успешна, добро пожаловать"
                } else{
                    return "7"; //"Пароль не подходит"
                }
            } else {
                return "4"; //"Нет пользователя с таким именем"
            }
        }
    }
}

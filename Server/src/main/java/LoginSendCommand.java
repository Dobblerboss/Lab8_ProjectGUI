import java.util.concurrent.atomic.AtomicBoolean;

public class LoginSendCommand extends LoginSendCommandUnex implements ExecuteCommand {
    public LoginSendCommand(LoginSendCommandUnex com){
        user = com.user;
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
                MainServer.loginPass.put(user," ");
                return "2"; //"Новый пользователь запомнен"
            }
        }else {
            if(exist.get()){
                return "3"; //"Пользователь обнаружен"
            } else {
                return "4"; //"Нет пользователя с таким именем"
            }
        }
    }
}

public class LoginSendCommandUnex extends Command {
    boolean newOrNot;
    @Override
    public boolean checkCom(String[] s) {
        if(s.length == 1){
            user = s[0];
            return true;
        } else {
            return false;
        }
    }
}

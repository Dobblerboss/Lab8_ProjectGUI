public class LoginPassSendCommandUnex extends Command{
    public String password;
    public boolean newOrNot;
    @Override
    public boolean checkCom(String[] s) {
        if(s.length == 2){
            user = s[0];
            password = s[1];
            return true;
        } else {
            return false;
        }
    }
}

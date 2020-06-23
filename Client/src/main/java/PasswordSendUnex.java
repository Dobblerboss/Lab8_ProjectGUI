public class PasswordSendUnex extends Command {
    public String password;
    public boolean newOrNot;
    @Override
    public boolean checkCom(String[] s) {
        if(s.length == 1){
            password = s[0];
            return true;

        } else {
            return false;
        }
    }
}

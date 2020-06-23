import java.io.Serializable;

public abstract class Command implements Serializable {
    public String user;
    public abstract boolean checkCom(String[] s);
    public Command() {
    }
}

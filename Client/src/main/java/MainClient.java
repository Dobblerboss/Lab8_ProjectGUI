import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class MainClient {

    public static LinkedHashMap<Integer, Flat> memoryFlats = new LinkedHashMap<>();
    public static String username;
    public static MainWindow mainWindow;
    public static LinkedHashMap<String, Color> colorize = new LinkedHashMap<>();
    public static ResourceBundle stats;
    public static Locale[] supportedLocales;
    private static int i = 0;
    public static Locale currentLocale;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Клиентское приложение запущено");
        supportedLocales = new Locale[]{
                new Locale("ru", "RU"),
                new Locale("en", "IE"),
                new Locale("pl", "PL"),
                new Locale("sk", "SK")
        };
        currentLocale = supportedLocales[i];
        stats = ResourceBundle.getBundle("Lables", currentLocale);
        InputMessage inputMessage = new InputMessage();
        inputMessage.start();
        binding();
    }

    public static void addAndColorize(LinkedHashMap<Integer, Flat> newFlats){
        memoryFlats = new LinkedHashMap<>();
        newFlats.entrySet().stream().forEach(x ->{
            memoryFlats.put(x.getKey(), x.getValue());
            if(colorize.get(x.getValue().getUser())==null){
                colorize.put(x.getValue().getUser(), new Color((int)(Math.random()*256), (int) (Math.random() * 256), (int)(Math.random()*256)));
            }
        });
    }

    private static void binding()  {
        SelectLanguageStart selectLanguageStart = new SelectLanguageStart();
        selectLanguageStart.setVisible(true);
    }

    public static void nextLocale(){
        i++;
        if(i==supportedLocales.length) i = 0;
        currentLocale = supportedLocales[i];
        stats = ResourceBundle.getBundle("Lables", currentLocale);
    }

}

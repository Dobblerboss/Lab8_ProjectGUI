import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;

public class InputString extends Thread {
    public void run(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            String s = scanner.nextLine();
            switch (s){
                case "save":
                    System.out.println("Сохраняю");
                    MainServer.LOGGER.log(Level.INFO, "Сохранение текущей коллекции");
                    try {
                        LoadSaveSQL.saving();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    //saveCommand.execute();
                    break;
                case "exit":
                    System.out.println("Выхожу с сохранением");
                    MainServer.LOGGER.log(Level.OFF, "Завершение с последующим сохранением коллекции");
                    try {
                        LoadSaveSQL.saving();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        MainServer.server.close();
                    } catch (Exception e) {
                    }
                    System.exit(0);
                    break;
            }
        }
    }
}

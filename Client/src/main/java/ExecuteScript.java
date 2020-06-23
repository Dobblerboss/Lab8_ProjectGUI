import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;


public class ExecuteScript {
    static HashMap<String,Boolean> AllScripts = new HashMap<>();
    public void execute(String filename) {
//        try {
//            Boolean all = AllScripts.get(filename);
//            try {
//                if (all.equals(false)) {
//                    all = false;
//                }
//            } catch (NullPointerException e) {
//                all = false;
//            }
//            if (all) {
//                System.out.println("Цикл обнаружен. Отказ выполнения цикла");
//                return;
//            }
//            AllScripts.put(filename, true);
////            while (Console.sc.hasNextLine()){
////                writeCommand();
////            }
//        } catch (FileNotFoundException e){
//            System.out.println("Файл скрипта с таким именем не найден");
//            return;
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        System.out.println("Скрипт в файле " + filename + " исполнен");
        AllScripts.put(filename, false);
    }
}

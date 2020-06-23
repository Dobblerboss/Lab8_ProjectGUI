import org.json.simple.*;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.Scanner;

public class SaveCommand {
    public void execute(){
        JSONArray toFileArray = new JSONArray();

        MainServer.flats.entrySet().stream()
                .forEach(pair->{
                    JSONObject toJsonArrayObj = new JSONObject();
                    Flat valueFromPair = pair.getValue();
                    ZonedDateTime dateTime = valueFromPair.getCreationDate();
                    try {
                        toJsonArrayObj.put("key", pair.getKey());
                        toJsonArrayObj.put("name", valueFromPair.getName());
                        toJsonArrayObj.put("id", valueFromPair.getId());
                        toJsonArrayObj.put("coordinate_x", valueFromPair.getCoordinates().getX());
                        toJsonArrayObj.put("coordinate_y", valueFromPair.getCoordinates().getY());
                        toJsonArrayObj.put("area", valueFromPair.getArea());
                        toJsonArrayObj.put("number_of_rooms", valueFromPair.getNumberOfRooms());
                        toJsonArrayObj.put("furnish", valueFromPair.getFurnish().toString());
                        toJsonArrayObj.put("new_or_not", valueFromPair.getNewOrNot());
                        toJsonArrayObj.put("transport", valueFromPair.getTransport().toString());
                        toJsonArrayObj.put("house_name", valueFromPair.getHouse().getName());
                        toJsonArrayObj.put("house_year", valueFromPair.getHouse().getYear());
                        toJsonArrayObj.put("house_number_of_floors", valueFromPair.getHouse().getNumberOfFloors());
                        toJsonArrayObj.put("year",dateTime.getYear());
                        toJsonArrayObj.put("month",dateTime.getMonthValue());
                        toJsonArrayObj.put("day",dateTime.getDayOfMonth());
                        toJsonArrayObj.put("hour",dateTime.getHour());
                        toJsonArrayObj.put("minute",dateTime.getMinute());
                        toJsonArrayObj.put("second",dateTime.getSecond());
                        toJsonArrayObj.put("nano",dateTime.getNano());
                        toJsonArrayObj.put("zone",dateTime.getZone().toString());
                        toFileArray.add(toJsonArrayObj);
                    } catch (Exception var11) {
                        System.out.println("Какое-то поле не заполнено");
                        return;
                    }
                });
        save(toFileArray, MainServer.pathname);
    }
    private void save(JSONArray toFileArray, String path){
        try {
            if (!path.endsWith(".json")){
                System.out.println("Нужен json формат");
                throw new FileNotFoundException();
            }
            File finalFile = new File(path);
            FileWriter writer = new FileWriter(finalFile);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write("{"+"\"FLATS\":"+toFileArray.toJSONString()+" }");
            bufferedWriter.close();
            System.out.println("Сохранено");
        } catch (FileNotFoundException var12) {
            System.out.println("Введите имя файла/путь(json формата)");
            path = new Scanner(System.in).nextLine();
            save(toFileArray, path);
        } catch (IOException var13) {
            var13.printStackTrace();
        }
    }
}

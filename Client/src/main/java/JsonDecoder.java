import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class JsonDecoder {
    private JSONObject jsonFlat;
    private Flat decodeValue;
    private Integer decodeKey;

    JsonDecoder(String jsonFlat){
        try {
            JSONParser parser = new JSONParser();
            this.jsonFlat = (JSONObject) parser.parse(jsonFlat);
        } catch (ParseException e){
            System.out.println("Что-то не так с json строкой");
        } catch (ClassCastException e){
            System.out.println("Не издевайтесь над строкой");
        }
    }
    public Integer getDecodeKey(){

        decodeKey = getInteger("key");
        return decodeKey;
    }
    private Long id;
    private String nameFlat;
    private Float x;
    private Float y;
    private Coordinates coordinates;
    private Integer area;
    private Long number_of_rooms;
    private Furnish furnish;
    private Boolean new_or_not;
    private Transport transport;
    private String house_name;
    private Long house_year;
    private Long house_number_of_floors;
    private House house;
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hour;
    private Integer minute;
    private Integer second;
    private Integer nano;
    private ZoneId zoneId;
    private ZonedDateTime creationDate;


    private void SetAllExceptId(){
        nameFlat = getString("name");
        x = getFloat("coordinate_x");
        y = getFloat("coordinate_y");
        coordinates = new Coordinates(x,y);
        area = getInteger("area");
        number_of_rooms = getLong("number_of_rooms");
        furnish = Furnish.getByName(getString("furnish"));
        new_or_not = getBoolean("new_or_not");
        transport = Transport.getByName(getString("transport"));
        house_name = getString("house_name");
        house_year = getLong("house_year");
        house_number_of_floors = getLong("house_number_of_floors");
        house = new House(house_name,house_year,house_number_of_floors);
        year = getInteger("year");
        month = getInteger("month");
        day = getInteger("day");
        hour = getInteger("hour");
        minute = getInteger("minute");
        second = getInteger("second");
        nano = getInteger("nano");
        zoneId = ZoneId.of(getString("zone"));
        creationDate =  ZonedDateTime.of(year,month,day,hour,minute,second,nano,zoneId);
    }
    private void SetId(){
        id = getLong("id");
    }

    public Flat getDecodeValue(){
        SetAllExceptId();
        SetId();
        decodeValue = new Flat(id,nameFlat,coordinates, creationDate,area,number_of_rooms,new_or_not,furnish,transport,house);
        return decodeValue;
    }
    public Flat getDecodeValueWithoutId(){
        SetAllExceptId();
        decodeValue = new Flat(nameFlat,coordinates, creationDate,area,number_of_rooms,new_or_not,furnish,transport,house);
        return decodeValue;
    }
    private String getString(String key){
        return (String) jsonFlat.get(key);
    }

    private Long getLong(String key){
        return Long.parseLong(jsonFlat.get(key).toString());
    }

    private Boolean getBoolean(String key){ return (Boolean) jsonFlat.get(key); }

    private Float getFloat(String key){
        return Float.parseFloat(jsonFlat.get(key).toString());
    }

    private Integer getInteger(String key){
        return Integer.parseInt(jsonFlat.get(key).toString());
    }
}

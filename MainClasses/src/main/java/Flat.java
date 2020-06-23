//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Random;


public class Flat implements Comparable<Flat>, Serializable {
    private Long id;
    private String name;
    private Coordinates coordinates = new Coordinates();
    private ZonedDateTime creationDate;
    private Integer area;
    private Long numberOfRooms;
    private Boolean newOrNot;
    private Furnish furnish;
    private Transport transport;
    private House house = new House();

    private String user;

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public Flat(String name, Coordinates coordinates, Integer area, Long numberOfRooms, Boolean newOrNot, Furnish furnish, Transport transport, House house){
        final Random random = new Random();
        this.id = Math.abs(random.nextLong());
        this.name = name;
        this.coordinates = coordinates;
        setCreationDate();
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.newOrNot = newOrNot;
        this.furnish = furnish;
        this.transport = transport;
        this.house = house;
    }

    public Flat(String name, Coordinates coordinates, ZonedDateTime creationDate, Integer area, Long numberOfRooms, Boolean newOrNot, Furnish furnish, Transport transport, House house){
        final Random random = new Random();
        this.id = Math.abs(random.nextLong());
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.newOrNot = newOrNot;
        this.furnish = furnish;
        this.transport = transport;
        this.house = house;
    }
    public Flat(Long id, String name, Coordinates coordinates, ZonedDateTime creationDate, Integer area, Long numberOfRooms, Boolean newOrNot, Furnish furnish, Transport transport, House house) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.newOrNot = newOrNot;
        this.furnish = furnish;
        this.transport = transport;
        this.house = house;
    }

    public Flat() {
        final Random random = new Random();
        this.id = Math.abs(random.nextLong());
        creationDate = ZonedDateTime.now();

    }



    public void setCoordinates(Float x, Float y) {
        this.coordinates.setX(x);
        this.coordinates.setY(y);
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public void setNumberOfRooms(Long numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public void setNewOrNot(Boolean newOrNot) {
        this.newOrNot = newOrNot;
    }

    public void setFurnish(String condition) {
        this.furnish = Furnish.getByName(condition);
    }

    public void setTransport(String condition) {
        this.transport = Transport.getByName(condition);
    }

    public void setHouse(String name, Long year, Long numberOfFloors) {
        this.house.setName(name);
        this.house.setYear(year);
        this.house.setNumberOfFloors(numberOfFloors);

    }

    public boolean setName(String name) {
        if (name != null && !name.equals("")) {
            this.name = name;
            return true;
        } else {
            return false;
        }
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) { this.id = id; }

    public String getName() {
        return this.name;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return this.creationDate;
    }
    public void setCreationDate(){creationDate = ZonedDateTime.now();}

    public Integer getArea() {
        return this.area;
    }

    public Long getNumberOfRooms() {
        return this.numberOfRooms;
    }

    public Boolean getNewOrNot() {
        return this.newOrNot;
    }

    public Furnish getFurnish() {
        return this.furnish;
    }

    public Transport getTransport() {
        return this.transport;
    }

    public House getHouse() {
        return this.house;
    }

    @Override
    public int compareTo(Flat o) {
        int what_return = name.compareTo(o.name);
        if(what_return!=0){ return what_return;}
        what_return = coordinates.compareTo(o.coordinates);
        if(what_return!=0){return  what_return;}
        what_return = area.compareTo(o.area);
        if(what_return!=0){return  what_return;}
        what_return =  numberOfRooms.compareTo(o.numberOfRooms);
        if(what_return!=0){return  what_return;}
        what_return = newOrNot.compareTo(o.newOrNot);
        if(what_return!=0){return  what_return;}
        what_return = furnish.compareTo(o.furnish);
        if(what_return!=0){return  what_return;}
        what_return = transport.compareTo(o.transport);
        if(what_return!=0){return  what_return;}
        what_return = house.compareTo(o.house);
        if(what_return!=0){ return what_return;}
        /*what_return = creationDate.compareTo(o.creationDate);
        if(what_return!=0){return what_return;}*/
        return 0;
    }

    public String toString() {
        return "Flat \n\t id:" + this.id + "\n\t Name : " + this.name + "\n\t Coordinates : " + this.coordinates + "\n\t Date : " + this.creationDate + "\n\t Area : " + this.area + "\n\t Number of rooms : " + this.numberOfRooms + "\n\t New or not : " + this.newOrNot + "\n\t Furnish : " + this.furnish + "\n\t Transport : " + this.transport + "\n\t House : " + this.house + "\n";
    }
}

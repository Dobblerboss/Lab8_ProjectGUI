//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.Serializable;

public class House implements Comparable<House>, Serializable {
    private String name;
    private Long year;
    private Long numberOfFloors;

    public House() {
    }

    public House(String name, Long year, Long numberOfFloors) {
        this.name = name;
        this.year = year;
        this.numberOfFloors = numberOfFloors;
    }

    public String getName() {
        return name;
    }

    public Long getYear() {
        return year;
    }

    public Long getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public void setNumberOfFloors(Long numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }



    @Override
    public String toString() {
        return "House {" +
                "\n\t\t name : " + name +
                "\n\t\t year : " + year +
                "\n\t\t numberOfFloors : " + numberOfFloors +
                "\n\t }";
    }

    @Override
    public int compareTo(House o) {
        int what_return = name.compareTo(o.name);
        if(what_return!=0){ return what_return;}
        what_return = year.compareTo(o.year);
        if(what_return!=0){ return what_return;}
        what_return = numberOfFloors.compareTo(o.numberOfFloors);
        if(what_return!=0){ return what_return;}
        return 0;
    }
}

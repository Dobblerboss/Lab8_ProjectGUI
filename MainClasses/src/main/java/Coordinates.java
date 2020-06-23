//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.Serializable;
import java.util.Objects;

public class Coordinates implements Comparable<Coordinates>, Serializable {
    private Float x;
    private Float y;

    public Coordinates() {
    }

    public Coordinates(Float x, Float y) {
        this.x = x;
        this.y = y;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getX() {
        return this.x;
    }

    public Float getY() {
        return this.y;
    }

    public int compareTo(Coordinates o) {
        return (int)((double)(this.x * this.x) + this.y * this.y - (double)(o.getX() * o.getX()) - o.getY() * o.getY());
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Coordinates)) {
            return false;
        } else {
            Coordinates that = (Coordinates)o;
            return this.getX() == that.getX() && this.getY().equals(that.getY());
        }
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "\n\t\t x = " + x +
                "\n\t\t y = " + y +
                "\n\t }";
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

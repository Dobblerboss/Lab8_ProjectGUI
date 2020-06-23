import java.io.Serializable;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
public enum Furnish implements Serializable {
    NONE,
    FINE,
    LITTLE;

    Furnish() {
    }

    public static boolean contains(String furnish) {
        if (furnish != null && !furnish.equals("")) {
            furnish = furnish.toUpperCase();
            return furnish.equals("NONE") || furnish.equals("FINE") || furnish.equals("LITTLE");
        } else {
            return false;
        }
    }

    public static Furnish getByName(String furnish) {

        switch(furnish) {
            case "NONE":
                return NONE;
            case "FINE":
                return FINE;
            case "LITTLE":
                return LITTLE;
            default:
                return null;
        }
    }

    public static String furnishToString() {
        return "NONE, FINE, LITTLE";
    }
}

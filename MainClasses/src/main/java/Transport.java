//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.Serializable;

public enum Transport implements Serializable {
    NONE,
    FEW,
    NORMAL,
    ENOUGH;


    private Transport() {
    }

    public static boolean contains(String transport) {
        if (transport != null && !transport.equals("")) {
            transport = transport.toUpperCase();
            return transport.equals("FEW") || transport.equals("NONE") || transport.equals("NORMAL") || transport.equals("ENOUGH");
        } else {
            return false;
        }
    }

    public static Transport getByName(String transport) {
        switch(transport) {
            case "FEW":
                return FEW;
            case "NONE":
                return NONE;
            case "NORMAL":
                return NORMAL;
            case "ENOUGH":
                return ENOUGH;
            default:
                return null;
        }
    }

    public static String transportToString() {
        return "FEW, NONE, NORMAL, ENOUGH";
    }


}

package unsw.blackout;

import java.util.ArrayList;

public class satType {
    public String type;
    public ArrayList<String> deviceSup = new ArrayList<String>();
    public int range;
    public Store store;
    public Speed speed;

    public String getType() {
        return type;
    }

    public ArrayList<String> getdeviceSup() {
        return deviceSup;
    }

    public int getRange() {
        return range;
    }

    public Store getStore() {
        return store;
    }

    public Speed getSpeed() {
        return speed;
    }
}

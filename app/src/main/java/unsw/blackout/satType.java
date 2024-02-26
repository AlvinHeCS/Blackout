package unsw.blackout;

import java.util.ArrayList;

public class satType {
    private String type;
    private ArrayList<Device> deviceSup = new ArrayList<Device>();
    private int range;
    private Store store;
    private Speed speed;

    public String getType() {
        return type;
    }

    public ArrayList<Device> getdeviceSup() {
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

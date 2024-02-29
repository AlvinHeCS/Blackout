package unsw.blackout;

import unsw.utils.Angle;
import java.util.ArrayList;

public class Satellite {
    private String name;
    private satType type;
    private Position ultPosition;
    private ArrayList<File> files = new ArrayList<File>();

    public Satellite(String name) {
        this.name = name;
    }

    public boolean addFile(File file) {
        if ((this.files).size() >= (this.type).getStore().getfileLimit()
                || (file.getSize() > (this.type.getStore().getsizeLimit()))) {
            return false;
        }
        this.files.add(file);
        return true;

    }

    public void setPosition(double height, Angle position) {
        this.ultPosition = new Position(height, position);
    }

    public String getName() {
        return name;
    }

    public void setsatType(String satType) {
        switch (satType) {
        case "StandardSatellite":
            this.type = new Standardsat();
            break;
        case "TeleportingSatellite":
            this.type = new Teleportingsat();
            break;
        case "RelaySatellite":
            this.type = new Relaysat();
            break;
        default:
            break;
        }
    }

}

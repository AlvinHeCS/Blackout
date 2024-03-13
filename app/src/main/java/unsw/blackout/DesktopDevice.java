package unsw.blackout;

import java.util.ArrayList;
import java.util.Arrays;

import unsw.utils.Angle;

public class DesktopDevice extends Device {
    public DesktopDevice(String name, Angle degree) {
        super(name, degree);
        this.setType("DesktopDevice");
        this.setEntitySupported(new ArrayList<String>(Arrays.asList("TeleportingSatellite", "RelaySatellite")));
        this.setRange(200000);
    }
}

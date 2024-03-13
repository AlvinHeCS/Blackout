package unsw.blackout;

import java.util.ArrayList;
import java.util.Arrays;

import unsw.utils.Angle;

public class LaptopDevice extends Device {
    public LaptopDevice(String name, Angle degree) {
        super(name, degree);
        this.setType("LaptopDevice");
        this.setEntitySupported(
                new ArrayList<String>(Arrays.asList("StandardSatellite", "TeleportingSatellite", "RelaySatellite")));
        this.setRange(100000);
    }
}

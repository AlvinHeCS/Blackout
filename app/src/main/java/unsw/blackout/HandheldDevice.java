package unsw.blackout;

import java.util.ArrayList;
import java.util.Arrays;

import unsw.utils.Angle;

public class HandheldDevice extends Device {
    public HandheldDevice(String name, Angle degree) {
        super(name, degree);
        this.setType("HandheldDevice");
        this.setEntitySupported(
                new ArrayList<String>(Arrays.asList("StandardSatellite", "TeleportingSatellite", "RelaySatellite")));
        this.setRange(50000);
    }
}

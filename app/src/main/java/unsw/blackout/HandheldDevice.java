package unsw.blackout;

import unsw.utils.Angle;

public class HandheldDevice extends Device {
    public HandheldDevice(String deviceId, Angle position) {
        setName(deviceId);
        setType("HandheldDevice");
        setDegree(position);
        setRange(50000);
    }
}

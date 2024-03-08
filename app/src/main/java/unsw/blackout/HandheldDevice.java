package unsw.blackout;

import unsw.utils.Angle;

public class HandheldDevice extends Device {
    public HandheldDevice(String deviceId, String type, Angle position) {
        setName(deviceId);
        setDegree(position);
        setRange(50000);
    }
}

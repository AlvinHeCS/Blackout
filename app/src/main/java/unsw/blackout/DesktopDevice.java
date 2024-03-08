package unsw.blackout;

import unsw.utils.Angle;

public class DesktopDevice extends Device {
    public DesktopDevice(String deviceId, String type, Angle position) {
        setName(deviceId);
        setDegree(position);
        setRange(200000);
    }
}

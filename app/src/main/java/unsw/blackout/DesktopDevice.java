package unsw.blackout;

import unsw.utils.Angle;

public class DesktopDevice extends Device {
    public DesktopDevice(String deviceId, Angle position) {
        setName(deviceId);
        setType("DesktopDevice");
        setDegree(position);
        setRange(200000);
    }
}

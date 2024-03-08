package unsw.blackout;

import unsw.utils.Angle;

public class LaptopDevice extends Device {
    public LaptopDevice(String deviceId, String type, Angle position) {
        setName(deviceId);
        setDegree(position);
        setRange(100000);
    }
}

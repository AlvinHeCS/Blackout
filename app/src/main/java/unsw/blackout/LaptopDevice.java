package unsw.blackout;

import unsw.utils.Angle;

public class LaptopDevice extends Device {
    public LaptopDevice(String deviceId, Angle position) {
        setName(deviceId);
        setType("LaptopDevice");
        setDegree(position);
        setRange(100000);
    }
}

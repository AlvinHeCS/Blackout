package unsw.blackout;

import unsw.utils.Angle;

public class RelaySatellite extends Satellite {
    public RelaySatellite(String satelliteId, String type, double height, Angle position) {
        setName(satelliteId);
        setDirection("clockwise");
        setDegree(position);
        setRange(300000);
        setHeight(height);
        setDeviceSup({"ALL"});
        setLinearSpeed(1500);
    }
}

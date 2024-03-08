package unsw.blackout;

import unsw.utils.Angle;

public class StandardSatellite extends Satellite implements fileSatellite {
    public StandardSatellite(String satelliteId, String type, double height, Angle position) {
        setName(satelliteId);
        setDirection("clockwise");
        setDegree(position);
        setRange(300000);
        setHeight(height);
        setDeviceSup({"ALL"});
        setLinearSpeed(1500);
    }

}

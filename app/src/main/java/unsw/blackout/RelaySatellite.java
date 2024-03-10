package unsw.blackout;

import unsw.utils.Angle;

public class RelaySatellite extends Satellite {
    public RelaySatellite(String satelliteId, double height, Angle position) {
        setName(satelliteId);
        setType("RelaySatellite");
        if (position.toDegrees() < 345) {
            setDirection("anticlockwise");
        } else {
            setDirection("clockwise");
        }
        setDegree(position);
        setRange(300000);
        setHeight(height);
        setLinearSpeed(15000);
    }

}

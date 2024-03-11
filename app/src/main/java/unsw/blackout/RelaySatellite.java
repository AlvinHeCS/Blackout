package unsw.blackout;

import java.util.ArrayList;

import unsw.utils.Angle;

public class RelaySatellite extends Satellite {
    public RelaySatellite(String satelliteId, double height, Angle position) {
        setName(satelliteId);
        setType("RelaySatellite");
        setDirection("clockwise");
        setDegree(position);
        ArrayList<String> devices = new ArrayList<>();
        devices.add("");
        setRange(300000);
        setHeight(height);
        setLinearSpeed(1500);
    }

    public void relaySatelliteMovement() {
        double degree = this.getDegree().toDegrees();
        if (degree < 140 && degree >= 0 || degree >= 345 && degree < 360) {
            this.setDirection("anticlockwise");
        } else if (degree > 190 && degree < 345) {
            this.setDirection("clockwise");
        }
        super.movement();

    }
}

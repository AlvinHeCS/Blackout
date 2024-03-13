package unsw.blackout;

import java.util.ArrayList;
import java.util.Arrays;

import unsw.utils.Angle;

public class RelaySatellite extends Satellite {
    public RelaySatellite(String satelliteId, double height, Angle position) {
        super(satelliteId, height, position);
        setType("RelaySatellite");
        setDirection("clockwise");
        this.setEntitySupported(new ArrayList<String>(Arrays.asList("StandardSatellite", "TeleportingSatellite",
                "RelaySatellite", "HandheldDevice", "LaptopDevice", "DesktopDevice")));
        setRange(300000);
        setLinearSpeed(1500);
        setFileLimit(2147483647, 200);
        setFileTransferSpeeds(15, 10);
    }

    @Override
    public void movement() {
        double degree = this.getDegree().toDegrees();
        if (degree < 140 && degree >= 0 || degree >= 345 && degree < 360) {
            this.setDirection("anticlockwise");
        } else if (degree > 190 && degree < 345) {
            this.setDirection("clockwise");
        }
        double angularVelocity = this.getLinearSpeed() / this.getHeight();
        if (this.getDirection().equals("clockwise")) {
            this.clockwise(angularVelocity);
        } else {
            this.anticlockwise(angularVelocity);
        }

    }
}

package unsw.blackout;

import unsw.utils.Angle;
import java.util.ArrayList;
import java.util.Arrays;

public class StandardSatellite extends Satellite {
    public StandardSatellite(String satelliteId, double height, Angle position) {
        super(satelliteId, height, position);
        setType("StandardSatellite");
        setDirection("clockwise");
        setRange(150000);
        setLinearSpeed(2500);
        setFileLimit(3, 80);
        setFileTransferSpeeds(1, 1);
        this.setEntitySupported(new ArrayList<String>(Arrays.asList("StandardSatellite", "TeleportingSatellite",
                "RelaySatellite", "HandheldDevice", "LaptopDevice")));
    }

    @Override
    public void movement() {
        double angularVelocity = this.getLinearSpeed() / this.getHeight();
        this.clockwise(angularVelocity);
    }
}

package unsw.blackout;

import unsw.utils.Angle;
import java.util.ArrayList;
import java.util.Arrays;

public class TeleportingSatellite extends Satellite {
    public TeleportingSatellite(String satelliteId, double height, Angle position) {
        super(satelliteId, height, position);
        setType("TeleportingSatellite");
        setDirection("anticlockwise");
        setRange(200000);
        setLinearSpeed(1000);
        setFileLimit(MAX, MAX);
        setFileTransferSpeeds(15, 10);
        this.setEntitySupported(new ArrayList<String>(Arrays.asList("StandardSatellite", "TeleportingSatellite",
                "RelaySatellite", "HandheldDevice", "LaptopDevice", "DesktopDevice")));
    }

    @Override
    public void movement() {
        double angularVelocity = this.getLinearSpeed() / this.getHeight();
        System.out.println(this.getDegree().toRadians() + angularVelocity);
        System.out.println(this.getDegree().toDegrees());
        if (this.getDirection().equals("anticlockwise") && this.getDegree().toRadians() + angularVelocity >= Math.PI
                && this.getDegree().toRadians() < Math.PI) {
            this.setDirection("clockwise");
            this.setDegree(Angle.fromDegrees(360));
            return;
        }
        if (this.getDirection().equals("clockwise") && this.getDegree().toRadians() - angularVelocity <= Math.PI
                && this.getDegree().toRadians() > Math.PI) {
            this.setDirection("anticlockwise");
            this.setDegree(Angle.fromDegrees(0));
            return;
        }

        angularVelocity = this.getLinearSpeed() / this.getHeight();
        if (this.getDirection().equals("clockwise")) {
            this.clockwise(angularVelocity);
        } else {
            this.anticlockwise(angularVelocity);
        }
    }
}

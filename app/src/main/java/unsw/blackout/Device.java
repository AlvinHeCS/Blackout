package unsw.blackout;

import static unsw.utils.MathsHelper.RADIUS_OF_JUPITER;

import unsw.utils.Angle;

public abstract class Device extends Entity {
    public Device(String name, Angle degree) {
        super(name, RADIUS_OF_JUPITER, degree);
        setFileLimit(MAX, MAX);
        setFileTransferSpeeds(MAX, MAX);
    }

}

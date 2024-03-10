package unsw.blackout;

import unsw.utils.Angle;
import java.util.ArrayList;

public abstract class Satellite {
    private String name;
    private String direction;
    private double height;
    private Angle degree;
    private ArrayList<String> deviceNotSup = new ArrayList<String>();
    private int range;
    private int linearSpeed;
    private String type;

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Angle getDegree() {
        return this.degree;
    }

    public void setDegree(Angle degree) {
        this.degree = degree;
    }

    public ArrayList<String> getDeviceNotSup() {
        return this.deviceNotSup;
    }

    public void setDeviceNotSup(ArrayList<String> deviceNotSup) {
        this.deviceNotSup = deviceNotSup;
    }

    public int getRange() {
        return this.range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getLinearSpeed() {
        return this.linearSpeed;
    }

    public void setLinearSpeed(int linearSpeed) {
        this.linearSpeed = linearSpeed;
    }

    private void clockwise(double angularVelocity) {
        if (this.getDegree().toRadians() - angularVelocity <= 0) {
            this.setDegree(Angle.fromRadians(2 * Math.PI + (this.getDegree().toRadians() - angularVelocity)));
        } else {
            this.setDegree(Angle.fromRadians(this.getDegree().toRadians() - angularVelocity));
        }
    }

    private void anticlockwise(double angularVelocity) {
        if (this.getDegree().toRadians() + angularVelocity > 2 * Math.PI) {
            this.setDegree(Angle.fromRadians((this.getDegree().toRadians() + angularVelocity) - 2 * Math.PI));
        } else {
            this.setDegree(Angle.fromRadians(this.getDegree().toRadians() + angularVelocity));
        }
    }

    public void movement() {
        double angularVelocity = this.linearSpeed / this.height;
        if (this.direction.equals("clockwise")) {
            this.clockwise(angularVelocity);
        } else {
            this.anticlockwise(angularVelocity);
        }
    }
}

package unsw.blackout;

import unsw.utils.Angle;

public abstract class Satellite extends Entity {
    private int linearSpeed;
    private String direction;

    public Satellite(String name, double height, Angle degree) {
        super(name, height, degree);
        this.setLinearSpeed(linearSpeed);
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getLinearSpeed() {
        return this.linearSpeed;
    }

    public void setLinearSpeed(int linearSpeed) {
        this.linearSpeed = linearSpeed;
    }

    public void clockwise(double angularVelocity) {
        if (this.getDegree().toRadians() - angularVelocity <= 0) {
            this.setDegree(Angle.fromRadians(2 * Math.PI + (this.getDegree().toRadians() - angularVelocity)));
        } else {
            this.setDegree(Angle.fromRadians(this.getDegree().toRadians() - angularVelocity));
        }
    }

    public void anticlockwise(double angularVelocity) {
        if (this.getDegree().toRadians() + angularVelocity > 2 * Math.PI) {
            this.setDegree(Angle.fromRadians((this.getDegree().toRadians() + angularVelocity) - 2 * Math.PI));
        } else {
            this.setDegree(Angle.fromRadians(this.getDegree().toRadians() + angularVelocity));
        }
    }

    public abstract void movement();
}

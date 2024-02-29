package unsw.blackout;

import unsw.utils.Angle;

public class Position {
    // anti-clockwise or clockwise
    //private String direction;
    private double height;
    private Angle degree;

    public Position(double height, Angle degree) {
        //this.direction = direction;
        this.height = height;
        this.degree = degree;
    }

    public Angle getDegree() {
        return degree;
    }

    public double getHeight() {
        return height;
    }
}

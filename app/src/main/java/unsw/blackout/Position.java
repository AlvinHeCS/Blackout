package unsw.blackout;

import unsw.utils.Angle;

public class Position {
    // anti-clockwise or clockwise
    private String direction;
    private int height;
    private Angle degree;

    public Position(String direction, int height, Angle degree) {
        this.direction = direction;
        this.height = height;
        this.degree = degree;
    }

    public Angle getDegree() {
        return degree;
    }

    public String getDirection() {
        return direction;
    }

    public int getHeight() {
        return height;
    }
}

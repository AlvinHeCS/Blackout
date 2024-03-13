package unsw.blackout;

import unsw.utils.Angle;

public abstract class Satellite extends Entity implements fileSatellite {
    private int linearSpeed;
    private String direction;
    private int[] fileLimit = new int[2];
    private int[] fileTransferSpeed = new int[2];

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

    public int[] getFileLimit() {
        return this.fileLimit;
    }

    public void setFileLimit(int fileQuantity, int fileSize) {
        this.fileLimit[0] = fileQuantity;
        this.fileLimit[1] = fileSize;
    }

    public int[] getFileTransferSpeeds() {
        return fileTransferSpeed;
    }

    public void setFileTransferSpeeds(int recieving, int sending) {
        this.fileTransferSpeed[0] = recieving;
        this.fileTransferSpeed[1] = sending;
    }

    public boolean addFileCheck(File file) {
        if (this.getFiles().size() >= this.getFileLimit()[0] || file.getSize() > this.getFileLimit()[1]) {
            return false;
        }
        return true;
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

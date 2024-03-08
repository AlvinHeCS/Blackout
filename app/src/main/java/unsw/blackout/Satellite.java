package unsw.blackout;

import unsw.utils.Angle;
import java.util.ArrayList;

public abstract class Satellite {
    private String name;
    private String direction;
    private int height;
    private Angle degree;
    private ArrayList<File> files = new ArrayList<File>();
    private ArrayList<String> deviceSup = new ArrayList<String>();
    private int range;
    private int[] fileLimit = new int[2];
    private int linearSpeed;
    private int[] fileTransferSpeed = new int[2];

    public boolean addFile(File file) {
        if (this.files.size() >= this.fileLimit[0] || file.getSize() > this.fileLimit[1]) {
            return false;
        }
        this.files.add(file);
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Angle getDegree() {
        return this.degree;
    }

    public void setDegree(Angle degree) {
        this.degree = degree;
    }

    public ArrayList<String> getDeviceSup() {
        return deviceSup;
    }

    public void setDeviceSup(ArrayList<String> deviceSup) {
        this.deviceSup = deviceSup;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getLinearSpeed() {
        return linearSpeed;
    }

    public void setLinearSpeed(int linearSpeed) {
        this.linearSpeed = linearSpeed;
    }

    public void setTransferSpeed(int recieving, int sending) {
        this.fileTransferSpeed[0] = recieving;
        this.fileTransferSpeed[1] = sending;
    }

    public int[] getTransferSpeed() {
        return fileTransferSpeed;
    }

    public int[] getFileLimit() {
        return fileLimit;
    }

    public void setFileLimit(int fileQuantity, int fileSize) {
        this.fileLimit[0] = fileQuantity;
        this.fileLimit[1] = fileSize;
    }
}

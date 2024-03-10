package unsw.blackout;

import unsw.utils.Angle;
import java.util.ArrayList;

public class StandardSatellite extends Satellite implements fileSatellite {
    private int[] fileLimit = new int[2];
    private int[] fileTransferSpeed = new int[2];
    private ArrayList<File> files = new ArrayList<File>();

    public StandardSatellite(String satelliteId, double height, Angle position) {
        setName(satelliteId);
        setType("StandardSatellite");
        setDirection("clockwise");
        setDegree(position);
        setRange(150000);
        setHeight(height);
        ArrayList<String> devices = new ArrayList<>();
        devices.add("DesktopDevice");
        setDeviceNotSup(devices);
        setLinearSpeed(2500);
        setFileLimit(3, 80);
        setFileTransferSpeeds(1, 1);
    }

    public ArrayList<File> getFiles() {
        return this.files;
    }

    public boolean addFile(File file) {
        if (this.files.size() >= this.fileLimit[0] || file.getSize() > this.fileLimit[1]) {
            return false;
        }
        this.files.add(file);
        return true;
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

    public void standardSatelliteMovement() {
        super.movement();
    }
}

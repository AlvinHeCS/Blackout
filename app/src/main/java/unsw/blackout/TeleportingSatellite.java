package unsw.blackout;

import unsw.utils.Angle;
import java.util.ArrayList;

public class TeleportingSatellite extends Satellite implements fileSatellite {
    private int[] fileLimit = new int[2];
    private int[] fileTransferSpeed = new int[2];
    private ArrayList<File> files = new ArrayList<File>();

    public TeleportingSatellite(String satelliteId, double height, Angle position) {
        setName(satelliteId);
        setType("TeleportingSatellite");
        setDirection("anticlockwise");
        setDegree(position);
        setRange(200000);
        setHeight(height);
        setLinearSpeed(1000);
        setFileLimit(2147483647, 200);
        setFileTransferSpeeds(15, 10);
        ArrayList<String> devices = new ArrayList<>();
        devices.add("");
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

    public void teleportingSatelliteMovement() {
        double angularVelocity = this.getLinearSpeed() / this.getHeight();
        if (this.getDirection().equals("anticlockwise") && this.getDegree().toRadians() + angularVelocity >= Math.PI) {
            this.setDirection("clockwise");
            this.setDegree(Angle.fromDegrees(360));
            return;
        }
        if (this.getDirection().equals("clockwise") && this.getDegree().toRadians() - angularVelocity <= Math.PI) {
            this.setDirection("anticlockwise");
            this.setDegree(Angle.fromDegrees(0));
            return;
        }
        super.movement();
    }
}

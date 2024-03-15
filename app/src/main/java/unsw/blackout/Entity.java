package unsw.blackout;

import unsw.utils.Angle;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static unsw.utils.MathsHelper.isVisible;
import static unsw.utils.MathsHelper.getDistance;

public abstract class Entity {
    private String name;
    private String type;
    private Angle degree;
    private double height;
    private ArrayList<String> entitySupported;
    private ArrayList<File> files;
    private int range;
    private int[] fileLimit = new int[2];
    private int[] fileTransferSpeed = new int[2];
    private ArrayList<File> filesRecieving = new ArrayList<File>();
    private ArrayList<File> filesSending = new ArrayList<File>();
    private int usedSpace;
    public static final int MAX = 2147483647;

    public Entity(String name, double height, Angle degree) {
        this.name = name;
        this.degree = degree;
        this.height = height;
        this.setFiles(new ArrayList<File>());
    }

    public double getHeight() {
        return this.height;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<File> getFiles() {
        return this.files;
    }

    public void addFile(String filename, String content, int transmittedBytes) {
        File file = new File(filename, content, content.length(), transmittedBytes);
        this.files.add(file);
    }

    public void addTransferFile(String filename, String content, int transmittedBytes, Entity sender, Entity reciever) {
        File file = new File(filename, content, content.length(), transmittedBytes, sender, reciever);
        this.files.add(file);
    }

    public int getRange() {
        return this.range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public ArrayList<String> getEntitySupported() {
        return this.entitySupported;
    }

    public void setEntitySupported(ArrayList<String> entitiesName) {
        this.entitySupported = entitiesName;
    }

    public Angle getDegree() {
        return this.degree;
    }

    public void setDegree(Angle degree) {
        this.degree = degree;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }

    private boolean Entitycompatability(Entity entity) {
        for (String string : entity.getEntitySupported()) {
            if (this.getType().equals(string)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkEntityCommunicatable(Entity entity) {
        if (isVisible(this.height, this.degree, entity.getHeight(), entity.getDegree())
                && getDistance(height, degree, entity.getHeight(), entity.getDegree()) < this.range
                && Entitycompatability(entity)) {
            return true;
        }
        return false;
    }

    public ArrayList<String> checkEntityAllCommunicatable(ArrayList<Entity> entities) {
        ArrayList<String> entitiesInRange = new ArrayList<>();
        for (Entity entity : entities) {
            if (checkEntityCommunicatable(entity)) {
                entitiesInRange.add(entity.getName());
            }
        }
        return entitiesInRange;
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

    public ArrayList<File> getFilesSending() {
        return this.filesSending;
    }

    public void setFilesSending(ArrayList<File> fileSending) {
        this.filesSending = fileSending;
    }

    public ArrayList<File> getFilesRecieving() {
        return this.filesRecieving;
    }

    public void setFilesRecieving(ArrayList<File> fileRecieving) {
        this.filesRecieving = fileRecieving;
    }

    public int calcRecievingBandwidth() {
        return this.getFileTransferSpeeds()[0] / this.getFilesRecieving().size();
    }

    public int calcSendingBandwidth() {
        return this.getFileTransferSpeeds()[1] / this.getFilesSending().size();
    }

    public int calcUsedSpace() {
        for (File file : this.files) {
            this.usedSpace = this.usedSpace + file.getSize();
        }
        return this.usedSpace;
    }

    public File getFile(String fileName) {
        for (File file : files) {
            if (file.getName().equals(fileName)) {
                return file;
            }
        }
        return null;
    }

    public void removeSuccessfullyTransferredFiles() {

        for (File file : files) {
            if (file.successfullyTransfered()) {
                String name = file.getName();
                this.filesRecieving.removeIf(fileRecieving -> fileRecieving.getName().equals(name));
                this.filesSending.removeIf(fileSending -> fileSending.getName().equals(name));
            }
        }
    }

    public void addFiletoSending(File file) {
        this.filesSending.add(file);
    }

    public void addFiletoRecieving(File file) {
        this.filesRecieving.add(file);
    }

    public void updateBandwidth(Entity reciving, Entity sending) {
        for (File file : files) {
            file.setByteTransferSpeed(Math.min(reciving.calcRecievingBandwidth(), sending.calcSendingBandwidth()));
        }
    }
}

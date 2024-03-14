package unsw.blackout;

import unsw.utils.Angle;
import java.util.ArrayList;
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
    private int filesRecieving;
    private int filesSending;
    private int usedSpace;
    public static final int MAX = 2147483647;

    public Entity(String name, double height, Angle degree) {
        this.name = name;
        this.degree = degree;
        this.height = height;
        this.setFiles(new ArrayList<File>());
        this.filesRecieving = 0;
        this.filesSending = 0;
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

    public int getFilesSending() {
        return this.filesSending;
    }

    public void setFilesSending(int fileSending) {
        this.filesSending = fileSending;
    }

    public int getFilesRecieving() {
        return this.filesRecieving;
    }

    public void setFilesRecieving(int fileRecieving) {
        this.filesRecieving = fileRecieving;
    }

    public int calcUsedSpace() {
        for (File file : this.files) {
            this.usedSpace = this.usedSpace + file.getSize();
        }
        return this.usedSpace;
    }

    public File getFile(String fileName) {
        for (File file : files) {
            System.out.println(file.getTotalContent());
            System.out.println(fileName);
            if (file.getName().equals(fileName)) {
                return file;
            }
        }
        return null;
    }
}

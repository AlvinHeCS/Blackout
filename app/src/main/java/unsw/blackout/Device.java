package unsw.blackout;

import java.util.ArrayList;

import unsw.utils.Angle;

public abstract class Device {
    private String name;
    private Angle degree;
    private ArrayList<File> files = new ArrayList<File>();
    private int range;
    private String type;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addFile(String filename, String content) {
        File file = new File(filename, content, content.length());
        this.files.add(file);
    }

    public ArrayList<File> getFiles() {
        return this.files;
    }

    public Angle getDegree() {
        return this.degree;
    }

    public void setDegree(Angle degree) {
        this.degree = degree;
    }

    public int getRange() {
        return this.range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}

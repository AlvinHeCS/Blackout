package unsw.blackout;

import java.util.ArrayList;

import unsw.utils.Angle;

public abstract class Device {
    private String name;
    private Angle degree;
    private ArrayList<File> files = new ArrayList<File>();
    private int range;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addFile(String filename, String content) {
        File file = new File(filename, content, content.length());
        this.files.add(file);
    }

    public ArrayList<File> getFiles() {
        return files;
    }

    public Angle getDegree() {
        return degree;
    }

    public void setDegree(Angle degree) {
        this.degree = degree;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}

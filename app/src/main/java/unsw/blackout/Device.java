package unsw.blackout;

import java.util.ArrayList;

public class Device {
    private String name;
    private devType type;
    private int degree;
    private ArrayList<File> files = new ArrayList<File>();

    public Device(String name, devType type, int degree, ArrayList<File> files) {
        this.name = name;
        this.type = type;
        this.degree = degree;
        this.files = files;
    }

    public String getName() {
        return name;
    }

    public devType getType() {
        return type;
    }

    public int getDegree() {
        return degree;
    }

    public ArrayList<File> getFiles() {
        return files;
    }

}

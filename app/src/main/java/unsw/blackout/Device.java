package unsw.blackout;

import java.util.ArrayList;

import unsw.utils.Angle;

public class Device {
    private String name;
    private devType type;
    private Angle degree;
    private ArrayList<File> files = new ArrayList<File>();

    public Device(String name, devType type, Angle degree, ArrayList<File> files) {
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

    public Angle getDegree() {
        return degree;
    }

    public ArrayList<File> getFiles() {
        return files;
    }

}

package unsw.blackout;

import java.util.ArrayList;

import unsw.utils.Angle;

public class Device {
    private String name;
    private devType type;
    private Angle degree;
    private ArrayList<File> files = new ArrayList<File>();

    public Device(String name, Angle degree) {
        this.name = name;
        this.degree = degree;
    }

    public void addFile(String filename, String content) {
        File file = new File(filename, content, content.length());
        this.files.add(file);
    }

    public String getName() {
        return name;
    }

    public Angle getDegree() {
        return degree;
    }

    public void setdevType(String devType) {
        switch (devType) {
        case "HandheldDevice":
            this.type = new HandheldDevice();
            break;
        case "LaptopDevice":
            this.type = new LaptopDevice();
            break;
        case "DesktopDevice":
            this.type = new DesktopDevice();
            break;
        default:
            break;
        }
    }

    public ArrayList<File> getFiles() {
        return files;
    }

}

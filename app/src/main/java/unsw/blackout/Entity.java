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

    public void addFile(String filename, String content) {
        File file = new File(filename, content, content.length());
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
}

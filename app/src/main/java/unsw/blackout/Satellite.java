package unsw.blackout;

import java.util.ArrayList;

public class Satellite {
    private String name;
    private satType type;
    private Position position;
    private ArrayList<File> files = new ArrayList<File>();

    public Satellite(String name, satType type, Position position, ArrayList<File> files) {
        this.name = name;
        //satType type = new satType(type, )
        //this.type = ;
        this.position = position;
    }

    public boolean addFile() {
        //if (this.files.length >= 
    }
}

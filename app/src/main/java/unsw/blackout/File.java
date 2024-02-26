package unsw.blackout;

public class File {
    private String name;
    private String content;
    private int size;
    
    public String getName() {
        return name;
    }

    public String getRange() {
        return content;
    } 

    public int getSize() {
        return size;
    }
}

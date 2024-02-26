package unsw.blackout;

public class Store {
    private int fileLimit;
    private int sizeLimit;

    public Store(int fileLimit, int sizeLimit) {
        this.fileLimit = fileLimit;
        this.sizeLimit = sizeLimit;
    }

    public int getfileLimit() {
        return fileLimit;
    }

    public int getsizeLimit() {
        return sizeLimit;
    }
}

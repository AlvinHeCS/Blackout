package unsw.blackout;

import java.util.ArrayList;

public interface fileSatellite {
    public ArrayList<File> getFiles();

    public boolean addFile(File file);

    public int[] getFileLimit();

    public void setFileLimit(int fileQuantity, int fileSize);

    public int[] getFileTransferSpeeds();

    public void setFileTransferSpeeds(int recieving, int sending);
}

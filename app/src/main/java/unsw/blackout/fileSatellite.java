package unsw.blackout;

import java.util.ArrayList;

public interface fileSatellite {
    public ArrayList<File> getFiles();

    public void addFile();

    public int[] getFileLimit();

    public void setFileLimit();

    public int[] getFileTransferSpeeds();

    public void setFileTransferSpeeds();
}

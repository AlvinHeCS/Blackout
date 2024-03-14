package unsw.blackout;

public class File {
    private String name;
    private String content;
    private int size;
    private int bytesTransmitted;
    private String totalContent;

    public File(String name, String content, int size, int bytesTransmitted) {
        this.name = name;
        this.content = content.substring(0, bytesTransmitted);
        this.size = size;
        this.bytesTransmitted = bytesTransmitted;
        this.totalContent = content;
    }

    public String getName() {
        return this.name;
    }

    public String getContent() {
        return this.content;
    }

    public String getTotalContent() {
        return this.totalContent;
    }

    public void updateContent() {
        this.content = this.totalContent.substring(0, bytesTransmitted);
        System.out.println(totalContent);
        System.out.println(bytesTransmitted);
        System.out.println(this.totalContent.substring(0, bytesTransmitted));
    }

    public int getSize() {
        return this.size;
    }

    public int getBytesTransmitted() {
        return this.bytesTransmitted;
    }

    public void setBytesTransmitted(int bytesTransmitted) {
        this.bytesTransmitted = bytesTransmitted;
    }

    public boolean successfullyTransfered() {
        if (this.bytesTransmitted == this.size) {
            return true;
        }
        return false;
    }

    // need to check if its within range/

    public void updateFile() {
        if (!successfullyTransfered()) {
            this.bytesTransmitted = this.bytesTransmitted + 1;
            updateContent();
        }
    }
}

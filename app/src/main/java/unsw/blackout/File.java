package unsw.blackout;

public class File {
    private String name;
    private String content;
    private int size;
    private int bytesTransmitted;
    private String totalContent;
    private int byteTransferSpeed = 0;
    private Entity fromId;
    private Entity toId;

    public File(String name, String content, int size, int bytesTransmitted) {
        this.name = name;
        this.content = content.substring(0, bytesTransmitted);
        this.size = size;
        this.bytesTransmitted = bytesTransmitted;
        this.totalContent = content;
    }

    public File(String name, String content, int size, int bytesTransmitted, Entity sender, Entity reciever) {
        this.name = name;
        this.content = content.substring(0, bytesTransmitted);
        this.size = size;
        this.bytesTransmitted = bytesTransmitted;
        this.totalContent = content;
        this.fromId = sender;
        this.toId = reciever;
    }

    public void setFromId(Entity fromId) {
        this.fromId = fromId;
    }

    public Entity getFromId() {
        return this.fromId;
    }

    public void setToId(Entity toId) {
        this.toId = toId;
    }

    public Entity getToId() {
        return this.toId;
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
        this.content = this.totalContent.substring(0, Math.min(totalContent.length(), bytesTransmitted));
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
            //System.out.println(this.getByteTransferSpeed());
            //System.out.println(this.bytesTransmitted);
            this.bytesTransmitted = this.bytesTransmitted + this.getByteTransferSpeed();
            updateContent();
        }
    }

    public void setByteTransferSpeed(int transferSpeed) {
        this.byteTransferSpeed = transferSpeed;
    }

    public int getByteTransferSpeed() {
        return byteTransferSpeed;
    }

}

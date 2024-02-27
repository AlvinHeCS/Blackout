package unsw.blackout;

public class Speed {
    private int linear;
    private int recieving;
    private int sending;

    public Speed(int linear, int recieving, int sending) {
        this.linear = linear;
        this.recieving = recieving;
        this.sending = sending;
    }

    public int getlinear() {
        return linear;
    }

    public int getRecieving() {
        return recieving;
    }

    public int getSending() {
        return sending;
    }
}

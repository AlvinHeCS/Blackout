package unsw.blackout;

public class Relaysat extends satType {
    public Relaysat() {
        this.type = "RelaySatellite";
        this.deviceSup.add("HandHeldDevice");
        this.deviceSup.add("LaptopDevice");
        this.deviceSup.add("DesktopDevice");
        this.range = 300000000;
        Store standardStore = new Store(0, 0);
        this.store = standardStore;
        Speed standardSpeed = new Speed(1500000, 0, 0);
        this.speed = standardSpeed;
    }
}

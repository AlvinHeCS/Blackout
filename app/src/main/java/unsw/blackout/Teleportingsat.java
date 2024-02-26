package unsw.blackout;

public class Teleportingsat extends satType {
    public Teleportingsat() {
        this.type = "TeleportingSatellite";
        this.deviceSup.add("HandHeldDevice");
        this.deviceSup.add("LaptopDevice");
        this.deviceSup.add("DesktopDevice");
        this.range = 200000000;
        Store standardStore = new Store(2147483647, 80);
        this.store = standardStore;
        Speed standardSpeed = new Speed(1000000, 15, 10);
        this.speed = standardSpeed;
    }
}

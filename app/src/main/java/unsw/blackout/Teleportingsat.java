package unsw.blackout;

public class Teleportingsat extends satType {
    public Teleportingsat() {
        super.type = "TeleportingSatellite";
        super.deviceSup.add("HandHeldDevice");
        super.deviceSup.add("LaptopDevice");
        super.deviceSup.add("DesktopDevice");
        super.range = 200000;
        Store standardStore = new Store(2147483647, 80);
        super.store = standardStore;
        Speed standardSpeed = new Speed(1000, 15, 10);
        super.speed = standardSpeed;
    }
}

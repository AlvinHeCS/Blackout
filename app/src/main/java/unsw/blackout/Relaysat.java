package unsw.blackout;

public class Relaysat extends satType {
    public Relaysat() {
        super.type = "RelaySatellite";
        super.deviceSup.add("HandHeldDevice");
        super.deviceSup.add("LaptopDevice");
        super.deviceSup.add("DesktopDevice");
        super.range = 300000;
        Store standardStore = new Store(0, 0);
        super.store = standardStore;
        Speed standardSpeed = new Speed(1500, 0, 0);
        super.speed = standardSpeed;
    }
}

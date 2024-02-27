package unsw.blackout;

public class Standardsat extends satType {
    public Standardsat() {
        this.type = "StandardSatellite";
        this.deviceSup.add("HandHeldDevice");
        this.deviceSup.add("LaptopDevice");
        this.range = 150000;
        Store standardStore = new Store(3, 80);
        this.store = standardStore;
        Speed standardSpeed = new Speed(2500, 1, 1);
        this.speed = standardSpeed;
    }
}

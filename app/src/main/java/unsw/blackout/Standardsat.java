package unsw.blackout;

public class Standardsat extends satType {
    public Standardsat() {
        super.type = "StandardSatellite";
        super.deviceSup.add("HandHeldDevice");
        super.deviceSup.add("LaptopDevice");
        super.range = 150000;
        Store standardStore = new Store(3, 80);
        super.store = standardStore;
        Speed standardSpeed = new Speed(2500, 1, 1);
        super.speed = standardSpeed;
    }
}

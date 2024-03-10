package blackout;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import unsw.blackout.BlackoutController;
import unsw.response.models.EntityInfoResponse;
import unsw.response.models.FileInfoResponse;
import unsw.utils.Angle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static blackout.TestHelpers.assertListAreEqualIgnoringOrder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static unsw.utils.MathsHelper.RADIUS_OF_JUPITER;

@TestInstance(value = Lifecycle.PER_CLASS)
public class MyTests {
    @Test
    public void testAddMultipleSatellite() {
        BlackoutController controller = new BlackoutController();
        controller.createSatellite("SatA", "StandardSatellite", 100 + RADIUS_OF_JUPITER, Angle.fromDegrees(30));
        controller.createSatellite("SatB", "TeleportingSatellite", 50 + RADIUS_OF_JUPITER, Angle.fromDegrees(180));
        controller.createSatellite("SatC", "RelaySatellite", 20 + RADIUS_OF_JUPITER, Angle.fromDegrees(330));
        assertListAreEqualIgnoringOrder(Arrays.asList("SatA", "SatB", "SatC"), controller.listSatelliteIds());
    }

    @Test
    public void testDeleteMultipleDevice() {
        // Task 1
        BlackoutController controller = new BlackoutController();

        // Creates 1 satellite and 3 devices and deletes them
        controller.createDevice("DeviceA", "HandheldDevice", Angle.fromDegrees(30));
        controller.createDevice("DeviceB", "LaptopDevice", Angle.fromDegrees(180));
        controller.createDevice("DeviceC", "DesktopDevice", Angle.fromDegrees(330));

        controller.removeDevice("DeviceB");
        assertListAreEqualIgnoringOrder(Arrays.asList("DeviceA", "DeviceC"), controller.listDeviceIds());
        controller.removeDevice("DeviceC");
        assertListAreEqualIgnoringOrder(Arrays.asList("DeviceA"), controller.listDeviceIds());
        controller.removeDevice("DeviceA");
        assertListAreEqualIgnoringOrder(Arrays.asList(), controller.listDeviceIds());
        controller.createDevice("DeviceC", "DesktopDevice", Angle.fromDegrees(330));
        assertListAreEqualIgnoringOrder(Arrays.asList("DeviceC"), controller.listDeviceIds());
    }

    // write an addfile get info Test

    // write a test for standard satellite movement
    @Test
    public void testStandardSatelliteMovement() {
        BlackoutController controller = new BlackoutController();
        controller.createSatellite("SatA", "StandardSatellite", 100 + RADIUS_OF_JUPITER, Angle.fromDegrees(29));
        controller.simulate(22);
        System.out.println(controller.getInfo("SatA"));
    }

    @Test
    public void testTeleportingMovement() {
        BlackoutController controller = new BlackoutController();
        controller.createSatellite("SatA", "TeleportingSatellite", 100 + RADIUS_OF_JUPITER, Angle.fromDegrees(179));
        controller.simulate(260);
        System.out.println(controller.getInfo("SatA"));
    }
}

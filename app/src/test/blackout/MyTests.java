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

    // did movement test by hand by comparing to simulation
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

    @Test
    public void testRelayMovement() {
        BlackoutController controller = new BlackoutController();
        controller.createSatellite("SatA", "RelaySatellite", 100 + RADIUS_OF_JUPITER, Angle.fromDegrees(190));
        controller.simulate(1);
        System.out.println(controller.getInfo("SatA"));
    }

    @Test
    public void testRangeSatellitetoSatellite() {
        // Task 2
        // Example from the specification
        BlackoutController controller = new BlackoutController();

        // Creates 1 satellite and 2 devices
        // Gets a device to send a file to a satellites and gets another device to download it.
        // StandardSatellites are slow and transfer 1 byte per minute.
        controller.createSatellite("Satellite2", "StandardSatellite", 79181, Angle.fromDegrees(148.26));
        controller.createDevice("DeviceB", "HandheldDevice", Angle.fromDegrees(180));
        controller.createSatellite("Satellite3", "TeleportingSatellite", 75800, Angle.fromDegrees(204.82));
        controller.createDevice("DeviceC", "HandheldDevice", Angle.fromDegrees(320));
        controller.createSatellite("Satellite4", "StandardSatellite", 76123, Angle.fromDegrees(170.57));

        assertListAreEqualIgnoringOrder(Arrays.asList("Satellite4"),
                controller.communicableEntitiesInRange("Satellite3"));

    }

    @Test
    public void testStandardSatelliteDesktopException() {
        // Task 2
        // Example from the specification
        BlackoutController controller = new BlackoutController();

        // Creates 1 satellite and 2 devices
        // Gets a device to send a file to a satellites and gets another device to download it.
        // StandardSatellites are slow and transfer 1 byte per minute.
        controller.createSatellite("Satellite4", "StandardSatellite", 76123, Angle.fromDegrees(234.16));
        controller.createDevice("DeviceD", "DesktopDevice", Angle.fromDegrees(236.99));

        assertListAreEqualIgnoringOrder(Arrays.asList(), controller.communicableEntitiesInRange("Satellite4"));

    }

    @Test
    public void testRangewithRelayDeviceToSatellite() {
        // Task 2
        // Example from the specification
        BlackoutController controller = new BlackoutController();

        // Creates 1 satellite and 2 devices
        // Gets a device to send a file to a satellites and gets another device to download it.
        // StandardSatellites are slow and transfer 1 byte per minute.
        controller.createSatellite("Satellite1", "RelaySatellite", 76123, Angle.fromDegrees(170.57));
        controller.createSatellite("Satellite2", "StandardSatellite", 79181, Angle.fromDegrees(148.26));
        controller.createDevice("DeviceB", "HandheldDevice", Angle.fromDegrees(180));

        assertListAreEqualIgnoringOrder(Arrays.asList("Satellite1", "Satellite2"),
                controller.communicableEntitiesInRange("DeviceB"));
    }

    @Test
    public void testRangewithRelaySatelliteToSatellite() {
        // Task 2
        // Example from the specification
        BlackoutController controller = new BlackoutController();

        // Creates 1 satellite and 2 devices
        // Gets a device to send a file to a satellites and gets another device to download it.
        // StandardSatellites are slow and transfer 1 byte per minute.
        controller.createSatellite("Satellite1", "RelaySatellite", 76123, Angle.fromDegrees(170.57));
        controller.createSatellite("Satellite2", "StandardSatellite", 79181, Angle.fromDegrees(148.26));
        controller.createDevice("DeviceB", "HandheldDevice", Angle.fromDegrees(180));
        controller.createSatellite("Satellite3", "TeleportingSatellite", 75800, Angle.fromDegrees(204.82));
        controller.createDevice("DeviceC", "HandheldDevice", Angle.fromDegrees(320));

        assertListAreEqualIgnoringOrder(Arrays.asList("Satellite1", "Satellite2", "DeviceB"),
                controller.communicableEntitiesInRange("Satellite3"));

    }

    @Test
    public void testRangeMultiSatelliteDeviceWithRelay() {
        // Task 2
        // Example from the specification
        BlackoutController controller = new BlackoutController();

        // Creates 1 satellite and 2 devices
        // Gets a device to send a file to a satellites and gets another device to download it.
        // StandardSatellites are slow and transfer 1 byte per minute.
        controller.createSatellite("Satellite1", "RelaySatellite", 76123, Angle.fromDegrees(170.57));
        controller.createSatellite("Satellite2", "StandardSatellite", 79181, Angle.fromDegrees(148.26));
        controller.createDevice("DeviceB", "HandheldDevice", Angle.fromDegrees(180));
        controller.createSatellite("Satellite3", "TeleportingSatellite", 75800, Angle.fromDegrees(204.82));
        controller.createDevice("DeviceC", "HandheldDevice", Angle.fromDegrees(320));
        controller.createSatellite("Satellite4", "StandardSatellite", 76123, Angle.fromDegrees(170.57));

        assertListAreEqualIgnoringOrder(Arrays.asList("Satellite1", "Satellite2", "DeviceB", "Satellite4"),
                controller.communicableEntitiesInRange("Satellite3"));

    }

}

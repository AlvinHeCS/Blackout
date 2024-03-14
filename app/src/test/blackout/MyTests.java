package blackout;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import unsw.blackout.BlackoutController;
import unsw.blackout.FileTransferException;
import unsw.response.models.EntityInfoResponse;
import unsw.response.models.FileInfoResponse;
import unsw.utils.Angle;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

        BlackoutController controller = new BlackoutController();
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

        BlackoutController controller = new BlackoutController();

        controller.createSatellite("Satellite4", "StandardSatellite", 76123, Angle.fromDegrees(234.16));
        controller.createDevice("DeviceD", "DesktopDevice", Angle.fromDegrees(236.99));

        assertListAreEqualIgnoringOrder(Arrays.asList(), controller.communicableEntitiesInRange("Satellite4"));

    }

    @Test
    public void testRangewithRelayDeviceToSatellite() {

        BlackoutController controller = new BlackoutController();
        controller.createSatellite("Satellite1", "RelaySatellite", 76123, Angle.fromDegrees(170.57));
        controller.createSatellite("Satellite2", "StandardSatellite", 79181, Angle.fromDegrees(148.26));
        controller.createDevice("DeviceB", "HandheldDevice", Angle.fromDegrees(180));

        assertListAreEqualIgnoringOrder(Arrays.asList("Satellite1", "Satellite2"),
                controller.communicableEntitiesInRange("DeviceB"));
    }

    @Test
    public void testRangewithRelaySatelliteToSatellite() {
        BlackoutController controller = new BlackoutController();

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

        BlackoutController controller = new BlackoutController();

        controller.createSatellite("Satellite1", "RelaySatellite", 76123, Angle.fromDegrees(170.57));
        controller.createSatellite("Satellite2", "StandardSatellite", 79181, Angle.fromDegrees(148.26));
        controller.createDevice("DeviceB", "HandheldDevice", Angle.fromDegrees(180));
        controller.createSatellite("Satellite3", "TeleportingSatellite", 75800, Angle.fromDegrees(204.82));
        controller.createDevice("DeviceC", "HandheldDevice", Angle.fromDegrees(320));
        controller.createSatellite("Satellite4", "StandardSatellite", 76123, Angle.fromDegrees(170.57));

        assertListAreEqualIgnoringOrder(Arrays.asList("Satellite1", "Satellite2", "DeviceB", "Satellite4"),
                controller.communicableEntitiesInRange("Satellite3"));

    }

    @Test
    public void testSendFileHalfwayMessageSent() {
        BlackoutController controller = new BlackoutController();

        controller.createSatellite("Satellite1", "StandardSatellite", 10000 + RADIUS_OF_JUPITER,
                Angle.fromDegrees(320));
        controller.createDevice("DeviceB", "LaptopDevice", Angle.fromDegrees(310));
        controller.createDevice("DeviceC", "HandheldDevice", Angle.fromDegrees(320));

        String msg = "Hey";
        controller.addFileToDevice("DeviceC", "FileAlpha", msg);
        assertDoesNotThrow(() -> controller.sendFile("FileAlpha", "DeviceC", "Satellite1"));
        assertEquals(new FileInfoResponse("FileAlpha", "", msg.length(), false),
                controller.getInfo("Satellite1").getFiles().get("FileAlpha"));

        controller.simulate(msg.length() * 2);
        assertEquals(new FileInfoResponse("FileAlpha", msg, msg.length(), true),
                controller.getInfo("Satellite1").getFiles().get("FileAlpha"));

        assertDoesNotThrow(() -> controller.sendFile("FileAlpha", "Satellite1", "DeviceB"));
        assertEquals(new FileInfoResponse("FileAlpha", "", msg.length(), false),
                controller.getInfo("DeviceB").getFiles().get("FileAlpha"));

        controller.simulate(2);
        assertEquals(new FileInfoResponse("FileAlpha", "He", msg.length(), false),
                controller.getInfo("DeviceB").getFiles().get("FileAlpha"));

    }

    @Test
    public void testSimulateOverMessageLength() {
        BlackoutController controller = new BlackoutController();

        controller.createSatellite("Satellite1", "StandardSatellite", 10000 + RADIUS_OF_JUPITER,
                Angle.fromDegrees(320));
        controller.createDevice("DeviceB", "LaptopDevice", Angle.fromDegrees(310));
        controller.createDevice("DeviceC", "HandheldDevice", Angle.fromDegrees(320));

        String msg = "Hey";
        controller.addFileToDevice("DeviceC", "FileAlpha", msg);
        assertDoesNotThrow(() -> controller.sendFile("FileAlpha", "DeviceC", "Satellite1"));
        assertEquals(new FileInfoResponse("FileAlpha", "", msg.length(), false),
                controller.getInfo("Satellite1").getFiles().get("FileAlpha"));

        controller.simulate(msg.length() * 2);
        assertEquals(new FileInfoResponse("FileAlpha", msg, msg.length(), true),
                controller.getInfo("Satellite1").getFiles().get("FileAlpha"));

        assertDoesNotThrow(() -> controller.sendFile("FileAlpha", "Satellite1", "DeviceB"));
        assertEquals(new FileInfoResponse("FileAlpha", "", msg.length(), false),
                controller.getInfo("DeviceB").getFiles().get("FileAlpha"));

        controller.simulate(20);
        assertEquals(new FileInfoResponse("FileAlpha", msg, msg.length(), true),
                controller.getInfo("DeviceB").getFiles().get("FileAlpha"));

    }

    @Test
    public void testSendFileSatellitetoSatellitehalfwayDownloadedException() {
        BlackoutController controller = new BlackoutController();

        controller.createSatellite("Satellite1", "StandardSatellite", 10000 + RADIUS_OF_JUPITER,
                Angle.fromDegrees(320));
        controller.createSatellite("Satellite2", "StandardSatellite", 10000 + RADIUS_OF_JUPITER,
                Angle.fromDegrees(340));
        controller.createDevice("DeviceB", "LaptopDevice", Angle.fromDegrees(310));
        controller.createDevice("DeviceC", "HandheldDevice", Angle.fromDegrees(320));

        String msg = "Hey";
        controller.addFileToDevice("DeviceC", "FileAlpha", msg);
        assertDoesNotThrow(() -> controller.sendFile("FileAlpha", "DeviceC", "Satellite1"));
        assertEquals(new FileInfoResponse("FileAlpha", "", msg.length(), false),
                controller.getInfo("Satellite1").getFiles().get("FileAlpha"));

        controller.simulate(msg.length() * 2);
        assertEquals(new FileInfoResponse("FileAlpha", msg, msg.length(), true),
                controller.getInfo("Satellite1").getFiles().get("FileAlpha"));

        assertDoesNotThrow(() -> controller.sendFile("FileAlpha", "Satellite1", "DeviceB"));
        assertEquals(new FileInfoResponse("FileAlpha", "", msg.length(), false),
                controller.getInfo("DeviceB").getFiles().get("FileAlpha"));

        controller.simulate(1);
        assertEquals(new FileInfoResponse("FileAlpha", "H", msg.length(), false),
                controller.getInfo("DeviceB").getFiles().get("FileAlpha"));
        assertThrows(FileTransferException.VirtualFileNotFoundException.class,
                () -> controller.sendFile(msg, "Satellite1", "Satellite2"));
    }

    @Test
    public void testSendFileSatellitetoSatellite() {
        BlackoutController controller = new BlackoutController();

        controller.createSatellite("Satellite1", "StandardSatellite", 10000 + RADIUS_OF_JUPITER,
                Angle.fromDegrees(320));
        controller.createSatellite("Satellite2", "StandardSatellite", 10000 + RADIUS_OF_JUPITER,
                Angle.fromDegrees(340));
        controller.createDevice("DeviceB", "LaptopDevice", Angle.fromDegrees(310));
        controller.createDevice("DeviceC", "HandheldDevice", Angle.fromDegrees(320));

        String msg = "Hey";
        controller.addFileToDevice("DeviceC", "FileAlpha", msg);
        assertDoesNotThrow(() -> controller.sendFile("FileAlpha", "DeviceC", "Satellite1"));
        assertEquals(new FileInfoResponse("FileAlpha", "", msg.length(), false),
                controller.getInfo("Satellite1").getFiles().get("FileAlpha"));

        controller.simulate(msg.length() * 2);
        assertEquals(new FileInfoResponse("FileAlpha", msg, msg.length(), true),
                controller.getInfo("Satellite1").getFiles().get("FileAlpha"));

        assertDoesNotThrow(() -> controller.sendFile("FileAlpha", "Satellite1", "Satellite2"));
        assertEquals(new FileInfoResponse("FileAlpha", "", msg.length(), false),
                controller.getInfo("Satellite2").getFiles().get("FileAlpha"));
        controller.simulate(msg.length());
        assertEquals(new FileInfoResponse("FileAlpha", "Hey", msg.length(), true),
                controller.getInfo("Satellite2").getFiles().get("FileAlpha"));
    }

    @Test
    public void testMaxFileExceptionError() {
        BlackoutController controller = new BlackoutController();
    }

    @Test
    public void testMaxStorageReached() {
        BlackoutController controller = new BlackoutController();
    }

    @Test
    public void testDeviceOutofRange() {
        BlackoutController controller = new BlackoutController();
    }

    @Test
    public void testRelaySatelliteTransferFrom() {
        BlackoutController controller = new BlackoutController();
    }

    @Test

    // tests for transferring to a relay satellite
    // test for teleportation 
    // test for min bandwidth sender to reciever bandwidth

    // test for badwidth even split
    // test for Max bandwidth error

    // still need to implement badwidth for min(sender and reciever)
    // need to implement out of range behvaiour
    // need to get rid of relay satellite behaviour
    // need to get 
}

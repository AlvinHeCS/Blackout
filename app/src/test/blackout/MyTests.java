package blackout;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import unsw.blackout.BlackoutController;
import unsw.blackout.FileTransferException;

import unsw.response.models.FileInfoResponse;
import unsw.utils.Angle;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static blackout.TestHelpers.assertListAreEqualIgnoringOrder;

import java.util.Arrays;

import static unsw.utils.MathsHelper.RADIUS_OF_JUPITER;

@TestInstance(value = Lifecycle.PER_CLASS)
public class MyTests {
        @Test
        public void testAddMultipleSatellite() {
                BlackoutController controller = new BlackoutController();
                controller.createSatellite("SatA", "StandardSatellite", 100 + RADIUS_OF_JUPITER, Angle.fromDegrees(30));
                controller.createSatellite("SatB", "TeleportingSatellite", 50 + RADIUS_OF_JUPITER,
                                Angle.fromDegrees(180));
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
                controller.createSatellite("SatA", "TeleportingSatellite", 100 + RADIUS_OF_JUPITER,
                                Angle.fromDegrees(179));
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
                String msg = "Hell";
                String msg2 = "fij";
                String msg3 = "oih";
                String msg4 = "oifuheoidjfwepoifjoweijf";
                controller.createSatellite("Satellite2", "StandardSatellite", 10000 + RADIUS_OF_JUPITER,
                                Angle.fromDegrees(340));
                controller.createDevice("DeviceC", "LaptopDevice", Angle.fromDegrees(310));
                controller.addFileToDevice("DeviceC", "FileAlpha", msg);
                controller.addFileToDevice("DeviceC", "FileAlpha2", msg2);
                controller.addFileToDevice("DeviceC", "FileAlpha3", msg3);
                controller.addFileToDevice("DeviceC", "FileAlpha4", msg4);

                assertDoesNotThrow(() -> controller.sendFile("FileAlpha", "DeviceC", "Satellite2"));
                controller.simulate(4);
                assertDoesNotThrow(() -> controller.sendFile("FileAlpha2", "DeviceC", "Satellite2"));
                controller.simulate(3);
                assertDoesNotThrow(() -> controller.sendFile("FileAlpha3", "DeviceC", "Satellite2"));
                controller.simulate(3);
                assertThrows(FileTransferException.VirtualFileNoStorageSpaceException.class,
                                () -> controller.sendFile("FileAlpha4", "DeviceC", "Satellite2"));

        }

        @Test
        public void testDeviceOutofRange() {
                BlackoutController controller = new BlackoutController();

        }

        @Test
        public void testTeleportationTransferBehaviour() {
                BlackoutController controller = new BlackoutController();
        }

        @Test
        public void testMaxBandwidth() {
                BlackoutController controller = new BlackoutController();
                String msg = "Hello My name is Jeff nice to meet yourwefoiweufd098weuf";
                String msg2 = "fijewoijfoiwefj dqipwojdipoqdwjoiwneocin";
                controller.createSatellite("Satellite2", "StandardSatellite", 10000 + RADIUS_OF_JUPITER,
                                Angle.fromDegrees(340));
                controller.createDevice("DeviceC", "LaptopDevice", Angle.fromDegrees(310));
                controller.addFileToDevice("DeviceC", "FileAlpha", msg);
                controller.addFileToDevice("DeviceC", "FileAlpha2", msg2);
                assertDoesNotThrow(() -> controller.sendFile("FileAlpha", "DeviceC", "Satellite2"));
                assertThrows(FileTransferException.VirtualFileNoBandwidthException.class,
                                () -> controller.sendFile("FileAlpha2", "DeviceC", "Satellite2"));
        }

        @Test
        public void testBandWidthchange() {
                BlackoutController controller = new BlackoutController();

                String msg = "Hello My name is Jeff nice to meet yourwefoiweufd098weuf";
                String msg2 = "fijewoijfoiwefj dqipwojdipoqdwjoiwneocin";
                String msg3 = "oiejwhfoiwedjiuewfhiouwedhoiuqhdwiuhwqiduhwqiudh";
                controller.createSatellite("Satellite2", "TeleportingSatellite", 78914, Angle.fromDegrees(310));
                controller.createDevice("DeviceC", "DesktopDevice", Angle.fromDegrees(312.78));
                controller.addFileToDevice("DeviceC", "FileAlpha", msg);
                controller.addFileToDevice("DeviceC", "FileAlpha2", msg2);
                controller.addFileToDevice("DeviceC", "FileAlpha3", msg3);
                assertDoesNotThrow(() -> controller.sendFile("FileAlpha", "DeviceC", "Satellite2"));
                controller.simulate();
                assertEquals(new FileInfoResponse("FileAlpha", "Hello My name i", msg.length(), false),
                                controller.getInfo("Satellite2").getFiles().get("FileAlpha"));
                assertDoesNotThrow(() -> controller.sendFile("FileAlpha2", "DeviceC", "Satellite2"));
                assertDoesNotThrow(() -> controller.sendFile("FileAlpha3", "DeviceC", "Satellite2"));
                controller.simulate(1);
                assertEquals(new FileInfoResponse("FileAlpha", "Hello My name is Jef", msg.length(), false),
                                controller.getInfo("Satellite2").getFiles().get("FileAlpha"));
                assertEquals(new FileInfoResponse("FileAlpha2", "fijew", msg2.length(), false),
                                controller.getInfo("Satellite2").getFiles().get("FileAlpha2"));
        }

        @Test
        public void testEntitiesInRangeAfterMovement() {
                // Task 2
                // Example from the specification
                BlackoutController controller = new BlackoutController();

                // Creates 1 satellite and 2 devices
                // Gets a device to send a file to a satellites and gets another device to download it.
                // StandardSatellites are slow and transfer 1 byte per minute.
                controller.createSatellite("Satellite2", "StandardSatellite", 78914, Angle.fromDegrees(310));
                controller.createDevice("DeviceC", "LaptopDevice", Angle.fromDegrees(312.78));

                assertListAreEqualIgnoringOrder(Arrays.asList("Satellite2"),
                                controller.communicableEntitiesInRange("DeviceC"));
                controller.simulate();
                assertListAreEqualIgnoringOrder(Arrays.asList("Satellite2"),
                                controller.communicableEntitiesInRange("DeviceC"));
        }

        @Test
        public void testOutOfRange() {
                // Create a device and a standard satellite
                BlackoutController controller = new BlackoutController();
                controller.createDevice("Device", "HandheldDevice", Angle.fromDegrees(0));
                // Create a satellite 3 ticks in range of the device
                controller.createSatellite("Standard", "StandardSatellite", 10000 + RADIUS_OF_JUPITER,
                                Angle.fromDegrees(337));
                String msg = "Hello";
                controller.addFileToDevice("Device", "File", msg);
                assertDoesNotThrow(() -> controller.sendFile("File", "Device", "Standard"));
                controller.simulate(3);
                assertEquals(new FileInfoResponse("File", "Hel", msg.length(), false),
                                controller.getInfo("Standard").getFiles().get("File"));
                // The satellite is now out of range of the device
                controller.simulate();
                assertEquals(null, controller.getInfo("Standard").getFiles().get("File"));
        }
        // need to implement out of range behvaiour
}

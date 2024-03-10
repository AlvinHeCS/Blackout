package unsw.blackout;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import unsw.response.models.EntityInfoResponse;
import unsw.response.models.FileInfoResponse;
import unsw.utils.Angle;
import static unsw.utils.MathsHelper.RADIUS_OF_JUPITER;
import java.util.HashMap;
import java.util.Map;

/**
 * The controller for the Blackout system.
 *
 * WARNING: Do not move this file or modify any of the existing method
 * signatures
 */
public class BlackoutController {
    private ArrayList<Satellite> satellites = new ArrayList<Satellite>();
    private ArrayList<Device> devices = new ArrayList<Device>();

    public void createDevice(String deviceId, String type, Angle position) {
        Device device;
        if (type.equals("HandheldDevice")) {
            device = new HandheldDevice(deviceId, position);
        } else if (type.equals("LaptopDevice")) {
            device = new LaptopDevice(deviceId, position);
        } else if (type.equals("DesktopDevice")) {
            device = new DesktopDevice(deviceId, position);
        } else {
            return;
        }
        devices.add(device);
    }

    public void removeDevice(String deviceId) {
        devices.removeIf(obj -> obj.getName().equals(deviceId));
    }

    public void createSatellite(String satelliteId, String type, double height, Angle position) {
        Satellite satellite;
        if (type.equals("StandardSatellite")) {
            satellite = new StandardSatellite(satelliteId, height, position);
        } else if (type.equals("TeleportingSatellite")) {
            satellite = new TeleportingSatellite(satelliteId, height, position);
        } else if (type.equals("RelaySatellite")) {
            satellite = new RelaySatellite(satelliteId, height, position);
        } else {
            return;
        }
        satellites.add(satellite);
    }

    public void removeSatellite(String satelliteId) {
        satellites.removeIf(obj -> obj.getName().equals(satelliteId));
    }

    public List<String> listDeviceIds() {
        return devices.stream().map(n -> n.getName()).collect(Collectors.toList());
    }

    public List<String> listSatelliteIds() {
        return satellites.stream().map(n -> n.getName()).collect(Collectors.toList());
    }

    public void addFileToDevice(String deviceId, String filename, String content) {
        Optional<Device> object = devices.stream().filter(obj -> obj.getName().equals(deviceId)).findFirst();
        Device device = object.get();
        device.addFile(filename, content);
    }

    public EntityInfoResponse getInfo(String id) {
        // temp map
        Map<String, FileInfoResponse> map = new HashMap<>();
        if (devices.stream().anyMatch(obj -> obj.getName().equals(id))) {
            Optional<Device> object = devices.stream().filter(obj -> obj.getName().equals(id)).findFirst();
            Device device = object.get();

            return new EntityInfoResponse(id, device.getDegree(), RADIUS_OF_JUPITER, device.getType(), map);
        } else {
            Optional<Satellite> object = satellites.stream().filter(obj -> obj.getName().equals(id)).findFirst();
            Satellite satellite = object.get();
            return new EntityInfoResponse(id, satellite.getDegree(), satellite.getHeight(), satellite.getType(), map);
        }
    }

    public void simulate() {
        // move satellites
        for (Satellite satellite : satellites) {
            if (satellite.getType().equals("StandardSatellite")) {
                StandardSatellite standardSatellite = (StandardSatellite) satellite;
                standardSatellite.standardSatelliteMovement();
            }
            if (satellite.getType().equals("TeleportingSatellite")) {
                TeleportingSatellite teleportingSatellite = (TeleportingSatellite) satellite;
                teleportingSatellite.teleportingSatelliteMovement();
            }
        }

        // during the move check if
        // 
    }

    /**
     * Simulate for the specified number of minutes. You shouldn't need to modify
     * this function.
     */
    public void simulate(int numberOfMinutes) {
        for (int i = 0; i < numberOfMinutes; i++) {
            simulate();
        }
    }

    public List<String> communicableEntitiesInRange(String id) {
        // TODO: Task 2 b)
        return new ArrayList<>();
    }

    public void sendFile(String fileName, String fromId, String toId) throws FileTransferException {
        // TODO: Task 2 c)
    }

    public void createDevice(String deviceId, String type, Angle position, boolean isMoving) {
        createDevice(deviceId, type, position);
        // TODO: Task 3
    }

    public void createSlope(int startAngle, int endAngle, int gradient) {
        // TODO: Task 3
        // If you are not completing Task 3 you can leave this method blank :)
    }
}

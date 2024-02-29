package unsw.blackout;

import java.util.ArrayList;
import java.util.List;

import unsw.response.models.EntityInfoResponse;
import unsw.utils.Angle;

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
        Device device = new Device(deviceId, position);
        device.setdevType(type);
        devices.add(device);
        // check if device is unique before adding it
    }

    public void removeDevice(String deviceId) {
        for (Device device : devices) {
            if (device.getName().equals(deviceId)) {
                devices.remove(device);
            }
        }
    }

    public void createSatellite(String satelliteId, String type, double height, Angle position) {
        Satellite satellite = new Satellite(satelliteId);
        satellite.setsatType(type);
        satellite.setPosition(height, position);
        satellites.add(satellite);
        // check if satellite is unique before adding it

    }

    public void removeSatellite(String satelliteId) {
        for (Satellite satellite : satellites) {
            if (satellite.getName().equals(satelliteId)) {
                satellites.remove(satellite);
            }
        }
    }

    public List<String> listDeviceIds() {
        List<String> deviceIds = new ArrayList<String>();
        for (Device device : devices) {
            deviceIds.add(device.getName());
        }
        return deviceIds;
    }

    public List<String> listSatelliteIds() {
        List<String> satelliteIds = new ArrayList<String>();
        for (Satellite satellite : satellites) {
            satelliteIds.add(satellite.getName());
        }
        return satelliteIds;
    }

    public void addFileToDevice(String deviceId, String filename, String content) {
        // check if fileId is unique
        for (Device device : devices) {
            if (device.getName().equals(deviceId)) {
                device.addFile(filename, content);
            }
        }
    }

    public EntityInfoResponse getInfo(String id) {
        // TODO: Task 1h)
        return null;
    }

    public void simulate() {
        // TODO: Task 2a)
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

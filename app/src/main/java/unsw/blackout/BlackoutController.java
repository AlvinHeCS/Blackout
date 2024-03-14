package unsw.blackout;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import unsw.response.models.EntityInfoResponse;
import unsw.response.models.FileInfoResponse;
import unsw.utils.Angle;

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
        device.addFile(filename, content, content.length());
    }

    public EntityInfoResponse getInfo(String id) {
        Map<String, FileInfoResponse> map = new HashMap<String, FileInfoResponse>();
        ArrayList<Entity> entities = getEntityList(devices, satellites);
        Entity sourceEntity = entities.stream().filter(entity -> entity.getName().equals(id)).findFirst().orElse(null);
        ArrayList<File> files = sourceEntity.getFiles();
        for (File file : files) {
            map.put(file.getName(), new FileInfoResponse(file.getName(), file.getContent(), file.getSize(),
                    file.successfullyTransfered()));
        }
        return new EntityInfoResponse(id, sourceEntity.getDegree(), sourceEntity.getHeight(), sourceEntity.getType(),
                map);

    }

    public void simulate() {
        ArrayList<Entity> entities = getEntityList(devices, satellites);
        satellites.forEach(satellite -> {
            satellite.movement();
        });
        for (Entity entity : entities) {
            for (File file : entity.getFiles()) {
                file.updateFile();
            }
        }
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

    private ArrayList<Entity> getEntityList(ArrayList<Device> devices, ArrayList<Satellite> satellites) {
        ArrayList<Entity> entities = new ArrayList<>();
        devices.forEach(device -> entities.add((Entity) device));
        satellites.forEach(satellite -> entities.add((Entity) satellite));
        return entities;
    }

    private ArrayList<String> relayExtension(ArrayList<String> ids) {
        ArrayList<Entity> entities = getEntityList(devices, satellites);
        ArrayList<String> entitiesValid = new ArrayList<>();
        for (String id : ids) {
            Entity sourceEntity = entities.stream().filter(entity -> entity.getName().equals(id)).findFirst()
                    .orElse(null);
            if (sourceEntity.getType().equals("RelaySatellite")) {
                entitiesValid = sourceEntity.checkEntityAllCommunicatable(entities);
            }
        }
        entitiesValid.addAll(ids);
        return entitiesValid;
    }

    public List<String> communicableEntitiesInRange(String id) {
        ArrayList<Entity> entities = getEntityList(devices, satellites);
        Entity sourceEntity = entities.stream().filter(entity -> entity.getName().equals(id)).findFirst().orElse(null);
        ArrayList<String> entitiesValid = sourceEntity.checkEntityAllCommunicatable(entities);
        entitiesValid = relayExtension(entitiesValid);
        List<String> entitiesValidNoDuplicates = entitiesValid.stream().distinct().collect(Collectors.toList());
        entitiesValidNoDuplicates.removeIf(mainId -> mainId == id);
        return entitiesValidNoDuplicates;
    }

    public void sendFile(String fileName, String fromId, String toId) throws FileTransferException {
        ArrayList<Entity> entities = getEntityList(devices, satellites);
        Entity sender = entities.stream().filter(entity -> entity.getName().equals(fromId)).findFirst().orElse(null);
        Entity reciever = entities.stream().filter(entity -> entity.getName().equals(toId)).findFirst().orElse(null);
        File senderFile = sender.getFile(fileName);

        if (senderFile == null || senderFile.getBytesTransmitted() != senderFile.getSize()) {
            throw new FileTransferException.VirtualFileNotFoundException(
                    fileName + "is not found or hasnt finished dowloading");
        }
        if (reciever.getFileTransferSpeeds()[0] / (reciever.getFilesRecieving() + 1) < 1) {
            throw new FileTransferException.VirtualFileNoBandwidthException(reciever + "Max Bandwidth Reached");
        }
        if (reciever.getFiles().stream().anyMatch(currfile -> currfile.getName().equals(fileName))) {
            throw new FileTransferException.VirtualFileAlreadyExistsException(fileName + "is already on that entity");
        }
        if (reciever.getFiles().size() + 1 > reciever.getFileLimit()[0]) {
            throw new FileTransferException.VirtualFileNoStorageSpaceException(reciever + "Max Files Reached");
        }
        if (senderFile.getSize() + reciever.calcUsedSpace() > reciever.getFileLimit()[1]) {
            throw new FileTransferException.VirtualFileNoStorageSpaceException(reciever + "Max Storage Reached");
        }

        reciever.addFile(fileName, senderFile.getContent(), 0);

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

package codenameburkley;

import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;

import java.util.ArrayList;
import java.util.List;

public class DiskInfoService {

    private final SystemInfo si = new SystemInfo();
    private final HardwareAbstractionLayer hal = si.getHardware();

    public List<DiskData> getDisks() {
        List<DiskData> list = new ArrayList<>();

        for (HWDiskStore disk : hal.getDiskStores()) {
            disk.updateAttributes();

            DiskData d = new DiskData();
            d.model = disk.getModel();
            d.serial = disk.getSerial();
            d.sizeBytes = disk.getSize() / 1_000_000_000;
            d.readBytes = disk.getReadBytes() / 1_000_000_000;
            d.writeBytes = disk.getWriteBytes() / 1_000_000_000;
            d.readCount = disk.getReads();
            d.writeCount = disk.getWrites();
            d.transferTimeMs = disk.getTransferTime();

            list.add(d);
        }

        return list;
    }
}


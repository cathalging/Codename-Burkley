package codenameburkley;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import java.util.ArrayList;
import java.util.List;

    class DiskData {
    public String model;
    public String serial;
    public long sizeBytes;
    public long readBytes;
    public long writeBytes;
    public long readCount;
    public long writeCount;
    public long transferTimeMs;
}
    class DiskInfoService {

    private final SystemInfo si = new SystemInfo();
    private final HardwareAbstractionLayer hal = si.getHardware();

    public List<DiskData> getDisks() {
        List<DiskData> list = new ArrayList<>();

        for (HWDiskStore disk : hal.getDiskStores()) {
            disk.updateAttributes();

            DiskData d = new DiskData();
            d.model = disk.getModel();
            d.serial = disk.getSerial();
            d.sizeBytes = disk.getSize();
            d.readBytes = disk.getReadBytes();
            d.writeBytes = disk.getWriteBytes();
            d.readCount = disk.getReads();
            d.writeCount = disk.getWrites();
            d.transferTimeMs = disk.getTransferTime();

            list.add(d);
        }

        return list;
    }
}


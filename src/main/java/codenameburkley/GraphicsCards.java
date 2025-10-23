package codenameburkley;

import oshi.SystemInfo;
import oshi.hardware.GraphicsCard;
import java.util.List;

public class GraphicsCards {
    protected SystemInfo si = new SystemInfo();
    protected List<GraphicsCard> gpuList = si.getHardware().getGraphicsCards();

    public String[] getNames() {
        String[] names = new String[gpuList.size()];
        int i = 0;

        for (GraphicsCard gpu: gpuList) {
            names[i] = gpu.getName();
            i++;
        }
        return names;
    }

    public String[] getDeviceIds() {
        String[] deviceIds = new String[gpuList.size()];
        int i = 0;

        for (GraphicsCard gpu: gpuList) {
            deviceIds[i] = gpu.getDeviceId();
            i++;
        }
        return deviceIds;
    }

    public String[] getVendors() {
        String[] vendors = new String[gpuList.size()];
        int i = 0;

        for (GraphicsCard gpu: gpuList) {
            vendors[i] = gpu.getVendor();
            i++;
        }
        return vendors;
    }

    public String[] getVersionInfos() {
        String[] VersionInfos = new String[gpuList.size()];
        int i = 0;

        for (GraphicsCard gpu: gpuList) {
            VersionInfos[i] = gpu.getVersionInfo();
            i++;
        }
        return VersionInfos;
    }

    public long[] getVRams() {
        long[] VersionInfos = new long[gpuList.size()];
        int i = 0;

        for (GraphicsCard gpu: gpuList) {
            VersionInfos[i] = gpu.getVRam();
            i++;
        }
        return VersionInfos;
    }
}

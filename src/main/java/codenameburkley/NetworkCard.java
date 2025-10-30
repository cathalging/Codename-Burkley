package codenameburkley;

import oshi.SystemInfo;
import oshi.hardware.NetworkIF;

import java.util.List;

public class NetworkCard {
    protected SystemInfo si = new SystemInfo();
    protected List<NetworkIF> networkIfs;

    public NetworkCard() {
        this(false);
    }

    public NetworkCard(boolean includeLocal) {
        refreshNetworkList(includeLocal);
    }

    public String[] getNames() {
        String[] names = new String[networkIfs.size()];
        int i = 0;

        for (NetworkIF networkIf : networkIfs) {
            names[i] = networkIf.getName();
            i++;
        }

        return names;
    }

    public int[] getIndices() {
        int[] indices = new int[networkIfs.size()];
        int i = 0;

        for (NetworkIF networkIf : networkIfs) {
            indices[i] = networkIf.getIndex();
            i++;
        }

        return indices;
    }

    public String[] getDisplayNames() {
        String[] displayNames = new String[networkIfs.size()];
        int i = 0;

        for (NetworkIF networkIf : networkIfs) {
            displayNames[i] = networkIf.getDisplayName();
            i++;
        }

        return displayNames;
    }

    public String[] getMacaddrs() {
        String[] macaddrs = new String[networkIfs.size()];
        int i = 0;

        for (NetworkIF networkIf : networkIfs) {
            macaddrs[i] = networkIf.getMacaddr();
            i++;
        }

        return macaddrs;
    }

    public String[][] getIPv4addrs() {
        String[][] ipv4addrs = new String[networkIfs.size()][];
        int i = 0;

        for (NetworkIF networkIf : networkIfs) {
            ipv4addrs[i] = networkIf.getIPv4addr();
            i++;
        }

        return ipv4addrs;
    }

    public String[][] getIPv6addr() {
        String[][] ipv6addrs = new String[networkIfs.size()][];
        int i = 0;

        for (NetworkIF networkIf : networkIfs) {
            ipv6addrs[i] = networkIf.getIPv6addr();
            i++;
        }

        return ipv6addrs;
    }

    public long[] getSpeeds() {
        long[] speeds = new long[networkIfs.size()];
        int i = 0;

        for (NetworkIF networkIf : networkIfs) {
            long bPerMB = 8_000_000_000L;
            speeds[i] = networkIf.getSpeed() / bPerMB;
            i++;
        }

        return speeds;
    }

    public void refreshNetworkList(boolean includeLocal) {
        if (includeLocal)
            networkIfs = si.getHardware().getNetworkIFs(true);
        else
            networkIfs = si.getHardware().getNetworkIFs();
    }
}

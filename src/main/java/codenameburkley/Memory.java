package codenameburkley;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.VirtualMemory;
import oshi.hardware.PhysicalMemory;
import java.util.List;


public class Memory {

    final long BYTES_PER_GiB = 1024 * 1024 * 1024;
    final long BYTES_PER_GB = 1_000_000_000;//BYTES_PER_GB converts bytes into gigabytes. Final to make sure it cannot be changed. Safeguarding
    SystemInfo si = new SystemInfo();
    GlobalMemory memory = si.getHardware().getMemory();

    double total = memory.getTotal();
    double available = memory.getAvailable();
    double used = total - available;

    VirtualMemory swap = memory.getVirtualMemory();

    public double getMemGB() {
        return (total / BYTES_PER_GB);
    }

    public double getUsedMemGB() {
        return (used / BYTES_PER_GB);
    }

    public double getUnusedMemGB() {
        return (available / BYTES_PER_GB);
    }
    public double getMemGiB() {
        return (total / BYTES_PER_GiB);
    }

    public double getUsedMemGiB() {
        return (used / BYTES_PER_GiB);
    }

    public double getUnusedMemGiB() {
        return (available / BYTES_PER_GiB);
    }

    public double swapMemTotalGB() {
        return (double) swap.getSwapTotal() / BYTES_PER_GB;
    }

    public double swapMemTotalGiB() {
        return (double) swap.getSwapTotal() / BYTES_PER_GiB;
    }

    public double swapMemUsedGB() {
        return (double) swap.getSwapUsed() / BYTES_PER_GB;
    }

    public double swapMemUsedGiB() {
        return (double) swap.getSwapUsed() / BYTES_PER_GiB;
    }

    public double swapMemPagesInKB() {
        return (double) swap.getSwapPagesIn() / 1000;
    }

    public double swapMemPagesInKiB() {
        return (double) swap.getSwapPagesIn() / 1024;
    }

    public double swapPagesOutKB() {
        return (double) swap.getSwapPagesOut() / 1000;
    }

    public double swapPagesOutKiB() {
        return (double) swap.getSwapPagesOut() / 1024;
    }

    protected List<PhysicalMemory> stickList = memory.getPhysicalMemory();

    public String[] getNames() {
        String[] names = new String[stickList.size()];
        int i = 0;

        for (PhysicalMemory ram : stickList) {
            names[i] = ram.getBankLabel();
            i++;
        }
        return names;
    }
    public long[] getCapacity() {
        long[] capacity = new long[stickList.size()];
        int i = 0;

        for (PhysicalMemory ram : stickList) {
            capacity[i] = ram.getCapacity();
            i++;
        }
        return capacity;
    }
    public long[] getSpeed() {
        long[] speed = new long[stickList.size()];
        int i = 0;

        for (PhysicalMemory ram : stickList) {
            speed[i] = ram.getCapacity();
            i++;
        }
        return speed;
    }
    public String[] getType() {
        String[] type = new String[stickList.size()];
        int i = 0;

        for (PhysicalMemory ram : stickList) {
            type[i] = ram.getBankLabel();
            i++;
        }
        return type;
    }

}

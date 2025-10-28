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

    long total = memory.getTotal(); //long is 64 bit and is exact, double is not exact.
    long available = memory.getAvailable(); // just initialising variables here
    long used = total - available;

    public long getMemGB() {
        long total = memory.getTotal();
        return (total / BYTES_PER_GB);
    }

    public long getUsedMemGB() {
        long total = memory.getTotal();
        long available = memory.getAvailable();
        long used = total - available;
        return (used / BYTES_PER_GB);
    }

    public long getUnusedMemGB() {
        long total = memory.getAvailable();
        return (available / BYTES_PER_GB);
    }
    public long getMemGiB() {
        long total = memory.getTotal();
        return (total / BYTES_PER_GiB);
    }

    public long getUsedMemGiB() {
        long total = memory.getTotal();
        long available = memory.getAvailable();
        long used = total - available;
        return (used / BYTES_PER_GiB);
    }

    public long getUnusedMemGiB() {
        long total = memory.getAvailable();
        return (available / BYTES_PER_GiB);
    }

    public long swapMemTotal() {
        VirtualMemory swap = memory.getVirtualMemory();
        return swap.getSwapTotal();
    }
    public long swapMemUsed() {
        VirtualMemory swap = memory.getVirtualMemory();
        return swap.getSwapUsed();
    }
    public long swapMemPagesIn() {
        VirtualMemory swap = memory.getVirtualMemory();
        return swap.getSwapPagesIn();
    }
    public long swapPagesOut() {
        VirtualMemory swap = memory.getVirtualMemory();
        return swap.getSwapPagesOut();
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

package codenameburkley;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import java.util.ArrayList;

public class CPU {
    private CentralProcessor cpu;
    private SystemInfo si;

    public CPU()
    {
        si = new SystemInfo();
        cpu = si.getHardware().getProcessor();
    }

    public String getName() {
        return cpu.getProcessorIdentifier().getName();
    }

    public String getVendor() {
        return cpu.getProcessorIdentifier().getVendor();
    }

    public String getMicroArc() {
        return cpu.getProcessorIdentifier().getMicroarchitecture();
    }

    public int getCoreCount() {
        return cpu.getPhysicalProcessorCount();
    }

    public int getThreadCount() {
        return cpu.getLogicalProcessorCount();
    }

    public long getMaxFreq() {
        return cpu.getMaxFreq() / 1000000;
    }

    public long[] getCurrentFreqs() {
        return cpu.getCurrentFreq();
    }
}
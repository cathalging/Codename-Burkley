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

    enum InfoFlags {
        NAME,
        IDENTIFIER,
        VENDOR,
        PROCESSORID,
        ALL
    }

    public ArrayList<String> getCPUInfo(InfoFlags... flags) {
        ArrayList<String> retValues = new ArrayList<String>();

        for (InfoFlags flag : flags)
        {
            switch (flag) {
                case InfoFlags.NAME:
                    retValues.add(cpu.getProcessorIdentifier().getName());
                case InfoFlags.IDENTIFIER:
                    retValues.add(cpu.getProcessorIdentifier().getIdentifier());
                case InfoFlags.PROCESSORID:
                    retValues.add(cpu.getProcessorIdentifier().getProcessorID());
                case InfoFlags.VENDOR:
                    retValues.add(cpu.getProcessorIdentifier().getVendor());
                case ALL:
                    retValues.add(cpu.getProcessorIdentifier().getName());
                    retValues.add(cpu.getProcessorIdentifier().getIdentifier());
                    retValues.add(cpu.getProcessorIdentifier().getProcessorID());
                    retValues.add(cpu.getProcessorIdentifier().getVendor());
            }
        }
        return retValues;
    }
}
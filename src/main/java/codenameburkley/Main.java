package codenameburkley;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

public class Main {
    public static void main(String[] args) {
        SystemInfo si = new SystemInfo();
        CentralProcessor cpu = si.getHardware().getProcessor();
        System.out.println("CPU: " + cpu.getProcessorIdentifier().getName());
    }
}

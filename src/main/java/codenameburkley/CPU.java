package codenameburkley;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

public class CPU {
    private CentralProcessor cpu;
    private SystemInfo si;
    CPULoad cpuLoad = new CPULoad();
    Thread thread = new Thread(cpuLoad);

    public CPU()
    {
        si = new SystemInfo();
        cpu = si.getHardware().getProcessor();
        thread.start();
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

class CPULoad implements Runnable {
    SystemInfo si = new SystemInfo();
    CentralProcessor cpu = si.getHardware().getProcessor();

    @Override
    public void run() {
        while (true) {
            System.out.println(calcCPULoad());
        }
    }

    public double calcCPULoad() {
        long[] prevTicks = cpu.getSystemCpuLoadTicks();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long[] ticks = cpu.getSystemCpuLoadTicks();

        long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long sys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];

        long totalCPU = user + nice + sys + idle + iowait + irq + softirq + steal;
        return (double) (totalCPU - idle) / totalCPU * 100;
    }
}
package codenameburkley;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import java.util.List;

public class CPU {
    private CentralProcessor cpu;
    private SystemInfo si;
    private CPULoad cpuLoad;
    private Thread thread;

    public CPU()
    {
        si = new SystemInfo();
        cpu = si.getHardware().getProcessor();
        cpuLoad = new CPULoad();
        thread = new Thread(cpuLoad);
    }

    public String getName() {
        return cpu.getProcessorIdentifier().getName();
    }

    public String getVendor() {
        return cpu.getProcessorIdentifier().getVendor();
    }

    public String getMicroArc() {
        return cpu.getProcessorIdentifier().getMicroarchitecture() + " " + System.getProperty("os.arch");
    }

    public int getCoreCount() {
        return cpu.getPhysicalProcessorCount();
    }

    public int getThreadCount() {
        return cpu.getLogicalProcessorCount();
    }

    public double getMaxFreq() {
        return cpu.getMaxFreq() / 1.0e9;
    }

    public long[] getCurrentFreqs() {
        return cpu.getCurrentFreq();
    }

    public double getAverageFreq() {
        long[] currFreqs = getCurrentFreqs();
        double sum = 0;
        for (int i = 0; i < currFreqs.length; i++) {
            sum += (currFreqs[i] / 1.0e9);
        }
        return sum / getCoreCount();
    }

    public String getCacheInfo() {
        List<CentralProcessor.ProcessorCache> caches = cpu.getProcessorCaches();
        String retString = "";
        for (CentralProcessor.ProcessorCache cache : caches) {
            retString += String.format("Level: %d, Size: %dKB, Type: %s, Line Size: %d bytes %n", cache.getLevel(), cache.getCacheSize(), cache.getType(), cache.getLineSize());
        }
        return retString;
    }

    public void startThread() {
        thread.start();
    }

    public void endThread() {
        cpuLoad.endThread();
    }
}

class CPULoad implements Runnable {
    private SystemInfo si;
    private CentralProcessor cpu;
    private volatile boolean running;

    CPULoad() {
        si = new SystemInfo();
        cpu = si.getHardware().getProcessor();
        running = true;
    }

    @Override
    public void run() {
        while (running) {
            System.out.printf("%nCPU Load: %.2f", calcCPULoad());
        }
        System.out.println("%nThread Stopped");
    }

    public double calcCPULoad() {
        long[] prevTicks = cpu.getSystemCpuLoadTicks();
        try {
            Thread.sleep(300);
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

    public void endThread() {
        running = false;
    }
}
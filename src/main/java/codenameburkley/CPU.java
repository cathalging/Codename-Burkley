package codenameburkley;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import java.util.List;

public class CPU {
    private CentralProcessor cpu;
    private SystemInfo si;
    private CPULoad cpuLoad;
    private CPUFreq cpuFreq;
    private Thread loadThread;
    private Thread freqThread;

    public CPU()
    {
        si = new SystemInfo();
        cpu = si.getHardware().getProcessor();
        cpuLoad = new CPULoad();
        cpuFreq = new CPUFreq();
        loadThread = new Thread(cpuLoad);
        freqThread = new Thread(cpuFreq);
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

    public void startFreqThread() {
        freqThread.start();
    }

    public void endFreqThread() {
        cpuFreq.endThread();
    }

    public String getCacheInfo() {
        List<CentralProcessor.ProcessorCache> caches = cpu.getProcessorCaches();
        String retString = "";
        for (CentralProcessor.ProcessorCache cache : caches) {
            retString += String.format("Level: %d, Size: %dKB, Type: %s, Line Size: %d bytes %n", cache.getLevel(), cache.getCacheSize(), cache.getType(), cache.getLineSize());
        }
        return retString;
    }

    public void startLoadThread() {
        loadThread.start();
    }

    public void endLoadThread() {
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
            System.out.printf("\rCPU Load: %.2f%%", calcCPULoad());
        }
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

class CPUFreq implements Runnable {
    private SystemInfo si;
    private CentralProcessor cpu;
    private volatile boolean running;
    int coreCount;
    String header = "";

    CPUFreq() {
        si = new SystemInfo();
        cpu = si.getHardware().getProcessor();
        running = true;
        coreCount = cpu.getLogicalProcessorCount();
        for (int i = 0; i < coreCount; i++) {
            if (i+1 < 10) header += String.format("CPU %d   ", i+1);
            else header += String.format("CPU %d  ", i+1);
        }
        header += "AVG";
    }

    @Override
    public void run() {
        System.out.println("\n" + header);
        while (running) {
            System.out.print("\r" + printFreqs());
        }
    }

    public String printFreqs() {
        long[] currFreqs = cpu.getCurrentFreq();
        String retStr = "";
        double sum = 0;
        for (int i = 0; i < currFreqs.length; i++) {
            double currFreq = currFreqs[i] / 1.0e9;
            retStr += String.format("%.2fGHz ", currFreq);
            sum += currFreq;
        }
        retStr += String.format("%.2fGHz", sum / coreCount);
        return retStr;
    }

    public void endThread() {
        running = false;
    }
}
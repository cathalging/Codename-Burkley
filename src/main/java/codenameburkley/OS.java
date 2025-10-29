package codenameburkley;

import oshi.SystemInfo;
import oshi.software.os.*;
import java.util.*;
import java.lang.Thread;

public class OS {
    protected SystemInfo si = new SystemInfo();
    private final OperatingSystem os = si.getOperatingSystem();

    private List<OSProcess> processes = os.getProcesses();
    private TreeMap<String, Double> processUsage = new TreeMap<>();

    public String getFamily() {
        return os.getFamily();
    }

    public int getBitness() {
        return os.getBitness();
    }

    //Processes
    public void setProcesses() {
        processes = os.getProcesses();
    }

    public List<OSProcess> getProcesses() {
        return processes;
    }

    /* public OSProcess getProcess(int id) {
        return processes.get(id);
    } */

    public String getProcessName(int id) {
        return processes.get(id).getName();
    }

    public void getProcessUsage() {
        new Thread(() -> {
            try {
                List<OSProcess> prev = os.getProcesses();
                Thread.sleep(100);
                List<OSProcess> curr = os.getProcesses();

                processUsage.clear();

                for (OSProcess proc : curr) {
                    try {
                        int currId = proc.getProcessID();
                        for (OSProcess prevProc : prev) {
                            if (currId == prevProc.getProcessID()) {
                                processUsage.put(proc.getName(), proc.getProcessCpuLoadBetweenTicks(prevProc));
                                break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                for (Map.Entry<String, Double> entry: processUsage.descendingMap().entrySet()){
                    System.out.printf("Process: " + entry.getKey() + "\nValue: " + entry.getValue() + "\n\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    //Threads
    public int getThreadCount() {
        return os.getThreadCount();
    }

    public String getCurrentThreadName() {
        return os.getCurrentThread().getName();
    }
}

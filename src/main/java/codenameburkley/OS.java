package codenameburkley;

import oshi.SystemInfo;
import oshi.software.os.*;
import java.util.List;
import java.lang.Thread;
import java.util.HashMap;

public class OS {
    protected SystemInfo si = new SystemInfo();
    private final OperatingSystem os = si.getOperatingSystem();

    private List<OSProcess> processes = os.getProcesses();
    private HashMap<String, Double> processUsage = new HashMap<>();

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

                processUsage.forEach((key, value) -> System.out.println(key + " " + value));
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

package codenameburkley;

import oshi.SystemInfo;
import oshi.software.os.*;
import java.util.List;

public class OS {
    protected SystemInfo si = new SystemInfo();
    private final OperatingSystem os = si.getOperatingSystem();

    private List<OSProcess> processes = os.getProcesses();

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

    public OSProcess getProcess(int id) {
        return processes.get(id);
    }

    public String getProcessName(int id) {
        return processes.get(id).getName();
    }

    //Threads
    public int getThreadCount() {
        return os.getThreadCount();
    }

    public String getCurrentThreadName() {
        return os.getCurrentThread().getName();
    }
}

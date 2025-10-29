package codenameburkley;

public class Main {
    public static void main(String[] args) {
        Controller c = new Controller();

        c.getGraphicsInfo();
        OS os = new OS();
        os.getProcessUsage();
        c.getUsbInfo();
        c.getNetworkInfo();
        c.getStaticCPUInfo();
        c.getMemoryInfo();
        c.getVirtualMemInfo();
        c.getRAMInfo();
        c.getCPULoad();
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.endCPULoad();
        c.getCPUFreq();
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.endCPUFreq();
        c.getPCIDevices();
    }
}

package codenameburkley;

public class Main {
    public static void main(String[] args) {
        Controller c = new Controller();

        //c.getGraphicsInfo();

        //c.getStaticCPUInfo();

        //OS os = new OS();
        //os.getProcessUsage();
        //c.getUsbInfo();
        //c.getNetworkInfo();
        //c.getCPUFreq();
        c.getMemoryInfo();
        c.getVirtualMemInfo();
        c.getRAMInfo();
    }
}

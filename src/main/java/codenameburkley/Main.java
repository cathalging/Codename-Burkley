package codenameburkley;

public class Main {
    public static void main(String[] args) {
        Controller c = new Controller();

        //c.getGraphicsInfo();

        OS os = new OS();
        os.getProcessUsage();
        c.getUsbInfo();
        c.getNetworkInfo();
    }
}

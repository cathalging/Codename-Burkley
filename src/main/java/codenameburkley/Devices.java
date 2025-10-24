package codenameburkley;

import oshi.SystemInfo;
import oshi.hardware.UsbDevice;

import java.util.List;

public class Devices {
    private final SystemInfo si = new SystemInfo();
    private List<UsbDevice> usbDevices;

    public List<UsbDevice> getUsbList() {
        refreshUsbList();

        return usbDevices;
    }

    private void refreshUsbList() {
        usbDevices = si.getHardware().getUsbDevices(false);
    }
}

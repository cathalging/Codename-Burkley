package codenameburkley;

import oshi.SystemInfo;
import oshi.hardware.UsbDevice;

import java.util.List;

public class Usb {
    private final SystemInfo si = new SystemInfo();
    private List<UsbDevice> usbDevices;

    public Usb() {
        refreshUsbList();
    }

    public String[] getNames() {
        String[] names = new String[usbDevices.size()];
        int i = 0;

        for (UsbDevice usb: usbDevices) {
            names[i] = usb.getName();
            i++;
        }
        return names;
    }

    public String[] getProductIds() {
        String[] productIds = new String[usbDevices.size()];
        int i = 0;

        for (UsbDevice usb: usbDevices) {
            productIds[i] = usb.getProductId();
            i++;
        }
        return productIds;
    }

    public String[] getUniqueDeviceIds() {
        String[] serialNumbers = new String[usbDevices.size()];
        int i = 0;

        for (UsbDevice usb: usbDevices) {
            serialNumbers[i] = usb.getUniqueDeviceId();
            i++;
        }
        return serialNumbers;
    }

    public String[] getVendors() {
        String[] vendors = new String[usbDevices.size()];
        int i = 0;

        for (UsbDevice usb: usbDevices) {
            vendors[i] = usb.getVendor();
            i++;
        }
        return vendors;
    }

    public String[] getVendorsId() {
        String[] vendorsId = new String[usbDevices.size()];
        int i = 0;

        for (UsbDevice usb: usbDevices) {
            vendorsId[i] = usb.getVendorId();
            i++;
        }
        return vendorsId;
    }

    public void refreshUsbList() {
        usbDevices = si.getHardware().getUsbDevices(false);
    }
}

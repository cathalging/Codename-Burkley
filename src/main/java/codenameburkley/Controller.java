package codenameburkley;

import oshi.hardware.UsbDevice;

import java.util.List;

public class Controller {
    GraphicsCards gpus = new GraphicsCards();
    Devices devices = new Devices();

    //GPU Info
    public void getGraphicsInfo() {
        System.out.printf("%nNames:");
        for (String name : gpus.getNames()) {
            System.out.println(name);
        }
        System.out.printf("%nVendors:");
        for (String vendor : gpus.getVendors()) {
            System.out.println(vendor);
        }
        System.out.printf("%nVersion Info:");
        for (String versionInfo : gpus.getVersionInfos()) {
            System.out.println(versionInfo);
        }
        System.out.printf("%nDevice Ids:");
        for (String deviceId : gpus.getDeviceIds()) {
            System.out.println((deviceId));
        }
        System.out.printf("%nVRams:");
        for (long VRam : gpus.getVRams()) {
            System.out.println(VRam);
        }
    }

    // USB Info
    public void getUsbInfo() {
        List<UsbDevice> usbDeviceList = devices.getUsbList();

        for (UsbDevice usbDevice : usbDeviceList) {
            System.out.printf("%nName: %s%nProduct ID: %s%nSerial Number: %s%nVendor: %s%nVendor ID: %s%n",
                    usbDevice.getName(), usbDevice.getProductId(), usbDevice.getSerialNumber(), usbDevice.getVendor(), usbDevice.getVendorId());
        }
    }
}
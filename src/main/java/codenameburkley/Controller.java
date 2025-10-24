package codenameburkley;

import oshi.hardware.UsbDevice;

import java.util.List;

public class Controller {
    GraphicsCards gpus = new GraphicsCards();
    Usb usbs = new Usb();

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
        System.out.println("\nNames:");
        for (String name : usbs.getNames()) {
            System.out.println(name);
        }

        System.out.println("\nProduct IDs:");
        for (String productId : usbs.getProductIds()) {
            System.out.println(productId);
        }

        System.out.println("\nSerial Numbers:");
        for (String serialNumber : usbs.getSerialNumbers()) {
            System.out.println(serialNumber);
        }

        System.out.println("\nVendors:");
        for (String vendor : usbs.getVendors()) {
            System.out.println(vendor);
        }

        System.out.println("\nVendor IDs:");
        for (String vendorIds : usbs.getVendorsId()) {
            System.out.println(vendorIds);
        }
    }
}
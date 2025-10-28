package codenameburkley;

import oshi.hardware.UsbDevice;

import java.util.List;

public class Controller {
    GraphicsCards gpus = new GraphicsCards();
    Usb usbs = new Usb();
    CPU cpu = new CPU();
    NetworkCard networkCard = new NetworkCard(true);

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

    //CPU Info
    public void getCPUInfo() {
        
    }

    //Network Info
    public void getNetworkInfo() {
        System.out.println("\nNames:");
        for (String name : networkCard.getNames()) {
            System.out.println(name);
        }

        System.out.println("\nDisplay Names:");
        for (String displayName : networkCard.getDisplayNames()) {
            System.out.println(displayName);
        }

        System.out.println("\nMac Addresses:");
        for (String macAddress : networkCard.getMacaddrs()) {
            System.out.println(macAddress);
        }

        System.out.println("\nIPv4 Addresses:");
        for (String[] ipv4Address : networkCard.getIPv4addrs()) {
            System.out.println(ipv4Address);
        }

        System.out.println("\nIPv6 Addresses:");
        for (String[] ipv6Address : networkCard.getIPv6addr()) {
            System.out.println(ipv6Address);
        }

        System.out.println("\nSpeeds:");
        for (long speed : networkCard.getSpeeds()) {
            System.out.println(speed);
        }
    }
}
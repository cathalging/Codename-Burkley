package codenameburkley;

import oshi.hardware.UsbDevice;

import java.util.List;

public class Controller {
    private final GraphicsCards gpus = new GraphicsCards();
    private final Usb usbs = new Usb();
    private final CPU cpu = new CPU();
    private final NetworkCard networkCard = new NetworkCard(true);
    private final Memory mem = new Memory();
    private final pciDevices pci = new pciDevices();

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

    public void getPCIDevices() {
        for (int i = 0; i < pci.getBusAddresses().size(); i++) {
            System.out.printf("%nBus Address: %s | Class: %s | Vendor & Device Name: %s | VendorID: %s | DeviceID: %s | Class Code: %s",
                    pci.getBusAddresses().get(i), pci.getClasses().get(i), pci.getVendorDeviceNames().get(i), pci.getVendorIds().get(i), pci.getDeviceIds().get(i), pci.getClassCodes().get(i));
        }
    }

    //CPU Info that won't change
    public void getStaticCPUInfo() {
        System.out.println("CPU Name: " + cpu.getName());
        System.out.println("CPU Vendor: " + cpu.getVendor());
        System.out.println("CPU Arc: " + cpu.getMicroArc());
        System.out.println("CPU Max Frequency: " + cpu.getMaxFreq() + "GHz");
        System.out.println("CPU Core Count: " + cpu.getCoreCount());
        System.out.println("CPU Thread Count: " + cpu.getThreadCount());
        System.out.println("CPU Cache Info: \n" + cpu.getCacheInfo());
    }

    //call when want to start displaying freq thread
    public void getCPUFreq() {
        cpu.startFreqThread();
    }

    //call when stop wanting to display freq
    public void endCPUFreq() {
        cpu.endFreqThread();
    }

    //call when want to start displaying load
    public void getCPULoad() {
        cpu.startLoadThread();
    }

    //call when stop wanting to display load
    public void endCPULoad() {
        cpu.endLoadThread();
    }

    public void getMemoryInfo() {
        System.out.printf("%nTotal Memory: %.2fGB || %.2fGib", mem.getMemGB(), mem.getMemGiB());
        System.out.printf("%nUsed Memory: %.2fGB || %.2fGib", mem.getUsedMemGB(), mem.getUsedMemGiB());
        System.out.printf("%nFree Memory: %.2fGB || %.2fGib", mem.getUnusedMemGB(), mem.getUnusedMemGiB());
    }

    public void getVirtualMemInfo() {
        System.out.printf("%nTotal Swap Memory: %.2fGB || %.2fGiB", mem.swapMemTotalGB(), mem.swapMemTotalGiB());
        System.out.printf("%nSwap Memory Used: %.2fGB || %.2fGiB", mem.swapMemUsedGB(), mem.swapMemUsedGiB());
        System.out.printf("%nSwap Memory Pages In: %.2fKB || %.2fKiB", mem.swapMemPagesInKB(), mem.swapMemPagesInKiB());
        System.out.printf("%nSwap Memory Pages Out: %.2fKB || %.2fkiB", mem.swapPagesOutKB(), mem.swapPagesOutKiB());
    }

    public void getRAMInfo() {
        System.out.println("\nBrand Labels");
        String[] names = mem.getNames();
        for (String name : names) {
            System.out.println(name);
        }

        System.out.println("Capacities");
        long[] capcities = mem.getCapacity();
        for (long capacity : capcities) {
            System.out.println(capacity + "GB");
        }

        System.out.println("Speeds");
        long[] speeds = mem.getSpeed();
        for (long speed : speeds) {
            System.out.println(speed + "MGHz");
        }

        System.out.println("Types");
        String[] types = mem.getType();
        for (String type : types) {
            System.out.println(type);
        }
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
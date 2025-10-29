package codenameburkley;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class pciDevices {
    private ArrayList<String> busAddresses;
    private ArrayList<String> classes;
    private ArrayList<String> vendorDeviceNames;
    private ArrayList<String> vendorIds;
    private ArrayList<String> deviceIds;
    private ArrayList<String> classCodes;

    pciDevices() {
        busAddresses = new ArrayList<>();
        classes = new ArrayList<>();
        vendorDeviceNames = new ArrayList<>();
        vendorIds = new ArrayList<>();
        deviceIds = new ArrayList<>();
        classCodes = new ArrayList<>();
        parseOutput();
    }

    private void parseOutput() {
        try {
            Process proc = new ProcessBuilder("lspci", "-nn").start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            while (reader.readLine() != null) {
                parseLine(reader.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //busAddy Class -name  -code             Vendor name + device name                       ven+dev id  rev num
    //00:00.0 Host bridge [0600]: Intel Corporation Alder Lake-U15 Host and DRAM Controller [8086:4601] (rev 04) -output

    private void parseLine(String line) {
        String[] lineParts = line.split("\\s+", 2); // \s+ is regex for whitespace
        busAddresses.add(lineParts[0]);

        String rest = lineParts[1];
        classes.add(rest.substring(0, rest.indexOf('[')).trim());
        classCodes.add(rest.substring(rest.indexOf('[') + 1, rest.indexOf(']')).trim());

        vendorDeviceNames.add(rest.substring(rest.indexOf(':') + 1, rest.lastIndexOf('[')).trim());

        String ids = rest.substring(rest.lastIndexOf('[') + 1, rest.lastIndexOf(']')).trim();
        String[] idsSplit = ids.split(":", 2);
        vendorIds.add(idsSplit[0]);
        deviceIds.add(idsSplit[1]);
    }

    public ArrayList<String> getBusAddresses() { return busAddresses; }
    public ArrayList<String> getClasses() { return classes; }
    public ArrayList<String> getVendorDeviceNames() { return vendorDeviceNames; }
    public ArrayList<String> getVendorIds() { return vendorIds; }
    public ArrayList<String> getDeviceIds() { return deviceIds; }
    public ArrayList<String> getClassCodes() { return classCodes; }

}
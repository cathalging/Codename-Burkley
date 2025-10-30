package codenameburkley;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class pciDevices {
    private ArrayList<PciDevice> devices;

    public pciDevices() {
        devices = new ArrayList<PciDevice>();
        parseOutput();
    }

    private void parseOutput() {
        try {
            Process proc = new ProcessBuilder("lspci", "-nn").start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            while (reader.readLine() != null) {
                parseLine(reader.readLine());
            }
            proc.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //busAddy Class -name  -code             Vendor name + device name                       ven+dev id  rev num
    //00:00.0 Host bridge [0600]: Intel Corporation Alder Lake-U15 Host and DRAM Controller [8086:4601] (rev 04) -output

    private void parseLine(String line) {
        PciDevice d = new PciDevice();
        String[] lineParts = line.split("\\s+", 2); // \s+ is regex for whitespace
        d.setBusAddress(lineParts[0]);

        String rest = lineParts[1];
        d.setClasses(rest.substring(0, rest.indexOf('[')).trim());
        d.setClassCodes(rest.substring(rest.indexOf('[') + 1, rest.indexOf(']')).trim());

        d.setVendorDeviceNames(rest.substring(rest.indexOf(':') + 1, rest.lastIndexOf('[')).trim());

        String ids = rest.substring(rest.lastIndexOf('[') + 1, rest.lastIndexOf(']')).trim();
        String[] idsSplit = ids.split(":", 2);
        d.setVendorIds(idsSplit[0]);
        d.setDeviceIds(idsSplit[1]);
        devices.add(d);
    }

    public ArrayList<PciDevice> getDevices() { return devices; }
}
package codenameburkley;

public class PciDevice {
    private String busAddress;
    private String classes;
    private String vendorDeviceNames;
    private String vendorIds;
    private String deviceIds;
    private String classCodes;

    public String getBusAddress() { return busAddress; }
    public String getClasses() { return classes; }
    public String getVendorDeviceNames() { return vendorDeviceNames; }
    public String getVendorIds() { return vendorIds; }
    public String getDeviceIds() { return deviceIds; }
    public String getClassCodes() { return classCodes; }

    public void setBusAddress(String busAddress) { this.busAddress = busAddress; }
    public void setClasses(String classes) { this.classes = classes; }
    public void setVendorDeviceNames(String vendorDeviceNames) { this.vendorDeviceNames = vendorDeviceNames; }
    public void setVendorIds(String vendorIds) { this.vendorIds = vendorIds; }
    public void setDeviceIds(String deviceIds) { this.deviceIds = deviceIds; }
    public void setClassCodes(String classCodes) { this.classCodes = classCodes; }
}
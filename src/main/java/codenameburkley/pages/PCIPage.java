package codenameburkley.pages;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import codenameburkley.MainFrame;
import codenameburkley.pciDevices;
import codenameburkley.PciDevice;

public class PCIPage extends JPanel {
    private pciDevices pci = new pciDevices();
    private ArrayList<PciDevice> devices = pci.getDevices();

    public PCIPage(MainFrame frame) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("PCI Devices Info");
        title.setFont(new Font("Serif", Font.BOLD, 32));

        JButton back = new JButton("Back");
        back.addActionListener(e -> frame.showPage("Home"));

        JPanel pciPanel = new JPanel();
        pciPanel.setLayout(new BoxLayout(pciPanel, BoxLayout.Y_AXIS));
        pciPanel.setBorder(BorderFactory.createTitledBorder("Devices"));

        for (PciDevice device : devices) {
            pciPanel.add(new JLabel("Bus Adress: " + device.getBusAddress() + " | Class: " + device.getClasses() + " |Class code: " + device.getClassCodes()+ " | Vendor and Device name: " + device.getVendorDeviceNames() + " | Device ID: " + device.getDeviceIds() + " | Vendor ID: " + device.getVendorIds()));
            /*
            pciPanel.add(new JLabel("Class: " + device.getClasses()));
            pciPanel.add(new JLabel("Class code: " + device.getClassCodes()));
            pciPanel.add(new JLabel("Vendor and Device name: " + device.getVendorDeviceNames()));"Device ID: " + device.getDeviceIds()
            pciPanel.add(new JLabel("Device ID: " + device.getDeviceIds()));
            pciPanel.add(new JLabel("Vendor ID: " + device.getVendorIds()));

             */
            pciPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        add(title, BorderLayout.PAGE_START);
        add(pciPanel);
        add(back, BorderLayout.PAGE_END);
    }
}
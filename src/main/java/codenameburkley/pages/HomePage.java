package codenameburkley.pages;

import javax.swing.*;
import java.awt.*;
import codenameburkley.MainFrame;

public class HomePage extends JPanel {
    public HomePage(MainFrame frame) {
        setLayout(new GridLayout(3, 3, 10, 10));

        JButton cpuPage = new JButton("CPU");
        JButton memPage = new JButton("Memory");
        JButton gpuPage = new JButton("GPU");
        JButton diskPage = new JButton("Disk");
        JButton networkPage = new JButton("Network");
        JButton osPage = new JButton("OS");
        JButton liveCPUPage = new JButton("Live CPU Info");
        JButton usbPage = new JButton("USB");
        JButton pciPage = new JButton("PCI Devices");

        cpuPage.addActionListener(e -> frame.showPage("CPUPage"));
        memPage.addActionListener(e -> frame.showPage("MemoryPage"));
        gpuPage.addActionListener(e -> frame.showPage("GPUPage"));
        diskPage.addActionListener(e -> frame.showPage("DiskPage"));
        networkPage.addActionListener(e -> frame.showPage("NetworkPage"));
        osPage.addActionListener(e -> frame.showPage("OSPage"));
        usbPage.addActionListener(e -> frame.showPage("USBPage"));
        liveCPUPage.addActionListener(e -> frame.showPage("LiveCPUPage"));
        pciPage.addActionListener(e -> frame.showPage("PCIPage"));

        add(cpuPage);
        add(memPage);
        add(gpuPage);
        add(diskPage);
        add(networkPage);
        add(osPage);
        add(usbPage);
        add(liveCPUPage);
        add(pciPage);
    }
}

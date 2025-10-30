package codenameburkley.pages;

import javax.swing.*;
import java.awt.*;
import codenameburkley.MainFrame;

public class HomePage extends JPanel {
    public HomePage(MainFrame frame) {
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton cpuPage = new JButton("CPU");
        JButton memPage = new JButton("Memory");
        JButton gpuPage = new JButton("GPU");
        JButton diskPage = new JButton("Disk");
        JButton networkPage = new JButton("Network");
        JButton osPage = new JButton("OS");
        JButton liveCPUPage = new JButton("Live CPU Info");

        cpuPage.addActionListener(e -> frame.showPage("CPUPage"));
        memPage.addActionListener(e -> frame.showPage("MemoryPage"));
        gpuPage.addActionListener(e -> frame.showPage("GPUPage"));
        diskPage.addActionListener(e -> frame.showPage("DiskPage"));
        networkPage.addActionListener(e -> frame.showPage("NetworkPage"));
        osPage.addActionListener(e -> frame.showPage("OSPage"));
        liveCPUPage.addActionListener(e -> frame.showPage("LiveCPUPage"));

        add(cpuPage);
        add(memPage);
        add(gpuPage);
        add(diskPage);
        add(networkPage);
        add(osPage);
        add(liveCPUPage);
    }
}

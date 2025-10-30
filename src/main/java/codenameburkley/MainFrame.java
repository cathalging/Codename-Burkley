package codenameburkley;

import javax.swing.*;
import java.awt.*;
import codenameburkley.pages.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        setTitle("Swing Multi-Page App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add pages
        mainPanel.add(new HomePage(this), "Home");
        mainPanel.add(new CPUPage(this), "CPUPage");
        mainPanel.add(new MemoryPage(this), "MemoryPage");
        mainPanel.add(new GPUPage(this), "GPUPage");
        mainPanel.add(new DiskPage(this), "DiskPage");
        mainPanel.add(new NetworkPage(this), "NetworkPage");
        mainPanel.add(new OSPage(this), "OSPage");
        mainPanel.add(new LiveCPUPage(this), "LiveCPUPage");
        mainPanel.add(new USBPage(this), "USBPage");
        mainPanel.add(new PCIPage(this), "PCIPage");

        add(mainPanel);
        setVisible(true);
    }

    // Switch method used by buttons
    public void showPage(String name) {
        cardLayout.show(mainPanel, name);
    }
}

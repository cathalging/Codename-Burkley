package codenameburkley.pages;

import javax.swing.*;
import java.awt.*;
import codenameburkley.MainFrame;
import codenameburkley.GraphicsCards;

public class GPUPage extends JPanel {
    private final GraphicsCards gpu = new GraphicsCards();

    public GPUPage(MainFrame frame) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("GPU Info");
        title.setFont(new Font("Serif", Font.BOLD, 32));

        JButton back = new JButton("Back");
        back.addActionListener(e -> frame.showPage("Home"));

        JPanel gpuPanel = new JPanel();
        gpuPanel.setLayout(new BoxLayout(gpuPanel, BoxLayout.Y_AXIS));
        gpuPanel.setBorder(BorderFactory.createTitledBorder("Graphics Cards"));

        String[] names = gpu.getNames();
        String[] deviceIds = gpu.getDeviceIds();
        String[] vendors = gpu.getVendors();
        String[] versionInfos = gpu.getVersionInfos();
        long[] vrams = gpu.getVRams();

        for (int i = 0; i < names.length; i++) {
            gpuPanel.add(new JLabel("GPU " + (i + 1) + ":"));
            gpuPanel.add(new JLabel("  Name: " + names[i]));
            gpuPanel.add(new JLabel("  Device ID: " + deviceIds[i]));
            gpuPanel.add(new JLabel("  Vendor: " + vendors[i]));
            gpuPanel.add(new JLabel("  Version Info: " + versionInfos[i]));
            gpuPanel.add(new JLabel("  VRAM: " + (double) vrams[i] / (1024 * 1024 * 1024) + " GiBs"));
            gpuPanel.add(Box.createRigidArea(new Dimension(0, 5))); // spacing between GPUs
        }

        add(title, BorderLayout.PAGE_START);
        add(gpuPanel);
        add(back, BorderLayout.PAGE_END);
    }
}

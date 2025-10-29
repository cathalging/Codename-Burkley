package codenameburkley.pages;

import javax.swing.*;
import java.awt.*;
import codenameburkley.MainFrame;
import codenameburkley.Memory;

public class MemoryPage extends JPanel {
    private Memory memory = new Memory();

    public MemoryPage(MainFrame frame) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Memory Info", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 32));

        JButton back = new JButton("Back");
        back.addActionListener(e -> frame.showPage("Home"));

        JLabel totalGB = new JLabel(String.format("Total Memory (GB): %.2f", memory.getMemGB()));
        JLabel usedGB = new JLabel(String.format("Used Memory (GB): %.2f", memory.getUsedMemGB()));
        JLabel unusedGB = new JLabel(String.format("Available Memory (GB): %.2f", memory.getUnusedMemGB()));

        JLabel totalGiB = new JLabel(String.format("Total Memory (GiB): %.2f", memory.getMemGiB()));
        JLabel usedGiB = new JLabel(String.format("Used Memory (GiB): %.2f", memory.getUsedMemGiB()));
        JLabel unusedGiB = new JLabel(String.format("Available Memory (GiB): %.2f", memory.getUnusedMemGiB()));

        JLabel swapTotalGB = new JLabel(String.format("Swap Total (GB): %.2f", memory.swapMemTotalGB()));
        JLabel swapUsedGB = new JLabel(String.format("Swap Used (GB): %.2f", memory.swapMemUsedGB()));

        JLabel swapTotalGiB = new JLabel(String.format("Swap Total (GiB): %.2f", memory.swapMemTotalGiB()));
        JLabel swapUsedGiB = new JLabel(String.format("Swap Used (GiB): %.2f", memory.swapMemUsedGiB()));

        JLabel swapPagesInKB = new JLabel(String.format("Swap Pages In (KB): %.2f", memory.swapMemPagesInKB()));
        JLabel swapPagesInKiB = new JLabel(String.format("Swap Pages In (KiB): %.2f", memory.swapMemPagesInKiB()));
        JLabel swapPagesOutKB = new JLabel(String.format("Swap Pages Out (KB): %.2f", memory.swapPagesOutKB()));
        JLabel swapPagesOutKiB = new JLabel(String.format("Swap Pages Out (KiB): %.2f", memory.swapPagesOutKiB()));

        JPanel memPanelGB = new JPanel();
        memPanelGB.setLayout(new BoxLayout(memPanelGB, BoxLayout.Y_AXIS));
        memPanelGB.setBorder(BorderFactory.createTitledBorder("Memory (GB)"));
        memPanelGB.add(totalGB);
        memPanelGB.add(usedGB);
        memPanelGB.add(unusedGB);

        JPanel memPanelGiB = new JPanel();
        memPanelGiB.setLayout(new BoxLayout(memPanelGiB, BoxLayout.Y_AXIS));
        memPanelGiB.setBorder(BorderFactory.createTitledBorder("Memory (GiB)"));
        memPanelGiB.add(totalGiB);
        memPanelGiB.add(usedGiB);
        memPanelGiB.add(unusedGiB);

        JPanel swapPanel = new JPanel();
        swapPanel.setLayout(new BoxLayout(swapPanel, BoxLayout.Y_AXIS));
        swapPanel.setBorder(BorderFactory.createTitledBorder("Swap Memory"));
        swapPanel.add(swapTotalGB);
        swapPanel.add(swapUsedGB);
        swapPanel.add(swapTotalGiB);
        swapPanel.add(swapUsedGiB);
        swapPanel.add(swapPagesInKB);
        swapPanel.add(swapPagesInKiB);
        swapPanel.add(swapPagesOutKB);
        swapPanel.add(swapPagesOutKiB);

        JPanel stickPanel = new JPanel();
        stickPanel.setLayout(new BoxLayout(stickPanel, BoxLayout.Y_AXIS));
        stickPanel.setBorder(BorderFactory.createTitledBorder("Physical Memory Sticks"));

        String[] names = memory.getNames();
        long[] capacity = memory.getCapacity();
        long[] speed = memory.getSpeed();
        String[] type = memory.getType();

        for (int i = 0; i < names.length; i++) {
            stickPanel.add(new JLabel("Stick " + (i + 1) + ":"));
            stickPanel.add(new JLabel("  Name: " + names[i]));
            stickPanel.add(new JLabel("  Type: " + type[i]));
            stickPanel.add(new JLabel("  Capacity: " + capacity[i] + " GB"));
            stickPanel.add(new JLabel("  Speed: " + speed[i] + " MHz"));
            stickPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 2));
        centerPanel.add(memPanelGB);
        centerPanel.add(memPanelGiB);
        centerPanel.add(swapPanel);
        centerPanel.add(stickPanel);

        add(title, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(back, BorderLayout.SOUTH);

        Thread updater = new Thread(() -> {
            while (true) {
                SwingUtilities.invokeLater(() -> {
                    usedGB.setText(String.format("Used Memory (GB): %.2f", memory.getUsedMemGB()));
                    unusedGB.setText(String.format("Available Memory (GB): %.2f", memory.getUnusedMemGB()));
                    usedGiB.setText(String.format("Used Memory (GiB): %.2f", memory.getUsedMemGiB()));
                    unusedGiB.setText(String.format("Available Memory (GiB): %.2f", memory.getUnusedMemGiB()));
                    swapUsedGB.setText(String.format("Swap Used (GB): %.2f", memory.swapMemUsedGB()));
                    swapUsedGiB.setText(String.format("Swap Used (GiB): %.2f", memory.swapMemUsedGiB()));
                    swapPagesInKB.setText(String.format("Swap Pages In (KB): %.2f", memory.swapMemPagesInKB()));
                    swapPagesInKiB.setText(String.format("Swap Pages In (KiB): %.2f", memory.swapMemPagesInKiB()));
                    swapPagesOutKB.setText(String.format("Swap Pages Out (KB): %.2f", memory.swapPagesOutKB()));
                    swapPagesOutKiB.setText(String.format("Swap Pages Out (KiB): %.2f", memory.swapPagesOutKiB()));
                });

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        updater.setDaemon(true);
        updater.start();
    }
}

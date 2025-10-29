package codenameburkley.pages;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import codenameburkley.MainFrame;
import codenameburkley.DiskData;
import codenameburkley.DiskInfoService;

public class DiskPage extends JPanel {
    private final DiskInfoService diskService = new DiskInfoService();

    public DiskPage(MainFrame frame) {
        setLayout(new BorderLayout()); // main page layout

        JLabel title = new JLabel("Disk Info", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 32));

        JButton back = new JButton("Back");
        back.addActionListener(e -> frame.showPage("Home"));

        // ==== Disk Panel (container for all disks) ====
        JPanel diskPanel = new JPanel();
        diskPanel.setLayout(new BoxLayout(diskPanel, BoxLayout.X_AXIS)); // horizontal flow

        List<DiskData> disks = diskService.getDisks();

        for (int i = 0; i < disks.size(); i++) {
            DiskData d = disks.get(i);

            JPanel singleDiskPanel = new JPanel();
            singleDiskPanel.setLayout(new BoxLayout(singleDiskPanel, BoxLayout.Y_AXIS));
            singleDiskPanel.setBorder(BorderFactory.createTitledBorder("Disk " + (i + 1)));

            singleDiskPanel.add(new JLabel("Model: " + d.model));
            singleDiskPanel.add(new JLabel("Serial: " + d.serial));
            singleDiskPanel.add(new JLabel("Size: " + d.sizeBytes + " GB"));
            singleDiskPanel.add(new JLabel("Read Bytes: " + d.readBytes + " GB"));
            singleDiskPanel.add(new JLabel("Write Bytes: " + d.writeBytes + " GB"));
            singleDiskPanel.add(new JLabel("Read Count: " + d.readCount));
            singleDiskPanel.add(new JLabel("Write Count: " + d.writeCount));
            singleDiskPanel.add(new JLabel("Transfer Time: " + d.transferTimeMs + " ms"));

            diskPanel.add(singleDiskPanel);
            diskPanel.add(Box.createRigidArea(new Dimension(15, 0))); // spacing between disks
        }

        // ==== Add Everything to Main Page ====
        add(title, BorderLayout.NORTH);
        add(new JScrollPane(diskPanel), BorderLayout.CENTER); // scroll if too many disks
        add(back, BorderLayout.SOUTH);
    }
}

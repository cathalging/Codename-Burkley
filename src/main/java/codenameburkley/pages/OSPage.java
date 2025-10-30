package codenameburkley.pages;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import codenameburkley.MainFrame;
import codenameburkley.OS;
import oshi.software.os.OSProcess;
import codenameburkley.CPU;

public class OSPage extends JPanel {
    private final OS os = new OS();
    private final CPU cpu = new CPU();

    public OSPage(MainFrame frame) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Operating System Info", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 32));

        JButton back = new JButton("Back");
        back.addActionListener(e -> frame.showPage("Home"));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createTitledBorder("System Info"));
        infoPanel.add(new JLabel("Family: " + os.getFamily()));
        infoPanel.add(new JLabel("Bitness: " + os.getBitness() + "-bit"));
        infoPanel.add(new JLabel("Thread Count: " + os.getThreadCount()));
        infoPanel.add(new JLabel("Current Thread: " + os.getCurrentThreadName()));

        os.setProcesses();
        List<OSProcess> processes = os.getProcesses();

        JPanel processPanel = new JPanel();
        processPanel.setLayout(new BoxLayout(processPanel, BoxLayout.X_AXIS));
        processPanel.setBorder(BorderFactory.createTitledBorder("Processes"));

        JLabel[] usageLabels = new JLabel[processes.size()];

        for (int i = 0; i < processes.size(); i++) {
            OSProcess proc = processes.get(i);
            JPanel procPanel = new JPanel();
            procPanel.setLayout(new BoxLayout(procPanel, BoxLayout.Y_AXIS));
            procPanel.setBorder(BorderFactory.createTitledBorder(proc.getName()));

            procPanel.add(new JLabel("Name: " + proc.getName()));
            JLabel usageLabel = new JLabel("CPU Usage: 0.00%");
            procPanel.add(usageLabel);
            usageLabels[i] = usageLabel;

            processPanel.add(procPanel);
            processPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        }

        JScrollPane scrollPane = new JScrollPane(processPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(infoPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(title, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(back, BorderLayout.SOUTH);

        Thread updater = new Thread(() -> {
            while (true) {
                List<OSProcess> oldList = os.getProcesses();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
                os.setProcesses();
                List<OSProcess> newList = os.getProcesses();

                SwingUtilities.invokeLater(() -> {
                    for (int i = 0; i < Math.min(newList.size(), usageLabels.length); i++) {
                        double usage = newList.get(i).getProcessCpuLoadBetweenTicks(oldList.get(i));
                        usage = (usage / cpu.getCoreCount()) * 100;
                        usageLabels[i].setText(String.format("CPU Usage: %.2f%%", usage));
                    }
                });
            }
        });
        updater.setDaemon(true);
        updater.start();
    }
}

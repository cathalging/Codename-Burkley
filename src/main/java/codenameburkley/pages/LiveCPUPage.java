package codenameburkley.pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import codenameburkley.MainFrame;
import codenameburkley.CPU;

public class LiveCPUPage extends JPanel {
    private CPU cpu;
    private JLabel load;
    private JLabel headerLabel;
    private JLabel freq;
    JLabel title;
    private JButton back;
    boolean running;
    int coreCount;
    String header;

    public LiveCPUPage(MainFrame frame) {
        cpu = new CPU();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        title = new JLabel("Live CPU Info");
        title.setFont(new Font("Serif", Font.BOLD, 32));
        back = new JButton("Back");
        running = true;
        load = new JLabel("Starting...");
        freq = new JLabel("Staring...");
        coreCount = cpu.getThreadCount();
        header = "";
        for (int i = 0; i < coreCount; i++) {
            if (i+1 < 10) header += String.format("CPU %d     ", i+1);
            else header += String.format("CPU %d    ", i+1);
        }
        header += "AVG";
        headerLabel = new JLabel(header);
        back.addActionListener(e -> stop());
        back.addActionListener(e -> frame.showPage("Home"));

        add(title);
        add(load);
        add(headerLabel);
        add(freq);
        add(back);
        start();
    }

    public void showInfo() {
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                freq.setText(cpu.getCurrFreq());
                load.setText(cpu.getCurrLoad());
            }
        });

        timer.start();
    }

    public void start() {
        cpu.startFreqThread();
        cpu.startLoadThread();
        showInfo();
    }

    public void stop() {
        running = false;
        cpu.endFreqThread();
        cpu.endLoadThread();
    }
}
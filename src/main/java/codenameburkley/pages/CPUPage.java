package codenameburkley.pages;

import javax.swing.*;
import java.awt.*;
import codenameburkley.MainFrame;
import codenameburkley.CPU;

public class CPUPage extends JPanel {
    private CPU cpu = new CPU();

    public CPUPage(MainFrame frame) {
        setLayout(new GridLayout(8, 1));

        JLabel title = new JLabel("CPU Info");
        title.setFont(new Font("Serif", Font.BOLD, 32));

        JLabel name = new JLabel("Name: " + cpu.getName());
        JLabel vendor = new JLabel("Vendor: " + cpu.getVendor());
        JLabel microarchitecture = new JLabel("Microarchitecture: " + cpu.getMicroArc());
        JLabel coreCount = new JLabel("Core Count: " + cpu.getCoreCount());
        JLabel threadCount = new JLabel("Thread Count: " + cpu.getThreadCount());
        JLabel maxFreq = new JLabel("Max Frequency: " + cpu.getMaxFreq() +"GHz");

        JButton back = new JButton("Back");
        back.addActionListener(e -> frame.showPage("Home"));


        add(title);

        add(name);
        add(vendor);
        add(microarchitecture);
        add(coreCount);
        add(threadCount);
        add(maxFreq);

        add(back);
    }
}

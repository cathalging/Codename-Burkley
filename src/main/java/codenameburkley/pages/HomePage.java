package codenameburkley.pages;

import javax.swing.*;
import java.awt.*;
import codenameburkley.MainFrame;

public class HomePage extends JPanel {
    public HomePage(MainFrame frame) {
        setLayout(new GridLayout(2, 1, 10, 10));

        JButton cpuPage = new JButton("CPU");
        JButton memPage = new JButton("Memory");

        cpuPage.addActionListener(e -> frame.showPage("CPUPage"));
        memPage.addActionListener(e -> frame.showPage("MemoryPage"));

        add(cpuPage);
        add(memPage);

    }
}

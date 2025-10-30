package codenameburkley.pages;

import javax.swing.*;
import java.awt.*;
import codenameburkley.MainFrame;
import codenameburkley.NetworkCard;

public class NetworkPage extends JPanel {
    private final NetworkCard networkCard = new NetworkCard();

    public NetworkPage(MainFrame frame) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Network Info", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 32));

        JButton back = new JButton("Back");
        back.addActionListener(e -> frame.showPage("Home"));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));

        String[] names = networkCard.getNames();
        String[] displayNames = networkCard.getDisplayNames();
        String[] macs = networkCard.getMacaddrs();
        String[][] ipv4s = networkCard.getIPv4addrs();
        String[][] ipv6s = networkCard.getIPv6addr();
        long[] speeds = networkCard.getSpeeds();

        JLabel[] speedLabels = new JLabel[names.length];

        for (int i = 0; i < names.length; i++) {
            JPanel singlePanel = new JPanel();
            singlePanel.setLayout(new BoxLayout(singlePanel, BoxLayout.Y_AXIS));
            singlePanel.setBorder(BorderFactory.createTitledBorder("NIC " + (i + 1)));

            singlePanel.add(new JLabel("Name: " + names[i]));
            singlePanel.add(new JLabel("Display Name: " + displayNames[i]));
            singlePanel.add(new JLabel("MAC Address: " + macs[i]));

            StringBuilder ipv4Str = new StringBuilder("IPv4: ");
            for (String addr : ipv4s[i]) ipv4Str.append(addr).append(" ");
            singlePanel.add(new JLabel(ipv4Str.toString()));

            StringBuilder ipv6Str = new StringBuilder("IPv6: ");
            for (String addr : ipv6s[i]) ipv6Str.append(addr).append(" ");
            singlePanel.add(new JLabel(ipv6Str.toString()));

            JLabel speedLabel = new JLabel("Speed: " + speeds[i] + " bps");
            singlePanel.add(speedLabel);
            speedLabels[i] = speedLabel;

            centerPanel.add(singlePanel);
            centerPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        }

        add(title, BorderLayout.NORTH);
        add(new JScrollPane(centerPanel), BorderLayout.CENTER);
        add(back, BorderLayout.SOUTH);

        Thread updater = new Thread(() -> {
            while (true) {
                networkCard.refreshNetworkList(true);
                long[] updatedSpeeds = networkCard.getSpeeds();
                SwingUtilities.invokeLater(() -> {
                    for (int i = 0; i < updatedSpeeds.length && i < speedLabels.length; i++) {
                        speedLabels[i].setText("Speed: " + updatedSpeeds[i] + " MBps");
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        updater.setDaemon(true);
        updater.start();
    }
}

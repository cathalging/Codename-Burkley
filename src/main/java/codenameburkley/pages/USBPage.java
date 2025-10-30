package codenameburkley.pages;

import codenameburkley.MainFrame;
import codenameburkley.Usb;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class USBPage extends JPanel {
    private MainFrame mainFrame;
    private Usb usb;

    private JTable usbTable;
    private DefaultTableModel tableModel;
    private JButton refreshButton;
    private JButton homeButton;

    public USBPage(MainFrame frame) {
        this.mainFrame = frame;
        this.usb = new Usb();

        setLayout(new BorderLayout());

        String[] columnNames = {"Name", "Product ID", "Unique Device ID", "Vendor"};
        tableModel = new DefaultTableModel(columnNames, 0);
        usbTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(usbTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        refreshButton = new JButton("Refresh");
        homeButton = new JButton("Home");

        buttonPanel.add(refreshButton);
        buttonPanel.add(homeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        loadUsbData();

        refreshButton.addActionListener(e -> loadUsbData());
        homeButton.addActionListener(e -> mainFrame.showPage("Home"));
    }

    private void loadUsbData() {
        usb.refreshUsbList();
        tableModel.setRowCount(0);

        String[] names = usb.getNames();
        String[] productIds = usb.getProductIds();
        String[] serialNumbers = usb.getUniqueDeviceIds();
        String[] vendors = usb.getVendors();

        for (int i = 0; i < names.length; i++) {
            tableModel.addRow(new Object[]{
                    names[i],
                    productIds[i],
                    serialNumbers[i],
                    vendors[i]
            });
        }
    }
}

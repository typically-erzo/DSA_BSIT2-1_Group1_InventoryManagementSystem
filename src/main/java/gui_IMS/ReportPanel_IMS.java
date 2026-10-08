package gui_IMS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import service_IMS.InventoryManagement_IMS;

public class ReportPanel_IMS extends JPanel implements ActionListener {

    private JLabel lblTotalItems, lblLowStock, lblTotalValue, lblThreshold;
    private JTextField txtThreshold;
    private JButton btnFullReport, btnLowStock, btnReset;
    private JTable reportTable;
    private DefaultTableModel tableModel;
    private InventoryManagement_IMS manager;

    public ReportPanel_IMS(InventoryManagement_IMS manager) {
        this.manager = manager;

        setLayout(null); 
        setSize(780, 450);

        // TOP SECTION: SUMMARY CARDS
        lblTotalItems = new JLabel("Total Items: 80");
        lblTotalItems.setBounds(20, 15, 200, 30);
        add(lblTotalItems);

        lblLowStock = new JLabel("Low Stock Alerts: 1");
        lblLowStock.setBounds(240, 15, 200, 30);
        add(lblLowStock);

        lblTotalValue = new JLabel("Total Value: $3,399.20");
        lblTotalValue.setBounds(480, 15, 250, 30);
        add(lblTotalValue);

        // LEFT SIDE: REPORT CONTROLS
        lblThreshold = new JLabel("Low Stock Threshold:");
        lblThreshold.setBounds(20, 60, 150, 25);
        add(lblThreshold);

        txtThreshold = new JTextField("10");
        txtThreshold.setBounds(20, 90, 150, 25);
        add(txtThreshold);

        btnFullReport = new JButton("Full Inventory Report");
        btnFullReport.setBounds(20, 130, 200, 30);
        add(btnFullReport);

        btnLowStock = new JButton("Low Stock Report");
        btnLowStock.setBounds(20, 175, 200, 30);
        add(btnLowStock);

        btnReset = new JButton("Reset Display");
        btnReset.setBounds(20, 220, 200, 30);
        add(btnReset);

        // RIGHT SIDE: REPORT DISPLAY TABLE
        String[] columns = {"ID", "Name", "Quantity", "Unit Price", "Total Value", "Status"};
        tableModel = new DefaultTableModel(columns, 0);

        // Sample initial table entries
        tableModel.addRow(new Object[]{"101", "Wireless Mouse", "50", "$19.99", "$999.50", "Normal"});
        tableModel.addRow(new Object[]{"102", "Mechanical KB", "5", "$79.99", "$399.95", "LOW STOCK"});

        reportTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(reportTable);
        scrollPane.setBounds(240, 60, 510, 330);
        add(scrollPane);

        // Register Action Listeners
        btnFullReport.addActionListener(this);
        btnLowStock.addActionListener(this);
        btnReset.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnFullReport) {
            handleFullReport();
        } else if (e.getSource() == btnLowStock) {
            handleLowStockReport();
        } else if (e.getSource() == btnReset) {
            handleReset();
        }
    }

    private void handleFullReport() {
        tableModel.setRowCount(0); // Clear current rows

        lblTotalItems.setText("Total Items: 80"); //Display
        lblLowStock.setText("Low Stock Alerts: 1"); //Display
        lblTotalValue.setText("Total Value: $6,399.20"); //Display

        JOptionPane.showMessageDialog(this, "Generated Full Inventory Report.", "Report Generated", JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleLowStockReport() {
        String thresholdText = txtThreshold.getText().trim();

        if (thresholdText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid threshold number.", "Input Error", JOptionPane.WARNING_MESSAGE);
        } else {
            tableModel.setRowCount(0); // Clear current rows

            // Mock filtering low-stock entries below threshold
            tableModel.addRow(new Object[]{"102", "Mechanical KB", "5", "$79.99", "$399.95", "LOW STOCK"});

            JOptionPane.showMessageDialog(this, "Filtered report for items under quantity: " + thresholdText, "Low Stock Report", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void handleReset() {
        txtThreshold.setText("10");
        handleFullReport();
    }
}

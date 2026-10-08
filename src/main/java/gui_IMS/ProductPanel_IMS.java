package gui_IMS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import service_IMS.InventoryManagement_IMS;

public class ProductPanel_IMS extends JPanel implements ActionListener {

    private JTextField txtId, txtName, txtQty, txtSupplier, txtPrice, txtSearch;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnSearch, btnReset;
    private JLabel lblId, lblName, lblQty, lblSupplier, lblPrice, lblSearch;
    private JTable productTable;
    private DefaultTableModel tableModel;
    private InventoryManagement_IMS manager;

    public ProductPanel_IMS(InventoryManagement_IMS manager) {
        this.manager = manager;

        setLayout(null); // Absolute positioning rule
        setSize(780, 450);

        // --- LEFT SIDE: INPUT FORM ---
        lblId = new JLabel("ID:");
        lblId.setBounds(20, 20, 80, 25);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(100, 20, 150, 25);
        add(txtId);

        lblName = new JLabel("Name:");
        lblName.setBounds(20, 55, 80, 25);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(100, 55, 150, 25);
        add(txtName);

        lblQty = new JLabel("Quantity:");
        lblQty.setBounds(20, 90, 80, 25);
        add(lblQty);

        txtQty = new JTextField();
        txtQty.setBounds(100, 90, 150, 25);
        add(txtQty);

        lblSupplier = new JLabel("Supplier:");
        lblSupplier.setBounds(20, 125, 80, 25);
        add(lblSupplier);

        txtSupplier = new JTextField();
        txtSupplier.setBounds(100, 125, 150, 25);
        add(txtSupplier);

        lblPrice = new JLabel("Price:");
        lblPrice.setBounds(20, 160, 80, 25);
        add(lblPrice);

        txtPrice = new JTextField();
        txtPrice.setBounds(100, 160, 150, 25);
        add(txtPrice);

        // --- FORM CONTROL BUTTONS ---
        btnAdd = new JButton("Add");
        btnAdd.setBounds(20, 200, 100, 30);
        add(btnAdd);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(130, 200, 100, 30);
        add(btnUpdate);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(20, 240, 100, 30);
        add(btnDelete);

        btnClear = new JButton("Clear");
        btnClear.setBounds(130, 240, 100, 30);
        add(btnClear);

        // --- RIGHT SIDE: SEARCH BAR & TABLE ---
        lblSearch = new JLabel("Search:");
        lblSearch.setBounds(280, 20, 60, 25);
        add(lblSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(340, 20, 180, 25);
        add(txtSearch);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(530, 20, 85, 25);
        add(btnSearch);

        btnReset = new JButton("Show All");
        btnReset.setBounds(625, 20, 90, 25);
        add(btnReset);

        // --- TABLE SETUP ---
        String[] columns = {"ID", "Name", "Quantity", "Supplier", "Price"};
        tableModel = new DefaultTableModel(columns, 0);

        productTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBounds(280, 55, 470, 340);
        add(scrollPane);

        // Add Table Selection Listener to populate form fields on row click
        productTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow != -1) {
                txtId.setText(tableModel.getValueAt(selectedRow, 0).toString());
                txtName.setText(tableModel.getValueAt(selectedRow, 1).toString());
                txtQty.setText(tableModel.getValueAt(selectedRow, 2).toString());
                txtSupplier.setText(tableModel.getValueAt(selectedRow, 3).toString());
                txtPrice.setText(tableModel.getValueAt(selectedRow, 4).toString());
            }
        });

        // Register Action Listeners
        btnAdd.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnClear.addActionListener(this);
        btnSearch.addActionListener(this);
        btnReset.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            handleAdd();
        } else if (e.getSource() == btnUpdate) {
            handleUpdate();
        } else if (e.getSource() == btnDelete) {
            handleDelete();
        } else if (e.getSource() == btnClear) {
            clearFields();
        } else if (e.getSource() == btnSearch) {
            handleSearch();
        } else if (e.getSource() == btnReset) {
            clearFields();
            JOptionPane.showMessageDialog(this, "Table filter reset.", "Show All", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void handleAdd() {
        String id = txtId.getText().trim();
        String name = txtName.getText().trim();
        String qty = txtQty.getText().trim();
        String supplier = txtSupplier.getText().trim();
        String price = txtPrice.getText().trim();

        if (id.isEmpty() || name.isEmpty() || qty.isEmpty() || supplier.isEmpty() || price.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all input fields.", "Validation Error", JOptionPane.WARNING_MESSAGE);
        } else {
            tableModel.addRow(new Object[]{id, name, qty, supplier, price});
            JOptionPane.showMessageDialog(this, "Product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        }
    }

    private void handleUpdate() {
        int selectedRow = productTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product row from the table to update.", "Selection Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            tableModel.setValueAt(txtId.getText().trim(), selectedRow, 0);
            tableModel.setValueAt(txtName.getText().trim(), selectedRow, 1);
            tableModel.setValueAt(txtQty.getText().trim(), selectedRow, 2);
            tableModel.setValueAt(txtSupplier.getText().trim(), selectedRow, 3);
            tableModel.setValueAt(txtPrice.getText().trim(), selectedRow, 4);

            JOptionPane.showMessageDialog(this, "Product updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        }
    }

    private void handleDelete() {
        int selectedRow = productTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product row from the table to delete.", "Selection Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            tableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Product deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        }
    }

    private void handleSearch() {
        String query = txtSearch.getText().trim().toLowerCase();

        if (query.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an ID or Name to search.", "Search Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            boolean found = false;
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String id = tableModel.getValueAt(i, 0).toString().toLowerCase();
                String name = tableModel.getValueAt(i, 1).toString().toLowerCase();

                if (id.equals(query) || name.contains(query)) {
                    productTable.setRowSelectionInterval(i, i);
                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "No matching product found in the table.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtQty.setText("");
        txtSupplier.setText("");
        txtPrice.setText("");
        txtSearch.setText("");
        productTable.clearSelection();
    }
}



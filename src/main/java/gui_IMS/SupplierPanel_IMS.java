/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_IMS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SupplierPanel_IMS extends JPanel implements ActionListener {

    private JTextField txtId, txtName, txtContact, txtEmail, txtSearch;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnSearch, btnReset;
    private JLabel lblId, lblName, lblContact, lblEmail, lblSearch;
    private JTable supplierTable;
    private DefaultTableModel tableModel;

    public SupplierPanel_IMS() {
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

        lblContact = new JLabel("Contact:");
        lblContact.setBounds(20, 90, 80, 25);
        add(lblContact);

        txtContact = new JTextField();
        txtContact.setBounds(100, 90, 150, 25);
        add(txtContact);

        lblEmail = new JLabel("Email:");
        lblEmail.setBounds(20, 125, 80, 25);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(100, 125, 150, 25);
        add(txtEmail);

        // --- FORM CONTROL BUTTONS ---
        btnAdd = new JButton("Add");
        btnAdd.setBounds(20, 170, 100, 30);
        add(btnAdd);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(130, 170, 100, 30);
        add(btnUpdate);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(20, 210, 100, 30);
        add(btnDelete);

        btnClear = new JButton("Clear");
        btnClear.setBounds(130, 210, 100, 30);
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
        String[] columns = {"ID", "Name", "Contact", "Email"};
        tableModel = new DefaultTableModel(columns, 0);

        // Sample static supplier entries
        tableModel.addRow(new Object[]{"SUP-01", "TechCorp Solutions", "+1-800-555-0199", "sales@techcorp.com"});
        tableModel.addRow(new Object[]{"SUP-02", "KeyGear Peripherals", "+1-800-555-0244", "info@keygear.com"});

        supplierTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(supplierTable);
        scrollPane.setBounds(280, 55, 470, 340);
        add(scrollPane);

        // Populate fields on table row click
        supplierTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = supplierTable.getSelectedRow();
            if (selectedRow != -1) {
                txtId.setText(tableModel.getValueAt(selectedRow, 0).toString());
                txtName.setText(tableModel.getValueAt(selectedRow, 1).toString());
                txtContact.setText(tableModel.getValueAt(selectedRow, 2).toString());
                txtEmail.setText(tableModel.getValueAt(selectedRow, 3).toString());
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
        String contact = txtContact.getText().trim();
        String email = txtEmail.getText().trim();

        if (id.isEmpty() || name.isEmpty() || contact.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all supplier fields.", "Validation Error", JOptionPane.WARNING_MESSAGE);
        } else if (!email.contains("@")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address containing '@'.", "Validation Error", JOptionPane.WARNING_MESSAGE);
        } 
        else {
            tableModel.addRow(new Object[]{id, name, contact, email});
            JOptionPane.showMessageDialog(this, "Supplier added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        }
    }

    private void handleUpdate() {
        int selectedRow = supplierTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a supplier row from the table to update.", "Selection Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            tableModel.setValueAt(txtId.getText().trim(), selectedRow, 0);
            tableModel.setValueAt(txtName.getText().trim(), selectedRow, 1);
            tableModel.setValueAt(txtContact.getText().trim(), selectedRow, 2);
            tableModel.setValueAt(txtEmail.getText().trim(), selectedRow, 3);

            JOptionPane.showMessageDialog(this, "Supplier updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        }
    }

    private void handleDelete() {
        int selectedRow = supplierTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a supplier row from the table to delete.", "Selection Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            tableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Supplier deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
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
                    supplierTable.setRowSelectionInterval(i, i);
                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "No matching supplier found.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtContact.setText("");
        txtEmail.setText("");
        txtSearch.setText("");
        supplierTable.clearSelection();
    }
}

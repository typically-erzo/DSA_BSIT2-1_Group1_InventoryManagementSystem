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

        setLayout(null);
        setSize(780, 450);

        // LEFT SIDE: INPUT FORM
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

        // FORM CONTROL BUTTONS
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

        // RIGHT SIDE: SEARCH BAR & TABLE 
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

        // TABLE SETUP
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}


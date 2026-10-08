
package gui_IMS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import service_IMS.InventoryManagement_IMS;

public class MainFrame_IMS extends JFrame implements ActionListener {

    // Labels
    private JButton btnProduct,btnReport,btnSupplier, btnLogout;
    
    
    private InventoryManagement_IMS manager;
    
    //Panels
    private ProductPanel_IMS productpanel;
    private ReportPanel_IMS reportpanel;
    private SupplierPanel_IMS supplierpanel;


    public MainFrame_IMS() {
        manager = new InventoryManagement_IMS();
        setTitle("Inventory Management System");
        setSize(900, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ADD BUTTON
        btnProduct = new JButton("Product");
        btnProduct.setBounds(20, 10, 120, 30);
        btnProduct.addActionListener(this);
        add(btnProduct);

        // UPDATE BUTTON
        btnSupplier = new JButton("Supplier");
        btnSupplier.setBounds(145, 10, 100, 30);
        btnSupplier.addActionListener(this);
        add(btnSupplier);

        // DELETE BUTTON
        btnReport = new JButton("Report");
        btnReport.setBounds(250, 10, 100, 30);
        btnReport.addActionListener(this);
        add(btnReport);

        // DISPLAY BUTTON
        btnLogout = new JButton("Logout");
        btnLogout.setBounds(750, 10, 120, 30);
        btnLogout.addActionListener(this);
        add(btnLogout);
        
       productpanel = new ProductPanel_IMS(manager);
       productpanel.setBounds (20, 50, 780, 450);
        productpanel.setVisible(true);
        add(productpanel);
        
        supplierpanel = new SupplierPanel_IMS();
        supplierpanel.setBounds(20,50,780, 450); 
        supplierpanel.setVisible(false);
        add (supplierpanel);
        
        reportpanel = new ReportPanel_IMS(manager);
        reportpanel.setBounds(20,50,780,450);
        reportpanel.setVisible (false);
        add (reportpanel);
        btnProduct.addActionListener(this);
        btnSupplier.addActionListener(this);
        btnReport.addActionListener(this);
        btnLogout.addActionListener(this);

        // Make Frame Visible
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnProduct) {
            productpanel.setVisible(true);
            supplierpanel.setVisible(false);
            reportpanel.setVisible(false);

        } else if (e.getSource() == btnSupplier) {
            productpanel.setVisible(false);
            supplierpanel.setVisible(true);
            reportpanel.setVisible(false);

        } else if (e.getSource() == btnReport) {
            productpanel.setVisible(false);
            supplierpanel.setVisible(false);
            reportpanel.setVisible(true);

        } else if (e.getSource() == btnLogout) {
            // Return to LoginFrame and close MainFrame
            LoginFrame_IMS login = new LoginFrame_IMS();
            login.setVisible(true);
            dispose();
        }
    }
}


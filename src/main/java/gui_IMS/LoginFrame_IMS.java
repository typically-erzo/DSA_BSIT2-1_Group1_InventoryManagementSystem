package gui_IMS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginFrame_IMS extends JFrame implements ActionListener {

    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin;

    public LoginFrame_IMS() {
        setTitle("IMS Login");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); 
        setResizable(false);
        setLocationRelativeTo(null);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setBounds(40, 30, 80, 25);
        add(lblUser);

        txtUser = new JTextField();
        txtUser.setBounds(130, 30, 140, 25);
        add(txtUser);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(40, 70, 80, 25);
        add(lblPass);

        txtPass = new JPasswordField();
        txtPass.setBounds(130, 70, 140, 25);
        add(txtPass);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(110, 120, 100, 30);
        add(btnLogin);

        btnLogin.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            String user = txtUser.getText();
            String pass = new String(txtPass.getPassword());

            if (user.equals("admin") && pass.equals("admin123")) {
                //MainFrame_IMS mainFrame = new MainFrame_IMS();
                //mainFrame.setVisible(true);
                dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
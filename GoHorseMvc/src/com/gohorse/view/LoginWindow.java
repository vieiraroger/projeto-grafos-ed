package com.gohorse.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.gohorse.database.model.Users;
import com.gohorse.lib.FileManipulation;

public class LoginWindow extends JFrame {
    
    private JTextField txfUser;
    private JPasswordField txfPassword;
    private JButton btnConfirm;
    private JButton btnSair;
    private JLabel lbUser;
    private JLabel lbPassword;

    public LoginWindow() {

        setSize(210,200);
        setTitle("Sistemão bom da porra");
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        createLoginComponents();
        
    }
    
    public void createLoginComponents() {
    	
    	lbUser = new JLabel();
    	lbUser.setBounds(40, 20, 125, 20);
    	lbUser.setText("Usuário");
    	getContentPane().add(lbUser);
    	
    	txfUser = new JTextField();
    	txfUser.setBounds(40, 40, 125, 20);
    	getContentPane().add(txfUser);
    	
    	lbPassword = new JLabel();
    	lbPassword.setBounds(40, 60, 125, 20);
    	lbPassword.setText("Senha");
    	getContentPane().add(lbPassword);
    	
    	txfPassword = new JPasswordField();
    	txfPassword.setBounds(40, 80, 125, 20);
    	getContentPane().add(txfPassword);
    	
    	btnConfirm = new JButton(new AbstractAction("Login") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					Users user = (Users) FileManipulation.select("Users.txt", txfUser.getText());
					
					if (user.getPassword().equals(String.copyValueOf(txfPassword.getPassword()))) {
						new LoginWindow().dispose();
						
						MainWindow mw = new MainWindow();
						mw.setVisible(true);
						
					}
					
					
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				
				
			}
		});
    	btnConfirm.setBounds(40, 115, 124, 20);    	
    	getContentPane().add(btnConfirm);
    	
    	
    }
    
    
 
    public static void main(String[] args) {
        
    	new LoginWindow().setVisible(true);
    	
    }

}

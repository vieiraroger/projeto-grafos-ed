package com.gohorse.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

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
import com.gohorse.database.service.UsersService;
import com.gohorse.lib.FileManipulation;

public class LoginWindow extends JFrame {
    
    private JTextField txfUser;
    private JPasswordField txfPassword;
    private JButton btnConfirm;

    private JLabel lbUser;
    private JLabel lbPassword;

    public LoginWindow() {
    	
        setSize(210,200);
        setTitle("Sistema");
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        createLoginComponents();
        
    }
    
    
    public void createLoginComponents() {
    	
    	lbUser = new JLabel();
    	lbUser.setBounds(40, 20, 125, 20);
    	lbUser.setText("Usu�rio");
    	getContentPane().add(lbUser);
    	
    	txfUser = new JTextField();
    	txfUser.setBounds(40, 40, 125, 20);
    	getContentPane().add(txfUser);
    	
        txfUser.addKeyListener(new KeyAdapter() {
    		
    		public void keyPressed(KeyEvent e) {
    			
    			if (e.getKeyCode() == e.VK_ENTER) { 
    				
    				btnConfirm.doClick(); 
			    }
			}
    	});
    	
    	lbPassword = new JLabel();
    	lbPassword.setBounds(40, 60, 125, 20);
    	lbPassword.setText("Senha");
    	getContentPane().add(lbPassword);
    	
    	txfPassword = new JPasswordField();
    	txfPassword.setBounds(40, 80, 125, 20);
    	getContentPane().add(txfPassword);
    	
    	txfPassword.addKeyListener(new KeyAdapter() {
    		
    		public void keyPressed(KeyEvent e) {
    			
    			if (e.getKeyCode() == e.VK_ENTER) { 
    				
    				btnConfirm.doClick(); 
			    }
			}
    	});
    	
    	btnConfirm = new JButton(new AbstractAction("Login") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					UsersService us = new UsersService();
					Collection<Users> list = us.findAll();
					
					if(list == null) {						
						us.save(new Users("admin","admin","Administrador"));
						throw new Exception("Nenhum usu�rio encontrado! Usu�rio padr�o cadastrado!");
					}
					
					
					boolean entrou = true;
					for (Users user : list) {
						if (user.getPassword().equals(String.copyValueOf(txfPassword.getPassword()))) {
							
							MainWindowNew mw = new MainWindowNew(user.getPerfil());
							mw.setVisible(true);
							entrou = false;
							LoginWindow.this.dispose();
							
						}
					}
					
					 if(entrou) {
						
						throw new Exception("Usu�rio ou senha inv�lidos!");
						
					}
								
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null,ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);					
				}		
			}
		});
    	
    	btnConfirm.setBounds(40, 115, 124, 20);    	
    	btnConfirm.setFocusPainted(false);
    	btnConfirm.setContentAreaFilled(false);
    	getContentPane().add(btnConfirm);
    	  	
    }
    
    public static void main(String[] args) {
        
    	/*Users u = new Users("admin","admin","admin" );
    	MainWindow mw = new MainWindow(u);
    	mw.setVisible(true);*/
    	new LoginWindow().setVisible(true);
    	
    }

}

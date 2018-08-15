package com.gohorse.view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LoginWindow extends JDialog {
    
    private JTextField txfUser;
    private JTextField txfSenha;
    private JButton btnConfirm;
    private JButton btnSair;


    public vLogin() {

        setSize(500,500);
        setTitle("Titulo da Janela");
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        

    }
    
    public static void main(String[] args) {
        
        new vLogin().setVisible(true);
        
        v

    }

}

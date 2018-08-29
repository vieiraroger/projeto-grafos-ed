package com.gohorse.view;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainWindow extends JFrame {
		
	private JMenuBar menu ;
	private JMenu mAlunos;
	private JMenu mCidades;
	
	public MainWindow () {
		setSize(500,500);
		setTitle("Menu");
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setJMenuBar(menu);
        CreateMenucomponents();
	}
	public void  CreateMenucomponents() {
		 mAlunos = new JMenu("Cadastros");
		 mCidades = new JMenu("Sair");
		 
		 menu.add(mAlunos);
		 menu.add(mCidades);
	}
	public static void main(String[] args) {
		new MainWindow().setVisible(true);
	}
}

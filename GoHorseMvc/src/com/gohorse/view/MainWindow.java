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
        CreateMenucomponents();
	}
	public void  CreateMenucomponents() {
		 menu = new  JMenuBar();
		 setJMenuBar(menu);
		 mAlunos = new JMenu("Alunos");
		 mCidades = new JMenu("Cidades");
		 
		 menu.add(mAlunos);
		 menu.add(mCidades);
	}
	public static void main(String[] args) {
		new MainWindow().setVisible(true);
	}
}

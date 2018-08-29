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
	private JMenuItem smCadastroAluno;
	private JMenuItem smCadastroCidade;
	private JMenuItem smListarAluno;
	private JMenuItem smListarCidade;
	
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
		 smCadastroAluno = new JMenuItem("Cadastrar");
		 smCadastroCidade = new JMenuItem("Cadastrar");
		 smListarAluno = new JMenuItem("Listar");
		 smListarCidade = new JMenuItem("Listar");
		 
		 menu.add(mAlunos);
		 menu.add(mCidades);
		 
		 mAlunos.add(smCadastroAluno);
		 mAlunos.add(smListarAluno);
		 
		 mCidades.add(smCadastroCidade);
		 mCidades.add(smListarCidade);
	}
	public static void main(String[] args) {
		new MainWindow().setVisible(true);
	}
}

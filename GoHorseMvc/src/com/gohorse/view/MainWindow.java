package com.gohorse.view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.gohorse.database.model.Users;

public class MainWindow extends JFrame {
	
	
	private JMenuBar menu ;
	private JMenu mAlunos;
	private JMenu mCidades;
	private JMenuItem smListarCidade;
	private JMenuItem smListarUsuario;
	private JMenuItem smListarAluno;
	
	// buttons for database manipulation
	private JButton dCadastroCidade;
	private JButton dCadastroUsuario;
	private JButton dCadastroAluno;
	private JButton dEditarCidade;
	private JButton dEditarUsuario;
	private JButton dEditarAluno;
	private JButton dExcluirCidade;
	private JButton dExcluirUsuario;
	private JButton dExcluirAluno;
	
	public MainWindow (Users user) {
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
		 
		 mAlunos 			= new JMenu("Alunos");
		 mCidades			= new JMenu("Cidades");
		 mUsuarios			= new JMenu("Usuarios");
		 smListarAluno	    = new JMenuItem("Listar");
		 smListarCidade     = new JMenuItem("Listar");
		 
		 menu.add(mAlunos);
		 menu.add(mCidades);
		 
		 mAlunos.add(smCadastroAluno);
		 mAlunos.add(smListarAluno);
		 
		 mCidades.add(smCadastroCidade);
		 mCidades.add(smListarCidade);
	}
	
	public void CitiesWindow() {
		
		
		
	}
	
	
	public void StudentsWindow() {
		
		
	}
	
	public void UsersWindow() {
		
		
	}
	
	public static void main(String[] args) {
		new MainWindow().setVisible(true);
	}
}

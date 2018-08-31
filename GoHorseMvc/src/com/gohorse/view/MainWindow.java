package com.gohorse.view;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.gohorse.database.model.Users;
import com.gohorse.lib.FileManipulation;

public class MainWindow extends JFrame {
	
 
	private JMenuBar menu ;
	private JMenu mAlunos;
	private JMenu mCidades;
	private JMenu mUsuarios;
	private JMenuItem smListarCidade;
	private JMenuItem smListarAluno;
	private JMenuItem smListarUsuario;
	private JButton btnCadastroCidade;
	private JButton btnCadastroAluno;
	
	public MainWindow (Users user) {
		setSize(500,500);
		setTitle("Menu");
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        CreateMenucomponents();
	}
	
	public void CreateMenucomponents() {
		 menu = new  JMenuBar();
		 
		 setJMenuBar(menu);
		 
		 mAlunos 			= new JMenu("Alunos");
		 mCidades			= new JMenu("Cidades");
		 mUsuarios			= new JMenu("Usuarios");
		 smListarAluno	    = new JMenuItem("Listar");
		 smListarCidade     = new JMenuItem("Listar");
		 smListarUsuario    = new JMenuItem("Listar");
		 
		 menu.add(mAlunos);
		 menu.add(mCidades);
		 
		 mAlunos.add(smListarAluno);
		 mCidades.add(smListarCidade);
		 
	}
	
	public void CitiesWindow() {
		
		
		//GRID DE CIDADES
		//CADASTRO CIDADES
		btnCadastroCidade = new JButton(new AbstractAction("Cadastrar Cidade") {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		btnCadastroCidade.setBounds(40, 150, 124, 20);    	
    	getContentPane().add(btnCadastroCidade);
		
		
	}

	public void StudentsWindow() {
		
		//GRID DE ALUNOS
		
		// CADASTRO ALUNOS
		btnCadastroAluno = new JButton(new AbstractAction("Cadastrar Aluno") {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		btnCadastroAluno.setBounds(40, 115, 124, 20);    	
    	getContentPane().add(btnCadastroAluno);
		
	}

	public void UsersWindow() {
		
		// GRID DE USUARIOS
		// CADASTRO USUARIOS
		btnCadastroCidade = new JButton(new AbstractAction("Cadastrar Usuário") {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		btnCadastroCidade.setBounds(40, 115, 124, 20);    	
    	getContentPane().add(btnCadastroCidade);
		
	}

	public static void main(String[] args) {
		
		new MainWindow(null).setVisible(true);
		
	}

}
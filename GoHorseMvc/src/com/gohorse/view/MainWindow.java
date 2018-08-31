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
import javax.swing.JTable;

import com.gohorse.database.model.Cities;
import com.gohorse.database.model.Users;
import com.gohorse.lib.FileManipulation;

public class MainWindow extends JFrame {
	
	private JTable Grid;

	private JMenuBar menu ;
	private JMenu mAlunos;
	private JMenu mCidades;
	private JMenu mUsuarios;
	private JMenuItem smListarCidade;
	private JMenuItem smListarAluno;
	private JMenuItem smListarUsuario;
	
	private JButton btnCadastroCidade;
	private JButton btnCadastroAluno;
	private JButton btnCadastroUsuario;
	private JButton btnEditarCidade;
	private JButton btnEditarAluno;
	private JButton btnEditarUsuario;
	private JButton btnDeletarCidade;
	private JButton btnDeletarAluno;
	private JButton btnDeletarUsuario;
	
	
	public MainWindow (Users user) {
		setSize(600,500);
		setTitle("Menu");
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
		 
		 menu.add(mAlunos);
		 menu.add(mCidades);
		 menu.add(mUsuarios);
		 
		 //db screens acesses
		 smListarAluno = new JMenuItem(new AbstractAction("Listar") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				StudentsWindow();
				
			}
			
		 });
		 
		 smListarCidade= new JMenuItem(new AbstractAction("Listar") {
				
			@Override
			public void actionPerformed(ActionEvent e) {

				
				CitiesWindow();
				
			}
			
		 });
		 
		 smListarUsuario= new JMenuItem(new AbstractAction("Listar") {
				
			@Override
			public void actionPerformed(ActionEvent e) {

				UsersWindow();
				
			}
		 });
		 
		 
		 mAlunos.add(smListarAluno);
		 mCidades.add(smListarCidade);
		 mUsuarios.add(smListarUsuario);
		 
	}
	
	public void CreateCitiesComponents() {
		
		//cities add
		btnCadastroCidade = new JButton(new AbstractAction("Cadastrar Cidade") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("futuramente um cadastro");
				
			}
			
		});
		btnCadastroCidade.setBounds(30, 20, 140, 30);    	
		getContentPane().add(btnCadastroCidade);
		
		//cities edit
    	btnEditarCidade = new JButton(new AbstractAction("Editar Cidade") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("futuramente uma edição");
				
			}
			
		});
    	btnEditarCidade.setBounds(230, 20, 140, 30);    	
    	getContentPane().add(btnEditarCidade);
    	
		//cities delete
    	btnDeletarCidade = new JButton(new AbstractAction("Deletar Cidade") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("futuramente uma exclusão");
				
			}
			
		});
    	btnDeletarCidade.setBounds(430, 20, 140, 30);    	
    	getContentPane().add(btnDeletarCidade);
		
	}

	public void CitiesWindow() {
		
		//cities add
		btnCadastroCidade = new JButton(new AbstractAction("Cadastrar Cidade") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("futuramente um cadastro");
				
			}
			
		});
		btnCadastroCidade.setBounds(30, 20, 140, 30);    	
		getContentPane().add(btnCadastroCidade);
		
		
		//cities edit
    	btnEditarCidade = new JButton(new AbstractAction("Editar Cidade") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("futuramente uma edição");
				
			}
			
		});
    	btnEditarCidade.setBounds(230, 20, 140, 30);    	
    	getContentPane().add(btnEditarCidade);
    	
    	
		//cities delete
    	btnDeletarCidade = new JButton(new AbstractAction("Deletar Cidade") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("futuramente uma exclusão");
				
			}
			
		});
    	btnDeletarCidade.setBounds(430, 20, 140, 30);    	
    	getContentPane().add(btnDeletarCidade);
    	
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
		btnCadastroUsuario = new JButton(new AbstractAction("Cadastrar Usuário") {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		btnCadastroUsuario.setBounds(40, 115, 124, 20);    	
    	getContentPane().add(btnCadastroUsuario);
		
	}

	public static void main(String[] args) {
		
		new MainWindow(null).setVisible(true);
		
	}

}
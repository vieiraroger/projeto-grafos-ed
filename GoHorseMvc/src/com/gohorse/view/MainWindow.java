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
	
	private JTable grdCidade;
	private JTable grdAluno;
	private JTable grdUsuario;
	
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
		setSize(600,700);
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
		 
		 menu.add(mAlunos);
		 menu.add(mCidades);
		 menu.add(mUsuarios);
	
		 
		 mAlunos.add(smListarAluno);
		 mCidades.add(smListarCidade);
		 mUsuarios.add(smListarUsuario);
		 
	}
	
	public void CitiesWindow() {
		
		//GRID DE CIDADES
		
		String[] columnCidades = {"Cidade",
                "Estado",
                "País"};
		
		grdCidade = new JTable(FileManipulation.selectAll(), columnCidades);
		
		//CADASTRO CIDADES
		btnCadastroCidade = new JButton(new AbstractAction("Cadastrar Cidade") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("futuramente um cadastro");
				
			}
			
		});
		btnCadastroCidade.setBounds(30, 140, 124, 20);    	
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
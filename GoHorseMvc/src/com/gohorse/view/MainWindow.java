package com.gohorse.view;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.gohorse.database.model.Cities;
import com.gohorse.database.model.Students;
import com.gohorse.database.model.Users;
import com.gohorse.lib.FileManipulation;

public class MainWindow extends JFrame {
	
	private JScrollPane scroll;
	private DefaultTableModel modelo = new DefaultTableModel();
	private JTable table;
	private int cont;
	
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
	private JButton btnWindowExit;
	
	private JPanel regCity;
	private JLabel lbCity;
	private JTextField txfCity; 
	private JLabel lbState;
	private JTextField txfState;
	private JLabel lbCountry;
	private JTextField txfCountry;
	private JButton btnCityRegister;
	
	String Sexo[] = { "Masculino", "Feminino" };
	
	
	
	private JPanel regStudent;
	private JLabel lbStudent;
	private JTextField txfStudent;
	private JLabel lbBirthdate;
	private JTextField txfBirthdate;
	private JLabel lbSex;
	private JComboBox<?> cmbSex;
	private JButton btnStudentRegister;
	private JLabel lbPhone;
	private JTextField txfPhone;
	private JLabel lbCellphone;
	private JTextField txfCellphone;
	private JLabel lbEmail;
	private JTextField txfEmail;
	private JLabel lbNote;
	private JTextField txfNote;
	private JLabel lbAdress;
	private JTextField txfAdress;
	private JLabel lbAdressNum;
	private JTextField txfAdressNum;
	private JLabel lbComplement;
	private JTextField txfComplement;
	private JLabel lbSuburb;
	private JTextField txfSuburb;
	private JLabel lbSCity;
	private JTextField txfSCity;
	private JLabel lbStuEstate;
	private JTextField txfStuEstate;
	private JLabel lbCep;
	private JTextField txfCep;
	

	public MainWindow (Users user) {
		
		setSize(600,600);
		setTitle("Menu");
		setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        CreateCitiesComponents();
        CreateStudentsComponents();
        CreateUsersComponents();
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
				
				regCity.setVisible(true);
				
			}
			
		});
		btnCadastroCidade.setBounds(30, 20, 140, 30);    	
		btnCadastroCidade.setFocusPainted(false);
    	btnCadastroCidade.setContentAreaFilled(false);
		getContentPane().add(btnCadastroCidade);
		
		//cities edit
    	btnEditarCidade = new JButton(new AbstractAction("Editar Cidade") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("futuramente uma edição");
				
			}
			
		});
    	btnEditarCidade.setBounds(230, 20, 140, 30);    	
    	btnEditarCidade.setFocusPainted(false);
    	btnEditarCidade.setContentAreaFilled(false);
    	getContentPane().add(btnEditarCidade);
    	
		//cities delete
    	btnDeletarCidade = new JButton(new AbstractAction("Deletar Cidade") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("futuramente uma exclusão");
				
			}
			
		});
    	btnDeletarCidade.setBounds(430, 20, 140, 30); 	
    	btnDeletarCidade.setFocusPainted(false);
    	btnDeletarCidade.setContentAreaFilled(false);
    	getContentPane().add(btnDeletarCidade);
		
    	//hide button after initializing
		btnCadastroCidade.setVisible(false);
    	btnEditarCidade.setVisible(false);
    	btnDeletarCidade.setVisible(false);
    	
    	//register panel
    	regCity = new JPanel();
		regCity.setLayout(null);
		regCity.setBounds(200, 80, 210, 280);
		regCity.setBorder(BorderFactory.createTitledBorder("Nova Cidade"));
		getContentPane().add(regCity);
		regCity.setVisible(false);
		
    	lbCity = new JLabel();
    	lbCity.setText("Cidade:");
    	lbCity.setBounds(40, 40, 125, 20);
    	regCity.add(lbCity);
    	
    	txfCity = new JTextField();
    	txfCity.setBounds(40, 60, 125, 20);
    	regCity.add(txfCity);
    	
    	lbState = new JLabel();
    	lbState.setText("Estado:");
    	lbState.setBounds(40, 100, 125, 20);
    	regCity.add(lbState);
    	
    	txfState = new JTextField();
    	txfState.setBounds(40, 120, 125, 20);
    	regCity.add(txfState);
    	
    	lbCountry = new JLabel();
    	lbCountry.setText("Pais:");
    	lbCountry.setBounds(40, 160, 125, 20);
    	regCity.add(lbCountry);
    	
    	txfCountry = new JTextField();
    	txfCountry.setBounds(40, 180, 125, 20);
    	regCity.add(txfCountry);
    	
    	btnCityRegister = new JButton(new AbstractAction("Cadastrar") {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
    	btnCityRegister.setBounds(53, 230, 100, 20);   	
    	regCity.add(btnCityRegister);
    	btnCityRegister.setFocusPainted(false);
    	btnCityRegister.setContentAreaFilled(false);
			    	
    	getContentPane().add(regCity);
    	
	}
	
	public void CreateStudentsComponents() {
		
		
		//students add
		btnCadastroAluno = new JButton(new AbstractAction("Cadastrar Aluno") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				regStudent.setVisible(true);
				btnCadastroAluno.setVisible(false);
				btnEditarAluno.setVisible(false);
		    	btnDeletarAluno.setVisible(false);
				
			}
			
		});
		btnCadastroAluno.setBounds(30, 20, 140, 30);   
		btnCadastroAluno.setFocusPainted(false);
		btnCadastroAluno.setContentAreaFilled(false);
		getContentPane().add(btnCadastroAluno);

		
		
		//students edit
    	btnEditarAluno = new JButton(new AbstractAction("Editar Aluno") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("futuramente uma edição");
				
			}
			
		});
    	btnEditarAluno.setBounds(230, 20, 140, 30);    	
    	btnEditarAluno.setFocusPainted(false);
    	btnEditarAluno.setContentAreaFilled(false);
    	getContentPane().add(btnEditarAluno);
    	
    	
    	
		//students delete
    	btnDeletarAluno = new JButton(new AbstractAction("Deletar Aluno") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("futuramente uma exclusão");
				
			}
			
		});
    	btnDeletarAluno.setBounds(430, 20, 140, 30); 	
    	btnDeletarAluno.setFocusPainted(false);
    	btnDeletarAluno.setContentAreaFilled(false);
    	getContentPane().add(btnDeletarAluno);
    	
		
    	//hide button after initializing
		btnCadastroAluno.setVisible(false);
		btnEditarAluno.setVisible(false);
    	btnDeletarAluno.setVisible(false);
    	
    	//register panel
    	regStudent = new JPanel();
    	regStudent.setLayout(null);
    	regStudent.setBounds(0, 0, 600, 600);
    	regStudent.setBorder(BorderFactory.createTitledBorder("Novo Aluno"));
		getContentPane().add(regStudent);
		regStudent.setVisible(false);
		
		lbStudent = new JLabel();
		lbStudent.setText("Estudante:");
		lbStudent.setBounds(40, 40, 125, 20);
		regStudent.add(lbStudent);
    	
    	txfStudent = new JTextField();
    	txfStudent.setBounds(40, 60, 125, 20);
    	regStudent.add(txfStudent);
    	
    	lbSex = new JLabel();
    	lbSex.setText("Sexo:");
    	lbSex.setBounds(40, 85, 125, 20);
    	regStudent.add(lbSex);
		
    	cmbSex = new JComboBox<>(Sexo);
    	cmbSex.setBounds(40, 105, 125, 20);
    	regStudent.add(cmbSex);
    	
    	lbBirthdate = new JLabel();
    	lbBirthdate.setText("Data de nascimento:");
    	lbBirthdate.setBounds(40, 135, 125, 20);
    	regStudent.add(lbBirthdate);
    	
    	txfBirthdate = new JTextField();
    	txfBirthdate.setBounds(40, 155, 125, 20);
    	regStudent.add(txfBirthdate);   
    	
    	lbAdress = new JLabel();
    	lbAdress.setText("Endereço:");
    	lbAdress.setBounds(270, 40, 125, 20);
    	regStudent.add(lbAdress);
    	
    	txfAdress = new JTextField();
    	txfAdress.setBounds(270, 60, 270, 20);
    	regStudent.add(txfAdress);
    	
    	lbAdressNum = new JLabel();
    	lbAdressNum.setText("Número:");
    	lbAdressNum.setBounds(270, 85, 40, 20);
    	regStudent.add(lbAdressNum);
    	
    	txfAdressNum = new JTextField();
    	txfAdressNum.setBounds(270, 105, 40, 20);
    	regStudent.add(txfAdressNum);
    	
    	lbSuburb = new JLabel();
    	lbSuburb.setText("Bairro:");
    	lbSuburb.setBounds(320, 85, 125, 20);
    	regStudent.add(lbSuburb);
    	
    	txfSuburb = new JTextField();
    	txfSuburb.setBounds(320, 105, 220, 20);
    	regStudent.add(txfSuburb);
    	
    	lbComplement = new JLabel();
    	lbComplement.setText("Complemento:");
    	lbComplement.setBounds(270, 130, 125, 20);
    	regStudent.add(lbComplement);
    	
    	txfComplement = new JTextField();
    	txfComplement.setBounds(270, 150, 270, 20);
    	regStudent.add(txfComplement);

    	lbSCity = new JLabel();
    	lbSCity.setText("Cidade:");
    	lbSCity.setBounds(270, 175, 125, 20);
    	regStudent.add(lbSCity);
    	
    	txfSCity = new JTextField();
    	txfSCity.setBounds(270, 195, 125, 20);
    	regStudent.add(txfSCity);
    	
    	lbStuEstate= new JLabel();
    	lbStuEstate.setText("Estado:");
    	lbStuEstate.setBounds(405, 175, 125, 20);
    	regStudent.add(lbStuEstate);
    	
    	txfStuEstate = new JTextField();
    	txfStuEstate.setBounds(405, 195, 135, 20);
    	regStudent.add(txfStuEstate);
    	
    	lbCep= new JLabel();
    	lbCep.setText("CEP:");
    	lbCep.setBounds(270, 220, 125, 20);
    	regStudent.add(lbCep);
    	
    	txfCep = new JTextField();
    	txfCep.setBounds(270, 240, 125, 20);
    	regStudent.add(txfCep);
    	
    	lbPhone = new JLabel();
    	lbPhone.setText("Telefone:");
    	lbPhone.setBounds(40, 310, 125, 20);
    	regStudent.add(lbPhone);
    	
    	txfPhone = new JTextField();
    	txfPhone.setBounds(40, 330, 125, 20);
    	regStudent.add(txfPhone);   	
    	
    	lbCellphone = new JLabel();
    	lbCellphone.setText("Celular:");
    	lbCellphone.setBounds(175, 310, 125, 20);
    	regStudent.add(lbCellphone);
    	
    	txfCellphone = new JTextField();
    	txfCellphone.setBounds(175, 330, 125, 20);
    	regStudent.add(txfCellphone);   
    	
    	lbEmail = new JLabel();
    	lbEmail.setText("Email:");
    	lbEmail.setBounds(310, 310, 125, 20);
    	regStudent.add(lbEmail);
    	
    	txfEmail = new JTextField();
    	txfEmail.setBounds(310, 330, 230, 20);
    	regStudent.add(txfEmail); 
    	
    	lbNote = new JLabel();
    	lbNote.setText("Observação:");
    	lbNote.setBounds(40, 355, 125, 20);
    	regStudent.add(lbNote);
    	
    	txfNote = new JTextField();
    	txfNote.setBounds(40, 375, 500, 70);
    	regStudent.add(txfNote);
    	
    	//button for registering
    	btnStudentRegister = new JButton(new AbstractAction("Cadastrar") {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				//TO-DO REGISTERING FUNCTIONS
				//
				
				regStudent.setVisible(false);
				btnCadastroAluno.setVisible(true);
				btnEditarAluno.setVisible(true);
		    	btnDeletarAluno.setVisible(true);
				
			}
		});
    	btnStudentRegister.setBounds(350, 475, 150, 30);   	
    	regStudent.add(btnStudentRegister);
    	btnStudentRegister.setFocusPainted(false);
    	btnStudentRegister.setContentAreaFilled(false);
    	
    	btnWindowExit = new JButton(new AbstractAction("Sair") {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				//edtStudent.setvisible(false);
				regStudent.setVisible(false);
				btnCadastroAluno.setVisible(true);
				btnEditarAluno.setVisible(true);
		    	btnDeletarAluno.setVisible(true);
				
			}
		});
    	btnWindowExit.setBounds(100, 475, 150, 30);   	
    	regStudent.add(btnWindowExit);
    	btnWindowExit.setFocusPainted(false);
    	btnWindowExit.setContentAreaFilled(false);
			    	
    	getContentPane().add(regStudent);
		
    	
	}
	
	public void CreateUsersComponents() {
		
		//user add
		btnCadastroUsuario = new JButton(new AbstractAction("Cadastrar Usuario") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("futuramente um cadastro");
				
			}
			
		});
		btnCadastroUsuario.setBounds(30, 20, 140, 30);  
		btnCadastroUsuario.setFocusPainted(false);
		btnCadastroUsuario.setContentAreaFilled(false);
		getContentPane().add(btnCadastroUsuario);
		
		
		//user edit
    	btnEditarUsuario = new JButton(new AbstractAction("Editar Usuario") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("futuramente uma edição");
				
			}
			
		});
    	btnEditarUsuario.setBounds(230, 20, 140, 30);    
    	btnEditarUsuario.setFocusPainted(false);
    	btnEditarUsuario.setContentAreaFilled(false);
    	getContentPane().add(btnEditarUsuario);
    	
    	
		//user delete
    	btnDeletarUsuario = new JButton(new AbstractAction("Deletar Usuario") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("futuramente uma exclusão");
				
			}
			
		});
    	btnDeletarUsuario.setBounds(430, 20, 140, 30); 	
    	btnDeletarUsuario.setFocusPainted(false);
    	btnDeletarUsuario.setContentAreaFilled(false);
    	getContentPane().add(btnDeletarUsuario);
		
    	btnCadastroUsuario.setVisible(false);
    	btnEditarUsuario.setVisible(false);
    	btnDeletarUsuario.setVisible(false);
		
	}

	public void CitiesWindow() {
		
		btnCadastroUsuario.setVisible(false);
		btnCadastroAluno.setVisible(false);
		btnCadastroCidade.setVisible(true);
		
		btnEditarUsuario.setVisible(false);
		btnEditarAluno.setVisible(false);
		btnEditarCidade.setVisible(true);
		
		btnDeletarUsuario.setVisible(false);
		btnDeletarAluno.setVisible(false);
		btnDeletarCidade.setVisible(true);
		
		
	}

	public void StudentsWindow() {

		btnCadastroUsuario.setVisible(false);
		btnCadastroCidade.setVisible(false);
		btnCadastroAluno.setVisible(true);
		
		btnEditarUsuario.setVisible(false);
		btnEditarCidade.setVisible(false);
		btnEditarAluno.setVisible(true);
		
		btnDeletarUsuario.setVisible(false);
		btnDeletarCidade.setVisible(false);
		btnDeletarAluno.setVisible(true);
		
	}
	
	public void UsersWindow() {
		
		btnCadastroCidade.setVisible(false);
		btnCadastroAluno.setVisible(false);
		btnCadastroUsuario.setVisible(true);
		
		btnEditarCidade.setVisible(false);
		btnEditarAluno.setVisible(false);
		btnEditarUsuario.setVisible(true);
		
		btnDeletarCidade.setVisible(false);
		btnDeletarAluno.setVisible(false);
		btnDeletarUsuario.setVisible(true);
		
		CreateTable("Users.txt");
		table.setVisible(true);
		scroll = new JScrollPane(table);
		getContentPane().add(scroll);
		scroll.setVisible(true);
		
	}
	
	/*public void CreateColumn (String coluna, int tamcoluna) {
        modelo.addColumn(coluna);
        table.getColumnModel().getColumn(1).setPreferredWidth(tamcoluna);
		cont++;
	}*/
	
	public void CreateTable(String filename) {
		JOptionPane.showMessageDialog(null, "Passou antes");
        switch(filename.charAt(0)) {
    	case 'U':
    		    JOptionPane.showMessageDialog(null, "Passou depois");
    		
    		
    		
	    		table = new JTable(modelo);
	            modelo.addColumn("Usuário");
	            modelo.addColumn("Perfil");
	            table.getColumnModel().getColumn(1).setPreferredWidth(120);
	            table.getColumnModel().getColumn(1).setPreferredWidth(120);
    		    
	            modelo.setNumRows(0);
	            
				ArrayList<Users> user;
				 try {
					user = (ArrayList) FileManipulation.selectAll("Users.txt");
					
					for (Users c : user) {
	    	            modelo.addRow(new Object[]{c.getUser(), c.getPerfil()});
	    	        }
				 } catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Erro aqui");
				 }
    	        
    		break;
    	case 'S':
				ArrayList<Students> student;
				try {
					student = (ArrayList) FileManipulation.selectAll("Cities.txt");
					for (Students c : student) {
	    	            modelo.addRow(new Object[]{c.getStudent(), c.getBirthdate(), c.getEmail(), c.getSex(), c.getPhone(), c.getCellphone(), c.getNumber(), c.getNote(), c.getCep(), c.getAddress(), c.getCity(), c.getEstate(), c.getComplement(), c.getSuburb()});
	    	        }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				regStudent.add(table);
				table.setVisible(true);
    	        
    	        
    		break;
    	case 'C':
				ArrayList<Cities> city;
				try {
					city = (ArrayList) FileManipulation.selectAll("Cities.txt");
					 for (Cities c : city) {
		    	            modelo.addRow(new Object[]{c.getCity(), c.getState(), c.getCountry()});
		    	     }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	        
    	       
    		break;
        }
    }

	
	public static void main(String[] args) {
		
		new MainWindow(null).setVisible(true);
		
	}

}
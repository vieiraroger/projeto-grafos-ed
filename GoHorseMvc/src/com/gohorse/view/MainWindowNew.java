package com.gohorse.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.gohorse.database.model.Cities;
import com.gohorse.database.model.Students;
import com.gohorse.database.model.Teacher;
import com.gohorse.database.model.Users;
import com.gohorse.lib.FileManipulation;

public class MainWindowNew extends JFrame {
		
	// Declarando botoes menu topo
	private JMenuItem smListCities;
	private JMenuItem smListStudents;
	private JMenuItem smListUsers;
	private JMenuItem smListPhases;
	private JMenuItem smListTeachers;
	private JMenuItem smListCourses;
	private JMenuItem smListSubjects;
	private JMenuBar menu;
	private JMenu mStudents;
	private JMenu mCities;
	private JMenu mUsers;
	private JMenu mTeachers;
	private JMenu mPhases;
	private JMenu mCourses;
	private JMenu mSubjects;
	
	// Paineis para registros
	private JPanel studentsRegisterPanel;
	
	// Paineis para cada menu
	private JPanel studentsPanel;
	private JPanel citiesPanel;
	private JPanel usersPanel;
	private JPanel teachersPanel;
	private JPanel phasesPanel;
	private JPanel coursesPanel;
	private JPanel subjectsPanel;

	public MainWindowNew () {

	    Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
	    setSize(tela.width, tela.height);
		setTitle("Menu");
		setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        CreateTopBarComponents();
        FillComponentsInStudentsPanel();
        FillComponentsInCitiesPanel();
        FillComponentsInUsersPanel();
        FillComponentsInTeachersPanel();
        FillComponentsInPhasesPanel();
        FillComponentsInCoursesPanel();
        FillComponentsInSubjectsPanel();
        
        }
	
	public static void main(String[] args) {
		MainWindowNew mw = new MainWindowNew();
		mw.setVisible(true);
		
	}
	
	public void CreateTopBarComponents() {
		
		 menu = new JMenuBar();
		 setJMenuBar(menu);
		 
		 mStudents 	= new JMenu("Alunos");
		 mCities 	= new JMenu("Cidades");
		 mTeachers 	= new JMenu("Professores");
		 mCourses	= new JMenu("Cursos");
		 mPhases 	= new JMenu("Fases");
		 mSubjects 	= new JMenu("Disciplinas");
		 mUsers		= new JMenu("Usuarios");
		 
		 menu.add(mStudents);
		 menu.add(mCities);
		 menu.add(mTeachers);
		 menu.add(mCourses);
		 menu.add(mPhases);
		 menu.add(mSubjects);
		 menu.add(mUsers);
		 
		 smListStudents = new JMenuItem(new AbstractAction("Listar") {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				
				studentsPanel.setVisible(true);
				
				citiesPanel.setVisible(false);
				usersPanel.setVisible(false);
				teachersPanel.setVisible(false);
				phasesPanel.setVisible(false);
				coursesPanel.setVisible(false);
				subjectsPanel.setVisible(false);
				
			}
			
		 });
		 
		 smListCities= new JMenuItem(new AbstractAction("Listar") {
				
			@Override
			public void actionPerformed(ActionEvent e) {

				citiesPanel.setVisible(true);
				
				studentsPanel.setVisible(false);
				usersPanel.setVisible(false);
				teachersPanel.setVisible(false);
				phasesPanel.setVisible(false);
				coursesPanel.setVisible(false);
				subjectsPanel.setVisible(false);
				
			}
			
		 });
		 
		 smListCourses = new JMenuItem(new AbstractAction("Listar") {
				
			@Override
			public void actionPerformed(ActionEvent e) {
					
				coursesPanel.setVisible(true);
				
				studentsPanel.setVisible(false);
				usersPanel.setVisible(false);
				teachersPanel.setVisible(false);
				phasesPanel.setVisible(false);
				citiesPanel.setVisible(false);
				subjectsPanel.setVisible(false);
		
			}
				
		 });
			 
		 smListPhases = new JMenuItem(new AbstractAction("Listar") {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				
				phasesPanel.setVisible(true);
				
				studentsPanel.setVisible(false);
				usersPanel.setVisible(false);
				teachersPanel.setVisible(false);
				coursesPanel.setVisible(false);
				citiesPanel.setVisible(false);
				subjectsPanel.setVisible(false);
					
			}
				
		 });
			 
		 smListSubjects = new JMenuItem(new AbstractAction("Listar") {
				
			@Override
			public void actionPerformed(ActionEvent e) {
					
				subjectsPanel.setVisible(true);
				
				studentsPanel.setVisible(false);
				usersPanel.setVisible(false);
				teachersPanel.setVisible(false);
				coursesPanel.setVisible(false);
				citiesPanel.setVisible(false);
				phasesPanel.setVisible(false);
					
			}
				
		 });
		 
		 smListTeachers = new JMenuItem(new AbstractAction("Listar") {
				
			@Override
			public void actionPerformed(ActionEvent e) {
					
				teachersPanel.setVisible(true);
				
				studentsPanel.setVisible(false);
				usersPanel.setVisible(false);
				subjectsPanel.setVisible(false);
				coursesPanel.setVisible(false);
				citiesPanel.setVisible(false);
				phasesPanel.setVisible(false);
				
			}
				
		 });
	 
		 smListUsers= new JMenuItem(new AbstractAction("Listar") {
				
				@Override
				public void actionPerformed(ActionEvent e) {

					usersPanel.setVisible(true);
					
					studentsPanel.setVisible(false);
					teachersPanel.setVisible(false);
					subjectsPanel.setVisible(false);
					coursesPanel.setVisible(false);
					citiesPanel.setVisible(false);
					phasesPanel.setVisible(false);
					
				}
			 });
		 
		 mStudents.add(smListStudents);
		 mCities.add(smListCities);
		 mCourses.add(smListCourses);
		 mPhases.add(smListPhases);
		 mSubjects.add(smListSubjects);
		 mTeachers.add(smListTeachers);
		 mUsers.add(smListUsers);
		 
	}
	
	public void FillComponentsInStudentsPanel() {	
		
		//Students Panel Declaration
		studentsPanel = new JPanel();
		studentsPanel.setLayout(null);
		studentsPanel.setBounds(0, 0, 1200, 1200);
		getContentPane().add(studentsPanel);
				
		//Main Panel Buttons
		JButton btnRegisterStudents = new JButton("Cadastrar Aluno");
		JButton btnEditStudents = new JButton("Editar Aluno");
		JButton btnDeleteStudents = new JButton("Deletar Aluno");
		
		//Students Registering 
	    btnRegisterStudents.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	            	studentsRegisterPanel.setVisible(true);
					btnRegisterStudents.setVisible(false);
					btnEditStudents.setVisible(false);
					btnDeleteStudents.setVisible(false);
					
	            }
	        });
		btnRegisterStudents.setBounds(30, 20, 140, 30);   
		btnRegisterStudents.setFocusPainted(false);
		btnRegisterStudents.setContentAreaFilled(false);
		studentsPanel.add(btnRegisterStudents);
	
		//Students Editing 
        btnEditStudents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	studentsRegisterPanel.setVisible(true);
				btnRegisterStudents.setVisible(false);
				btnEditStudents.setVisible(false);
				btnDeleteStudents.setVisible(false);
				
            }
        });
    	btnEditStudents.setBounds(230, 20, 140, 30);    	
    	btnEditStudents.setFocusPainted(false);
    	btnEditStudents.setContentAreaFilled(false);
    	studentsPanel.add(btnEditStudents);
    	
		//Students deleting
    	btnDeleteStudents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	studentsRegisterPanel.setVisible(true);
				btnRegisterStudents.setVisible(false);
				btnEditStudents.setVisible(false);
				btnDeleteStudents.setVisible(false);
				
            }
        });
        btnDeleteStudents.setBounds(430, 20, 140, 30); 	
        btnDeleteStudents.setFocusPainted(false);
        btnDeleteStudents.setContentAreaFilled(false);
    	studentsPanel.add(btnDeleteStudents);
    	    	
  
    	CreateComponentsStudentsRegisterPanel();
    	studentsPanel.setVisible(false);
    	
	}

	public void FillComponentsInCitiesPanel() {	
		
		//Cities Panel Declaration
		citiesPanel = new JPanel();
		citiesPanel.setLayout(null);
		citiesPanel.setBounds(0, 0, 1200, 1200);
		getContentPane().add(citiesPanel);
		
		JButton btnRegisterCities;
		JButton btnEditCities;
		JButton btnDeleteCities;
		
		//Cities Registering
		btnRegisterCities = new JButton(new AbstractAction("Cadastrar Cidade") {

			@Override
			public void actionPerformed(ActionEvent e) {					    	
				
			}
			
		});
		btnRegisterCities.setBounds(30, 20, 140, 30);   
		btnRegisterCities.setFocusPainted(false);
		btnRegisterCities.setContentAreaFilled(false);
		citiesPanel.add(btnRegisterCities);
	
		//Cities Editing 
    	btnEditCities = new JButton(new AbstractAction("Editar Cidade") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
		    	
			}
			
		});
    	btnEditCities.setBounds(230, 20, 140, 30);    	
    	btnEditCities.setFocusPainted(false);
    	btnEditCities.setContentAreaFilled(false);
    	citiesPanel.add(btnEditCities);
    	
		//Cities deleting
    	btnDeleteCities = new JButton(new AbstractAction("Deletar Cidade") {

			@Override
			public void actionPerformed(ActionEvent e) {
	
				
			}
			
		});
    	btnDeleteCities.setBounds(430, 20, 140, 30); 	
    	btnDeleteCities.setFocusPainted(false);
    	btnDeleteCities.setContentAreaFilled(false);
    	citiesPanel.add(btnDeleteCities);
    
    	citiesPanel.setVisible(false);
    	
	}

	public void FillComponentsInUsersPanel() {	
		
		//Users Panel Declaration
		usersPanel = new JPanel();
		usersPanel.setLayout(null);
		usersPanel.setBounds(0, 0, 1200, 1200);
		getContentPane().add(usersPanel);
		
		JButton btnRegisterUsers;
		JButton btnEditUsers;
		JButton btnDeleteUsers;
		
		//Users Registering
		btnRegisterUsers = new JButton(new AbstractAction("Cadastrar Usuário") {

			@Override
			public void actionPerformed(ActionEvent e) {					    	
				
			}
			
		});
		btnRegisterUsers.setBounds(30, 20, 140, 30);   
		btnRegisterUsers.setFocusPainted(false);
		btnRegisterUsers.setContentAreaFilled(false);
		usersPanel.add(btnRegisterUsers);
	
		//Users Editing 
    	btnEditUsers = new JButton(new AbstractAction("Editar Usuário") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
		    	
			}
			
		});
    	btnEditUsers.setBounds(230, 20, 140, 30);    	
    	btnEditUsers.setFocusPainted(false);
    	btnEditUsers.setContentAreaFilled(false);
    	usersPanel.add(btnEditUsers);
    	
		//Users deleting
    	btnDeleteUsers = new JButton(new AbstractAction("Deletar Usuário") {

			@Override
			public void actionPerformed(ActionEvent e) {
	
				
			}
			
		});
    	btnDeleteUsers.setBounds(430, 20, 140, 30); 	
    	btnDeleteUsers.setFocusPainted(false);
    	btnDeleteUsers.setContentAreaFilled(false);
    	usersPanel.add(btnDeleteUsers);
    
    	usersPanel.setVisible(false);
    	
	}
	
	public void FillComponentsInTeachersPanel() {	
		
		//Teachers Panel Declaration
		teachersPanel = new JPanel();
		teachersPanel.setLayout(null);
		teachersPanel.setBounds(0, 0, 1200, 1200);
		getContentPane().add(teachersPanel);
		
		JButton btnRegisterTeachers;
		JButton btnEditTeachers;
		JButton btnDeleteTeachers;
		
		//Teachers Registering
		btnRegisterTeachers = new JButton(new AbstractAction("Cadastrar Professores") {

			@Override
			public void actionPerformed(ActionEvent e) {					    	
				
			}
			
		});
		btnRegisterTeachers.setBounds(30, 20, 140, 30);   
		btnRegisterTeachers.setFocusPainted(false);
		btnRegisterTeachers.setContentAreaFilled(false);
		teachersPanel.add(btnRegisterTeachers);
	
		//Teachers Editing 
    	btnEditTeachers = new JButton(new AbstractAction("Editar Professores") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
		    	
			}
			
		});
    	btnEditTeachers.setBounds(230, 20, 140, 30);    	
    	btnEditTeachers.setFocusPainted(false);
    	btnEditTeachers.setContentAreaFilled(false);
    	teachersPanel.add(btnEditTeachers);
    	
		//Teachers deleting
    	btnDeleteTeachers = new JButton(new AbstractAction("Deletar Professores") {

			@Override
			public void actionPerformed(ActionEvent e) {
	
				
			}
			
		});
    	btnDeleteTeachers.setBounds(430, 20, 140, 30); 	
    	btnDeleteTeachers.setFocusPainted(false);
    	btnDeleteTeachers.setContentAreaFilled(false);
    	teachersPanel.add(btnDeleteTeachers);
    
    	teachersPanel.setVisible(false);
    	
	}

	public void FillComponentsInPhasesPanel() {	
		
		//Phases Panel Declaration
		phasesPanel = new JPanel();
		phasesPanel.setLayout(null);
		phasesPanel.setBounds(0, 0, 1200, 1200);
		getContentPane().add(phasesPanel);
		
		JButton btnRegisterPhases;
		JButton btnEditPhases;
		JButton btnDeletePhases;
		
		//Phases Registering
		btnRegisterPhases = new JButton(new AbstractAction("Cadastrar Fases") {

			@Override
			public void actionPerformed(ActionEvent e) {					    	
				
			}
			
		});
		btnRegisterPhases.setBounds(30, 20, 140, 30);   
		btnRegisterPhases.setFocusPainted(false);
		btnRegisterPhases.setContentAreaFilled(false);
		phasesPanel.add(btnRegisterPhases);
	
		//Phases Editing 
    	btnEditPhases = new JButton(new AbstractAction("Editar Fases") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
		    	
			}
			
		});
    	btnEditPhases.setBounds(230, 20, 140, 30);    	
    	btnEditPhases.setFocusPainted(false);
    	btnEditPhases.setContentAreaFilled(false);
    	phasesPanel.add(btnEditPhases);
    	
		//Phases deleting
    	btnDeletePhases = new JButton(new AbstractAction("Deletar Fases") {

			@Override
			public void actionPerformed(ActionEvent e) {
	
				
			}
			
		});
    	btnDeletePhases.setBounds(430, 20, 140, 30); 	
    	btnDeletePhases.setFocusPainted(false);
    	btnDeletePhases.setContentAreaFilled(false);
    	phasesPanel.add(btnDeletePhases);
    
    	phasesPanel.setVisible(false);
    	
	}
	
	public void FillComponentsInCoursesPanel() {	
		
		//Courses Panel Declaration
		coursesPanel = new JPanel();
		coursesPanel.setLayout(null);
		coursesPanel.setBounds(0, 0, 1200, 1200);
		getContentPane().add(coursesPanel);
		
		JButton btnRegisterCourses;
		JButton btnEditCourses;
		JButton btnDeleteCourses;
		
		//Courses Registering
		btnRegisterCourses = new JButton(new AbstractAction("Cadastrar Curso") {

			@Override
			public void actionPerformed(ActionEvent e) {					    	
				
			}
			
		});
		btnRegisterCourses.setBounds(30, 20, 140, 30);   
		btnRegisterCourses.setFocusPainted(false);
		btnRegisterCourses.setContentAreaFilled(false);
		coursesPanel.add(btnRegisterCourses);
	
		//Courses Editing 
    	btnEditCourses = new JButton(new AbstractAction("Editar Curso") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
		    	
			}
			
		});
    	btnEditCourses.setBounds(230, 20, 140, 30);    	
    	btnEditCourses.setFocusPainted(false);
    	btnEditCourses.setContentAreaFilled(false);
    	coursesPanel.add(btnEditCourses);
    	
		//Courses deleting
    	btnDeleteCourses = new JButton(new AbstractAction("Deletar Curso") {

			@Override
			public void actionPerformed(ActionEvent e) {
	
				
			}
			
		});
    	btnDeleteCourses.setBounds(430, 20, 140, 30); 	
    	btnDeleteCourses.setFocusPainted(false);
    	btnDeleteCourses.setContentAreaFilled(false);
    	coursesPanel.add(btnDeleteCourses);
    
    	coursesPanel.setVisible(false);
    	
	}

	public void FillComponentsInSubjectsPanel() {	
		
		//Subjects Panel Declaration
		subjectsPanel = new JPanel();
		subjectsPanel.setLayout(null);
		subjectsPanel.setBounds(0, 0, 1200, 1200);
		getContentPane().add(subjectsPanel);
		
		JButton btnRegisterSubjects;
		JButton btnEditSubjects;
		JButton btnDeleteSubjects;
		
		//Subjects Registering
		btnRegisterSubjects = new JButton(new AbstractAction("Cadastrar Materia") {

			@Override
			public void actionPerformed(ActionEvent e) {					    	
				
			}
			
		});
		btnRegisterSubjects.setBounds(30, 20, 140, 30);   
		btnRegisterSubjects.setFocusPainted(false);
		btnRegisterSubjects.setContentAreaFilled(false);
		subjectsPanel.add(btnRegisterSubjects);
	
		//Subjects Editing 
    	btnEditSubjects = new JButton(new AbstractAction("Editar Materia") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
		    	
			}
			
		});
    	btnEditSubjects.setBounds(230, 20, 140, 30);    	
    	btnEditSubjects.setFocusPainted(false);
    	btnEditSubjects.setContentAreaFilled(false);
    	subjectsPanel.add(btnEditSubjects);
    	
		//Subjects deleting
    	btnDeleteSubjects = new JButton(new AbstractAction("Deletar Materia") {

			@Override
			public void actionPerformed(ActionEvent e) {
	
				
			}
			
		});
    	btnDeleteSubjects.setBounds(430, 20, 140, 30); 	
    	btnDeleteSubjects.setFocusPainted(false);
    	btnDeleteSubjects.setContentAreaFilled(false);
    	subjectsPanel.add(btnDeleteSubjects);
    
    	subjectsPanel.setVisible(false);
    	
	}
	
	public void CreateComponentsStudentsRegisterPanel() {
		
		studentsRegisterPanel = new JPanel();
		studentsRegisterPanel.setLayout(null);
		studentsRegisterPanel.setBounds(0, 0, 600, 600);
		studentsRegisterPanel.setBorder(BorderFactory.createTitledBorder("Aluno"));
		getContentPane().add(studentsRegisterPanel);
		studentsRegisterPanel.setVisible(false);
		
		//Registering Panel Buttons
		JButton btnSaveStudents = new JButton("Salvar");
		JButton btnExitStudents = new JButton("Sair");
		
		String Sexo[] = { "Masculino", "Feminino" };
		
		JLabel lbStudent;
		JTextField txfStudent;
		JLabel lbBirthdate;
		JTextField txfBirthdate;
		JLabel lbSex;
		JComboBox<?> cmbSex;
		JLabel lbPhone;
		JTextField txfPhone;
		JLabel lbCellphone;
		JTextField txfCellphone;
		JLabel lbEmail;
		JTextField txfEmail;
		JLabel lbNote;
		JTextArea txfNote;
		JLabel lbAdress;
		JTextField txfAdress;
		JLabel lbAdressNum;
		JTextField txfAdressNum;
		JLabel lbComplement;
		JTextField txfComplement;
		JLabel lbSuburb;
		JTextField txfSuburb;
		JLabel lbSCity;
		JTextField txfSCity;
		JLabel lbStuEstate;
		JTextField txfStuEstate;
		JLabel lbCep;
		JTextField txfCep;
		
		lbStudent = new JLabel();
		lbStudent.setText("Estudante:");
		lbStudent.setBounds(40, 40, 125, 20);
		studentsRegisterPanel.add(lbStudent);
    	
    	txfStudent = new JTextField();
    	txfStudent.setBounds(40, 60, 125, 20);
    	studentsRegisterPanel.add(txfStudent);
    	
    	lbSex = new JLabel();
    	lbSex.setText("Sexo:");
    	lbSex.setBounds(40, 85, 125, 20);
    	studentsRegisterPanel.add(lbSex);
		
    	cmbSex = new JComboBox<>(Sexo);
    	cmbSex.setBounds(40, 105, 125, 20);
    	studentsRegisterPanel.add(cmbSex);
    	
    	lbBirthdate = new JLabel();
    	lbBirthdate.setText("Data de nascimento:");
    	lbBirthdate.setBounds(40, 135, 125, 20);
    	studentsRegisterPanel.add(lbBirthdate);
    	
    	txfBirthdate = new JTextField();
    	txfBirthdate.setBounds(40, 155, 125, 20);
    	studentsRegisterPanel.add(txfBirthdate);   
    	
    	lbAdress = new JLabel();
    	lbAdress.setText("EndereÃ§o:");
    	lbAdress.setBounds(270, 40, 125, 20);
    	studentsRegisterPanel.add(lbAdress);
    	
    	txfAdress = new JTextField();
    	txfAdress.setBounds(270, 60, 270, 20);
    	studentsRegisterPanel.add(txfAdress);
    	
    	lbAdressNum = new JLabel();
    	lbAdressNum.setText("NÂº:");
    	lbAdressNum.setBounds(270, 85, 40, 20);
    	studentsRegisterPanel.add(lbAdressNum);
    	
    	txfAdressNum = new JTextField();
    	txfAdressNum.setBounds(270, 105, 40, 20);
    	studentsRegisterPanel.add(txfAdressNum);
    	
    	lbSuburb = new JLabel();
    	lbSuburb.setText("Bairro:");
    	lbSuburb.setBounds(320, 85, 125, 20);
    	studentsRegisterPanel.add(lbSuburb);
    	
    	txfSuburb = new JTextField();
    	txfSuburb.setBounds(320, 105, 220, 20);
    	studentsRegisterPanel.add(txfSuburb);
    	
    	lbComplement = new JLabel();
    	lbComplement.setText("Complemento:");
    	lbComplement.setBounds(270, 130, 125, 20);
    	studentsRegisterPanel.add(lbComplement);
    	
    	txfComplement = new JTextField();
    	txfComplement.setBounds(270, 150, 270, 20);
    	studentsRegisterPanel.add(txfComplement);

    	lbSCity = new JLabel();
    	lbSCity.setText("Cidade:");
    	lbSCity.setBounds(270, 175, 125, 20);
    	studentsRegisterPanel.add(lbSCity);
    	
    	txfSCity = new JTextField();
    	txfSCity.setBounds(270, 195, 125, 20);
    	studentsRegisterPanel.add(txfSCity);
    	
    	lbStuEstate= new JLabel();
    	lbStuEstate.setText("Estado:");
    	lbStuEstate.setBounds(405, 175, 125, 20);
    	studentsRegisterPanel.add(lbStuEstate);
    	
    	txfStuEstate = new JTextField();
    	txfStuEstate.setBounds(405, 195, 135, 20);
    	studentsRegisterPanel.add(txfStuEstate);
    	
    	lbCep= new JLabel();
    	lbCep.setText("CEP:");
    	lbCep.setBounds(270, 220, 125, 20);
    	studentsRegisterPanel.add(lbCep);
    	
    	txfCep = new JTextField();
    	txfCep.setBounds(270, 240, 125, 20);
    	studentsRegisterPanel.add(txfCep);
    	
    	lbPhone = new JLabel();
    	lbPhone.setText("Telefone:");
    	lbPhone.setBounds(40, 310, 125, 20);
    	studentsRegisterPanel.add(lbPhone);
    	
    	txfPhone = new JTextField();
    	txfPhone.setBounds(40, 330, 125, 20);
    	studentsRegisterPanel.add(txfPhone);   	
    	
    	lbCellphone = new JLabel();
    	lbCellphone.setText("Celular:");
    	lbCellphone.setBounds(175, 310, 125, 20);
    	studentsRegisterPanel.add(lbCellphone);
    	
    	txfCellphone = new JTextField();
    	txfCellphone.setBounds(175, 330, 125, 20);
    	studentsRegisterPanel.add(txfCellphone);   
    	
    	lbEmail = new JLabel();
    	lbEmail.setText("Email:");
    	lbEmail.setBounds(310, 310, 125, 20);
    	studentsRegisterPanel.add(lbEmail);
    	
    	txfEmail = new JTextField();
    	txfEmail.setBounds(310, 330, 230, 20);
    	studentsRegisterPanel.add(txfEmail); 
    	
    	lbNote = new JLabel();
    	lbNote.setText("ObservaÃ§Ãµes:");
    	lbNote.setBounds(40, 355, 125, 20);
    	studentsRegisterPanel.add(lbNote);
    	
    	txfNote = new JTextArea();
    	txfNote.setBounds(40, 375, 500, 70);
    	txfNote.setBorder(BorderFactory.createEtchedBorder());
    	studentsRegisterPanel.add(txfNote);
    	
    	//Students register panel saving
    	btnSaveStudents.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {;
				
				studentsPanel.setVisible(true);
				studentsRegisterPanel.setVisible(false);
				
			}
		});
    	btnSaveStudents.setBounds(350, 475, 150, 30);   	
    	btnSaveStudents.setFocusPainted(false);
    	btnSaveStudents.setContentAreaFilled(false);
    	studentsRegisterPanel.add(btnSaveStudents);
		
    	studentsPanel.add(studentsRegisterPanel);
    	
	}
	
}
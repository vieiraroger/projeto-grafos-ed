package com.gohorse.view;

import java.awt.BasicStroke;
import java.awt.Color;
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
import javax.swing.JInternalFrame;
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
		
	// Declaring Panels
	private JPanel studentsPanel;
	private JPanel citiesPanel;
	private JPanel usersPanel;
	private JPanel teachersPanel;
	private JPanel phasesPanel;
	private JPanel coursesPanel;
	private JPanel subjectsPanel;
	
	// Declaring internal frames
	private JInternalFrame studentsInternalFrame;
	private JInternalFrame citiesInternalFrame;
	private JInternalFrame usersInternalFrame;
	private JInternalFrame teachersInternalFrame;
	private JInternalFrame phasesInternalFrame;
	private JInternalFrame coursesInternalFrame;
	private JInternalFrame subjectsInternalFrame;
	
	public MainWindowNew () {

	    setSize(1400, 800);
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
		
		 //declaring top bar objects
		 JMenuItem smListCities;
		 JMenuItem smListStudents;
		 JMenuItem smListUsers;
		 JMenuItem smListPhases;
		 JMenuItem smListTeachers;
		 JMenuItem smListCourses;
		 JMenuItem smListSubjects;
		 JMenuBar menu;
		 JMenu mStudents;
		 JMenu mCities;
		 JMenu mUsers;
		 JMenu mTeachers;
		 JMenu mPhases;
		 JMenu mCourses;
		 JMenu mSubjects;

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
				
				ShowPanel("students");
				
			}
			
		 });
		 
		 smListCities= new JMenuItem(new AbstractAction("Listar") {
				
			@Override
			public void actionPerformed(ActionEvent e) {

				ShowPanel("cities");
				
			}
			
		 });
		 
		 smListCourses = new JMenuItem(new AbstractAction("Listar") {
				
			@Override
			public void actionPerformed(ActionEvent e) {
					
				ShowPanel("courses");
		
			}
				
		 });
			 
		 smListPhases = new JMenuItem(new AbstractAction("Listar") {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ShowPanel("phases");
					
			}
				
		 });
			 
		 smListSubjects = new JMenuItem(new AbstractAction("Listar") {
				
			@Override
			public void actionPerformed(ActionEvent e) {
					
				ShowPanel("subjects");
					
			}
				
		 });
		 
		 smListTeachers = new JMenuItem(new AbstractAction("Listar") {
				
			@Override
			public void actionPerformed(ActionEvent e) {
					
				ShowPanel("teachers");
				
			}
				
		 });
	 
		 smListUsers= new JMenuItem(new AbstractAction("Listar") {
				
				@Override
				public void actionPerformed(ActionEvent e) {

					ShowPanel("users");
					
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
		
		CreateComponentStudentsInternalFrame();
		
		//Students Panel Declaration
		studentsPanel = new JPanel();
		studentsPanel.setLayout(null);
		studentsPanel.setBounds(0, 0, 1400, 800);
		getContentPane().add(studentsPanel);
		
		//Adding Internal frame and table to Panel (ORDER IS IMPORTANT)
		studentsPanel.add(studentsInternalFrame);	
		CreateStudentsTable();
		
		//Main Panel Buttons
		JButton btnRegisterStudents = new JButton("Cadastrar Aluno");
		JButton btnEditStudents = new JButton("Editar Aluno");
		JButton btnDeleteStudents = new JButton("Deletar Aluno");
		
		//Students Registering 
	    btnRegisterStudents.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	            	studentsInternalFrame.setVisible(true);	   
					
	            }
	        });
		btnRegisterStudents.setBounds(50, 30, 150, 40);   
		btnRegisterStudents.setFocusPainted(false);
		btnRegisterStudents.setContentAreaFilled(false);
		studentsPanel.add(btnRegisterStudents);
	
		//Students Editing 
        btnEditStudents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	

				
            }
        });
    	btnEditStudents.setBounds(210, 30, 150, 40);    	
    	btnEditStudents.setFocusPainted(false);
    	btnEditStudents.setContentAreaFilled(false);
    	studentsPanel.add(btnEditStudents);
    	
		//Students deleting
    	btnDeleteStudents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	
				
            }
        });
        btnDeleteStudents.setBounds(370, 30, 150, 40); 	
        btnDeleteStudents.setFocusPainted(false);
        btnDeleteStudents.setContentAreaFilled(false);
        studentsPanel.add(btnDeleteStudents);
    	
    	studentsPanel.setVisible(false);
    	
	}

	public void FillComponentsInCitiesPanel() {	
		
		CreateComponentCitiesInternalFrame();
		
		//Cities Panel Declaration
		citiesPanel = new JPanel();
		citiesPanel.setLayout(null);
		citiesPanel.setBounds(0, 0, 1400, 800);
		getContentPane().add(citiesPanel);
		
		//Adding Internal frame to Panel
		citiesPanel.add(citiesInternalFrame);	
		CreateCitiesTable();
		
		//Main Panel Buttons
		JButton btnRegisterCities;
		JButton btnEditCities;
		JButton btnDeleteCities;
		
		//Cities Registering
		btnRegisterCities = new JButton(new AbstractAction("Cadastrar Cidade") {

			@Override
			public void actionPerformed(ActionEvent e) {					    	
				
				citiesInternalFrame.setVisible(true);
				
			}
			
		});
		btnRegisterCities.setBounds(50, 30, 150, 40);    
		btnRegisterCities.setFocusPainted(false);
		btnRegisterCities.setContentAreaFilled(false);
		citiesPanel.add(btnRegisterCities);
	
		//Cities Editing 
    	btnEditCities = new JButton(new AbstractAction("Editar Cidade") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
		    	
			}
			
		});
    	btnEditCities.setBounds(210, 30, 150, 40);    	
    	btnEditCities.setFocusPainted(false);
    	btnEditCities.setContentAreaFilled(false);
    	citiesPanel.add(btnEditCities);
    	
		//Cities deleting
    	btnDeleteCities = new JButton(new AbstractAction("Deletar Cidade") {

			@Override
			public void actionPerformed(ActionEvent e) {
	
				
			}
			
		});
    	btnDeleteCities.setBounds(370, 30, 150, 40); 	
    	btnDeleteCities.setFocusPainted(false);
    	btnDeleteCities.setContentAreaFilled(false);
    	citiesPanel.add(btnDeleteCities);
    
    	citiesPanel.setVisible(false);
    	
	}

	public void FillComponentsInUsersPanel() {	
		
		CreateComponentUsersInternalFrame();
		
		//Users Panel Declaration
		usersPanel = new JPanel();
		usersPanel.setLayout(null);
		usersPanel.setBounds(0, 0, 1400, 800);
		getContentPane().add(usersPanel);
		
		//Adding Internal frame and Table to Panel
		usersPanel.add(usersInternalFrame);	
		CreateUsersTable();
		
		//Main Panel Buttons
		JButton btnRegisterUsers;
		JButton btnEditUsers;
		JButton btnDeleteUsers;
		
		//Users Registering
		btnRegisterUsers = new JButton(new AbstractAction("Cadastrar Usuário") {

			@Override
			public void actionPerformed(ActionEvent e) {					    	
				
				usersInternalFrame.setVisible(true);
				
			}
			
		});
		btnRegisterUsers.setBounds(50, 30, 150, 40);   
		btnRegisterUsers.setFocusPainted(false);
		btnRegisterUsers.setContentAreaFilled(false);
		usersPanel.add(btnRegisterUsers);
	
		//Users Editing 
    	btnEditUsers = new JButton(new AbstractAction("Editar Usuário") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
		    	
			}
			
		});
    	btnEditUsers.setBounds(210, 30, 150, 40);   	
    	btnEditUsers.setFocusPainted(false);
    	btnEditUsers.setContentAreaFilled(false);
    	usersPanel.add(btnEditUsers);
    	
		//Users deleting
    	btnDeleteUsers = new JButton(new AbstractAction("Deletar Usuário") {

			@Override
			public void actionPerformed(ActionEvent e) {
	
				
			}
			
		});
    	btnDeleteUsers.setBounds(370, 30, 150, 40); 
    	btnDeleteUsers.setFocusPainted(false);
    	btnDeleteUsers.setContentAreaFilled(false);
    	usersPanel.add(btnDeleteUsers);
    
    	usersPanel.setVisible(false);
    	
	}
	
	public void FillComponentsInTeachersPanel() {	
		
		CreateComponentTeachersInternalFrame();
		
		//Teachers Panel Declaration
		teachersPanel = new JPanel();
		teachersPanel.setLayout(null);
		teachersPanel.setBounds(0, 0, 1200, 1200);
		getContentPane().add(teachersPanel);
		
		//Adding Internal frame to Panel
		teachersPanel.add(teachersInternalFrame);
		
		JButton btnRegisterTeachers;
		JButton btnEditTeachers;
		JButton btnDeleteTeachers;
		
		//Teachers Registering
		btnRegisterTeachers = new JButton(new AbstractAction("Cadastrar Professores") {

			@Override
			public void actionPerformed(ActionEvent e) {					    	
				
				teachersInternalFrame.setVisible(true);
				
			}
			
		});
		btnRegisterTeachers.setBounds(30, 20, 180, 30);   
		btnRegisterTeachers.setFocusPainted(false);
		btnRegisterTeachers.setContentAreaFilled(false);
		teachersPanel.add(btnRegisterTeachers);
	
		//Teachers Editing 
    	btnEditTeachers = new JButton(new AbstractAction("Editar Professores") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
		    	
			}
			
		});
    	btnEditTeachers.setBounds(230, 20, 180, 30);    	
    	btnEditTeachers.setFocusPainted(false);
    	btnEditTeachers.setContentAreaFilled(false);
    	teachersPanel.add(btnEditTeachers);
    	
		//Teachers deleting
    	btnDeleteTeachers = new JButton(new AbstractAction("Deletar Professores") {

			@Override
			public void actionPerformed(ActionEvent e) {
	
				
			}
			
		});
    	btnDeleteTeachers.setBounds(430, 20, 180, 30); 	
    	btnDeleteTeachers.setFocusPainted(false);
    	btnDeleteTeachers.setContentAreaFilled(false);
    	teachersPanel.add(btnDeleteTeachers);
    
    	teachersPanel.setVisible(false);
    	
	}

	public void FillComponentsInPhasesPanel() {	
		
		CreateComponentPhasesInternalFrame();
		
		//Phases Panel Declaration
		phasesPanel = new JPanel();
		phasesPanel.setLayout(null);
		phasesPanel.setBounds(0, 0, 1200, 1200);
		getContentPane().add(phasesPanel);
		
		//Adding Internal frame to Panel
		phasesPanel.add(phasesInternalFrame);
		
		JButton btnRegisterPhases;
		JButton btnEditPhases;
		JButton btnDeletePhases;
		
		//Phases Registering
		btnRegisterPhases = new JButton(new AbstractAction("Cadastrar Fases") {

			@Override
			public void actionPerformed(ActionEvent e) {					    	
				
				phasesInternalFrame.setVisible(true);
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
		
		CreateComponentCoursesInternalFrame();
		
		//Courses Panel Declaration
		coursesPanel = new JPanel();
		coursesPanel.setLayout(null);
		coursesPanel.setBounds(0, 0, 1200, 1200);
		getContentPane().add(coursesPanel);
		
		//Adding Internal frame to Panel
		coursesPanel.add(coursesInternalFrame);
		
		JButton btnRegisterCourses;
		JButton btnEditCourses;
		JButton btnDeleteCourses;
		
		//Courses Registering
		btnRegisterCourses = new JButton(new AbstractAction("Cadastrar Curso") {

			@Override
			public void actionPerformed(ActionEvent e) {					    	
				
				coursesInternalFrame.setVisible(true);
				
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
	
	public void CreateComponentStudentsInternalFrame() {
		
		studentsInternalFrame = new JInternalFrame("Cadastro de Aluno");
		studentsInternalFrame.setLayout(null);
		studentsInternalFrame.setBounds(0, 0, 600, 600);
		studentsInternalFrame.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.5f)));
		
		//Registering Panel Buttons declarations
		JButton btnSaveStudents = new JButton("Salvar");
		JButton btnExitStudents = new JButton("Sair");
		
		//Registering Panel Fields declarations
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
		studentsInternalFrame.add(lbStudent);
    	
    	txfStudent = new JTextField();
    	txfStudent.setBounds(40, 60, 125, 20);
    	studentsInternalFrame.add(txfStudent);
    	
    	lbSex = new JLabel();
    	lbSex.setText("Sexo:");
    	lbSex.setBounds(40, 85, 125, 20);
    	studentsInternalFrame.add(lbSex);
		
    	cmbSex = new JComboBox<>(Sexo);
    	cmbSex.setBounds(40, 105, 125, 20);
    	studentsInternalFrame.add(cmbSex);
    	
    	lbBirthdate = new JLabel();
    	lbBirthdate.setText("Data de nascimento:");
    	lbBirthdate.setBounds(40, 135, 125, 20);
    	studentsInternalFrame.add(lbBirthdate);
    	
    	txfBirthdate = new JTextField();
    	txfBirthdate.setBounds(40, 155, 125, 20);
    	studentsInternalFrame.add(txfBirthdate);   
    	
    	lbAdress = new JLabel();
    	lbAdress.setText("EndereÃ§o:");
    	lbAdress.setBounds(270, 40, 125, 20);
    	studentsInternalFrame.add(lbAdress);
    	
    	txfAdress = new JTextField();
    	txfAdress.setBounds(270, 60, 270, 20);
    	studentsInternalFrame.add(txfAdress);
    	
    	lbAdressNum = new JLabel();
    	lbAdressNum.setText("NÂº:");
    	lbAdressNum.setBounds(270, 85, 40, 20);
    	studentsInternalFrame.add(lbAdressNum);
    	
    	txfAdressNum = new JTextField();
    	txfAdressNum.setBounds(270, 105, 40, 20);
    	studentsInternalFrame.add(txfAdressNum);
    	
    	lbSuburb = new JLabel();
    	lbSuburb.setText("Bairro:");
    	lbSuburb.setBounds(320, 85, 125, 20);
    	studentsInternalFrame.add(lbSuburb);
    	
    	txfSuburb = new JTextField();
    	txfSuburb.setBounds(320, 105, 220, 20);
    	studentsInternalFrame.add(txfSuburb);
    	
    	lbComplement = new JLabel();
    	lbComplement.setText("Complemento:");
    	lbComplement.setBounds(270, 130, 125, 20);
    	studentsInternalFrame.add(lbComplement);
    	
    	txfComplement = new JTextField();
    	txfComplement.setBounds(270, 150, 270, 20);
    	studentsInternalFrame.add(txfComplement);

    	lbSCity = new JLabel();
    	lbSCity.setText("Cidade:");
    	lbSCity.setBounds(270, 175, 125, 20);
    	studentsInternalFrame.add(lbSCity);
    	
    	txfSCity = new JTextField();
    	txfSCity.setBounds(270, 195, 125, 20);
    	studentsInternalFrame.add(txfSCity);
    	
    	lbStuEstate= new JLabel();
    	lbStuEstate.setText("Estado:");
    	lbStuEstate.setBounds(405, 175, 125, 20);
    	studentsInternalFrame.add(lbStuEstate);
    	
    	txfStuEstate = new JTextField();
    	txfStuEstate.setBounds(405, 195, 135, 20);
    	studentsInternalFrame.add(txfStuEstate);
    	
    	lbCep= new JLabel();
    	lbCep.setText("CEP:");
    	lbCep.setBounds(270, 220, 125, 20);
    	studentsInternalFrame.add(lbCep);
    	
    	txfCep = new JTextField();
    	txfCep.setBounds(270, 240, 125, 20);
    	studentsInternalFrame.add(txfCep);
    	
    	lbPhone = new JLabel();
    	lbPhone.setText("Telefone:");
    	lbPhone.setBounds(40, 310, 125, 20);
    	studentsInternalFrame.add(lbPhone);
    	
    	txfPhone = new JTextField();
    	txfPhone.setBounds(40, 330, 125, 20);
    	studentsInternalFrame.add(txfPhone);   	
    	
    	lbCellphone = new JLabel();
    	lbCellphone.setText("Celular:");
    	lbCellphone.setBounds(175, 310, 125, 20);
    	studentsInternalFrame.add(lbCellphone);
    	
    	txfCellphone = new JTextField();
    	txfCellphone.setBounds(175, 330, 125, 20);
    	studentsInternalFrame.add(txfCellphone);   
    	
    	lbEmail = new JLabel();
    	lbEmail.setText("Email:");
    	lbEmail.setBounds(310, 310, 125, 20);
    	studentsInternalFrame.add(lbEmail);
    	
    	txfEmail = new JTextField();
    	txfEmail.setBounds(310, 330, 230, 20);
    	studentsInternalFrame.add(txfEmail); 
    	
    	lbNote = new JLabel();
    	lbNote.setText("ObservaÃ§Ãµes:");
    	lbNote.setBounds(40, 355, 125, 20);
    	studentsInternalFrame.add(lbNote);
    	
    	txfNote = new JTextArea();
    	txfNote.setBounds(40, 375, 500, 70);
    	txfNote.setBorder(BorderFactory.createEtchedBorder());
    	studentsInternalFrame.add(txfNote); 
    	//Registering Panel Fields declarations
    	
    	//button for register panel saving
    	btnSaveStudents.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {;
				
				studentsPanel.setVisible(true);
				studentsInternalFrame.setVisible(false);
				
			}
		});
    	btnSaveStudents.setBounds(350, 475, 150, 30);   	
    	btnSaveStudents.setFocusPainted(false);
    	btnSaveStudents.setContentAreaFilled(false);
    	studentsInternalFrame.add(btnSaveStudents);
    	
    	//button for register panel exiting
    	btnExitStudents.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {;
				
				studentsPanel.setVisible(true);
				studentsInternalFrame.setVisible(false);
				
			}
		});
    	btnExitStudents.setBounds(100, 475, 150, 30);   	
    	btnExitStudents.setFocusPainted(false);
    	btnExitStudents.setContentAreaFilled(false);
    	studentsInternalFrame.add(btnExitStudents);
		
    	studentsInternalFrame.setVisible(false);
	
	}

	public void CreateComponentCitiesInternalFrame() {
		
		citiesInternalFrame = new JInternalFrame("Cadastro de Cidade");
		citiesInternalFrame.setLayout(null);
		citiesInternalFrame.setBounds(200, 80, 210, 310);
		citiesInternalFrame.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.5f)));
		
		//Registering Panel Buttons declarations
		JButton btnSaveCities = new JButton("Salvar");
		JButton btnExitCities = new JButton("Sair");
		
		//Registering Panel Fields declarations
		JLabel lbCity;
		JTextField txfCity; 
		JLabel lbState;
		JTextField txfState;
		JLabel lbCountry;
		JTextField txfCountry;
		
    	lbCity = new JLabel();
    	lbCity.setText("Cidade:");
    	lbCity.setBounds(40, 40, 125, 20);
    	citiesInternalFrame.add(lbCity);
    	
    	txfCity = new JTextField();
    	txfCity.setBounds(40, 60, 125, 20);
    	citiesInternalFrame.add(txfCity);
    	
    	lbState = new JLabel();
    	lbState.setText("Estado:");
    	lbState.setBounds(40, 100, 125, 20);
    	citiesInternalFrame.add(lbState);
    	
    	txfState = new JTextField();
    	txfState.setBounds(40, 120, 125, 20);
    	citiesInternalFrame.add(txfState);
    	
    	lbCountry = new JLabel();
    	lbCountry.setText("Pais:");
    	lbCountry.setBounds(40, 160, 125, 20);
    	citiesInternalFrame.add(lbCountry);
    	
    	txfCountry = new JTextField();
    	txfCountry.setBounds(40, 180, 125, 20);
    	citiesInternalFrame.add(txfCountry);
    	////Registering Panel Fields declarations
    	
    	
    	//Register panel saving
    	btnSaveCities.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {;
				
				citiesInternalFrame.setVisible(false);
				
			}
		});
    	btnSaveCities.setBounds(93, 230, 95, 20);    	
    	btnSaveCities.setFocusPainted(false);
    	btnSaveCities.setContentAreaFilled(false);
    	citiesInternalFrame.add(btnSaveCities);
    	
    	//Register panel exiting
    	btnExitCities.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {;
				
				citiesInternalFrame.setVisible(false);
				
			}
		});
    	btnExitCities.setBounds(23, 230, 60, 20);
    	btnExitCities.setFocusPainted(false);
    	btnExitCities.setContentAreaFilled(false);
    	citiesInternalFrame.add(btnExitCities);
		
    	citiesInternalFrame.setVisible(false);
	
	}

	public void CreateComponentUsersInternalFrame() {
		
		usersInternalFrame = new JInternalFrame("Cadastro de Usuário");
		usersInternalFrame.setLayout(null);
		usersInternalFrame.setBounds(180, 150, 228, 290);
		usersInternalFrame.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.5f)));
		
		//Registering Panel Buttons declarations
		JButton btnSaveUsers = new JButton("Salvar");
		JButton btnExitUsers = new JButton("Sair");
		
		//Registering Panel Fields declarations
		
		String UserType[] = { "User", "Admin" };
		
		JLabel lbUser;
		JTextField txfUser;
		JLabel lbPassword;
		JPasswordField txfPassword;
		JLabel lbType;
		JComboBox<?> cmbType;
				
    	lbUser = new JLabel();
    	lbUser.setText("UsuÃ¡rio:");
    	lbUser.setBounds(50, 40, 125, 20);
    	usersInternalFrame.add(lbUser);
    	
    	txfUser = new JTextField();
    	txfUser.setBounds(50, 60, 125, 20);
    	usersInternalFrame.add(txfUser);
    	
    	lbPassword = new JLabel();
    	lbPassword.setText("Senha:");
    	lbPassword.setBounds(50, 85, 125, 20);
    	usersInternalFrame.add(lbPassword);
    	
    	txfPassword = new JPasswordField();
    	txfPassword.setBounds(50, 105, 125, 20);
    	usersInternalFrame.add(txfPassword);
    	
    	lbType= new JLabel();
    	lbType.setText("Perfil:");
    	lbType.setBounds(50, 130, 125, 20);
    	usersInternalFrame.add(lbType);
    	
    	cmbType = new JComboBox<>(UserType);
    	cmbType.setBounds(50, 150, 125, 20);
    	usersInternalFrame.add(cmbType);
    	////Registering Panel Fields declarations
    	
    	
    	//Register panel saving
    	btnSaveUsers.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {;
				
				usersInternalFrame.setVisible(false);
				
			}
		});
    	btnSaveUsers.setBounds(115, 200, 95, 20); 	
    	btnSaveUsers.setFocusPainted(false);
    	btnSaveUsers.setContentAreaFilled(false);
    	usersInternalFrame.add(btnSaveUsers);
    	
    	//Register panel exiting
    	btnExitUsers.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {;
				
				usersInternalFrame.setVisible(false);
				
			}
		});
    	btnExitUsers.setBounds(20, 200, 90, 20); 
    	btnExitUsers.setFocusPainted(false);
    	btnExitUsers.setContentAreaFilled(false);
    	usersInternalFrame.add(btnExitUsers);
		
    	usersInternalFrame.setVisible(false);
	
	}
	
	public void CreateComponentTeachersInternalFrame() {
		
		teachersInternalFrame = new JInternalFrame("Cadastro de Professores");
		teachersInternalFrame.setLayout(null);
		teachersInternalFrame.setBounds(200, 80, 210, 280);
		teachersInternalFrame.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.5f)));
		
		//Registering Panel Buttons declarations
		JButton btnSaveTeachers = new JButton("Salvar");
		JButton btnExitTeachers = new JButton("Sair");
		
		//Registering Panel Fields declarations
		String TeacherGraduationType[] = { "Graduacao", "Pos-Graducao","Mestrado","Doutorado"};
		JLabel lbTeacherName;
		JTextField txfTeacherName; 
		JLabel lbTeacherCode;
		JTextField txfTeacherCode;
		JLabel lbTeacherGraduation;
		JComboBox<?> cbmTeacherGraduation;
		
    	lbTeacherCode= new JLabel();
    	lbTeacherCode.setText("Codigo:");
    	lbTeacherCode.setBounds(40, 40, 125, 20);
    	teachersInternalFrame.add(lbTeacherCode);
    	
    	txfTeacherCode= new JTextField();
    	txfTeacherCode.setBounds(40, 60, 125, 20);
    	teachersInternalFrame.add(txfTeacherCode);
    	
    	lbTeacherGraduation= new JLabel();
    	lbTeacherGraduation.setText("Graduacao:");
    	lbTeacherGraduation.setBounds(40, 130, 125, 20);
    	teachersInternalFrame.add(lbTeacherGraduation);
    	
    	cbmTeacherGraduation = new JComboBox<>(TeacherGraduationType);
    	cbmTeacherGraduation.setBounds(40, 150, 125, 20);
    	teachersInternalFrame.add(cbmTeacherGraduation);
    	
    	lbTeacherName= new JLabel();
    	lbTeacherName.setText("Nome:");
    	lbTeacherName.setBounds(40, 85, 125, 20);
    	teachersInternalFrame.add(lbTeacherName);
    	
    	txfTeacherName= new JTextField();
    	txfTeacherName.setBounds(40, 105, 125, 20);
    	teachersInternalFrame.add(txfTeacherName);
    	
    	//Registering Panel Fields declarations
    	
    	
    	//Register panel saving
    	btnSaveTeachers.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {;
				
				teachersInternalFrame.setVisible(false);
				
			}
		});
    	btnSaveTeachers.setBounds(93, 230, 95, 20);  	
    	btnSaveTeachers.setFocusPainted(false);
    	btnSaveTeachers.setContentAreaFilled(false);
    	teachersInternalFrame.add(btnSaveTeachers);
    	
    	//Register panel exiting
    	btnExitTeachers.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {;
				
				teachersInternalFrame.setVisible(false);
				
			}
		});
    	btnExitTeachers.setBounds(23, 230, 60, 20); 
    	btnExitTeachers.setFocusPainted(false);
    	btnExitTeachers.setContentAreaFilled(false);
    	teachersInternalFrame.add(btnExitTeachers);
		
    	teachersInternalFrame.setVisible(false);
	
	}
	
	public void CreateComponentPhasesInternalFrame() {
		
		phasesInternalFrame = new JInternalFrame("Cadastro de Fases");
		phasesInternalFrame.setLayout(null);
		phasesInternalFrame.setBounds(200, 80, 210, 280);
		phasesInternalFrame.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.5f)));
		
		//Registering Panel Buttons declarations
		JButton btnSavePhases = new JButton("Salvar");
		JButton btnExitPhases = new JButton("Sair");
		
		//Registering Panel Fields declarations
		JLabel lbPhaseName;
		JTextField txfPhaseName; 
		JLabel lbPhaseCode;
		JTextField txfPhaseCode;
		
    	lbPhaseCode= new JLabel();
    	lbPhaseCode.setText("Codigo:");
    	lbPhaseCode.setBounds(40, 40, 125, 20);
    	phasesInternalFrame.add(lbPhaseCode);
    	
    	txfPhaseCode= new JTextField();
    	txfPhaseCode.setBounds(40, 60, 125, 20);
    	phasesInternalFrame.add(txfPhaseCode);
    	
    	lbPhaseName= new JLabel();
    	lbPhaseName.setText("Nome:");
    	lbPhaseName.setBounds(40, 85, 125, 20);
    	phasesInternalFrame.add(lbPhaseName);
    	
    	txfPhaseName= new JTextField();
    	txfPhaseName.setBounds(40, 105, 125, 20);
    	phasesInternalFrame.add(txfPhaseName); 		
    	
    	//Register panel saving
    	btnSavePhases.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {;
				
			phasesInternalFrame.setVisible(false);
				
			}
		});
    	btnSavePhases.setBounds(93, 230, 95, 20);  	
    	btnSavePhases.setFocusPainted(false);
    	btnSavePhases.setContentAreaFilled(false);
    	phasesInternalFrame.add(btnSavePhases);
    	
    	//Register panel exiting
    	btnExitPhases.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {;
				
				phasesInternalFrame.setVisible(false);
				
			}
		});
    	btnExitPhases.setBounds(23, 230, 60, 20); 
    	btnExitPhases.setFocusPainted(false);
    	btnExitPhases.setContentAreaFilled(false);
    	phasesInternalFrame.add(btnExitPhases);
		
    	phasesInternalFrame.setVisible(false);
	
	}
	
	public void CreateComponentCoursesInternalFrame() {
		
		coursesInternalFrame = new JInternalFrame("Cadastro de Curso");
		coursesInternalFrame.setLayout(null);
		coursesInternalFrame.setBounds(200, 80, 210, 280);
		coursesInternalFrame.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.5f)));
		
		//Registering Panel Buttons declarations
		JButton btnSaveCourses = new JButton("Salvar");
		JButton btnExitCourses = new JButton("Sair");
		
		//Registering Panel Fields declarations
		JLabel lbCourseName;
		JTextField txfCourseName; 
		JLabel lbCourseCode;
		JTextField txfCourseCode;
		
    	lbCourseCode= new JLabel();
    	lbCourseCode.setText("Codigo:");
    	lbCourseCode.setBounds(40, 40, 125, 20);
    	coursesInternalFrame.add(lbCourseCode);
    	
    	txfCourseCode= new JTextField();
    	txfCourseCode.setBounds(40, 60, 125, 20);
    	coursesInternalFrame.add(txfCourseCode);
    	
    	lbCourseName= new JLabel();
    	lbCourseName.setText("Nome:");
    	lbCourseName.setBounds(40, 85, 125, 20);
    	coursesInternalFrame.add(lbCourseName);
    	
    	txfCourseName= new JTextField();
    	txfCourseName.setBounds(40, 105, 125, 20);
    	coursesInternalFrame.add(txfCourseName); 		
    	
    	//Register panel saving
    	btnSaveCourses.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {;
				
			coursesInternalFrame.setVisible(false);
				
			}
		});
    	btnSaveCourses.setBounds(93, 230, 95, 20);  	
    	btnSaveCourses.setFocusPainted(false);
    	btnSaveCourses.setContentAreaFilled(false);
    	coursesInternalFrame.add(btnSaveCourses);
    	
    	//Register panel exiting
    	btnExitCourses.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {;
				
				coursesInternalFrame.setVisible(false);
				
			}
		});
    	btnExitCourses.setBounds(23, 230, 60, 20); 
    	btnExitCourses.setFocusPainted(false);
    	btnExitCourses.setContentAreaFilled(false);
    	coursesInternalFrame.add(btnExitCourses);
		
    	coursesInternalFrame.setVisible(false);
	
	}
	
	public void CreateStudentsTable(){
		
		//Declaring Table Model
		DefaultTableModel studentTableModel = new DefaultTableModel() {
			
			String[] estudante = {"Id","Estudante","Data de Nascimento","E-Mail","Sexo","Telefone",
					  "Celular","CEP","Número", "Endereço", "bairro", "Cidade","Estado","Complemento",
					  "Observação"};
			
	        public int getColumnCount() { 
	            return estudante.length; 
	        } 
	        
	 		@Override
			public String getColumnName(int index) {
			    return estudante[index];
			}
			
		};
		
		//Declaring Table and Scroll pane
		JTable studentTable;
		JScrollPane studentScrollPane;
		
		//Table Configuration
		studentTable = new JTable(studentTableModel);	    		    
		studentTable.setBounds(1, 1, 539, 399);
		studentTable.setEnabled(false);
		
		//Scroll Pane Configuration
		studentScrollPane = new JScrollPane(studentTable);
		studentScrollPane.setBounds(50, 100, 1300, 600);	
		
	    studentsPanel.add(studentScrollPane);
	    
	    studentTable.setVisible(true);
	    
	}

	public void CreateUsersTable(){
		
		//Declaring Table Model
		DefaultTableModel usersTableModel = new DefaultTableModel() {
			
			String[] usuario = {"UsuÃ¡rio", "Senha", "Perfil"};
			
	        @Override 
	        public int getColumnCount() { 
	            return usuario.length; 
	        } 
	        
	 		@Override
			public String getColumnName(int index) {
			    return usuario[index];
			}
			
		};
		
		//Declaring Table and Scroll pane
		JTable usersTable;
		JScrollPane usersScrollPane;
		
		//Table Configuration
		usersTable = new JTable(usersTableModel);	    		    
		usersTable.setBounds(1, 1, 539, 399);
		usersTable.setEnabled(false);
		
		//Scroll Pane Configuration
		usersScrollPane = new JScrollPane(usersTable);
		usersScrollPane.setBounds(50, 100, 1300, 600);	
		
	    usersPanel.add(usersScrollPane);
	    
	    usersTable.setVisible(true);
	    
	}

	public void CreateCitiesTable(){
		
		//Declaring Table Model
		DefaultTableModel citiesTableModel = new DefaultTableModel() {
			
			String[] cidade = {"Cidade", "Pais", "Estado"};
			
			public int getColumnCount() { 
			
				return cidade.length; 
				
			} 
			
			@Override
			public String getColumnName(int index) {
			
				return cidade[index];
				
			}
			
		};
		
		//Declaring Table and Scroll pane
		JTable citiesTable;
		JScrollPane citiesScrollPane;
		
		//Table Configuration
		citiesTable = new JTable(citiesTableModel);	    		    
		citiesTable.setBounds(1, 1, 539, 399);
		citiesTable.setEnabled(false);
		
		//Scroll Pane Configuration
		citiesScrollPane = new JScrollPane(citiesTable);
		citiesScrollPane.setBounds(50, 100, 1300, 600);	
		
	    citiesPanel.add(citiesScrollPane);
	    
	    citiesTable.setVisible(true);
	    
	}
	
	public void ShowPanel(String PanelName){
		
		switch(PanelName) {
		
			case "students":
				
				studentsPanel.setVisible(true);
				citiesPanel.setVisible(false);
				usersPanel.setVisible(false);
				teachersPanel.setVisible(false);
				phasesPanel.setVisible(false);
				coursesPanel.setVisible(false);
				subjectsPanel.setVisible(false);
				
			break;
			
			case "cities":
				
				studentsPanel.setVisible(false);
				citiesPanel.setVisible(true);
				usersPanel.setVisible(false);
				teachersPanel.setVisible(false);
				phasesPanel.setVisible(false);
				coursesPanel.setVisible(false);
				subjectsPanel.setVisible(false);
				
			break;
			
			case "users":
				
				studentsPanel.setVisible(false);
				citiesPanel.setVisible(false);
				usersPanel.setVisible(true);
				teachersPanel.setVisible(false);
				phasesPanel.setVisible(false);
				coursesPanel.setVisible(false);
				subjectsPanel.setVisible(false);
				
			break;
			
			case "teachers":
				
				studentsPanel.setVisible(false);
				citiesPanel.setVisible(false);
				usersPanel.setVisible(false);
				teachersPanel.setVisible(true);
				phasesPanel.setVisible(false);
				coursesPanel.setVisible(false);
				subjectsPanel.setVisible(false);
				
			break;
			
			case "phases":
				
				studentsPanel.setVisible(false);
				citiesPanel.setVisible(false);
				usersPanel.setVisible(false);
				teachersPanel.setVisible(false);
				phasesPanel.setVisible(true);
				coursesPanel.setVisible(false);
				subjectsPanel.setVisible(false);
				
			break;
			
			case "courses":
				
				studentsPanel.setVisible(false);
				citiesPanel.setVisible(false);
				usersPanel.setVisible(false);
				teachersPanel.setVisible(false);
				phasesPanel.setVisible(false);
				coursesPanel.setVisible(true);
				subjectsPanel.setVisible(false);
				
			break;
			
			case "subjects":
				
				studentsPanel.setVisible(false);
				citiesPanel.setVisible(false);
				usersPanel.setVisible(false);
				teachersPanel.setVisible(false);
				phasesPanel.setVisible(false);
				coursesPanel.setVisible(false);
				subjectsPanel.setVisible(true);
				
			break;
			
			default:
				
				 JOptionPane.showMessageDialog(studentsPanel, "Parametro errado, codificador.");
				
		}

	}

	public void HidePanel(String PanelName){
		
		switch(PanelName) {
		
			case "students":
				
				studentsPanel.setVisible(false);
				
			break;
			
			case "cities":
				
				citiesPanel.setVisible(false);
				
			break;
			
			case "users":
				
				usersPanel.setVisible(false);
				
			break;
			
			case "teachers":
				
				teachersPanel.setVisible(false);
				
			break;
			
			case "phases":
				
				phasesPanel.setVisible(false);
				
			break;
			
			case "courses":
				
				coursesPanel.setVisible(false);
				
			break;
			
			case "subjects":
				
				subjectsPanel.setVisible(false);
				
			break;
			
			default:
				
				 JOptionPane.showMessageDialog(studentsPanel, "Parametro errado, codificador.");
				
		}
	

	}
	
}
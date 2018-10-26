package com.gohorse.view;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
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
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.gohorse.database.model.Cities;
import com.gohorse.database.model.Courses;
import com.gohorse.database.model.FileImported;
import com.gohorse.database.model.Phases;
import com.gohorse.database.model.Subjects;
import com.gohorse.database.model.Students;
import com.gohorse.database.model.Teachers;
import com.gohorse.database.model.Users;
import com.gohorse.database.service.CitiesService;
import com.gohorse.database.service.CoursesService;
import com.gohorse.database.service.PhasesService;
import com.gohorse.database.service.SubjectsService;
import com.gohorse.database.service.StudentsService;
import com.gohorse.database.service.TeachersService;
import com.gohorse.database.service.UsersService;
import com.gohorse.lib.Import;

public class MainWindowNew extends JFrame {
	
	//Define if table is editable or not
	private boolean editable = false;
	
	// Saving screen size in variable
	public static boolean isFullScreen;
	Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();     

	// Declaring Panels
	private JPanel studentsPanel;
	private JPanel teachersPanel;
	private JPanel subjectsPanel;
	private JPanel phasesPanel;
	private JPanel coursesPanel;
	private JPanel citiesPanel;
	private JPanel usersPanel;
	private JPanel importerPanel;

	//Declaring Tables
	private JTable studentTable;
	private JTable teacherTable;
	private JTable subjectTable;
	private JTable phaseTable;
	private JTable courseTable;
	private JTable citiesTable;
	private JTable usersTable;
	
	//Declaring Tables Models
	private DefaultTableModel studentTableModel;
	private DefaultTableModel teacherTableModel;
	private DefaultTableModel subjectTableModel;
	private DefaultTableModel phaseTableModel;
	private DefaultTableModel courseTableModel;
	private DefaultTableModel citiesTableModel;
	private DefaultTableModel usersTableModel;

	// Declaring internal frames
	private JInternalFrame configInternalFrame;
	private JInternalFrame studentsInternalFrame;
	private JInternalFrame teachersInternalFrame;
	private JInternalFrame subjectsInternalFrame;
	private JInternalFrame phasesInternalFrame;
	private JInternalFrame coursesInternalFrame;
	private JInternalFrame citiesInternalFrame;
	private JInternalFrame usersInternalFrame;	
	
	//Define perfil of user
	private String perfil; 
	
	
	//Declaring Importer components
	private JLabel labelDescricao;
	private JTextField txfData;
	private JTextField txfFaseInicial;
	private JTextField txfFaseFinal;
	private JTextField txfCurso;
	private JTextField txfImportacao;
	private JButton btnSelecionarArquivo;
	private JButton btnImportarArquivo;

	private JList listFases;
	private JList listDisciplinas;
	private JList listProfessores;
	
	private DefaultListModel modelFases = new DefaultListModel();
	private DefaultListModel modelDisciplinas = new DefaultListModel();
	private DefaultListModel modelProfessores = new DefaultListModel();
	
	private String caminho = "";
	
	
	//JFrame constructor

	public MainWindowNew (String perfil) {
		
		
		this.perfil = perfil;
		
		//Setting window size depending on IsFullscreen
		if(isFullScreen == true) {          
			ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
			setExtendedState(Frame.MAXIMIZED_BOTH);               
		}                     
		else {          
			ScreenSize.setSize((ScreenSize.getWidth()*0.7),(ScreenSize.getHeight()*0.7));
			setSize(ScreenSize.width, ScreenSize.height);               
		}                                       

		//Setting up main JFrame
		setTitle("Menu");
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);    
		
		setUndecorated(true);
		Color color = UIManager.getColor("activeCaptionBorder");
		getRootPane().setBorder(BorderFactory.createLineBorder(color, 6));

		//Creating visual components
		CreateTopBarComponents();
		CreateConfigInternalFrame();
		FillComponentsInStudentsPanel();
		FillComponentsInCitiesPanel();
		FillComponentsInUsersPanel();
		FillComponentsInTeachersPanel();
		//FillComponentsInPhasesPanel();
		FillComponentsInCoursesPanel();
		FillComponentsInSubjectsPanel();                              
		FillComponentsInImporterPanel();

	}

	//Top bar - CREATE AND FILL

	public void CreateTopBarComponents() {

		//declaring top bar objects
		JMenuBar menu;
		JMenu mStudents;
		JMenu mCities;
		JMenu mUsers;
		JMenu mTeachers;
		JMenu mPhases;
		JMenu mCourses;
		JMenu mSubjects;
		JMenu mOptions;
		JMenu mUtilities;
		JMenuItem smListCities;
		JMenuItem smListStudents;
		JMenuItem smListUsers;
		JMenuItem smListPhases;
		JMenuItem smListTeachers;
		JMenuItem smListCourses;
		JMenuItem smListSubjects;
		JMenuItem smSoftwareInfo;
		JMenuItem smConfig;
		JMenuItem smImporter;
		JMenuItem smLogoff;
		JMenuItem smEndProgram;
		

		menu = new JMenuBar();
		setJMenuBar(menu);

		mStudents  = new JMenu("Alunos");
		mTeachers  = new JMenu("Professores");
		mSubjects  = new JMenu("Disciplinas");
		mPhases    = new JMenu("Fases");
		mCourses   = new JMenu("Cursos");       
		mCities    = new JMenu("Cidades");

		mOptions   = new JMenu("Opcoes");
		mUsers     = new JMenu("Usuarios");  
		mUtilities = new JMenu("Utilidades");
        
		menu.add(mOptions);
		menu.add(mStudents);
		menu.add(mTeachers);
		menu.add(mSubjects);
		//menu.add(mPhases);
		menu.add(mCourses);
		menu.add(mCities);
		menu.add(mUtilities);

		if (perfil.equals("Administrador")) {
			menu.add(mUsers);
		}
		
		//setting menu option actions
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
		
		smSoftwareInfo = new JMenuItem(new AbstractAction("Sobre") {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, "Criado por:\n\nBruno Firme\nRoger Vieira\nJackson Belloli\nRomulo Ramos\nGabriel Fernandes\nJunior Topanotti\nFelipe Alves\n\nUNESC - 2018");

			}
		});

		smConfig = new JMenuItem(new AbstractAction("Configuracoes") {

			@Override
			public void actionPerformed(ActionEvent e) {

				configInternalFrame.setVisible(true);

			}
		});

		smImporter = new JMenuItem(new AbstractAction("Importar") {

			@Override
			public void actionPerformed(ActionEvent e) {

				ShowPanel("importer");
				importerPanel.setVisible(true);

			}
		});

		smLogoff = new JMenuItem(new AbstractAction("Logoff") {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				new LoginWindow().setVisible(true);
				
			}
			
			
		});
		
		smEndProgram = new JMenuItem(new AbstractAction("Encerrar") {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				int DialogResult = JOptionPane.showConfirmDialog(configInternalFrame, "Encerrar mesmo o programa?", "Aww", JOptionPane.YES_NO_OPTION);
				if (DialogResult == JOptionPane.YES_OPTION) {      

					dispose(); 					

				}
				
			}
		
			
		});
		
		//adding options to menus on bar
		mStudents.add(smListStudents);
		mCities.add(smListCities);
		mCourses.add(smListCourses);
		mPhases.add(smListPhases);
		mSubjects.add(smListSubjects);
		mTeachers.add(smListTeachers);
		mUsers.add(smListUsers);
		mOptions.add(smConfig);
		mOptions.add(smSoftwareInfo);
		mOptions.add(smLogoff);
		mOptions.add(smEndProgram);
		mUtilities.add(smImporter);

	}

	//Panels - CREATE AND FILL

	public void FillComponentsInStudentsPanel() {

		CreateComponentStudentsInternalFrame();

		//Students Panel Declaration
		studentsPanel = new JPanel();
		studentsPanel.setLayout(null);
		studentsPanel.setSize(ScreenSize.width, ScreenSize.height);
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
				
				if (studentTable.getRowCount() == 0) {
					
					return;
				
				}
				
				JOptionPane.showMessageDialog(null, "Para editar um dado clique duas vezes sobre a c�lula dele na tabela! \n Depois de editado precione enter duas vezes! \n Pressione qualquer tecla para sair!", null, JOptionPane.WARNING_MESSAGE);
					
					btnDeleteStudents.setEnabled(false);
					btnRegisterStudents.setEnabled(false);
					
					studentTable.setEnabled(true);				
					editable = true;
					UpdateRowsStudentsTable();			
				
				
					studentTable.addKeyListener(new KeyAdapter() {
				
					public void keyPressed(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							
							StudentsService sts = new StudentsService();
							 							
							try {
								Collection<Students> StudentList = sts.findAll();
								
								if (StudentList == null) {
									return;
								}
								
								for (Students st : StudentList) {
									if (st.getId() == (Integer) studentTable.getValueAt(studentTable.getSelectedRow(), 0)) {									
										
										st.setId((Integer) studentTable.getValueAt(studentTable.getSelectedRow(), 0));
										st.setName((String) studentTable.getValueAt(studentTable.getSelectedRow(), 1));
										st.setBirthdate((String) studentTable.getValueAt(studentTable.getSelectedRow(), 2));
										st.setEmail((String) studentTable.getValueAt(studentTable.getSelectedRow(), 3));
										st.setSex((char) studentTable.getValueAt(studentTable.getSelectedRow(), 4));
										st.setPhone((String) studentTable.getValueAt(studentTable.getSelectedRow(), 5));
										st.setCellphone((String) studentTable.getValueAt(studentTable.getSelectedRow(), 6));
										st.setCep((String) studentTable.getValueAt(studentTable.getSelectedRow(), 7));
										st.setNumber((String) studentTable.getValueAt(studentTable.getSelectedRow(), 8));
										st.setAddress((String) studentTable.getValueAt(studentTable.getSelectedRow(), 9));
										st.setSuburb((String) studentTable.getValueAt(studentTable.getSelectedRow(), 10));
										st.setCity((String) studentTable.getValueAt(studentTable.getSelectedRow(), 11));
										st.setEstate((String) studentTable.getValueAt(studentTable.getSelectedRow(), 12));
										st.setComplement((String) studentTable.getValueAt(studentTable.getSelectedRow(), 13));
										st.setNote((String) studentTable.getValueAt(studentTable.getSelectedRow(), 14));
										
										
										sts.update(st);
										
										editable = false;
										UpdateRowsStudentsTable();
										
										btnDeleteStudents.setEnabled(true);
										btnRegisterStudents.setEnabled(true);
										studentTable.setEnabled(false);										
										continue;
									}
								}
							} catch (Exception e2) {
								e2.printStackTrace();
								JOptionPane.showMessageDialog(null,"Opa..., um erro inesperado aconteceu, contate o suporte!", "Erro", JOptionPane.ERROR_MESSAGE);
							}
							
						}else if (e.getKeyCode() != KeyEvent.VK_ENTER) {
							
							editable = false;					
							UpdateRowsStudentsTable();
							
							btnDeleteStudents.setEnabled(true);
							btnRegisterStudents.setEnabled(true);
							studentTable.setEnabled(false);
							
							return;
						}
					}
				});
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
				
				if (studentTable.getRowCount() == 0) {
					return;
				}
				
				JOptionPane.showMessageDialog(null, "Para deletar um dado selecione a linha dele na tabela! \n Depois precione Delete! \n Pressione qualquer tecla para sair!", null, JOptionPane.WARNING_MESSAGE);
				
				btnEditStudents.setEnabled(false);
				btnRegisterStudents.setEnabled(false);
				
				studentTable.setEnabled(true);				
				
				studentTable.addKeyListener(new KeyAdapter() {
				
					public void keyPressed(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_DELETE) {
							
							StudentsService sts = new StudentsService();
							
							try {
								Collection<Students> StudentList = sts.findAll();
								
								if (StudentList == null) {
									return;
								}
								
								for (Students st : StudentList) {
									if (st.getId() == (Integer) studentTableModel.getValueAt(studentTable.getSelectedRow(), 0)) {
										
										sts.delete(st.getId());
										
										btnEditStudents.setEnabled(true);
										btnRegisterStudents.setEnabled(true);
										studentTable.setEnabled(false);
										
										UpdateRowsStudentsTable();

										continue;
									}
								}
							} catch (Exception e2) {
								e2.printStackTrace();
								JOptionPane.showMessageDialog(null,"Opa..., um erro inesperado aconteceu, contate o suporte!", "Erro", JOptionPane.ERROR_MESSAGE);
							}
							
							
						}else if (e.getKeyCode() != KeyEvent.VK_DELETE) {
							btnEditStudents.setEnabled(true);
							btnRegisterStudents.setEnabled(true);
							studentTable.setEnabled(false);
							return;
						}
					}
				});
			}
		});
		btnDeleteStudents.setBounds(370, 30, 150, 40);  
		btnDeleteStudents.setFocusPainted(false);
		btnDeleteStudents.setContentAreaFilled(false);
		studentsPanel.add(btnDeleteStudents);

		studentsPanel.setVisible(false);

	}

	public void FillComponentsInTeachersPanel() {

		CreateComponentTeachersInternalFrame();

		//Teachers Panel Declaration
		teachersPanel = new JPanel();
		teachersPanel.setLayout(null);
		teachersPanel.setSize(ScreenSize.width, ScreenSize.height);
		getContentPane().add(teachersPanel);

		//Adding Internal frame to Panel
		teachersPanel.add(teachersInternalFrame);
		CreateTeacherTable();

		//Main Panel Buttons
		JButton btnRegisterTeachers = new JButton("Cadastrar Professores");
		JButton btnEditTeachers = new JButton("Editar Professores");
		JButton btnDeleteTeachers = new JButton("Deletar Professores");

		//Teachers Registering
		btnRegisterTeachers.addActionListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {                            

				teachersInternalFrame.setVisible(true);

			}

		});
		btnRegisterTeachers.setBounds(50, 30, 170, 40);     
		btnRegisterTeachers.setFocusPainted(false);
		btnRegisterTeachers.setContentAreaFilled(false);
		teachersPanel.add(btnRegisterTeachers);

		//Teachers Editing 
		btnEditTeachers.addActionListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
               if (teacherTable.getRowCount() == 0) {
					
					return;
				
				}
				
               JOptionPane.showMessageDialog(null, "Para editar um dado clique duas vezes sobre a c�lula dele na tabela! \n Depois de editado precione enter duas vezes! \n Pressione qualquer tecla para sair!", null, JOptionPane.WARNING_MESSAGE);
               
					btnDeleteTeachers.setEnabled(false);
					btnRegisterTeachers.setEnabled(false);
					
					teacherTable.setEnabled(true);				
					editable = true;
					UpdateRowsTeachersTable();			
				
				
					teacherTable.addKeyListener(new KeyAdapter() {
				
					public void keyPressed(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							
							TeachersService tcs = new TeachersService();
							 							
							try {
								Collection<Teachers> TeacherList = tcs.findAll();
								
								if (TeacherList == null) {
									return;
								}
								
								for (Teachers tc : TeacherList) {
									if (tc.getId() == (int) teacherTable.getValueAt(teacherTable.getSelectedRow(), 0)) {									
										
										tc.setId((int) teacherTable.getValueAt(teacherTable.getSelectedRow(), 0));
										tc.setName((String) teacherTable.getValueAt(teacherTable.getSelectedRow(), 1));						
										tc.setGraduation((Integer) teacherTable.getValueAt(teacherTable.getSelectedRow(), 2));										
										
										tcs.update(tc);
										
										editable = false;
										
										UpdateRowsTeachersTable();
										btnDeleteTeachers.setEnabled(true);
										btnRegisterTeachers.setEnabled(true);
										teacherTable.setEnabled(false);										
										continue;
									}
								}
							} catch (Exception e2) {
								e2.printStackTrace();
								UpdateRowsTeachersTable();
								JOptionPane.showMessageDialog(null,"Opa..., um erro inesperado aconteceu, contate o suporte!", "Erro", JOptionPane.ERROR_MESSAGE);
							}
							
						}else if (e.getKeyCode() != KeyEvent.VK_ENTER) {
							
							editable = false;					
							UpdateRowsTeachersTable();
							
							btnDeleteTeachers.setEnabled(true);
							btnRegisterTeachers.setEnabled(true);
							teacherTable.setEnabled(false);
							
							return;
						}
					}
				});

			}

		});
		btnEditTeachers.setBounds(230, 30, 170, 40);        
		btnEditTeachers.setFocusPainted(false);
		btnEditTeachers.setContentAreaFilled(false);
		teachersPanel.add(btnEditTeachers);

		//Teachers deleting
		btnDeleteTeachers.addActionListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
                
				if (teacherTable.getRowCount() == 0) {
					return;
				}
				
				JOptionPane.showMessageDialog(null, "Para deletar um dado selecione a linha dele na tabela! \n Depois precione Delete! \n Pressione qualquer tecla para sair!", null, JOptionPane.WARNING_MESSAGE);
				
				btnEditTeachers.setEnabled(false);
				btnRegisterTeachers.setEnabled(false);
				teacherTable.setEnabled(true);	
                
				teacherTable.addKeyListener(new KeyAdapter() {
					
					public void keyPressed(KeyEvent e) {
						
						if (e.getKeyCode() == KeyEvent.VK_DELETE) {
							
							TeachersService tcs = new TeachersService();
							 							
							try {
								
								Collection<Teachers> TeacherList = tcs.findAll();
								
								if (TeacherList == null) {
									return;
								}
								
								for (Teachers tc : TeacherList) {

									if (tc.getId() == (Integer) teacherTable.getValueAt(teacherTable.getSelectedRow(), 0)) {
										
										tcs.delete(tc.getId());
										
										UpdateRowsTeachersTable();
																			
										btnEditTeachers.setEnabled(true);
										btnRegisterTeachers.setEnabled(true);
										teacherTable.setEnabled(false);
										break;
									}
								}
							} catch (Exception e2) {
								e2.printStackTrace();
								JOptionPane.showMessageDialog(null,"Opa..., um erro inesperado aconteceu, contate o suporte!", "Erro", JOptionPane.ERROR_MESSAGE);
							}
							
							
						}else if(e.getKeyCode() != KeyEvent.VK_DELETE) {
							btnEditTeachers.setEnabled(true);
							btnRegisterTeachers.setEnabled(true);
							teacherTable.setRowSelectionAllowed(false);
							teacherTable.setEnabled(false);
							return;
						}
					}
				});
			}

		});
		btnDeleteTeachers.setBounds(410, 30, 170, 40);  
		btnDeleteTeachers.setFocusPainted(false);
		btnDeleteTeachers.setContentAreaFilled(false);
		teachersPanel.add(btnDeleteTeachers);

		teachersPanel.setVisible(false);

	}

	public void FillComponentsInSubjectsPanel() {

		CreateComponentSubjectsInternalFrame();

		//Subjects Panel Declaration
		subjectsPanel = new JPanel();
		subjectsPanel.setLayout(null);
		subjectsPanel.setSize(ScreenSize.width, ScreenSize.height);
		getContentPane().add(subjectsPanel);

		//Adding Internal frame to Panel
		subjectsPanel.add(subjectsInternalFrame);
		CreateSubjectTable();

		//Main Panel Buttons
		JButton btnRegisterSubjects = new JButton("Cadastrar Disciplina");
		JButton btnEditSubjects = new JButton("Editar Disciplina");
		JButton btnDeleteSubjects = new JButton("Deletar Disciplina");

		//Subjects Registering
		btnRegisterSubjects.addActionListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {                            

				subjectsInternalFrame.setVisible(true);

			}

		});
		btnRegisterSubjects.setBounds(50, 30, 150, 40);   
		btnRegisterSubjects.setFocusPainted(false);
		btnRegisterSubjects.setContentAreaFilled(false);
		subjectsPanel.add(btnRegisterSubjects);

		//Subjects Editing 
		btnEditSubjects.addActionListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (subjectTable.getRowCount() == 0) {
					
					return;
				
				}
					
				JOptionPane.showMessageDialog(null, "Para editar um dado clique duas vezes sobre a c�lula dele na tabela! \n Depois de editado precione enter duas vezes! \n Pressione qualquer tecla para sair!", null, JOptionPane.WARNING_MESSAGE);
				
					btnDeleteSubjects.setEnabled(false);
					btnRegisterSubjects.setEnabled(false);
					
					subjectTable.setEnabled(true);				
					editable = true;
					UpdateRowsSubjectsTable();			
				
				
					subjectTable.addKeyListener(new KeyAdapter() {
				
					public void keyPressed(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							
							SubjectsService sbs = new SubjectsService();
							 							
							try {
								Collection<Subjects> SubjectList = sbs.findAll();
								
								if (SubjectList == null) {
									return;
								}
								
								for (Subjects sb : SubjectList) {
									if (sb.getCode() == (Integer) subjectTable.getValueAt(subjectTable.getSelectedRow(), 0)) {									
										System.out.println("Passou aqui");
										sb.setCode((Integer) subjectTable.getValueAt(subjectTable.getSelectedRow(), 0));
										sb.setname((String) subjectTable.getValueAt(subjectTable.getSelectedRow(), 1));
										sb.setweek_day(Integer.parseInt((String) subjectTable.getValueAt(subjectTable.getSelectedRow(), 2)));									
										
										
										sbs.update(sb);
										
										editable = false;
										UpdateRowsStudentsTable();
										
										btnDeleteSubjects.setEnabled(true);
										btnRegisterSubjects.setEnabled(true);
										subjectTable.setRowSelectionAllowed(false);
										subjectTable.setEnabled(false);										
										continue;
									}
								}
							} catch (Exception e2) {
								e2.printStackTrace();
								JOptionPane.showMessageDialog(null,"Opa..., um erro inesperado aconteceu, contate o suporte!", "Erro", JOptionPane.ERROR_MESSAGE);
							}
							
						}else if (e.getKeyCode() != KeyEvent.VK_ENTER) {
							
							editable = false;					
							UpdateRowsStudentsTable();
							
							btnDeleteSubjects.setEnabled(true);
							btnRegisterSubjects.setEnabled(true);
							subjectTable.setRowSelectionAllowed(false);
							subjectTable.setEnabled(false);
							
							return;
						}
					}
				});

			}

		});
		btnEditSubjects.setBounds(210, 30, 150, 40);        
		btnEditSubjects.setFocusPainted(false);
		btnEditSubjects.setContentAreaFilled(false);
		subjectsPanel.add(btnEditSubjects);

		//Subjects deleting
		btnDeleteSubjects.addActionListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (subjectTable.getRowCount() == 0) {
					return;
				}
				
				JOptionPane.showMessageDialog(null, "Para deletar um dado selecione a linha dele na tabela! \n Depois precione Delete! \n Pressione qualquer tecla para sair!", null, JOptionPane.WARNING_MESSAGE);
				
				btnEditSubjects.setEnabled(false);
				btnRegisterSubjects.setEnabled(false);
				subjectTable.setEnabled(true);	
                
				subjectTable.addKeyListener(new KeyAdapter() {
					
					public void keyPressed(KeyEvent e) {
						
						if (e.getKeyCode() == KeyEvent.VK_DELETE) {
							
							SubjectsService sbs = new SubjectsService();
							 							
							try {
								
								Collection<Subjects> SubjectList = sbs.findAll();
								
								if (SubjectList == null) {
									return;
								}
								
								for (Subjects sb : SubjectList) {

									if (sb.getCode() == (Integer) subjectTable.getValueAt(subjectTable.getSelectedRow(), 0)) {
										
										sbs.delete(sb.getId());
										UpdateRowsSubjectsTable();
										btnEditSubjects.setEnabled(true);
										btnRegisterSubjects.setEnabled(true);
										subjectTable.setEnabled(false);
										break;
									}
								}
							} catch (Exception e2) {
								e2.printStackTrace();
								JOptionPane.showMessageDialog(null,"Opa..., um erro inesperado aconteceu, contate o suporte!", "Erro", JOptionPane.ERROR_MESSAGE);
							}
							
							
						}else if(e.getKeyCode() != KeyEvent.VK_DELETE) {
							btnEditSubjects.setEnabled(true);
							btnRegisterSubjects.setEnabled(true);
							subjectTable.setRowSelectionAllowed(false);
							subjectTable.setEnabled(false);
							return;
						}
					}
				});

			}

		});
		btnDeleteSubjects.setBounds(370, 30, 150, 40);  
		btnDeleteSubjects.setFocusPainted(false);
		btnDeleteSubjects.setContentAreaFilled(false);
		subjectsPanel.add(btnDeleteSubjects);

		subjectsPanel.setVisible(false);

	}

	/*public void FillComponentsInPhasesPanel(){ 

		CreateComponentPhasesInternalFrame();

		//Phases Panel Declaration
		phasesPanel = new JPanel();
		phasesPanel.setLayout(null);
		phasesPanel.setSize(ScreenSize.width, ScreenSize.height);
		getContentPane().add(phasesPanel);

		//Adding Internal frame to Panel
		phasesPanel.add(phasesInternalFrame);

		//Main Panel Buttons
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
		btnRegisterPhases.setBounds(50, 30, 150, 40);   
		btnRegisterPhases.setFocusPainted(false);
		btnRegisterPhases.setContentAreaFilled(false);
		phasesPanel.add(btnRegisterPhases);

		//Phases Editing 
		btnEditPhases = new JButton(new AbstractAction("Editar Fases") {

			@Override
			public void actionPerformed(ActionEvent e) {


			}

		});
		btnEditPhases.setBounds(210, 30, 150, 40);      
		btnEditPhases.setFocusPainted(false);
		btnEditPhases.setContentAreaFilled(false);
		phasesPanel.add(btnEditPhases);

		//Phases deleting
		btnDeletePhases = new JButton(new AbstractAction("Deletar Fases") {

			@Override
			public void actionPerformed(ActionEvent e) {


			}

		});
		btnDeletePhases.setBounds(370, 30, 150, 40);    
		btnDeletePhases.setFocusPainted(false);
		btnDeletePhases.setContentAreaFilled(false);
		phasesPanel.add(btnDeletePhases);

		phasesPanel.setVisible(false);

	}*/

	public void FillComponentsInCoursesPanel(){

		CreateComponentCoursesInternalFrame();

		//Courses Panel Declaration
		coursesPanel = new JPanel();
		coursesPanel.setLayout(null);
		coursesPanel.setSize(ScreenSize.width, ScreenSize.height);
		getContentPane().add(coursesPanel);

		//Adding Internal frame to Panel
		coursesPanel.add(coursesInternalFrame);
		CreateCourseTable();

		//Main Panel Buttons
		JButton btnRegisterCourses = new JButton("Cadastrar Curso");
		JButton btnEditCourses = new JButton("Editar Curso");
		JButton btnDeleteCourses = new JButton("Deletar Curso");

		//Courses Registering
		btnRegisterCourses.addActionListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {                            

				coursesInternalFrame.setVisible(true);

			}

		});
		btnRegisterCourses.setBounds(50, 30, 150, 40);   
		btnRegisterCourses.setFocusPainted(false);
		btnRegisterCourses.setContentAreaFilled(false);
		coursesPanel.add(btnRegisterCourses);

		//Courses Editing 
		btnEditCourses.addActionListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (courseTable.getRowCount() == 0) {
					
					return;
				
				}
					
				JOptionPane.showMessageDialog(null, "Para editar um dado clique duas vezes sobre a c�lula dele na tabela! \n Depois de editado precione enter duas vezes! \n Pressione qualquer tecla para sair!", null, JOptionPane.WARNING_MESSAGE);
				
					btnDeleteCourses.setEnabled(false);
					btnRegisterCourses.setEnabled(false);
					
					courseTable.setEnabled(true);				
					editable = true;
					UpdateRowsCoursesTable();			
				
				
					courseTable.addKeyListener(new KeyAdapter() {
				
					public void keyPressed(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							
							CoursesService crs = new CoursesService();
							 							
							try {
								Collection<Courses> CourseList = crs.findAll();
								
								if (CourseList == null) {
									return;
								}
								
								for (Courses cr : CourseList) {
									if (cr.getId() == (Integer) courseTable.getValueAt(courseTable.getSelectedRow(), 0)) {									
										
										cr.setCode((Integer) courseTable.getValueAt(courseTable.getSelectedRow(), 0));
										cr.setName((String) courseTable.getValueAt(courseTable.getSelectedRow(), 1));
																			
										
										
										crs.update(cr);
										
										editable = false;
										UpdateRowsCoursesTable();
										
										btnDeleteCourses.setEnabled(true);
										btnRegisterCourses.setEnabled(true);
										courseTable.setEnabled(false);										
										continue;
									}
								}
							} catch (Exception e2) {
								e2.printStackTrace();
								JOptionPane.showMessageDialog(null,"Opa..., um erro inesperado aconteceu, contate o suporte!", "Erro", JOptionPane.ERROR_MESSAGE);
							}
							
						}else if (e.getKeyCode() != KeyEvent.VK_ENTER) {
							
							editable = false;					
							UpdateRowsCoursesTable();
							
							btnDeleteCourses.setEnabled(true);
							btnRegisterCourses.setEnabled(true);
							courseTable.setEnabled(false);
							
							return;
						}
					}
				});
			}

		});
		btnEditCourses.setBounds(210, 30, 150, 40);         
		btnEditCourses.setFocusPainted(false);
		btnEditCourses.setContentAreaFilled(false);
		coursesPanel.add(btnEditCourses);

		//Courses deleting
		btnDeleteCourses.addActionListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (courseTable.getRowCount() == 0) {
					return;
				}
				
				JOptionPane.showMessageDialog(null, "Para deletar um dado selecione a linha dele na tabela! \n Depois precione Delete! \n Pressione qualquer tecla para sair!", null, JOptionPane.WARNING_MESSAGE);
				
				btnEditCourses.setEnabled(false);
				btnRegisterCourses.setEnabled(false);
				courseTable.setEnabled(true);	
                
				courseTable.addKeyListener(new KeyAdapter() {
					
					public void keyPressed(KeyEvent e) {
						
						if (e.getKeyCode() == KeyEvent.VK_DELETE) {
							
							CoursesService crs = new CoursesService();
							 							
							try {
								
								Collection<Courses> CoursesList = crs.findAll();
								
								if (CoursesList == null) {
									return;
								}
								
								for (Courses cr : CoursesList) {

									if (cr.getId() == (int) courseTable.getValueAt(courseTable.getSelectedRow(), 0)) {
										
										crs.delete(cr.getId());
										UpdateRowsCoursesTable();
										btnEditCourses.setEnabled(true);
										btnRegisterCourses.setEnabled(true);
										courseTable.setEnabled(false);
										break;
									}
								}
							} catch (Exception e2) {
								e2.printStackTrace();
								JOptionPane.showMessageDialog(null,"Opa..., um erro inesperado aconteceu, contate o suporte!", "Erro", JOptionPane.ERROR_MESSAGE);
							}
							
							
						}else if(e.getKeyCode() != KeyEvent.VK_DELETE) {
							btnEditCourses.setEnabled(true);
							btnRegisterCourses.setEnabled(true);
							courseTable.setEnabled(false);
							return;
						}
					}
				});

			}

		});
		btnDeleteCourses.setBounds(370, 30, 150, 40);   
		btnDeleteCourses.setFocusPainted(false);
		btnDeleteCourses.setContentAreaFilled(false);
		coursesPanel.add(btnDeleteCourses);

		coursesPanel.setVisible(false);

	}

	public void FillComponentsInCitiesPanel(){ 

		CreateComponentCitiesInternalFrame();

		//Cities Panel Declaration
		citiesPanel = new JPanel();
		citiesPanel.setLayout(null);
		citiesPanel.setSize(ScreenSize.width, ScreenSize.height);
		getContentPane().add(citiesPanel);

		//Adding Internal frame to Panel
		citiesPanel.add(citiesInternalFrame);   
		CreateCitiesTable();

		//Main Panel Buttons
		JButton btnRegisterCities = new JButton("Cadastrar Cidade");
		JButton btnEditCities = new JButton("Editar Cidade");
		JButton btnDeleteCities = new JButton("Deletar Cidade");

		//Cities Registering
		btnRegisterCities.addActionListener(new AbstractAction() {

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
		btnEditCities.addActionListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (citiesTable.getRowCount() == 0) {
					
					return;
				
				}
				
				JOptionPane.showMessageDialog(null, "Para editar um dado clique duas vezes sobre a c�lula dele na tabela! \n Depois de editado precione enter duas vezes! \n Pressione qualquer tecla para sair!", null, JOptionPane.WARNING_MESSAGE);
					
					btnDeleteCities.setEnabled(false);
					btnRegisterCities.setEnabled(false);
					
					citiesTable.setEnabled(true);				
					editable = true;
					UpdateRowsCitiesTable();			
				
				
					citiesTable.addKeyListener(new KeyAdapter() {
				
					public void keyPressed(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							
							CitiesService cts = new CitiesService();
							 							
							try {
								Collection<Cities> CitiesList = cts.findAll();
								
								if (CitiesList == null) {
									return;
								}
								
								for (Cities ct : CitiesList) {
									if (ct.getId() == (Integer) citiesTable.getValueAt(citiesTable.getSelectedRow(), 0)) {									
										
										ct.setId((int) citiesTable.getValueAt(citiesTable.getSelectedRow(), 0));
										ct.setName((String) citiesTable.getValueAt(citiesTable.getSelectedRow(), 1));
										ct.setState((String) citiesTable.getValueAt(citiesTable.getSelectedRow(), 2));
										ct.setCountry((String) citiesTable.getValueAt(citiesTable.getSelectedRow(), 3));
																			
										
										
										cts.update(ct);
										
										editable = false;
										UpdateRowsCoursesTable();
										
										
										btnDeleteCities.setEnabled(true);
										btnRegisterCities.setEnabled(true);
										citiesTable.setEnabled(false);										
										continue;
									}
								}
							} catch (Exception e2) {
								e2.printStackTrace();
								JOptionPane.showMessageDialog(null,"Opa..., um erro inesperado aconteceu, contate o suporte!", "Erro", JOptionPane.ERROR_MESSAGE);
							}
							
						}else if (e.getKeyCode() != KeyEvent.VK_ENTER) {
							
							editable = false;					
							UpdateRowsCitiesTable();
																				
							btnDeleteCities.setEnabled(true);
							btnRegisterCities.setEnabled(true);
							citiesTable.setEnabled(false);
							
							return;
						}
					}
				});
				
			}

		});
		btnEditCities.setBounds(210, 30, 150, 40);      
		btnEditCities.setFocusPainted(false);
		btnEditCities.setContentAreaFilled(false);
		citiesPanel.add(btnEditCities);

		//Cities deleting
		btnDeleteCities.addActionListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (citiesTable.getRowCount() == 0) {
					return;
				}
				
				JOptionPane.showMessageDialog(null, "Para deletar um dado selecione a linha dele na tabela! \n Depois precione Delete! \n Pressione qualquer tecla para sair!", null, JOptionPane.WARNING_MESSAGE);
				
				btnEditCities.setEnabled(false);
				btnRegisterCities.setEnabled(false);
				citiesTable.setEnabled(true);	
                
				citiesTable.addKeyListener(new KeyAdapter() {
					
					public void keyPressed(KeyEvent e) {
						
						if (e.getKeyCode() == KeyEvent.VK_DELETE) {
							
							CitiesService cts = new CitiesService();
							 							
							try {
								
								Collection<Cities> CitiesList = cts.findAll();
								
								if (CitiesList == null) {
									return;
								}
								
								for (Cities ct : CitiesList) {

									if (ct.getId() == (int) citiesTable.getValueAt(citiesTable.getSelectedRow(), 0)) {
										
										cts.delete(ct.getId());
										
										UpdateRowsCitiesTable();
										System.out.println("entrou");
										btnEditCities.setEnabled(true);
										btnRegisterCities.setEnabled(true);
										citiesTable.setEnabled(false);
										break;
									}
								}
							} catch (Exception e2) {
								e2.printStackTrace();
								UpdateRowsCitiesTable();
								JOptionPane.showMessageDialog(null,"Opa..., um erro inesperado aconteceu, contate o suporte!", "Erro", JOptionPane.ERROR_MESSAGE);
							}
							
							
						}else if(e.getKeyCode() != KeyEvent.VK_DELETE) {
							btnEditCities.setEnabled(true);
							btnRegisterCities.setEnabled(true);
							citiesTable.setEnabled(false);
							return;
						}
					}
				});
				
			}

		});
		btnDeleteCities.setBounds(370, 30, 150, 40);    
		btnDeleteCities.setFocusPainted(false);
		btnDeleteCities.setContentAreaFilled(false);
		citiesPanel.add(btnDeleteCities);

		citiesPanel.setVisible(false);

	}

	public void FillComponentsInUsersPanel(){  

		CreateComponentUsersInternalFrame();

		//Users Panel Declaration
		usersPanel = new JPanel();
		usersPanel.setLayout(null);
		usersPanel.setSize(ScreenSize.width, ScreenSize.height);
		getContentPane().add(usersPanel);

		//Adding Internal frame and Table to Panel
		usersPanel.add(usersInternalFrame); 
		CreateUsersTable();

		//Main Panel Buttons
		JButton btnRegisterUsers = new JButton("Cadastrar Usuario");
		JButton btnEditUsers = new JButton("Editar Usuario");
		JButton btnDeleteUsers = new JButton("Deletar Usuario");

		//Users Registering
		btnRegisterUsers.addActionListener(new AbstractAction() {

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
		btnEditUsers.addActionListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (usersTable.getRowCount() == 0) {
					
					return;
				
				}
					
				JOptionPane.showMessageDialog(null, "Para editar um dado clique duas vezes sobre a c�lula dele na tabela! \n Depois de editado precione enter duas vezes! \n Pressione qualquer tecla para sair!", null, JOptionPane.WARNING_MESSAGE);
				
					btnDeleteUsers.setEnabled(false);
					btnRegisterUsers.setEnabled(false);
					
					usersTable.setEnabled(true);				
					editable = true;
					UpdateRowsUsersTable();			
				
				
					usersTable.addKeyListener(new KeyAdapter() {
				
					public void keyPressed(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							
							UsersService uss = new UsersService();
							 							
							try {
								Collection<Users> UsersList = uss.findAll();
								
								if (UsersList == null) {
									return;
								}
								
								for (Users us : UsersList) {
									if (us.getId() == (Integer) usersTable.getValueAt(usersTable.getSelectedRow(), 0)) {									
										
										us.setId((int) usersTable.getValueAt(usersTable.getSelectedRow(), 0));
										us.setUser((String) usersTable.getValueAt(usersTable.getSelectedRow(), 1));
										us.setPassword((String) usersTable.getValueAt(usersTable.getSelectedRow(), 2));
										us.setPerfil((String) usersTable.getValueAt(usersTable.getSelectedRow(), 3));
																			
										
										
										uss.update(us);
										
										editable = false;
										UpdateRowsUsersTable();
										
										usersTable.setRowSelectionAllowed(false);
										
										btnDeleteUsers.setEnabled(true);
										btnRegisterUsers.setEnabled(true);
										usersTable.setEnabled(false);										
										continue;
									}
								}
							} catch (Exception e2) {
								e2.printStackTrace();
								JOptionPane.showMessageDialog(null,"Opa..., um erro inesperado aconteceu, contate o suporte!", "Erro", JOptionPane.ERROR_MESSAGE);
							}
							
						}else if (e.getKeyCode() != KeyEvent.VK_ENTER) {
							
							editable = false;					
							UpdateRowsUsersTable();
																				
							btnDeleteUsers.setEnabled(true);
							btnRegisterUsers.setEnabled(true);
							usersTable.setRowSelectionAllowed(false);
							usersTable.setEnabled(false);
							
							return;
						}
					}
				});
				
			}

		});
		btnEditUsers.setBounds(210, 30, 150, 40);       
		btnEditUsers.setFocusPainted(false);
		btnEditUsers.setContentAreaFilled(false);
		usersPanel.add(btnEditUsers);

		//Users deleting
		btnDeleteUsers.addActionListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (usersTable.getRowCount() == 0) {
					return;
				}
				
				JOptionPane.showMessageDialog(null, "Para deletar um dado selecione a linha dele na tabela! \n Depois precione Delete! \n Pressione qualquer tecla para sair!", null, JOptionPane.WARNING_MESSAGE);
				
				btnEditUsers.setEnabled(false);
				btnRegisterUsers.setEnabled(false);
				usersTable.setEnabled(true);	
                
				usersTable.addKeyListener(new KeyAdapter() {
					
					public void keyPressed(KeyEvent e) {
						
						if (e.getKeyCode() == KeyEvent.VK_DELETE) {
							
							UsersService uss = new UsersService();
							 							
							try {
								
								Collection<Users> UsersList = uss.findAll();
								
								if (UsersList == null) {
									return;
								}
								
								for (Users us : UsersList) {

									if (us.getId() == (Integer) usersTable.getValueAt(usersTable.getSelectedRow(), 0)) {
										
										uss.delete(us.getId());
										
										UpdateRowsUsersTable();
										
										btnEditUsers.setEnabled(true);
										btnRegisterUsers.setEnabled(true);
										usersTable.setRowSelectionAllowed(false);
										usersTable.setEnabled(false);
										break;
									}
								}
							} catch (Exception e2) {
								e2.printStackTrace();
								UpdateRowsUsersTable();
								JOptionPane.showMessageDialog(null,"Opa..., um erro inesperado aconteceu, contate o suporte!", "Erro", JOptionPane.ERROR_MESSAGE);
							}
							
							
						}else if(e.getKeyCode() != KeyEvent.VK_DELETE) {
							btnEditUsers.setEnabled(true);
							btnRegisterUsers.setEnabled(true);
							usersTable.setRowSelectionAllowed(false);
							usersTable.setEnabled(false);							
							return;
						}
					}
				});

			}

		});
		btnDeleteUsers.setBounds(370, 30, 150, 40); 
		btnDeleteUsers.setFocusPainted(false);
		btnDeleteUsers.setContentAreaFilled(false);
		usersPanel.add(btnDeleteUsers);

		usersPanel.setVisible(false);

	}

	public void FillComponentsInImporterPanel(){

		//Importers Panel Declaration
		importerPanel = new JPanel();
		importerPanel.setLayout(null);
		importerPanel.setBounds(0, 0, ScreenSize.width, ScreenSize.height);
		getContentPane().add(importerPanel); 

		
		//Select and import buttons
		btnSelecionarArquivo = new JButton("?");
		btnSelecionarArquivo.setBounds((int) Math.round(ScreenSize.width*0.44), (int) Math.round(ScreenSize.height*0.03), 25, 25);
		importerPanel.add(btnSelecionarArquivo);    
		
		btnImportarArquivo = new JButton("Importar");
		btnImportarArquivo.setBounds((int) Math.round(ScreenSize.width*0.46), (int) Math.round(ScreenSize.height*0.03), 90, 25);
		importerPanel.add(btnImportarArquivo); 
		
		
		btnSelecionarArquivo.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
								
				
				JFileChooser abrir = new JFileChooser(); 
				abrir.setFileFilter(new FileNameExtensionFilter("text files", "txt"));
				abrir.setAcceptAllFileFilterUsed(false);
				int retorno = abrir.showOpenDialog(null);  
				           if (retorno==JFileChooser.APPROVE_OPTION)  {
				             caminho = abrir.getSelectedFile().getAbsolutePath();  
				           }
				          
				txfImportacao.setText(caminho);
				
				
				Import imp = new Import();
				FileImported filed = new FileImported();
				Courses cs = new Courses();		
				
				filed = imp.importFile(caminho);
				if(filed == null) {
					return;
				}
				cs = filed.getCourse();
				
				txfData.setText(filed.getDate());
				txfFaseInicial.setText(filed.getPhaseStart());
				txfFaseFinal.setText(filed.getPhaseFinish());
				txfCurso.setText(cs.getName());
				
				LinkedHashSet<Phases> lp = cs.getPhases();
				
				for(Phases ph : lp) {
					modelFases.addElement(ph.getName());
					
					LinkedHashSet<Subjects> ls = ph.getSubjects();
					
					for (Subjects sb : ls) {
						modelDisciplinas.addElement(sb.getCode());
						
						LinkedHashSet<Teachers> lt = sb.getTeachers();
						
						for (Teachers tc : lt) {
							modelProfessores.addElement(tc.getName());
						}
					}
					
				}          
			}
		});
		
		btnImportarArquivo.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Import imp = new Import();
				FileImported filed = new FileImported();
				Courses cs = new Courses();	
				CoursesService css = new CoursesService();
				SubjectsService sbs = new SubjectsService();
				PhasesService phs = new PhasesService();
				TeachersService tcs = new TeachersService();
				
				filed = imp.importFile(caminho);
				if(filed == null) {
					return;
				}
				cs = filed.getCourse();
				css.save(cs);
				LinkedHashSet<Phases> lp = cs.getPhases();
				

				
				for(Phases ph : lp) {
					
					phs.save(ph);
					
					LinkedHashSet<Subjects> ls = ph.getSubjects();
					
					for (Subjects sb : ls) {

						sbs.save(sb);
						
						LinkedHashSet<Teachers> lt = sb.getTeachers();
						
						for (Teachers tc : lt) {
							tcs.save(tc);
						}
					}
					
				}  
				
				modelFases.clear();
				modelDisciplinas.clear();
				modelProfessores.clear();
				txfImportacao.setText("");
				txfCurso.setText("");
				txfData.setText("");
				txfFaseFinal.setText("");
				txfFaseInicial.setText("");				
				
				UpdateRowsCoursesTable();
				UpdateRowsSubjectsTable();
				UpdateRowsTeachersTable();
				
			}
		});
		
		
		//Importing Panel Fields declarations
		labelDescricao = new JLabel("Arquivo:");
		labelDescricao.setBounds((int) Math.round(ScreenSize.width*0.07), (int) Math.round(ScreenSize.height*0.03), 100, 25);
		importerPanel.add(labelDescricao);

		txfImportacao = new JTextField();
		txfImportacao.setBounds((int) Math.round(ScreenSize.width*0.11), (int) Math.round(ScreenSize.height*0.03), (int) Math.round(ScreenSize.width*0.32), 25);
		importerPanel.add(txfImportacao);

		labelDescricao = new JLabel(" Data:");
		labelDescricao.setBounds((int) Math.round(ScreenSize.width*0.08), (int) Math.round(ScreenSize.height*0.08), 100, 25);
		importerPanel.add(labelDescricao);

		txfData = new JTextField();
		txfData.setBounds((int) Math.round(ScreenSize.width*0.11), (int) Math.round(ScreenSize.height*0.08), 80, 25);
		importerPanel.add(txfData);

		labelDescricao = new JLabel("Fase Inicial:");
		labelDescricao.setBounds((int) Math.round(ScreenSize.width*0.17), (int) Math.round(ScreenSize.height*0.08), 100, 25);
		importerPanel.add(labelDescricao);

		txfFaseInicial = new JTextField();
		txfFaseInicial.setBounds((int) Math.round(ScreenSize.width*0.22), (int) Math.round(ScreenSize.height*0.08), 75, 25);
		importerPanel.add(txfFaseInicial);  

		labelDescricao = new JLabel("Fase Final:");
		labelDescricao.setBounds((int) Math.round(ScreenSize.width*0.28), (int) Math.round(ScreenSize.height*0.08), 100, 25);
		importerPanel.add(labelDescricao);

		txfFaseFinal = new JTextField();
		txfFaseFinal.setBounds((int) Math.round(ScreenSize.width*0.33), (int) Math.round(ScreenSize.height*0.08), 75, 25);
		importerPanel.add(txfFaseFinal);

		labelDescricao = new JLabel("Curso:");
		labelDescricao.setBounds((int) Math.round(ScreenSize.width*0.39), (int) Math.round(ScreenSize.height*0.08), 100, 25);
		importerPanel.add(labelDescricao);

		txfCurso = new JTextField();
		txfCurso.setBounds((int) Math.round(ScreenSize.width*0.42), (int) Math.round(ScreenSize.height*0.08), 145, 25);
		importerPanel.add(txfCurso);

		labelDescricao = new JLabel("FASES:");
		labelDescricao.setBounds((int) Math.round(ScreenSize.width*0.07), (int) Math.round(ScreenSize.height*0.17), 100, 25);
		importerPanel.add(labelDescricao);

		labelDescricao = new JLabel("DISCIPLINAS:");
		labelDescricao.setBounds((int) Math.round(ScreenSize.width*0.37), (int) Math.round(ScreenSize.height*0.17), 100, 25);
		importerPanel.add(labelDescricao);

		labelDescricao = new JLabel("PROFESSORES:");
		labelDescricao.setBounds((int) Math.round(ScreenSize.width*0.67), (int) Math.round(ScreenSize.height*0.17), 100, 25);
		importerPanel.add(labelDescricao);

		listFases = new JList(modelFases);
		JScrollPane scrollFases = new JScrollPane(listFases);
		scrollFases.setBounds((int) Math.round(ScreenSize.width*0.07), (int) Math.round(ScreenSize.height*0.2), (int) Math.round(ScreenSize.width*0.25), (int) Math.round(ScreenSize.height*0.6));
		importerPanel.add(scrollFases, BorderLayout.CENTER);
		listFases.addMouseListener(null);

		listDisciplinas = new JList(modelDisciplinas);
		JScrollPane scrollDisciplinas = new JScrollPane(listDisciplinas);
		scrollDisciplinas.setBounds((int) Math.round(ScreenSize.width*0.37), (int) Math.round(ScreenSize.height*0.2), (int) Math.round(ScreenSize.width*0.25), (int) Math.round(ScreenSize.height*0.6));
		importerPanel.add(scrollDisciplinas, BorderLayout.CENTER);
		listDisciplinas.addMouseListener(null);

		listProfessores = new JList(modelProfessores);
		JScrollPane scrollProfessores = new JScrollPane(listProfessores);
		scrollProfessores.setBounds((int) Math.round(ScreenSize.width*0.67), (int) Math.round(ScreenSize.height*0.2), (int) Math.round(ScreenSize.width*0.25), (int) Math.round(ScreenSize.height*0.6));
		importerPanel.add(scrollProfessores, BorderLayout.CENTER);
		listProfessores.addMouseListener(null);
		//Importing Panel Fields declarations
		
		
		
		
		
		importerPanel.setVisible(false);
		
	}

	//Internal Frames - CREATE AND FILL   

	public void CreateComponentStudentsInternalFrame(){

		studentsInternalFrame = new JInternalFrame("Cadastro de Aluno");
		studentsInternalFrame.setLayout(null);
		studentsInternalFrame.setBounds(10, 10, 600, 600);
		studentsInternalFrame.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL)));

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
		cmbSex.setSelectedIndex(-1);
		studentsInternalFrame.add(cmbSex);

		lbBirthdate = new JLabel();
		lbBirthdate.setText("Data de nascimento:");
		lbBirthdate.setBounds(40, 135, 125, 20);
		studentsInternalFrame.add(lbBirthdate);

		txfBirthdate = new JTextField();
		txfBirthdate.setBounds(40, 155, 125, 20);
		studentsInternalFrame.add(txfBirthdate);   

		lbAdress = new JLabel();
		lbAdress.setText("Endereco:");
		lbAdress.setBounds(270, 40, 125, 20);
		studentsInternalFrame.add(lbAdress);

		txfAdress = new JTextField();
		txfAdress.setBounds(270, 60, 270, 20);
		studentsInternalFrame.add(txfAdress);

		lbAdressNum = new JLabel();
		lbAdressNum.setText("N�:");
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
		lbNote.setText("Observacao:");
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

			Students st;
			StudentsService sts = new StudentsService();
			try {
				if(txfStudent.getText().isEmpty()) {
					throw new Exception("Campo Estudante estÃ¡ vazio!");
				}else if (txfBirthdate.getText().isEmpty()) {
					throw new Exception("Campo Data de nascimento estÃ¡ vazio!");
				}else if (cmbSex.getSelectedIndex() == -1) {
					throw new Exception("Campo Sexo estÃ¡ vazio!");
				}else if (txfPhone.getText().isEmpty()) {
					throw new Exception("Campo Telefone estÃ¡ vazio!");
				}else if (txfCellphone.getText().isEmpty()) {
					throw new Exception("Campo Celular estÃ¡ vazio!");
				}else if (txfEmail.getText().isEmpty()) {
					throw new Exception("Campo Email estÃ¡ vazio!");
				}else if (txfNote.getText().isEmpty()) {
					throw new Exception("Campo ObservaÃ§Ãµes estÃ¡ vazio!");
				}else if (txfAdress.getText().isEmpty()) {
					throw new Exception("Campo EndereÃ§o estÃ¡ vazio!");
				}else if (txfAdressNum.getText().isEmpty()) {
					throw new Exception("Campo NÂ° estÃ¡ vazio!");
				}else if (txfComplement.getText().isEmpty()) {
					throw new Exception("Campo Complemento estÃ¡ vazio!");
				}else if (txfSuburb.getText().isEmpty()) {
					throw new Exception("Campo Bairro estÃ¡ vazio!");
				}else if (txfSCity.getText().isEmpty()) {
					throw new Exception("Campo Cidade estÃ¡ vazio!");
				}else if (txfStuEstate.getText().isEmpty()) {
					throw new Exception("Campo Estado estÃ¡ vazio!");
				}else if (txfCep.getText().isEmpty()) {
					throw new Exception("Campo CEP estÃ¡ vazio!");
				}

				char sex;

				if (cmbSex.getSelectedIndex() == 0)                         
					sex = 'm';
				else                        
					sex = 'f';                  

				st = new Students(txfStudent.getText(), txfBirthdate.getText(), sex, txfPhone.getText() , txfCellphone.getText(), txfEmail.getText(), txfNote.getText(), 
						txfAdress.getText(), txfAdressNum.getText() , txfComplement.getText(), txfSuburb.getText() , txfSCity.getText(), txfStuEstate.getText(), txfCep.getText());                    
				sts.save(st);
				
				txfStudent.setText(""); 
				txfBirthdate.setText(""); 
				cmbSex.setSelectedIndex(-1);
				txfPhone.setText(""); 
				txfCellphone.setText(""); 
				txfEmail.setText(""); 
				txfNote.setText(""); 
				txfAdress.setText(""); 
				txfAdressNum.setText(""); 
				txfComplement.setText(""); 
				txfSuburb.setText(""); 
				txfSCity.setText(""); 
				txfStuEstate.setText(""); 
				txfCep.setText("");
				
				JOptionPane.showMessageDialog(null,"Aluno cadastrado com sucesso!","Sucesso", JOptionPane.PLAIN_MESSAGE);
				
				UpdateRowsStudentsTable();

				studentsPanel.setVisible(true);
				studentsInternalFrame.setVisible(false);


			} catch (Exception e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null,e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}




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

	public void CreateComponentTeachersInternalFrame(){

		teachersInternalFrame = new JInternalFrame("Cadastro de Professores");
		teachersInternalFrame.setLayout(null);
		teachersInternalFrame.setBounds(200, 80, 210, 280);
		teachersInternalFrame.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL)));

		//Registering Panel Buttons declarations
		JButton btnSaveTeachers = new JButton("Salvar");
		JButton btnExitTeachers = new JButton("Sair");

		//Registering Panel Fields declarations

		String TeacherGraduationType[] = { "Graduacao", "Pos-graduacao","Mestrado","Doutorado"};

		JLabel lbTeacherName;
		JTextField txfTeacherName; 
		JLabel lbTeacherGraduation;
		JComboBox<?> cbmTeacherGraduation;

		lbTeacherGraduation= new JLabel();
		lbTeacherGraduation.setText("Graduaï¿½ï¿½o");
		lbTeacherGraduation.setBounds(40, 120, 125, 20);
		teachersInternalFrame.add(lbTeacherGraduation);

		cbmTeacherGraduation = new JComboBox<>(TeacherGraduationType);
		cbmTeacherGraduation.setBounds(40, 140, 125, 20);
		cbmTeacherGraduation.setSelectedIndex(-1);
		teachersInternalFrame.add(cbmTeacherGraduation);

		lbTeacherName= new JLabel();
		lbTeacherName.setText("Nome:");
		lbTeacherName.setBounds(40, 75, 125, 20);
		teachersInternalFrame.add(lbTeacherName);

		txfTeacherName= new JTextField();
		txfTeacherName.setBounds(40, 95, 125, 20);
		teachersInternalFrame.add(txfTeacherName);

		//Registering Panel Fields declarations


		//Register panel saving
		btnSaveTeachers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {;

			Teachers tc;
			TeachersService tcs = new TeachersService();

			try {
				if (txfTeacherName.getText().isEmpty()) {
					throw new Exception("Campo Nome está vazio!");
				}else if (cbmTeacherGraduation.getSelectedIndex() == -1) {
					throw new Exception("Campo Graduação está vazio!");
				}
				int graduation = 0;

				if (cbmTeacherGraduation.getSelectedIndex() == 0) {
					graduation = 01;
				}else if (cbmTeacherGraduation.getSelectedIndex() == 1 ) {
					graduation = 02;
				}else if (cbmTeacherGraduation.getSelectedIndex() == 2) {
					graduation = 03;
				}else if (cbmTeacherGraduation.getSelectedIndex() == 3) {
					graduation = 04;
				}

				tc = new Teachers(txfTeacherName.getText(), graduation);

				tcs.save(tc);
				
				txfTeacherName.setText("");
				cbmTeacherGraduation.setSelectedIndex(-1);
				
				UpdateRowsTeachersTable();

				teachersInternalFrame.setVisible(false);

			} catch (Exception e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null,e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}                                                
			}
		});
		btnSaveTeachers.setBounds(93, 200, 95, 20);     
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
		btnExitTeachers.setBounds(23, 200, 60, 20); 
		btnExitTeachers.setFocusPainted(false);
		btnExitTeachers.setContentAreaFilled(false);
		teachersInternalFrame.add(btnExitTeachers);

		teachersInternalFrame.setVisible(false);

	}

	public void CreateComponentSubjectsInternalFrame(){

		subjectsInternalFrame = new JInternalFrame("Cadastro de Professores");
		subjectsInternalFrame.setLayout(null);
		subjectsInternalFrame.setBounds(200, 80, 210, 310);
		subjectsInternalFrame.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL)));

		//Registering Panel Buttons declarations
		JButton btnSaveSubjects = new JButton("Salvar");
		JButton btnExitSubjects = new JButton("Sair");

		//Registering Panel Fields declarations
		String Weekdays[] = {"Segunda-feira", "Terca-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sabado" };
		JLabel lbSubjectWeekday;
		JComboBox<?> cmbSubjectWeekdays;

		JLabel lbSubjectName;
		JTextField txfSubjectName; 
		JLabel lbSubjectCode;
		JTextField txfSubjectCode;
		JLabel lbSubjectTeacherAmount;
		JTextField txfSubjectTeacherAmount;
		JLabel lbSubjectPhases;
		JComboBox cmbSubjectPhases;

		lbSubjectCode= new JLabel();
		lbSubjectCode.setText("Cï¿½digo:");
		lbSubjectCode.setBounds(40, 30, 125, 20);
		subjectsInternalFrame.add(lbSubjectCode);

		txfSubjectCode= new JTextField();
		txfSubjectCode.setBounds(40, 50, 125, 20);
		subjectsInternalFrame.add(txfSubjectCode);

		lbSubjectName= new JLabel();
		lbSubjectName.setText("Nome:");
		lbSubjectName.setBounds(40, 75, 125, 20);
		subjectsInternalFrame.add(lbSubjectName);

		txfSubjectName= new JTextField();
		txfSubjectName.setBounds(40, 95, 125, 20);
		subjectsInternalFrame.add(txfSubjectName);

		lbSubjectWeekday= new JLabel();
		lbSubjectWeekday.setText("Dia da Semana:");
		lbSubjectWeekday.setBounds(40, 120, 125, 20);
		subjectsInternalFrame.add(lbSubjectWeekday);

		cmbSubjectWeekdays = new JComboBox<>(Weekdays);
		cmbSubjectWeekdays.setBounds(40, 140, 125, 20);
		cmbSubjectWeekdays.setSelectedIndex(-1);
		subjectsInternalFrame.add(cmbSubjectWeekdays);

		lbSubjectPhases = new JLabel();
		lbSubjectPhases.setText("Fase");
		lbSubjectPhases.setBounds(40, 165, 125, 20);
		subjectsInternalFrame.add(lbSubjectPhases);


		cmbSubjectPhases= new JComboBox();
		cmbSubjectPhases.setBounds(40, 185, 125, 20);
		
		PhasesService phs = new PhasesService();
		Collection<Phases> cph = phs.findAll();
		
		for (Phases ph : cph) {
			
			cmbSubjectPhases.addItem(ph.getName());
			
		}
		
		subjectsInternalFrame.add(cmbSubjectPhases);
		

		//Registering Panel Fields declarations


		//Register panel saving
		btnSaveSubjects.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {;

			Subjects mh;
			SubjectsService mhs = new SubjectsService();

			try {
				if (txfSubjectCode.getText().isEmpty()) {
					throw new Exception("Campo Código está vazio!");
				}else if (txfSubjectName.getText().isEmpty()) {
					throw new Exception("Campo Nome está vazio!");
				}else if (cmbSubjectWeekdays.getSelectedIndex() == -1) {
					throw new Exception("Campo Dia da Semana está vazio!");
				}else if (cmbSubjectPhases.getSelectedIndex() == -1) {
					throw new Exception("Campo Fase esta vazio!");
				}
				Integer weekday = 0;

				if (cmbSubjectWeekdays.getSelectedIndex() == 0) {
					weekday = 02;
				}else if (cmbSubjectWeekdays.getSelectedIndex() == 1 ) {
					weekday = 03;
				}else if (cmbSubjectWeekdays.getSelectedIndex() == 2) {
					weekday = 04;
				}else if (cmbSubjectWeekdays.getSelectedIndex() == 3) {
					weekday = 05;
				}else if (cmbSubjectWeekdays.getSelectedIndex() == 4) {
					weekday = 06;
				}else if (cmbSubjectWeekdays.getSelectedIndex() == 5) {
					weekday = 07;
				}
				
				
				
				mh = new Subjects(Integer.parseInt(txfSubjectCode.getText()) , txfSubjectName.getText(), weekday);

				mhs.save(mh);
				
				txfSubjectCode.setText("");
				txfSubjectName.setText("");
				txfSubjectTeacherAmount.setText("");
				cmbSubjectWeekdays.setSelectedIndex(-1);
				
				UpdateRowsSubjectsTable();
				
				subjectsInternalFrame.setVisible(false);

			} catch (Exception e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null,e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			} 

			}
		});
		btnSaveSubjects.setBounds(93, 230, 95, 20);     
		btnSaveSubjects.setFocusPainted(false);
		btnSaveSubjects.setContentAreaFilled(false);
		subjectsInternalFrame.add(btnSaveSubjects);

		//Register panel exiting
		btnExitSubjects.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {;

			subjectsInternalFrame.setVisible(false);

			}
		});
		btnExitSubjects.setBounds(23, 230, 60, 20); 
		btnExitSubjects.setFocusPainted(false);
		btnExitSubjects.setContentAreaFilled(false);
		subjectsInternalFrame.add(btnExitSubjects);

		subjectsInternalFrame.setVisible(false);

	}

	/*public void CreateComponentPhasesInternalFrame(){

		phasesInternalFrame = new JInternalFrame("Cadastro de Fases");
		phasesInternalFrame.setLayout(null);
		phasesInternalFrame.setBounds(200, 80, 210, 230);
		phasesInternalFrame.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL)));

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
		lbPhaseCode.setBounds(40, 30, 125, 20);
		phasesInternalFrame.add(lbPhaseCode);

		txfPhaseCode= new JTextField();
		txfPhaseCode.setBounds(40, 50, 125, 20);
		phasesInternalFrame.add(txfPhaseCode);

		lbPhaseName= new JLabel();
		lbPhaseName.setText("Nome:");
		lbPhaseName.setBounds(40, 75, 125, 20);
		phasesInternalFrame.add(lbPhaseName);

		txfPhaseName= new JTextField();
		txfPhaseName.setBounds(40, 95, 125, 20);
		phasesInternalFrame.add(txfPhaseName);      

		//Register panel saving
		btnSavePhases.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {;
			
			Phases fs;
			PhasesService fss = new PhasesService();

			try {
				if (txfPhaseCode.getText().isEmpty()) {
					throw new Exception("Campo Código está vazio!");
				}else if (txfPhaseName.getText().isEmpty()) {
					throw new Exception("Campo Nome está vazio!");
				}
				
				fs = new Phases(txfPhaseName.getText());

				fss.save(fs);
				
				txfPhaseCode.setText("");
				txfPhaseName.setText("");

				phasesInternalFrame.setVisible(false);

			} catch (Exception e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null,e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			} 
		  }
		});
		btnSavePhases.setBounds(93, 150, 95, 20);   
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
		btnExitPhases.setBounds(23, 150, 60, 20); 
		btnExitPhases.setFocusPainted(false);
		btnExitPhases.setContentAreaFilled(false);
		phasesInternalFrame.add(btnExitPhases);

		phasesInternalFrame.setVisible(false);

	}*/

	public void CreateComponentCoursesInternalFrame(){

		coursesInternalFrame = new JInternalFrame("Cadastro de Curso");
		coursesInternalFrame.setLayout(null);
		coursesInternalFrame.setBounds(200, 80, 210, 230);
		coursesInternalFrame.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL)));

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
		lbCourseCode.setBounds(40, 30, 125, 20);
		coursesInternalFrame.add(lbCourseCode);

		txfCourseCode= new JTextField();
		txfCourseCode.setBounds(40, 50, 125, 20);
		coursesInternalFrame.add(txfCourseCode);

		lbCourseName= new JLabel();
		lbCourseName.setText("Nome:");
		lbCourseName.setBounds(40, 75, 125, 20);
		coursesInternalFrame.add(lbCourseName);

		txfCourseName= new JTextField();
		txfCourseName.setBounds(40, 95, 125, 20);
		coursesInternalFrame.add(txfCourseName);      

		//Register panel saving
		btnSaveCourses.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {;

			Courses cs;
			CoursesService css = new CoursesService();

			try {
				if (txfCourseCode.getText().isEmpty()) {
					throw new Exception("Campo Código está vazio!");
				}else if (txfCourseName.getText().isEmpty()) {
					throw new Exception("Campo Nome está vazio!");
				}
				
				cs = new Courses(Integer.parseInt(txfCourseCode.getText()) , txfCourseName.getText());
				
				LinkedHashSet<Phases> cph = new LinkedHashSet<Phases>();
				Phases ph = new Phases();
				
				ph.setName("Fase 01");
				cph.add(ph);
				ph.setName("Fase 02");
				cph.add(ph);
				ph.setName("Fase 03");
				cph.add(ph);
				ph.setName("Fase 04");
				cph.add(ph);
				ph.setName("Fase 05");
				cph.add(ph);
				ph.setName("Fase 06");
				cph.add(ph);
				ph.setName("Fase 08");
				cph.add(ph);
				ph.setName("Fase 09");
				cph.add(ph);
				ph.setName("Fase 10");
				cph.add(ph);
				
				cs.setPhases(cph);
				
				css.save(cs);
				
				txfCourseCode.setText("");
				txfCourseName.setText("");
				
				UpdateRowsCoursesTable();
				
				coursesInternalFrame.setVisible(false);

			} catch (Exception e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null,e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			} 

			}
		});
		btnSaveCourses.setBounds(93, 150, 95, 20);   
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
		btnExitCourses.setBounds(23, 150, 60, 20); 
		btnExitCourses.setFocusPainted(false);
		btnExitCourses.setContentAreaFilled(false);
		coursesInternalFrame.add(btnExitCourses);

		coursesInternalFrame.setVisible(false);

	}

	public void CreateComponentCitiesInternalFrame(){

		citiesInternalFrame = new JInternalFrame("Cadastro de Cidade");
		citiesInternalFrame.setLayout(null);
		citiesInternalFrame.setBounds(200, 80, 210, 310);
		citiesInternalFrame.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL)));

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
			
			Cities ct;
			CitiesService cts = new CitiesService();

			try {
				if (txfCity.getText().isEmpty()) {
					throw new Exception("Campo Cidade está vazio!");
				}else if (txfState.getText().isEmpty()) {
					throw new Exception("Campo Estado está vazio!");
				}else if (txfCountry.getText().isEmpty()) {
					throw new Exception("Campo País está vazio!");
				}
				
				ct = new Cities(txfCity.getText(), txfState.getText(), txfCountry.getText());

				cts.save(ct);
				
				txfCity.setText("");
				txfCountry.setText("");
				txfState.setText("");
				
				UpdateRowsCitiesTable();
				
				citiesInternalFrame.setVisible(false);

			} catch (Exception e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null,e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			} 
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

	public void CreateComponentUsersInternalFrame(){

		usersInternalFrame = new JInternalFrame("Cadastro de Usuario");
		usersInternalFrame.setLayout(null);
		usersInternalFrame.setBounds(180, 150, 228, 290);
		usersInternalFrame.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL)));

		//Registering Panel Buttons declarations
		JButton btnSaveUsers = new JButton("Salvar");
		JButton btnExitUsers = new JButton("Sair");

		//Registering Panel Fields declarations

		String UserType[] = { "Usuario", "Administrador" };

		JLabel lbUser;
		JTextField txfUser;
		JLabel lbPassword;
		JPasswordField txfPassword;
		JLabel lbType;
		JComboBox<?> cmbType;

		lbUser = new JLabel();
		lbUser.setText("Usuario:");
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
		cmbType.setSelectedIndex(-1);
		usersInternalFrame.add(cmbType);
		////Registering Panel Fields declarations


		//Register panel saving
		btnSaveUsers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {;
			  
				Users us;
				UsersService uss = new UsersService();
	
				try {
					if (txfUser.getText().isEmpty()) {
						throw new Exception("Campo Usuário está vazio!");
					}else if (txfPassword.getPassword().toString().isEmpty()) {
						throw new Exception("Campo Senha está vazio!");
					}else if (cmbType.getSelectedIndex() == -1) {
						throw new Exception("Campo Perfil está vazio!");
					}
					
					String type;
					
					if (cmbType.getSelectedIndex() == 0) {
						type = "Usuário";
					}else {
						type = "Administrador";
					}
					
					us = new Users(txfUser.getText(), String.copyValueOf(txfPassword.getPassword()) , type);
	
					uss.save(us);
					
					txfUser.setText("");
					txfPassword.setText("");
					cmbType.setSelectedIndex(-1);
					
					UpdateRowsUsersTable();
					
					usersInternalFrame.setVisible(false);
	
				} catch (Exception e) {
					System.out.println(e.getMessage());
					JOptionPane.showMessageDialog(null,e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} 

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

	//JTables - CREATE   

	public void CreateStudentsTable(){

		//Declaring Scroll pane
		JScrollPane studentScrollPane;                                          

		//Table Configuration
		studentTable = new JTable();
		studentTable.setEnabled(false);
		
		//Fill Rows in studentModel
		UpdateRowsStudentsTable();
		
		//Scroll Pane Configuration
		studentScrollPane = new JScrollPane(studentTable);        
		studentScrollPane.setLocation(50, 100); 
		studentScrollPane.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL)));
		studentScrollPane.setSize((int)Math.round(ScreenSize.width*0.916), (int)Math.round(ScreenSize.height*0.76));                     
		
		
		studentsPanel.add(studentScrollPane);   

		studentTable.setVisible(true);

	}

	public void CreateTeacherTable(){

		//Declaring Scroll pane
		JScrollPane teacherScrollPane;

		//Table Configuration
		teacherTable = new JTable(); 
		teacherTable.setEnabled(false);
		
		//Fill Rows in studentModel
		UpdateRowsTeachersTable();
		
		//Scroll Pane Configuration
		teacherScrollPane = new JScrollPane(teacherTable);
		teacherScrollPane.setLocation(50, 100); 
		teacherScrollPane.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL)));
		teacherScrollPane.setSize((int)Math.round(ScreenSize.width*0.916), (int)Math.round(ScreenSize.height*0.76));    
		
		teachersPanel.add(teacherScrollPane);

		teacherTable.setVisible(true);

	}

	public void CreateSubjectTable(){

		//Declaring Scroll pane
		JScrollPane subjectScrollPane;

		//Table Configuration
		subjectTable = new JTable();                   
		subjectTable.setEnabled(false);
		
		//Fill Rows in studentModel
		UpdateRowsSubjectsTable();
		
		//Scroll Pane Configuration
		subjectScrollPane = new JScrollPane(subjectTable);
		subjectScrollPane.setLocation(50, 100); 
		subjectScrollPane.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL)));
		subjectScrollPane.setSize((int)Math.round(ScreenSize.width*0.916), (int)Math.round(ScreenSize.height*0.76));        
		
		
		
		subjectsPanel.add(subjectScrollPane);

		subjectTable.setVisible(true);

	}

	/*public void CreatePhasesTable(){
		//Declaring Scroll pane
		JScrollPane phaseScrollPane;

		//Table Configuration
		phaseTable = new JTable();                   
		phaseTable.setEnabled(false);
		
		//Fill Rows in studentModel
		UpdateRowsPhasesTable();
		
		//Scroll Pane Configuration
		phaseScrollPane = new JScrollPane(phaseTable);
		phaseScrollPane.setLocation(50, 100); 
		phaseScrollPane.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL)));
		phaseScrollPane.setSize((int)Math.round(ScreenSize.width*0.916), (int)Math.round(ScreenSize.height*0.76));        
		
		
		
		phasesPanel.add(phaseScrollPane);

		phaseTable.setVisible(true);
		

	}*/

	public void CreateCourseTable(){

		//Declaring Table and Scroll pane
		JScrollPane courseScrollPane;

		//Table Configuration
		courseTable = new JTable();                   
		courseTable.setEnabled(false);
		
		UpdateRowsCoursesTable();
		
		//Scroll Pane Configuration
		courseScrollPane = new JScrollPane(courseTable);
		courseScrollPane.setLocation(50, 100); 
		courseScrollPane.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL)));
		courseScrollPane.setSize((int)Math.round(ScreenSize.width*0.916), (int)Math.round(ScreenSize.height*0.76));        
        
		
		
		coursesPanel.add(courseScrollPane);

		courseTable.setVisible(true);

	} 

	public void CreateCitiesTable(){

		//Declaring Table and Scroll pane
		JScrollPane citiesScrollPane;

		//Table Configuration
		citiesTable = new JTable();                 
		citiesTable.setEnabled(false);
		
		UpdateRowsCitiesTable();
		
		//Scroll Pane Configuration
		citiesScrollPane = new JScrollPane(citiesTable);
		citiesScrollPane.setLocation(50, 100); 
		citiesScrollPane.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL)));
		citiesScrollPane.setSize((int)Math.round(ScreenSize.width*0.916), (int)Math.round(ScreenSize.height*0.76));        
		
		citiesPanel.add(citiesScrollPane);

		citiesTable.setVisible(true);

	}

	public void CreateUsersTable(){

		
		//Declaring Table and Scroll pane
		JScrollPane usersScrollPane;

		//Table Configuration
		usersTable = new JTable();                   
		usersTable.setEnabled(false);
		
		UpdateRowsUsersTable();
		
		//Scroll Pane Configuration
		usersScrollPane = new JScrollPane(usersTable);
		usersScrollPane.setLocation(50, 100); 
		usersScrollPane.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL)));
		usersScrollPane.setSize((int)Math.round(ScreenSize.width*0.916), (int)Math.round(ScreenSize.height*0.76));        
		
		usersPanel.add(usersScrollPane);

		usersTable.setVisible(true);

	}

	//JTables - FILL AND UPDATE

	public void UpdateRowsStudentsTable(){

		//Declaring Table Model
		studentTableModel = new DefaultTableModel() {

			String[] studentColumns = {"Codigo","Estudante","Data de Nascimento","E-Mail","Sexo","Telefone",
					"Celular","CEP","Numero", "Endereco", "Bairro", "Cidade","Estado","Complemento",
			"Observacao"};

			public int getColumnCount() { 
				return studentColumns.length; 
			} 

			@Override
			public String getColumnName(int index) {
				return studentColumns[index];
			}
			
			public boolean isCellEditable(int rowIndex, int mColIndex){ 
		         return editable; 
		    } 

		};
		try {
			//Initialize StudentService and pull data object
			StudentsService sts = new StudentsService(); 
			Collection<Students> StudentsList = sts.findAll();
			if (StudentsList == null) {
				return;
			}
			//ADD ROWS TO TABLE
			for(Students st : StudentsList) {                         

				Object[] data = {st.getId(), st.getName(), st.getBirthdate(),st.getEmail(), st.getSex(), st.getPhone(), st.getCellphone(),
						st.getCep(), st.getNumber(), st.getAddress(), st.getSuburb(),st.getCity(), st.getEstate(), st.getComplement(), st.getNote()};

				studentTableModel.addRow(data);				
			}
			studentTable.setModel(studentTableModel);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null,"Opa..., um erro inesperado aconteceu, contate o suporte!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		

	

	}

	public void UpdateRowsTeachersTable() {

		//Set Table Model
		teacherTableModel = new DefaultTableModel() {

			String[] teacherColumns = {"Codigo", "Nome", "Graduacao"};

			public int getColumnCount() { 
				return teacherColumns.length; 
			} 

			@Override
			public String getColumnName(int index) {
				return teacherColumns[index];
			}
			
			public boolean isCellEditable(int rowIndex, int mColIndex){ 
		         return editable; 
		    } 

		};
		try {
			//Initialize TeacherService and pull data object
			TeachersService tts = new TeachersService(); 
			Collection<Teachers> TeacherList = tts.findAll();
			
			if (TeacherList == null) {
				return;
			}
			
			//ADD ROWS TO TABLE
			for(Teachers tc : TeacherList) {                         

				Object[] data = {tc.getId(), tc.getName(), tc.getGraduation()};

				teacherTableModel.addRow(data);
			}
			
			teacherTable.setModel(teacherTableModel);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Ou Aqui");
			JOptionPane.showMessageDialog(null,"Opa..., um erro inesperado aconteceu, contate o suporte!", "Erro", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void UpdateRowsSubjectsTable() {

		//Declaring Table Model
		subjectTableModel = new DefaultTableModel() {


			String[] subjectColumns = {"Codigo", "Nome", "Dia da Semana", "N° de Professores"};

			public int getColumnCount() { 
				return subjectColumns.length; 
			} 

			@Override
			public String getColumnName(int index) {
				return subjectColumns[index];
			}
			
			@Override
			public boolean isCellEditable(int rowIndex, int mColIndex){ 
		         return editable; 
		    } 

		};

		try {
			//Initialize SubjectService and pull data object
			SubjectsService mts = new SubjectsService(); 
			Collection<Subjects> SubjectList = mts.findAll();
			
			if (SubjectList == null) {
				return;
			}
			
			//ADD ROWS TO TABLE
			for(Subjects mh : SubjectList) {                         

				Object[] data = {mh.getCode(), mh.getname(), mh.getweek_day(), mh.getTeacher_amount()};

				subjectTableModel.addRow(data);
				

			}
			
			subjectTable.setModel(subjectTableModel);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null,"Opa..., um erro inesperado aconteceu, contate o suporte!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		

	}
	
	/*public void UpdateRowsPhasesTable() {

		//Declaring Table Model
		phaseTableModel = new DefaultTableModel() {


			String[] subjectColumns = {"Codigo", "Nome"};

			public int getColumnCount() { 
				return subjectColumns.length; 
			} 

			@Override
			public String getColumnName(int index) {
				return subjectColumns[index];
			}
			
			@Override
			public boolean isCellEditable(int rowIndex, int mColIndex){ 
		         return editable; 
		    } 

		};

		try {
			//Initialize SubjectService and pull data object
			PhasesService fss = new PhasesService(); 
			Collection<Phases> PhaseList = fss.findAll();
			
			if (PhaseList == null) {
				return;
			}
			
			//ADD ROWS TO TABLE
			for(Phases fs : PhaseList) {                         

      			Object[] data = {fs.getId(), fs.getName()};

				phaseTableModel.addRow(data);
				

			}
			
			phaseTable.setModel(phaseTableModel);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null,"Opa..., um erro inesperado aconteceu, contate o suporte!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		

	}*/
	
	public void UpdateRowsCoursesTable() {

		//Declaring Table Model
		courseTableModel = new DefaultTableModel() {


			String[] CoursesColumns = {"Codigo", "Nome"};

			public int getColumnCount() { 
				return CoursesColumns.length; 
			} 

			@Override
			public String getColumnName(int index) {
				return CoursesColumns[index];
			}
			
			@Override
			public boolean isCellEditable(int rowIndex, int mColIndex){ 
		         return editable; 
		    } 
			
		};

		try {
			//Initialize SubjectService and pull data object
			CoursesService css = new CoursesService(); 
			Collection<Courses> CourseList = css.findAll();
			
			if (CourseList == null) {
				return;
			}
			
			//ADD ROWS TO TABLE
			for(Courses cs : CourseList) {                         

				Object[] data = {cs.getId(), cs.getName()};

				courseTableModel.addRow(data);
				

			}
			
			courseTable.setModel(courseTableModel);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null,"Opa..., um erro inesperado aconteceu, contate o suporte!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		

	}
	
	public void UpdateRowsCitiesTable() {

		//Declaring Table Model
		citiesTableModel = new DefaultTableModel() {


			String[] CitiesColumns = {"Codigo", "Cidade", "Estado", "Pais"};

			public int getColumnCount() { 
				return CitiesColumns.length; 
			} 

			@Override
			public String getColumnName(int index) {
				return CitiesColumns[index];
			}
			
			@Override
			public boolean isCellEditable(int rowIndex, int mColIndex){ 
		         return editable; 
		    } 
			
		};

		try {
			//Initialize SubjectService and pull data object
			CitiesService cts = new CitiesService(); 
			Collection<Cities> CityList = cts.findAll();
			
			if (CityList == null) {
				return;
			}
			
			//ADD ROWS TO TABLE
			for(Cities ct : CityList) {                         

				Object[] data = {ct.getId(), ct.getName(), ct.getState(), ct.getCountry() };

				citiesTableModel.addRow(data);
				

			}
			
			citiesTable.setModel(citiesTableModel);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null,"Opa..., um erro inesperado aconteceu, contate o suporte!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		

	}
	
	public void UpdateRowsUsersTable() {

		//Declaring Table Model
		usersTableModel = new DefaultTableModel() {


			String[] UsersColumns = {"Codigo", "Usuario", "Senha", "Perfil"};

			public int getColumnCount() { 
				return UsersColumns.length; 
			} 

			@Override
			public String getColumnName(int index) {
				return UsersColumns[index];
			}
			
			@Override
			public boolean isCellEditable(int rowIndex, int mColIndex){ 
		         return editable; 
		    } 
			
		};

		try {
			//Initialize SubjectService and pull data object
			UsersService uss = new UsersService(); 
			Collection<Users> UserList = uss.findAll();
			
			if (UserList == null) {
				return;
			}
			
			//ADD ROWS TO TABLE
			for(Users us : UserList) {                         

				Object[] data = {us.getId(), us.getUser(), us.getPassword(), us.getPerfil() };

				usersTableModel.addRow(data);
				

			}
			
			usersTable.setModel(usersTableModel);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null,"Opa..., um erro inesperado aconteceu, contate o suporte!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		

	}

	//Configuration Internal Frame - CREATE AND FILL

	public void CreateConfigInternalFrame() {

		configInternalFrame = new JInternalFrame("Configuracoes");
		configInternalFrame.setLayout(null);
		configInternalFrame.setBounds(200, 80, 210, 230);
		configInternalFrame.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL)));
		getContentPane().add(configInternalFrame);

		//Configuration button declarations
		JButton btnSaveConfig = new JButton("Salvar");
		JButton btnExitConfig = new JButton("Sair");

		//Configuration field declarations
		JCheckBox isFullScreenOnOff;        
		JLabel isFullScreenLabel;

		isFullScreenLabel = new JLabel("FullScreen");
		isFullScreenLabel.setBounds(40, 50, 125, 20);
		configInternalFrame.add(isFullScreenLabel);

		isFullScreenOnOff = new JCheckBox("Fullscreen", isFullScreen);
		isFullScreenOnOff.setBounds(20, 50, 20, 20);
		configInternalFrame.add(isFullScreenOnOff);
		//Configuration field declarations

		//Save changes button - if statements verify JCheckbox and isFullScreen status - display confirm box if it will change screen size
		btnSaveConfig.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {;

			if(isFullScreenOnOff.isSelected() == true && isFullScreen == true) {

				configInternalFrame.setVisible(false);

			}

			else if(isFullScreenOnOff.isSelected() == true && isFullScreen == false) {

				int DialogResult = JOptionPane.showConfirmDialog(configInternalFrame, "Esta alteracao ira reiniciar o programa, deseja prosseguir?", "", JOptionPane.YES_NO_OPTION);
				if (DialogResult == JOptionPane.YES_OPTION) {      

					isFullScreen = true;
					ResizeWindow();         

				}


			}
			else if(isFullScreenOnOff.isSelected() == false && isFullScreen == false) {   

				configInternalFrame.setVisible(false); 

			}
			else {      

				int DialogResult = JOptionPane.showConfirmDialog(configInternalFrame, "Esta altera��o ir� reiniciar o programa, deseja prosseguir?", "", JOptionPane.YES_NO_OPTION);
				if (DialogResult == JOptionPane.YES_OPTION) {          

					isFullScreen = false;                   
					ResizeWindow();             

				}                   

			}                                   

			}
		});
		btnSaveConfig.setBounds(93, 150, 95, 20);       
		btnSaveConfig.setFocusPainted(false);
		btnSaveConfig.setContentAreaFilled(false);
		configInternalFrame.add(btnSaveConfig);

		//Exit configuration menu button
		btnExitConfig.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {;

			isFullScreenOnOff.setSelected(isFullScreen);
			configInternalFrame.setVisible(false);

			}
		});
		btnExitConfig.setBounds(23, 150, 60, 20);        
		btnExitConfig.setFocusPainted(false);
		btnExitConfig.setContentAreaFilled(false);
		configInternalFrame.add(btnExitConfig);

		configInternalFrame.setVisible(false);

	}

	//Other Methods      

	public void ShowPanel(String PanelName){

		switch(PanelName) {

		case "students":

			studentsPanel.setVisible(true);
			citiesPanel.setVisible(false);
			usersPanel.setVisible(false);
			teachersPanel.setVisible(false);
			//phasesPanel.setVisible(false);
			coursesPanel.setVisible(false);
			subjectsPanel.setVisible(false);
			importerPanel.setVisible(false);

			break;

		case "cities":

			studentsPanel.setVisible(false);
			citiesPanel.setVisible(true);
			usersPanel.setVisible(false);
			teachersPanel.setVisible(false);
			//phasesPanel.setVisible(false);
			coursesPanel.setVisible(false);
			subjectsPanel.setVisible(false);
			importerPanel.setVisible(false);

			break;

		case "users":

			studentsPanel.setVisible(false);
			citiesPanel.setVisible(false);
			usersPanel.setVisible(true);
			teachersPanel.setVisible(false);
			//phasesPanel.setVisible(false);
			coursesPanel.setVisible(false);
			subjectsPanel.setVisible(false);
			importerPanel.setVisible(false);

			break;

		case "teachers":

			studentsPanel.setVisible(false);
			citiesPanel.setVisible(false);
			usersPanel.setVisible(false);
			teachersPanel.setVisible(true);
			//phasesPanel.setVisible(false);
			coursesPanel.setVisible(false);
			subjectsPanel.setVisible(false);
			importerPanel.setVisible(false);

			break;

		case "phases":

			studentsPanel.setVisible(false);
			citiesPanel.setVisible(false);
			usersPanel.setVisible(false);
			teachersPanel.setVisible(false);
			//phasesPanel.setVisible(true);
			coursesPanel.setVisible(false);
			subjectsPanel.setVisible(false);
			importerPanel.setVisible(false);

			break;

		case "courses":

			studentsPanel.setVisible(false);
			citiesPanel.setVisible(false);
			usersPanel.setVisible(false);
			teachersPanel.setVisible(false);
			//phasesPanel.setVisible(false);
			coursesPanel.setVisible(true);
			subjectsPanel.setVisible(false);
			importerPanel.setVisible(false);

			break;

		case "subjects":

			studentsPanel.setVisible(false);
			citiesPanel.setVisible(false);
			usersPanel.setVisible(false);
			teachersPanel.setVisible(false);
			//phasesPanel.setVisible(false);
			coursesPanel.setVisible(false);
			subjectsPanel.setVisible(true);
			importerPanel.setVisible(false);

			break;

		case "importer":

			studentsPanel.setVisible(false);
			citiesPanel.setVisible(false);
			usersPanel.setVisible(false);
			teachersPanel.setVisible(false);
			//phasesPanel.setVisible(false);
			coursesPanel.setVisible(false);
			subjectsPanel.setVisible(false);
			importerPanel.setVisible(true);

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

	public void ResizeWindow() {

		dispose();
		MainWindowNew mw = new MainWindowNew(perfil);
		mw.setVisible(true);

	}
	
}

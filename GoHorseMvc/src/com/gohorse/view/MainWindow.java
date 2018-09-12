package com.gohorse.view;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
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
import com.gohorse.database.model.Users;
import com.gohorse.lib.FileManipulation;

public class MainWindow extends JFrame {
		
	private JScrollPane scrollUser;
	private JScrollPane scrollStudent;
	private JScrollPane scrollCity;
	private DefaultTableModel modeloCity = new DefaultTableModel() {
		
		String[] cidade = {"Cidade", "Pais", "Estado"};
		
        public int getColumnCount() { 
            return cidade.length; 
        } 
        
 		@Override
		public String getColumnName(int index) {
		    return cidade[index];
		}
		
	};
		
	private DefaultTableModel modeloStudent = new DefaultTableModel() {
		
		String[] estudante = {"Id","Estudante","Data de Nascimento","E-Mail","Sexo","Telefone",
				  "Celular","CEP","N�", "Endere�o", "bairro", "Cidade","Estado","Complemento",
				  "Observa��o"};
		
        public int getColumnCount() { 
            return estudante.length; 
        } 
        
 		@Override
		public String getColumnName(int index) {
		    return estudante[index];
		}
		
	};
		
	private DefaultTableModel modeloUser = new DefaultTableModel() {
		
		String[] usuario = {"Usu�rio", "Senha", "Perfil"};
		
        @Override 
        public int getColumnCount() { 
            return usuario.length; 
        } 
        
 		@Override
		public String getColumnName(int index) {
		    return usuario[index];
		}
		
	};
	
	private JTable tableUser;
	private JTable tableCity;
	private JTable tableStudent;
	private int cont;
	
	private JMenuBar menu ;
	private JMenu mAlunos;
	private JMenu mCidades;
	private JMenu mUsuarios;
	private JMenuItem smListarCidade;
	private JMenuItem smListarAluno;
	private JMenuItem smListarUsuario;
	
	private JButton btnMRegisterCity;
	private JButton btnMRegisterStudent;
	private JButton btnMRegisterUser;
	private JButton btnMEditCity;
	private JButton btnMEditStudent;
	private JButton btnMEditUser;
	private JButton btnMDeleteCity;
	private JButton btnMDeleteStudent;
	private JButton btnMDeleteUser;
	
	private JPanel regCity;
	private JLabel lbCity;
	private JTextField txfCity; 
	private JLabel lbState;
	private JTextField txfState;
	private JLabel lbCountry;
	private JTextField txfCountry;
	private JButton btnCityRegister;
	private JButton btnCityEdit;
	private JButton btnCityWindowExit;
	
	String Sexo[] = { "Masculino", "Feminino" };

	private JPanel regStudent;
	private JLabel lbStudent;
	private JTextField txfStudent;
	private JLabel lbBirthdate;
	private JTextField txfBirthdate;
	private JLabel lbSex;
	private JComboBox<?> cmbSex;
	private JButton btnStudentRegister;
	private JButton btnStudentEdit;
	private JButton btnStudentWindowExit;
	private JLabel lbPhone;
	private JTextField txfPhone;
	private JLabel lbCellphone;
	private JTextField txfCellphone;
	private JLabel lbEmail;
	private JTextField txfEmail;
	private JLabel lbNote;
	private JTextArea txfNote;
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
	
	private JPanel regUser;
	private JLabel lbUser;
	private JTextField txfUser;
	private JLabel lbPassword;
	private JPasswordField txfPassword;
	private JLabel lbType;
	private JComboBox<?> cmbType;
	private JButton btnUserRegister;
	private JButton btnUserWindowExit;
	private JButton btnUserEdit;

	String Tipo[] = { "User", "Admin" };
	boolean acess;
	
	
	public MainWindow (Users user) {
		acess = user.getPerfil().equals(Tipo[1]);
		
		Container c = getContentPane();
	    Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
	    setSize(tela.width, tela.height);
		setTitle("Menu");
		setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        CreateMenucomponents();
        CreateCitiesComponents();
        CreateTable("Cities.txt");
        CreateStudentsComponents();
        CreateTable("Students.txt");
        if(acess) {
        	CreateUsersComponents();
            CreateTable("Users.txt");
        }      
	}

	public void CreateMenucomponents() {
		
		 menu = new  JMenuBar();
		 
		 setJMenuBar(menu);
		 
		 mAlunos 			= new JMenu("Alunos");
		 mCidades			= new JMenu("Cidades");
		 
		 
		 menu.add(mAlunos);
		 menu.add(mCidades);
		 if(acess) {
			 mUsuarios			= new JMenu("Usuarios");
			 menu.add(mUsuarios);
			 smListarUsuario= new JMenuItem(new AbstractAction("Listar") {
					
					@Override
					public void actionPerformed(ActionEvent e) {

						UsersWindow();
						
					}
				 });
			 mUsuarios.add(smListarUsuario);
		 }
		 
		 
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
		 
		 
		 
		 
		 mAlunos.add(smListarAluno);
		 mCidades.add(smListarCidade);
		 
		 
		 
		 
	}
	
	public void CreateCitiesComponents() {
		if(acess) {
			//cities add
			btnMRegisterCity = new JButton(new AbstractAction("Cadastrar Cidade") {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					regCity.setBorder(BorderFactory.createTitledBorder("Cadastrar Cidade"));
					scrollCity.setVisible(false);
					btnMRegisterCity.setVisible(false);
					btnMEditCity.setVisible(false);
			    	btnMDeleteCity.setVisible(false);
			    	regCity.setVisible(true);
			    	btnCityEdit.setVisible(false);
			    	btnCityRegister.setVisible(true);
					
				}
				
			});
			btnMRegisterCity.setBounds(30, 20, 140, 30);    	
			btnMRegisterCity.setFocusPainted(false);
	    	btnMRegisterCity.setContentAreaFilled(false);
			getContentPane().add(btnMRegisterCity);
			
			//cities edit
	    	btnMEditCity = new JButton(new AbstractAction("Editar Cidade") {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					regCity.setBorder(BorderFactory.createTitledBorder("Editar Cidade"));
					btnMRegisterCity.setVisible(false);
					btnMEditCity.setVisible(false);
			    	btnMDeleteCity.setVisible(false);
			    	regCity.setVisible(true);
			    	btnCityRegister.setVisible(false);
			    	btnCityEdit.setVisible(true);
					
				}
				
			});
	    	btnMEditCity.setBounds(230, 20, 140, 30);    	
	    	btnMEditCity.setFocusPainted(false);
	    	btnMEditCity.setContentAreaFilled(false);
	    	getContentPane().add(btnMEditCity);
	    	
			//cities delete
	    	btnMDeleteCity = new JButton(new AbstractAction("Deletar Cidade") {

				@Override
				public void actionPerformed(ActionEvent e) {
					
	                tableCity.setEnabled(true);
					
					tableCity.addKeyListener(new KeyAdapter() {
				         public void keyPressed(KeyEvent e) {
				        	 int linhaSelecionada = -1;
					            linhaSelecionada = tableCity.getSelectedRow();
					            if (linhaSelecionada >= 0) {
					                String city = (String) tableCity.getValueAt(linhaSelecionada, 0);
					                FileManipulation fm = new FileManipulation();
					                try {
										fm.delete("Cities.txt", city);
									} catch (IOException e1) {
									}
					                modeloCity.removeRow(linhaSelecionada);
					            } else {
					               //JOptionPane.showMessageDialog(null, "� neces�rio selecionar uma linha.");
					            }
					           tableCity.setEnabled(false); 
				             }
				            
				           }
				        );
					
				}
				
			});
	    	btnMDeleteCity.setBounds(430, 20, 140, 30); 	
	    	btnMDeleteCity.setFocusPainted(false);
	    	btnMDeleteCity.setContentAreaFilled(false);
	    	getContentPane().add(btnMDeleteCity);
	    	
	    	//hide button after initializing
			btnMRegisterCity.setVisible(false);
	    	btnMEditCity.setVisible(false);
	    	btnMDeleteCity.setVisible(false);
		}
		
		
    	
    	
    	//register panel
    	regCity = new JPanel();
		regCity.setLayout(null);
		regCity.setBounds(200, 80, 210, 280);
		regCity.setBorder(BorderFactory.createTitledBorder("Cidade"));
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
    	
    	if(acess) {
    		//register button TODO registering
        	btnCityRegister = new JButton(new AbstractAction("Cadastrar") {
    			
    			@Override
    			public void actionPerformed(ActionEvent arg0) {
    				Cities city = new Cities(txfCity.getText(),txfState.getText(),txfCountry.getText());
    				try {
    					FileManipulation.insert(city);
    					ClearTable("Cities.txt");
    					CreateTable("Cities.txt");
    					scrollCity.setVisible(true);
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
    				
    				regCity.setVisible(false);
    				btnMRegisterCity.setVisible(true);
    				btnMEditCity.setVisible(false);
    		    	btnMDeleteCity.setVisible(true);
    				
    				
    			}
    		});
        	btnCityRegister.setBounds(93, 230, 95, 20);   	
        	regCity.add(btnCityRegister);
        	btnCityRegister.setFocusPainted(false);
        	btnCityRegister.setContentAreaFilled(false);
        	
        	//edit button TODO PULL DATA TO EDIT
        	btnCityEdit = new JButton(new AbstractAction("Aplicar") {
    			
    			@Override
    			public void actionPerformed(ActionEvent arg0) {
    				
    				//TODO editing registry
    				//
    				
    				regCity.setVisible(false);
    				btnMRegisterCity.setVisible(true);
    				btnMEditCity.setVisible(true);
    		    	btnMDeleteCity.setVisible(true);
    				
    				
    			}
    		});
        	btnCityEdit.setBounds(93, 230, 95, 20);   	
        	regCity.add(btnCityEdit);
        	btnCityEdit.setFocusPainted(false);
        	btnCityEdit.setContentAreaFilled(false);
    	}
    	
    	
    	//window exit button
    	btnCityWindowExit = new JButton(new AbstractAction("Sair") {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				scrollCity.setVisible(true);
				regCity.setVisible(false);
				if(acess) {
					btnMRegisterCity.setVisible(true);
					btnMEditCity.setVisible(false);
			    	btnMDeleteCity.setVisible(true);
					
				}
				
			}
		});
    	btnCityWindowExit.setBounds(23, 230, 60, 20);   	
    	regCity.add(btnCityWindowExit);
    	btnCityWindowExit.setFocusPainted(false);
    	btnCityWindowExit.setContentAreaFilled(false);
    	
    	getContentPane().add(regCity);
    	
	}
	
	public void CreateStudentsComponents() {
		
		//students add
		btnMRegisterStudent = new JButton(new AbstractAction("Cadastrar Aluno") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				regStudent.setBorder(BorderFactory.createTitledBorder("Cadastrar Aluno"));
				btnMRegisterStudent.setVisible(false);
				btnMEditStudent.setVisible(false);
		    	btnMDeleteStudent.setVisible(false);
		    	regStudent.setVisible(true);
		    	btnStudentEdit.setVisible(false);
		    	btnStudentRegister.setVisible(true);		    	
				
			}
			
		});
		btnMRegisterStudent.setBounds(30, 20, 140, 30);   
		btnMRegisterStudent.setFocusPainted(false);
		btnMRegisterStudent.setContentAreaFilled(false);
		getContentPane().add(btnMRegisterStudent);

		
		
		//students edit TO DO PULL DATA TO EDIT
    	btnMEditStudent = new JButton(new AbstractAction("Editar Aluno") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				tableStudent.setEnabled(true);
				
				tableStudent.addKeyListener(new KeyAdapter() {
			         public void keyPressed(KeyEvent e) {
			        	 	try {
			        	 		int linhaSelecionada = -1;
					            linhaSelecionada = tableStudent.getSelectedRow();
					            if (linhaSelecionada >= 0) {
					            
					                Students student = new Students((String) tableStudent.getValueAt(linhaSelecionada, 1),
					                		                         (String)tableStudent.getValueAt(linhaSelecionada, 2),
					                		                         ((char) tableStudent.getValueAt(linhaSelecionada, 4)),
					                		                         (String)tableStudent.getValueAt(linhaSelecionada, 5),
					                		                         (String)tableStudent.getValueAt(linhaSelecionada, 6),
					                		                         (String)tableStudent.getValueAt(linhaSelecionada, 3),
					                		                         (String)tableStudent.getValueAt(linhaSelecionada, 14),
					                		                         (String)tableStudent.getValueAt(linhaSelecionada, 9),
					                		                         (String)tableStudent.getValueAt(linhaSelecionada, 8),
					                		                         (String)tableStudent.getValueAt(linhaSelecionada, 13),
					                		                         (String)tableStudent.getValueAt(linhaSelecionada, 10),
					                		                         (String)tableStudent.getValueAt(linhaSelecionada, 11),
					                		                         (String)tableStudent.getValueAt(linhaSelecionada, 12),
					                		                         (String)tableStudent.getValueAt(linhaSelecionada, 7));
					                student.setStudent_id((Integer) tableStudent.getValueAt(linhaSelecionada, 0));
					                try {
					                	FileManipulation.update(student);
									} catch (IOException e1) {
									}
					            }
			        	 	
				            } 
			        	 	catch(Exception e1) {
				                JOptionPane.showMessageDialog(null, e1.getMessage());
				            }
				           tableStudent.setEnabled(false); 
			             }
			            
			           }
			        );
		    	
			}
			
		});
    	btnMEditStudent.setBounds(230, 20, 140, 30);    	
    	btnMEditStudent.setFocusPainted(false);
    	btnMEditStudent.setContentAreaFilled(false);
    	getContentPane().add(btnMEditStudent);
    	
    	
    	
		//students delete
    	btnMDeleteStudent = new JButton(new AbstractAction("Deletar Aluno") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
                tableStudent.setEnabled(true);
				
				tableStudent.addKeyListener(new KeyAdapter() {
			         public void keyPressed(KeyEvent e) {
			        	 int linhaSelecionada = -1;
				            linhaSelecionada = tableStudent.getSelectedRow();
				            if (linhaSelecionada >= 0) {
				                try {
				                	FileManipulation.delete("Students.txt", tableStudent.getValueAt(linhaSelecionada, 0).toString());
								} catch (IOException e1) {
								}
				                modeloStudent.removeRow(linhaSelecionada);
				            } else {
				                //JOptionPane.showMessageDialog(null, "� neces�rio selecionar uma linha.");
				            }
				           tableStudent.setEnabled(false); 
			             }
			            
			           }
			        );
				
			}
			
		});
    	btnMDeleteStudent.setBounds(430, 20, 140, 30); 	
    	btnMDeleteStudent.setFocusPainted(false);
    	btnMDeleteStudent.setContentAreaFilled(false);
    	getContentPane().add(btnMDeleteStudent);
    	
		
    	//hide button after initializing
		btnMRegisterStudent.setVisible(false);
		btnMEditStudent.setVisible(false);
    	btnMDeleteStudent.setVisible(false);
    	
    	//student form
    	regStudent = new JPanel();
    	regStudent.setLayout(null);
    	regStudent.setBounds(0, 0, 600, 600);
    	regStudent.setBorder(BorderFactory.createTitledBorder("Aluno"));
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
    	lbAdress.setText("Endere�o:");
    	lbAdress.setBounds(270, 40, 125, 20);
    	regStudent.add(lbAdress);
    	
    	txfAdress = new JTextField();
    	txfAdress.setBounds(270, 60, 270, 20);
    	regStudent.add(txfAdress);
    	
    	lbAdressNum = new JLabel();
    	lbAdressNum.setText("N�:");
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
    	lbNote.setText("Observa��es:");
    	lbNote.setBounds(40, 355, 125, 20);
    	regStudent.add(lbNote);
    	
    	txfNote = new JTextArea();
    	txfNote.setBounds(40, 375, 500, 70);
    	txfNote.setBorder(BorderFactory.createEtchedBorder());
    	regStudent.add(txfNote);
    	
    	////register button TODO registering
    	btnStudentRegister = new JButton(new AbstractAction("Cadastrar") {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {;
				
				ClearTable("Students.txt");
				try {
					if(txfStudent.getText().length() < 1) {
						throw new Exception("Aluno n�o pode ser vazio.");
					}
					if(txfBirthdate.getText().length() < 1) {
						throw new Exception("Data de nascimento n�o pode ser vazio.");
					}
					if(txfPhone.getText().length() < 1) {
						throw new Exception("Telefone n�o pode ser vazio.");
					}
					if(txfCellphone.getText().length() < 1) {
						throw new Exception("Celular n�o pode ser vazio.");
					}
					if(txfEmail.getText().length() < 1) {
						throw new Exception("Email n�o pode ser vazio.");
					}
					if(txfAdress.getText().length() < 1) {
						throw new Exception("Endere�o n�o pode ser vazio.");
					}
					if(txfAdressNum.getText().length() < 1) {
						throw new Exception("Numero n�o pode ser vazio.");
					}
					if(txfComplement.getText().length() < 1) {
						throw new Exception("Complemento n�o pode ser vazio.");
					}
					if(txfSuburb.getText().length() < 1) {
						throw new Exception("Bairro n�o pode ser vazio.");
					}
					if(txfSCity.getText().length() < 1) {
						throw new Exception("Cidade n�o pode ser vazio.");
					}
					if(lbStuEstate.getText().length() < 1) {
						throw new Exception("Estado n�o pode ser vazio.");
					}
					if(txfCep.getText().length() < 1) {
						throw new Exception("Cep n�o pode ser vazio.");
					}
					
					Students stu = new Students(txfStudent.getText(),
							txfBirthdate.getText(), 
							((String) cmbSex.getSelectedItem()).charAt(0),
							txfPhone.getText(),
							txfCellphone.getText(),
							txfEmail.getText(),
							txfNote.getText(),
							txfAdress.getText(),
							txfAdressNum.getText(),
							txfComplement.getText(),
							txfSuburb.getText(),
							txfSCity.getText(),
							lbStuEstate.getText(),
							txfCep.getText());

					try {
						FileManipulation.insert(stu);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				
							
				UpdateTable("Students.txt");
				regStudent.setVisible(false);
				btnMRegisterStudent.setVisible(true);
				btnMEditStudent.setVisible(true);
		    	btnMDeleteStudent.setVisible(true);
				
			}
		});
    	btnStudentRegister.setBounds(350, 475, 150, 30);   	
    	regStudent.add(btnStudentRegister);
    	btnStudentRegister.setFocusPainted(false);
    	btnStudentRegister.setContentAreaFilled(false);
    	
    	//button for editing TODO editing
    	btnStudentEdit = new JButton(new AbstractAction("Aplicar") {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				//TODO EDITING FUNCTIONS
				//
				
				ClearTable("Students.txt");
				UpdateTable("Students.txt");
				regStudent.setVisible(false);
				btnMRegisterStudent.setVisible(true);
				btnMEditStudent.setVisible(true);
		    	btnMDeleteStudent.setVisible(true);
				
			}
		});
    	btnStudentEdit.setBounds(350, 475, 150, 30);   	
    	regStudent.add(btnStudentEdit);
    	btnStudentEdit.setFocusPainted(false);
    	btnStudentEdit.setContentAreaFilled(false);
    	
    	//window exit button
    	btnStudentWindowExit = new JButton(new AbstractAction("Sair") {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
								
				regStudent.setVisible(false);
				btnMRegisterStudent.setVisible(true);
				btnMEditStudent.setVisible(true);
		    	btnMDeleteStudent.setVisible(true);
				
			}
		});
    	btnStudentWindowExit.setBounds(100, 475, 150, 30);   	
    	regStudent.add(btnStudentWindowExit);
    	btnStudentWindowExit.setFocusPainted(false);
    	btnStudentWindowExit.setContentAreaFilled(false);
		
    	getContentPane().add(regStudent);
    	
	}
	
	public void CreateUsersComponents() {
		
		//user add
		btnMRegisterUser = new JButton(new AbstractAction("Cadastrar Usuario") {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				regUser.setBorder(BorderFactory.createTitledBorder("Cadastrar Usu�rio"));
				btnMRegisterUser.setVisible(false);
				btnMEditUser.setVisible(false);
				btnMDeleteUser.setVisible(false);
				regUser.setVisible(true);	
				btnUserEdit.setVisible(false);
				btnUserRegister.setVisible(true);
				scrollUser.setVisible(false);
				
			}
			
		});
		btnMRegisterUser.setBounds(30, 20, 140, 30);  
		btnMRegisterUser.setFocusPainted(false);
		btnMRegisterUser.setContentAreaFilled(false);
		getContentPane().add(btnMRegisterUser);
		
		
		//user edit
    	btnMEditUser = new JButton(new AbstractAction("Editar Usuario") {

			@Override
			public void actionPerformed(ActionEvent e) {
				

                tableUser.setEnabled(true);
				
				tableUser.addKeyListener(new KeyAdapter() {
			         public void keyPressed(KeyEvent e) {
			        	 int linhaSelecionada = -1;
				            linhaSelecionada = tableUser.getSelectedRow();
				            if (linhaSelecionada >= 0) {
				            	System.out.println(tableUser.getValueAt(linhaSelecionada, 0));
				            	System.out.println(tableUser.getValueAt(linhaSelecionada, 1));
				            	System.out.println(tableUser.getValueAt(linhaSelecionada, 2));
				            	
				                Users user = new Users((String) tableUser.getValueAt(linhaSelecionada, 0), (String) tableUser.getValueAt(linhaSelecionada, 1), (String) tableUser.getValueAt(linhaSelecionada, 2) );
				                try {
				                	FileManipulation.update(user);
								} catch (IOException e1) {
								}
				            } else {
				                //JOptionPane.showMessageDialog(null, "� neces�rio selecionar uma linha.");
				            }
				           tableUser.setEnabled(false); 
			             }
			            
			           }
			        );
			}
			
		});
    	btnMEditUser.setBounds(230, 20, 140, 30);    
    	btnMEditUser.setFocusPainted(false);
    	btnMEditUser.setContentAreaFilled(false);
    	getContentPane().add(btnMEditUser);
    	
    	
		//user delete
    	btnMDeleteUser = new JButton(new AbstractAction("Deletar Usuario") {

			@Override
			public void actionPerformed(ActionEvent e) {
				tableUser.setEnabled(true);
				
				tableUser.addKeyListener(new KeyAdapter() {
			         public void keyPressed(KeyEvent e) {
			        	 int linhaSelecionada = -1;
				            linhaSelecionada = tableUser.getSelectedRow();
				            if (linhaSelecionada >= 0) {
				                String user = (String) tableUser.getValueAt(linhaSelecionada, 0);
				                FileManipulation fm = new FileManipulation();
				                try {
									fm.delete("Users.txt", user);
								} catch (IOException e1) {
								}
				                modeloUser.removeRow(linhaSelecionada);
				            } else {
				                //JOptionPane.showMessageDialog(null, "� neces�rio selecionar uma linha.");
				            }
				           
				           tableUser.setEnabled(false);
			             }
			           }
			        );
				
			}
			
		});
    	btnMDeleteUser.setBounds(430, 20, 140, 30); 	
    	btnMDeleteUser.setFocusPainted(false);
    	btnMDeleteUser.setContentAreaFilled(false);
    	getContentPane().add(btnMDeleteUser);
		
    	//hide buttons after initializing
    	btnMRegisterUser.setVisible(false);
    	btnMEditUser.setVisible(false);
    	btnMDeleteUser.setVisible(false);
    	
    	//register panel
    	regUser = new JPanel();
    	regUser.setLayout(null);
    	regUser.setBounds(180, 150, 228, 250);
    	regUser.setBorder(BorderFactory.createTitledBorder("Usu�rio"));
		getContentPane().add(regUser);
		regUser.setVisible(false);
		
    	lbUser = new JLabel();
    	lbUser.setText("Usu�rio:");
    	lbUser.setBounds(50, 40, 125, 20);
    	regUser.add(lbUser);
    	
    	txfUser = new JTextField();
    	txfUser.setBounds(50, 60, 125, 20);
    	regUser.add(txfUser);
    	
    	lbPassword = new JLabel();
    	lbPassword.setText("Senha:");
    	lbPassword.setBounds(50, 85, 125, 20);
    	regUser.add(lbPassword);
    	
    	txfPassword = new JPasswordField();
    	txfPassword.setBounds(50, 105, 125, 20);
    	regUser.add(txfPassword);
    	
    	lbType= new JLabel();
    	lbType.setText("Perfil:");
    	lbType.setBounds(50, 130, 125, 20);
    	regUser.add(lbType);
    	
    	cmbType = new JComboBox<>(Tipo);
    	cmbType.setBounds(50, 150, 125, 20);
    	regUser.add(cmbType);
    	
    	//register button TODO registering
    	btnUserRegister = new JButton(new AbstractAction("Cadastrar") {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Users u = new Users(txfUser.getText(),String.valueOf(txfPassword.getPassword()),(String) cmbType.getSelectedItem());
				
				try {
					FileManipulation.insert(u);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ClearTable("Users.txt");
				UpdateTable("Users.txt");
				regUser.setVisible(false);
				btnMRegisterUser.setVisible(true);
				btnMEditUser.setVisible(true);
		    	btnMDeleteUser.setVisible(true);
		    	scrollUser.setVisible(true);
		    	

			}
		});
    	btnUserRegister.setBounds(115, 200, 95, 20);   	
    	regUser.add(btnUserRegister);
    	btnUserRegister.setFocusPainted(false);
    	btnUserRegister.setContentAreaFilled(false);
    	
    	//edit button TODO editing
    	btnUserEdit = new JButton(new AbstractAction("Aplicar") {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				//TODO editing registry
				//
				
				ClearTable("Users.txt");
				UpdateTable("Users.txt");
				regUser.setVisible(false);
				btnMRegisterUser.setVisible(true);
				btnMEditUser.setVisible(true);
		    	btnMDeleteUser.setVisible(true);
		    	scrollUser.setVisible(true);
				
			}
		});
    	btnUserEdit.setBounds(115, 200, 95, 20);   	
    	regUser.add(btnUserEdit);
    	btnUserEdit.setFocusPainted(false);
    	btnUserEdit.setContentAreaFilled(false);
    	
    	//window exit button
    	btnUserWindowExit = new JButton(new AbstractAction("Sair") {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				ClearTable("Users.txt");
				UpdateTable("Users.txt");
				regUser.setVisible(false);
				btnMRegisterUser.setVisible(true);
				btnMEditUser.setVisible(true);
		    	btnMDeleteUser.setVisible(true);
		    	scrollUser.setVisible(true);
		    	
				
			}
		});
    	btnUserWindowExit.setBounds(20, 200, 90, 20); 
    	regUser.add(btnUserWindowExit);
    	btnUserWindowExit.setFocusPainted(false);
    	btnUserWindowExit.setContentAreaFilled(false);
    	
    	getContentPane().add(regUser);
		
	}
	
	public void CitiesWindow() {
		
		regCity.setVisible(false);
		regStudent.setVisible(false);
		
		
		if(acess) {
			regUser.setVisible(false);
			btnMRegisterUser.setVisible(false);
			btnMRegisterStudent.setVisible(false);
			btnMRegisterCity.setVisible(true);
			
			btnMEditUser.setVisible(false);
			btnMEditStudent.setVisible(false);
			btnMEditCity.setVisible(false);
			
			btnMDeleteUser.setVisible(false);
			btnMDeleteStudent.setVisible(false);
			btnMDeleteCity.setVisible(true);
		}
	
		scrollStudent.setVisible(false);
		if(acess) {
			scrollUser.setVisible(false);
		}
		
		scrollCity.setVisible(true);
		
	}

	public void StudentsWindow() {
		
		regCity.setVisible(false);
		regStudent.setVisible(false);
		if(acess) {
			regUser.setVisible(false);
		}
		
		
		if(acess) {
			btnMRegisterUser.setVisible(false);
			btnMRegisterCity.setVisible(false);
			btnMRegisterStudent.setVisible(true);
			
			btnMEditUser.setVisible(false);
			btnMEditCity.setVisible(false);
			btnMEditStudent.setVisible(true);
			
			btnMDeleteUser.setVisible(false);
			btnMDeleteCity.setVisible(false);
			btnMDeleteStudent.setVisible(true);
		}
		
		
		scrollCity.setVisible(false);
		if(acess) {
			scrollUser.setVisible(false);
		}
		
		scrollStudent.setVisible(true);
		
	}

	public void UsersWindow() {
		
		regCity.setVisible(false);
		regStudent.setVisible(false);
		regUser.setVisible(false);
		if(acess) {
			btnMRegisterCity.setVisible(false);
			btnMRegisterStudent.setVisible(false);
			btnMRegisterUser.setVisible(true);
			
			btnMEditCity.setVisible(false);
			btnMEditStudent.setVisible(false);
			btnMEditUser.setVisible(true);
			
			btnMDeleteCity.setVisible(false);
			btnMDeleteStudent.setVisible(false);
			btnMDeleteUser.setVisible(true);
		}
		
		
		scrollCity.setVisible(false);
		scrollStudent.setVisible(false);
		if(acess) {
			scrollUser.setVisible(true);
		}
		
						
	}
	
	/*public void CreateColumn (String coluna, int tamcoluna) {
        modelo.addColumn(coluna);
        table.getColumnModel().getColumn(1).setPreferredWidth(tamcoluna);
		cont++;
	}*/
	
	public void CreateTable(String filename) {
		
        switch(filename.charAt(0)) {
        
    	case 'U':
    	   		
				ArrayList<Users> user;
				 try {
					 
					 user = (ArrayList)FileManipulation.selectAll("Users.txt");
					
					 for (Users c : user) {
						
						 modeloUser.addRow(new Object[]{c.getUser(), c.getPassword(), c.getPerfil()});
	    	            
					 	}
					
				 } 
				 
				 catch (IOException e) {
					 
					JOptionPane.showMessageDialog(null, "Ops!  Ocorreu algum problema, contato o administrador");
					
				 	}
	            
	    		  tableUser = new JTable(modeloUser);	    		    
			      tableUser.setBounds(1, 11, 539, 399);
			      tableUser.setEnabled(false); 
			      
			      scrollUser = new JScrollPane(tableUser);
				  scrollUser.setBounds(30, 100, 540, 400);
			      scrollUser.setViewportView(tableUser);
			      getContentPane().add(scrollUser);
				  tableUser.setVisible(true);
				  scrollUser.setVisible(false);
	                
    	break;
    		
    	case 'S':
    	    	   			
				ArrayList<Students> student;
				try {
					
					student = (ArrayList)FileManipulation.selectAll("Students.txt");
					
					for (Students c : student) {
						modeloStudent.addRow(new Object[]{Integer.toString(c.getStudent_id()),c.getStudent(), c.getBirthdate(), c.getEmail(), c.getSex(), c.getPhone(), c.getCellphone(), c.getCep(), c.getNumber(), c.getAddress(), c.getSuburb(), c.getCity(), c.getEstate(), c.getComplement(), c.getNote()});
	    	        }
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Erro aqui");
					e.printStackTrace();
				}
				
				tableStudent = new JTable(modeloStudent);	    		    
				tableStudent.setBounds(1, 11, 539, 399);
				tableStudent.setEnabled(false);
				
				scrollStudent = new JScrollPane(tableStudent);
				scrollStudent.setBounds(30, 100, 540, 400);	
    		    scrollStudent.setViewportView(tableStudent);
    		    getContentPane().add(scrollStudent);
    		    tableStudent.setVisible(true);
    		    scrollStudent.setVisible(false);
    	        
    		break;
    		
    	case 'C':
    			
				ArrayList<Cities> city;
				try {
					
					city = (ArrayList) FileManipulation.selectAll("Cities.txt");
					 for (Cities c : city) {
						 modeloCity.addRow(new Object[]{c.getCity(), c.getState(), c.getCountry()});
		    	     }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tableCity = new JTable(modeloCity);	    		    
				tableCity.setBounds(1, 11, 539, 399);
				tableCity.setEnabled(false);
    		    
				scrollCity = new JScrollPane(tableCity);
				scrollCity.setBounds(30, 100, 540, 400);				
				scrollCity.setViewportView(tableCity);
				getContentPane().add(scrollCity);
				tableCity.setVisible(true);
    		    scrollCity.setVisible(false);
				
    		break;
    		
        }
        
    }
	
	public void UpdateTable(String filename) {
		
        switch(filename.charAt(0)) {
        
    	case 'U':
    	   		
				ArrayList<Users> user;
				 try {
					 
					 user = (ArrayList)FileManipulation.selectAll("Users.txt");
					 
					 for (Users c : user) {
						
						 modeloUser.addRow(new Object[]{c.getUser(), c.getPassword(), c.getPerfil()});
	    	            
					 	}
					
				 } 
				 
				 catch (IOException e) {
					 
					JOptionPane.showMessageDialog(null, "Erro aqui");
					
				 }               
    	break;
    		
    	case 'S':
    	    	   			
				ArrayList<Students> student;
				try {
					
					student = (ArrayList)FileManipulation.selectAll("Students.txt");
					for (Students c : student) {
						modeloStudent.addRow(new Object[]{c.getStudent_id(),c.getStudent(), c.getBirthdate(), c.getEmail(), c.getSex(), c.getPhone(), c.getCellphone(), c.getCep(), c.getNumber(), c.getAddress(), c.getSuburb(), c.getCity(), c.getEstate(), c.getComplement(), c.getNote()});
	    	        }
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Erro aqui");
					e.printStackTrace();
				}
				    	        
    		break;
    		
    	case 'C':
    			
				ArrayList<Cities> city;
				try {
					modeloCity = null;
					city = (ArrayList) FileManipulation.selectAll("Cities.txt");
					 for (Cities c : city) {
						 modeloCity.addRow(new Object[]{c.getCity(), c.getState(), c.getCountry()});
		    	     }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
    		break;
    		
        }
        
    }
	
	public void ClearTable(String filename) {
		
		int i;
		
		switch(filename.charAt(0)) {
        
    	case 'U':
    	   		
    		for (i = modeloUser.getRowCount() - 1; i >= 0 ; i--) {
						
    			modeloUser.removeRow(i);
    			tableUser.setModel(modeloUser);
						 
				}
             
    	break;
    		
    	case 'S':
    	    	   			
    		for (i = modeloStudent.getRowCount() - 1; i >= 0 ; i--) {
				
    			modeloStudent.removeRow(i);
    			tableStudent.setModel(modeloStudent);
    			
				}
				    	        
    	break;
    		
    	case 'C':
    			
    		for (i = modeloCity.getRowCount() - 1; i >= 0 ; i--) {
				
    			modeloCity.removeRow(i);
    			tableCity.setModel(modeloCity);
						 
				}
				
    		break;
    		
        }
    		
        
        
}

}
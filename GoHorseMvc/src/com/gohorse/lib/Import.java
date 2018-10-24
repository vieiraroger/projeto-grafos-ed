package com.gohorse.lib;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;

import com.gohorse.database.model.Courses;
import com.gohorse.database.model.Phases;
import com.gohorse.database.model.Subjects;
import com.gohorse.database.model.Teachers;

public class Import {
	
	public Courses importFile(String file_uri) {
		//file_uri importacao.txt
		
		try {
			BufferedReader buffRead = new BufferedReader(new FileReader(file_uri));
	        
	        String line = "";
	        line = buffRead.readLine();
	        
	        //FILE
	        if(line == null) {
	        	throw new Exception("ERRO FILE 001: Arquivo se encontra vazio");
	        }
	        
	        //HEADER
	        verifyHeader(line);
	        Courses course = new Courses();
	        course.setName(line.substring(1, 11));
	        
	        line = buffRead.readLine();
	        Integer totalOfLines = 1;
	        LinkedHashSet<Phases> phases_hash_set = new LinkedHashSet<Phases>();
	        Integer in = 0;
	        while (line != null) {
	        	
	        	//RESUMO OPERACAO
	        	if(line.charAt(0) == '9') {
	        		
	        		if(Integer.parseInt(line.substring(1, line.length())) != totalOfLines - 1) {
	        			System.out.println(totalOfLines);
	        			throw new Exception ("Trilho incorreto.");
	        		}
	        		else {
	        			break;
	        		}
	        		
	        	}
	        	
	        	in++;
	        	verifySummaryOperation(line);
	        	
	        	Phases local_phase = new Phases(line.substring(1, 8));
	        	
	        	/* TO DOVERIFICAR */
	        	Integer subjects_defined = Integer.parseInt(line.substring(8,10));
	        	Integer teachers_defined = Integer.parseInt(line.substring(10,12));
	        	Integer teachers_total = 0;
	        	
	        	LinkedHashSet<Subjects> subjects_hash_set = new LinkedHashSet<Subjects>();
	            for(int i=0;i<subjects_defined;i++) {
	            	
	            	String subjectLine = buffRead.readLine();
	            	totalOfLines++;
	            	
	            	verifySubject(subjectLine);
	            	Subjects local_subjects = new Subjects(Integer.parseInt(subjectLine.substring(1,7)),
	            									null,
	            									Integer.parseInt(subjectLine.substring(7,8)));
	            	
	            	Integer subject_teacher = Integer.parseInt(subjectLine.substring(9, 11));

	            	LinkedHashSet<Teachers> teachers_hash_set = new LinkedHashSet<Teachers>();
	            	for(int j=0;j<subject_teacher;j++) {
	    	            
	            		String teacherLine = buffRead.readLine();
	            		totalOfLines++;
	            		verifyTeacher(teacherLine);
	            		System.out.println(teacherLine.length());
	            		if(teacherLine.length() != 43) {
	            			System.out.println(teacherLine);
	            		}
	            		Teachers local_teacher = new Teachers(teacherLine.substring(1, 41),
	            												Integer.parseInt(teacherLine.substring(41, 43)));
	            		
	            		teachers_hash_set.add(local_teacher);
	            	}
	            	
	            	local_subjects.setTeachers(teachers_hash_set);
	            	subjects_hash_set.add(local_subjects);
	            }     	
	            
	            local_phase.setSubjects(subjects_hash_set);
	            
	            phases_hash_set.add(local_phase);

	            line = buffRead.readLine();
	            totalOfLines++;
	        }
	        buffRead.close();
	        
	        course.setPhases(phases_hash_set);
	        
	        return course;
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return null;
	}
	
	//TODO um for para verificar onde Ã© para ter numero
	
	private void verifyHeader(String header) throws Exception {
		//TODO SEQUENCIAL
		if(header.length() != 43) {
			System.out.println(header);
        	throw new Exception("ERRO HEADER 001: Tamanho de header invalido.");
        }
        
        if(header.charAt(0) != '0') {
        	throw new Exception("ERRO HEADER 002: Tipo de Registro Errado.");
        }
        
        verifyDate(header.substring(11,19));
        
        if(!header.substring(40,43).equals("001")) {
        	throw new Exception("ERRO HEADER 003: VersÃ£o do Layout Invalido.");
        }
        
        
	}
	
	private void verifyDate(String date) throws Exception {
		Integer day = Integer.parseInt(date.substring(6,8));
		Integer year = Integer.parseInt(date.substring(0,4));
		boolean verify = false;
		switch(Integer.parseInt(date.substring(4,6))) {
			case 1:
				if(day > 31) {
					verify = true;
				}
				break;
			case 2:
				
				if(year%4 == 0 && day > 29) {
					verify = true;
				}
				else if(year%4 != 0 && day > 28) {
					verify = true;
				}
				
				break;
			case 3:
				if(day > 31) {
					verify = true;
				}
				break;
			case 4:
				if(day > 30) {
					verify = true;
				}
				break;
			case 5:
				if(day > 31) {
					verify = true;
				}
				break;
			case 6:
				if(day > 30) {
					verify = true;
				}
				break;
			case 7:
				if(day > 31) {
					verify = true;
				}
				break;
			case 8:
				if(day > 31) {
					verify = true;
				}
				break;
			case 9:
				if(day > 30) {
					verify = true;
				}
				break;
			case 10:
				if(day > 31) {
					verify = true;
				}
				break;
			case 11:
				if(day > 30) {
					verify = true;
				}
				break;
			case 12:
				if(day > 31) {
					verify = true;
				}
				break;
			default: verify = true;
				
		}
		if(verify) {
			throw new Exception("ERRO DATA 001: Data invalida.");
		}
	}
	
	private void verifySummaryOperation(String summary) throws Exception {
		
		if(summary.length() != 12) {
        	throw new Exception("ERRO R. DE OPERACAO 001: Tamanho de resumo invalido.");
        }
		
		if(summary.charAt(0) != '1') {
			throw new Exception("ERRO R. DE OPERACAO 002:");
		}
	}

	private void verifySubject(String subject) throws Exception {
		if(subject.length() != 11) {
        	throw new Exception("ERRO MATERIA 001: Tamanho da materia invalido.");
        }
        
        if(subject.charAt(0) != '2') {
        	throw new Exception("ERRO MATERIA 002: Tipo de Registro Errado.");
        }
	}
	
	private void verifyTeacher(String teacher) throws Exception {
		if(teacher.length() != 43) {
        	throw new Exception("ERRO PROFESSOR 001: Tamanho do professor invalido.");
        }
        
        if(teacher.charAt(0) != '3') {
        	throw new Exception("ERRO PROFESSOR 002: Tipo de Registro Errado.");
        }
	}
	
}

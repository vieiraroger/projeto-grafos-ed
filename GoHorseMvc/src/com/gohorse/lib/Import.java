package com.gohorse.lib;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.gohorse.database.model.Courses;

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
	        
	        while (line != null) {
	        	//RESUMO OPERACAO
	        	line = buffRead.readLine();
	        	verifySummaryOperation(line);
	            
	        }
	        buffRead.close();
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return null;
	}
	
	private void verifyHeader(String header) throws Exception {
		//TODO SEQUENCIAL
		if(header.length() != 43) {
        	throw new Exception("ERRO HEADER 001: Tamanho de header invalido.");
        }
        
        if(header.charAt(0) != '0') {
        	throw new Exception("ERRO HEADER 002: Tipo de Registro Errado.");
        }
        
        verifyDate(header.substring(11,19));
        
        if(!header.substring(40,43).equals("001")) {
        	throw new Exception("ERRO HEADER 003: Versão do Layout Invalido.");
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
}

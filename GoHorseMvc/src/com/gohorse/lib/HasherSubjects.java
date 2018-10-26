package com.gohorse.lib;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedHashSet;

import com.gohorse.database.model.Courses;
import com.gohorse.database.model.Phases;
import com.gohorse.database.model.Subjects;
import com.gohorse.database.model.Teachers;

public class HasherSubjects {

	HashMap<Integer, String> subjectsName = new HashMap<Integer, String>();
	
	public HasherSubjects(String file_uri) {
		importSubjects(file_uri);
	}
	
	public String getSubjectName(Integer code) {
		if(subjectsName.containsKey(code)) {
			return (String) subjectsName.get(code);
		}
		
		return "Nao Atribuido.";
	}
	
	/* Arquivo deve estar na mesma pasta do Hasher */
	private void importSubjects(String file_uri) {
		
		try {
			BufferedReader buffRead = new BufferedReader(new FileReader(file_uri));

	        String line = "";
	        line = buffRead.readLine();
	        
	        while (line != null) {
	        	
	        	String[] ss = line.split("@");
	        	if(ss.length == 2) {
	        		System.out.print(ss[0] + " ");
	        		System.out.println(ss[1]);
	        		subjectsName.put(Integer.parseInt(ss[0]), ss[1]);
	        	}
	        	else {
	        		throw new Exception("Erro ao ler arquivo hash.");
	        	}
	        	
	        	line = buffRead.readLine();
	        }
	        
	        buffRead.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	        
	        
	        return;
	}
}

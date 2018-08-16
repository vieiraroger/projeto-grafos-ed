package com.gohorse.lib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import com.gohorse.database.model.Cities;
import com.gohorse.database.model.Students;
import com.gohorse.database.model.Users;

/**
 * @author Roger Vieira
 */
public class FileManipulation {
	
	private static String DIVISOR = "@";
	
	/**
	 * @param file name and a string from some file.txt
	 * @return ArrayList of some specify object 
	 */
	private static Object createObject(String filename,String s) {
		Object obj;
		String[] data = s.split(DIVISOR);
		switch(filename.charAt(0)) {
			case 'U':
				obj = new Users(data[0],data[1],data[2]); //create constructor
				break;
			case 'C':
				obj = new Cities(data[0],data[1],data[2]);
				break;
			case 'S':
				obj = new Students(data[0],new Date(0),data[3].charAt(0),
									data[4],
									data[5],data[6],data[7],data[8],
									data[9],data[10],data[11],data[12],
									null,data[14]);
				break;
			default:
				return null;
		}
		
		return obj;
	}
	
	/**
	 * @param filename
     * @param primary key of an object
     * @return one object or null
     * @throws IOException 
     */
    public static Object select(String filename,String primaryKey) throws IOException  {
    	BufferedReader buffRead = new BufferedReader(new FileReader(filename));

        String line = "";
        line = buffRead.readLine();
        while (line != null) {
            
        	String[] aux = line.split(DIVISOR);
        	
        	if(aux[0].equals(primaryKey)) {
        		buffRead.close();
        		return createObject(filename, line);
        	}
            
            line = buffRead.readLine();
        }
        
        buffRead.close();
    	return null;
    }
    
	/**
	 * @param file name where you want to read
	 * @return ArrayList of some specify object 
	 * @throws IOException 
	 */
	public static ArrayList<Object> selectAll(String filename) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(filename));
        ArrayList<Object> list = new ArrayList<Object>();
        
        String line = "";
        line = buffRead.readLine();
        while (line != null) {
        	
        	list.add(createObject(filename,line));         
            line = buffRead.readLine();
        }
        
        buffRead.close();
        return list;
    }
	
	/**
     * @param filename
     * @param primary key of an object
     * @throws IOException 
     */
    public static void delete(String filename,String primaryKey) throws IOException {
    	
    	ArrayList<Object> list = selectAll(filename); //save the lines
    	
    	//clean the file
    	BufferedWriter buffWrite = new BufferedWriter(new FileWriter(filename));
    	buffWrite.write("");
    	buffWrite.close();
    	
    	//re-write the lines
    	switch(filename.charAt(0)) {
	    	case 'U':
	    		for(int i=0;i<list.size();i++) {
	    			if(!(((Users) list.get(i)).getUser().equals(primaryKey))) {
	    				insert((Users) list.get(i));
	    			}
	        	}
	    		break;
	    	case 'S':
	    		for(int i=0;i<list.size();i++) {
	    			Students stu = (Students) list.get(i);
	    			Integer id = stu.getStudent_id();
	    			if(id.toString().equals(primaryKey)) {
	    				insert((Students) list.get(i));
	    			}
	        	}
	    		break;
	    	case 'C':
	    		for(int i=0;i<list.size();i++) {
	    			if(!(((Cities) list.get(i)).getCity().equals(primaryKey))) {
	    				insert((Cities) list.get(i));
	    			}
	        	}
	    		break;
    	}
    	   
    }
	
	/** 
	 * @param Users object
	 * @throws IOException 
	 */
    public static void insert(Users obj) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("Users.txt",true));
        String line = "";
        
        line = obj.getUser() + DIVISOR + obj.getPassword() + DIVISOR + obj.getPerfil();
        
        buffWrite.append(line + "\n");
        buffWrite.close();
        
        return;
    }
    
    /** 
	 * @param Cities object
	 * @throws IOException 
	 */
    public static void insert(Cities obj) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("Cities",true));
        String line = "";
        
        line = obj.getCity() + DIVISOR + obj.getState() + DIVISOR + obj.getCountry();
        
        buffWrite.append(line + "\n");
        buffWrite.close();
        
        return;
    }
    
    /** 
	 * @param Students object
	 * @throws IOException 
	 */
    public static Integer insert(Students obj) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("Students.txt",true));
        String line = "";
        Integer id = 0;
        
        /* get last id */
        BufferedReader buffRead = new BufferedReader(new FileReader("Students.txt"));

        line = buffRead.readLine();
        while (line != null) {
            
        	String[] aux = line.split(DIVISOR);
        	
        	id = Integer.parseInt(aux[0]);
            
            line = buffRead.readLine();
        }
        
        buffRead.close();
         
        line = id + DIVISOR
        	+ obj.getStudent() + DIVISOR
        	+ obj.getBirthdate() + DIVISOR
        	+ obj.getSex()  + DIVISOR
        	+ obj.getPhone()  + DIVISOR
        	+ obj.getCellphone()  + DIVISOR
        	+ obj.getEmail() + DIVISOR
        	+ obj.getObservacao()  + DIVISOR
        	+ obj.getEndereco()  + DIVISOR
        	+ obj.getNumero()  + DIVISOR
        	+ obj.getBairro() + DIVISOR
        	+ obj.getCidade() + DIVISOR
        	+ obj.getEstado()  + DIVISOR
        	+ obj.getCep();

        buffWrite.append(line + "\n");
        buffWrite.close();
        
        return id;
    }
	
    /**
     * @param User object
     * @throws IOException 
     * 
     */
    public static void update(Users obj) throws IOException {
    	delete("Users.txt",obj.getUser());
    	insert(obj);
    }
    
    /**
     * @param Citie object
     * @throws IOException 
     * 
     */
    /* We cant update a city
    public static void update(Cities obj) throws IOException {
    	delete("Cities.txt",obj.getCity());
    	insert(obj);
    }
    */
    
    /**
     * @param Student object
     * @throws IOException 
     * 
     */
    public static void update(Students obj) throws IOException {
    	
    	delete("Students.txt",obj.getStudent_id().toString());
    	insert(obj);
    }
    

}

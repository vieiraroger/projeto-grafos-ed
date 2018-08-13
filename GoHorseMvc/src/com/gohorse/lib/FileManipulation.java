package com.gohorse.lib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.gohorse.database.model.Cities;
import com.gohorse.database.model.Students;
import com.gohorse.database.model.Users;

/**
 * @author Roger Vieira
 */
public class FileManipulation {
	
	
	/**
	 * @param file name and a string from some file.txt
	 * @return ArrayList of some specify object 
	 */
	private static Object createObject(String filename,String s) {
		Object obj;
		String[] data = s.split("@");
		switch(filename.charAt(0)) {
			case 'U':
				obj = new Users(data[0],data[1],data[2]); //create constructor
				
				break;
			case 'C':
				obj = new Cities(data[0],data[1],data[2]);
			default:
				return null;
		}
		
		return obj;
	}
	
	/**
	 * @param filename
     * @param primary key of an object
     * @return one object
     * @throws IOException 
     */
    public static Object select(String filename,String primaryKey) throws IOException  {
    	BufferedReader buffRead = new BufferedReader(new FileReader(filename));

        String line = "";
        line = buffRead.readLine();
        while (true) {
            if (line != null) {
            	String[] aux = line.split("@");
            	
            	if(aux[0].equals(primaryKey)) {
            		buffRead.close();
            		return createObject(filename, line);
            	}
            } 
            else {
                break;
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
        while (true) {
            if (line != null) {
            	list.add(createObject(filename,line));
            } 
            else {
                break;
            }
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
	    			/*if(!(((Students) list.get(i)).getId.equals(primaryKey))) {
	    				insert((Students) list.get(i));
	    			}*/
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
        
        line = obj.getUser() + "@" + obj.getPassword() + "@" + obj.getPerfil();
        
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
        
        line = obj.getCity() + "@" + obj.getState() + "@" + obj.getCountry();
        
        buffWrite.append(line + "\n");
        buffWrite.close();
        
        return;
    }
    
    /** 
	 * @param Students object
	 * @throws IOException 
	 */
    public static void insert(Students obj) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("Students.txt",true));
        String line = "";

        buffWrite.append(line + "\n");
        buffWrite.close();
        
        return;
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
    public static void update(Cities obj) throws IOException {
    	delete("Cities.txt",obj.getCity());
    	insert(obj);
    }
    
    /**
     * @param Student object
     * @throws IOException 
     * 
     */
    public static void update(Students obj) throws IOException {
    	//delete("Students.txt",obj.Id);
    	insert(obj);
    }
    

}

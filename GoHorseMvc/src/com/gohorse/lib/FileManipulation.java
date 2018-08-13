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
	 * @param file name and a string 
	 from some file.txt
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
     * @param primary key of an object
     * @return one object
     */
    public static Object select(String filename,String primaryKey) {
    	
    	return null;
    }
    
	/**
	 * @param file name where you want to read
	 * @return ArrayList of some specify object 
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
	 * @param Users object
	 */
    public static void insert(Users obj) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("Users",true));
        String line = "";
        
        line = obj.getUser() + "@" + obj.getPassword() + "@" + obj.getPerfil();
        
        buffWrite.append(line + "\n");
        buffWrite.close();
        
        return;
    }
    
    /** 
	 * @param Cities object
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
	 */
    public static void insert(Students obj) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("Students",true));
        String line = "";
        
        
        
        buffWrite.append(line + "\n");
        buffWrite.close();
        
        return;
    }
	
    /**
     * @param primary key of an object
     * @return boolean
     */
    public static boolean update(String filename,String primaryKey) {
    	
    	return true;
    }
    
}

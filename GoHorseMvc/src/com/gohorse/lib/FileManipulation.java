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
				obj = new Users(); //create constructor
				
				((Users) obj).setPassword(data[1]);
				((Users) obj).setPerfil(data[2]);
				((Users) obj).setUser(data[0]);
				break;
			case 'C':
				obj = new Cities();
				((Cities) obj).setCity(data[0]);
				((Cities) obj).setState(data[1]);
				((Cities) obj).setCountry(data[2]);
			default:
				return null;
		}
		
		return obj;
	}
	
	/**
	 * @param file name where you want to read
	 * @return ArrayList of some specify object 
	 */
	public static ArrayList<Object> read(String filename) throws IOException {
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
	 * @param file name 
	 * @param Users object
	 */
    public static void write(String filename,Users obj) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(filename,true));
        String line = "";
        
        line = obj.getUser() + "@" + obj.getPassword() + "@" + obj.getPerfil();
        
        buffWrite.append(line + "\n");
        buffWrite.close();
        
        return;
    }
    
    /** 
	 * @param file name 
	 * @param Cities object
	 */
    public static void write(String filename,Cities obj) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(filename,true));
        String line = "";
        
        line = obj.getCity() + "@" + obj.getState() + "@" + obj.getCountry();
        
        buffWrite.append(line + "\n");
        buffWrite.close();
        
        return;
    }
    
    /** 
	 * @param file name 
	 * @param Students object
	 */
    public static void write(String filename,Students obj) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(filename,true));
        String line = "";
        
        
        
        buffWrite.append(line + "\n");
        buffWrite.close();
        
        return;
    }
	
}

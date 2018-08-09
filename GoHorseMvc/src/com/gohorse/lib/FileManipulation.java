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

public class FileManipulation {
	
	
	
	private Object createObject(String s,String filename) {
		Object obj;
		
		switch(filename.charAt(0)) {
			case 'U':
				obj = new Users();
				break;
			default:
				return null;
		}
		
		return obj;
	}
	
	public static ArrayList<Object> read(String filename) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(filename));
        
        String line = "";
        while (true) {
            if (line != null) {
                System.out.println(line);
 
            } 
            else {
                break;
            }
            line = buffRead.readLine();
        }
        
        ArrayList<Object> list = new ArrayList<Object>();
        buffRead.close();
        
        return list;
    }
 
    public static boolean write(String filename,Users obj) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(filename,true));
        String line = "";
        
        line = obj.getuser();
        line += '@';
        line += obj.getpassword(); //make md5 hash
        line += '@';
        line += obj.getperfil();
        
        buffWrite.append(line + "\n");
        buffWrite.close();
        
        return true;
    }
    
    public static boolean write(String filename,Cities obj) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(filename));
        String line = "";
        
        
        
        buffWrite.append(line + "\n");
        buffWrite.close();
        
        return true;
    }
    
    public static boolean write(String filename,Students obj) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(filename));
        String line = "";
        
        
        
        buffWrite.append(line + "\n");
        buffWrite.close();
        
        return true;
    }
	
}

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import com.gohorse.database.model.Cities;
import com.gohorse.database.model.Students;
import com.gohorse.database.model.Users;
import com.gohorse.lib.FileManipulation;

public class Tester {

	public static void main(String[] args) throws IOException {
		char[] estado = new char[3];
		estado[0] = 'S';
		estado[1] = 'C';
		Students s = new Students("romulo", new Date(0), 'f', 
									"48999783992", "48999783992", 
									"romulogay", "Nenhuma", 
									"rua truco", "24", "teste", 
									"Rio Maina", "Criciuma", estado, "52078520");
		FileManipulation.insert(s);
		ArrayList<Object> stu = FileManipulation.selectAll("Students.txt");
		
		for(int i=0;i<stu.size();i++) {
			Students local = (Students) stu.get(i);
			System.out.println(local.getBirthdate());
		}
		
	}

}

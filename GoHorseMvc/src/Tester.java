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
		Students s = new Students("roger", new Date(0), 'M', 
									"48999783992", "48999783992", 
									"rogeramorinvieiraa", "Nenhuma", 
									"Rodovia SC447", "95", "Tobias", 
									"Rio Maina", "Criciuma", estado, "88817440");
		//FileManipulation.insert(s);
		ArrayList<Object> stu = FileManipulation.selectAll("Students.txt");
		
		for(int i=0;i<stu.size();i++) {
			Students local = (Students) stu.get(i);
			System.out.println(local.getBirthdate());
		}
		
	}

}

import java.io.IOException;
import java.util.ArrayList;

import com.gohorse.database.model.Cities;
import com.gohorse.database.model.Users;
import com.gohorse.lib.FileManipulation;

public class Tester {

	public static void main(String[] args) throws IOException {
		ArrayList<Object> users = FileManipulation.selectAll("Users.txt");
		
		for(int i=0;i<users.size();i++) {
			System.out.println(((Users) users.get(i)).getUser());
		}
		
		Users t = (Users) FileManipulation.select("Users.txt","hello");
		System.out.println(t.getPerfil());
		FileManipulation.delete("Users.txt", "roger");
	}

}

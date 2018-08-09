import java.io.IOException;
import java.util.ArrayList;

import com.gohorse.database.model.Users;
import com.gohorse.lib.FileManipulation;

public class Tester {

	public static void main(String[] args) throws IOException {
		ArrayList<Object> users = FileManipulation.read("Users.txt");
		
		for(int i=0;i<users.size();i++) {
			System.out.println(((Users) users.get(i)).getpassword());
		}
		
		
		/*
		Users roger = new Users();
		
		roger.setpassword("romuloviado");
		roger.setperfil("truco");
		roger.setuser("hello");
		
		FileManipulation.write("Users.txt", roger);
		*/
	
		
	}

}

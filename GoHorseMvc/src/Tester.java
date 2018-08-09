import java.io.IOException;

import com.gohorse.database.model.Users;
import com.gohorse.lib.FileManipulation;

public class Tester {

	public static void main(String[] args) throws IOException {
		FileManipulation fm = new FileManipulation();
		
		fm.read("Users.txt");
		
		Users roger = new Users();
		
		roger.setpassword("123456");
		roger.setperfil("perfil");
		roger.setuser("roger");
		
		System.out.println(roger.getpassword());
		fm.write("Users.txt", roger);
		
		fm.read("Users.txt");
		
	}

}

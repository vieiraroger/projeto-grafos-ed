import com.gohorse.database.model.Courses;
import com.gohorse.lib.Import;

public class tester {
	public static void main(String[] args) {
		Import imp = new Import();
		Courses course = imp.importFile("D:\\Dados Usuario\\Desktop\\imports\\import.txt.txt");

		if(course != null) {
			System.out.println("test" +course.getName());
		}
		else {
			System.out.println("ERRO");
		}
		
	}
}

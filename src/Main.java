import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		DAO dao = DAO.getInstance();
		String Nickname = dao.getMember("cjfsud23");
		System.out.println(Nickname);
	}
}
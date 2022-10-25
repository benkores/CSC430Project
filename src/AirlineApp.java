import java.io.File;
import java.io.IOException;
public class AirlineApp {
	
	public static void main (String[] args) {
		new SQLConnect();
		checkLogin();
	}
	
	public static void checkLogin() {
		//check if user is not already logged in
		File login_auto = new File("login_info.txt");
		if (!(login_auto.exists())) {
			try {
				login_auto.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//launch User login/Register page here
		} else {
			
		}
	}
}

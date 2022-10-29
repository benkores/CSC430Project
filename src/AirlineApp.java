import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;

public class AirlineApp {



	public static void main(String[] args) {
		new SQLConnect();
		checkLoginStatus();
	}

	// This should be the User Login/Register GUI window (if user is not already logged,
	// otherwise, skip to main window)
	public static void checkLoginStatus() {
		Scanner stdin = new Scanner(System.in);
		File login_auto = new File("login_username.txt");
		if (!(login_auto.exists())) {
			System.out.print("Enter username:");
			String username = stdin.nextLine();
			System.out.print("Enter password:");
			String password = stdin.nextLine();
			System.out.print("Login or Register (l/r)?");
			char option = stdin.nextLine().charAt(0);
			if (option == 'r') {
				boolean exists = SQLConnect.checkAccountExists(username);
				if (exists == true) {
					System.err.println("Account already exists!");
					checkLoginStatus();
				} else {
					SQLConnect.insertAccount(username, password);
					checkLoginStatus();
				}
			} else if (option == 'l') {
				boolean valid = SQLConnect.authenticateAccount(username, password);
				if (!(valid)) {
					System.err.println("Incorrect username/password!");
					checkLoginStatus();
				}
				try {
					login_auto.createNewFile();
					FileWriter writer = new FileWriter("login_username.txt");
					writer.write(username);
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				new MainWindow(username);
			}
		} else {
			Scanner reader;
			try {
				reader = new Scanner(login_auto);
				String username = null;
				while (reader.hasNextLine()) {
					username = reader.nextLine();
				}
				boolean exists = SQLConnect.checkAccountExists(username);
				if (exists) {
					new MainWindow(username);
				} else {
					reader.close();
					login_auto.delete();
					checkLoginStatus();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainWindow {

	// There should be a new GUI window here that represents the main screen of the
	// program that
	// provides the user with options to choose from
	public MainWindow(File login_auto) {
		try {
			Scanner reader = new Scanner(login_auto);
			String username = null;
			while (reader.hasNextLine()) {
				username = reader.nextLine();
			}
			System.out.println("Welcome, " + username);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}

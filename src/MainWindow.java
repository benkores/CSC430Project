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
		showOptions();
	}
	
	//part of above GUI window
	public void showOptions() {
		Scanner stdin = new Scanner(System.in);
		int option = 0;
		System.out.println("What would you like to so today? ");
		System.out.println("1 - Search for and book a flight");
		System.out.println("2 - View Boarding Passes");
		System.out.println("3 - Exit");
		System.out.print("Enter an option(1-3): ");
		option = stdin.nextInt();
		if (option == 1) {
			new SearchFlights();
		} else if (option == 2) {
			new BoardingPass();
		} else {
			SQLConnect.shutdown();
		}
	}
	
	

}

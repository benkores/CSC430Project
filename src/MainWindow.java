
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainWindow {
	
	private String username;

	public MainWindow(String username) {
		this.username = username;
		showOptions();
	}
	
	public void showOptions() {
		Scanner stdin = new Scanner(System.in);
		int option;
		while (true) {
		option = 0;
		System.out.println("Welcome, " + username);
		System.out.println("What would you like to do today? ");
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
			break;
		}
		}
	}
	
	

}


import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
public class SearchFlights {
	
	private ArrayList<ArrayList<String>> from_airports = new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<String>> to_airports = new ArrayList<ArrayList<String>>();
	private String from_airport;
	private String to_airport;
	private String departure_date;
	private String return_date;
	
	public SearchFlights() {
		displayFlightSearch();
	}
	
	
	private void displayFlightSearch() {
		Scanner stdin = new Scanner(System.in);
		System.out.println("1 - One-way");
		System.out.println("2- Round-trip");
		System.out.println("Enter an option(1-2): ");
		int option = stdin.nextInt();
		stdin = new Scanner(System.in);
		from_airports = SQLConnect.getFromAirports();
		System.out.println("From:");
		for (int i = 0; i < from_airports.size() - 1; i++) {
			System.out.println(from_airports.get(i).get(0) + " - " + from_airports.get(i+1).get(1));
		}
		System.out.print("Enter 3-letter airport code: ");
		from_airport = stdin.nextLine().toUpperCase().replaceAll("\"", "");
		to_airports = SQLConnect.getToAirports(from_airport);
		stdin = new Scanner(System.in);
		System.out.println("To:");
		for (int i = 0; i < to_airports.size() - 1; i++) {
			System.out.println(to_airports.get(i).get(0) + " - " + to_airports.get(i).get(1));
		}
		System.out.print("Enter 3-letter airport code: ");
		to_airport = stdin.nextLine().toUpperCase().replaceAll("\"","");
		if (option == 1) {
			System.out.print("Departure date (MM/DD/YYYY):" );
			departure_date = stdin.nextLine();
			new FlightResults(from_airport, to_airport, departure_date);
		} else if (option == 2) {
			System.out.print("Departure date (MM/DD/YYYY): ");
			departure_date = stdin.nextLine();
			System.out.print("Return date (MM/DD/YYYY): ");
			return_date = stdin.nextLine();
			new FlightResults(from_airport, to_airport, departure_date, return_date);
		}
		}
	}

import java.util.Scanner;
public class SearchFlights {
	
	public SearchFlights() {
		displayFlightSearch();
	}
	
	
	private void displayFlightSearch() {
		Scanner stdin = new Scanner(System.in);
		System.out.println("1 - One-way");
		System.out.println("2- Round-trip");
		System.out.println("Enter an option(1-2): ");
		int option = stdin.nextInt();
		String from_airport = SQLConnect.selectFromAirport();
		String to_airport = SQLConnect.selectToAirport(from_airport);
		String departure_date;
		String return_date;
		stdin = new Scanner(System.in);
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

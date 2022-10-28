import java.util.ArrayList;
import java.util.Scanner;

public class FlightResults {

	private String from_airport;
	private String to_airport;
	private String departure_date;
	private String return_date;
	private ArrayList<ArrayList<String>> flights;
	private ArrayList<ArrayList<String>> flights_return;
	private ArrayList<String> first_flight = new ArrayList<String>();
	private ArrayList<String> second_flight = new ArrayList<String>();

	public FlightResults(String from_airport, String to_airport, String departure_date) {
		this.from_airport = from_airport;
		this.to_airport = to_airport;
		this.departure_date = departure_date;
		flights = SQLConnect.getFlights(from_airport, to_airport, departure_date);
		OneWay();
	}

	public FlightResults(String from_airport, String to_airport, String departure_date, String return_date) {
		this.from_airport = from_airport;
		this.to_airport = to_airport;
		this.departure_date = departure_date;
		this.return_date = return_date;
		flights = SQLConnect.getFlights(from_airport, to_airport, departure_date);
		flights_return = SQLConnect.getFlights(to_airport, from_airport, return_date);
		RoundTrip();
	}
	
	public void OneWay() {
		if (flights.size() == 0) {
			System.out.println("No flight results found");
		} else {
			System.out.println("Select first flight: ");
			first_flight = selectFlight(flights);
		}
	}
	
	public void RoundTrip() {
		if (flights.size() == 0 || flights_return.size() == 0) {
			System.out.println("No flight results found");
		} else {
			System.out.println("Select first flight: ");
			first_flight = selectFlight(flights);
			System.out.println("Select second flight: ");
			second_flight = selectFlight(flights_return);
		}
	}

	private ArrayList<String> selectFlight(ArrayList<ArrayList<String>> flights) {
		Scanner stdin = new Scanner(System.in);
		int flight_option = 0;
		if (flights.size() == 0) {
			System.out.println("No flight results found.");
			return null;
		} else {
			ArrayList<String> flight = new ArrayList<String>();
			int i = 0;
			while (i < flights.size()) {
				System.out.println("\nFlight " + (i + 1));
				System.out.println(flights.get(i).get(0) + " ---> " + flights.get(i).get(1));
				System.out.println("Departs: " + flights.get(i).get(2) + " " + flights.get(i).get(3));
				System.out.println("Arrives: " + flights.get(i).get(4) + " " + flights.get(i).get(5));
				System.out.println("Gate: " + flights.get(i).get(6));
				System.out.println("Terminal: " + flights.get(i).get(7));
				System.out.println("Boarding begins: " + flights.get(i).get(8));
				System.out.println("Boarding ends: " + flights.get(i).get(9));
				System.out.println(
						"Number of seats: " + flights.get(i).get(10) + " first-class seats, " + flights.get(i).get(11)
								+ " business-class seats, " + flights.get(i).get(12) + " economy seats");
				System.out.println("First-class ticket price: $" + flights.get(i).get(13));
				System.out.println("Business-class ticket price: $" + flights.get(i).get(14));
				System.out.println("Economy ticket price: $" + flights.get(i).get(15));
				i++;
			}
			System.out.println("Enter a flight option(1-" + i + "): ");
			flight_option = stdin.nextInt() - 1;
			flight.add(flights.get(flight_option).get(0));
			flight.add(flights.get(flight_option).get(1));
			flight.add(flights.get(flight_option).get(2));
			flight.add(flights.get(flight_option).get(3));
			flight.add(flights.get(flight_option).get(4));
			flight.add(flights.get(flight_option).get(5));
			flight.add(flights.get(flight_option).get(6));
			flight.add(flights.get(flight_option).get(7));
			flight.add(flights.get(flight_option).get(8));
			flight.add(flights.get(flight_option).get(9));
			flight.add(flights.get(flight_option).get(10));
			flight.add(flights.get(flight_option).get(11));
			flight.add(flights.get(flight_option).get(12));
			flight.add(flights.get(flight_option).get(13));
			flight.add(flights.get(flight_option).get(14));
			flight.add(flights.get(flight_option).get(15));
			return flight;
		}
	}
}

public class FlightResults {

	private String from_airport;
	private String to_airport;
	private String departure_date;
	private String return_date;

	public FlightResults(String from_airport, String to_airport, String departure_date) {
		this.from_airport = from_airport;
		this.to_airport = to_airport;
		this.departure_date = departure_date;
		displayOneWay();
	}

	public FlightResults(String from_airport, String to_airport, String departure_date, String return_date) {
		this.from_airport = from_airport;
		this.to_airport = to_airport;
		this.departure_date = departure_date;
		this.return_date = return_date;
		displayRoundTrip();
	}
	
	private void displayOneWay() {
		SQLConnect.selectBooking(from_airport, to_airport, departure_date);
	}
	
	private void displayRoundTrip() {
	}
}

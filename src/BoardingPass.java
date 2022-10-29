import java.util.ArrayList;

public class BoardingPass {
	private ArrayList<ArrayList<String>> bookings;
	
	public BoardingPass() {
		bookings = SQLConnect.getUserBookings();
		displayPasses();
	}
	
	public void displayPasses() {
		int i=0;
		while (i < bookings.size()) {
			System.out.println("\n--------------------------------");
			System.out.println(" Boarding Pass #" + (i+1));
			System.out.println("--------------------------------");
			System.out.println("Flight ID: " + bookings.get(i).get(0));
			System.out.println("From: " + bookings.get(i).get(1));
			System.out.println("To: " + bookings.get(i).get(2));
			System.out.println("Passenger Name: " + bookings.get(i).get(3));
			System.out.println("Person Type: " + bookings.get(i).get(4));
			System.out.println("Gate: " + bookings.get(i).get(5));
			System.out.println("Terminal: " + bookings.get(i).get(6));
			System.out.println("Boarding begins: " + bookings.get(i).get(7));
			System.out.println("Boarding ends: " + bookings.get(i).get(8));
			System.out.println("Boarding group: " + bookings.get(i).get(9));
			System.out.println("Seat: " + bookings.get(i).get(10));
			System.out.println("----------------------------------");
			i++;
		}
	}
	
}

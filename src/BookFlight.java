import java.util.ArrayList;
import java.util.Scanner;

public class BookFlight {

	private ArrayList<String> first_flight;
	private ArrayList<String> second_flight;
	private ArrayList<ArrayList<String>> tickets;
	private double total_cost;
	private int number_of_tickets;

	public BookFlight(ArrayList<String> first_flight) {
		this.first_flight = first_flight;
		tickets = new ArrayList<ArrayList<String>>();
		int errorcode = Trip1();
		if (errorcode != -1) {
			displayTickets();
			payment();
		}
	}

	public BookFlight(ArrayList<String> first_flight, ArrayList<String> second_flight) {
		this.first_flight = first_flight;
		this.second_flight = second_flight;
		tickets = new ArrayList<ArrayList<String>>();
		int errorcode = Trip1();
		if (errorcode != -1) {
			errorcode = Trip2();
			if (errorcode != -1) {
				displayTickets();
				payment();
			}
		}
	}

	public int Trip1() {
		Scanner stdin = new Scanner(System.in);
		ArrayList<String> ticket;
		char cont;
		System.out.println("Enter quantity of tickets: ");
		number_of_tickets = stdin.nextInt();
		for (int i = 0; i < number_of_tickets; i++) {
			System.out.println("Trip 1: Ticket " + (i + 1));
			ticket = getTicketInfo(Integer.parseInt(first_flight.get(0)), first_flight);
			System.out.println("Cancel or Next (c/n):");
			cont = stdin.next().charAt(0);
			if (cont == 'c' || cont == 'C') {
				return -1;
			} else {
				tickets.add(ticket);
			}
		}
		return 0;
	}

	public int Trip2() {
		Scanner stdin = new Scanner(System.in);
		ArrayList<String> ticket;
		char cont;
		for (int i = 0; i < number_of_tickets; i++) {
			System.out.println("Trip 2: Ticket " + (i + 1));
			ticket = getTicketInfo(Integer.parseInt(second_flight.get(0)), second_flight);
			System.out.println("Cancel or Next (c/n):");
			cont = stdin.next().charAt(0);
			if (cont == 'c' || cont == 'C') {
				return -1;
			} else {
				tickets.add(ticket);
			}
		}
		return 0;
	}

	public void displayTickets() {
		int i = 0;
		while (i < tickets.size()) {
			System.out.println("\nTicket: " + (i + 1));
			System.out.println("-----------------------");
			System.out.println("Flight ID: " + tickets.get(i).get(0));
			System.out.println("Name of passenger: " + tickets.get(i).get(1) + " " + tickets.get(i).get(2));
			System.out.println("Person type: " + tickets.get(i).get(2));
			System.out.println("Seat type: " + tickets.get(i).get(4));
			System.out.println("Seat: " + tickets.get(i).get(5));
			System.out.println("Cost: $" + tickets.get(i).get(6));
			i++;
		}
	}

	public void payment() {
		Scanner stdin = new Scanner(System.in);
		char cont;
		System.out.println("\nCheckout");
		System.out.println("----------------------");
		total_cost = total_cost * 1.0875;
		System.out.println("Total w/tax: $" + total_cost);
		System.out.println("-------------------------------");
		System.out.println("Name on card:");
		System.out.println("Enter credit card number: ");
		System.out.println("Enter expiration date: ");
		System.out.println("Enter security code: ");
		System.out.println("Enter billing address: ");
		System.out.println("Cancel or Buy(c/b): ");
		cont = stdin.next().charAt(0);
		if (cont == 'b' || cont == 'B') {
			addBookings();
		}
	}

	public ArrayList<String> getTicketInfo(int flight_id, ArrayList<String> flight) {
		ArrayList<String> ticket = new ArrayList<String>();
		ticket.add(String.valueOf(flight_id));
		Scanner stdin = new Scanner(System.in);
		System.out.println("Enter first name of passenger: ");
		String first_name = stdin.nextLine();
		ticket.add(first_name);
		System.out.println("Enter last name of passenger: ");
		String last_name = stdin.nextLine();
		ticket.add(last_name);
		System.out.println("Enter person type(child/adult): ");
		String person_type = stdin.nextLine();
		ticket.add(person_type);
		System.out.println("Select a seat: ");
		ArrayList<ArrayList<String>> seats = SQLConnect.getFlightSeats(flight_id);
		int i = 0;
		while (i < seats.size()) {
			System.out.println(seats.get(i).get(3) + " - " + seats.get(i).get(2));
			i++;
		}
		System.out.println("Enter seat number and letter: ");
		String seat = stdin.nextLine();
		int seat_id = SQLConnect.getSeatID(flight_id, seat.replaceAll("\"", ""));
		ticket.add(String.valueOf(seat_id));
		String seat_type = SQLConnect.getSeatType(flight_id, seat.replaceAll("\"", ""));
		ticket.add(seat_type);
		ticket.add(seat);
		double cost = SQLConnect.getSeatCost(flight_id, seat_type.replaceAll("\"", ""));
		ticket.add(String.valueOf(cost));
		System.out.println("Cost: $" + cost);
		total_cost += cost;
		System.out.println("Total Cost: $" + total_cost);
		return ticket;
	}

	public void addBookings() {
		int i = 0;
		while (i < tickets.size()) {
			SQLConnect.addBooking(Integer.parseInt(tickets.get(i).get(0)), Integer.parseInt(tickets.get(i).get(4)),
					tickets.get(i).get(1), tickets.get(i).get(2), tickets.get(i).get(3));
			SQLConnect.updateSeatAmount(Integer.parseInt(tickets.get(i).get(0)), tickets.get(i).get(5));
			SQLConnect.updateSeatStatus(Integer.parseInt(tickets.get(i).get(0)), Integer.parseInt(tickets.get(i).get(4)));
			i++;
		}

	}
}

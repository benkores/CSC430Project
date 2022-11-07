
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTransactionRollbackException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class SQLConnect {
	private static String dbURL = "jdbc:derby://localhost:1527/my_database;create=true;user=teamviper;password=teamviper";
	private static Connection conn = null;
	private static Statement stmt = null;

	public SQLConnect() {
		System.out.println("Loading...");
		createConnection();
		executeSQLScripts();
	}

	private static void createConnection() {
		try {
			Runtime.getRuntime()
					.exec("cmd /c start /min java -jar \"Apache\\derbyrun.jar\" server start -noSecurityManager");
			TimeUnit.SECONDS.sleep(2);
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			conn = DriverManager.getConnection(dbURL);
		} catch (Exception except) {
			except.printStackTrace();
		}
	}

	public static void executeSQLScripts() {
		File file;
		Scanner reader;
		try {
		/* For testing purposes only
			file = new File("sql/drop.sql");
			reader = new Scanner(file);
			while (reader.hasNextLine()) {
				stmt = conn.createStatement();
				stmt.execute(reader.nextLine());
			}
			*/
			file = new File("sql/accounts.sql");
			reader = new Scanner(file);
			while (reader.hasNextLine()) {
				stmt = conn.createStatement();
				stmt.execute(reader.nextLine());
			}
			file = new File("sql/airports.sql");
			reader = new Scanner(file);
			while (reader.hasNextLine()) {
				stmt = conn.createStatement();
				stmt.execute(reader.nextLine());
			}
			file = new File("sql/flights.sql");
			reader = new Scanner(file);
			while (reader.hasNextLine()) {
				stmt = conn.createStatement();
				stmt.execute(reader.nextLine());
			}
			file = new File("sql/flight_seats.sql");
			reader = new Scanner(file);
			while (reader.hasNextLine()) {
				stmt = conn.createStatement();
				stmt.execute(reader.nextLine());
			}
			file = new File("sql/user_bookings.sql");
			reader = new Scanner(file);
			while (reader.hasNextLine()) {
				stmt = conn.createStatement();
				stmt.execute(reader.nextLine());
			}
		} catch (SQLException ex) {
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	public static void insertAccount(String username, String password) {
		try {
			stmt = conn.createStatement();
			int id = getNextID("ACCOUNTS");
			stmt.execute("INSERT INTO ACCOUNTS(id,username,password) VALUES(" + id + ",'" + username + "','" + password
					+ "')");
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}

	public static int getNextID(String table) {
		ResultSet results = null;
		int count = 1;
		try {
			stmt = conn.createStatement();
			results = stmt.executeQuery("SELECT ID FROM " + table);
			while (results.next()) {
				count++;
			}
			return count;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return count;
	}

	public static boolean authenticateAccount(String username, String password) {
		ResultSet results = null;
		int count = 1;
		try {
			stmt = conn.createStatement();
			results = stmt.executeQuery(
					"SELECT ID FROM ACCOUNTS WHERE USERNAME = '" + username + "' AND PASSWORD = '" + password + "'");
			while (results.next()) {
				count++;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		if (count == 1) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean checkAccountExists(String username) {
		ResultSet results = null;
		int count = 1;
		try {
			stmt = conn.createStatement();
			results = stmt.executeQuery("SELECT ID FROM ACCOUNTS WHERE USERNAME = '" + username + "'");
			while (results.next()) {
				count++;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		if (count == 1) {
			return false;
		} else {
			return true;
		}
	}

	public static ArrayList<ArrayList<String>> getFromAirports() {
		ResultSet results = null;
		ArrayList<ArrayList<String>> from_airports = new ArrayList<ArrayList<String>>();
		ArrayList<String> airport;
		try {
			stmt = conn.createStatement();
			results = stmt.executeQuery("SELECT ID, NAME FROM AIRPORTS");
			while (results.next()) {
				airport = new ArrayList<String>();
				airport.add(results.getString("id"));
				airport.add(results.getString("name"));
				from_airports.add(airport);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return from_airports;
	}

	public static ArrayList<ArrayList<String>> getToAirports(String from_airport) {
		ResultSet results = null;
		ArrayList<ArrayList<String>> to_airports = new ArrayList<ArrayList<String>>();
		ArrayList<String> airport;
		try {
			stmt = conn.createStatement();
			results = stmt.executeQuery("SELECT ID, NAME FROM AIRPORTS WHERE ID != '" + from_airport + "'");
			while (results.next()) {
				airport = new ArrayList<String>();
				airport.add(results.getString("id"));
				airport.add(results.getString("name"));
				to_airports.add(airport);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return to_airports;
	}

	public static ArrayList<ArrayList<String>> getFlights(String from_airport, String to_airport,
			String departure_date) {
		ResultSet results = null;
		Date dep_date = null;
		ArrayList<ArrayList<String>> flights = new ArrayList<ArrayList<String>>();
		ArrayList<String> flight;
		try {
			dep_date = new SimpleDateFormat("MM/dd/yyyy").parse(departure_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String dep_date_sql = new SimpleDateFormat("yyyy-MM-dd").format(dep_date).replaceAll("\"", "");
		try {
			stmt = conn.createStatement();
			results = stmt.executeQuery("SELECT * FROM FLIGHTS WHERE DEPARTURE_ID = '" + from_airport
					+ "' AND ARRIVAL_ID = '" + to_airport + "' AND DEPARTURE_DATE = '" + dep_date_sql + "'");
			while (results.next()) {
				flight = new ArrayList<String>();
				flight.add(results.getString("id"));
				flight.add(from_airport);
				flight.add(to_airport);
				flight.add(departure_date);
				flight.add(results.getString("departure_time"));
				flight.add(results.getString("arrival_date"));
				flight.add(results.getString("arrival_time"));
				flight.add(results.getString("gate"));
				flight.add(results.getString("terminal"));
				flight.add(results.getString("boarding_begins"));
				flight.add(results.getString("boarding_ends"));
				flight.add(results.getString("number_of_first_class_seats"));
				flight.add(results.getString("number_of_business_class_seats"));
				flight.add(results.getString("number_of_economy_seats"));
				flight.add(results.getString("first_cost"));
				flight.add(results.getString("business_cost"));
				flight.add(results.getString("economy_cost"));
				flights.add(flight);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return flights;
	}

	public static ArrayList<ArrayList<String>> getFlightSeats(int flight_id) {
		ResultSet results = null;
		ArrayList<ArrayList<String>> seats = new ArrayList<ArrayList<String>>();
		ArrayList<String> seat;
		try {
			stmt = conn.createStatement();
			results = stmt
					.executeQuery("SELECT * FROM FLIGHT_SEATS WHERE FLIGHT_ID=" + flight_id + " AND SEAT_BOOKED=false");
			while (results.next()) {
				seat = new ArrayList<String>();
				seat.add(results.getString("flight_id"));
				seat.add(results.getString("id"));
				seat.add(results.getString("seat_type"));
				seat.add(results.getString("seat"));
				seats.add(seat);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return seats;
	}

	public static int getSeatID(int flight_id, String seat) {
		ResultSet results = null;
		int seat_id = 0;
		try {
			stmt = conn.createStatement();
			results = stmt.executeQuery(
					"SELECT ID FROM FLIGHT_SEATS WHERE FLIGHT_ID=" + flight_id + " AND SEAT='" + seat + "'");
			while (results.next()) {
				seat_id = results.getInt("id");
			}
			return seat_id;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return seat_id;
	}

	public static String getSeatType(int flight_id, String seat) {
		ResultSet results = null;
		String seat_type = null;
		try {
			stmt = conn.createStatement();
			results = stmt.executeQuery(
					"SELECT SEAT_TYPE FROM FLIGHT_SEATS WHERE FLIGHT_ID=" + flight_id + "AND SEAT = '" + seat + "'");
			while (results.next()) {
				seat_type = results.getString("seat_type");
			}
			return seat_type;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return seat_type;
	}

	public static double getSeatCost(int flight_id, String seat_type) {
		ResultSet results = null;
		double cost = 0;
		if (seat_type.equals("first")) {
			seat_type = "first_cost".replaceAll("\"", "");
		} else if (seat_type.equals("business")) {
			seat_type = "business_cost".replaceAll("\"", "");
		} else {
			seat_type = "economy_cost".replaceAll("\"", "");
		}
		try {
			stmt = conn.createStatement();
			results = stmt.executeQuery("SELECT " + seat_type + " FROM FLIGHTS WHERE ID=" + flight_id);
			while (results.next()) {
				cost = results.getDouble(seat_type);
			}
			return cost;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return cost;
	}

	public static void updateSeatAmount(int flight_id, String seat_type) {
		ResultSet results = null;
		int seatnum = 0;
		if (seat_type.equals("first")) {
			seat_type = "number_of_first_class_seats".replaceAll("\"", "");
		} else if (seat_type.equals("business")) {
			seat_type = "number_of_business_class_seats".replaceAll("\"", "");
		} else {
			seat_type = "number_of_economy_seats".replaceAll("\"", "");
		}
		try {
			stmt = conn.createStatement();
			results = stmt.executeQuery("SELECT " + seat_type + " FROM FLIGHTS WHERE ID=" + flight_id);
			while (results.next()) {
				seatnum = results.getInt(seat_type);
			}
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE FLIGHTS " + "SET " + seat_type + "=" + (seatnum - 1) + " WHERE ID=" + flight_id);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static void updateSeatStatus(int flight_id, int seat_id) {
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(
					"UPDATE FLIGHT_SEATS SET SEAT_BOOKED=TRUE WHERE FLIGHT_ID=" + flight_id + " AND ID=" + seat_id);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static void addBooking(int flight_id, int seat_id, String first_name, String last_name, String person_type) {
		int account_id = getAccountID();
		int booking_id = getNextID("USER_BOOKINGS");
		try {
			stmt = conn.createStatement();
			stmt.execute("INSERT INTO USER_BOOKINGS VALUES(" + account_id + "," + booking_id + "," + flight_id + ","
					+ seat_id + ",'" + first_name + "','" + last_name + "','" + person_type + "')");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static ArrayList<ArrayList<String>> getUserBookings() {
		int account_id = getAccountID();
		ArrayList<ArrayList<String>> bookings = new ArrayList<ArrayList<String>>();
		ArrayList<String> booking;
		ResultSet results = null;
		try {
			results = stmt.executeQuery("SELECT * FROM USER_BOOKINGS WHERE ACCOUNT_ID=" + account_id);
			while (results.next()) {
				booking = new ArrayList<String>();
				booking.add(String.valueOf(results.getInt("flight_id")));
				String from_airport = getStringFromFlights(Integer.parseInt(booking.get(0)), "departure_id");
				booking.add(from_airport);
				String to_airport = getStringFromFlights(Integer.parseInt(booking.get(0)), "arrival_id");
				booking.add(to_airport);
				booking.add(results.getString("first_name"));
				booking.add(results.getString("last_name"));
				booking.add(results.getString("person_type"));
				String gate = getStringFromFlights(Integer.parseInt(booking.get(0)), "gate");
				booking.add(gate);
				int terminal = getIntFromFlights(Integer.parseInt(booking.get(0)), "terminal");
				booking.add(String.valueOf(terminal));
				String start_boarding = getStringFromFlights(Integer.parseInt(booking.get(0)), "boarding_begins");
				booking.add(start_boarding);
				String end_boarding = getStringFromFlights(Integer.parseInt(booking.get(0)), "boarding_ends");
				booking.add(end_boarding);
				String seat_type = getStringFromFlightSeats("seat_type", Integer.parseInt(booking.get(0)),
						Integer.parseInt(results.getString("flight_seats_id")));
				if (seat_type.equals("first")) {
					booking.add("1");
				} else if (seat_type.equals("business")) {
					booking.add("2");
				} else {
					booking.add("3");
				}
				String seat = getStringFromFlightSeats("seat", Integer.parseInt(booking.get(0)),
						Integer.parseInt(results.getString("flight_seats_id")));
				booking.add(seat);
				bookings.add(booking);
			}
			return bookings;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return bookings;
	}

	public static String getStringFromFlights(int flight_id, String column) {
		ResultSet results = null;
		String result = null;
		try {
			stmt = conn.createStatement();
			results = stmt.executeQuery("SELECT " + column + " FROM FLIGHTS WHERE ID=" + flight_id);
			while (results.next()) {
				result = results.getString(column);
			}
			return result;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static int getIntFromFlights(int flight_id, String column) {
		ResultSet results = null;
		int result = 0;
		try {
			stmt = conn.createStatement();
			results = stmt.executeQuery("SELECT " + column + " FROM FLIGHTS WHERE ID=" + flight_id);
			while (results.next()) {
				result = results.getInt(column);
			}
			return result;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static String getStringFromFlightSeats(String column, int flight_id, int seat_id) {
		ResultSet results = null;
		String result = null;
		try {
			stmt = conn.createStatement();
			results = stmt.executeQuery(
					"SELECT " + column + " FROM FLIGHT_SEATS WHERE FLIGHT_ID=" + flight_id + " AND ID=" + seat_id);
			while (results.next()) {
				result = results.getString(column);
			}
			return result;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static int getAccountID() {
		File login = new File("login_username.txt");
		String username = null;
		ResultSet results = null;
		int id = 0;
		try {
			Scanner reader = new Scanner(login);
			while (reader.hasNextLine()) {
				username = reader.nextLine().replaceAll("\"", "");
			}
			stmt = conn.createStatement();
			results = stmt.executeQuery("SELECT ID FROM ACCOUNTS WHERE USERNAME='" + username + "'");
			while (results.next()) {
				id = results.getInt("id");
			}
			return id;
		} catch (FileNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public static void shutdown() {
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				DriverManager.getConnection(dbURL + ";shutdown=true");
				conn.close();
			}
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}

	}
}
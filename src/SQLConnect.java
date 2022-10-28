import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.sql.ResultSetMetaData;

public class SQLConnect {
	private static String dbURL = "jdbc:derby://localhost:1527/my_database;user=teamviper;password=teamviper";
	// jdbc Connection
	private static Connection conn = null;
	private static Statement stmt = null;

	public SQLConnect() {
		createConnection();
	}

	private static void createConnection() {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			// Get a connection
			conn = DriverManager.getConnection(dbURL);
		} catch (Exception except) {
			except.printStackTrace();
		}
	}

	public static void create(String query) {
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		}
		if (count == 1) {
			return false;
		} else {
			return true;
		}
	}

	public static ResultSet select(String query) {
		ResultSet results = null;
		try {
			stmt = conn.createStatement();
			results = stmt.executeQuery(query);
			results.close();
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		return results;
	}

	public static String selectFromAirport() {
		Scanner stdin = new Scanner(System.in);
		ResultSet results = null;
		String option = null;
		try {
			stmt = conn.createStatement();
			results = stmt.executeQuery("SELECT ID, NAME FROM AIRPORTS");
			int i = 1;
			while (results.next()) {
				System.out.println(i++ + " " + results.getString("id") + " - " + results.getString("name"));
			}
			System.out.print("Enter 3-letter airport code: ");
			option = stdin.nextLine();
			return option.toUpperCase().replaceAll("\"", "");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static String selectToAirport(String from_airport) {
		Scanner stdin = new Scanner(System.in);
		ResultSet results = null;
		String option = null;
		try {
			stmt = conn.createStatement();
			results = stmt.executeQuery("SELECT ID, NAME FROM AIRPORTS WHERE ID != '" + from_airport + "'");
			int i = 1;
			while (results.next()) {
				System.out.println(i++ + " " + results.getString("id") + " - " + results.getString("name"));
			}
			System.out.print("Enter 3-letter airport code: ");
			option = stdin.nextLine();
			return option.toUpperCase().replaceAll("\"", "");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static int selectBooking(String from_airport, String to_airport, String departure_date) {
		Scanner stdin = new Scanner(System.in);
		ResultSet results = null;
		int flight_index = 0;
		Date dep_date = null;
		try {
			dep_date = new SimpleDateFormat("MM/dd/yyyy").parse(departure_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String dep_date_sql = new SimpleDateFormat("yyyy-MM-dd").format(dep_date).replaceAll("\"", "");
		try {
			stmt = conn.createStatement();
			results = stmt.executeQuery("SELECT * FROM FLIGHTS WHERE DESTINATION_ID = '" + from_airport
					+ "' AND ARRIVAL_ID = '" + to_airport + "' AND DEPARTURE_DATE = '" + dep_date_sql + "'");
			int i = 1;
			while (results.next()) {
				System.out.println("\nFlight " + i++);
				System.out.println(from_airport + " ---> " + to_airport);
				System.out.println("Departs: " + departure_date + " " + results.getString("departure_time"));
				System.out.println(
						"Arrives: " + results.getString("arrival_date") + " " + results.getString("arrival_time"));
				System.out.println("Gate: " + results.getString("gate"));
				System.out.println("Terminal: " + results.getString("terminal"));
				System.out.println("Boarding begins: " + results.getString("boarding_begins"));
				System.out.println("Boarding ends: " + results.getString("boarding_ends"));
				System.out.println("Number of seats: " + results.getString("number_of_first_class_seats")
						+ " first-class seats, " + results.getString("number_of_business_class_seats")
						+ " business-class seats, " + results.getString("number_of_economy_seats") + " economy seats");
				System.out.println("First-class ticket price: $" + results.getString("first_cost"));
				System.out.println("Business-class ticket price: $" + results.getString("business_cost"));
				System.out.println("Economy ticket price: $" + results.getString("business_cost"));
			}
			if (i == 1) {
				System.out.println("No flights found.");
			} else {
				System.out.print("Enter a flight option(1-" + (i - 1) + "): ");
				flight_index = stdin.nextInt();
				return flight_index;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return flight_index;
	}
	
	public static void update(String query) {
		try {
			stmt = conn.createStatement();
			stmt.executeQuery(query);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
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
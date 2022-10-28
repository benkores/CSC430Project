import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	
	public static ArrayList<ArrayList<String>> getFlights(String from_airport, String to_airport, String departure_date) {
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
			results = stmt.executeQuery("SELECT * FROM FLIGHTS WHERE DESTINATION_ID = '" + from_airport
					+ "' AND ARRIVAL_ID = '" + to_airport + "' AND DEPARTURE_DATE = '" + dep_date_sql + "'");
			while (results.next()) {
				flight = new ArrayList<String>();
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
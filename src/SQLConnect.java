import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;


public class SQLConnect
{
    private static String dbURL = "jdbc:derby://localhost:1527/my_database;user=teamviper;password=teamviper";
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;

    public SQLConnect()
    {
        createConnection();
    }
    
    private static void createConnection()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            //Get a connection
            conn = DriverManager.getConnection(dbURL); 
        }
        catch (Exception except)
        {
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
    public static void insertAccount(String username, String password)
    {
        try
        {
            stmt = conn.createStatement();
            int id = getNextID("ACCOUNTS");
            stmt.execute("INSERT INTO ACCOUNTS VALUES(" + id + ",'" + username + "','" + password + "')");
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
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
    	int count  = 1;
    	try {
    		stmt = conn.createStatement();
    		results = stmt.executeQuery("SELECT ID FROM ACCOUNTS WHERE NAME = '" + username + "' AND PASSWORD = '" + password + "'");
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
    	int count  = 1;
    	try {
    		stmt = conn.createStatement();
    		results = stmt.executeQuery("SELECT ID FROM ACCOUNTS WHERE NAME = '" + username + "'");
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
    public static ResultSet select(String query)
    {
    	ResultSet results = null;
        try
        {
            stmt = conn.createStatement();
            results = stmt.executeQuery(query);
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        return results;
    }
    
    public static void update(String query) {
    	try {
    		stmt = conn.createStatement();
    		stmt.executeQuery(query);
    	} catch (SQLException ex) {
    		ex.printStackTrace();
    	}
    }
    
    private static void shutdown()
    {
        try
        {
            if (stmt != null)
            {
                stmt.close();
            }
            if (conn != null)
            {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }           
        }
        catch (SQLException sqlExcept)
        {
            
        }

    }
}
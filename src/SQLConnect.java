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

    public static void main(String[] args)
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
    
    
    private static void create(String query) {
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
    }
    private static void insert(String query)
    {
        try
        {
            stmt = conn.createStatement();
            stmt.execute(query);
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
    private static ResultSet select(String query)
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
    
    private static void update(String query) {
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
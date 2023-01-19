package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class DatabaseConnection {
	private final String username = "root";
	private final String password = "";
	private final String database = "fla_project";
	private final String host = "localhost:3306";
	private final String connection = String.format("jdbc:mysql://%s/%s", host, database);

	public ResultSet rs;
	public ResultSetMetaData rsm;
	private Connection con;
	private Statement st;
	private static DatabaseConnection dbConnection;
	
	private DatabaseConnection() {
    	try {  
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(connection, username, password);  
            st = con.createStatement(); 
        } catch(Exception e) {
        	e.printStackTrace();
        	System.out.println("Connection failed!");
        	System.exit(0);
        }  
    }

	public static synchronized DatabaseConnection getInstance() {
		if (dbConnection == null) {
			dbConnection = new DatabaseConnection();
		}
		
		return dbConnection;
    }
	
    /**
	* This method is used for SELECT SQL statements.
	* @param String This is the query statement
	* @return ResultSet This returns result data from the database
	*/
    public ResultSet executeQuery(String query) {
        rs = null;
    	try {
            rs = st.executeQuery(query);
            rsm = (ResultSetMetaData) rs.getMetaData();
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return rs;
    }

	/**
	* This method is used for INSERT, UPDATE, or DELETE SQL statements.
	* @param String This is the query statement
	*/
    public void executeUpdate(String query) {
    	try {
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
	/**
	* This method is used for SELECT, INSERT, UPDATE, or DELETE SQL statements using prepare statement.
	* @param String This is the query statement
	*/
    public PreparedStatement prepareStatement(String query) {
    	PreparedStatement ps = null;
    	try {
			ps = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return ps;
    }
}

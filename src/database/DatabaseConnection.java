package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DatabaseConnection {
	private final String username = "root";
	private final String password = "";
	private final String database = "fla_project";
	private final String host = "localhost:3306";
	private final String connection = String.format("jdbc:mysql://%s/%s", host, database);

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
	
	public PreparedStatement prepareStatement(String query) {
    	PreparedStatement ps = null;
    	try {
			ps = con.prepareStatement(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return ps;
    }
}

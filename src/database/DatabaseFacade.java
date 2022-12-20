package database;

public class DatabaseFacade {
	private DatabaseConnection db;
	
	public DatabaseFacade() {
		this.db = DatabaseConnection.getConnection();
	}

}

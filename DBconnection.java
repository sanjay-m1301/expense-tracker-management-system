package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
	

	
	

	    private static final String URL =
	            "jdbc:mysql://localhost:3306/expense_tracker";
	    private static final String USER = "expense";
	    private static final String PASSWORD = "track&2026";

	    public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(URL, USER, PASSWORD);
	    
	}
}

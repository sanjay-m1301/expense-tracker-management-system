package util;
import java.sql.Connection;
import util.DBconnection;
public class TestConnection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try {
	            Connection con = DBconnection.getConnection();

	            System.out.println("Database connected successfully!");

	            con.close();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	}

}

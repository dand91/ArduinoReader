
package Storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Shared.Data;


/**
 * Database is a class that specifies the interface to the movie database. Uses
 * JDBC and the MySQL Connector/J driver.
 */
public class Database {
	/**
	 * The database connection.
	 */
	private Connection conn;
	protected boolean connected;
    private static Database db;
    private static String PASSWORD;

    public static void initiate() {
        db = new Database();
        
        try {

        BufferedReader br = new BufferedReader(new FileReader("/Users/Andersson/Google Drive/Java_saved_files/Bluetooth_workspace/Bluetooth_Connector/password/password.txt"));
		PASSWORD = br.readLine();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static Database getInstance() throws Exception {
        if (db != null) {
            return db;
        } else {
            throw new Exception("Must initiate Database first");
        }
    }

	/**
	 * Open a connection to the database, using the specified user name and
	 * password.
	 *
	 * @param userName
	 *            The user name.
	 * @param password
	 *            The user's password.
	 * @return true if the connection succeeded, false if the supplied user name
	 *         and password were not recognized. Returns false also if the JDBC
	 *         driver isn't found.
	 */
    
	public boolean openConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/bluetooth", "DAnd91", PASSWORD);

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}

		return isConnected();
	}


	/**
	 * Close the connection to the database.
	 */
	public void closeConnection() {

		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
		}
		conn = null;
	}

	/**
	 * Check if the connection to the database has been established
	 *
	 * @return true if the connection has been established
	 */
	public boolean isConnected() {
		return conn != null;
	}
	
	public Database getDatabase(){
		
		return this;
	}
	
	
	
	public List<Data> getData(int nbr) {

		ArrayList<Data> list = new ArrayList<Data>();

		try {

			PreparedStatement ps = conn
					.prepareStatement("SELECT value, date FROM data ORDER BY date DESC LIMIT ?;");
			ps.setInt(1, nbr);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				list.add( new Data( String.valueOf(rs.getInt("value")) , rs.getString("date") ) );

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return list;

	}

	public void addData(String input){
		

		PreparedStatement ps;
		try {
			ps = conn
					.prepareStatement("INSERT INTO Data (value, date)  VALUES ( ?, NOW());");
	
		ps.setInt(1, Integer.valueOf(input));
		ps.execute();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}
	public void addComment(String input){
		

		PreparedStatement ps;
		try {
			ps = conn
					.prepareStatement("INSERT INTO Comment (comment, date)  VALUES ( ?, NOW());");
	
		ps.setString(1, input);
		ps.execute();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}
	public void clearDB(){
		

		PreparedStatement ps;
		try {
			ps = conn
					.prepareStatement("DELETE FROM Data");
	
		ps.execute();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}
	
	
	
}



package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/*
 * This code helpful only for 
 * MySQL system. if you want to use with Oracle 
 * than change the url and driver registration parameter
 */
public class DBConnection {

	private static Connection conn;

	public static Connection getConn() {
		return conn;
	}

	public static boolean connectToDB(String database) {
		String url;
		String username;
		String password;

		url = "jdbc:mysql://localhost:3306/" + database;
		username = "root";
		password = "";
		try {

			// Registration of Driver
			Class.forName("com.mysql.jdbc.Driver");

			try {
				// Try to open a Connection
				conn = DriverManager.getConnection(url, username, password);

				return true;
			} catch (SQLException e) {
				System.out.println("Problem in username and password or there will no database with name " + database);
				return false;
			}

		} catch (ClassNotFoundException e1) {
			System.out.println("Driver Not Found \nAdd Jar file of Driver in the Project ");
			return false;
		}

	}

	public static void releaseConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("There is problem in closing connection");
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter Database name: ");
		String databaseName = sc.next();
		boolean status = DBConnection.connectToDB(databaseName);

		if (status) {
			System.out.println("You are connected with database " + databaseName);
			if (DBConnection.getConn() != null) {
				System.out.println("Connection object is found");
			} else {
				System.out.println("Connection is not established yet");
			}
		} else {
			System.out.println("You are not connected with database " + databaseName);
		}

	}

}

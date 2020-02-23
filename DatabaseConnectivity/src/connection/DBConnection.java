package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

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
			Class.forName("com.mysql.jdbc.Driver");

			try {
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

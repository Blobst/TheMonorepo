package io.github.blobst.bankingapp.db_objs;

import java.math.BigDecimal;
import java.sql.*;

public class MyJDBC {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/bankapp";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "root";

	public static User validateLogin(String username, String password) {
		try {
			Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int userID = resultSet.getInt("id");
				BigDecimal currentBalance = resultSet.getBigDecimal("current_balance");

				return new User(userID, username, password, currentBalance);
			}

		} catch (SQLException e) {
			System.out.printf("SQLException: %s\n", e.getMessage());
		}
		return null;
	}

	public static boolean registerUser(String username, String password) {
		try {
			if (!checkUser(username)) {
				Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

				PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO users(username, password) " +
								"VALUES(?, ?)"
				);

				preparedStatement.setString(1, username);
				preparedStatement.setString(2, password);

				preparedStatement.executeUpdate();
				return true;
			}
		} catch (SQLException e) {
			System.out.printf("SQLException: %s\n", e.getMessage());
		}

		return false;
	}

	private static boolean checkUser(String username) {
		try {
			Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");

			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				return false;
			}
		} catch (SQLException e) {
			System.out.printf("SQLException: %s\n", e.getMessage());
		}
		return true;
	}
}

























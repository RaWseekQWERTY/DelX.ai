package controller.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Category;
import model.LoginModel;
import model.PasswordEncryptionWithAes;
import model.UserModel;
import utils.StringUtils;

public class DBController {
	/**
	 * Establishes a connection to the database using pre-defined credentials and
	 * driver information.
	 * 
	 * @return A `Connection` object representing the established connection to the
	 *         database.
	 * @throws SQLException           if a database access error occurs.
	 * @throws ClassNotFoundException if the JDBC driver class is not found.
	 */
	public Connection getConnection() throws SQLException, ClassNotFoundException {

		// Load the JDBC driver class specified by the StringUtils.DRIVER_NAME constant
		Class.forName(StringUtils.DRIVER_NAME);

		// Create a connection to the database using the provided credentials
		return DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME,
				StringUtils.LOCALHOST_PASSWORD);
	}

	/**
	 * This method attempts to register a new student in the database.
	 * 
	 * @param student A `StudentModel` object containing the student's information.
	 * @return An integer value indicating the registration status: - 1:
	 *         Registration successful - 0: Registration failed (no rows affected) -
	 *         -1: Internal error (e.g., ClassNotFound or SQLException)
	 * @throws SQLException           if a database access error occurs.
	 * @throws ClassNotFoundException if the JDBC driver class is not found.
	 */
	public int registerUser(UserModel user) {
		try {
			// Prepare a statement using the predefined query for student registration
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_REGISTER_STUDENT);
			// Set the student information in the prepared statement
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setString(3, user.getUsername());
			stmt.setDate(4, Date.valueOf(user.getDob()));
			stmt.setString(5, user.getEmail());
			stmt.setString(6, user.getGender());
			stmt.setString(7, user.getUserType());
			stmt.setString(8, PasswordEncryptionWithAes.encrypt(user.getUsername(), user.getPassword()));
			stmt.setString(9, user.getAvatar());

			// Execute the update statement and store the number of affected rows
			int result = stmt.executeUpdate();
			// Check if the update was successful (i.e., at least one row affected)
			if (result > 0) {
				System.out.println("success");
				return 1;// Registration successful
			} else {
				System.out.println("failed");
				return 0; // Registration failed (no rows affected)

			}

		} catch (ClassNotFoundException | SQLException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			System.out.println("stackktree error");
			return -1;
		}
	}

	public int addCategory(Category category) {
		ResultSet rs = null;
		int catId = -1;
		try {
			// Prepare a statement using the predefined query for adding a category
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_ADD_CATEGORY,
					Statement.RETURN_GENERATED_KEYS);
			// Set the category information in the prepared statement
			stmt.setString(1, category.getCategoryName());
			stmt.setString(2, category.getCategoryDesc());

			// Execute the update statement and store the number of affected rows
			int result = stmt.executeUpdate();
			// Check if the update was successful (i.e., at least one row affected)
			if (result == 1) {
				// Get the generated keys
				rs = stmt.getGeneratedKeys();
				// Check if keys were generated
				if (rs.next()) {
					catId = rs.getInt(1); // Get the generated category ID
					System.out.println("Added category with ID: " + catId);
				} else {
					System.out.println("Failed to retrieve generated keys");
				}
			} else {
				System.out.println("Failed to add category");
			}
			return catId; // Return the generated category ID or -1 if failed
		} catch (ClassNotFoundException | SQLException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			System.out.println("stackktree error");
			return -1;
		}
	}

	public int getUserLoginInfo(LoginModel loginModel) {
		// Try-catch block to handle potential SQL or ClassNotFound exceptions
		try {
			// Prepare a statement using the predefined query for login check
			PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_LOGIN_USER_CHECK);

			// Set the username in the first parameter of the prepared statement
			st.setString(1, loginModel.getUsername());

			// Execute the query and store the result set
			ResultSet result = st.executeQuery();

			// Check if there's a record returned from the query
			if (result.next()) {
				// Get the username from the database
				String userDb = result.getString(StringUtils.USER_NAME);

				// Get the password from the database
				String encryptedPwd = result.getString(StringUtils.PASSWORD);

				String decryptedPwd = PasswordEncryptionWithAes.decrypt(encryptedPwd, userDb);

//				String decryptedPwd = PasswordEncryptionWithAes.decrypt(encryptedPwd, userDb);
				// Check if the username and password match the credentials from the database
				if (userDb != null && decryptedPwd != null && userDb.equals(loginModel.getUsername())
						&& decryptedPwd.equals(loginModel.getPassword())) {
					// Login successful, return 1
					return 1;
				} else {
					// Username or password mismatch, return 0
					return 0;
				}
			} else {
				// Username not found in the database, return -1
				return -1;
			}

			// Catch SQLException and ClassNotFoundException if they occur
		} catch (SQLException | ClassNotFoundException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			// Return -2 to indicate an internal error
			return -2;
		}
	}

	public UserModel getUserByUsername(String username) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		UserModel user = null;

		try {
			connection = getConnection();
			String query = "SELECT * FROM user_detail WHERE user_name = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, username);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				user = new UserModel();
				user.setUserID(resultSet.getInt("userID"));
				user.setFirstName(resultSet.getString("firstName"));
				user.setLastName(resultSet.getString("lastName"));
				user.setUsername(resultSet.getString("user_name"));
				user.setDob(resultSet.getDate("dob").toLocalDate());
				user.setEmail(resultSet.getString("gmail"));
				user.setGender(resultSet.getString("gender"));
				user.setUserType(resultSet.getString("userType"));
				user.setPassword(resultSet.getString("password"));
				user.setAvatar(resultSet.getString("avatar"));
			}

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return user;
	}

	public Boolean checkUsernameIfExists(String username) {
		// TODO: Implement logic to check if the provided username exists in the
		// database
		// This method should likely query the database using DBController and return
		// true if the username exists, false otherwise.
		try {
			// Prepare a statement using the predefined query for login check
			PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_USERNAME_CHECK);
			st.setString(1, username);
			// Execute the query and store the result set
			ResultSet result = st.executeQuery();
			if (result.next()) {
				return true;
			} else {
				return false;
			}

			// Catch SQLException and ClassNotFoundException if they occur
		} catch (SQLException | ClassNotFoundException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			// Return -2 to indicate an internal error
			return false;
		}
	}

	public Boolean checkEmailIfExists(String gmail) {
		// TODO: Implement logic to check if the provided username exists in the
		// database
		// This method should likely query the database using DBController and return
		// true if the username exists, false otherwise.
		try {
			// Prepare a statement using the predefined query for login check
			PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_EMAIL_CHECK);
			st.setString(1, gmail);
			// Execute the query and store the result set
			ResultSet result = st.executeQuery();
			if (result.next()) {
				return true;
			} else {
				return false;
			}

			// Catch SQLException and ClassNotFoundException if they occur
		} catch (SQLException | ClassNotFoundException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			// Return -2 to indicate an internal error
			return false;
		}
	}

	public ArrayList<Category> getAllCategories() {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GETALL_CATEGORY);
			ResultSet result = stmt.executeQuery();

			ArrayList<Category> categories = new ArrayList<Category>();

			while (result.next()) {
				Category category = new Category();
				category.setCategoryID(result.getInt(1));
				category.setCategoryName(result.getString(2));
				category.setCategoryDesc(result.getString(3));
				categories.add(category);
			}
			return categories;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	
}
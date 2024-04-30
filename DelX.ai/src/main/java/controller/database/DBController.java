package controller.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Catalog;
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

	// Create operations
	public int registerUser(UserModel user) {
		try {
			// Prepare a statement using the predefined query for student registration
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_REGISTER_USER);
			// Set the student information in the prepared statement
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setString(3, user.getUsername());
			stmt.setDate(4, Date.valueOf(user.getDob()));
			stmt.setString(5, user.getEmail());
			stmt.setString(6, user.getGender());
			stmt.setString(7, user.getUserType());
			stmt.setString(8, PasswordEncryptionWithAes.encrypt(user.getUsername(), user.getPassword()));
			stmt.setString(9, user.getImageUrlFromPart());
			// stmt.setString(9, user.getAvatar());

			// Execute the update statement and store the number of affected rows
			int result = stmt.executeUpdate();
			// Check if the update was successful (i.e., at least one row affected)
			if (result > 0) {

				return 1;// Registration successful
			} else {

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

	public int addTools(Catalog tools) {
		try {
			// Prepare a statement using the predefined query for insterting tool details
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_ADD_TOOLS);

			// set tool information in prepare statement
			stmt.setString(1, tools.getToolName());
			stmt.setString(2, tools.getToolAuthor());
			stmt.setString(3, tools.getToolDesc());
			stmt.setString(4, tools.getImageUrlFromPart());
			stmt.setInt(5, tools.getCategory().getCategoryID());
			// Execute the update statement and store the number of affected rows
			int result = stmt.executeUpdate();
			// Check if the update was successful (i.e., at least one row affected)
			if (result > 0) {
				return 1; // Registration successful
			} else {
				return 0; // Registration failed (no rows affected)
			}

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	// End Create operations

	// READ operations
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
				String userDb = resultSet.getString(StringUtils.USER_NAME);
				String encryptedPwd = resultSet.getString(StringUtils.PASSWORD);

				String decryptedPwd = PasswordEncryptionWithAes.decrypt(encryptedPwd, userDb);
				user = new UserModel();
				user.setUserID(resultSet.getInt("userID"));
				user.setFirstName(resultSet.getString("firstName"));
				user.setLastName(resultSet.getString("lastName"));
				user.setUsername(resultSet.getString("user_name"));
				user.setDob(resultSet.getDate("dob").toLocalDate());
				user.setEmail(resultSet.getString("gmail"));
				user.setGender(resultSet.getString("gender"));
				user.setUserType(resultSet.getString("userType"));
				user.setPassword(decryptedPwd);
				user.setImageUrlFromDB(resultSet.getString("avatar"));
				// user.setAvatar(resultSet.getString("avatar"));
			}

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return user;
	}

	public Boolean checkUsernameIfExists(String username) {

		// database
		// This method should query the database using DBController and return
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

	public ArrayList<UserModel> getAllUsers() {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GETALL_USERS);
			ResultSet result = stmt.executeQuery();

			ArrayList<UserModel> user = new ArrayList<UserModel>();

			while (result.next()) {
				String userDb = result.getString(StringUtils.USER_NAME);
				String encryptedPwd = result.getString(StringUtils.PASSWORD);

				String decryptedPwd = PasswordEncryptionWithAes.decrypt(encryptedPwd, userDb);

				UserModel users = new UserModel();
				users.setUserID(result.getInt(1));
				users.setFirstName(result.getString(2));
				users.setLastName(result.getString(3));
				users.setUsername(result.getString(4));
				users.setDob(result.getDate(5).toLocalDate());
				users.setEmail(result.getString(6));
				users.setGender(result.getString(7));
				users.setUserType(result.getString(8));
				users.setPassword(encryptedPwd);
				users.setImageUrlFromDB(result.getString("avatar"));
				user.add(users);
			}
			return user;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
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

	public ArrayList<Catalog> getAllTools() {
		ArrayList<Catalog> toolsList = null;
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GETALL_TOOLS);
			ResultSet result = stmt.executeQuery();

			toolsList = new ArrayList<Catalog>();

			while (result.next()) {
				Catalog tools = new Catalog();
				tools.setCatalogID(result.getInt(1));
				tools.setToolName(result.getString(2));
				tools.setToolDesc(result.getString(3));
				tools.setToolAuthor(result.getString(4));
				tools.setImageUrlFromDB(result.getString("Toolimg"));
				// get category information
				int catId = result.getInt("categoryID");
				Category category = getCategoryById(catId);

				// set category object
				tools.setCategory(category);
				toolsList.add(tools);
			}
			return toolsList;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public Category getCategoryById(int categoryID) {
		Category cat = null;
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GET_CATEGORY_BY_ID);
			stmt.setInt(1, categoryID);
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				// Retrieve category details from the result set
				int categoryId = result.getInt("categoryId");
				String categoryName = result.getString("categoryTitle");
				String categoryDesc = result.getString("categoryDesc");

				// creating category object
				cat = new Category();
				cat.setCategoryID(categoryId);
				cat.setCategoryName(categoryName);
				cat.setCategoryDesc(categoryDesc);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cat;
	}

	public ArrayList<Catalog> getToolsByCategoryId(int id) {
		ArrayList<Catalog> catalogList = new ArrayList<>();
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GET_CATALOG_BY_CategoryID);
			stmt.setInt(1, id);

			ResultSet resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				Catalog catalog = new Catalog();
				catalog.setCatalogID(resultSet.getInt("catalogID"));
				catalog.setToolName(resultSet.getString("toolName"));
				catalog.setToolDesc(resultSet.getString("toolDesc"));
				catalog.setToolAuthor(resultSet.getString("toolAuthor"));
				catalog.setImageUrlFromDB(resultSet.getString(5));

				Category category = new Category();
				category.setCategoryID(resultSet.getInt("categoryID"));
				catalog.setCategory(category);

				catalogList.add(catalog);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return catalogList;
	}

	public Catalog getCatalogById(int catalogID) {
		Catalog tool = null;
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GET_CATALOG_BY_ID);
			stmt.setInt(1, catalogID);
			ResultSet result = stmt.executeQuery();
			if (result.next()) {
				tool = new Catalog();
				tool.setCatalogID(result.getInt(1));
				tool.setToolName(result.getString(2));
				tool.setToolDesc(result.getString(3));
				tool.setToolAuthor(result.getString(4));
				tool.setImageUrlFromDB(result.getString(5));
				int catId = result.getInt("categoryID");
				Category category = getCategoryById(catId);

				// set category object
				tool.setCategory(category);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tool;
	}
	// END READ operations

	// Update Operations
	public boolean updateTool(Catalog tool) {
		boolean result = false;
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_UPDATE_TOOLS);
			stmt.setString(1, tool.getToolName());
			stmt.setString(2, tool.getToolDesc());
			stmt.setString(3, tool.getToolAuthor());
			stmt.setString(4, tool.getImageUrlFromPart());
			stmt.setInt(5, tool.getCategory().getCategoryID());
			stmt.setInt(6, tool.getCatalogID());

			int executeResult = stmt.executeUpdate();
			if (executeResult == 1) {
				result = true;
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
	}

	// END Update operations

	// DELETE operations
	public int deleteTool(int toolID) {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_DELETE_TOOLS);
			stmt.setInt(1, toolID);
			return stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	// END Delete operations

}

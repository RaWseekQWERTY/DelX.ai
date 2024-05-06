package utils;

public class StringValidation {

	public static boolean isValidPassword(String password) {
		// Add length validation (at least 8 characters)
		if (password.length() < 8) {
			return false;
		}
		// Regular expression for validating password strength
		return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]*$");
	}

}

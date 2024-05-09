package utils;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidation {

	public static boolean isValidPassword(String password) {
		// Add length validation (at least 8 characters)
		if (password.length() < 8) {
			return false;
		}
		// Regular expression for validating password strength
		return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]*$");
	}

	// Check if a string contains only letters
	public static boolean containsOnlyLetters(String str) {
		Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	// Check if a string is a valid email address
	public static boolean isValidEmail(String email) {
		Pattern pattern = Pattern
				.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	// Check if a string is a valid date (not in the future)
	public static boolean isValidDate(String dateString) {
		Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
		Matcher matcher = pattern.matcher(dateString);
		if (!matcher.matches()) {
			return false;
		}

		String[] parts = dateString.split("-");
		int year = Integer.parseInt(parts[0]);
		int month = Integer.parseInt(parts[1]);
		int day = Integer.parseInt(parts[2]);

		// Check if it's a valid date
		if (month < 1 || month > 12 || day < 1 || day > 31) {
			return false;
		}

		// Check if it's a future date
		return !(year > LocalDate.now().getYear()
				|| (year == LocalDate.now().getYear() && month > LocalDate.now().getMonthValue())
				|| (year == LocalDate.now().getYear() && month == LocalDate.now().getMonthValue()
						&& day > LocalDate.now().getDayOfMonth()));
	}
}

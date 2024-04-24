package model;

import java.io.Serializable;
import java.time.LocalDate;

public class UserModel implements Serializable {
	private static final long serialVersionUID =1;
	private int userID;
	private String firstName;
	private String lastName;
	private String username;
	private LocalDate dob;
	private String email;
	private String gender;
	private String userType;
	private String password;
	private String avatar;

	public UserModel() {
		super();
	}

	public UserModel(int userID, String firstName, String lastName, String username, LocalDate dob, String email,
			String gender, String userType, String password, String avatar) {
		super();
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.dob = dob;
		this.email = email;
		this.gender = gender;
		this.userType = userType;
		this.password = password;
		this.avatar = avatar;

	}

	public UserModel(String firstName, String lastName, String username, LocalDate dob, String email, String gender,
			String userType, String password, String avatar) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.dob = dob;
		this.email = email;
		this.gender = gender;
		this.userType = userType;
		this.password = password;
		this.avatar = avatar;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "UserModel [userID=" + userID + ", firstName=" + firstName + ", lastName=" + lastName + ", username="
				+ username + ", dob=" + dob + ", email=" + email + ", gender=" + gender + ", userType=" + userType
				+ ", password=" + password + ", avatar=" + avatar + "]";
	}

}

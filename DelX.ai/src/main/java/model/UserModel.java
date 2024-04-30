package model;

import java.io.File;
import java.io.Serializable;
import javax.servlet.http.Part;

import utils.StringUtils;

import java.time.LocalDate;

public class UserModel implements Serializable {
	private static final long serialVersionUID = 1;
	private int userID;
	private String firstName;
	private String lastName;
	private String username;
	private LocalDate dob;
	private String email;
	private String gender;
	private String userType;
	private String password;
	private String imageUrlFromPart;

	public UserModel() {
		super();
	}

	public UserModel(int userID, String firstName, String lastName, String username, LocalDate dob, String email,
			String gender, String userType, String password, Part imgPart) {
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
		this.imageUrlFromPart = getImageUrl(imgPart);

	}

	public UserModel(String firstName, String lastName, String username, LocalDate dob, String email, String gender,
			String userType, String password, Part imgPart) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.dob = dob;
		this.email = email;
		this.gender = gender;
		this.userType = userType;
		this.password = password;
		this.imageUrlFromPart = getImageUrl(imgPart);

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getImageUrlFromPart() {
		return imageUrlFromPart;
	}

	public void setImageUrlFromPart(Part part) {
		this.imageUrlFromPart = getImageUrl(part);
	}

	public void setImageUrlFromDB(String imageUrl) {
		this.imageUrlFromPart = imageUrl;
	}

	private String getImageUrl(Part part) {
		String savePath = StringUtils.IMAGE_DIR_SAVE_PATH_USER;
		File fileSaveDir = new File(savePath);
		String imageUrlFromPart = null;
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		if (imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
			imageUrlFromPart = "defaultAV.jpg";
		}
		return imageUrlFromPart;
	}

}

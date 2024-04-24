package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	private int categoryID;
	private String categoryTitle;
	private String categoryDesc;
	private ArrayList<Catalog> catalogs = new ArrayList<Catalog>();

	public Category() {
		

	}

	public Category(int categoryID, String categoryName, String categoryDesc) {
		super();
		this.categoryID = categoryID;
		this.categoryTitle = categoryName;
		this.categoryDesc = categoryDesc;
	}

	public Category(String categoryTitle, String categoryDesc) {
		super();
		this.categoryTitle = categoryTitle;
		this.categoryDesc = categoryDesc;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryTitle;
	}

	public void setCategoryName(String categoryName) {
		this.categoryTitle = categoryName;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	@Override
	public String toString() {
		return "Category [categoryID=" + categoryID + ", categoryName=" + categoryTitle + ", categoryDesc="
				+ categoryDesc + "]";
	}

}

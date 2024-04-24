package model;

public class Catalog {

	private int catalogID;
	private String toolName;
	private String toolDesc;
	private String toolAuthor;
	private String toolImg;
	private Category category;

	public Catalog() {

	}

	public Catalog(int catalogID, String toolName, String toolDesc, String toolAuthor, String toolImg,
			Category category) {
		super();
		this.catalogID = catalogID;
		this.toolName = toolName;
		this.toolDesc = toolDesc;
		this.toolAuthor = toolAuthor;
		this.toolImg = toolImg;
		this.category = category;
	}

	public Catalog(String toolName, String toolDesc, String toolAuthor, String toolImg, Category category) {
		super();
		this.toolName = toolName;
		this.toolDesc = toolDesc;
		this.toolAuthor = toolAuthor;
		this.toolImg = toolImg;
		this.category = category;
	}

	public int getCatalogID() {
		return catalogID;
	}

	public void setCatalogID(int catalogID) {
		this.catalogID = catalogID;
	}

	public String getToolName() {
		return toolName;
	}

	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	public String getToolDesc() {
		return toolDesc;
	}

	public void setToolDesc(String toolDesc) {
		this.toolDesc = toolDesc;
	}

	public String getToolAuthor() {
		return toolAuthor;
	}

	public void setToolAuthor(String toolAuthor) {
		this.toolAuthor = toolAuthor;
	}

	public String getToolImg() {
		return toolImg;
	}

	public void setToolImg(String toolImg) {
		this.toolImg = toolImg;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Catalog [catalogID=" + catalogID + ", toolName=" + toolName + ", toolDesc=" + toolDesc + ", toolAuthor="
				+ toolAuthor + ", toolImg=" + toolImg + ", category=" + category + "]";
	}

}

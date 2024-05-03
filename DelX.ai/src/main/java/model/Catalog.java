package model;

import javax.servlet.http.Part;
import java.io.File;
import java.io.Serializable;

import utils.StringUtils;

public class Catalog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int catalogID;
	private String toolName;
	private String toolDesc;
	private String toolAuthor;
	private String imageUrlFromPart;
	private Category category;

	public Catalog() {

	}

	public Catalog(int catalogID, String toolName, String toolDesc, String toolAuthor, Part imgPart,
			Category category) {
		super();
		this.catalogID = catalogID;
		this.toolName = toolName;
		this.toolDesc = toolDesc;
		this.toolAuthor = toolAuthor;
		this.imageUrlFromPart = getImageUrl(imgPart);
		this.category = category;
	}

	public Catalog(String toolName, String toolDesc, String toolAuthor, Part imgPart, Category category) {
		super();
		this.toolName = toolName;
		this.toolDesc = toolDesc;
		this.toolAuthor = toolAuthor;
		this.imageUrlFromPart = getImageUrl(imgPart);
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
		String savePath = StringUtils.IMAGE_DIR_SAVE_PATH;
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
			imageUrlFromPart = "defaultAI2.jpg";
		}
		return imageUrlFromPart;
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
				+ toolAuthor + ", imageUrlFromPart=" + imageUrlFromPart + ", category=" + category + "]";
	}

}

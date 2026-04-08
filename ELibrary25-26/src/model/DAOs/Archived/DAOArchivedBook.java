package model.DAOs.Archived;

public class DAOArchivedBook {

	

	private String callNumber;
	private String title;
	private String author;
	private String publicationYear;
	private String bookType;
	private String collectionCode;
	private String classificationCode;

	public String getCallNumber() {
		return callNumber;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getPublicationYear() {
		return publicationYear;
	}

	public String getBookType() {
		return bookType;
	}

	public String getCollectionCode() {
		return collectionCode;
	}

	public String getClassificationCode() {
		return classificationCode;
	}

	public DAOArchivedBook(String callNumber, String title, String author, String publicationYear, String bookType, String collectionCode, String classificationCode) {
		this.callNumber = callNumber;
		this.title = title;
		this.author = author;
		this.publicationYear = publicationYear;
		this.bookType = bookType;
		this.collectionCode = collectionCode;
		this.classificationCode = classificationCode;
	}
}

package model.DAOs.Book;

public class DAOBook {

	

    private String callNumber;
    private String title;
    private String author;
    private String publicationYear;
    private String borrowable;
    private String classificationCode;

    public String getCallnumber() {
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

    public String getBorrowable() {
        return borrowable;
    }

    public String getClassificationCode() {
        return classificationCode;
    }

    public DAOBook(String callNumber, String title, String author, String publicationYear, String borrowable, String classificationCode) {
        this.callNumber = callNumber;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.borrowable = borrowable;
        this.classificationCode = classificationCode;
    }
}

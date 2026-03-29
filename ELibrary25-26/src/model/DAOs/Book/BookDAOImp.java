package model.DAOs.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImp implements DAOShowBook, DAOInsertBook {

    private final String URL = "jdbc:sqlserver://26.91.144.197:1433;databaseName=bsu_elibrary;encrypt=true;trustServerCertificate=true";
    private final String USER = "Pia";
    private final String PASSWORD = "passwordPia";

    @Override
    public List<DAOBook> getAllBooks(String collection) {

        List<DAOBook> books = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM showAllBooks WHERE CollectionCode = ?")) {

            stmt.setString(1, collection);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                books.add(new DAOBook(
                        rs.getString("CallNumber"),
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getString("PublicationYear"),
                        rs.getString("BookType"),
                        rs.getString("ClassificationCode")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

	@Override
	public void insertOne(String callNumber, String title, String author, String year, String bookType,
			String collection, String classification) {
		String bookTypeValue = "BB";
		switch (bookType) {
		case "Borrowable":
			bookTypeValue = "BB";
			break;
		case "Non-Borrowable":
			bookTypeValue = "NBB";
			break;
		default:
			System.out.println("error at book type");
			break;
		}
		
		String collectionValue = "BulColl";
		switch (collection) {
		case "Bulacaniana Collection":
			collectionValue = "BulColl";
			break;
		case "General Circulation Collection":
			collectionValue = "CircColl";
			break;
		case "Fiction Collection":
			collectionValue = "FictColl";
			break;
		case "Filipiniana Collection":
			collectionValue = "FiliColl";
			break;
		case "Reference Collection":
			collectionValue = "RefColl";
			break;
		case "Reserve Collection":
			collectionValue = "ResColl";
			break;
		case "Theses and Dissertations":
			collectionValue = "T&D";
			break;
		default:
			System.out.println("error at collection");
			break;
		}
		
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); CallableStatement stmt = conn.prepareCall("{CALL addNewRecord_Book(?, ?, ?, ?, ?, ?, ?, ?)}")) {

			
			
            stmt.setString(1, callNumber);
            stmt.setString(2, title);
            stmt.setString(3, author);
            stmt.setString(4, year);
            stmt.setString(5, bookTypeValue);
            stmt.setString(6, collectionValue);
            stmt.setString(7, classification);
            stmt.setString(8, "test");

            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}

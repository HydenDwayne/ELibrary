package model.DAOs.Book;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImp {

	private final String URL = "jdbc:sqlserver://26.91.144.197:1433;databaseName=bsu_elibrary;encrypt=true;trustServerCertificate=true";
	private final String USER = "Pia";
	private final String PASSWORD = "passwordPia";

	public List<DAOBook> getAllBooks(String collection, String searchQuery, String filterClass, String filterStartYear,
			String filterEndYear) {

		List<DAOBook> books = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				CallableStatement stmt = conn.prepareCall("{CALL searchBooks(?,?,?,?,?)}")) {

			if (searchQuery == null) {
				stmt.setString(1, "");
			} else {
				stmt.setString(1, searchQuery);
			}
			stmt.setString(2, collection);

			if (filterClass.equals("")) {
				stmt.setNull(3, java.sql.Types.INTEGER);
			} else {
				stmt.setString(3, filterClass);
			}

			if (filterStartYear.equals("")) {
				stmt.setNull(4, java.sql.Types.INTEGER);
			} else {
				stmt.setString(4, filterStartYear);
			}

			if (filterEndYear.equals("")) {
				stmt.setNull(5, java.sql.Types.INTEGER);
			} else {
				stmt.setString(5, filterEndYear);
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				books.add(new DAOBook(rs.getString("CallNumber"), rs.getString("Title"), rs.getString("Author"),
						rs.getString("PublicationYear"), rs.getString("BookType"), rs.getString("ClassificationCode")));
			}

		} catch (SQLException e) {
//			e.printStackTrace();
		}

		return books;
	}

	public boolean insertOne(String callNumber, String title, String author, String year, String bookType,
			String collection, String classification, String seriesTitle) {
		String bookTypeValue = "BB";
		switch (bookType) {
		case "BORROWABLE":
			bookTypeValue = "BB";
			break;
		case "NON-BORROWABLE":
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

		String seriesText = "";
		if (seriesTitle.equals("") || seriesTitle.equals(null)) {
			seriesText = "";
		} else {
			seriesText = seriesTitle;
		}

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				CallableStatement stmt = conn.prepareCall("{CALL addNewRecord_Book(?, ?, ?, ?, ?, ?, ?, ?)}")) {

			stmt.setString(1, callNumber);
			stmt.setString(2, title);
			stmt.setString(3, author);
			stmt.setDate(4, java.sql.Date.valueOf(year + "-01-01"));
			stmt.setString(5, bookTypeValue);
			stmt.setString(6, collectionValue);
			stmt.setString(7, classification);
			stmt.setString(8, seriesText);

			stmt.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String[] getBookDetails(String callNumber) {

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement("SELECT BookType FROM BOOK WHERE CallNumber = ?")) {
			stmt.setString(1, callNumber);

			ResultSet checker = stmt.executeQuery();

			String bookType = "";

			while (checker.next()) {
				bookType = checker.getString("BookType");
			}

			if (bookType.equals("NBB")) {
				try (CallableStatement cs = conn.prepareCall("{CALL getNonBorrowableBookDetails(?)}")) {

					cs.setString(1, callNumber);

					String bookTypeFull = "NON-BORROWABLE BOOK";

					ResultSet rs = cs.executeQuery();

					rs.next();

					String[] bookDetails = { rs.getString("CallNumber"), rs.getString("Title"), rs.getString("Author"),
							rs.getString("PublicationYear"), bookType, rs.getString("CollectionName"),
							rs.getString("Classification"), rs.getString("SeriesTitle"), };

					return bookDetails;

				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
			if (bookType.equals("BB")) {
				try (CallableStatement cs = conn.prepareCall("{CALL getBorrowableBookDetails(?)}")) {

					cs.setString(1, callNumber);

					String bookTypeFull = "BORROWABLE BOOK";

					ResultSet rs = cs.executeQuery();

					rs.next();

					String[] bookDetails = { rs.getString("CallNumber"), rs.getString("Title"), rs.getString("Author"),
							rs.getString("PublicationYear"), bookType, rs.getString("CollectionName"),
							rs.getString("Classification"), rs.getString("AvailabilityStatus"), };

					return bookDetails;

				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			} else {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String[] getAllClassification() {

		List<String> classifications = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(
						"SELECT ClassificationCode + ' - ' + ClassificationName AS Classification FROM BOOK_CLASSIFICATION")) {

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				classifications.add(rs.getString("Classification"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		// Convert List<String> → String[]
		return classifications.toArray(new String[0]);
	}

	public boolean checkBookExists(String callNumber) {
		String sql = "SELECT COUNT(*) FROM BOOK WHERE CallNumber = ? and isArchived = 0";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, callNumber);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0; // If count > 0, record exists
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean checkBookType(String callNumber) {
		String sql = "SELECT COUNT(*) FROM BOOK as b, BORROWABLE_BOOK as bb WHERE b.CallNumber = ? AND BookType = 'BB' AND b.isArchived = 0 AND b.CallNumber = bb.CallNumber AND bb.AvailabilityStatus = 'Available'";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, callNumber);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0; // If count > 0, record exists
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public String[] getBorrowDetails(String callNumber) {

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				CallableStatement stmt = conn.prepareCall("{CALL getBorrowBookDetails(?)}")) {

			stmt.setString(1, callNumber);
			ResultSet rs = stmt.executeQuery();

			rs.next();

			String[] bookDetails = { rs.getString("Title"), rs.getString("Author"), rs.getString("CollectionName"),
					rs.getString("AvailabilityStatus"), rs.getString("CallNumber") };

			return bookDetails;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getTransactionID() {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn
						.prepareStatement("select top 1 TransactionID from BOOK_LOAN order by TransactionID desc")) {

			ResultSet rs = stmt.executeQuery();

			String txnID;
			if (rs.next()) {
				txnID = rs.getString("TransactionID");
			} else {
				txnID = "BL2026000000"; // default if table is empty
			}

			// Split into prefix and numeric part
			int splitIndex = 0;
			// Find where digits start
			for (int i = 0; i < txnID.length(); i++) {
				if (Character.isDigit(txnID.charAt(i))) {
					splitIndex = i;
					break;
				}
			}
			String prefix = txnID.substring(0, splitIndex);
			String numberPart = txnID.substring(splitIndex);

			// Increment numeric part
			long number = Long.parseLong(numberPart);
			number += 1;

			// Preserve leading zeros: use the same length as original numeric part
			String newNumberPart = String.format("%0" + numberPart.length() + "d", number);

			return prefix + newNumberPart;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String[] addBookLoan(String[] borrowDetails) {

		String txnID = getTransactionID();

		LocalDate borrowDate = LocalDate.now();

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				CallableStatement stmt = conn.prepareCall("{CALL addNewRecord_BookLoan(?,?,?,?,?,?)}")) {

			stmt.setString(1, txnID);
			stmt.setString(2, borrowDetails[0]);
			stmt.setDate(3, java.sql.Date.valueOf(borrowDate));
			stmt.setDate(4, java.sql.Date.valueOf(borrowDetails[1]));
			stmt.setString(5, borrowDetails[2]);
			stmt.setString(6, borrowDetails[3]);
			stmt.executeUpdate();

			String[] bookDetails = { txnID, borrowDetails[0], borrowDetails[3], borrowDetails[1] };

			return bookDetails;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String[] checkTxnID(String txnID) {
	    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	         CallableStatement stmt = conn.prepareCall(
	             "{CALL getBookLoanByTransactionID(?)}")) {

	        stmt.setString(1, txnID);

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                return new String[]{
	                    rs.getString("TransactionID"),
	                    rs.getString("CallNumber"),
	                    rs.getString("BorrowDate"),
	                    rs.getString("DueDate"),
	                    rs.getString("PatronID")
	                };
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public boolean returnBook(String txnID, String callNumber) {
		LocalDate today = LocalDate.now();

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				CallableStatement stmt = conn.prepareCall("{CALL addNewRecord_ReturnBook(?,?,?)}")) {

			stmt.setString(1, txnID);
			stmt.setString(2, callNumber);
			stmt.setDate(3, java.sql.Date.valueOf(today));

			stmt.executeUpdate();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateBook(String[] bookDetails) {
		
		String collectionValue = "";
		switch (bookDetails[5]) {
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
		
		String classification = bookDetails[6];
		String classificationCode = classification.split("-")[0].trim();
		
		boolean isArchived = false;
		if (bookDetails[7].equals("Archived")) {
			isArchived = true;
		}
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        		CallableStatement stmt = conn.prepareCall("{call updateBook(?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {

            stmt.setString(1, bookDetails[0]);
            stmt.setString(2, bookDetails[1]);
            stmt.setString(3, bookDetails[2]);
            stmt.setDate(4, Date.valueOf(bookDetails[3] + "-01-01"));
            stmt.setString(5, bookDetails[4]);
            stmt.setString(6, collectionValue);
            stmt.setString(7, classificationCode);
            stmt.setBoolean(8, isArchived);
            stmt.setString(9, bookDetails[8]);

            stmt.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

package model.DAOs.Book;


public interface DAOInsertBook {

	void insertOne(String callNumber, String title, String author, String year, String bookType, String collection, String classification);
	
}

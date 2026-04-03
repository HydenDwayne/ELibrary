package controller;

import model.DAOs.Book.BookDAOImp;
import view.RoundedComponents.RoundedComboBox;
import view.RoundedComponents.RoundedTextField;
import view.modal.books_modal.ViewBooks;

public class BookController {

	BookDAOImp daoBook = new BookDAOImp();

	public BookController(ViewBooks vb, String callNumber) {
		String[] bookDetails = daoBook.getBookDetails(callNumber);
		
		vb.bookTitleField.setText(bookDetails[1]);
		vb.authorField.setText(bookDetails[2]);
		vb.yearField.setText(bookDetails[3].substring(0, 4));
		vb.classField.setText(bookDetails[6]);
		
		switch (bookDetails[5]) {
		case "Bulacaniana Collection":
			vb.collectionCombo.setSelectedIndex(0);
			break;
		case "General Circulation Section":
			vb.collectionCombo.setSelectedIndex(1);
			break;
		case "Fiction Collection":
			vb.collectionCombo.setSelectedIndex(2);
			break;
		case "Filipiniana Collection":
			vb.collectionCombo.setSelectedIndex(3);
			break;
		case "Reference Collection":
			vb.collectionCombo.setSelectedIndex(4);
			break;
		case "Reserve Collection":
			vb.collectionCombo.setSelectedIndex(5);
			break;
		case "Theses and Dissertations":
			vb.collectionCombo.setSelectedIndex(6);
			break;
			
		default:
			break;
		}
		
		if (bookDetails[4].equals("BB")) {
			
			if (bookDetails[7].equals("Available")) {
				vb.availCombo.setSelectedIndex(0);
			}
			if (bookDetails[7].equals("Borrowed")) {
				vb.availCombo.setSelectedIndex(1);
			}
			if (bookDetails[7].equals("Archived")) {
				vb.availCombo.setSelectedIndex(2);
			}
			vb.typeCombo.setSelectedIndex(1);
			
			vb.seriesField.setText("N/A");
			vb.seriesField.setEnabled(false);
		}
		if (bookDetails[4].equals("NBB")) {
			vb.seriesField.setText(bookDetails[7]);
			vb.typeCombo.setSelectedIndex(0);
			
			vb.availCombo.setEnabled(false);
		}	
	}
}

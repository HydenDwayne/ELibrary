package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.time.DayOfWeek;
import java.time.LocalDate;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.DAOs.Book.BookDAOImp;
import model.DAOs.Patron.PatronDAOImp;
import view.RoundedComponents.RoundedComboBox;
import view.RoundedComponents.RoundedTextField;
import view.modal.books_modal.AddBook;
import view.modal.books_modal.AddBorrowingRequestBook1;
import view.modal.books_modal.AddBorrowingRequestBook2;
import view.modal.books_modal.AddBorrowingRequestReceipt;
import view.modal.books_modal.ReturnBook;
import view.modal.books_modal.ReturnBookConfirm;
import view.modal.books_modal.ViewBooks;

public class BookController {

	BookDAOImp daoBook = new BookDAOImp();
	PatronDAOImp daoPatron = new PatronDAOImp();
	
	String callNumber;

	public BookController(ViewBooks vb, String callNumber) {
		String[] bookDetails = daoBook.getBookDetails(callNumber);
		
		vb.bookTitleField.setText(bookDetails[1]);
		vb.authorField.setText(bookDetails[2]);
		vb.yearField.setText(bookDetails[3].substring(0, 4));
		vb.classField.setText(bookDetails[6]);
		vb.callNumberField.setText(callNumber);
		
		
		
		
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
			vb.collectionCombo.setSelectedIndex(0);
			break;
		case "Reserve Collection":
			vb.collectionCombo.setSelectedIndex(1);
			break;
		case "Theses and Dissertations":
			vb.collectionCombo.setSelectedIndex(2);
			break;
			
		default:
			break;
		}
	}
	
	public BookController(AddBook ab, GridBagConstraints gbc, Font poppins10, JPanel innerBody, int FIELD_RADIUS, Color FIELD_BORDER) {
	    gbc.gridx = 1;
	    String[] classCodes = daoBook.getAllClassification();
	    
	    ab.classCombo = new RoundedComboBox<>(classCodes, FIELD_RADIUS); 
	    ab.classCombo.setFont(poppins10);
	    ab.classCombo.setPlaceholder("Select");
	    ab.classCombo.setBorderColor(FIELD_BORDER);
	    ab.classCombo.setBorderThickness(1);
	    ab.classCombo.setPreferredSize(new Dimension(200, 30));
	    innerBody.add(ab.classCombo, gbc);
	}
	
	
	public BookController(String[] bookDetails) {
		boolean isSuccessful = daoBook.insertOne(
				bookDetails[0],
				bookDetails[1],
				bookDetails[2],
				bookDetails[3],
				bookDetails[4],
				bookDetails[5],
				bookDetails[6],
				bookDetails[7]
						);
		
		if (!isSuccessful) {
			JOptionPane.showMessageDialog(null, "Error. No record added.");
		}
	}
	
	
	public BookController(AddBorrowingRequestBook1 step1, String callNumber, String patronID) {
		boolean doesExistBook = daoBook.checkBookExists(callNumber);
		boolean doesExistPatron = daoPatron.checkPatronExists(patronID);
		boolean isBorrowable = daoBook.checkBookType(callNumber);
		boolean isLimited = daoPatron.checkPatronBorrowLimit(patronID);
		
		if (!doesExistBook && doesExistPatron) {
			JOptionPane.showMessageDialog(null, "Call number does not match any record");
		} 
		else if (isLimited) {
			JOptionPane.showMessageDialog(null, "Patron can only borrow one book at a time");
		}
		else if (doesExistBook && !doesExistPatron) {
			JOptionPane.showMessageDialog(null, "Patron ID does not match any record");
		} 
		else if (!doesExistBook && !doesExistPatron) {
			JOptionPane.showMessageDialog(null, "Call number and Patron ID does not match any record");
		} 
		else if (doesExistBook && doesExistPatron) {
			if (isBorrowable) {
				step1.isFound = true;
			} else {
				JOptionPane.showMessageDialog(null, "Book cannot be borrowed at this time.");
			}
			
		}
		
	}
	
	public String getReturnDate() {
        LocalDate today = LocalDate.now();

        LocalDate finalDate = today.plusDays(3);

        DayOfWeek dayOfWeek = finalDate.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY) {
            finalDate = finalDate.plusDays(2);
        } else if (dayOfWeek == DayOfWeek.SUNDAY) {
            finalDate = finalDate.plusDays(1);
        }

        return finalDate+"";
    }
	
	public BookController(AddBorrowingRequestBook2 step2, String callNumber, String patronID) {
		String[] bookDetails = daoBook.getBorrowDetails(callNumber);
		String returnDate = getReturnDate();

		step2.bookTitleField.setText(bookDetails[0]);
		step2.authorField.setText(bookDetails[1]);
		step2.collectionField.setText(bookDetails[2]);
		step2.statusField.setText(bookDetails[3]);
		step2.callNumField.setText(bookDetails[4]);
		step2.patronField.setText(patronID);
		step2.dueDateField.setText(returnDate);
	}
	
	public BookController(AddBorrowingRequestReceipt step3, String[] borrowDetails) {
		String[] borrowReceipt = daoBook.addBookLoan(borrowDetails);
		
		step3.txnField.setText(borrowReceipt[0]);
		step3.callField.setText(borrowReceipt[1]);
		step3.patronField.setText(borrowReceipt[2]);
		step3.dueField.setText(borrowReceipt[3]);
		
	}
	
	public BookController(ReturnBook rb, String txnID) {
		String[] borrowDetails = daoBook.checkTxnID(txnID);

		
		if (borrowDetails == null) {
			JOptionPane.showMessageDialog(null, "No matching record found");
		} else {
			rb.borrowDetails = borrowDetails;
		}
	}
	
	public BookController(ReturnBookConfirm rbc, String txnID, String callNumber) {
		boolean isSuccessful = daoBook.returnBook(txnID, callNumber);
		
		if(!isSuccessful) {
			JOptionPane.showMessageDialog(null, "Error. No book returned");
		}
	}

    public BookController(ViewBooks vb, String[] bookDetails) {
    	boolean isSuccessful = daoBook.updateBook(bookDetails);
    	
    	if(!isSuccessful) {
			JOptionPane.showMessageDialog(null, "Error. No details updated");
		}
    }
    
    public BookController(String callNumber) {
    	this.callNumber = callNumber;
    }
    
    public boolean checkIfExists() {
    	boolean exists = daoBook.checkBookExists(callNumber);
    	
    	
    	return exists;
    }
}
	


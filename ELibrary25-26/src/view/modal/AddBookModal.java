package view.modal;

import javax.swing.*;

import controller.MainFunctions;

import java.awt.*;
import view.RoundedComponents.*;

public class AddBookModal extends JOptionPane{

	JPanel modalContent = new JPanel();
	
	public JPanel getModalContent() {
		return modalContent;
	}

	public String getCallNumberStr() {
		return callNumberStr;
	}

	public String getTitleStr() {
		return titleStr;
	}

	public String getAuthorStr() {
		return authorStr;
	}

	public String getYearStr() {
		return yearStr;
	}

	public String getBookTypeStr() {
		return bookTypeStr;
	}

	public String getCollectionStr() {
		return collectionStr;
	}

	public String getClassificationStr() {
		return classificationStr;
	}

	String titleStr;
	String authorStr;
	String yearStr;
	String bookTypeStr;
	String collectionStr;
	String classificationStr;
	String callNumberStr;

	public AddBookModal() {
		modalContent.setLayout(new GridLayout(7,1));
		modalContent.setPreferredSize(new Dimension(500,350));
		
		JPanel callNumberPanel = new JPanel();
		JPanel bookTitlePanel = new JPanel();
		JPanel authorPanel = new JPanel();
		JPanel publicationYearPanel = new JPanel();
		JPanel bookTypePanel = new JPanel();
		JPanel collectionNumberPanel = new JPanel();
		JPanel classificationPanel = new JPanel();
		
		callNumberPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		bookTitlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		authorPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		publicationYearPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		bookTypePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		collectionNumberPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		classificationPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		
		callNumberPanel.setLayout(new BorderLayout());
		bookTitlePanel.setLayout(new BorderLayout());
		authorPanel.setLayout(new BorderLayout());
		publicationYearPanel.setLayout(new BorderLayout());
		bookTypePanel.setLayout(new BorderLayout());
		collectionNumberPanel.setLayout(new BorderLayout());
		classificationPanel.setLayout(new BorderLayout());
		
		JLabel callNumberLabel = new JLabel("Call Number");
		JLabel bookTitleLabel = new JLabel("Book Title");
		JLabel authorLabel = new JLabel("Author");
		JLabel publicationYearLabel = new JLabel("Publication Year");
		JLabel bookTypeLabel = new JLabel("Book Type");
		JLabel collectionLabel = new JLabel("Collection");
		JLabel classificationLabel = new JLabel("Classification");
		
		String[] bookTypeItems = {
				"Borrowable",
				"Non-Borrowable"
				};
		String[] collectionItems = {
	            "Bulacaniana Collection",
	            "General Circulation Section",
	            "Fiction Collection",
	            "Filipiniana Collection",
	            "Reference Collection",
	            "Reserve Collection",
	            "Theses and Dissertations"
	        };
		
		String[] classificationItems = {
				"A - General Works",
				"B - Philosophy, Psychology, Religion",
				"C - Auxiliary Sciences of History",
				"D - History: General and Old World",
				"E - America",
				"F - U.S. Local History, Canada, Latin America",
				"G - Geography, Anthropology, Recreation",
				"H - Social Sciences",
				"J - Political Science",
				"K - Law",
				"L - Education",
				"M - Music",
				"N - Fine Arts",
				"P - Language and Literature",
				"Q - Science",
				"R - Medicine",
				"S - Agriculture",
				"T - Technology",
				"U - Military Science",
				"V - Naval Science",
				"Z - Bibliography"
		};
		
		RoundedTextField callNumberField = new RoundedTextField(25, 20);
		RoundedTextField bookTitleField = new RoundedTextField(25, 20);
		RoundedTextField authorField = new RoundedTextField(25, 20);
		RoundedTextField publicationYearField = new RoundedTextField(25, 20);
		RoundedComboBox<String> bookTypeField = new RoundedComboBox<>(bookTypeItems,20);
		RoundedComboBox<String> collectionField = new RoundedComboBox<>(collectionItems,20);
		RoundedComboBox<String> classificationField = new RoundedComboBox<>(classificationItems,20);
		
		
		bookTypeField.setPreferredSize(new Dimension(270, 30));
		collectionField.setPreferredSize(new Dimension(270, 30));
		classificationField.setPreferredSize(new Dimension(270, 30));
		
		callNumberPanel.add(callNumberLabel, BorderLayout.WEST);
		bookTitlePanel.add(bookTitleLabel, BorderLayout.WEST);
		authorPanel.add(authorLabel, BorderLayout.WEST);
		publicationYearPanel.add(publicationYearLabel, BorderLayout.WEST);
		bookTypePanel.add(bookTypeLabel, BorderLayout.WEST);
		collectionNumberPanel.add(collectionLabel, BorderLayout.WEST);
		classificationPanel.add(classificationLabel, BorderLayout.WEST);
		
		callNumberPanel.add(callNumberField, BorderLayout.EAST);
		bookTitlePanel.add(bookTitleField, BorderLayout.EAST);
		authorPanel.add(authorField, BorderLayout.EAST);
		publicationYearPanel.add(publicationYearField, BorderLayout.EAST);
		bookTypePanel.add(bookTypeField, BorderLayout.EAST);
		collectionNumberPanel.add(collectionField, BorderLayout.EAST);
		classificationPanel.add(classificationField, BorderLayout.EAST);

		modalContent.add(callNumberPanel);
		modalContent.add(bookTitlePanel);
		modalContent.add(authorPanel);
		modalContent.add(publicationYearPanel);
		modalContent.add(bookTypePanel);
		modalContent.add(collectionNumberPanel);
		modalContent.add(classificationPanel);
		
		
		
		
		int option = showConfirmDialog(
                null,
                modalContent,
                "Add a new book",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );
		
		if (option == JOptionPane.OK_OPTION) {
			callNumberStr = callNumberField.getText().trim();
			titleStr = bookTitleField.getText().trim();
			authorStr = authorField.getText().trim();
			yearStr = publicationYearField.getText().trim();			
			
			int selectedType = bookTypeField.getSelectedIndex();
			bookTypeStr = bookTypeItems[selectedType];
			
			int selectedColl = collectionField.getSelectedIndex();
			collectionStr = collectionItems[selectedColl];
			
			int selectedClass = classificationField.getSelectedIndex();
			classificationStr = classificationItems[selectedClass];
			
			if (callNumberStr.isEmpty() || titleStr.isEmpty() || authorStr.isEmpty() || yearStr.isEmpty()) {
				JOptionPane.showMessageDialog(
                        null,
                        "All fields are required. Please fill in every field.",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE
                );
			} else {
				MainFunctions comp = new MainFunctions(this);
				
				
				
				JOptionPane.showMessageDialog(
	                    null,
	                    "Succesfully added book!",
	                    "Notice",
	                    JOptionPane.INFORMATION_MESSAGE
	            );
				
			}
			
			
			
		}
	}
	

}

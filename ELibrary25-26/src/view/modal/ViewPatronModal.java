package view.modal;

import javax.swing.*;

import controller.MainFunctions;

import java.awt.*;
import view.RoundedComponents.*;

public class ViewPatronModal extends JOptionPane{

	JPanel modalContent = new JPanel();

	public String getRow1Str() {
		return row1Str;
	}

	public String getRow2Str() {
		return row2Str;
	}

	public String getRow3Str() {
		return row3Str;
	}

	public String getRow4Str() {
		return row4Str;
	}

	public String getRow5Str() {
		return row5Str;
	}

	public String getRow6Str() {
		return row6Str;
	}

	public String getRow7Str() {
		return row7Str;
	}

	String row1Str;
	String row2Str;
	String row3Str;
	String row4Str;
	String row5Str;
	String row6Str;
	String row7Str;

	public ViewPatronModal() {
		modalContent.setLayout(new GridLayout(7,1));
		modalContent.setPreferredSize(new Dimension(500,350));
		
		JPanel row1 = new JPanel();
		JPanel row2 = new JPanel();
		JPanel row3 = new JPanel();
		JPanel row4 = new JPanel();
		JPanel row5 = new JPanel();
		JPanel row6 = new JPanel();
		JPanel row7 = new JPanel();
		
		row1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		row2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		row3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		row4.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		row5.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		row6.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		row7.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		
		row1.setLayout(new BorderLayout());
		row2.setLayout(new BorderLayout());
		row3.setLayout(new BorderLayout());
		row4.setLayout(new BorderLayout());
		row5.setLayout(new BorderLayout());
		row6.setLayout(new BorderLayout());
		row7.setLayout(new BorderLayout());
		
		JLabel text1 = new JLabel("Call Number");
		JLabel text2 = new JLabel("Book Title");
		JLabel text3 = new JLabel("Author");
		JLabel text4 = new JLabel("Publication Year");
		JLabel text5 = new JLabel("Book Type");
		JLabel text6 = new JLabel("Collection");
		JLabel text7 = new JLabel("Classification");
		
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
		
		RoundedTextField field1 = new RoundedTextField(25, 20);
		RoundedTextField field2 = new RoundedTextField(25, 20);
		RoundedTextField field3 = new RoundedTextField(25, 20);
		RoundedTextField field4 = new RoundedTextField(25, 20);
		RoundedComboBox<String> field5 = new RoundedComboBox<>(bookTypeItems,20);
		RoundedComboBox<String> field6 = new RoundedComboBox<>(collectionItems,20);
		RoundedComboBox<String> field7 = new RoundedComboBox<>(classificationItems,20);
		
		
		field5.setPreferredSize(new Dimension(270, 30));
		field6.setPreferredSize(new Dimension(270, 30));
		field7.setPreferredSize(new Dimension(270, 30));
		
		row1.add(text1, BorderLayout.WEST);
		row2.add(text2, BorderLayout.WEST);
		row3.add(text3, BorderLayout.WEST);
		row4.add(text4, BorderLayout.WEST);
		row5.add(text5, BorderLayout.WEST);
		row6.add(text6, BorderLayout.WEST);
		row7.add(text7, BorderLayout.WEST);
		
		row1.add(field1, BorderLayout.EAST);
		row2.add(field2, BorderLayout.EAST);
		row3.add(field3, BorderLayout.EAST);
		row4.add(field4, BorderLayout.EAST);
		row5.add(field5, BorderLayout.EAST);
		row6.add(field6, BorderLayout.EAST);
		row7.add(field7, BorderLayout.EAST);

		modalContent.add(row1);
		modalContent.add(row2);
		modalContent.add(row3);
		modalContent.add(row4);
		modalContent.add(row5);
		modalContent.add(row6);
		modalContent.add(row7);
		
		
		
		
		int option = showConfirmDialog(
                null,
                modalContent,
                "Add a new book",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );
		
		if (option == JOptionPane.OK_OPTION) {
			row1Str = field1.getText().trim();
			row2Str = field2.getText().trim();
			row3Str = field3.getText().trim();
			row4Str = field4.getText().trim();			
			
			int selectedType = field5.getSelectedIndex();
			row5Str = bookTypeItems[selectedType];
			
			int selectedColl = field6.getSelectedIndex();
			row6Str = collectionItems[selectedColl];
			
			int selectedClass = field7.getSelectedIndex();
			row7Str = classificationItems[selectedClass];
			
			if (row1Str.isEmpty() || row2Str.isEmpty() || row3Str.isEmpty() || row4Str.isEmpty()) {
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

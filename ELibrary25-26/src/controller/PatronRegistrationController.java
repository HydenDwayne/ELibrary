package controller;

import javax.swing.*;
import java.awt.event.ActionListener;

import model.DAOs.Patron.PatronDAOImp;
import view.front_pages.patron_registration.*;

public class PatronRegistrationController {

	StudentModal studModal;
	EmployeeModal empModal;
	PatronDAOImp daoPatron = new PatronDAOImp();
	String campusCode;
	GeneralModal genModal;

	public PatronRegistrationController(StudentModal studModal, String campusCode, GeneralModal genModal) {
	    this.empModal = null;
	    this.studModal = studModal;
	    this.genModal = genModal;

	    
	    this.campusCode = toDbCampusCode(campusCode);

	    hookCollegeSelection();
	    hookStudentTypeSelection();
	    loadCollegesAndPrograms();
	}


	
	public PatronRegistrationController(EmployeeModal empModal, String campusCode, GeneralModal genModal) {
		this.studModal = null;
		this.empModal = empModal;
		this.campusCode = campusCode;
		this.genModal = genModal;

		loadEmployeeColleges();
	}
	
	
	public void reloadStudentCollegesAndPrograms() {
	    if (studModal == null) return;
	    loadCollegesAndPrograms();
	}

	
	public void reloadEmployeeColleges() {
	    if (empModal == null) return;
	    loadEmployeeColleges();
	}


	private void loadEmployeeColleges() {

		
		if (campusCode.equalsIgnoreCase("Main") || campusCode.equalsIgnoreCase("Bustos")
				|| campusCode.equalsIgnoreCase("Malolos")) {

			String normalizedCampus = campusCode.equalsIgnoreCase("Malolos") ? "Main" : campusCode;

			String[] colleges = daoPatron.getCollegesPerCampus(normalizedCampus);
			empModal.setCollege(colleges);

		} else {
			empModal.setCollege(new String[] { "" });
		}
	}

	private void loadCollegesAndPrograms() {

		
		int typeIndex = studModal.row3Field.getSelectedIndex();

		if (typeIndex == 1) { 


			String[] gradColleges = { "CLAW", "GS" };
			studModal.setCollege(gradColleges);

			
			studModal.row4Field.setSelectedIndex(0);

			
			SwingUtilities.invokeLater(() -> {
				loadGraduateProgramsInitial();
			});

			return;
		}

		
		String[] colleges = daoPatron.getCollegesPerCampus(campusCode);
		studModal.setCollege(colleges);

		boolean hasColleges = colleges.length > 0;

		if (hasColleges) {
			hookCollegeSelection();
		} else {
			loadProgramsByCampus();
		}
	}

	private void hookCollegeSelection() {
	    studModal.row4Field.addActionListener(e -> {
	        String selectedCollege = (String) studModal.row4Field.getSelectedItem();
	        if (selectedCollege != null && !selectedCollege.isBlank()) {
	            String[] programs =
	                daoPatron.getPrograms(selectedCollege, campusCode);
	            studModal.setPrograms(programs);
	        }
	    });
	}

	private void loadProgramsByCampus() {
		String[] programs = daoPatron.getProgramsByCampus(campusCode);
		studModal.setPrograms(programs);
	}

	
	private void hookStudentTypeSelection() {
		studModal.row3Field.addActionListener(e -> {
			loadCollegesAndPrograms(); 
		});
	}

	private void loadGraduateProgramsInitial() {
		String college = (String) studModal.row4Field.getSelectedItem();

		if (college != null && !college.isEmpty()) {
			String[] programs = daoPatron.getProgramsByCollegeOnly(college);
			studModal.setPrograms(programs);
		}
	}

	public boolean saveStudentRecord() {

		try {
			
			
			
			String patronID = normalizeText(genModal.getPatronID(), "Enter Patron ID");
			String firstName = normalizeText(genModal.getFirstName(), "Enter First Name");
			String lastName = normalizeText(genModal.getLastName(), "Enter Last Name");
			String middleInitial = normalizeText(genModal.getMiddleInitial(), "Enter Middle Initial");
			String email = normalizeText(genModal.getEmail(), "Enter Email Address");
			String contact = normalizeText(genModal.getContact(), "Enter Contact Number");
			String address = normalizeText(genModal.getAddress(), "Enter Home Address");
			

			
			
			
			String yearText =
			        normalizeText(studModal.row2Field.getText(), "e.g. 2026");

			java.sql.Date yearEnrolled =
			        java.sql.Date.valueOf(yearText + "-01-01");

			String studentType = (String) studModal.row3Field.getSelectedItem();

			
			
			
			String yearLevel = null;
			String colCode = null;
			String programCode = null;
			String campus = campusCode;

			String thesisTitle = null;
			java.sql.Date yearGraduated = null;
			
			String gradYearText =
			        normalizeText(studModal.row10Field.getText(), "e.g. 2026");

			if (gradYearText != null) {

			    yearGraduated =
			            java.sql.Date.valueOf(gradYearText + "-01-01");
			}
			
			String degree = null;
			Integer gradeLevel = null;

			
			
			
			switch (studentType) {

			case "UNDERGRADUATE":
				yearLevel = (String) studModal.row6Field.getSelectedItem();
				colCode = (String) studModal.row4Field.getSelectedItem();
				programCode = (String) studModal.row5Field.getSelectedItem();
				break;

			case "GRADUATE":
				thesisTitle = studModal.row8Field.getText().trim();
				degree = (String) studModal.row7Field.getSelectedItem();
				colCode = (String) studModal.row4Field.getSelectedItem();
				programCode = (String) studModal.row5Field.getSelectedItem();

				if (!studModal.row10Field.getText().isBlank()) {
					yearGraduated = java.sql.Date.valueOf(studModal.row10Field.getText());
				}
				break;

			case "LABHIGH":
				String gradeStr = (String) studModal.row9Field.getSelectedItem();
				gradeLevel = Integer.parseInt(gradeStr.replaceAll("\\D", ""));
				break;

			case "ALUMNI":
				if (!studModal.row10Field.getText().isBlank()) {
					yearGraduated = java.sql.Date.valueOf(studModal.row10Field.getText());
				}
				break;
			}

			
			
			
			return daoPatron.insertStudentRecord(patronID, firstName, middleInitial, lastName, email, contact, address,
					campusCode,

					yearEnrolled, studentType,

					yearLevel, colCode, programCode, campus,

					thesisTitle, yearGraduated, degree, gradeLevel);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private String normalizeText(String value, String placeholder) {
		if (value == null)
			return null;

		String trimmed = value.trim();

		if (trimmed.isEmpty())
			return null;
		if (trimmed.equalsIgnoreCase(placeholder))
			return null;

		return trimmed;
	}
	
	private String toDbCampusCode(String uiCampus) {
	    if (uiCampus == null) return null;

	    switch (uiCampus.toLowerCase()) {
	        case "main": return "M";
	        case "bustos": return "BC";
	        case "hagonoy": return "HC";
	        case "meneses": return "MC";
	        case "san rafael": return "SRC";
	        case "sarmiento": return "SC";
	        default: return uiCampus;
	    }
	}
	
	public void setCampusCode(String campusCode) {
	    this.campusCode = toDbCampusCode(campusCode);
	}

	public void reloadCollegesAndPrograms() {
	    loadCollegesAndPrograms();
	}
	
	public boolean saveEmployeeRecord() {

	    try {
	        
	        
	        
	        String patronID  = normalizeText(genModal.getPatronID(), "Enter Patron ID");
	        String firstName = normalizeText(genModal.getFirstName(), "Enter First Name");
	        String lastName  = normalizeText(genModal.getLastName(), "Enter Last Name");
	        String middle    = normalizeText(genModal.getMiddleInitial(), "Enter Middle Initial");
	        String email     = normalizeText(genModal.getEmail(), "Enter Email Address");
	        String contact   = normalizeText(genModal.getContact(), "Enter Contact Number");
	        String address   = normalizeText(genModal.getAddress(), "Enter Home Address");

	        if (patronID == null || firstName == null || lastName == null) {
	            throw new IllegalArgumentException("Patron ID, First Name, and Last Name are required.");
	        }

	        
	        String camp = toDbCampusCode(campusCode);
	        

	        
	        
	        
	        boolean admin   = empModal.adminCheck.isSelected();
	        boolean staff   = empModal.staffCheck.isSelected();
	        boolean faculty = empModal.facultyCheck.isSelected();

	        if (!admin && !staff && !faculty) {
	            throw new IllegalArgumentException("At least one role must be selected.");
	        }

	        
	        
	        
	        String dateText = normalizeText(empModal.row2Field.getText(), "YYYY");


	        java.sql.Date dateHired = java.sql.Date.valueOf(dateText + "-01-01");


	        
	        
	        
	        String adminPos  = admin  ? normalizeText(empModal.row5Field.getText(), "") : null;
	        String assign    = staff  ? normalizeText(empModal.row4Field.getText(), "") : null;
	        String staffPos  = staff  ? normalizeText(empModal.row5Field.getText(), "") : null;
	        String facRank   = faculty? normalizeText(empModal.row6Field.getText(), "") : null;
	        String colCode   = faculty? (String) empModal.row8Field.getSelectedItem() : null;

	        
	        if (admin && adminPos == null) {
	            throw new IllegalArgumentException("Administrator position is required.");
	        }

	        
	        if (staff && (assign == null || staffPos == null)) {
	            throw new IllegalArgumentException("Assignment Code and Staff Position are required.");
	        }

	        
	        if (faculty && (facRank == null || colCode == null || colCode.isBlank())) {
	            throw new IllegalArgumentException("Faculty Rank and College are required.");
	        }

	        
	        
	        
	        return daoPatron.insertEmployeeRecord(
	                patronID, firstName, middle, lastName,
	                email, contact, address, camp,

	                admin, staff, faculty, dateHired,

	                adminPos, assign, staffPos,
	                facRank, colCode
	        );

	    } catch (IllegalArgumentException ex) {
	        JOptionPane.showMessageDialog(
	            null,
	            ex.getMessage(),
	            "Validation Error",
	            JOptionPane.ERROR_MESSAGE
	        );
	        return false;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}
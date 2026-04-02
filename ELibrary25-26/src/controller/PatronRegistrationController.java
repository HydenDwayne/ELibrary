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

	    // ✅ normalize ONCE
	    this.campusCode = toDbCampusCode(campusCode);

	    hookCollegeSelection();
	    hookStudentTypeSelection();
	    loadCollegesAndPrograms();
	}


	// ✅ Employee constructor
	public PatronRegistrationController(EmployeeModal empModal, String campusCode) {
		this.studModal = null;
		this.empModal = empModal;
		this.campusCode = campusCode;
		this.genModal = null;

		loadEmployeeColleges();
	}

	private void loadEmployeeColleges() {

		// Only Main (Malolos) and Bustos have colleges
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

		// First detect if user selected GRADUATE
		int typeIndex = studModal.row3Field.getSelectedIndex();

		if (typeIndex == 1) { // GRADUATE
//            removeExistingListeners();

			String[] gradColleges = { "CLAW", "GS" };
			studModal.setCollege(gradColleges);

			// Force CLAW to be selected
			studModal.row4Field.setSelectedIndex(0);

			// 🔥 Load programs AFTER UI becomes visible
			SwingUtilities.invokeLater(() -> {
				loadGraduateProgramsInitial();
			});

			return;
		}

		// NORMAL BEHAVIOR (Undergrad, LabHigh, Alumni)
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

	// ⭐ NEW — Recalculate when switching student type
	private void hookStudentTypeSelection() {
		studModal.row3Field.addActionListener(e -> {
			loadCollegesAndPrograms(); // Refresh colleges based on student type
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
			// ─────────────────────────────────────────────
			// PATRON DATA (FROM GENERAL MODAL)
			// ─────────────────────────────────────────────
			String patronID = normalizeText(genModal.getPatronID(), "Enter Patron ID");
			String firstName = normalizeText(genModal.getFirstName(), "Enter First Name");
			String lastName = normalizeText(genModal.getLastName(), "Enter Last Name");
			String middleInitial = normalizeText(genModal.getMiddleInitial(), "Enter Middle Initial");
			String email = normalizeText(genModal.getEmail(), "Enter Email Address");
			String contact = normalizeText(genModal.getContact(), "Enter Contact Number");
			String address = normalizeText(genModal.getAddress(), "Enter Home Address");
			

			// ─────────────────────────────────────────────
			// STUDENT DATA (FROM STUDENT MODAL)
			// ─────────────────────────────────────────────
			String yearText =
			        normalizeText(studModal.row2Field.getText(), "e.g. 2026");

			java.sql.Date yearEnrolled =
			        java.sql.Date.valueOf(yearText + "-01-01");

			String studentType = (String) studModal.row3Field.getSelectedItem();

			// ─────────────────────────────────────────────
			// OPTIONAL FIELDS (NULL BY DEFAULT)
			// ─────────────────────────────────────────────
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

			// ─────────────────────────────────────────────
			// STUDENT TYPE-SPECIFIC DATA
			// ─────────────────────────────────────────────
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

			// ─────────────────────────────────────────────
			// DAO CALL
			// ─────────────────────────────────────────────
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
}
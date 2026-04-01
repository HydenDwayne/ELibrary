package controller;

import javax.swing.*;
import java.awt.event.ActionListener;

import model.DAOs.Patron.PatronDAOImp;
import view.front_pages.patron_registration.StudentModal;

public class PatronRegistrationController {

    private final StudentModal studModal;
    private final PatronDAOImp daoPatron = new PatronDAOImp();
    private final String campusCode;

    public PatronRegistrationController(StudentModal studModal, String campusCode) {
        this.studModal = studModal;
        this.campusCode = campusCode;

        hookStudentTypeSelection();   // ⭐ ADD THIS
        loadCollegesAndPrograms();
    }

    private void removeExistingCollegeListeners() {
        for (ActionListener al : studModal.row4Field.getActionListeners()) {
            studModal.row4Field.removeActionListener(al);
        }
    }

    private void loadCollegesAndPrograms() {
        removeExistingCollegeListeners();

        // First detect if user selected GRADUATE
        int typeIndex = studModal.row3Field.getSelectedIndex();

        if (typeIndex == 1) {  // GRADUATE
//            removeExistingListeners();

            String[] gradColleges = { "CLAW", "GS" };
            studModal.setCollege(gradColleges);

            // Force CLAW to be selected
            studModal.row4Field.setSelectedIndex(0);

            // 🔥 Load programs AFTER UI becomes visible
            SwingUtilities.invokeLater(() -> {
                loadGraduateProgramsInitial();
            });

            // Listener for switching between CLAW <-> GS
            studModal.row4Field.addActionListener(e -> {
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

    // ⭐ NEW — Only CLAW + GS for graduates
    private void loadGraduateColleges() {
        removeExistingCollegeListeners();

        String[] gradColleges = { "CLAW", "GS" };
        studModal.setCollege(gradColleges);

        // Clear program combobox until college is chosen
        studModal.setPrograms(new String[]{""});

        // Programs load based on CLAW/GS selection
        studModal.row4Field.addActionListener(e -> {
            String college = (String) studModal.row4Field.getSelectedItem();
            if (college != null && !college.isEmpty()) {
                String[] programs = daoPatron.getPrograms(college, campusCode);
                studModal.setPrograms(programs);
            }
        });
    }

    private void hookCollegeSelection() {
        studModal.row4Field.addActionListener(e -> {
            String selectedCollege = (String) studModal.row4Field.getSelectedItem();
            if (selectedCollege != null && !selectedCollege.trim().isEmpty()) {
                String[] programs = daoPatron.getPrograms(selectedCollege, campusCode);
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
            loadCollegesAndPrograms();  // Refresh colleges based on student type
        });
    }
    
    private void loadGraduateProgramsInitial() {
        String college = (String) studModal.row4Field.getSelectedItem();

        if (college != null && !college.isEmpty()) {
            String[] programs = daoPatron.getProgramsByCollegeOnly(college);
            studModal.setPrograms(programs);
        }
    }
}
package view.modal.patron_modal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.PatronController;

import java.awt.*;

import view.RoundedComponents.*;
import view.front_pages.FilePath;
import view.fonts.Fonts;

public class ViewStudent extends JPanel {

    static final Color MAROON = new Color(132, 43, 40);
    static final Color LIGHT_PINK = new Color(250, 236, 238);
    static final Color WHITE = Color.WHITE;
    static final Color DARK_TEXT = new Color(109, 35, 33);
    static final Color FIELD_BORDER = new Color(146, 76, 74);

    static final int PANEL_RADIUS = 20;
    static final int FIELD_RADIUS = 15;

    // ==================== PUBLIC LABELS ====================
    public JLabel studTypeLbl = new JLabel("Student Type:");
    public JLabel levelLbl = new JLabel("Grade / Year Level:");
    public JLabel collegeLbl = new JLabel("College:");
    public JLabel programLbl = new JLabel("Program:");
    public JLabel thesisLbl = new JLabel("Thesis Title:");
    public JLabel degreeLbl = new JLabel("Degree:");
    public JLabel gradYearLbl = new JLabel("Year Graduated:");
    public JLabel campusLbl = new JLabel("Campus:");

    // ==================== PUBLIC FIELDS ====================
    public RoundedTextField thesisField = new RoundedTextField(19, FIELD_RADIUS);
    public RoundedTextField gradYearField = new RoundedTextField(19, FIELD_RADIUS);

    public RoundedComboBox<String> studentTypeField = new RoundedComboBox<>(
            new String[]{"UNDERGRADUATE", "GRADUATE", "LABHIGH", "ALUMNI"}, FIELD_RADIUS
    );

    public RoundedComboBox<String> levelField;
    public RoundedComboBox<String> collegeField;
    public RoundedComboBox<String> programField;
    public RoundedComboBox<String> degreeField = new RoundedComboBox<>(new String[]{"Master", "Doctorate"}, FIELD_RADIUS);
    public RoundedComboBox<String> campusField = new RoundedComboBox<>(
            new String[]{"Main", "Bustos", "Hagonoy", "Meneses", "Sarmiento", "San Rafael"}, FIELD_RADIUS
    );

    // Left panel fields
    public RoundedTextField pidField = new RoundedTextField(19, FIELD_RADIUS);
    public RoundedTextField firstNameField = new RoundedTextField(19, FIELD_RADIUS);
    public RoundedTextField middleField = new RoundedTextField(5, FIELD_RADIUS);
    public RoundedTextField lastNameField = new RoundedTextField(19, FIELD_RADIUS);
    public RoundedTextField emailField = new RoundedTextField(19, FIELD_RADIUS);
    public RoundedTextField contactField = new RoundedTextField(19, FIELD_RADIUS);
    public RoundedTextField addressField = new RoundedTextField(19, FIELD_RADIUS);

    // ==================== OTHER CLASS VARIABLES ====================
    String[] colleges = {""};
    String[] programs = {""};
    String[] yearLevel = {""};
    String patronID;

    PatronController comp;
    String selectedCollege = "";

    public ViewStudent(String patronID) {
        this.patronID = patronID;

        setLayout(new BorderLayout());
        setOpaque(false);

        /* ================= FONTS ================= */
        Font introRust36 = new Fonts("IntroRust", 36f).getAppliedFont();
        Font introRust24 = new Fonts("IntroRust", 24f).getAppliedFont();
        Font poppins16 = new Fonts("Poppins", 16f).getAppliedFont();
        Font poppins10 = new Fonts("Poppins", 10f).getAppliedFont();

        // ================= COMBOBOX INIT =================
        levelField = new RoundedComboBox<>(yearLevel, FIELD_RADIUS);
        collegeField = new RoundedComboBox<>(colleges, FIELD_RADIUS);
        programField = new RoundedComboBox<>(programs, FIELD_RADIUS);

        Dimension fieldDim = new Dimension(thesisField.getPreferredSize());
        RoundedTextField[] textFields = {pidField, firstNameField, middleField, lastNameField, emailField, contactField, addressField,
                thesisField, gradYearField};
        for (RoundedTextField f : textFields) {
            f.setFont(poppins10);
            f.setBorderColor(FIELD_BORDER);
            f.setPreferredSize(fieldDim);
        }

        RoundedComboBox<?>[] comboFields = {levelField, collegeField, programField, degreeField, campusField, studentTypeField};
        for (RoundedComboBox<?> c : comboFields) {
            c.setFont(poppins10);
            c.setBorderColor(FIELD_BORDER);
            c.setPreferredSize(new Dimension(210, 30));
        }

        pidField.setEnabled(false);

        // ================= MODAL =================
        RoundedPanel modal = new RoundedPanel(PANEL_RADIUS);
        modal.setLayout(new BorderLayout());
        modal.setPreferredSize(new Dimension(900, 560));
        modal.setBackground(LIGHT_PINK);

        // ================= HEADER =================
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(MAROON);
        header.setPreferredSize(new Dimension(900, 100));
        header.setBorder(new EmptyBorder(10, 0, 10, 10));

        JLabel logo = new JLabel(new ImageIcon(
                new ImageIcon(FilePath.image("elib_logo.png"))
                        .getImage().getScaledInstance(110, 50, Image.SCALE_SMOOTH)
        ));

        JLabel headerLabel = new JLabel("PATRONS");
        headerLabel.setFont(introRust36);
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBorder(new EmptyBorder(0, 260, 0, 0));

        header.add(logo, BorderLayout.WEST);
        header.add(headerLabel, BorderLayout.CENTER);

        // ================= BODY =================
        JPanel body = new JPanel(new BorderLayout());
        body.setOpaque(false);
        body.setBorder(new EmptyBorder(10, 15, 10, 15));

        JLabel title = new JLabel("DETAILED VIEW FOR STUDENT", SwingConstants.CENTER);
        title.setFont(introRust24);
        title.setForeground(DARK_TEXT);
        body.add(title, BorderLayout.NORTH);

        JPanel content = new JPanel(new GridLayout(1, 2, 20, 0));
        content.setOpaque(false);
        content.setBorder(new EmptyBorder(15, 5, 5, 5));

        // ================= LEFT PANEL =================
        RoundedPanel left = new RoundedPanel(12);
        left.setLayout(new GridBagLayout());
        left.setBackground(WHITE);
        left.setBorder(new EmptyBorder(15, 15, 15, 15));

        GridBagConstraints l = new GridBagConstraints();
        l.gridy = 0;
        l.insets = new Insets(6, 6, 6, 6);
        l.fill = GridBagConstraints.HORIZONTAL;
        l.weightx = 0.5;

        // Left fields
        String[] leftLabels = {"Patron ID:", "First Name:", "Middle Initial:", "Last Name:", "Email Address:", "Contact Number:", "Home Address:"};
        RoundedTextField[] leftFields = {pidField, firstNameField, middleField, lastNameField, emailField, contactField, addressField};
        for (int i = 0; i < leftLabels.length; i++) {
            l.gridx = 0;
            JLabel lbl = new JLabel(leftLabels[i]);
            lbl.setFont(poppins16);
            lbl.setForeground(DARK_TEXT);
            left.add(lbl, l);
            l.gridx = 1;
            left.add(leftFields[i], l);
            l.gridy++;
        }

//        // ================= RIGHT PANEL (REVISED BOXLAYOUT) =================
//        RoundedPanel right = new RoundedPanel(12);
//        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
//        right.setBackground(WHITE);
//        right.setBorder(new EmptyBorder(15, 15, 15, 15));

     // ================= RIGHT PANEL (GridBagLayout – MANUAL ROWS) =================
        RoundedPanel right = new RoundedPanel(12);
        right.setLayout(new GridBagLayout());
        right.setBackground(WHITE);
        right.setBorder(new EmptyBorder(15, 15, 15, 15));

        GridBagConstraints r = new GridBagConstraints();
        r.insets = new Insets(6, 6, 6, 6);
        r.fill = GridBagConstraints.HORIZONTAL;
        r.weightx = 0.5;
        r.gridy = 0;

        // ===== ROW 1: STUDENT TYPE =====
        studTypeLbl.setFont(poppins16);
        studTypeLbl.setForeground(DARK_TEXT);

        r.gridx = 0;
        right.add(studTypeLbl, r);

        r.gridx = 1;
        right.add(studentTypeField, r);

        // ===== ROW 2: CAMPUS =====
        r.gridy++;
        campusLbl.setFont(poppins16);
        campusLbl.setForeground(DARK_TEXT);

        r.gridx = 0;
        right.add(campusLbl, r);

        r.gridx = 1;
        right.add(campusField, r);

        // ===== ROW 3: LEVEL =====
        r.gridy++;
        levelLbl.setFont(poppins16);
        levelLbl.setForeground(DARK_TEXT);

        r.gridx = 0;
        right.add(levelLbl, r);

        r.gridx = 1;
        right.add(levelField, r);

        // ===== ROW 4: COLLEGE =====
        r.gridy++;
        collegeLbl.setFont(poppins16);
        collegeLbl.setForeground(DARK_TEXT);

        r.gridx = 0;
        right.add(collegeLbl, r);

        r.gridx = 1;
        right.add(collegeField, r);

        // ===== ROW 5: PROGRAM =====
        r.gridy++;
        programLbl.setFont(poppins16);
        programLbl.setForeground(DARK_TEXT);

        r.gridx = 0;
        right.add(programLbl, r);

        r.gridx = 1;
        right.add(programField, r);

        // ===== ROW 6: THESIS =====
        r.gridy++;
        thesisLbl.setFont(poppins16);
        thesisLbl.setForeground(DARK_TEXT);

        r.gridx = 0;
        right.add(thesisLbl, r);

        r.gridx = 1;
        right.add(thesisField, r);

        // ===== ROW 7: DEGREE =====
        r.gridy++;
        degreeLbl.setFont(poppins16);
        degreeLbl.setForeground(DARK_TEXT);

        r.gridx = 0;
        right.add(degreeLbl, r);

        r.gridx = 1;
        right.add(degreeField, r);

        // ===== ROW 8: YEAR GRADUATED =====
        r.gridy++;
        gradYearLbl.setFont(poppins16);
        gradYearLbl.setForeground(DARK_TEXT);

        r.gridx = 0;
        right.add(gradYearLbl, r);

        r.gridx = 1;
        right.add(gradYearField, r);

        // Initial visibility
        levelLbl.setVisible(false);
        levelField.setVisible(false);
        collegeLbl.setVisible(false);
        collegeField.setVisible(false);
        programLbl.setVisible(false);
        programField.setVisible(false);
        thesisLbl.setVisible(false);
        thesisField.setVisible(false);
        degreeLbl.setVisible(false);
        degreeField.setVisible(false);
        gradYearLbl.setVisible(false);
        gradYearField.setVisible(false);

        // Initial visibility
        levelLbl.setVisible(false);
        levelField.setVisible(false);
        collegeLbl.setVisible(false);
        collegeField.setVisible(false);
        programLbl.setVisible(false);
        programField.setVisible(false);
        thesisLbl.setVisible(false);
        thesisField.setVisible(false);
        degreeLbl.setVisible(false);
        degreeField.setVisible(false);
        gradYearLbl.setVisible(false);
        gradYearField.setVisible(false);

        // Initial visibility
        levelLbl.setVisible(false);
        levelField.setVisible(false);
        collegeLbl.setVisible(false);
        collegeField.setVisible(false);
        programLbl.setVisible(false);
        programField.setVisible(false);
        thesisLbl.setVisible(false);
        thesisField.setVisible(false);
        degreeLbl.setVisible(false);
        degreeField.setVisible(false);
        gradYearLbl.setVisible(false);
        gradYearField.setVisible(false);

        content.add(left);
        content.add(right);
        body.add(content, BorderLayout.CENTER);

        /* ================= FOOTER ================= */
        JPanel footer = new JPanel(new GridLayout(1, 2, 10, 0));
        footer.setPreferredSize(new Dimension(900, 60));
        footer.setBorder(new EmptyBorder(10, 20, 10, 20));
        footer.setOpaque(false);

        RoundedButton backBtn = new RoundedButton("< BACK", FIELD_RADIUS);
        backBtn.setFont(poppins10);
        backBtn.setForeground(MAROON);
        backBtn.setBorderColor(MAROON);
        backBtn.setBorderThickness(1);
        backBtn.addActionListener(e -> {
            Window w = SwingUtilities.getWindowAncestor(this);
            if (w instanceof JDialog)
                w.dispose();
        });

        RoundedButton submitBtn = new RoundedButton("SAVE DETAILS", FIELD_RADIUS);
        submitBtn.setFont(poppins10);
        submitBtn.setBackground(MAROON);
        submitBtn.setForeground(WHITE);

        footer.add(backBtn);
        footer.add(submitBtn);

        modal.add(header, BorderLayout.NORTH);
        modal.add(body, BorderLayout.CENTER);
        modal.add(footer, BorderLayout.SOUTH);

        this.comp = new PatronController(this, patronID);

        // ================= EVENT HANDLERS =================
        studentTypeField.addActionListener(e -> setStudentType(studentTypeField.getSelectedIndex()));
        setStudentType(studentTypeField.getSelectedIndex());

        if (collegeField.getSelectedIndex() == -1) {
            collegeField.setSelectedIndex(0);
        }

        campusField.addActionListener(e -> setCampusFields(campusField.getSelectedItem().toString()));
        setCampusFields(campusField.getSelectedItem().toString());

        if (collegeField.getSelectedIndex() == -1) {
            collegeField.setSelectedIndex(0);
        }

        collegeField.addActionListener(e -> setProgramFields(collegeField.getSelectedItem().toString(), campusField.getSelectedItem().toString()));
        setProgramFields(collegeField.getSelectedItem().toString(), campusField.getSelectedItem().toString());

        add(modal, BorderLayout.CENTER);
    }

    public void setProgramFields(String prog, String camp) {
        if (camp.equals("Main")) {
            comp.reloadProgramsPerCollege(this, prog, "M");
        }
        if (camp.equals("Bustos")) {
            comp.reloadProgramsPerCollege(this, prog, "BC");
        }
    }

    public void setCampusFields(String campus) {
        switch (campus) {
            case "Main", "Bustos" -> selectedCollege = collegeField.getSelectedItem().toString();
            case "Hagonoy", "Meneses", "Sarmiento", "San Rafael" -> {}
        }
        comp.setCollegesAndPrograms(this, campus, selectedCollege);
        revalidate();
        repaint();
    }

    public void setStudentType(int type) {
        // Hide all
        Component[] rightComponents = {levelLbl, levelField, collegeLbl, collegeField,
                programLbl, programField, thesisLbl, thesisField,
                degreeLbl, degreeField, gradYearLbl, gradYearField};
        for (Component c : rightComponents) c.setVisible(false);
        campusLbl.setVisible(true);
        campusField.setVisible(true);

        switch (type) {
            case 0 -> { // UNDERGRADUATE
                setYearLevels(new String[]{"1st", "2nd", "3rd", "4th", "5th"});
                levelLbl.setVisible(true);
                levelField.setVisible(true);
                collegeLbl.setVisible(true);
                collegeField.setVisible(true);
                programLbl.setVisible(true);
                programField.setVisible(true);
                comp.isGraduateSelected(this, false, campusField.getSelectedItem().toString());
            }
            case 1 -> { // GRADUATE
                collegeLbl.setVisible(true);
                collegeField.setVisible(true);
                programLbl.setVisible(true);
                programField.setVisible(true);
                thesisLbl.setVisible(true);
                thesisField.setVisible(true);
                degreeLbl.setVisible(true);
                degreeField.setVisible(true);
                gradYearLbl.setVisible(true);
                gradYearField.setVisible(true);
                campusLbl.setVisible(false);
                campusField.setVisible(false);
                comp.isGraduateSelected(this, true, campusField.getSelectedItem().toString());
            }
            case 2 -> { // LABHIGH
                setYearLevels(new String[]{"7", "8", "9", "10"});
                levelLbl.setVisible(true);
                levelField.setVisible(true);
                campusLbl.setVisible(false);
                campusField.setVisible(false);
            }
            case 3 -> { // ALUMNI
                gradYearLbl.setVisible(true);
                gradYearField.setVisible(true);
            }
        }
        revalidate();
        repaint();
    }

    public void setColleges(String[] colleges) {
        if (colleges != null) {
            this.collegeField.setModel(new DefaultComboBoxModel<>(colleges));
            if (colleges.length > 0) this.collegeField.setSelectedIndex(0);
        }
    }

    public void setPrograms(String[] programs) {
        if (programs != null) {
            this.programField.setModel(new DefaultComboBoxModel<>(programs));
            if (programs.length > 0) this.programField.setSelectedIndex(0);
        }
    }

    public void setYearLevels(String[] yearLevel) {
        if (yearLevel != null) {
            this.levelField.setModel(new DefaultComboBoxModel<>(yearLevel));
            if (yearLevel.length > 0) this.levelField.setSelectedIndex(0);
        }
    }

    public String[] getColleges() {
        return colleges;
    }

    public String[] getPrograms() {
        return programs;
    }
}
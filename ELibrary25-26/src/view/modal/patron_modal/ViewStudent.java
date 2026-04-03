package view.modal.patron_modal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
    static final String imgFilePath = FilePath.getImgFilePath();

    RoundedComboBox<String> studentTypeField;
    
 // Toggleable labels
    JLabel levelLbl, collegeLbl, programLbl, thesisLbl, degreeLbl, gradYearLbl;

    // Toggleable fields
    RoundedTextField levelField, collegeField, programField,
                     thesisField, degreeField, gradYearField;

    public ViewStudent() {

        setLayout(new BorderLayout());
        setOpaque(false);

        /* ================= FONTS ================= */

        Font introRust36 = new Fonts("IntroRust", 36f).getAppliedFont();
        Font introRust24 = new Fonts("IntroRust", 24f).getAppliedFont();
        Font poppins16   = new Fonts("Poppins", 16f).getAppliedFont();
        Font poppins10   = new Fonts("Poppins", 10f).getAppliedFont();

        /* ================= MODAL ================= */

        RoundedPanel modal = new RoundedPanel(PANEL_RADIUS);
        modal.setLayout(new BorderLayout());
        modal.setPreferredSize(new Dimension(900, 560));
        modal.setBackground(LIGHT_PINK);

        /* ================= HEADER ================= */

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(MAROON);
        header.setPreferredSize(new Dimension(900, 100));
        header.setBorder(new EmptyBorder(10, 0, 10, 10));

        JLabel logo = new JLabel(new ImageIcon(
                new ImageIcon(imgFilePath + "elib_logo.png")
                        .getImage().getScaledInstance(110, 50, Image.SCALE_SMOOTH)
        ));

        JLabel headerLabel = new JLabel("PATRONS");
        headerLabel.setFont(introRust36);
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBorder(new EmptyBorder(0, 260, 0, 0));

        header.add(logo, BorderLayout.WEST);
        header.add(headerLabel, BorderLayout.CENTER);

        /* ================= BODY ================= */

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

        /* ================= LEFT PANEL ================= */

        RoundedPanel left = new RoundedPanel(12);
        left.setLayout(new GridBagLayout());
        left.setBackground(WHITE);
        left.setBorder(new EmptyBorder(15, 15, 15, 15));

        GridBagConstraints l = new GridBagConstraints();
        l.gridy = 0;
        l.insets = new Insets(6, 6, 6, 6);
        l.fill = GridBagConstraints.HORIZONTAL;
        l.weightx = 0.5;

        // Patron ID
        l.gridx = 0;
        JLabel pidLbl = new JLabel("Patron ID:");
        pidLbl.setFont(poppins16);
        pidLbl.setForeground(DARK_TEXT);
        left.add(pidLbl, l);

        l.gridx = 1;
        RoundedTextField pidField = new RoundedTextField(19, FIELD_RADIUS);
        pidField.setFont(poppins10);
        pidField.setBorderColor(FIELD_BORDER);
        pidField.setEditable(false);
        left.add(pidField, l);

        // Full Name
        l.gridy++;
        l.gridx = 0;
        JLabel nameLbl = new JLabel("Full Name:");
        nameLbl.setFont(poppins16);
        nameLbl.setForeground(DARK_TEXT);
        left.add(nameLbl, l);

        l.gridx = 1;
        RoundedTextField nameField = new RoundedTextField(19, FIELD_RADIUS);
        nameField.setFont(poppins10);
        nameField.setBorderColor(FIELD_BORDER);
        left.add(nameField, l);

        // Email
        l.gridy++;
        l.gridx = 0;
        JLabel emailLbl = new JLabel("Email Address:");
        emailLbl.setFont(poppins16);
        emailLbl.setForeground(DARK_TEXT);
        left.add(emailLbl, l);

        l.gridx = 1;
        RoundedTextField emailField = new RoundedTextField(19, FIELD_RADIUS);
        emailField.setFont(poppins10);
        emailField.setBorderColor(FIELD_BORDER);
        left.add(emailField, l);

        // Contact
        l.gridy++;
        l.gridx = 0;
        JLabel contactLbl = new JLabel("Contact Number:");
        contactLbl.setFont(poppins16);
        contactLbl.setForeground(DARK_TEXT);
        left.add(contactLbl, l);

        l.gridx = 1;
        RoundedTextField contactField = new RoundedTextField(19, FIELD_RADIUS);
        contactField.setFont(poppins10);
        contactField.setBorderColor(FIELD_BORDER);
        left.add(contactField, l);

        // Campus
        l.gridy++;
        l.gridx = 0;
        JLabel campusLbl = new JLabel("Campus:");
        campusLbl.setFont(poppins16);
        campusLbl.setForeground(DARK_TEXT);
        left.add(campusLbl, l);

        l.gridx = 1;
        RoundedTextField campusField = new RoundedTextField(19, FIELD_RADIUS);
        campusField.setFont(poppins10);
        campusField.setBorderColor(FIELD_BORDER);
        left.add(campusField, l);

        /* ================= RIGHT PANEL ================= */

        RoundedPanel right = new RoundedPanel(12);
        right.setLayout(new GridBagLayout());
        right.setBackground(WHITE);
        right.setBorder(new EmptyBorder(15, 15, 15, 15));

        GridBagConstraints r = new GridBagConstraints();
        r.gridy = 0;
        r.insets = new Insets(6, 6, 6, 6);
        r.fill = GridBagConstraints.HORIZONTAL;
        r.weightx = 0.5;

        /* ==== Student Type ==== */

        r.gridx = 0;
        JLabel typeLbl = new JLabel("Student Type:");
        typeLbl.setFont(poppins16);
        typeLbl.setForeground(DARK_TEXT);
        right.add(typeLbl, r);

        r.gridx = 1;
        studentTypeField = new RoundedComboBox<>(
                new String[]{"UNDERGRADUATE", "GRADUATE", "LABHIGH", "ALUMNI"},
                FIELD_RADIUS
        );
        studentTypeField.setFont(poppins10);
        studentTypeField.setBorderColor(FIELD_BORDER);
        studentTypeField.addActionListener(e ->
                setStudentType(studentTypeField.getSelectedIndex())
        );
        right.add(studentTypeField, r);

        /* ==== Grade / Year Level ==== */

        r.gridy++;
        r.gridx = 0;
         levelLbl = new JLabel("Grade / Year Level:");
        levelLbl.setFont(poppins16);
        levelLbl.setForeground(DARK_TEXT);
        right.add(levelLbl, r);

        r.gridx = 1;
         levelField = new RoundedTextField(19, FIELD_RADIUS);
        levelField.setFont(poppins10);
        levelField.setBorderColor(FIELD_BORDER);
        right.add(levelField, r);

//        /* store toggle refs */
//        levelRow = levelLbl;   // label
//        /* or store field if you prefer */
//        JComponent levelRowField = levelField;

        /* ==== College ==== */

        r.gridy++;
        r.gridx = 0;
         collegeLbl = new JLabel("College:");
        collegeLbl.setFont(poppins16);
        collegeLbl.setForeground(DARK_TEXT);
        right.add(collegeLbl, r);

        r.gridx = 1;
         collegeField = new RoundedTextField(19, FIELD_RADIUS);
        collegeField.setFont(poppins10);
        collegeField.setBorderColor(FIELD_BORDER);
        right.add(collegeField, r);

        /* ==== Program ==== */

        r.gridy++;
        r.gridx = 0;
         programLbl = new JLabel("Program:");
        programLbl.setFont(poppins16);
        programLbl.setForeground(DARK_TEXT);
        right.add(programLbl, r);

        r.gridx = 1;
         programField = new RoundedTextField(19, FIELD_RADIUS);
        programField.setFont(poppins10);
        programField.setBorderColor(FIELD_BORDER);
        right.add(programField, r);

        /* ==== Thesis ==== */

        r.gridy++;
        r.gridx = 0;
         thesisLbl = new JLabel("Thesis Title:");
        thesisLbl.setFont(poppins16);
        thesisLbl.setForeground(DARK_TEXT);
        right.add(thesisLbl, r);

        r.gridx = 1;
         thesisField = new RoundedTextField(19, FIELD_RADIUS);
        thesisField.setFont(poppins10);
        thesisField.setBorderColor(FIELD_BORDER);
        right.add(thesisField, r);

        /* ==== Degree ==== */

        r.gridy++;
        r.gridx = 0;
         degreeLbl = new JLabel("Degree:");
        degreeLbl.setFont(poppins16);
        degreeLbl.setForeground(DARK_TEXT);
        right.add(degreeLbl, r);

        r.gridx = 1;
         degreeField = new RoundedTextField(19, FIELD_RADIUS);
        degreeField.setFont(poppins10);
        degreeField.setBorderColor(FIELD_BORDER);
        right.add(degreeField, r);

        /* ==== Year Graduated ==== */

        r.gridy++;
        r.gridx = 0;
         gradYearLbl = new JLabel("Year Graduated:");
        gradYearLbl.setFont(poppins16);
        gradYearLbl.setForeground(DARK_TEXT);
        right.add(gradYearLbl, r);

        r.gridx = 1;
         gradYearField = new RoundedTextField(19, FIELD_RADIUS);
        gradYearField.setFont(poppins10);
        gradYearField.setBorderColor(FIELD_BORDER);
        right.add(gradYearField, r);

        /* ==== INITIAL STATE ==== */

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

        RoundedButton submitBtn = new RoundedButton("SUBMIT BORROW REQUEST", FIELD_RADIUS);
        submitBtn.setFont(poppins10);
        submitBtn.setBackground(MAROON);
        submitBtn.setForeground(WHITE);

        footer.add(backBtn);
        footer.add(submitBtn);

        modal.add(header, BorderLayout.NORTH);
        modal.add(body, BorderLayout.CENTER);
        modal.add(footer, BorderLayout.SOUTH);

        add(modal, BorderLayout.CENTER);
    }

    /* ================= STUDENT TYPE HANDLER ================= */

    public void setStudentType(int type) {

        // hide all
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

        switch (type) {
            case 0: // UNDERGRADUATE
                levelLbl.setVisible(true);
                levelField.setVisible(true);
                collegeLbl.setVisible(true);
                collegeField.setVisible(true);
                programLbl.setVisible(true);
                programField.setVisible(true);
                break;

            case 1: // GRADUATE
                collegeLbl.setVisible(true);
                collegeField.setVisible(true);
                programLbl.setVisible(true);
                programField.setVisible(true);
                thesisLbl.setVisible(true);
                thesisField.setVisible(true);
                degreeLbl.setVisible(true);
                degreeField.setVisible(true);
                break;

            case 2: // LABHIGH
                levelLbl.setVisible(true);
                levelField.setVisible(true);
                break;

            case 3: // ALUMNI
                gradYearLbl.setVisible(true);
                gradYearField.setVisible(true);
                break;
        }

        revalidate();
        repaint();
    }
}
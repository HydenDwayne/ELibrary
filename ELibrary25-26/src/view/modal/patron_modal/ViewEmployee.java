package view.modal.patron_modal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import view.RoundedComponents.*;
import view.front_pages.FilePath;
import view.fonts.Fonts;

public class ViewEmployee extends JPanel {

    static final Color MAROON = new Color(132, 43, 40);
    static final Color LIGHT_PINK = new Color(250, 236, 238);
    static final Color WHITE = Color.WHITE;
    static final Color DARK_TEXT = new Color(109, 35, 33);
    static final Color FIELD_BORDER = new Color(146, 76, 74);

    static final int PANEL_RADIUS = 20;
    static final int FIELD_RADIUS = 15;

    static final String imgFilePath = FilePath.getImgFilePath();

    JCheckBox adminCheck, libraryStaffCheck, facultyCheck;
    JPanel assignmentCodeRow, positionRow, facultyRankRow, collegeRow, collegeCampusRow;

    public ViewEmployee() {

        setOpaque(false);
        setLayout(new BorderLayout());

        /* ================= FONTS ================= */

        Font introRust36 = new Fonts("IntroRust", 36f).getAppliedFont();
        Font introRust24 = new Fonts("IntroRust", 24f).getAppliedFont();
        Font poppins16   = new Fonts("Poppins", 16f).getAppliedFont();
        Font poppins10   = new Fonts("Poppins", 10f).getAppliedFont();

        /* ================= MODAL ================= */

        RoundedPanel modal = new RoundedPanel(PANEL_RADIUS);
        modal.setLayout(new BorderLayout());
        modal.setPreferredSize(new Dimension(900, 550));
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

        JLabel bodyTitle = new JLabel("DETAILED VIEW FOR EMPLOYEE", SwingConstants.CENTER);
        bodyTitle.setFont(introRust24);
        bodyTitle.setForeground(DARK_TEXT);
        body.add(bodyTitle, BorderLayout.NORTH);

        JPanel content = new JPanel(new GridLayout(1, 2, 20, 0));
        content.setOpaque(false);
        content.setBorder(new EmptyBorder(15, 5, 5, 5));

        /* ================= LEFT PANEL ================= */

        RoundedPanel left = new RoundedPanel(12);
        left.setBackground(WHITE);
        left.setBorder(new EmptyBorder(15, 15, 15, 15));
        left.setLayout(new GridBagLayout());

        GridBagConstraints l = new GridBagConstraints();
        l.gridy = 0;
        l.insets = new Insets(6, 6, 6, 6);
        l.fill = GridBagConstraints.HORIZONTAL;
        l.weightx = 0.5;

        // Patron ID
        l.gridx = 0;
        JLabel patronIdLbl = new JLabel("Patron ID:");
        patronIdLbl.setFont(poppins16);
        patronIdLbl.setForeground(DARK_TEXT);
        left.add(patronIdLbl, l);

        l.gridx = 1;
        RoundedTextField patronIdField = new RoundedTextField(19, FIELD_RADIUS);
        patronIdField.setFont(poppins10);
        patronIdField.setBorderColor(FIELD_BORDER);
        patronIdField.setEnabled(false);
        left.add(patronIdField, l);

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

        // Email Address
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

        // Contact Number
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
        right.setBackground(WHITE);
        right.setBorder(new EmptyBorder(15, 15, 15, 15));
        right.setLayout(new GridBagLayout());

        GridBagConstraints r = new GridBagConstraints();
        r.gridy = 0;
        r.insets = new Insets(6, 6, 6, 6);
        r.fill = GridBagConstraints.HORIZONTAL;
        r.weightx = 0.5;

        // Patron Type
        r.gridx = 0;
        JLabel ptLbl = new JLabel("Patron Type:");
        ptLbl.setFont(poppins16);
        ptLbl.setForeground(DARK_TEXT);
        right.add(ptLbl, r);

        r.gridx = 1;
        RoundedTextField ptField = new RoundedTextField(19, FIELD_RADIUS);
        ptField.setFont(poppins10);
        ptField.setBorderColor(FIELD_BORDER);
        ptField.setEnabled(false);
        right.add(ptField, r);

        // Employee Type/s
        r.gridy++;
        r.gridx = 0;
        JLabel empLbl = new JLabel("Employee Type/s:");
        empLbl.setFont(poppins16);
        empLbl.setForeground(DARK_TEXT);
        right.add(empLbl, r);

        r.gridx = 1;
        JPanel checksPanel = new JPanel();
        checksPanel.setOpaque(false);
        checksPanel.setLayout(new BoxLayout(checksPanel, BoxLayout.Y_AXIS));

        adminCheck = new JCheckBox("Administrator");
        libraryStaffCheck = new JCheckBox("Library Staff");
        facultyCheck = new JCheckBox("Faculty");

        for (JCheckBox cb : new JCheckBox[]{adminCheck, libraryStaffCheck, facultyCheck}) {
            cb.setFont(poppins10);
            cb.setForeground(DARK_TEXT);
            cb.setOpaque(false);
            checksPanel.add(cb);
        }

        right.add(checksPanel, r);

        /* ===== CONDITIONAL ROW PANELS ===== */

        // Assignment Code
        r.gridy++;
        r.gridx = 0;
        r.gridwidth = 2;
        assignmentCodeRow = new JPanel(new GridLayout(1, 2, 10, 0));
        assignmentCodeRow.setOpaque(false);

        JLabel acLbl = new JLabel("Assignment Code:");
        acLbl.setFont(poppins16);
        acLbl.setForeground(DARK_TEXT);

        RoundedTextField acField = new RoundedTextField(19, FIELD_RADIUS);
        acField.setFont(poppins10);
        acField.setBorderColor(FIELD_BORDER);

        assignmentCodeRow.add(acLbl);
        assignmentCodeRow.add(acField);
        right.add(assignmentCodeRow, r);

        // Position
        r.gridy++;
        positionRow = new JPanel(new GridLayout(1, 2, 10, 0));
        positionRow.setOpaque(false);

        JLabel posLbl = new JLabel("Position:");
        posLbl.setFont(poppins16);
        posLbl.setForeground(DARK_TEXT);

        RoundedTextField posField = new RoundedTextField(19, FIELD_RADIUS);
        posField.setFont(poppins10);
        posField.setBorderColor(FIELD_BORDER);

        positionRow.add(posLbl);
        positionRow.add(posField);
        right.add(positionRow, r);

        // Faculty Rank
        r.gridy++;
        facultyRankRow = new JPanel(new GridLayout(1, 2, 10, 0));
        facultyRankRow.setOpaque(false);

        JLabel frLbl = new JLabel("Faculty Rank:");
        frLbl.setFont(poppins16);
        frLbl.setForeground(DARK_TEXT);

        RoundedTextField frField = new RoundedTextField(19, FIELD_RADIUS);
        frField.setFont(poppins10);
        frField.setBorderColor(FIELD_BORDER);

        facultyRankRow.add(frLbl);
        facultyRankRow.add(frField);
        right.add(facultyRankRow, r);

        // College
        r.gridy++;
        collegeRow = new JPanel(new GridLayout(1, 2, 10, 0));
        collegeRow.setOpaque(false);

        JLabel collegeLbl = new JLabel("College:");
        collegeLbl.setFont(poppins16);
        collegeLbl.setForeground(DARK_TEXT);

        RoundedTextField collegeField = new RoundedTextField(19, FIELD_RADIUS);
        collegeField.setFont(poppins10);
        collegeField.setBorderColor(FIELD_BORDER);

        collegeRow.add(collegeLbl);
        collegeRow.add(collegeField);
        right.add(collegeRow, r);

        // College Campus
        r.gridy++;
        collegeCampusRow = new JPanel(new GridLayout(1, 2, 10, 0));
        collegeCampusRow.setOpaque(false);

        JLabel ccLbl = new JLabel("College Campus:");
        ccLbl.setFont(poppins16);
        ccLbl.setForeground(DARK_TEXT);

        RoundedTextField ccField = new RoundedTextField(19, FIELD_RADIUS);
        ccField.setFont(poppins10);
        ccField.setBorderColor(FIELD_BORDER);

        collegeCampusRow.add(ccLbl);
        collegeCampusRow.add(ccField);
        right.add(collegeCampusRow, r);

        // Initial hidden state
        assignmentCodeRow.setVisible(false);
        positionRow.setVisible(false);
        facultyRankRow.setVisible(false);
        collegeRow.setVisible(false);
        collegeCampusRow.setVisible(false);

        // Toggle wiring
        adminCheck.addActionListener(e -> toggleRows());
        libraryStaffCheck.addActionListener(e -> toggleRows());
        facultyCheck.addActionListener(e -> toggleRows());

        content.add(left);
        content.add(right);
        body.add(content, BorderLayout.CENTER);

        /* ================= FOOTER ================= */

        JPanel footer = new JPanel(new GridLayout(1, 2, 10, 0));
        footer.setPreferredSize(new Dimension(900, 50));   // ⬅️ taller footer
        footer.setBorder(new EmptyBorder(10, 20, 10, 20)); // ⬅️ vertical padding
        footer.setOpaque(false);

        RoundedButton cancelBtn = new RoundedButton("CANCEL", FIELD_RADIUS);
        cancelBtn.setFont(poppins10);
        cancelBtn.setForeground(MAROON);
        cancelBtn.setBorderColor(MAROON);
        cancelBtn.setBorderThickness(1);
        cancelBtn.setFocusPainted(false);

        RoundedButton saveBtn = new RoundedButton("SAVE DETAILS", FIELD_RADIUS);
        saveBtn.setFont(poppins10);
        saveBtn.setBackground(MAROON);
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFocusPainted(false);

        footer.add(cancelBtn);
        footer.add(saveBtn);

        /* ================= ASSEMBLY ================= */

        modal.add(header, BorderLayout.NORTH);
        modal.add(body, BorderLayout.CENTER);
        modal.add(footer, BorderLayout.SOUTH);
        add(modal, BorderLayout.CENTER);
    }

    /* ================= TOGGLE LOGIC ================= */

    private void toggleRows() {
        boolean a = adminCheck.isSelected();
        boolean l = libraryStaffCheck.isSelected();
        boolean f = facultyCheck.isSelected();

        assignmentCodeRow.setVisible(l);
        positionRow.setVisible(a || l);
        facultyRankRow.setVisible(f);
        collegeRow.setVisible(f);
        collegeCampusRow.setVisible(f);

        revalidate();
        repaint();
    }
}
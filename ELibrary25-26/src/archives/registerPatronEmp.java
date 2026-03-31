package elibrary;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import com.toedter.calendar.JDateChooser;

public class registerPatronEmp extends JFrame {

    JPanel registerPanel;

    static final Color MAROON       = new Color(106, 27, 27);
    static final Color LIGHT_PINK   = new Color(250, 235, 235);
    static final Color WHITE        = Color.WHITE;
    static final Color DARK_TEXT    = new Color(60, 20, 20);
    static final Color FIELD_BORDER = new Color(180, 130, 130);

    static final int FIELD_HEIGHT = 22;

    JPanel detailSection;
    JPanel bodyPanel;

    JCheckBox admin;
    JCheckBox libStaff;
    JCheckBox faculty;

    public registerPatronEmp() {

        setTitle("Add Patron");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        registerPanel = new JPanel();
        registerPanel.setBackground(LIGHT_PINK);
        registerPanel.setBorder(BorderFactory.createEtchedBorder());
        registerPanel.setLayout(new BorderLayout());

        // Logo banner
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(MAROON);
        logoPanel.setPreferredSize(new Dimension(400, 45));
        logoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 8));
        JLabel logoLabel = new JLabel("📚 Library");
        logoLabel.setForeground(WHITE);
        logoPanel.add(logoLabel);
        registerPanel.add(logoPanel, BorderLayout.NORTH);

        // Body
        bodyPanel = new JPanel();
        bodyPanel.setBackground(LIGHT_PINK);
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
        bodyPanel.setBorder(new EmptyBorder(14, 16, 14, 16));

        // Title + Step
        JPanel parentPanel = new JPanel(new GridLayout(2, 1, 5, 4));
        parentPanel.setBackground(LIGHT_PINK);
        parentPanel.setAlignmentX(LEFT_ALIGNMENT);

        JPanel titlePanelChild = new JPanel(new GridBagLayout());
        titlePanelChild.setBackground(LIGHT_PINK);
        JLabel title = new JLabel("ADD PATRON");
        title.setForeground(DARK_TEXT);
        titlePanelChild.add(title);
        parentPanel.add(titlePanelChild);

        JPanel stepPanelChild = new JPanel(new BorderLayout());
        stepPanelChild.setBackground(LIGHT_PINK);
        JLabel step = new JLabel("Step 2: Employee Information");
        step.setForeground(DARK_TEXT);
        stepPanelChild.add(step);
        parentPanel.add(stepPanelChild);

        bodyPanel.add(parentPanel);
        bodyPanel.add(Box.createVerticalStrut(6));
        bodyPanel.add(separator());
        bodyPanel.add(Box.createVerticalStrut(6));

        // Employee ID + Date Hired
        JPanel grid = new JPanel(new GridLayout(2, 2, 8, 4));
        grid.setBackground(LIGHT_PINK);
        grid.setAlignmentX(LEFT_ALIGNMENT);
        grid.add(darkLabel("Employee ID:"));
        grid.add(styledField());
        grid.add(twoPartLabel("Date Hired", " (2024-10-27)"));
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        grid.add(dateChooser);
        bodyPanel.add(grid);

        bodyPanel.add(Box.createVerticalStrut(6));
        bodyPanel.add(separator());
        bodyPanel.add(Box.createVerticalStrut(6));

        // Role label
        JPanel rolePanel = new JPanel(new BorderLayout());
        rolePanel.setBackground(LIGHT_PINK);
        rolePanel.setAlignmentX(LEFT_ALIGNMENT);
        rolePanel.add(darkLabel("Select Role(s):"), BorderLayout.WEST);
        bodyPanel.add(rolePanel);
        bodyPanel.add(Box.createVerticalStrut(4));

        // Checkboxes
        JPanel checkBoxPanel = new JPanel(new GridLayout(3, 1, 0, 2));
        checkBoxPanel.setBackground(LIGHT_PINK);
        checkBoxPanel.setAlignmentX(LEFT_ALIGNMENT);

        admin    = new JCheckBox("Administrator");
        libStaff = new JCheckBox("Library Staff");
        faculty  = new JCheckBox("Faculty");

        for (JCheckBox cb : new JCheckBox[]{admin, libStaff, faculty}) {
            cb.setBackground(LIGHT_PINK);
            cb.setForeground(DARK_TEXT);
            cb.addActionListener(e -> updateDetailSection());
            checkBoxPanel.add(cb);
        }

        bodyPanel.add(checkBoxPanel);
        bodyPanel.add(Box.createVerticalStrut(6));
        bodyPanel.add(separator());

        // Detail section — starts empty, filled dynamically
        detailSection = new JPanel();
        detailSection.setBackground(LIGHT_PINK);
        detailSection.setLayout(new BoxLayout(detailSection, BoxLayout.Y_AXIS));
        detailSection.setAlignmentX(LEFT_ALIGNMENT);
        bodyPanel.add(detailSection);

        // Buttons
        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        btnPanel.setBackground(LIGHT_PINK);
        btnPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        btnPanel.setAlignmentX(LEFT_ALIGNMENT);

        JButton cancelBtn = new JButton("CANCEL");
        cancelBtn.setForeground(DARK_TEXT);
        cancelBtn.setBackground(WHITE);
        cancelBtn.setBorder(BorderFactory.createLineBorder(FIELD_BORDER, 1));
        cancelBtn.setFocusPainted(false);

        JButton nextBtn = new JButton("NEXT");
        nextBtn.setForeground(WHITE);
        nextBtn.setBackground(MAROON);
        nextBtn.setBorder(BorderFactory.createEmptyBorder(6, 16, 6, 16));
        nextBtn.setOpaque(true);
        nextBtn.setFocusPainted(false);

        btnPanel.add(cancelBtn);
        btnPanel.add(nextBtn);
        bodyPanel.add(Box.createVerticalStrut(6));
        bodyPanel.add(btnPanel);

        registerPanel.add(bodyPanel, BorderLayout.CENTER);

        // Wrapper
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(new Color(210, 180, 180));
        wrapper.add(registerPanel);
        
        
        add(wrapper, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateDetailSection() {
        detailSection.removeAll();

        boolean a = admin.isSelected();
        boolean l = libStaff.isSelected();
        boolean f = faculty.isSelected();

        if (a || l || f) {
            detailSection.add(Box.createVerticalStrut(6));

            // Detail title
            StringBuilder titleStr = new StringBuilder();
            if (a) titleStr.append("Administrator");
            if (l) { if (titleStr.length() > 0) titleStr.append(" + "); titleStr.append("Library Staff"); }
            if (f) { if (titleStr.length() > 0) titleStr.append(" + "); titleStr.append("Faculty"); }
            titleStr.append(" Details");

            JPanel detailTitlePanel = new JPanel(new BorderLayout());
            detailTitlePanel.setBackground(LIGHT_PINK);
            detailTitlePanel.setAlignmentX(LEFT_ALIGNMENT);
            JLabel detailTitleLabel = new JLabel(titleStr.toString());
            detailTitleLabel.setForeground(MAROON);
            detailTitlePanel.add(detailTitleLabel, BorderLayout.WEST);
            detailSection.add(detailTitlePanel);
            detailSection.add(Box.createVerticalStrut(6));

            // Count rows
            int rows = 0;
            if (l)      rows += 1; // Assignment Code
            if (a || l) rows += 1; // Position
            if (f)      rows += 3; // Faculty Rank + Campus + College Code

            JPanel detailGrid = new JPanel(new GridLayout(rows, 2, 8, 4));
            detailGrid.setBackground(LIGHT_PINK);
            detailGrid.setAlignmentX(LEFT_ALIGNMENT);

            if (l) {
                detailGrid.add(darkLabel("Assignment Code:"));
                detailGrid.add(styledField());
            }

            if (a || l) {
                detailGrid.add(darkLabel("Position:"));
                detailGrid.add(styledField());
            }

            if (f) {
                detailGrid.add(darkLabel("Faculty Rank:"));
                detailGrid.add(styledField());

                detailGrid.add(darkLabel("Campus:"));
                JComboBox<String> campusCombo = new JComboBox<>(new String[]{
                    "Main", "Bustos", "Hagonoy", "Meneses", "San Rafael", "Sarmiento"
                });
                campusCombo.setBackground(WHITE);
                detailGrid.add(campusCombo);

                detailGrid.add(darkLabel("College Code:"));
                JComboBox<String> collegeCombo = new JComboBox<>(new String[]{
                    "CAFA","CAL","CBEA","CCJE","CHTM","CICT","CIT","CLaw",
                    "CN","COE","COED","CS","CSER","CSSP","GS","CICTE","CBA"
                });
                collegeCombo.setBackground(WHITE);
                detailGrid.add(collegeCombo);
            }

            detailSection.add(detailGrid);
            detailSection.add(Box.createVerticalStrut(6));
            detailSection.add(separator());
            detailSection.add(Box.createVerticalStrut(6));
        }

        detailSection.revalidate();
        detailSection.repaint();
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel twoPartLabel(String main, String hint) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        p.setBackground(LIGHT_PINK);
        JLabel lbl = new JLabel(main);
        lbl.setForeground(DARK_TEXT);
        JLabel h = new JLabel(hint);
        h.setForeground(Color.GRAY);
        p.add(lbl);
        p.add(h);
        return p;
    }

    private JLabel darkLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(DARK_TEXT);
        return lbl;
    }

    private JSeparator separator() {
        JSeparator sep = new JSeparator();
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        sep.setForeground(new Color(180, 140, 140));
        return sep;
    }

    private JTextField styledField() {
        JTextField tf = new JTextField();
        tf.setPreferredSize(new Dimension(0, FIELD_HEIGHT));
        tf.setBorder(BorderFactory.createLineBorder(FIELD_BORDER, 1));
        return tf;
    }

    public static void main(String[] args) {
        new registerPatronEmp();
    }
}
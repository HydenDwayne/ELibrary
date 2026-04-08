package archives;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;

public class RegisterPatronStu extends JFrame {

    JPanel registerPanel;

    static final Color MAROON       = new Color(106, 27, 27);
    static final Color LIGHT_PINK   = new Color(250, 235, 235);
    static final Color WHITE        = Color.WHITE;
    static final Color DARK_TEXT    = new Color(60, 20, 20);
    static final Color FIELD_BORDER = new Color(180, 130, 130);
    static final int   FIELD_HEIGHT = 22;

    JPanel ugGrid, lGrid, gGrid, aGrid;
    JPanel bodyPanel;

    public RegisterPatronStu() {
        setTitle("Add Patron");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        registerPanel = new JPanel();
        
        registerPanel.setBackground(LIGHT_PINK);
        registerPanel.setBorder(BorderFactory.createEtchedBorder());
        registerPanel.setLayout(new BorderLayout());

        
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(MAROON);
        logoPanel.setPreferredSize(new Dimension(400, 45));
        logoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 8));
        JLabel logoLabel = new JLabel("📚 Library");
        logoLabel.setForeground(WHITE);
        logoPanel.add(logoLabel);
        registerPanel.add(logoPanel, BorderLayout.NORTH);

        
        bodyPanel = new JPanel();
        bodyPanel.setBackground(LIGHT_PINK);
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
        bodyPanel.setBorder(new EmptyBorder(14, 16, 14, 16));

        
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
        JLabel step = new JLabel("Step 2: Student Information");
        step.setForeground(DARK_TEXT);
        stepPanelChild.add(step);
        parentPanel.add(stepPanelChild);

        bodyPanel.add(parentPanel);
        bodyPanel.add(Box.createVerticalStrut(6));
        bodyPanel.add(separator());
        bodyPanel.add(Box.createVerticalStrut(6));

        
        JPanel grid = new JPanel(new GridLayout(2, 2, 8, 4));
        grid.setBackground(LIGHT_PINK);
        grid.setAlignmentX(LEFT_ALIGNMENT);

        grid.add(darkLabel("Student ID:"));
        grid.add(styledField());
        grid.add(twoPartLabel("Year Enrolled", " (2024-10-27)"));



        bodyPanel.add(grid);
        bodyPanel.add(Box.createVerticalStrut(4));

        
        JPanel typeRow = new JPanel(new GridLayout(1, 2, 8, 0));
        typeRow.setBackground(LIGHT_PINK);
        typeRow.setAlignmentX(LEFT_ALIGNMENT);
        typeRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, FIELD_HEIGHT + 4));
        typeRow.add(darkLabel("Student Type:"));
        JComboBox<String> sTypeCombo = new JComboBox<>(
                new String[]{"UNDERGRADUATE", "GRADUATE", "LABHIGH", "ALUMNI"});
        sTypeCombo.setBackground(WHITE);
        typeRow.add(sTypeCombo);
        bodyPanel.add(typeRow);
        bodyPanel.add(Box.createVerticalStrut(6));

        
        ugGrid = new JPanel(new GridLayout(4, 2, 8, 4));
        ugGrid.setBackground(LIGHT_PINK);
        ugGrid.setAlignmentX(LEFT_ALIGNMENT);

        ugGrid.add(darkLabel("Campus:"));
        JComboBox<String> campusCombo = new JComboBox<>(new String[]{
            "Main", "Bustos", "Hagonoy", "Meneses", "San Rafael", "Sarmiento"
        });
        campusCombo.setBackground(WHITE);
        ugGrid.add(campusCombo);

        ugGrid.add(darkLabel("College Code:"));
        JComboBox<String> ugCollegeCombo = new JComboBox<>(new String[]{
            "CAFA","CAL","CBEA","CCJE","CHTM","CICT","CIT","CLaw",
            "CN","COE","COED","CS","CSER","CSSP","GS","CICTE","CBA"
        });
        ugCollegeCombo.setBackground(WHITE);
        ugGrid.add(ugCollegeCombo);

        ugGrid.add(darkLabel("Program Code:"));
        JComboBox<String> ugProgramCombo = new JComboBox<>(new String[]{
            "BSIT","BSIS","BLIS"
        });
        ugProgramCombo.setBackground(WHITE);
        ugGrid.add(ugProgramCombo);

        ugGrid.add(darkLabel("Year Level:"));
        JComboBox<String> yearLvlCombo = new JComboBox<>(new String[]{
            "1st", "2nd", "3rd", "4th", "5th"
        });
        yearLvlCombo.setBackground(WHITE);
        ugGrid.add(yearLvlCombo);

        
        lGrid = new JPanel(new GridLayout(1, 2, 8, 4));
        lGrid.setBackground(LIGHT_PINK);
        lGrid.setAlignmentX(LEFT_ALIGNMENT);

        lGrid.add(darkLabel("Grade Level:"));
        JComboBox<String> grLvlCombo = new JComboBox<>(new String[]{
            "7th", "8th", "9th", "10th"
        });
        grLvlCombo.setBackground(WHITE);
        lGrid.add(grLvlCombo);

        
        gGrid = new JPanel(new GridLayout(6, 2, 8, 4));
        gGrid.setBackground(LIGHT_PINK);
        gGrid.setAlignmentX(LEFT_ALIGNMENT);

        gGrid.add(darkLabel("Campus Code:"));
        JTextField campusField = styledField();
        campusField.setText("M");
        campusField.setEditable(false);
        gGrid.add(campusField);

        gGrid.add(darkLabel("College Code:"));
        JComboBox<String> gCollegeCombo = new JComboBox<>(new String[]{
            "Graduate School", "College of Law"
        });
        gCollegeCombo.setBackground(WHITE);
        gGrid.add(gCollegeCombo);

        gGrid.add(darkLabel("Program Code:"));
        JComboBox<String> gProgramCombo = new JComboBox<>(new String[]{
            "DPA","EdD","JD","LLB","MAEd","MBA","MEng","MIT","MITM","MME",
            "MPA","MPE","MSCE","MSCompE","MSECE","MSIT","PhD"
        });
        gProgramCombo.setBackground(WHITE);
        gGrid.add(gProgramCombo);

        gGrid.add(darkLabel("Degree:"));
        JComboBox<String> degreeCombo = new JComboBox<>(new String[]{
            "Masteral", "Doctoral"
        });
        degreeCombo.setBackground(WHITE);
        gGrid.add(degreeCombo);

        gGrid.add(twoPartLabel("Year Graduated", " (2024-10-27)"));




        gGrid.add(darkLabel("Thesis Title:"));
        gGrid.add(styledField());

        
        aGrid = new JPanel(new GridLayout(2, 2, 8, 4));
        aGrid.setBackground(LIGHT_PINK);
        aGrid.setAlignmentX(LEFT_ALIGNMENT);

        aGrid.add(twoPartLabel("Year Graduated", " (2024-10-27)"));




        aGrid.add(darkLabel("Thesis Title:"));
        aGrid.add(styledField());

        
        bodyPanel.add(ugGrid);
        bodyPanel.add(lGrid);
        bodyPanel.add(gGrid);
        bodyPanel.add(aGrid);

        lGrid.setVisible(false);
        gGrid.setVisible(false);
        aGrid.setVisible(false);

        bodyPanel.add(Box.createVerticalStrut(6));
        bodyPanel.add(separator());
        bodyPanel.add(Box.createVerticalStrut(6));

        
        sTypeCombo.addActionListener(e -> {
            String selected = (String) sTypeCombo.getSelectedItem();
            ugGrid.setVisible("UNDERGRADUATE".equals(selected));
            lGrid.setVisible("LABHIGH".equals(selected));
            gGrid.setVisible("GRADUATE".equals(selected));
            aGrid.setVisible("ALUMNI".equals(selected));
            pack();
        });

        
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
        bodyPanel.add(btnPanel);

        registerPanel.add(bodyPanel, BorderLayout.CENTER);

        
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(new Color(210, 180, 180));
        wrapper.add(registerPanel);

        add(wrapper, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
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
        new RegisterPatronStu();
    }
}

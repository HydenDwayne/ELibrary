package view.modal;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class registerPatron1 extends JFrame {

    JPanel registerPanel;

    static final Color MAROON       = new Color(106, 27, 27);
    static final Color LIGHT_PINK   = new Color(250, 235, 235);
    static final Color WHITE        = Color.WHITE;
    static final Color DARK_TEXT    = new Color(60, 20, 20);
    static final Color FIELD_BORDER = new Color(180, 130, 130);

    static final int FIELD_HEIGHT = 22;

    public registerPatron1() {
        setTitle("Add Patron");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        registerPanel = new JPanel();
        registerPanel.setPreferredSize(new Dimension(400, 450));
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
        JPanel bodyPanel = new JPanel();
        bodyPanel.setBackground(LIGHT_PINK);
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
        bodyPanel.setBorder(new EmptyBorder(14, 16, 14, 16));

        // Title
        JPanel parentPanel = new JPanel(new GridLayout(2, 1,5,10));
        parentPanel.setPreferredSize(new Dimension(399, 50));
        parentPanel.setBackground(LIGHT_PINK);
   
        JPanel titlePanelChild = new JPanel(new GridBagLayout());
        titlePanelChild.setPreferredSize(new Dimension(397, 21));
        titlePanelChild.setBackground(LIGHT_PINK);
        JLabel title = new JLabel("ADD PATRON");
        title.setForeground(DARK_TEXT);
        title.setMaximumSize(new Dimension(Integer.MAX_VALUE, 26));
        titlePanelChild.add(title);
        parentPanel.add(titlePanelChild);
       
       
        // Step
        JPanel stepPanelChild = new JPanel(new BorderLayout());
        stepPanelChild.setPreferredSize(new Dimension(397, 21));
        stepPanelChild.setBackground(LIGHT_PINK);
        JLabel step = new JLabel("Step 1: Patron Information");
        step.setForeground(DARK_TEXT);
        stepPanelChild.add(step);
        parentPanel.add(stepPanelChild);
        
        bodyPanel.add(parentPanel);
       
        bodyPanel.add(Box.createVerticalStrut(6));

        bodyPanel.add(separator());
        bodyPanel.add(Box.createVerticalGlue());

        // ── All field rows using a single shared GridBagLayout table ─────
        JPanel grid = new JPanel(new GridBagLayout());
        grid.setBackground(LIGHT_PINK);
        grid.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        GridBagConstraints lc = new GridBagConstraints();
        lc.gridx = 0; lc.anchor = GridBagConstraints.WEST;
        lc.insets = new Insets(4, 0, 4, 8);

        GridBagConstraints fc = new GridBagConstraints();
        fc.gridx = 1; fc.fill = GridBagConstraints.HORIZONTAL;
        fc.weightx = 1.0; fc.insets = new Insets(4, 0, 4, 0);

        // Patron ID
        lc.gridy = 0; fc.gridy = 0;
        grid.add(twoPartLabel("Patron ID", " (2026XXXXXX)"), lc);
        grid.add(styledField(), fc);

        // First Name label + Last Name label row
        GridBagConstraints lc2 = new GridBagConstraints();
        lc2.gridx = 0; lc2.gridy = 1;
        lc2.anchor = GridBagConstraints.WEST;
        lc2.insets = new Insets(4, 0, 0, 8);

        GridBagConstraints lc3 = new GridBagConstraints();
        lc3.gridx = 1; lc3.gridy = 1;
        lc3.anchor = GridBagConstraints.WEST;
        lc3.weightx = 1.0;
        lc3.insets = new Insets(4, 0, 0, 0);

        grid.add(darkLabel("First Name"), lc2);
        grid.add(darkLabel("Last Name"), lc3);

        // First Name field + Last Name field row
        GridBagConstraints fc2 = new GridBagConstraints();
        fc2.gridx = 0; fc2.gridy = 2;
        fc2.fill = GridBagConstraints.HORIZONTAL;
        fc2.insets = new Insets(0, 0, 4, 8);

        GridBagConstraints fc3 = new GridBagConstraints();
        fc3.gridx = 1; fc3.gridy = 2;
        fc3.fill = GridBagConstraints.HORIZONTAL;
        fc3.weightx = 1.0;
        fc3.insets = new Insets(0, 0, 4, 0);

        grid.add(styledField(), fc2);
        grid.add(styledField(), fc3);

        // Middle Initial
        lc.gridy = 3; fc.gridy = 3;
        grid.add(twoPartLabel("Middle Initial", " (Optional)"), lc);
        grid.add(styledField(), fc);

        // Email Address
        lc.gridy = 4; fc.gridy = 4;
        grid.add(twoPartLabel("Email Address", " (Optional)"), lc);
        grid.add(styledField(), fc);

        // Contact Num
        lc.gridy = 5; fc.gridy = 5;
        grid.add(twoPartLabel("Contact Num.", " (Optional)"), lc);
        grid.add(styledField(), fc);

        // Home Address
        lc.gridy = 6; fc.gridy = 6;
        grid.add(twoPartLabel("Home Address", " (Optional)"), lc);
        grid.add(styledField(), fc);

        // Campus
        lc.gridy = 7; fc.gridy = 7;
        grid.add(darkLabel("Campus Code"), lc);
        JComboBox<String> campusCombo = new JComboBox<>(new String[]{"Main", "Bustos", "Hagonoy", "Meneses", "San Rafael","Sarmiento"});
        campusCombo.setBackground(WHITE);
        campusCombo.setPreferredSize(new Dimension(0, FIELD_HEIGHT));
        grid.add(campusCombo, fc);

        bodyPanel.add(grid);
        bodyPanel.add(Box.createVerticalGlue());

        bodyPanel.add(separator());
        bodyPanel.add(Box.createVerticalGlue());

        // Radio buttons
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        radioPanel.setBackground(LIGHT_PINK);
        radioPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 24));
        ButtonGroup bg = new ButtonGroup();
        JRadioButton studentBtn  = new JRadioButton("Student");
        JRadioButton employeeBtn = new JRadioButton("Employee");
        studentBtn.setSelected(true);
        studentBtn.setBackground(LIGHT_PINK);
        employeeBtn.setBackground(LIGHT_PINK);
        studentBtn.setForeground(DARK_TEXT);
        employeeBtn.setForeground(DARK_TEXT);
        bg.add(studentBtn);
        bg.add(employeeBtn);
        radioPanel.add(studentBtn);
        radioPanel.add(employeeBtn);
        bodyPanel.add(radioPanel);
        bodyPanel.add(Box.createVerticalGlue());

        bodyPanel.add(separator());
        bodyPanel.add(Box.createVerticalGlue());

        // Buttons
        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        btnPanel.setBackground(LIGHT_PINK);
        btnPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));


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

        // Wrapper
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(new Color(210, 180, 180));
        wrapper.add(registerPanel);

        add(wrapper, BorderLayout.CENTER);
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
        new registerPatron1();
    }
}
package elibrary;

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

        JPanel grid = new JPanel(new GridLayout(0, 2, 8, 4));
        grid.setBackground(LIGHT_PINK);

        grid.add(twoPartLabel("Patron ID", " (2026XXXXXX)"));
        grid.add(styledField());

        grid.add(darkLabel("First Name"));
        grid.add(darkLabel("Last Name"));

        grid.add(styledField());
        grid.add(styledField());

        grid.add(twoPartLabel("Middle Initial", " (Optional)"));
        grid.add(styledField());

        grid.add(twoPartLabel("Email Address", " (Optional)"));
        grid.add(styledField());

        grid.add(twoPartLabel("Contact Num.", " (Optional)"));
        grid.add(styledField());

        grid.add(twoPartLabel("Home Address", " (Optional)"));
        grid.add(styledField());

        grid.add(darkLabel("Campus Code"));
        JComboBox<String> campusCombo = new JComboBox<>(new String[]{"Main", "Bustos", "Hagonoy", "Meneses", "San Rafael", "Sarmiento"});
        campusCombo.setBackground(WHITE);
        grid.add(campusCombo);

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
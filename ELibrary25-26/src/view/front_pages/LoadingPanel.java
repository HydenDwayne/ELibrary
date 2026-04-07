package view.front_pages;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import view.RoundedComponents.RoundedButton;
import view.RoundedComponents.RoundedPanel;
import view.RoundedComponents.RoundedTextField;
import view.fonts.Fonts;

public class LoadingPanel extends JPanel {

    static final Color MAROON       = new Color(132, 43, 40);      // #842b28
    static final Color LIGHT_PINK   = new Color(250, 236, 238);  // #faecee
    static final Color WHITE        = Color.WHITE;
    static final Color DARK_TEXT    = new Color(109, 35, 33);      // #6d2321
    static final Color FIELD_BORDER = new Color(146, 76, 74);    // #924c4a

    static final int PANEL_RADIUS = 20;
    static final int FIELD_RADIUS = 15;
    
    private Image backgroundImage;
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    public LoadingPanel() {
    	backgroundImage = new ImageIcon(FilePath.image("blurred_bg.jpg")).getImage();
         

        setLayout(new BorderLayout());

        // 👉 Wider and shorter
        setPreferredSize(new Dimension(700, 300));


        // ===== CENTER CONTENT =====
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 20, 40));

        // LOGO (LEFT-CENTER)
        ImageIcon icon = new ImageIcon(FilePath.image("elib_logo.png"));
        Image scaled = icon.getImage().getScaledInstance(240, 120, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaled));
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        centerPanel.add(logoLabel, BorderLayout.WEST);

        // TITLE (RIGHT-CENTER, ONE LINE ONLY)
        JLabel titleLabel = new JLabel("E-Library Management System");
        titleLabel.setFont(new Fonts("IntroRust", 20f).getAppliedFont());
        titleLabel.setForeground(DARK_TEXT);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        centerPanel.add(titleLabel, BorderLayout.CENTER);


        // ===== BOTTOM LEFT (Names) =====
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 10, 20));
        

        JPanel namesBox = new JPanel();
        namesBox.setLayout(new BoxLayout(namesBox, BoxLayout.Y_AXIS));
        namesBox.setOpaque(false);
        namesBox.setPreferredSize(new Dimension(400, 100));

        namesBox.add(darkLabel("Hyden Dwayne Sapasap"));
        namesBox.add(darkLabel("Pia Amanda Rebuelta"));
        namesBox.add(darkLabel("Jury Gabriel Albania"));
        namesBox.add(darkLabel("Fer John Cleries Clemente"));
        
        bottomPanel.add(namesBox, BorderLayout.WEST);
        
        JPanel loading = new JPanel();
        loading.setLayout(new BorderLayout());
        loading.setOpaque(false);
        loading.add(darkLabel("Loading system..."), BorderLayout.SOUTH);

        bottomPanel.add(loading, BorderLayout.EAST);

        // ===== ADD TO MODAL =====
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JLabel darkLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(DARK_TEXT);
        lbl.setHorizontalAlignment(JLabel.CENTER);
        lbl.setFont(new Fonts("PoppinsBold", 16f).getAppliedFont());
        return lbl;
    }

}


package view.modal.ims_modal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.ArchiveController;
import controller.IMSController;

import java.awt.*;

import view.RoundedComponents.RoundedButton;
import view.RoundedComponents.RoundedPanel;
import view.RoundedComponents.RoundedTextField;
import view.front_pages.FilePath;
import view.fonts.Fonts;

public class ViewIMS extends JPanel {

    static final Color MAROON = new Color(132, 43, 40);
    static final Color LIGHT_PINK = new Color(250, 236, 238);
    static final Color WHITE = Color.WHITE;
    static final Color DARK_TEXT = new Color(109, 35, 33);
    static final Color FIELD_BORDER = new Color(146, 76, 74);

    static final int PANEL_RADIUS = 20;
    static final int FIELD_RADIUS = 15;
    
    public RoundedTextField itemField;
    public RoundedTextField equipField;
    public RoundedTextField serialField;
    
    IMSController comp;

    public ViewIMS(String serialNumber) {
    	comp = new IMSController(this);
        setOpaque(false);
        setLayout(new BorderLayout());

        /* ================= FONTS ================= */

        Font introRust26 = new Fonts("IntroRust", 36f).getAppliedFont();
        Font introRust24 = new Fonts("IntroRust", 24f).getAppliedFont();
        Font poppins16   = new Fonts("Poppins", 16f).getAppliedFont();
        Font poppins12   = new Fonts("Poppins", 12f).getAppliedFont();
        Font poppins10   = new Fonts("Poppins", 10f).getAppliedFont();

        /* ================= MODAL ================= */

        RoundedPanel modal = new RoundedPanel(PANEL_RADIUS);
        modal.setLayout(new BorderLayout());
        modal.setPreferredSize(new Dimension(500, 400));
        modal.setBackground(LIGHT_PINK);

        /* ================= HEADER ================= */

        JPanel header = new JPanel();
        header.setBackground(MAROON);
        header.setPreferredSize(new Dimension(500, 100));
        header.setBorder(new EmptyBorder(10, 0, 10, 10));

        JPanel headerWrapper = new JPanel(new BorderLayout());
        headerWrapper.setPreferredSize(new Dimension(450, 80));
        headerWrapper.setOpaque(false);

        ImageIcon icon = new ImageIcon(FilePath.image("elib_logo.png"));
        Image scaled = icon.getImage().getScaledInstance(110, 50, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(scaled));
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        headerWrapper.add(logo, BorderLayout.WEST);

        JLabel headerLabel = new JLabel("IMS");
        headerLabel.setFont(introRust26);
        headerLabel.setForeground(WHITE);
        headerLabel.setBorder(new EmptyBorder(0, 70, 0, 0));
        headerWrapper.add(headerLabel, BorderLayout.CENTER);

        header.add(headerWrapper);

        /* ================= BODY ================= */

        JPanel body = new JPanel(new BorderLayout());
        body.setOpaque(false);
        body.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("DETAILED VIEW FOR AN IMS", SwingConstants.CENTER);
        title.setFont(introRust24);
        title.setForeground(DARK_TEXT);
        body.add(title, BorderLayout.NORTH);

        JPanel innerBody = new JPanel(new GridBagLayout());
        innerBody.setOpaque(false);
        innerBody.setBorder(new EmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = -1;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        /* ================= FORM FIELDS ================= */

        // Serial Number
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel serialLbl = new JLabel("Serial Number:");
        serialLbl.setFont(poppins16);
        serialLbl.setForeground(DARK_TEXT);
        innerBody.add(serialLbl, gbc);

        gbc.gridx = 1;
         serialField = new RoundedTextField(19, FIELD_RADIUS);
         serialField.setEnabled(false);
        serialField.setFont(poppins10);
        serialField.setBorderColor(FIELD_BORDER);
        serialField.setBorderThickness(1);
        innerBody.add(serialField, gbc);

        // Equipment Name
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel equipLbl = new JLabel("Equipment Name:");
        equipLbl.setFont(poppins16);
        equipLbl.setForeground(DARK_TEXT);
        innerBody.add(equipLbl, gbc);

        gbc.gridx = 1;
         equipField = new RoundedTextField(19, FIELD_RADIUS);
        equipField.setFont(poppins10);
        equipField.setBorderColor(FIELD_BORDER);
        equipField.setBorderThickness(1);
        innerBody.add(equipField, gbc);

        // Patron ID
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel typeLbl = new JLabel("Item Type:");
        typeLbl.setFont(poppins16);
        typeLbl.setForeground(DARK_TEXT);
        innerBody.add(typeLbl, gbc);

        gbc.gridx = 1;
        itemField = new RoundedTextField(19, FIELD_RADIUS);
        itemField.setFont(poppins10);
        itemField.setBorderColor(FIELD_BORDER);
        itemField.setBorderThickness(1);
        innerBody.add(itemField, gbc);

        body.add(innerBody, BorderLayout.CENTER);

        /* ================= FOOTER ================= */

        JPanel footer = new JPanel(new GridLayout(2, 1, 0, 10));
        footer.setBorder(new EmptyBorder(0, 35, 10, 35));
        footer.setOpaque(false);

        RoundedButton saveBtn = new RoundedButton("SAVE DETAILS", FIELD_RADIUS);
        saveBtn.setFont(poppins12);
        saveBtn.setBackground(MAROON);
        saveBtn.setForeground(WHITE);
        saveBtn.addActionListener(e -> {
        	
        	String[] equipmentInfo = {
        			serialNumber,
        			equipField.getRealText().trim(),
        			itemField.getRealText().trim()
        	}; 
        	boolean isSuccessful = comp.updateEquipmentInfo(equipmentInfo);
        	
        	if (isSuccessful) {
        		Window w = SwingUtilities.getWindowAncestor(this);
                if (w != null) w.dispose();
        	}
        });
        footer.add(saveBtn);

        RoundedButton cancelBtn = new RoundedButton("CANCEL", FIELD_RADIUS);
        cancelBtn.setFont(poppins12);
        cancelBtn.setForeground(MAROON);
        cancelBtn.setBorderColor(MAROON);
        cancelBtn.setBorderThickness(1);
        cancelBtn.addActionListener(e -> {
            Window w = SwingUtilities.getWindowAncestor(this);
            if (w != null) w.dispose();
        });
        
        
        JPanel bottomBtns = new JPanel();
        bottomBtns.setOpaque(false);
        bottomBtns.setLayout(new GridLayout(1,2,10,0));
        
        RoundedButton archiveBtn = new RoundedButton("ARCHIVE ITEM", FIELD_RADIUS);
        archiveBtn.setFont(poppins10);
        archiveBtn.setForeground(MAROON);
        archiveBtn.setBorderColor(MAROON);
        archiveBtn.setBorderThickness(1);
        archiveBtn.addActionListener(e -> {
        	ArchiveController comp = new ArchiveController("IMS", serialNumber);
        	
        	boolean isSuccessful = comp.setArchived();
        	if(isSuccessful) {
        		Window w = SwingUtilities.getWindowAncestor(this);
        		if (w != null) w.dispose();
        	}
        });
        
        bottomBtns.add(cancelBtn);
        bottomBtns.add(archiveBtn);
        
        footer.add(bottomBtns);

        /* ================= ASSEMBLY ================= */
        
        comp.getEquipmentInfo(serialNumber);
        

        modal.add(header, BorderLayout.NORTH);
        modal.add(body, BorderLayout.CENTER);
        modal.add(footer, BorderLayout.SOUTH);

        add(modal, BorderLayout.CENTER);
    }
}
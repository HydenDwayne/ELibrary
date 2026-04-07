package view.modal.librarian_profile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.LoginController;

import java.awt.*;

import view.RoundedComponents.*;
import view.fonts.Fonts;
import view.front_pages.Dashboard;

public class ViewUserProfile extends JPanel {

    static final Color MAROON = new Color(132, 43, 40);
    static final Color LIGHT_PINK = new Color(250, 236, 238);
    static final Color WHITE = Color.WHITE;
    static final Color DARK_TEXT = new Color(109, 35, 33);
    static final Color FIELD_BORDER = new Color(146, 76, 74);

    static final int PANEL_RADIUS = 20;
    static final int FIELD_RADIUS = 15;

    public RoundedTextField usernameField;
    public RoundedTextField emailField;
    public RoundedTextField fullNameField;
    public RoundedPasswordField prevPasswordField;
    public RoundedPasswordField newPasswordField;
    public RoundedPasswordField confirmPasswordField;

    JLabel usernameLbl;
    JLabel emailLbl;
    JLabel fullNameLbl;
    JLabel prevPasswordLbl;
    JLabel newPasswordLbl;
    JLabel confirmPasswordLbl;

    public ViewUserProfile(String user, String password, Dashboard frame) {
    	
        setOpaque(false);
        setLayout(new BorderLayout());

        /* ================= FONTS ================= */
        Font introRust24 = new Fonts("IntroRust", 24f).getAppliedFont();
        Font poppins16 = new Fonts("Poppins", 16f).getAppliedFont();
        Font poppins12 = new Fonts("Poppins", 12f).getAppliedFont();
        Font poppins10 = new Fonts("Poppins", 10f).getAppliedFont();

        /* ================= MODAL ================= */
        RoundedPanel modal = new RoundedPanel(PANEL_RADIUS);
        modal.setLayout(new BorderLayout());
        modal.setPreferredSize(new Dimension(520, 480));
        modal.setBackground(LIGHT_PINK);

        /* ================= HEADER ================= */
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(MAROON);
        header.setPreferredSize(new Dimension(520, 80));
        header.setBorder(new EmptyBorder(10, 20, 10, 10));

        JLabel headerLabel = new JLabel("USER PROFILE", SwingConstants.CENTER);
        headerLabel.setFont(introRust24);
        headerLabel.setForeground(WHITE);

        header.add(headerLabel, BorderLayout.CENTER);

        /* ================= BODY ================= */
        JPanel body = new JPanel(new BorderLayout());
        body.setOpaque(false);
        body.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel bodyTitle = new JLabel("ACCOUNT DETAILS", SwingConstants.CENTER);
        bodyTitle.setFont(introRust24);
        bodyTitle.setForeground(DARK_TEXT);
        body.add(bodyTitle, BorderLayout.NORTH);

        JPanel innerBody = new JPanel(new GridBagLayout());
        innerBody.setOpaque(false);
        innerBody.setBorder(new EmptyBorder(15, 20, 15, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.weightx = 0.5;
        gbc.gridy = 0;

        Dimension labelSize = new Dimension(180, 30);
        
        

        /* ================= ROW 1: Username ================= */
        gbc.gridx = 0;
        usernameLbl = new JLabel("Username:");
        usernameLbl.setFont(poppins16);
        usernameLbl.setForeground(DARK_TEXT);
        usernameLbl.setPreferredSize(labelSize);
        innerBody.add(usernameLbl, gbc);

        gbc.gridx = 1;
        usernameField = new RoundedTextField(20, FIELD_RADIUS);
        usernameField.setFont(poppins10);
        usernameField.setText("");
        usernameField.setBorderColor(FIELD_BORDER);
        usernameField.setBorderThickness(1);
        innerBody.add(usernameField, gbc);

        /* ================= ROW 2: Email ================= */
        gbc.gridy++;
        gbc.gridx = 0;
        emailLbl = new JLabel("Email:");
        emailLbl.setFont(poppins16);
        emailLbl.setForeground(DARK_TEXT);
        emailLbl.setPreferredSize(labelSize);
        innerBody.add(emailLbl, gbc);

        gbc.gridx = 1;
        emailField = new RoundedTextField(20, FIELD_RADIUS);
        emailField.setFont(poppins10);
        emailField.setText("");
        emailField.setBorderColor(FIELD_BORDER);
        emailField.setBorderThickness(1);
        innerBody.add(emailField, gbc);

        /* ================= ROW 3: Full Name ================= */
        gbc.gridy++;
        gbc.gridx = 0;
        fullNameLbl = new JLabel("Full Name:");
        fullNameLbl.setFont(poppins16);
        fullNameLbl.setForeground(DARK_TEXT);
        fullNameLbl.setPreferredSize(labelSize);
        innerBody.add(fullNameLbl, gbc);

        gbc.gridx = 1;
        fullNameField = new RoundedTextField(20, FIELD_RADIUS);
        fullNameField.setFont(poppins10);
        fullNameField.setText("");
        fullNameField.setBorderColor(FIELD_BORDER);
        fullNameField.setBorderThickness(1);
        innerBody.add(fullNameField, gbc);

        /* ================= ROW 4: Previous Password ================= */
        gbc.gridy++;
        gbc.gridx = 0;
        prevPasswordLbl = new JLabel("Previous Password:");
        prevPasswordLbl.setFont(poppins16);
        prevPasswordLbl.setForeground(DARK_TEXT);
        prevPasswordLbl.setPreferredSize(labelSize);
        
        innerBody.add(prevPasswordLbl, gbc);

        gbc.gridx = 1;
        prevPasswordField = new RoundedPasswordField(20, FIELD_RADIUS);
        prevPasswordField.setFont(poppins10);
        prevPasswordField.setBorder(BorderFactory.createLineBorder(FIELD_BORDER, 1, true));
        prevPasswordField.setEnabled(false);
        innerBody.add(prevPasswordField, gbc);

        /* ================= ROW 5: New Password ================= */
        gbc.gridy++;
        gbc.gridx = 0;
        newPasswordLbl = new JLabel("New Password:");
        newPasswordLbl.setFont(poppins16);
        newPasswordLbl.setForeground(DARK_TEXT);
        newPasswordLbl.setPreferredSize(labelSize);
        innerBody.add(newPasswordLbl, gbc);

        gbc.gridx = 1;
        newPasswordField = new RoundedPasswordField(20, FIELD_RADIUS);
        newPasswordField.setFont(poppins10);
        newPasswordField.setBorder(BorderFactory.createLineBorder(FIELD_BORDER, 1, true));
        innerBody.add(newPasswordField, gbc);

        /* ================= ROW 6: Confirm Password ================= */
        gbc.gridy++;
        gbc.gridx = 0;
        confirmPasswordLbl = new JLabel("Confirm Password:");
        confirmPasswordLbl.setFont(poppins16);
        confirmPasswordLbl.setForeground(DARK_TEXT);
        confirmPasswordLbl.setPreferredSize(labelSize);
        innerBody.add(confirmPasswordLbl, gbc);

        gbc.gridx = 1;
        confirmPasswordField = new RoundedPasswordField(20, FIELD_RADIUS);
        confirmPasswordField.setFont(poppins10);
        confirmPasswordField.setBorder(BorderFactory.createLineBorder(FIELD_BORDER, 1, true));
        innerBody.add(confirmPasswordField, gbc);
        
        
        LoginController comp = new LoginController(user, null);
        String[] details = comp.getLibrarianDetails();
        
        usernameField.setText(details[2]);
        emailField.setText(details[1]);
        fullNameField.setText(details[0]);
        prevPasswordField.setText(details[3]);

        body.add(innerBody, BorderLayout.CENTER);

        /* ================= FOOTER ================= */
        JPanel footer = new JPanel(new GridLayout(1, 2, 10, 0));
        footer.setBorder(new EmptyBorder(10, 35, 10, 35));
        footer.setOpaque(false);

        RoundedButton backBtn = new RoundedButton("< BACK", FIELD_RADIUS);
        backBtn.setFont(poppins12);
        backBtn.setForeground(MAROON);
        backBtn.setBorderColor(MAROON);
        backBtn.setBorderThickness(1);
        backBtn.addActionListener(e -> {
            Window w = SwingUtilities.getWindowAncestor(this);
            if (w instanceof JDialog) {
                w.dispose();
            }
        });

        RoundedButton updateBtn = new RoundedButton("SAVE CHANGES", FIELD_RADIUS);
        updateBtn.setFont(poppins12);
        updateBtn.setBackground(MAROON);
        updateBtn.setForeground(WHITE);
        updateBtn.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String email = emailField.getText().trim();
            String fullName = fullNameField.getText().trim();
            String newPass = new String(newPasswordField.getPassword());
            String confirm = new String(confirmPasswordField.getPassword());

            // Check if username, fullName, email are non-whitespace
            if (!username.isEmpty() && !email.isEmpty() && !fullName.isEmpty()) {

                String[] userData;

                // Case 1: new password fields are empty or null
                if ((newPass == null || newPass.isEmpty()) && (confirm == null || confirm.isEmpty())) {
                    userData = new String[]{user, username, fullName, email, password,"","", "false"};
                    boolean isSuccessful = comp.updateLibrarianDetails(userData);
                    if (isSuccessful) {
                    	System.out.println(username);
                    	LoginController updatedComp = new LoginController(username, null);
                    	String[] librarianDetails = updatedComp.getLibrarianDetails();
                    	frame.password = librarianDetails[3];
                    	frame.username = librarianDetails[2];
                    	frame.accountName.setText(librarianDetails[0]);
                    	frame.accountEmail.setText(librarianDetails[1]);
                    	Window w = SwingUtilities.getWindowAncestor(this);
                        if (w instanceof JDialog) {
                            w.dispose();
                        }
                    }
                } 
                // Case 2: new password fields both have values
                else if (!newPass.isEmpty() && !confirm.isEmpty()) {
                    if (!newPass.equals(confirm)) {
                        JOptionPane.showMessageDialog(this, "New passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    userData = new String[]{user, username, fullName, email, password, newPass, confirm, "true"};
                    boolean isSuccessful = comp.updateLibrarianDetails(userData);
                    if (isSuccessful) {
                    	System.out.println(username);
                    	LoginController updatedComp = new LoginController(username, null);
                    	String[] librarianDetails = updatedComp.getLibrarianDetails();
                    	frame.password = librarianDetails[3];
                    	frame.username = librarianDetails[2];
                    	frame.accountName.setText(librarianDetails[0]);
                    	frame.accountEmail.setText(librarianDetails[1]);
                    	Window w = SwingUtilities.getWindowAncestor(this);
                        if (w instanceof JDialog) {
                            w.dispose();
                        }
                    }
                } 
                else {
                    JOptionPane.showMessageDialog(this, "Please fill both new password fields or leave them empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

//                JOptionPane.showMessageDialog(this, "Data validated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Username, Full Name, and Email cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        footer.add(backBtn);
        footer.add(updateBtn);

        /* ================= ASSEMBLY ================= */
        modal.add(header, BorderLayout.NORTH);
        modal.add(body, BorderLayout.CENTER);
        modal.add(footer, BorderLayout.SOUTH);

        add(modal, BorderLayout.CENTER);
    }
}
package view;

import view.RoundedComponents.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.Border;

import controller.LoginController;
import view.fonts.*;

public class LoginWindow extends JFrame {
	
	static String imgFilePath = FilePath.getImgFilePath();

    public LoginWindow(Dashboard frame) {
        setTitle("E-Library Management System");

        // Background panel with image
        JPanel panel = new JPanel() {
            Image backgroundImage = new ImageIcon(imgFilePath + "blurred_bg.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new GridBagLayout());
        setContentPane(panel);

        // red frosty panel
        RoundedPanel loginContainer = new RoundedPanel(30);
        loginContainer.setPreferredSize(new Dimension(350, 400));
        loginContainer.setBackground(new Color(121, 25, 24, 160));
        Border padding = BorderFactory.createEmptyBorder(20, 10, 10, 10);
        loginContainer.setBorder(padding);

        loginContainer.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0); // spacing between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // elib logo
        ImageIcon icon = new ImageIcon(imgFilePath + "elib_logo.png");
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(160, 80, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);
        JLabel elibLogo = new JLabel(icon);
        elibLogo.setHorizontalAlignment(SwingConstants.CENTER);
        loginContainer.add(elibLogo, gbc);

        // welcome message
        gbc.gridy++;
        JLabel welcomeMsg = new JLabel("<html><div style='text-align:center;'>Welcome to BulSU e-Library <br>Management Tool</div></html>");
        
        Fonts aBeeZee = new Fonts("ABeeZee", 18f);
        Font aBeeZee18Style = aBeeZee.getAppliedFont();
        welcomeMsg.setFont(aBeeZee18Style);
        
        welcomeMsg.setForeground(Color.WHITE);
        welcomeMsg.setHorizontalAlignment(SwingConstants.CENTER);
        loginContainer.add(welcomeMsg, gbc);

        // username field
        gbc.gridy++;
        RoundedTextField username = new RoundedTextField(25, 20);
        username.setPlaceholder("Admin username");
        loginContainer.add(username, gbc);

        // password field
        gbc.gridy++;
        RoundedTextField password = new RoundedTextField(25, 20);
        password.setPlaceholder("Password");
        loginContainer.add(password, gbc);

        // submit button
        gbc.gridy++;
        RoundedButton submitBtn = new RoundedButton("Submit", 20);
        submitBtn.setBackground(Color.decode("#5d1513"));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFocusPainted(false);
        submitBtn.addActionListener((ActionEvent e) -> {
//            Dashboard dash = new Dashboard();
//            dash.setVisible(true);
//            SwingUtilities.getWindowAncestor(submitBtn).setVisible(false);
        	
        	LoginController comp = new LoginController(username.getText(), password.getText());
        	
        	if (comp.isLoggedIn()) {
        		frame.setVisible(true);
        		dispose();
        	} else {
        		JOptionPane.showMessageDialog(null, "Invalid login credentials");
        	}
        });
        loginContainer.add(submitBtn, gbc);

        // facility login button
        gbc.gridy++;
        JButton loginFacility = new JButton("Set-up as Facility Login");
        loginFacility.setBorderPainted(false);
        loginFacility.setContentAreaFilled(false);
        loginFacility.setFocusPainted(false);
        loginFacility.setOpaque(false);
        loginFacility.setForeground(Color.decode("#f8c169"));
        loginFacility.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginFacility.setFont(loginFacility.getFont().deriveFont(Font.PLAIN, 14f));
        loginFacility.addActionListener(e -> {
            FacilityLogin fl = new FacilityLogin(frame);
            fl.setVisible(true);
            dispose();
        });
        loginContainer.add(loginFacility, gbc);

        // Add loginContainer to panel
        panel.add(loginContainer);

        // JFrame final setup

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });

        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                // Request focus on something else, or a panel
                panel.requestFocusInWindow();
            }
        });

        setContentPane(panel);
        pack();


        if (getWidth() < 1120 || getHeight() < 600) {
            setSize(1120, 600);
        }

        setMinimumSize(new Dimension(1120, 600));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

package view.front_pages;

import javax.swing.*;

import view.RoundedComponents.*;
import view.fonts.Fonts;

import java.awt.*;
import java.awt.event.*;

import controller.LoginController;
import view.front_pages.patron_registration.RegisterPatron;

public class LoginWindow extends JFrame {

    public LoginWindow(Dashboard frame) {

        int panelRadius = 20;

        JPanel panel = new JPanel() {
            Image backgroundImage = new ImageIcon(FilePath.image("blurred_bg.jpg")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new GridBagLayout());

        RoundedPanel modal = new RoundedPanel(panelRadius);
        modal.setLayout(new BorderLayout());
        modal.setPreferredSize(new Dimension(500, 500));
        modal.setBackground(Color.decode("#faecee"));

        // ================= HEADER =================
        JPanel header = new JPanel();
        header.setBackground(Color.decode("#842b28"));
        header.setPreferredSize(new Dimension(500, 100));
        header.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));

        JPanel headerWrapper = new JPanel();
        headerWrapper.setLayout(new BorderLayout());
        headerWrapper.setPreferredSize(new Dimension(450, 80));
        headerWrapper.setOpaque(false);

        ImageIcon icon = new ImageIcon(FilePath.image("elib_logo.png"));
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(110, 50, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);

        JLabel elibLogo = new JLabel(icon);
        elibLogo.setHorizontalAlignment(SwingConstants.CENTER);
        headerWrapper.add(elibLogo, BorderLayout.WEST);

        Fonts introRust36 = new Fonts("IntroRust", 20f);
        Font introRustStyle26 = introRust36.getAppliedFont();

        JLabel headerLabel = new JLabel("E-Library Management System");
        headerLabel.setFont(introRustStyle26);
        headerLabel.setForeground(Color.WHITE);

        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerWrapper.add(headerLabel, BorderLayout.CENTER);
        header.add(headerWrapper);

        // ================= BODY =================
        JPanel body = new JPanel();
        body.setOpaque(false);
        body.setLayout(new BorderLayout());
        
        body.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Fonts introRust = new Fonts("IntroRust", 24f);
        Font introRustStyle = introRust.getAppliedFont();

        JLabel bodyTitle = new JLabel("LOGIN WINDOW", SwingConstants.CENTER);
        bodyTitle.setForeground(Color.decode("#6d2321"));
        bodyTitle.setFont(introRustStyle);

        body.add(bodyTitle, BorderLayout.NORTH);

        JPanel innerBody = new JPanel();
        innerBody.setOpaque(false);
        
        innerBody.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        innerBody.setLayout(new GridLayout(4,1,0,10));

//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.gridy = 0;
//        gbc.gridx = 0;
//        gbc.insets = new Insets(5, 5, 5, 5);
        
        


        Fonts poppins16 = new Fonts("Poppins", 16f);
        Font poppinsStyle16 = poppins16.getAppliedFont();

        Fonts poppins12 = new Fonts("Poppins", 10f);
        Font poppinsStyle = poppins12.getAppliedFont();

        
        
        
        // USERNAME
        JPanel userLabelWrapper = new JPanel();
        userLabelWrapper.setOpaque(false);
//        userLabelWrapper.setPreferredSize(labelSize);
        userLabelWrapper.setLayout(new BorderLayout());

        JLabel userLabel = new JLabel("Username", SwingConstants.CENTER);
        userLabel.setFont(poppinsStyle16);
        userLabel.setForeground(Color.decode("#842b28"));
        userLabelWrapper.add(userLabel, BorderLayout.CENTER);

        innerBody.add(userLabelWrapper);


        RoundedTextField username = new RoundedTextField(25, 15);
        username.setPlaceholder("Enter username");
        username.setFont(poppinsStyle);
        username.setBorderColor(Color.decode("#924c4a"));
        username.setBorderThickness(1);

        innerBody.add(username);
        
        

        // PASSWORD


        JPanel passLabelWrapper = new JPanel();
        passLabelWrapper.setOpaque(false);
//        passLabelWrapper.setPreferredSize(labelSize);
        passLabelWrapper.setLayout(new BorderLayout());

        JLabel passLabel = new JLabel("Password", SwingConstants.CENTER);
        passLabel.setFont(poppinsStyle16);
        passLabel.setForeground(Color.decode("#842b28"));
        passLabelWrapper.add(passLabel, BorderLayout.CENTER);

        innerBody.add(passLabelWrapper);


        RoundedPasswordField password = new RoundedPasswordField(25, 15);
        password.setPlaceholder("Enter password");
        password.setFont(poppinsStyle);
        password.setBorderColor(Color.decode("#924c4a"));
        password.setBorderThickness(1);

        innerBody.add(password);

        body.add(innerBody);

        // ================= FOOTER =================
        JPanel footer = new JPanel();
        footer.setPreferredSize(new Dimension(500, 150));
        footer.setBorder(BorderFactory.createEmptyBorder(0, 35, 10, 35));
        footer.setLayout(new GridLayout(3,1,10,0));

        Fonts poppins12btn = new Fonts("Poppins", 12f);
        Font poppinsStyle12 = poppins12btn.getAppliedFont();

        RoundedButton submitBtn = new RoundedButton("LOGIN", 15);
        submitBtn.setPreferredSize(new Dimension(500, 40));
        submitBtn.setBackground(Color.decode("#842b28"));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFont(poppinsStyle12);

        submitBtn.addActionListener(e -> {
            LoginController comp = new LoginController(username.getText(), password.getText());

            if (comp.isLoggedIn()) {
            	String[] librarianDetails = comp.getLibrarianDetails();
            	frame.password = librarianDetails[3];
            	frame.username = librarianDetails[2];
            	frame.accountName.setText(librarianDetails[0]);
            	frame.accountEmail.setText(librarianDetails[1]);
                frame.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid login credentials");
            }
        });

        footer.add(submitBtn);
        JPanel labelWrapper = new JPanel();
        labelWrapper.setOpaque(false);
        labelWrapper.setLayout(new GridBagLayout());
        
        JLabel setupLabel = new JLabel("Setup options");
        labelWrapper.add(setupLabel);
        setupLabel.setForeground(Color.decode("#6d2321"));
        setupLabel.setFont(new Fonts("IntroRust", 14f).getAppliedFont());
        footer.add(labelWrapper);
        
        JPanel btnWrapper = new JPanel();
        btnWrapper.setOpaque(false);
//        btnWrapper.setBorder(BorderFactory.createEmptyBorder(0, 35, 10, 35));
        btnWrapper.setLayout(new GridLayout(1,3,10,0));
        
        
        
        

        RoundedButton reportBtn = new RoundedButton("Lost and Found", 15);
        reportBtn.setForeground(Color.decode("#842b28"));
        reportBtn.setBorderColor(Color.decode("#842b28"));
        reportBtn.setBorderThickness(1);
        reportBtn.setPreferredSize(new Dimension(500, 40));
        reportBtn.setFont(poppinsStyle12);

        reportBtn.addActionListener(e -> {
            new ReportLNF(frame, this).setVisible(true);
            setVisible(false);
        });
        
        RoundedButton facilityBtn = new RoundedButton("Facility Login", 15);
        facilityBtn.setForeground(Color.decode("#842b28"));
        facilityBtn.setBorderColor(Color.decode("#842b28"));
        facilityBtn.setBorderThickness(1);
        facilityBtn.setPreferredSize(new Dimension(500, 40));
        facilityBtn.setFont(poppinsStyle12);

        facilityBtn.addActionListener(e -> {
            new FacilityLogin(frame, this).setVisible(true);
            setVisible(false);
        });
        
        RoundedButton patronBtn = new RoundedButton("Register Patron", 15);
        patronBtn.setForeground(Color.decode("#842b28"));
        patronBtn.setBorderColor(Color.decode("#842b28"));
        patronBtn.setBorderThickness(1);
        patronBtn.setPreferredSize(new Dimension(500, 40));
        patronBtn.setFont(poppinsStyle12);

        patronBtn.addActionListener(e -> {
            new RegisterPatron(frame, this).setVisible(true);
            setVisible(false);
        });
        
        btnWrapper.add(reportBtn);
        btnWrapper.add(facilityBtn);
        btnWrapper.add(patronBtn);

        footer.add(btnWrapper, BorderLayout.SOUTH);

        // ================= ADD =================
        modal.add(header, BorderLayout.NORTH);
        modal.add(body, BorderLayout.CENTER);
        modal.add(footer, BorderLayout.SOUTH);

        panel.add(modal);
        add(panel);

        // FRAME SETTINGS
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });

        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                modal.requestFocusInWindow();
            }
        });

        setTitle("E-Library Management System");
        setContentPane(panel);
        pack();

        if (getWidth() < 1120 || getHeight() < 600) {
            setSize(1120, 600);
        }

        setMinimumSize(new Dimension(1120, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(false);
    }
}
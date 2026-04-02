package view.facility_login;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.Border;

import controller.TimeInTimeOutController;
import view.RoundedComponents.*;
import view.fonts.Fonts;
import view.front_pages.Dashboard;
import view.front_pages.FilePath;
import view.front_pages.LoginWindow;

public class FacilityLogin extends JFrame implements ActionListener {

	RoundedComboBox<String> dropdownCollection;
	JPanel wCard;
	JPanel nCard;
	public RoundedTextField username;
	public RoundedTextField cardNo;
	
	public JLabel patronIDValue;
	public JTextArea fullNameValue;
	public JTextArea college_campusValue;
	public JLabel patronTypeValue;
	public JLabel timeInValue;
	public JLabel timeOutValue;
	
	String selectedFacility = "iPAD";
	
	public String getSelectedFacility() {
		return selectedFacility;
	}
	
	String patronID = "";
	
	public String getPatronID() {
		return patronID;
	}
	
	String cardNoText = "";
	
	public String getCardNo() {
		return patronID;
	}

	static String imgFilePath = FilePath.getImgFilePath();

	public FacilityLogin(Dashboard frame) {
		int panelRadius = 20;
		JPanel panel = new JPanel() {
			Image backgroundImage = new ImageIcon(imgFilePath + "blurred_bg.jpg").getImage();

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
			}
		};
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbcMain = new GridBagConstraints();
		gbcMain.gridx = 0;
		gbcMain.gridy = 0;
		gbcMain.insets = new Insets(0, 100, 0, 75); // spacing between components

		RoundedPanel loginContainer = new RoundedPanel(30);
		loginContainer.setPreferredSize(new Dimension(350, 400));
		loginContainer.setBackground(new Color(109, 35, 33, 200));
		Border padding = BorderFactory.createEmptyBorder(20, 10, 10, 10);
		loginContainer.setBorder(padding);

		loginContainer.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 0, 10, 0); // spacing between components

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
		JLabel welcomeMsg = new JLabel("Enter ID Number to Login");

		Fonts introRust18 = new Fonts("IntroRust", 18f);
		Font introRust18Style = introRust18.getAppliedFont();
		welcomeMsg.setFont(introRust18Style);

		welcomeMsg.setForeground(Color.WHITE);
		welcomeMsg.setHorizontalAlignment(SwingConstants.CENTER);
		loginContainer.add(welcomeMsg, gbc);

		// username field
		gbc.gridy++;
		
		JPanel inputWrapper = new JPanel();
		
		wCard = addCardInput();
		nCard = addNonCardInput();
		
		nCard.setVisible(false);
		
		inputWrapper.setPreferredSize(new Dimension(300, 50));
		inputWrapper.setOpaque(false);
		
		inputWrapper.add(wCard);
		inputWrapper.add(nCard);
		
		loginContainer.add(inputWrapper, gbc);

		gbc.gridy++;
		
		Fonts poppins = new Fonts("PoppinsBold", 14f);
		Font poppinsStyle = poppins.getAppliedFont();
		
		RoundedButton submitBtn = new RoundedButton("Submit", 20);
		submitBtn.setPreferredSize(new Dimension(150, 30));
		submitBtn.setFont(poppinsStyle);
		submitBtn.setBackground(Color.decode("#5d1513"));
		submitBtn.setForeground(Color.WHITE);
		submitBtn.setFocusPainted(false);
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // unfinished ------------------------------------ insert logic for inserting login/logout here
                // need to display user info or display user not found error
                if (username.getText().equalsIgnoreCase("exit")) {
                    LoginWindow lw = new LoginWindow(frame);
                    lw.setVisible(true);
                    dispose();
                } else {
                    username.setText("");
                    cardNoText = cardNo.getText();
                    patronID = username.getText();
                    checkPatronID();
                }
            }
        });
		loginContainer.add(submitBtn, gbc);
		
		gbc.gridy++;
        JButton loginFacility = new JButton("Register here");
        loginFacility.setBorderPainted(false);
        loginFacility.setContentAreaFilled(false);
        loginFacility.setFocusPainted(false);
        loginFacility.setOpaque(false);
        loginFacility.setForeground(Color.decode("#f8c169"));
        loginFacility.setFont(loginFacility.getFont().deriveFont(Font.PLAIN, 14f));
        loginFacility.addActionListener(e -> {
//            FacilityLogin fl = new FacilityLogin(frame);
//            fl.setVisible(true);
//            dispose();
        });
        loginContainer.add(loginFacility, gbc);

		panel.add(loginContainer, gbcMain);

		gbcMain.gridx++;

		RoundedPanel rightPanel = new RoundedPanel(panelRadius);
		rightPanel.setLayout(new BorderLayout());
		rightPanel.setPreferredSize(new Dimension(650, 400));
		rightPanel.setBackground(new Color(109, 35, 33, 180));

		RoundedPanel facilitySelector = new RoundedPanel(panelRadius);
		facilitySelector.setBackground(Color.decode("#5d1513"));
		facilitySelector.setPreferredSize(new Dimension(650, 80));
		facilitySelector.setLayout(new BorderLayout());
		facilitySelector.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JLabel selectFacility = new JLabel("SELECT FACILITY");
		selectFacility.setForeground(Color.WHITE);

		Fonts introRust = new Fonts("IntroRust", 20f);
		Font introRustStyle = introRust.getAppliedFont();
		selectFacility.setFont(introRustStyle);

		JPanel selectPanel = new JPanel();
		selectPanel.setOpaque(false);
		selectPanel.add(selectFacility);

		facilitySelector.add(selectPanel, BorderLayout.WEST);

		// combobox
		JPanel cmbbxPanel = new JPanel();
		cmbbxPanel.setOpaque(false);

		String[] facilityNames = { 
				"iPad Area", 
				"Individual Study Room", 
				"Entrance",
				"Laptop Section", 
				"PWD Area", 
				"Relaxation Room", 
				"Smart Development Zone" };
		dropdownCollection = new RoundedComboBox<>(facilityNames, 15);
		dropdownCollection.setPlaceholder("none");
		dropdownCollection.setPreferredSize(new Dimension(200, 30));
		dropdownCollection.addActionListener(this);

		JPanel dropdownPanel = new JPanel();
		dropdownPanel.setOpaque(false);
		dropdownPanel.add(dropdownCollection);

		facilitySelector.add(dropdownPanel, BorderLayout.EAST);

		rightPanel.add(facilitySelector, BorderLayout.NORTH);

		JPanel patronInfo = new JPanel();
		patronInfo.setPreferredSize(new Dimension(650, 400));
		patronInfo.setLayout(new GridBagLayout());
		patronInfo.setOpaque(false);
		GridBagConstraints gbcPatInfo = new GridBagConstraints();
		gbcPatInfo.gridx = 0;
		gbcPatInfo.gridy = 0;
		gbcPatInfo.insets = new Insets(8, 8, 8, 8);

		Fonts introRust14 = new Fonts("IntroRust", 12f);
		Font introRustLabel = introRust14.getAppliedFont();

		Fonts poppinsBold22 = new Fonts("PoppinsBold", 22f);
		Font poppinsValue = poppinsBold22.getAppliedFont();

		Fonts poppinsBold14 = new Fonts("PoppinsBold", 15f);
		Font poppinsValueLong = poppinsBold14.getAppliedFont();

		RoundedPanel patronIDPanel = new RoundedPanel(panelRadius);
		patronIDPanel.setLayout(new BorderLayout());
		patronIDPanel.setPreferredSize(new Dimension(280, 70));
		patronIDPanel.setBackground(new Color(203, 203, 203, 140));
		patronIDPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 0));
		JLabel patronIDLabel = new JLabel("Student Number/Employee ID");
		patronIDValue = new JLabel("--");

		patronIDLabel.setFont(introRustLabel);
		patronIDValue.setFont(poppinsValue);

		patronIDPanel.add(patronIDLabel, BorderLayout.NORTH);
		patronIDPanel.add(patronIDValue, BorderLayout.CENTER);

		patronInfo.add(patronIDPanel, gbcPatInfo);

//        start
		gbcPatInfo.gridx = 1;

		RoundedPanel fullNamePanel = new RoundedPanel(panelRadius);
		fullNamePanel.setLayout(new BorderLayout());
		fullNamePanel.setPreferredSize(new Dimension(280, 70));
		fullNamePanel.setBackground(new Color(203, 203, 203, 140));
		fullNamePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 0));
		JLabel fullNameLabel = new JLabel("Full Name");
		fullNameValue = new JTextArea(
			    "--"
			);
		
		fullNameValue.setFont(poppinsValueLong);
		fullNameValue.setLineWrap(true);
		fullNameValue.setWrapStyleWord(true);
		fullNameValue.setEditable(false);
		fullNameValue.setFocusable(false);
		fullNameValue.setOpaque(false);
		fullNameValue.setBorder(null);

		fullNameLabel.setFont(introRustLabel);
		fullNameValue.setFont(poppinsValueLong);

		fullNamePanel.add(fullNameLabel, BorderLayout.NORTH);
		fullNamePanel.add(fullNameValue, BorderLayout.CENTER);

		patronInfo.add(fullNamePanel, gbcPatInfo);

//        end

//      start
		gbcPatInfo.gridx = 0;
		gbcPatInfo.gridy++;

		RoundedPanel college_campusPanel = new RoundedPanel(panelRadius);
		college_campusPanel.setLayout(new BorderLayout());
		college_campusPanel.setPreferredSize(new Dimension(280, 100));
		college_campusPanel.setBackground(new Color(203, 203, 203, 140));
		college_campusPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 0));
		JLabel college_campusLabel = new JLabel("College/Campus");
		college_campusValue = new JTextArea(
			    "--"
			);
		
		college_campusValue.setFont(poppinsValueLong);
		college_campusValue.setLineWrap(true);
		college_campusValue.setWrapStyleWord(true);
		college_campusValue.setEditable(false);
		college_campusValue.setFocusable(false);
		college_campusValue.setOpaque(false);
		college_campusValue.setBorder(null);
		
		college_campusLabel.setFont(introRustLabel);
		college_campusValue.setFont(poppinsValueLong);

		college_campusPanel.add(college_campusLabel, BorderLayout.NORTH);
		college_campusPanel.add(college_campusValue, BorderLayout.CENTER);

		patronInfo.add(college_campusPanel, gbcPatInfo);

//      end
		
//      start
		gbcPatInfo.gridx = 1;

		RoundedPanel patronTypePanel = new RoundedPanel(panelRadius);
		patronTypePanel.setLayout(new BorderLayout());
		patronTypePanel.setPreferredSize(new Dimension(280, 100));
		patronTypePanel.setBackground(new Color(203, 203, 203, 140));
		patronTypePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 0));
		JLabel patronTypeLabel = new JLabel("Patron Category");
		patronTypeValue = new JLabel("--");

		patronTypeLabel.setFont(introRustLabel);
		patronTypeValue.setFont(poppinsValueLong);
		
		JPanel patronTypeValueWrapper = new JPanel();
		patronTypeValueWrapper.setOpaque(false);
		patronTypeValueWrapper.setLayout(new BorderLayout());
		patronTypeValueWrapper.add(patronTypeValue, BorderLayout.NORTH);

		patronTypePanel.add(patronTypeLabel, BorderLayout.NORTH);
		patronTypePanel.add(patronTypeValueWrapper, BorderLayout.CENTER);

		patronInfo.add(patronTypePanel, gbcPatInfo);

//      end
		
//      start
		gbcPatInfo.gridx = 0;
		gbcPatInfo.gridy++;

		RoundedPanel timeInPanel = new RoundedPanel(panelRadius);
		timeInPanel.setLayout(new BorderLayout());
		timeInPanel.setPreferredSize(new Dimension(280, 70));
		timeInPanel.setBackground(new Color(203, 203, 203, 140));
		timeInPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 0));
		JLabel timeInLabel = new JLabel("Time In");
		timeInValue = new JLabel(
			    "--"
			);

		timeInLabel.setFont(introRustLabel);
		timeInValue.setFont(poppinsValue);

		timeInPanel.add(timeInLabel, BorderLayout.NORTH);
		timeInPanel.add(timeInValue, BorderLayout.CENTER);

		patronInfo.add(timeInPanel, gbcPatInfo);

//      end
		
//      start
		gbcPatInfo.gridx = 1;

		RoundedPanel timeOutPanel = new RoundedPanel(panelRadius);
		timeOutPanel.setLayout(new BorderLayout());
		timeOutPanel.setPreferredSize(new Dimension(280, 70));
		timeOutPanel.setBackground(new Color(203, 203, 203, 140));
		timeOutPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 0));
		JLabel timeOutLabel = new JLabel("Time Out");
		timeOutValue = new JLabel(
			    "--"
			);

		timeOutLabel.setFont(introRustLabel);
		timeOutValue.setFont(poppinsValue);

		timeOutPanel.add(timeOutLabel, BorderLayout.NORTH);
		timeOutPanel.add(timeOutValue, BorderLayout.CENTER);

		patronInfo.add(timeOutPanel, gbcPatInfo);

//      end

		rightPanel.add(patronInfo, BorderLayout.CENTER);

		panel.add(rightPanel, gbcMain);

		add(panel);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				requestFocusInWindow();
			}
		});

		addWindowFocusListener(new WindowAdapter() {
			public void windowGainedFocus(WindowEvent e) {
				// Request focus on something else, or a panel
				panel.requestFocusInWindow();
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
		setVisible(true);
	}
	
	public void checkPatronID() {
		TimeInTimeOutController titoc = new TimeInTimeOutController(this);
	}
	
	public JPanel addNonCardInput() {
		JPanel input = new JPanel();
		input.setPreferredSize(new Dimension(300, 30));
		input.setLayout(new BorderLayout());
		username = new RoundedTextField(25, 20);
		username.setPlaceholder("Enter Patron ID");
		input.setOpaque(false);
		
		input.add(username, BorderLayout.CENTER);
		
		return input;
	}
	
	public JPanel addCardInput() {
		JPanel input = new JPanel();
		input.setPreferredSize(new Dimension(300, 30));
		input.setLayout(new BorderLayout());
		username = new RoundedTextField(20, 20);
		username.setPlaceholder("Enter Patron ID");
		cardNo = new RoundedTextField(5, 20);
		cardNo.setPlaceholder("Card #");
		input.setOpaque(false);
		
		input.add(username, BorderLayout.WEST);
		input.add(cardNo, BorderLayout.EAST);
		
		return input;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		wCard.setVisible(false);
		nCard.setVisible(false);
		
		String selectedFacility = (String) dropdownCollection.getSelectedItem();
		
		switch (selectedFacility) {
		case "iPad Area": 
			selectedFacility = "iPAD";
			wCard.setVisible(true);
			break;
		case "Individual Study Room": 
			selectedFacility = "ISR";
			wCard.setVisible(true);
			break;
		case "Entrance": 
			nCard.setVisible(true);
			selectedFacility = "LOGIN";
			break;
		case "Laptop Section": 
			selectedFacility = "LSect";
			nCard.setVisible(true);
			break;
		case "PWD Area": 
			selectedFacility = "PWD";
			nCard.setVisible(true);
			break;
		case "Relaxation Room": 
			selectedFacility = "RelaxRoom";
			wCard.setVisible(true);
			break;
		case "Smart Development Zone": 
			selectedFacility = "SDZ";
			wCard.setVisible(true);
			break;
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + e.getActionCommand());
		}

	}
}

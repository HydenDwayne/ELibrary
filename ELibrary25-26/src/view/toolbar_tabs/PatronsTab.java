package view.toolbar_tabs;

import controller.MainFunctions;
import view.RoundedComponents.*;
import view.fonts.Fonts;
import view.front_pages.FilePath;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import view.modal.*;
import view.modal.books_modal.ReturnBookModal;
import view.modal.filter_modal.FilterByPatronModal;
import view.modal.patron_modal.ViewEmployeeModal;

public class PatronsTab extends JPanel {

	static String imgFilePath = FilePath.getImgFilePath();

	int minColumnWidth = 55;
	int minColumnHeight = 25;

	JPanel tableData;
	MainFunctions comp;

	int columnCount = 0;

	public String searchQuery = "";

	private String campuses = "";
	private String types = "";

	public void setTypes(String types) {
		this.types = types;
	}

	// Campus filters
	private boolean filterMain;
	private boolean filterHagonoy;
	private boolean filterSanRafael;
	private boolean filterBustos;
	private boolean filterMeneses;
	private boolean filterSarmiento;

	// Patron type filters
	private boolean filterEmployee;
	private boolean filterStudent;

	public void setPatronFilters(boolean main, boolean hagonoy, boolean sanRafael, boolean bustos, boolean meneses,
			boolean sarmiento, boolean employee, boolean student) {

		this.filterMain = main;
		this.filterHagonoy = hagonoy;
		this.filterSanRafael = sanRafael;
		this.filterBustos = bustos;
		this.filterMeneses = meneses;
		this.filterSarmiento = sarmiento;
		this.filterEmployee = employee;
		this.filterStudent = student;

		reloadData(searchQuery); // apply filters to table
	}

	// --- getters ---
	public boolean isFilterMain() {
		return filterMain;
	}

	public boolean isFilterHagonoy() {
		return filterHagonoy;
	}

	public boolean isFilterSanRafael() {
		return filterSanRafael;
	}

	public boolean isFilterBustos() {
		return filterBustos;
	}

	public boolean isFilterMeneses() {
		return filterMeneses;
	}

	public boolean isFilterSarmiento() {
		return filterSarmiento;
	}

	public boolean isFilterEmployee() {
		return filterEmployee;
	}

	public boolean isFilterStudent() {
		return filterStudent;
	}

	public PatronsTab() {
		int panelRadius = 20;
		JPanel patronsTab = new JPanel();
		patronsTab.setOpaque(false);
		patronsTab.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = 1;
		gbc.gridx = 1;
		gbc.insets = new Insets(40, 10, 10, 10);

		RoundedPanel patronContainer = new RoundedPanel(panelRadius);
		patronContainer.setPreferredSize(new Dimension(1280, 560));
		patronContainer.setLayout(new BorderLayout());
		patronContainer.setBackground(Color.WHITE);

		// no red part
		JLabel tabLabel = new JLabel("PATRONS");
		tabLabel.setForeground(Color.decode("#6d2321"));

		Fonts introRust22 = new Fonts("IntroRust", 22f);
		Font introRust22Style = introRust22.getAppliedFont();
		tabLabel.setFont(introRust22Style);

		// search + filter container
		JPanel searchFilter = new JPanel();
		searchFilter.setLayout(new BorderLayout());
		searchFilter.setPreferredSize(new Dimension(375, 30));
		searchFilter.setOpaque(false);
		// search item
		JPanel searchContainer = new JPanel();
		searchContainer.setOpaque(false);
		searchContainer.setPreferredSize(new Dimension(275, 20));

		RoundedTextField searchItem = new RoundedTextField(20, panelRadius);
		searchItem.setPlaceholder("Search item");
		searchItem.setBackground(Color.decode("#a6a6a6"));
		searchItem.setForeground(Color.WHITE);
		searchItem.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				searchQuery = searchItem.getText();
				reloadData(searchQuery);
			}

		});

		Fonts poppins10 = new Fonts("Poppins", 10f);
		Font poppins10Style = poppins10.getAppliedFont();
		searchItem.setFont(poppins10Style);

		searchContainer.add(searchItem);

		// sort by
		ImageIcon sortByIcon = new ImageIcon(imgFilePath + "sort_by_icon.png");
		Image sortByImage = sortByIcon.getImage();
		Image scaledImageSortBy = sortByImage.getScaledInstance(30, 15, Image.SCALE_SMOOTH);
		sortByIcon = new ImageIcon(scaledImageSortBy);
		JButton sortByLogo = new JButton(sortByIcon);
		sortByLogo.setBackground(new Color(0, 0, 0, 0));
		sortByLogo.setContentAreaFilled(false);
		sortByLogo.setBorderPainted(false);
		sortByLogo.setFocusPainted(false);
		sortByLogo.setHorizontalAlignment(SwingConstants.CENTER);
		sortByLogo.addActionListener(e -> {
			Window parent = SwingUtilities.getWindowAncestor(this);
			new FilterByPatronModal(parent, this);
		});

		ImageIcon archiveIcon = new ImageIcon(imgFilePath + "archive.png");
		Image archiveImage = archiveIcon.getImage();
		Image scaledImageArchive = archiveImage.getScaledInstance(30, 15, Image.SCALE_SMOOTH);
		archiveIcon = new ImageIcon(scaledImageArchive);
		JButton archiveLogo = new JButton(archiveIcon);
		archiveLogo.setBackground(new Color(0, 0, 0, 0));
		archiveLogo.setContentAreaFilled(false);
		archiveLogo.setBorderPainted(false);
		archiveLogo.setFocusPainted(false);
		archiveLogo.setHorizontalAlignment(SwingConstants.CENTER);

		ImageIcon reloadIcon = new ImageIcon(imgFilePath + "reload.png");
		Image reloadImage = reloadIcon.getImage();
		Image scaledImageReload = reloadImage.getScaledInstance(30, 15, Image.SCALE_SMOOTH);
		reloadIcon = new ImageIcon(scaledImageReload);
		JButton reloadLogo = new JButton(reloadIcon);
		reloadLogo.setBackground(new Color(0, 0, 0, 0));
		reloadLogo.setContentAreaFilled(false);
		reloadLogo.setBorderPainted(false);
		reloadLogo.setFocusPainted(false);
		reloadLogo.setHorizontalAlignment(SwingConstants.CENTER);

		reloadLogo.addActionListener(e -> {
			reloadData(searchQuery);
		});

		JPanel iconsPanel = new JPanel();
		iconsPanel.setOpaque(false);
		iconsPanel.setLayout(new GridLayout(1, 3));
		iconsPanel.setPreferredSize(new Dimension(100, 30));

		iconsPanel.add(sortByLogo);
		iconsPanel.add(archiveLogo);
		iconsPanel.add(reloadLogo);

		// add book button
		JPanel outerBtnCont = new JPanel();
		outerBtnCont.setLayout(new BorderLayout());
		outerBtnCont.setOpaque(false);

		Fonts poppinsBtn = new Fonts("Poppins", 10f);
		Font poppinsStyle10 = poppinsBtn.getAppliedFont();

		RoundedButton addPatron = new RoundedButton("Add Patron", panelRadius);
		addPatron.setFont(poppinsStyle10);
		addPatron.setBackground(Color.decode("#842b28"));
		addPatron.setForeground(Color.WHITE);
		addPatron.setPreferredSize(new Dimension(110, 30));
		

		outerBtnCont.add(addPatron, BorderLayout.EAST);
		outerBtnCont.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 25));

		// right side of the top wrapper
		JPanel topRight = new JPanel();
		topRight.setOpaque(false);
		topRight.setPreferredSize(new Dimension(390, 50));
		topRight.setLayout(new BorderLayout());

		iconsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 25));

		searchFilter.add(searchContainer, BorderLayout.WEST);
		searchFilter.add(iconsPanel, BorderLayout.EAST);

		// container of the red part
		JPanel topWrapper = new JPanel();
		topWrapper.setLayout(new BorderLayout());
		topWrapper.setPreferredSize(new Dimension(1280, 90));
		topWrapper.setBorder(BorderFactory.createEmptyBorder(20, 50, 0, 40));
		topWrapper.setOpaque(false);

		JPanel northPanel = new JPanel();
		northPanel.setOpaque(false);
		northPanel.setPreferredSize(new Dimension(1200, 30));
		northPanel.setLayout(new BorderLayout());
		northPanel.add(tabLabel, BorderLayout.WEST);
		northPanel.add(searchFilter, BorderLayout.EAST);

		JPanel southPanel = new JPanel();
		southPanel.setOpaque(false);
		southPanel.setPreferredSize(new Dimension(1200, 30));
		southPanel.setLayout(new BorderLayout());
		southPanel.add(outerBtnCont, BorderLayout.EAST);

		topWrapper.add(northPanel, BorderLayout.NORTH);
		topWrapper.add(southPanel, BorderLayout.SOUTH);

		patronContainer.add(topWrapper, BorderLayout.NORTH);

		// selected patrons items
		JPanel slctdPatrons = new JPanel();
		slctdPatrons.setOpaque(false);

// Create labels
		JPanel tableHeader = new JPanel();
		slctdPatrons.setLayout(new BorderLayout());
		tableHeader.setLayout(new GridBagLayout());
		JLabel idNumber = new JLabel("ID Number");
		JLabel nameOfPatron = new JLabel("Name of Patron");
		JLabel emailAddress = new JLabel("Email Address");
		JLabel contactNo = new JLabel("Contact No.");
		JLabel campus = new JLabel("Campus");
		JLabel homeAddress = new JLabel("Home Address");
		JLabel patronType = new JLabel("Category");
		JLabel action = new JLabel("Action");

		Fonts poppins12 = new Fonts("Poppins", 12f);
		Font poppins12Style = poppins12.getAppliedFont();

		idNumber.setFont(poppins12Style);
		nameOfPatron.setFont(poppins12Style);
		emailAddress.setFont(poppins12Style);
		contactNo.setFont(poppins12Style);
		campus.setFont(poppins12Style);
		homeAddress.setFont(poppins12Style);
		patronType.setFont(poppins12Style);
		action.setFont(poppins12Style);

		idNumber.setForeground(Color.decode("#737373"));
		nameOfPatron.setForeground(Color.decode("#737373"));
		emailAddress.setForeground(Color.decode("#737373"));
		contactNo.setForeground(Color.decode("#737373"));
		campus.setForeground(Color.decode("#737373"));
		homeAddress.setForeground(Color.decode("#737373"));
		patronType.setForeground(Color.decode("#737373"));
		action.setForeground(Color.decode("#737373"));

		JPanel idNumberPanel = new JPanel();
		JPanel nameOfPatronPanel = new JPanel();
		JPanel emailAddressPanel = new JPanel();
		JPanel contactNoPanel = new JPanel();
		JPanel campusPanel = new JPanel();
		JPanel homeAddressPanel = new JPanel();
		JPanel patronTypePanel = new JPanel();
		JPanel actionPanel = new JPanel();

		idNumberPanel.add(idNumber);
		nameOfPatronPanel.add(nameOfPatron);
		emailAddressPanel.add(emailAddress);
		contactNoPanel.add(contactNo);
		campusPanel.add(campus);
		homeAddressPanel.add(homeAddress);
		patronTypePanel.add(patronType);
		actionPanel.add(action);

		idNumberPanel.setOpaque(false);
		nameOfPatronPanel.setOpaque(false);
		emailAddressPanel.setOpaque(false);
		contactNoPanel.setOpaque(false);
		campusPanel.setOpaque(false);
		homeAddressPanel.setOpaque(false);
		patronTypePanel.setOpaque(false);
		actionPanel.setOpaque(false);

		idNumberPanel.setPreferredSize(new Dimension(minColumnWidth + 20, minColumnHeight));
		nameOfPatronPanel.setPreferredSize(new Dimension(minColumnWidth * 4, minColumnHeight));
		emailAddressPanel.setPreferredSize(new Dimension(minColumnWidth * 4, minColumnHeight));
		contactNoPanel.setPreferredSize(new Dimension(minColumnWidth + 25, minColumnHeight));
		campusPanel.setPreferredSize(new Dimension(minColumnWidth, minColumnHeight));
		homeAddressPanel.setPreferredSize(new Dimension(minColumnWidth * 3, minColumnHeight));
		patronTypePanel.setPreferredSize(new Dimension(minColumnWidth + 20, minColumnHeight));
		actionPanel.setPreferredSize(new Dimension(minColumnWidth + 37, minColumnHeight));

		GridBagConstraints gbcPat = new GridBagConstraints();
		gbcPat.gridy = 0;
		gbcPat.gridx = 0;
		gbcPat.insets = new Insets(10, 10, 10, 10);

		gbcPat.gridy = 1;
		gbcPat.gridx = 1;
		tableHeader.add(idNumberPanel, gbcPat);

		gbcPat.gridx = 2;
		tableHeader.add(nameOfPatronPanel, gbcPat);

		gbcPat.gridx = 3;
		tableHeader.add(emailAddressPanel, gbcPat);

		gbcPat.gridx = 4;
		tableHeader.add(contactNoPanel, gbcPat);

		gbcPat.gridx = 5;
		tableHeader.add(campusPanel, gbcPat);

		gbcPat.gridx = 6;
		tableHeader.add(homeAddressPanel, gbcPat);

		gbcPat.gridx = 7;
		tableHeader.add(patronTypePanel, gbcPat);

		gbcPat.gridx = 8;
		tableHeader.add(actionPanel, gbcPat);

		tableHeader.setOpaque(false);
		tableHeader.setPreferredSize(new Dimension(1240, 50));

		slctdPatrons.add(tableHeader, BorderLayout.NORTH);

		// actual table data ============================
		tableData = new JPanel();
		tableData.setOpaque(false);

		reloadData(searchQuery);

		tableData.setLayout(new BorderLayout());
		tableData.add(comp, BorderLayout.NORTH);

		JScrollPane scrollBar = new JScrollPane(tableData);
		scrollBar.setOpaque(false);
		scrollBar.setPreferredSize(new Dimension(1240, 400));
		scrollBar.setBorder(BorderFactory.createEmptyBorder(0, 65, 40, 65));
		scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollBar.getVerticalScrollBar().setPreferredSize(new Dimension(8, Integer.MAX_VALUE));
		scrollBar.getVerticalScrollBar().setUI(new RoundedScrollbar());

		slctdPatrons.add(scrollBar, BorderLayout.CENTER);

		patronContainer.add(slctdPatrons, BorderLayout.CENTER);

		// ==============================================
		patronsTab.add(patronContainer, gbc);
		setBackground(Color.decode("#c4c4c4"));
		add(patronsTab);
	}

	public int getMinColumnWidth() {
		return minColumnWidth;
	}

	public int getMinColumnHeight() {
		return minColumnHeight;
	}

	public void reloadData(String searchQuery) {
		concatCampuses();
		concatTypes();
		tableData.removeAll();
		comp = new MainFunctions(this, searchQuery, campuses, types);
		tableData.add(comp, BorderLayout.NORTH);

		revalidate();
		repaint();
	}

	public void concatCampuses() {

		try {
			campuses = "";

			if (filterMain) {
				campuses += ",M";
			}
			if (filterHagonoy) {
				campuses += ",HC";
			}
			if (filterSanRafael) {
				campuses += ",SRC";
			}
			if (filterBustos) {
				campuses += ",BC";
			}
			if (filterMeneses) {
				campuses += ",MC";
			}
			if (filterSarmiento) {
				campuses += ",SC";
			}

			campuses = campuses.substring(1);

		} catch (Exception e) {
			return;
		}

//		if (filterStudent) {
//			campuses += ",M";
//		}
//		if (filterEmployee) {
//			campuses += ",M";
//		}
	}

	public void concatTypes() {

		try {
			types = "";

			if (filterEmployee) {
				types += ",EMPLOYEE";
			}
			if (filterStudent) {
				types += ",STUDENT";
			}

			types = types.substring(1);

		} catch (Exception e) {
			return;
		}
	}
}

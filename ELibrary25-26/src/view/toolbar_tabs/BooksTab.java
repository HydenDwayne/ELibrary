package view.toolbar_tabs;

import view.RoundedComponents.*;
import view.book_panels.*;
import view.fonts.Fonts;
import view.front_pages.FilePath;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.MainFunctions;
import view.modal.*;
import view.modal.books_modal.*;
import view.modal.filter_modal.*;
import view.report_panels.Modals.FacilityModal;

public class BooksTab extends JPanel implements ActionListener {

    RoundedComboBox<String> dropdownCollection;
    Bulacaniana bulacaniana = new Bulacaniana();
    Fiction fiction = new Fiction();
    Filipiniana filipiniana = new Filipiniana();
    GeneralCirculation gencirc = new GeneralCirculation();
    Reference reference = new Reference();
    Reserve reserve = new Reserve();
    ThesesAndDissertations tad = new ThesesAndDissertations();
    JLabel tabLabel = new JLabel("");
    
    private String filterClass = "";
    private String filterStartYear ="";
    private String filterEndYear = "";

	public void setAllFilter(String filterClass, String filterStartYear, String filterEndYear) {
		this.filterClass = filterClass;
		this.filterStartYear = filterStartYear;
		this.filterEndYear = filterEndYear;
		setFilter();
	}
	
	public String getFilterClass() {
	    return filterClass;
	}

	public String getFilterStartYear() {
	    return filterStartYear;
	}

	public String getFilterEndYear() {
	    return filterEndYear;
	}

	public JLabel getTabLabel() {
        return tabLabel;
        
    }
    
    String searchQuery = "";

    public BooksTab() {
        int panelRadius = 20;
        JPanel booksTab = new JPanel();
        booksTab.setOpaque(false);
        booksTab.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.insets = new Insets(40, 10, 10, 10);

        RoundedPanel bookContainer = new RoundedPanel(panelRadius);
        bookContainer.setPreferredSize(new Dimension(1280, 560));
        bookContainer.setLayout(new BorderLayout());
        bookContainer.setBackground(Color.WHITE);

        RoundedPanel slctBooks = new RoundedPanel(panelRadius);
        slctBooks.setLayout(new GridBagLayout());
        GridBagConstraints gbcRed = new GridBagConstraints();
        gbcRed.gridy = 1;
        gbcRed.gridx = 1;
        gbcRed.insets = new Insets(10, 30, 10, 10);
        slctBooks.setBackground(Color.decode("#842b28"));
        slctBooks.setPreferredSize(new Dimension(500, 80));

        JLabel selectCollection = new JLabel("SELECT COLLECTION");
        selectCollection.setForeground(Color.WHITE);


        Fonts introRust = new Fonts("IntroRust", 18f);
        Font introRustStyle = introRust.getAppliedFont();
        selectCollection.setFont(introRustStyle);
        slctBooks.add(selectCollection, gbcRed);


        JPanel cmbbxPanel = new JPanel();
        cmbbxPanel.setOpaque(false);

        String[] facilityItems = {
            "Bulacaniana Collection",
            "General Circulation Section",
            "Fiction Collection",
            "Filipiniana Collection",
            "Reference Collection",
            "Reserve Collection",
            "Theses and Dissertations"
        };
        dropdownCollection = new RoundedComboBox<>(facilityItems, panelRadius);
        dropdownCollection.setPlaceholder("none");
        dropdownCollection.setPreferredSize(new Dimension(200, 30));
        dropdownCollection.addActionListener(this);

        gbcRed.gridx++;

        cmbbxPanel.add(dropdownCollection);
        slctBooks.add(cmbbxPanel, gbcRed);

        JPanel topWrapper = new JPanel();
        topWrapper.setLayout(new BorderLayout());
        topWrapper.setOpaque(false);
        topWrapper.add(slctBooks, BorderLayout.WEST);

        tabLabel.setText("4th | Bulacaniana");
        tabLabel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        tabLabel.setForeground(Color.decode("#6d2321"));

        Fonts introRust20 = new Fonts("IntroRust", 20f);
        Font introRust20Style = introRust20.getAppliedFont();
        tabLabel.setFont(introRust20Style);

        JPanel searchFilter = new JPanel();
        searchFilter.setLayout(new BorderLayout());
        searchFilter.setPreferredSize(new Dimension(275, 30));
        searchFilter.setOpaque(false);

        JPanel searchContainer = new JPanel();
        searchContainer.setOpaque(false);
        searchContainer.setPreferredSize(new Dimension(275, 20));

        RoundedTextField searchItem = new RoundedTextField(19, panelRadius);
        searchItem.setPlaceholder("Search item");
        searchItem.setBackground(Color.decode("#a6a6a6"));
        searchItem.setForeground(Color.WHITE);
        
        searchItem.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				searchQuery = searchItem.getText();
				reloadCurrentCollection();
			}

		});

        Fonts poppins12 = new Fonts("Poppins", 10f);
        Font poppins12Style = poppins12.getAppliedFont();
        searchItem.setFont(poppins12Style);

        searchContainer.add(searchItem);

        ImageIcon sortByIcon = new ImageIcon(FilePath.image("sort_by_icon.png"));
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
		    new FilterByModal(parent, this);
		});
        
        ImageIcon archiveIcon = new ImageIcon(FilePath.image("archive.png"));
        Image archiveImage = archiveIcon.getImage();
        Image scaledImageArchive = archiveImage.getScaledInstance(30, 15, Image.SCALE_SMOOTH);
        archiveIcon = new ImageIcon(scaledImageArchive);
        JButton archiveLogo = new JButton(archiveIcon);
        archiveLogo.setBackground(new Color(0, 0, 0, 0));
        archiveLogo.setContentAreaFilled(false);
        archiveLogo.setBorderPainted(false);
        archiveLogo.setFocusPainted(false);
        archiveLogo.setHorizontalAlignment(SwingConstants.CENTER);
        archiveLogo.addActionListener(e -> {
        	Window parent = SwingUtilities.getWindowAncestor(this);
	        new FacilityModal(parent, "books");
	        reloadCurrentCollection();
        });
        
        ImageIcon reloadIcon = new ImageIcon(FilePath.image("reload.png"));
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
        	reloadCurrentCollection();
        });
        
        JPanel iconsPanel = new JPanel();
        iconsPanel.setOpaque(false);
        iconsPanel.setLayout(new GridLayout(1, 3));
        iconsPanel.setPreferredSize(new Dimension(100,30));
        
        iconsPanel.add(sortByLogo);
        iconsPanel.add(archiveLogo);
        iconsPanel.add(reloadLogo);
        
        JPanel outerBtnCont = new JPanel();
        outerBtnCont.setLayout(new FlowLayout());
        outerBtnCont.setOpaque(false);
        
        JPanel innerBtn1Cont = new JPanel();
        innerBtn1Cont.setOpaque(false);
        innerBtn1Cont.setPreferredSize(new Dimension(100, 40));
        
        JPanel innerBtn2Cont = new JPanel();
        innerBtn2Cont.setOpaque(false);
        innerBtn2Cont.setPreferredSize(new Dimension(100, 40));
        
        JPanel innerBtn3Cont = new JPanel();
        innerBtn3Cont.setOpaque(false);
        innerBtn3Cont.setPreferredSize(new Dimension(100, 40));
        outerBtnCont.setPreferredSize(new Dimension(180, 40));
        
        Fonts poppins = new Fonts("Poppins", 10f);
        Font poppinsStyle = poppins.getAppliedFont();
        
        RoundedButton addBook = new RoundedButton("Add Book",panelRadius);
        addBook.setFont(poppinsStyle);
        addBook.setBackground(Color.decode("#842b28"));
        addBook.setForeground(Color.WHITE);
        addBook.setPreferredSize(new Dimension(100, 30) );
        innerBtn1Cont.add(addBook);
        
        addBook.addActionListener(e -> {
        	Window parent = SwingUtilities.getWindowAncestor(this);
			new AddBookModal(parent);
			reloadCurrentCollection();
        });
        
        outerBtnCont.add(innerBtn1Cont);
        
        RoundedButton lendBook = new RoundedButton("Lend Book",panelRadius);
        lendBook.setFont(poppinsStyle);
        lendBook.setBackground(Color.decode("#842b28"));
        lendBook.setForeground(Color.WHITE);
        lendBook.setPreferredSize(new Dimension(100, 30) );
        lendBook.addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(this);
            new BorrowBookModal(window);
            reloadCurrentCollection();
        });
        innerBtn2Cont.add(lendBook);
        
        outerBtnCont.add(innerBtn2Cont);
        
        RoundedButton returnBook = new RoundedButton("Return Book",panelRadius);
        returnBook.setFont(poppinsStyle);
        returnBook.setBackground(Color.decode("#842b28"));
        returnBook.setForeground(Color.WHITE);
        returnBook.setPreferredSize(new Dimension(100, 30) );
		returnBook.addActionListener(e -> {
		    Window parent = SwingUtilities.getWindowAncestor(this);
		    new ReturnBookModal(parent);
		    reloadCurrentCollection();
		});

        innerBtn3Cont.add(returnBook);
        
        outerBtnCont.add(innerBtn3Cont);

        JPanel topRight = new JPanel();
        topRight.setOpaque(false);
        topRight.setPreferredSize(new Dimension(390, 50));
        topRight.setLayout(new BorderLayout());
        
        
        searchFilter.add(searchContainer, BorderLayout.WEST);
        searchFilter.add(iconsPanel, BorderLayout.EAST);
        
        searchFilter.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 30));
        
        topRight.add(searchFilter, BorderLayout.NORTH);
        topRight.add(outerBtnCont, BorderLayout.SOUTH);

        JPanel rightWrapper = new JPanel();
        rightWrapper.setLayout(new BorderLayout());
        rightWrapper.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 40));
        rightWrapper.setOpaque(false);
        rightWrapper.add(topRight, BorderLayout.EAST);

        topWrapper.add(rightWrapper, BorderLayout.EAST);


        topWrapper.add(tabLabel, BorderLayout.CENTER);

        bookContainer.add(topWrapper, BorderLayout.NORTH);

        JPanel slctdPadding = new JPanel();
        slctdPadding.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        slctdPadding.setOpaque(false);
        JPanel slctdBookCol = new JPanel();
        slctdBookCol.setPreferredSize(new Dimension(1200, 435));
        slctdBookCol.setBackground(Color.GRAY);

        slctdPadding.add(slctdBookCol, BorderLayout.CENTER);
        bookContainer.add(slctdPadding, BorderLayout.CENTER);

        slctdBookCol.add(bulacaniana);
        slctdBookCol.add(fiction);
        slctdBookCol.add(filipiniana);
        slctdBookCol.add(gencirc);
        slctdBookCol.add(reference);
        slctdBookCol.add(reserve);
        slctdBookCol.add(tad);

        bulacaniana.reloadData(searchQuery);
        
        bulacaniana.setVisible(true); 
        fiction.setVisible(false);
        filipiniana.setVisible(false); 
        gencirc.setVisible(false);
        reference .setVisible(false);
        reserve.setVisible(false);
        tad.setVisible(false);
        
        slctdBookCol.setOpaque(false);
        booksTab.add(bookContainer, gbc);
        setBackground(Color.decode("#c4c4c4"));
        add(booksTab);

    }
    
    private void reloadCurrentCollection() {

        int selectedBookCol = dropdownCollection.getSelectedIndex();

        switch (selectedBookCol) {

        case 0:
            bulacaniana.reloadData(searchQuery);
            break;

        case 1:
            gencirc.reloadData(searchQuery);
            break;

        case 2:
            fiction.reloadData(searchQuery);
            break;

        case 3:
            filipiniana.reloadData(searchQuery);
            break;

        case 4:
            reference.reloadData(searchQuery);
            break;

        case 5:
            reserve.reloadData(searchQuery);
            break;

        case 6:
            tad.reloadData(searchQuery);
            break;

        default:
            System.out.println("Nothing to reload");
            break;
    }
    }
    
    public void setFilter() {
    	bulacaniana.setFilterStartYear(filterStartYear);
        bulacaniana.setFilterEndYear(filterEndYear);
        bulacaniana.setFilterClass(filterClass);
    	
        fiction.setFilterStartYear(filterStartYear);
        fiction.setFilterEndYear(filterEndYear);
        fiction.setFilterClass(filterClass);
        
        filipiniana.setFilterStartYear(filterStartYear);
        filipiniana.setFilterEndYear(filterEndYear);
        filipiniana.setFilterClass(filterClass);
        
        gencirc.setFilterStartYear(filterStartYear);
        gencirc.setFilterEndYear(filterEndYear);
        gencirc.setFilterClass(filterClass);
        
        reference.setFilterStartYear(filterStartYear);
        reference.setFilterEndYear(filterEndYear);
        reference.setFilterClass(filterClass);
        
        reserve.setFilterStartYear(filterStartYear);
        reserve.setFilterEndYear(filterEndYear);
        reserve.setFilterClass(filterClass);
        
        tad.setFilterStartYear(filterStartYear);
        tad.setFilterEndYear(filterEndYear);
        tad.setFilterClass(filterClass);
        
        tad.reloadData(searchQuery);
        reserve.reloadData(searchQuery);
        reference.reloadData(searchQuery);
        filipiniana.reloadData(searchQuery);
        fiction.reloadData(searchQuery);
        gencirc.reloadData(searchQuery);
        bulacaniana.reloadData(searchQuery);
       
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        bulacaniana.setVisible(false); 
        fiction.setVisible(false);
        filipiniana.setVisible(false); 
        gencirc.setVisible(false);
        reference .setVisible(false);
        reserve.setVisible(false);
        tad.setVisible(false);

        String selectedBookCol = (String) dropdownCollection.getSelectedItem();

        switch (selectedBookCol) {
            case "Bulacaniana Collection":
                tabLabel.setText("4th | Bulacaniana");
                bulacaniana.setVisible(true);
                bulacaniana.reloadData(searchQuery);
                break;
            case "Fiction Collection":
                tabLabel.setText("4th | Fiction Collection");
                fiction.setVisible(true);
                fiction.reloadData(searchQuery);
                break;
            case "Filipiniana Collection":
                tabLabel.setText("4th | Filipiniana Collection");
                filipiniana.setVisible(true);
                filipiniana.reloadData(searchQuery);
                break;
            case "General Circulation Section":
                tabLabel.setText("2nd | General Circulation");
                gencirc.setVisible(true);
                gencirc.reloadData(searchQuery);
                break;
            case "Reference Collection":
                tabLabel.setText("4th | Reference Collection");
                reference.setVisible(true);
                reference.reloadData(searchQuery);
                break;
            case "Reserve Collection":
                tabLabel.setText("4th | Reserve Collection");
                reserve.setVisible(true);
                reserve.reloadData(searchQuery);
                break;
            case "Theses and Dissertations":
                tabLabel.setText("3rd | Theses & Dissertation");
                tad.setVisible(true);
                tad.reloadData(searchQuery);
                break;
            default:
                throw new AssertionError("Unknown facility selected: " + selectedBookCol);
        }
    }
}

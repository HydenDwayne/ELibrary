package controller;

import model.DAOs.Book.*;
import model.DAOs.FunctionHall.FuncHallDAOImp;
import model.DAOs.IMS.*;
import model.DAOs.LostAndFound.*;
import model.DAOs.ActiveRequest.*;
import model.DAOs.Overview.DAOOverview;
import model.DAOs.Overview.OverviewDAOImp;
import model.DAOs.Patron.*;
import view.RoundedComponents.*;
import view.*;
import view.book_panels.*;
import view.facility_panels.*;
import view.fonts.Fonts;
import view.toolbar_tabs.*;
import view.modal.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.*;

public class MainFunctions extends JPanel {
	
	static String imgFilePath = FilePath.getImgFilePath();

    private PatronDAOImp daoPatron = new PatronDAOImp();        //model
    private IMSDAOImp daoIMS = new IMSDAOImp();        //model
    private ActiveRequestDAOImp daoAR = new ActiveRequestDAOImp();
    private BookDAOImp daoBook = new BookDAOImp();        //model
    private OverviewDAOImp daoOverview = new OverviewDAOImp();        //model
    private PatronsTab patTab;                            //view
    private IMSTab imsTab;                            //view
    private Bulacaniana bulacanianaTab;                            //view
    private Fiction fictionTab;                            //view
    private Filipiniana filipinianaTab;                            //view
    private GeneralCirculation genSecTab;                            //view
    private Reference referenceTab;                            //view
    private Reserve reserveTab;                            //view
    private ThesesAndDissertations TaDTab;                            //view
    private OverviewTab ovTab;                            //view
    private LNFTab lnfTab;
    private LNFDAOImp daoLNF = new LNFDAOImp();
    private AddBookModal addBookModal;
    private ViewPatronModal viewPatronModal;
    
    public MainFunctions(ViewPatronModal viewPatronModal) {
    	this.viewPatronModal = viewPatronModal;
    	showPatronDetails();
    }
    
    public MainFunctions(AddBookModal addBookModal) {
    	this.addBookModal = addBookModal;
    	insertOneToBook();
    }
 
    public MainFunctions(OverviewTab ovTab) {
        this.ovTab = ovTab;
        showOverviewData();
    }

    public MainFunctions(PatronsTab patTab) {
        this.patTab = patTab;
        showPatrons();
    }
    
    public MainFunctions(LNFTab lnfTab) {
        this.lnfTab = lnfTab;
        showLNF();
    }

    public MainFunctions(IMSTab imsTab, String element) {
        this.imsTab = imsTab;
        if (element.equals("cards")) {
        	showActiveRequest();
        } else if (element.equals("rows")) {
        	showIMS();
        } else {
        	System.out.println("error at IMSTab MainFunction");
        }
        
        
    }

    public MainFunctions(Bulacaniana bulacanianaTab) {
        this.bulacanianaTab = bulacanianaTab;
        showBulacanianaBooks();
    }

    public MainFunctions(Fiction fictionTab) {
        this.fictionTab = fictionTab;
        showFictionBooks();
    }

    public MainFunctions(Filipiniana filipinianaTab) {
        this.filipinianaTab = filipinianaTab;
        showFilipinianaBooks();
    }

    public MainFunctions(GeneralCirculation genSecTab) {
        this.genSecTab = genSecTab;
        showGenSecBooks();
    }

    public MainFunctions(Reference referenceTab) {
        this.referenceTab = referenceTab;
        showReferenceBooks();
    }

    public MainFunctions(Reserve reserveTab) {
        this.reserveTab = reserveTab;
        showReserveBooks();
    }

    public MainFunctions(ThesesAndDissertations TaDTab) {
        this.TaDTab = TaDTab;
        showThesisAndDissertationBooks();
    }
    
    public void showPatronDetails() {
    	String callNumber = addBookModal.getCallNumberStr();
    	String title = addBookModal.getTitleStr();
    	String author = addBookModal.getAuthorStr();
    	String year = addBookModal.getYearStr();
    	String bookType = addBookModal.getBookTypeStr();
    	String collection = addBookModal.getCollectionStr();
    	String classification = addBookModal.getClassificationStr().substring(0,1);
    	
    	
    	
    	
    	
    	daoBook.insertOne(callNumber,title, author, year, bookType, collection, classification);
    }
    
    public void insertOneToBook() {
    	String callNumber = addBookModal.getCallNumberStr();
    	String title = addBookModal.getTitleStr();
    	String author = addBookModal.getAuthorStr();
    	String year = addBookModal.getYearStr();
    	String bookType = addBookModal.getBookTypeStr();
    	String collection = addBookModal.getCollectionStr();
    	String classification = addBookModal.getClassificationStr().substring(0,1);
    	
    	
    	
    	
    	
    	daoBook.insertOne(callNumber,title, author, year, bookType, collection, classification);
    }
    

    public void showOverviewData() {
        List<DAOOverview> overviewData = daoOverview.getAllUsers();


        for(DAOOverview ovData : overviewData) {
            ovTab.activeBooksData.setText(ovData.getActiveBooks()+"");
            ovTab.borrowedBooksData.setText(ovData.getBorrowedBooks()+"");
            ovTab.overdueBooksData.setText(ovData.getOverdueBooks()+"");
            ovTab.activePatronsData.setText(ovData.getActivePatrons()+"");
        }
    }

    public void showPatrons() {
        List<DAOPatron> patrons = daoPatron.getAllUsers();

        // loadPatrons();
        int minColumnHeight = patTab.getMinColumnHeight();
        int minColumnWidth = patTab.getMinColumnWidth();

        // int columnCount = patTab.getColumnCount();
        setOpaque(false);
        setLayout(new GridBagLayout());

        GridBagConstraints gbcRow = new GridBagConstraints();
        gbcRow.gridy = 1;
        gbcRow.gridx = 1;
        gbcRow.insets = new Insets(10, 10, 10, 10);

        for (DAOPatron patron : patrons) {

            JLabel col1 = new JLabel("");
            JLabel col2 = new JLabel("");
            JLabel col3 = new JLabel("");
            JLabel col4 = new JLabel("");
            JLabel col5 = new JLabel("");
            JLabel col6 = new JLabel("");
            JLabel col7 = new JLabel("");

            col1.setText(patron.getPatronID());
            col2.setText(patron.getFullName());
            col3.setText(patron.getEmailAddress());
            col4.setText(patron.getContactNumber());
            col5.setText(patron.getCampus());
            col6.setText(patron.getHomeAddress());
            col7.setText(patron.getPatronType());

            Fonts poppins = new Fonts("Poppins", 10f);
            Font poppinsStyle = poppins.getAppliedFont();

            col1.setFont(poppinsStyle);
            col2.setFont(poppinsStyle);
            col3.setFont(poppinsStyle);
            col4.setFont(poppinsStyle);
            col5.setFont(poppinsStyle);
            col6.setFont(poppinsStyle);
            col7.setFont(poppinsStyle);

            col1.setForeground(Color.BLACK);
            col2.setForeground(Color.BLACK);
            col3.setForeground(Color.BLACK);
            col4.setForeground(Color.BLACK);
            col5.setForeground(Color.BLACK);
            col6.setForeground(Color.BLACK);
            col7.setForeground(Color.BLACK);

            JPanel col1Panel = new JPanel();
            JPanel col2Panel = new JPanel();
            JPanel col3Panel = new JPanel();
            JPanel col4Panel = new JPanel();
            JPanel col5Panel = new JPanel();
            JPanel col6Panel = new JPanel();
            JPanel col7Panel = new JPanel();
            JPanel col8Panel = new JPanel();

            RoundedButton col8 = new RoundedButton("Edit", 12);
            col8.setPreferredSize(new Dimension(minColumnWidth, minColumnHeight - 5));
            col8.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            col8.setBackground(Color.decode("#842b28"));
            col8.setForeground(Color.WHITE);
            col8.setFont(poppinsStyle);
            
            col8.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(col1.getText());
					System.out.println(col2.getText());
					System.out.println(col3.getText());
					System.out.println(col4.getText());
					System.out.println(col5.getText());
					System.out.println(col6.getText());
					System.out.println(col7.getText());
				}
			});

            col1Panel.add(col1);
            col2Panel.add(col2);
            col3Panel.add(col3);
            col4Panel.add(col4);
            col5Panel.add(col5);
            col6Panel.add(col6);
            col7Panel.add(col7);
            col8Panel.add(col8);

            col1Panel.setOpaque(false);
            col2Panel.setOpaque(false);
            col3Panel.setOpaque(false);
            col4Panel.setOpaque(false);
            col5Panel.setOpaque(false);
            col6Panel.setOpaque(false);
            col7Panel.setOpaque(false);

            col1Panel.setPreferredSize(new Dimension(minColumnWidth + 20, minColumnHeight));
            col2Panel.setPreferredSize(new Dimension(minColumnWidth * 4, minColumnHeight));
            col3Panel.setPreferredSize(new Dimension(minColumnWidth * 4, minColumnHeight));
            col4Panel.setPreferredSize(new Dimension(minColumnWidth + 25, minColumnHeight));
            col5Panel.setPreferredSize(new Dimension(minColumnWidth, minColumnHeight));
            col6Panel.setPreferredSize(new Dimension(minColumnWidth * 3, minColumnHeight));
            col7Panel.setPreferredSize(new Dimension(minColumnWidth + 20, minColumnHeight));
            col8Panel.setPreferredSize(new Dimension(minColumnWidth + 37, minColumnHeight));

            gbcRow.gridx = 1;
            add(col1Panel, gbcRow);

            gbcRow.gridx = 2;
            add(col2Panel, gbcRow);

            gbcRow.gridx = 3;
            add(col3Panel, gbcRow);

            gbcRow.gridx = 4;
            add(col4Panel, gbcRow);

            gbcRow.gridx = 5;
            add(col5Panel, gbcRow);

            gbcRow.gridx = 6;
            add(col6Panel, gbcRow);

            gbcRow.gridx = 7;
            add(col7Panel, gbcRow);

            gbcRow.gridx = 8;
            add(col8Panel, gbcRow);

            gbcRow.gridy++;

        }

    }
    
    public void showLNF() {
    	List<DAOLNF> lnf = daoLNF.getAllUsers();

    	Fonts poppins12 = new Fonts("Poppins", 10f);
        Font poppins12Style = poppins12.getAppliedFont();
        
        // loadPatrons();
        int minColumnHeight = lnfTab.getMinColumnHeight();
        int minColumnWidth = lnfTab.getMinColumnWidth();
        
        setOpaque(false);
        setLayout(new GridBagLayout());

        GridBagConstraints gbcRow = new GridBagConstraints();
        gbcRow.gridy = 1;
        gbcRow.gridx = 1;
        gbcRow.insets = new Insets(10, 10, 10, 10);

        for (DAOLNF item : lnf) {

        	JLabel col1 = new JLabel("");
            JLabel col2 = new JLabel("");
            JLabel col3 = new JLabel("");
            JLabel col4 = new JLabel("");
            JLabel col5 = new JLabel("");
            JLabel col6 = new JLabel("");

            
            col1 = new JLabel(item.getItemLost());
            
            if (item.getNameOfOwner() == null) {
            	col2.setText("--");
            } else {
            	col2 = new JLabel(item.getNameOfOwner());
            }
            
            
            
            col3 = new JLabel(item.getItemDescription());
            col4 = new JLabel(item.getLostOnFloor());
            col5 = new JLabel(item.getStatus());
            col6 = new JLabel(item.getLastSeen());
            //     JLabel col7 = new JLabel(row1[6]);

            Font poppins = getFont();
            Font smallerPoppins = getFont();
            col1.setFont(poppins12Style);
            col2.setFont(poppins12Style);
            col3.setFont(poppins12Style);
            col4.setFont(poppins12Style);
            col5.setFont(poppins12Style);
            col6.setFont(poppins12Style);

            Fonts poppins10 = new Fonts("Poppins", 10);
            Font poppins10Style = poppins10.getAppliedFont();

            JPanel col1Panel = new JPanel();
            JPanel col2Panel = new JPanel();
            JPanel col3Panel = new JPanel();
            JPanel col4Panel = new JPanel();
            JPanel col5Panel = new JPanel();
            JPanel col6Panel = new JPanel();
            JPanel col7Panel = new JPanel();

            if (item.getStatus().equals("Missing")) {
                RoundedPanel roundedStatus = new RoundedPanel(25);
                roundedStatus.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 3));
                roundedStatus.setPreferredSize(new Dimension(minColumnWidth, 20));
                roundedStatus.setBackground(Color.decode("#ff3131"));
                roundedStatus.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

                col5.setText("Missing");
                col5.setFont(poppins10Style);
                col5.setForeground(Color.WHITE);

                roundedStatus.add(col5);
                col5Panel.add(roundedStatus);
            } 
            else if (item.getStatus().equals("Found")){
                RoundedPanel roundedStatus = new RoundedPanel(25);
                roundedStatus.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 3));
                roundedStatus.setPreferredSize(new Dimension(minColumnWidth, 20));
                roundedStatus.setBackground(Color.decode("#14c600"));
                roundedStatus.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

                col5.setText("Found");
                col5.setFont(poppins10Style);
                col5.setForeground(Color.WHITE);

                roundedStatus.add(col5);
                col5Panel.add(roundedStatus);
            }
            else if (item.getStatus().equals("Surrendered")){
                RoundedPanel roundedStatus = new RoundedPanel(25);
                roundedStatus.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 3));
                roundedStatus.setPreferredSize(new Dimension(minColumnWidth + 20, 20));
                roundedStatus.setBackground(Color.decode("#e8bf1a"));
                roundedStatus.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

                col5.setText("Surrendered");
                col5.setFont(poppins10Style);
                col5.setForeground(Color.WHITE);

                roundedStatus.add(col5);
                col5Panel.add(roundedStatus);
            }

            RoundedButton col7 = new RoundedButton("Mark as Found", 12);
            col7.setPreferredSize(new Dimension(minColumnWidth + 45, minColumnHeight - 5));
            col7.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            col7.setBackground(Color.decode("#842b28"));
            col7.setForeground(Color.WHITE);
            col7.setFont(poppins10Style);

            col1Panel.add(col1);
            col2Panel.add(col2);
            col3Panel.add(col3);
            col4Panel.add(col4);
            //     col5Panel.add(col5);
            col6Panel.add(col6);
            col7Panel.add(col7);

            col1Panel.setOpaque(false);
            col2Panel.setOpaque(false);
            col3Panel.setOpaque(false);
            col4Panel.setOpaque(false);
            col5Panel.setOpaque(false);
            col6Panel.setOpaque(false);
            col7Panel.setOpaque(false);

            col1Panel.setPreferredSize(new Dimension(minColumnWidth * 2, minColumnHeight));
            col2Panel.setPreferredSize(new Dimension(minColumnWidth * 4, minColumnHeight));
            col3Panel.setPreferredSize(new Dimension((minColumnWidth * 5) + 19, minColumnHeight));
            col4Panel.setPreferredSize(new Dimension(minColumnWidth + 20, minColumnHeight));
            col5Panel.setPreferredSize(new Dimension(minColumnWidth + 25, minColumnHeight));
            col6Panel.setPreferredSize(new Dimension(minColumnWidth, minColumnHeight));
            col7Panel.setPreferredSize(new Dimension(minColumnWidth + 50, minColumnHeight));

            gbcRow.gridx = 1;
            add(col1Panel, gbcRow);

            gbcRow.gridx = 2;
            add(col2Panel, gbcRow);

            gbcRow.gridx = 3;
            add(col3Panel, gbcRow);

            gbcRow.gridx = 4;
            add(col4Panel, gbcRow);

            gbcRow.gridx = 5;
            add(col5Panel, gbcRow);

            gbcRow.gridx = 6;
            add(col6Panel, gbcRow);

            gbcRow.gridx = 7;
            add(col7Panel, gbcRow);

            gbcRow.gridy++;

        }
    }

    public void showIMS() {
        List<DAOIMS> ims = daoIMS.getAllUsers();

        // loadPatrons();
        int minColumnHeight = imsTab.getMinColumnHeight();
        int minColumnWidth = imsTab.getMinColumnWidth();

        // int columnCount = patTab.getColumnCount();
        setOpaque(false);
        setLayout(new GridBagLayout());

        GridBagConstraints gbcRow = new GridBagConstraints();
        gbcRow.gridy = 1;
        gbcRow.gridx = 1;
        gbcRow.insets = new Insets(10, 10, 10, 10);

        for (DAOIMS item : ims) {

            JLabel col1 = new JLabel(item.getSerialNumber());
            JLabel col2 = new JLabel(item.getFacilityCode());
            JLabel col3 = new JLabel(item.getEquipmentName());

            Fonts poppins12 = new Fonts("Poppins", 12f);
            Font poppins12Style = poppins12.getAppliedFont();
            Fonts poppins10 = new Fonts("Poppins", 10f);
            Font poppins10Style = poppins10.getAppliedFont();

            col1.setFont(poppins12Style);
            col2.setFont(poppins12Style);
            col3.setFont(poppins12Style);

            col1.setForeground(Color.BLACK);
            col2.setForeground(Color.BLACK);
            col3.setForeground(Color.BLACK);

            JPanel col1Panel = new JPanel();
            JPanel col2Panel = new JPanel();
            JPanel col3Panel = new JPanel();
            JPanel col4Panel = new JPanel();

            RoundedButton col4 = new RoundedButton("Edit", 12);
            col4.setPreferredSize(new Dimension(minColumnWidth, minColumnHeight - 5));
            col4.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            col4.setBackground(Color.decode("#842b28"));
            col4.setForeground(Color.WHITE);
            col4.setFont(poppins10Style);

            col1Panel.add(col1);
            col2Panel.add(col2);
            col3Panel.add(col3);
            col4Panel.add(col4);

            col1Panel.setOpaque(false);
            col2Panel.setOpaque(false);
            col3Panel.setOpaque(false);
            col4Panel.setOpaque(false);

            col1Panel.setPreferredSize(new Dimension(minColumnWidth + 80, minColumnHeight));
            col2Panel.setPreferredSize(new Dimension(minColumnWidth * 5, minColumnHeight));
            col3Panel.setPreferredSize(new Dimension(minColumnWidth * 7, minColumnHeight));
            col4Panel.setPreferredSize(new Dimension(minColumnWidth + 55, minColumnHeight));

            gbcRow.gridx = 1;
            add(col1Panel, gbcRow);

            gbcRow.gridx = 2;
            add(col2Panel, gbcRow);

            gbcRow.gridx = 3;
            add(col3Panel, gbcRow);

            gbcRow.gridx = 4;
            add(col4Panel, gbcRow);

            gbcRow.gridy++;

        }
    }
    
    public void showActiveRequest() {
    	List<DAOActiveRequest> ar = daoAR.getAllRequests();

        JPanel cardPanel = new JPanel();
        cardPanel.setOpaque(false);
        cardPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcCard = new GridBagConstraints();
        gbcCard.gridy = 1;
        gbcCard.insets = new Insets(10, 10, 10, 10);


        Fonts poppins10 = new Fonts("Poppins", 10f);
        Font poppinsStyle10 = poppins10.getAppliedFont();

        Fonts poppins14 = new Fonts("Poppins", 14f);
        Font poppinsStyle14 = poppins14.getAppliedFont();

        Fonts poppinsBold = new Fonts("PoppinsBold", 14f);
        Font poppinsBoldStyle14 = poppinsBold.getAppliedFont();

        

        // card
        for (DAOActiveRequest item: ar) {
            RoundedPanel card = new RoundedPanel(20);
            card.setPreferredSize(new Dimension(300, 150));
            card.setBackground(Color.decode("#842b28"));
            card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            card.setLayout(new BorderLayout());

            //left part of card
            JPanel leftPartCard = new JPanel();
            leftPartCard.setOpaque(false);
            leftPartCard.setLayout(new BorderLayout());

            // left upper part
            JPanel northLPCard = new JPanel();
            northLPCard.setOpaque(false);
            northLPCard.setLayout(new BorderLayout());

            JLabel nameOfReserver = new JLabel("Name of Reserver");
            nameOfReserver.setFont(poppinsStyle10);
            nameOfReserver.setForeground(Color.decode("#b4b4b4"));
            northLPCard.add(nameOfReserver, BorderLayout.NORTH);

            JLabel nameValue = new JLabel(item.getPatronName());
            nameValue.setFont(poppinsStyle14);
            nameValue.setForeground(Color.WHITE);;
            northLPCard.add(nameValue, BorderLayout.SOUTH);

            leftPartCard.add(northLPCard, BorderLayout.NORTH);

            // center container
            JPanel centerContainer = new JPanel();
            centerContainer.setOpaque(false);
            centerContainer.setLayout(new BorderLayout());

            // center upper part
            JPanel centerLPCard = new JPanel();
            centerLPCard.setOpaque(false);
            centerLPCard.setLayout(new BorderLayout());

            JLabel itemlabel = new JLabel("Item Label");
            itemlabel.setFont(poppinsStyle10);
            itemlabel.setForeground(Color.decode("#b4b4b4"));
            centerLPCard.add(itemlabel, BorderLayout.NORTH);

            JLabel itemValueLabel = new JLabel(item.getEquipmentName());
            itemValueLabel.setFont(poppinsStyle14);
            itemValueLabel.setForeground(Color.WHITE);;
            centerLPCard.add(itemValueLabel, BorderLayout.SOUTH);

            centerContainer.add(centerLPCard, BorderLayout.NORTH);

            // center lower part
            JPanel lowerLPCard = new JPanel();
            lowerLPCard.setOpaque(false);
            lowerLPCard.setLayout(new BorderLayout());

            JLabel statusLabel = new JLabel("Status");
            statusLabel.setFont(poppinsStyle10);
            statusLabel.setForeground(Color.decode("#b4b4b4"));
            lowerLPCard.add(statusLabel, BorderLayout.NORTH);

            JLabel statusValue = new JLabel("Pending");
            statusValue.setFont(poppinsBoldStyle14);
            statusValue.setForeground(Color.decode("#ffde59"));;
            lowerLPCard.add(statusValue, BorderLayout.SOUTH);

            centerContainer.add(lowerLPCard, BorderLayout.SOUTH);

            leftPartCard.add(centerContainer, BorderLayout.CENTER);

            //right part of card
            JPanel rightPartCard = new JPanel();
            rightPartCard.setOpaque(false);
            rightPartCard.setLayout(new BorderLayout());

            // right upper part
            JPanel northRPCard = new JPanel();
            northRPCard.setOpaque(false);
            northRPCard.setLayout(new BorderLayout());

            JLabel dateRequested = new JLabel("Date Requested");
            dateRequested.setFont(poppinsStyle10);
            dateRequested.setForeground(Color.decode("#b4b4b4"));
            northRPCard.add(dateRequested, BorderLayout.NORTH);

            JLabel dateValue = new JLabel(item.getBorrowDate());
            dateValue.setFont(poppinsStyle14);
            dateValue.setForeground(Color.WHITE);;
            northRPCard.add(dateValue, BorderLayout.SOUTH);

            rightPartCard.add(northRPCard, BorderLayout.NORTH);

            // button to view Details
            RoundedButton viewBtn = new RoundedButton("View Details", 20);
            viewBtn.setPreferredSize(new Dimension(100, 30));
            viewBtn.setBackground(Color.decode("#5d1513"));
            viewBtn.setForeground(Color.WHITE);
            viewBtn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

            rightPartCard.add(viewBtn, BorderLayout.SOUTH);

            // add
            card.add(leftPartCard, BorderLayout.WEST);
            card.add(rightPartCard, BorderLayout.EAST);
            cardPanel.add(card, gbcCard);
            gbcCard.gridy++;
        }

        // card panel
        JScrollPane cardScrollbar = new JScrollPane(cardPanel);
        cardScrollbar.setOpaque(false);
        cardScrollbar.getViewport().setOpaque(false);
        cardScrollbar.setPreferredSize(new Dimension(500, 420));
        cardScrollbar.setBorder(BorderFactory.createEmptyBorder(0, 40, 20, 40));
        cardScrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        cardScrollbar.getVerticalScrollBar().setPreferredSize(new Dimension(8, Integer.MAX_VALUE));
        cardScrollbar.getVerticalScrollBar().setUI(new RoundedScrollbar());
        
        add(cardScrollbar);
    }

    public void showBulacanianaBooks() {
        List<DAOBook> books = daoBook.getAllBooks("BulColl");

        // loadPatrons();
        int minColumnHeight = bulacanianaTab.getMinColumnHeight();
        int minColumnWidth = bulacanianaTab.getMinColumnWidth();

        // int columnCount = patTab.getColumnCount();
        setOpaque(false);
        setLayout(new GridBagLayout());

        GridBagConstraints gbcRow = new GridBagConstraints();
        gbcRow.gridy = 1;
        gbcRow.gridx = 1;
        gbcRow.insets = new Insets(10, 10, 10, 10);

        for (DAOBook book : books) {

            JLabel col1 = new JLabel(book.getCallnumber());
            JLabel col2 = new JLabel(book.getTitle());
            JLabel col3 = new JLabel(book.getAuthor());
            JLabel col4 = new JLabel(book.getPublicationYear());
            JLabel col5 = new JLabel(book.getClassificationCode());
            JLabel col6 = new JLabel(book.getBorrowable());

            Fonts poppins12 = new Fonts("Poppins", 12f);
            Font poppins12Style = poppins12.getAppliedFont();

            col1.setFont(poppins12Style);
            col2.setFont(poppins12Style);
            col3.setFont(poppins12Style);
            col4.setFont(poppins12Style);
            col5.setFont(poppins12Style);
            col6.setFont(poppins12Style);

            Fonts poppins10 = new Fonts("Poppins", 10);
            Font poppins10Style = poppins10.getAppliedFont();

            JPanel col1Panel = new JPanel();
            JPanel col2Panel = new JPanel();
            JPanel col3Panel = new JPanel();
            JPanel col4Panel = new JPanel();
            JPanel col5Panel = new JPanel();
            JPanel col6Panel = new JPanel();
            JPanel col7Panel = new JPanel();

            if (col6.getText().equals("NBB")) {

                ImageIcon profileIcon = new ImageIcon(imgFilePath + "cross_mark.png");
                Image profileImage = profileIcon.getImage();
                Image scaledImageProfile = profileImage.getScaledInstance(30, 15, Image.SCALE_SMOOTH);
                profileIcon = new ImageIcon(scaledImageProfile);
                JButton profileLogo = new JButton(profileIcon);
                profileLogo.setBackground(new Color(0, 0, 0, 0));
                profileLogo.setContentAreaFilled(false);
                profileLogo.setBorderPainted(false);
                profileLogo.setFocusPainted(false);
                profileLogo.setHorizontalAlignment(SwingConstants.CENTER);

                col6Panel.add(profileLogo);
            } else {
                ImageIcon profileIcon = new ImageIcon(imgFilePath + "check_mark.png");
                Image profileImage = profileIcon.getImage();
                Image scaledImageProfile = profileImage.getScaledInstance(30, 15, Image.SCALE_SMOOTH);
                profileIcon = new ImageIcon(scaledImageProfile);
                JButton profileLogo = new JButton(profileIcon);
                profileLogo.setBackground(new Color(0, 0, 0, 0));
                profileLogo.setContentAreaFilled(false);
                profileLogo.setBorderPainted(false);
                profileLogo.setFocusPainted(false);
                profileLogo.setHorizontalAlignment(SwingConstants.CENTER);

                col6Panel.add(profileLogo);

            }

            RoundedButton col7 = new RoundedButton("Edit", 12);
            col7.setPreferredSize(new Dimension(minColumnWidth, minColumnHeight - 5));
            col7.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            col7.setBackground(Color.decode("#842b28"));
            col7.setForeground(Color.WHITE);
            col7.setFont(poppins10Style);

            col1Panel.add(col1);
            col2Panel.add(col2);
            col3Panel.add(col3);
            col4Panel.add(col4);
            col5Panel.add(col5);
            col6Panel.add(col6);
            col7Panel.add(col7);

            col1Panel.setOpaque(false);
            col2Panel.setOpaque(false);
            col3Panel.setOpaque(false);
            col4Panel.setOpaque(false);
            col5Panel.setOpaque(false);
            col6Panel.setOpaque(false);
            col7Panel.setOpaque(false);

            col1Panel.setPreferredSize(new Dimension(minColumnWidth * 2, minColumnHeight));
            col2Panel.setPreferredSize(new Dimension((minColumnWidth * 5) + 40, minColumnHeight));
            col3Panel.setPreferredSize(new Dimension(minColumnWidth * 3, minColumnHeight));
            col4Panel.setPreferredSize(new Dimension(minColumnWidth + 38, minColumnHeight));
            col5Panel.setPreferredSize(new Dimension(minColumnWidth + 25, minColumnHeight));
            col6Panel.setPreferredSize(new Dimension(minColumnWidth + 20, minColumnHeight));
            col7Panel.setPreferredSize(new Dimension(minColumnWidth + 45, minColumnHeight));

            gbcRow.gridx = 1;
            add(col1Panel, gbcRow);

            gbcRow.gridx = 2;
            add(col2Panel, gbcRow);

            gbcRow.gridx = 3;
            add(col3Panel, gbcRow);

            gbcRow.gridx = 4;
            add(col4Panel, gbcRow);

            gbcRow.gridx = 5;
            add(col5Panel, gbcRow);

            gbcRow.gridx = 6;
            add(col6Panel, gbcRow);

            gbcRow.gridx = 7;
            add(col7Panel, gbcRow);

            gbcRow.gridy++;

        }
    }

    public void showFictionBooks() {
        List<DAOBook> books = daoBook.getAllBooks("FictColl");

        // loadPatrons();
        int minColumnHeight = fictionTab.getMinColumnHeight();
        int minColumnWidth = fictionTab.getMinColumnWidth();

        // int columnCount = patTab.getColumnCount();
        setOpaque(false);
        setLayout(new GridBagLayout());

        GridBagConstraints gbcRow = new GridBagConstraints();
        gbcRow.gridy = 1;
        gbcRow.gridx = 1;
        gbcRow.insets = new Insets(10, 10, 10, 10);

        for (DAOBook book : books) {

            JLabel col1 = new JLabel(book.getCallnumber());
            JLabel col2 = new JLabel(book.getTitle());
            JLabel col3 = new JLabel(book.getAuthor());
            JLabel col4 = new JLabel(book.getPublicationYear());
            JLabel col5 = new JLabel(book.getClassificationCode());
            JLabel col6 = new JLabel(book.getBorrowable());

            Fonts poppins12 = new Fonts("Poppins", 12f);
            Font poppins12Style = poppins12.getAppliedFont();

            col1.setFont(poppins12Style);
            col2.setFont(poppins12Style);
            col3.setFont(poppins12Style);
            col4.setFont(poppins12Style);
            col5.setFont(poppins12Style);
            col6.setFont(poppins12Style);

            Fonts poppins10 = new Fonts("Poppins", 10);
            Font poppins10Style = poppins10.getAppliedFont();

            JPanel col1Panel = new JPanel();
            JPanel col2Panel = new JPanel();
            JPanel col3Panel = new JPanel();
            JPanel col4Panel = new JPanel();
            JPanel col5Panel = new JPanel();
            JPanel col6Panel = new JPanel();
            JPanel col7Panel = new JPanel();

            if (col6.getText().equals("NBB")) {

                ImageIcon profileIcon = new ImageIcon(imgFilePath + "cross_mark.png");
                Image profileImage = profileIcon.getImage();
                Image scaledImageProfile = profileImage.getScaledInstance(30, 15, Image.SCALE_SMOOTH);
                profileIcon = new ImageIcon(scaledImageProfile);
                JButton profileLogo = new JButton(profileIcon);
                profileLogo.setBackground(new Color(0, 0, 0, 0));
                profileLogo.setContentAreaFilled(false);
                profileLogo.setBorderPainted(false);
                profileLogo.setFocusPainted(false);
                profileLogo.setHorizontalAlignment(SwingConstants.CENTER);

                col6Panel.add(profileLogo);
            } else {
                ImageIcon profileIcon = new ImageIcon(imgFilePath + "check_mark.png");
                Image profileImage = profileIcon.getImage();
                Image scaledImageProfile = profileImage.getScaledInstance(30, 15, Image.SCALE_SMOOTH);
                profileIcon = new ImageIcon(scaledImageProfile);
                JButton profileLogo = new JButton(profileIcon);
                profileLogo.setBackground(new Color(0, 0, 0, 0));
                profileLogo.setContentAreaFilled(false);
                profileLogo.setBorderPainted(false);
                profileLogo.setFocusPainted(false);
                profileLogo.setHorizontalAlignment(SwingConstants.CENTER);

                col6Panel.add(profileLogo);

            }

            RoundedButton col7 = new RoundedButton("Edit", 12);
            col7.setPreferredSize(new Dimension(minColumnWidth, minColumnHeight - 5));
            col7.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            col7.setBackground(Color.decode("#842b28"));
            col7.setForeground(Color.WHITE);
            col7.setFont(poppins10Style);

            col1Panel.add(col1);
            col2Panel.add(col2);
            col3Panel.add(col3);
            col4Panel.add(col4);
            col5Panel.add(col5);
            col6Panel.add(col6);
            col7Panel.add(col7);

            col1Panel.setOpaque(false);
            col2Panel.setOpaque(false);
            col3Panel.setOpaque(false);
            col4Panel.setOpaque(false);
            col5Panel.setOpaque(false);
            col6Panel.setOpaque(false);
            col7Panel.setOpaque(false);

            col1Panel.setPreferredSize(new Dimension(minColumnWidth * 2, minColumnHeight));
            col2Panel.setPreferredSize(new Dimension((minColumnWidth * 5) + 40, minColumnHeight));
            col3Panel.setPreferredSize(new Dimension(minColumnWidth * 3, minColumnHeight));
            col4Panel.setPreferredSize(new Dimension(minColumnWidth + 38, minColumnHeight));
            col5Panel.setPreferredSize(new Dimension(minColumnWidth + 25, minColumnHeight));
            col6Panel.setPreferredSize(new Dimension(minColumnWidth + 20, minColumnHeight));
            col7Panel.setPreferredSize(new Dimension(minColumnWidth + 45, minColumnHeight));

            gbcRow.gridx = 1;
            add(col1Panel, gbcRow);

            gbcRow.gridx = 2;
            add(col2Panel, gbcRow);

            gbcRow.gridx = 3;
            add(col3Panel, gbcRow);

            gbcRow.gridx = 4;
            add(col4Panel, gbcRow);

            gbcRow.gridx = 5;
            add(col5Panel, gbcRow);

            gbcRow.gridx = 6;
            add(col6Panel, gbcRow);

            gbcRow.gridx = 7;
            add(col7Panel, gbcRow);

            gbcRow.gridy++;

        }
    }

    public void showFilipinianaBooks() {
        List<DAOBook> books = daoBook.getAllBooks("FiliColl");

        // loadPatrons();
        int minColumnHeight = filipinianaTab.getMinColumnHeight();
        int minColumnWidth = filipinianaTab.getMinColumnWidth();

        // int columnCount = patTab.getColumnCount();
        setOpaque(false);
        setLayout(new GridBagLayout());

        GridBagConstraints gbcRow = new GridBagConstraints();
        gbcRow.gridy = 1;
        gbcRow.gridx = 1;
        gbcRow.insets = new Insets(10, 10, 10, 10);

        for (DAOBook book : books) {

            JLabel col1 = new JLabel(book.getCallnumber());
            JLabel col2 = new JLabel(book.getTitle());
            JLabel col3 = new JLabel(book.getAuthor());
            JLabel col4 = new JLabel(book.getPublicationYear());
            JLabel col5 = new JLabel(book.getClassificationCode());
            JLabel col6 = new JLabel(book.getBorrowable());

            Fonts poppins12 = new Fonts("Poppins", 12f);
            Font poppins12Style = poppins12.getAppliedFont();

            col1.setFont(poppins12Style);
            col2.setFont(poppins12Style);
            col3.setFont(poppins12Style);
            col4.setFont(poppins12Style);
            col5.setFont(poppins12Style);
            col6.setFont(poppins12Style);

            Fonts poppins10 = new Fonts("Poppins", 10);
            Font poppins10Style = poppins10.getAppliedFont();

            JPanel col1Panel = new JPanel();
            JPanel col2Panel = new JPanel();
            JPanel col3Panel = new JPanel();
            JPanel col4Panel = new JPanel();
            JPanel col5Panel = new JPanel();
            JPanel col6Panel = new JPanel();
            JPanel col7Panel = new JPanel();

            if (col6.getText().equals("NBB")) {

                ImageIcon profileIcon = new ImageIcon(imgFilePath + "cross_mark.png");
                Image profileImage = profileIcon.getImage();
                Image scaledImageProfile = profileImage.getScaledInstance(30, 15, Image.SCALE_SMOOTH);
                profileIcon = new ImageIcon(scaledImageProfile);
                JButton profileLogo = new JButton(profileIcon);
                profileLogo.setBackground(new Color(0, 0, 0, 0));
                profileLogo.setContentAreaFilled(false);
                profileLogo.setBorderPainted(false);
                profileLogo.setFocusPainted(false);
                profileLogo.setHorizontalAlignment(SwingConstants.CENTER);

                col6Panel.add(profileLogo);
            } else {
                ImageIcon profileIcon = new ImageIcon(imgFilePath + "check_mark.png");
                Image profileImage = profileIcon.getImage();
                Image scaledImageProfile = profileImage.getScaledInstance(30, 15, Image.SCALE_SMOOTH);
                profileIcon = new ImageIcon(scaledImageProfile);
                JButton profileLogo = new JButton(profileIcon);
                profileLogo.setBackground(new Color(0, 0, 0, 0));
                profileLogo.setContentAreaFilled(false);
                profileLogo.setBorderPainted(false);
                profileLogo.setFocusPainted(false);
                profileLogo.setHorizontalAlignment(SwingConstants.CENTER);

                col6Panel.add(profileLogo);

            }

            RoundedButton col7 = new RoundedButton("Edit", 12);
            col7.setPreferredSize(new Dimension(minColumnWidth, minColumnHeight - 5));
            col7.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            col7.setBackground(Color.decode("#842b28"));
            col7.setForeground(Color.WHITE);
            col7.setFont(poppins10Style);

            col1Panel.add(col1);
            col2Panel.add(col2);
            col3Panel.add(col3);
            col4Panel.add(col4);
            col5Panel.add(col5);
            col6Panel.add(col6);
            col7Panel.add(col7);

            col1Panel.setOpaque(false);
            col2Panel.setOpaque(false);
            col3Panel.setOpaque(false);
            col4Panel.setOpaque(false);
            col5Panel.setOpaque(false);
            col6Panel.setOpaque(false);
            col7Panel.setOpaque(false);

            col1Panel.setPreferredSize(new Dimension(minColumnWidth * 2, minColumnHeight));
            col2Panel.setPreferredSize(new Dimension((minColumnWidth * 5) + 40, minColumnHeight));
            col3Panel.setPreferredSize(new Dimension(minColumnWidth * 3, minColumnHeight));
            col4Panel.setPreferredSize(new Dimension(minColumnWidth + 38, minColumnHeight));
            col5Panel.setPreferredSize(new Dimension(minColumnWidth + 25, minColumnHeight));
            col6Panel.setPreferredSize(new Dimension(minColumnWidth + 20, minColumnHeight));
            col7Panel.setPreferredSize(new Dimension(minColumnWidth + 45, minColumnHeight));

            gbcRow.gridx = 1;
            add(col1Panel, gbcRow);

            gbcRow.gridx = 2;
            add(col2Panel, gbcRow);

            gbcRow.gridx = 3;
            add(col3Panel, gbcRow);

            gbcRow.gridx = 4;
            add(col4Panel, gbcRow);

            gbcRow.gridx = 5;
            add(col5Panel, gbcRow);

            gbcRow.gridx = 6;
            add(col6Panel, gbcRow);

            gbcRow.gridx = 7;
            add(col7Panel, gbcRow);

            gbcRow.gridy++;

        }
    }

    public void showGenSecBooks() {
        List<DAOBook> books = daoBook.getAllBooks("CircColl");

        // loadPatrons();
        int minColumnHeight = genSecTab.getMinColumnHeight();
        int minColumnWidth = genSecTab.getMinColumnWidth();

        // int columnCount = patTab.getColumnCount();
        setOpaque(false);
        setLayout(new GridBagLayout());

        GridBagConstraints gbcRow = new GridBagConstraints();
        gbcRow.gridy = 1;
        gbcRow.gridx = 1;
        gbcRow.insets = new Insets(10, 10, 10, 10);

        for (DAOBook book : books) {

            JLabel col1 = new JLabel(book.getCallnumber());
            JLabel col2 = new JLabel(book.getTitle());
            JLabel col3 = new JLabel(book.getAuthor());
            JLabel col4 = new JLabel(book.getPublicationYear());
            JLabel col5 = new JLabel(book.getClassificationCode());
            JLabel col6 = new JLabel(book.getBorrowable());

            Fonts poppins12 = new Fonts("Poppins", 12f);
            Font poppins12Style = poppins12.getAppliedFont();

            col1.setFont(poppins12Style);
            col2.setFont(poppins12Style);
            col3.setFont(poppins12Style);
            col4.setFont(poppins12Style);
            col5.setFont(poppins12Style);
            col6.setFont(poppins12Style);

            Fonts poppins10 = new Fonts("Poppins", 10);
            Font poppins10Style = poppins10.getAppliedFont();

            JPanel col1Panel = new JPanel();
            JPanel col2Panel = new JPanel();
            JPanel col3Panel = new JPanel();
            JPanel col4Panel = new JPanel();
            JPanel col5Panel = new JPanel();
            JPanel col6Panel = new JPanel();
            JPanel col7Panel = new JPanel();

            if (col6.getText().equals("NBB")) {

                ImageIcon profileIcon = new ImageIcon(imgFilePath + "cross_mark.png");
                Image profileImage = profileIcon.getImage();
                Image scaledImageProfile = profileImage.getScaledInstance(30, 15, Image.SCALE_SMOOTH);
                profileIcon = new ImageIcon(scaledImageProfile);
                JButton profileLogo = new JButton(profileIcon);
                profileLogo.setBackground(new Color(0, 0, 0, 0));
                profileLogo.setContentAreaFilled(false);
                profileLogo.setBorderPainted(false);
                profileLogo.setFocusPainted(false);
                profileLogo.setHorizontalAlignment(SwingConstants.CENTER);

                col6Panel.add(profileLogo);
            } else {
                ImageIcon profileIcon = new ImageIcon(imgFilePath + "check_mark.png");
                Image profileImage = profileIcon.getImage();
                Image scaledImageProfile = profileImage.getScaledInstance(30, 15, Image.SCALE_SMOOTH);
                profileIcon = new ImageIcon(scaledImageProfile);
                JButton profileLogo = new JButton(profileIcon);
                profileLogo.setBackground(new Color(0, 0, 0, 0));
                profileLogo.setContentAreaFilled(false);
                profileLogo.setBorderPainted(false);
                profileLogo.setFocusPainted(false);
                profileLogo.setHorizontalAlignment(SwingConstants.CENTER);

                col6Panel.add(profileLogo);

            }

            RoundedButton col7 = new RoundedButton("Edit", 12);
            col7.setPreferredSize(new Dimension(minColumnWidth, minColumnHeight - 5));
            col7.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            col7.setBackground(Color.decode("#842b28"));
            col7.setForeground(Color.WHITE);
            col7.setFont(poppins10Style);

            col1Panel.add(col1);
            col2Panel.add(col2);
            col3Panel.add(col3);
            col4Panel.add(col4);
            col5Panel.add(col5);
            col6Panel.add(col6);
            col7Panel.add(col7);

            col1Panel.setOpaque(false);
            col2Panel.setOpaque(false);
            col3Panel.setOpaque(false);
            col4Panel.setOpaque(false);
            col5Panel.setOpaque(false);
            col6Panel.setOpaque(false);
            col7Panel.setOpaque(false);

            col1Panel.setPreferredSize(new Dimension(minColumnWidth * 2, minColumnHeight));
            col2Panel.setPreferredSize(new Dimension((minColumnWidth * 5) + 40, minColumnHeight));
            col3Panel.setPreferredSize(new Dimension(minColumnWidth * 3, minColumnHeight));
            col4Panel.setPreferredSize(new Dimension(minColumnWidth + 38, minColumnHeight));
            col5Panel.setPreferredSize(new Dimension(minColumnWidth + 25, minColumnHeight));
            col6Panel.setPreferredSize(new Dimension(minColumnWidth + 20, minColumnHeight));
            col7Panel.setPreferredSize(new Dimension(minColumnWidth + 45, minColumnHeight));

            gbcRow.gridx = 1;
            add(col1Panel, gbcRow);

            gbcRow.gridx = 2;
            add(col2Panel, gbcRow);

            gbcRow.gridx = 3;
            add(col3Panel, gbcRow);

            gbcRow.gridx = 4;
            add(col4Panel, gbcRow);

            gbcRow.gridx = 5;
            add(col5Panel, gbcRow);

            gbcRow.gridx = 6;
            add(col6Panel, gbcRow);

            gbcRow.gridx = 7;
            add(col7Panel, gbcRow);

            gbcRow.gridy++;

        }
    }

    public void showReferenceBooks() {
        List<DAOBook> books = daoBook.getAllBooks("RefColl");

        // loadPatrons();
        int minColumnHeight = referenceTab.getMinColumnHeight();
        int minColumnWidth = referenceTab.getMinColumnWidth();

        // int columnCount = patTab.getColumnCount();
        setOpaque(false);
        setLayout(new GridBagLayout());

        GridBagConstraints gbcRow = new GridBagConstraints();
        gbcRow.gridy = 1;
        gbcRow.gridx = 1;
        gbcRow.insets = new Insets(10, 10, 10, 10);

        for (DAOBook book : books) {

            JLabel col1 = new JLabel(book.getCallnumber());
            JLabel col2 = new JLabel(book.getTitle());
            JLabel col3 = new JLabel(book.getAuthor());
            JLabel col4 = new JLabel(book.getPublicationYear());
            JLabel col5 = new JLabel(book.getClassificationCode());
            JLabel col6 = new JLabel(book.getBorrowable());

            Fonts poppins12 = new Fonts("Poppins", 12f);
            Font poppins12Style = poppins12.getAppliedFont();

            col1.setFont(poppins12Style);
            col2.setFont(poppins12Style);
            col3.setFont(poppins12Style);
            col4.setFont(poppins12Style);
            col5.setFont(poppins12Style);
            col6.setFont(poppins12Style);

            Fonts poppins10 = new Fonts("Poppins", 10);
            Font poppins10Style = poppins10.getAppliedFont();

            JPanel col1Panel = new JPanel();
            JPanel col2Panel = new JPanel();
            JPanel col3Panel = new JPanel();
            JPanel col4Panel = new JPanel();
            JPanel col5Panel = new JPanel();
            JPanel col6Panel = new JPanel();
            JPanel col7Panel = new JPanel();

            if (col6.getText().equals("NBB")) {

                ImageIcon profileIcon = new ImageIcon(imgFilePath + "cross_mark.png");
                Image profileImage = profileIcon.getImage();
                Image scaledImageProfile = profileImage.getScaledInstance(30, 15, Image.SCALE_SMOOTH);
                profileIcon = new ImageIcon(scaledImageProfile);
                JButton profileLogo = new JButton(profileIcon);
                profileLogo.setBackground(new Color(0, 0, 0, 0));
                profileLogo.setContentAreaFilled(false);
                profileLogo.setBorderPainted(false);
                profileLogo.setFocusPainted(false);
                profileLogo.setHorizontalAlignment(SwingConstants.CENTER);

                col6Panel.add(profileLogo);
            } else {
                ImageIcon profileIcon = new ImageIcon(imgFilePath + "check_mark.png");
                Image profileImage = profileIcon.getImage();
                Image scaledImageProfile = profileImage.getScaledInstance(30, 15, Image.SCALE_SMOOTH);
                profileIcon = new ImageIcon(scaledImageProfile);
                JButton profileLogo = new JButton(profileIcon);
                profileLogo.setBackground(new Color(0, 0, 0, 0));
                profileLogo.setContentAreaFilled(false);
                profileLogo.setBorderPainted(false);
                profileLogo.setFocusPainted(false);
                profileLogo.setHorizontalAlignment(SwingConstants.CENTER);

                col6Panel.add(profileLogo);

            }

            RoundedButton col7 = new RoundedButton("Edit", 12);
            col7.setPreferredSize(new Dimension(minColumnWidth, minColumnHeight - 5));
            col7.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            col7.setBackground(Color.decode("#842b28"));
            col7.setForeground(Color.WHITE);
            col7.setFont(poppins10Style);

            col1Panel.add(col1);
            col2Panel.add(col2);
            col3Panel.add(col3);
            col4Panel.add(col4);
            col5Panel.add(col5);
            col6Panel.add(col6);
            col7Panel.add(col7);

            col1Panel.setOpaque(false);
            col2Panel.setOpaque(false);
            col3Panel.setOpaque(false);
            col4Panel.setOpaque(false);
            col5Panel.setOpaque(false);
            col6Panel.setOpaque(false);
            col7Panel.setOpaque(false);

            col1Panel.setPreferredSize(new Dimension(minColumnWidth * 2, minColumnHeight));
            col2Panel.setPreferredSize(new Dimension((minColumnWidth * 5) + 40, minColumnHeight));
            col3Panel.setPreferredSize(new Dimension(minColumnWidth * 3, minColumnHeight));
            col4Panel.setPreferredSize(new Dimension(minColumnWidth + 38, minColumnHeight));
            col5Panel.setPreferredSize(new Dimension(minColumnWidth + 25, minColumnHeight));
            col6Panel.setPreferredSize(new Dimension(minColumnWidth + 20, minColumnHeight));
            col7Panel.setPreferredSize(new Dimension(minColumnWidth + 45, minColumnHeight));

            gbcRow.gridx = 1;
            add(col1Panel, gbcRow);

            gbcRow.gridx = 2;
            add(col2Panel, gbcRow);

            gbcRow.gridx = 3;
            add(col3Panel, gbcRow);

            gbcRow.gridx = 4;
            add(col4Panel, gbcRow);

            gbcRow.gridx = 5;
            add(col5Panel, gbcRow);

            gbcRow.gridx = 6;
            add(col6Panel, gbcRow);

            gbcRow.gridx = 7;
            add(col7Panel, gbcRow);

            gbcRow.gridy++;

        }
    }

    public void showReserveBooks() {
        List<DAOBook> books = daoBook.getAllBooks("ResColl");

        // loadPatrons();
        int minColumnHeight = reserveTab.getMinColumnHeight();
        int minColumnWidth = reserveTab.getMinColumnWidth();

        // int columnCount = patTab.getColumnCount();
        setOpaque(false);
        setLayout(new GridBagLayout());

        GridBagConstraints gbcRow = new GridBagConstraints();
        gbcRow.gridy = 1;
        gbcRow.gridx = 1;
        gbcRow.insets = new Insets(10, 10, 10, 10);

        for (DAOBook book : books) {

            JLabel col1 = new JLabel(book.getCallnumber());
            JLabel col2 = new JLabel(book.getTitle());
            JLabel col3 = new JLabel(book.getAuthor());
            JLabel col4 = new JLabel(book.getPublicationYear());
            JLabel col5 = new JLabel(book.getClassificationCode());
            JLabel col6 = new JLabel(book.getBorrowable());

            Fonts poppins12 = new Fonts("Poppins", 12f);
            Font poppins12Style = poppins12.getAppliedFont();

            col1.setFont(poppins12Style);
            col2.setFont(poppins12Style);
            col3.setFont(poppins12Style);
            col4.setFont(poppins12Style);
            col5.setFont(poppins12Style);
            col6.setFont(poppins12Style);

            Fonts poppins10 = new Fonts("Poppins", 10);
            Font poppins10Style = poppins10.getAppliedFont();

            JPanel col1Panel = new JPanel();
            JPanel col2Panel = new JPanel();
            JPanel col3Panel = new JPanel();
            JPanel col4Panel = new JPanel();
            JPanel col5Panel = new JPanel();
            JPanel col6Panel = new JPanel();
            JPanel col7Panel = new JPanel();

            if (col6.getText().equals("NBB")) {

                ImageIcon profileIcon = new ImageIcon(imgFilePath + "cross_mark.png");
                Image profileImage = profileIcon.getImage();
                Image scaledImageProfile = profileImage.getScaledInstance(30, 15, Image.SCALE_SMOOTH);
                profileIcon = new ImageIcon(scaledImageProfile);
                JButton profileLogo = new JButton(profileIcon);
                profileLogo.setBackground(new Color(0, 0, 0, 0));
                profileLogo.setContentAreaFilled(false);
                profileLogo.setBorderPainted(false);
                profileLogo.setFocusPainted(false);
                profileLogo.setHorizontalAlignment(SwingConstants.CENTER);

                col6Panel.add(profileLogo);
            } else {
                ImageIcon profileIcon = new ImageIcon(imgFilePath + "check_mark.png");
                Image profileImage = profileIcon.getImage();
                Image scaledImageProfile = profileImage.getScaledInstance(30, 15, Image.SCALE_SMOOTH);
                profileIcon = new ImageIcon(scaledImageProfile);
                JButton profileLogo = new JButton(profileIcon);
                profileLogo.setBackground(new Color(0, 0, 0, 0));
                profileLogo.setContentAreaFilled(false);
                profileLogo.setBorderPainted(false);
                profileLogo.setFocusPainted(false);
                profileLogo.setHorizontalAlignment(SwingConstants.CENTER);

                col6Panel.add(profileLogo);

            }

            RoundedButton col7 = new RoundedButton("Edit", 12);
            col7.setPreferredSize(new Dimension(minColumnWidth, minColumnHeight - 5));
            col7.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            col7.setBackground(Color.decode("#842b28"));
            col7.setForeground(Color.WHITE);
            col7.setFont(poppins10Style);

            col1Panel.add(col1);
            col2Panel.add(col2);
            col3Panel.add(col3);
            col4Panel.add(col4);
            col5Panel.add(col5);
            col6Panel.add(col6);
            col7Panel.add(col7);

            col1Panel.setOpaque(false);
            col2Panel.setOpaque(false);
            col3Panel.setOpaque(false);
            col4Panel.setOpaque(false);
            col5Panel.setOpaque(false);
            col6Panel.setOpaque(false);
            col7Panel.setOpaque(false);

            col1Panel.setPreferredSize(new Dimension(minColumnWidth * 2, minColumnHeight));
            col2Panel.setPreferredSize(new Dimension((minColumnWidth * 5) + 40, minColumnHeight));
            col3Panel.setPreferredSize(new Dimension(minColumnWidth * 3, minColumnHeight));
            col4Panel.setPreferredSize(new Dimension(minColumnWidth + 38, minColumnHeight));
            col5Panel.setPreferredSize(new Dimension(minColumnWidth + 25, minColumnHeight));
            col6Panel.setPreferredSize(new Dimension(minColumnWidth + 20, minColumnHeight));
            col7Panel.setPreferredSize(new Dimension(minColumnWidth + 45, minColumnHeight));

            gbcRow.gridx = 1;
            add(col1Panel, gbcRow);

            gbcRow.gridx = 2;
            add(col2Panel, gbcRow);

            gbcRow.gridx = 3;
            add(col3Panel, gbcRow);

            gbcRow.gridx = 4;
            add(col4Panel, gbcRow);

            gbcRow.gridx = 5;
            add(col5Panel, gbcRow);

            gbcRow.gridx = 6;
            add(col6Panel, gbcRow);

            gbcRow.gridx = 7;
            add(col7Panel, gbcRow);

            gbcRow.gridy++;

        }
    }

    public void showThesisAndDissertationBooks() {
        List<DAOBook> books = daoBook.getAllBooks("T&D");

        // loadPatrons();
        int minColumnHeight = TaDTab.getMinColumnHeight();
        int minColumnWidth = TaDTab.getMinColumnWidth();

        // int columnCount = patTab.getColumnCount();
        setOpaque(false);
        setLayout(new GridBagLayout());

        GridBagConstraints gbcRow = new GridBagConstraints();
        gbcRow.gridy = 1;
        gbcRow.gridx = 1;
        gbcRow.insets = new Insets(10, 10, 10, 10);

        for (DAOBook book : books) {

            JLabel col1 = new JLabel(book.getCallnumber());
            JLabel col2 = new JLabel(book.getTitle());
            JLabel col3 = new JLabel(book.getAuthor());
            JLabel col4 = new JLabel(book.getPublicationYear());
            JLabel col5 = new JLabel(book.getClassificationCode());
            JLabel col6 = new JLabel(book.getBorrowable());

            Fonts poppins12 = new Fonts("Poppins", 12f);
            Font poppins12Style = poppins12.getAppliedFont();

            col1.setFont(poppins12Style);
            col2.setFont(poppins12Style);
            col3.setFont(poppins12Style);
            col4.setFont(poppins12Style);
            col5.setFont(poppins12Style);
            col6.setFont(poppins12Style);

            Fonts poppins10 = new Fonts("Poppins", 10);
            Font poppins10Style = poppins10.getAppliedFont();

            JPanel col1Panel = new JPanel();
            JPanel col2Panel = new JPanel();
            JPanel col3Panel = new JPanel();
            JPanel col4Panel = new JPanel();
            JPanel col5Panel = new JPanel();
            JPanel col6Panel = new JPanel();
            JPanel col7Panel = new JPanel();

            if (col6.getText().equals("NBB")) {

                ImageIcon profileIcon = new ImageIcon(imgFilePath + "ross_mark.png");
                Image profileImage = profileIcon.getImage();
                Image scaledImageProfile = profileImage.getScaledInstance(30, 15, Image.SCALE_SMOOTH);
                profileIcon = new ImageIcon(scaledImageProfile);
                JButton profileLogo = new JButton(profileIcon);
                profileLogo.setBackground(new Color(0, 0, 0, 0));
                profileLogo.setContentAreaFilled(false);
                profileLogo.setBorderPainted(false);
                profileLogo.setFocusPainted(false);
                profileLogo.setHorizontalAlignment(SwingConstants.CENTER);

                col6Panel.add(profileLogo);
            } else {
                ImageIcon profileIcon = new ImageIcon(imgFilePath + "check_mark.png");
                Image profileImage = profileIcon.getImage();
                Image scaledImageProfile = profileImage.getScaledInstance(30, 15, Image.SCALE_SMOOTH);
                profileIcon = new ImageIcon(scaledImageProfile);
                JButton profileLogo = new JButton(profileIcon);
                profileLogo.setBackground(new Color(0, 0, 0, 0));
                profileLogo.setContentAreaFilled(false);
                profileLogo.setBorderPainted(false);
                profileLogo.setFocusPainted(false);
                profileLogo.setHorizontalAlignment(SwingConstants.CENTER);

                col6Panel.add(profileLogo);

            }

            RoundedButton col7 = new RoundedButton("Edit", 12);
            col7.setPreferredSize(new Dimension(minColumnWidth, minColumnHeight - 5));
            col7.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            col7.setBackground(Color.decode("#842b28"));
            col7.setForeground(Color.WHITE);
            col7.setFont(poppins10Style);

            col1Panel.add(col1);
            col2Panel.add(col2);
            col3Panel.add(col3);
            col4Panel.add(col4);
            col5Panel.add(col5);
            col6Panel.add(col6);
            col7Panel.add(col7);

            col1Panel.setOpaque(false);
            col2Panel.setOpaque(false);
            col3Panel.setOpaque(false);
            col4Panel.setOpaque(false);
            col5Panel.setOpaque(false);
            col6Panel.setOpaque(false);
            col7Panel.setOpaque(false);

            col1Panel.setPreferredSize(new Dimension(minColumnWidth * 2, minColumnHeight));
            col2Panel.setPreferredSize(new Dimension((minColumnWidth * 5) + 40, minColumnHeight));
            col3Panel.setPreferredSize(new Dimension(minColumnWidth * 3, minColumnHeight));
            col4Panel.setPreferredSize(new Dimension(minColumnWidth + 38, minColumnHeight));
            col5Panel.setPreferredSize(new Dimension(minColumnWidth + 25, minColumnHeight));
            col6Panel.setPreferredSize(new Dimension(minColumnWidth + 20, minColumnHeight));
            col7Panel.setPreferredSize(new Dimension(minColumnWidth + 45, minColumnHeight));

            gbcRow.gridx = 1;
            add(col1Panel, gbcRow);

            gbcRow.gridx = 2;
            add(col2Panel, gbcRow);

            gbcRow.gridx = 3;
            add(col3Panel, gbcRow);

            gbcRow.gridx = 4;
            add(col4Panel, gbcRow);

            gbcRow.gridx = 5;
            add(col5Panel, gbcRow);

            gbcRow.gridx = 6;
            add(col6Panel, gbcRow);

            gbcRow.gridx = 7;
            add(col7Panel, gbcRow);

            gbcRow.gridy++;

        }
    }

    public static void main(String[] args) {
    	
    	Dashboard frame = new Dashboard();
    	frame.setVisible(false);
    	LoginWindow login = new LoginWindow(frame);

        
    }

}

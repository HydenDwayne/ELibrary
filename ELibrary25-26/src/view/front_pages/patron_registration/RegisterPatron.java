package view.front_pages.patron_registration;
import javax.swing.*;

import view.RoundedComponents.*;
import view.fonts.*;
import view.front_pages.FilePath;

import java.awt.*;
import java.awt.event.*;
import view.front_pages.*;


public class RegisterPatron extends JFrame{
	
	static String imgFilePath = FilePath.getImgFilePath();
	
	
    
	CardLayout cardLayout;
	JPanel cardPanel;
	
	private String campus = "Main";
	
	GeneralModal genModal;
	StudentModal studModal;

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public RegisterPatron(Dashboard frame, LoginWindow lw) {


	    BackgroundPanel bgPanel = new BackgroundPanel(imgFilePath + "blurred_bg.jpg");

	    cardLayout = new CardLayout();
	    cardPanel = new JPanel(cardLayout);
	    cardPanel.setOpaque(false); 

	    genModal = new GeneralModal(lw, this);
	    studModal = new StudentModal(this, genModal);
	    
	    
	    JPanel genWrapper = new JPanel(new GridBagLayout());
        genWrapper.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        genWrapper.add(genModal, gbc);

        JPanel studWrapper = new JPanel(new GridBagLayout());
        studWrapper.setOpaque(false);
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.anchor = GridBagConstraints.CENTER;
        studWrapper.add(studModal, gbc2);

        cardPanel.add(genWrapper, "general");
        cardPanel.add(studWrapper, "student");

	    bgPanel.setLayout(new BorderLayout());
	    bgPanel.add(cardPanel, BorderLayout.CENTER);

	    setContentPane(bgPanel);

	    cardLayout.show(cardPanel, "general");


		// Frame settings
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
            	bgPanel.requestFocusInWindow();
            }
        });
        
		setTitle("E-Library Management System");
		setContentPane(bgPanel);
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
	
	public void showCard(String cardName) {
	    if (cardName.equals("student")) {
	        studModal.setCampusCode(campus);

	        studModal.updateVisibility();
	        studModal.resizeModal();
	    }

	    cardLayout.show(cardPanel, cardName);
	}

	
}

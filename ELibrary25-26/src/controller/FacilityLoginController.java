package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.DAOs.FacilityLogin.*;
import view.RoundedComponents.RoundedPanel;
import view.facility_panels.IPadArea;
import view.facility_panels.IndividualStudyRoom;
import view.facility_panels.RelaxationRoom;
import view.facility_panels.SmartDeviceZone;
import view.fonts.Fonts;

import java.time.*;
import java.time.format.*;

public class FacilityLoginController {
	
	private FacilityLoginDAOImp daoLogin = new FacilityLoginDAOImp();
	private IPadArea iPad;
	private RelaxationRoom relax;
	private IndividualStudyRoom isr;
	private SmartDeviceZone sdz;
	
	int panelRadius = 20;
	Fonts poppins = new Fonts("Poppins", 12f);
    Font poppins10Style = poppins.getAppliedFont();
    
    Fonts introRust = new Fonts("IntroRust", 18f);
    Font introRustStyle = introRust.getAppliedFont();
	
	public FacilityLoginController(IPadArea iPad, JPanel cardCont, GridBagConstraints gbc) {
		this.iPad = iPad;
		List<DAOFacilityLogin> cards = daoLogin.getLoggedIn("iPAD");
		
		for (int i = 1; i <= 45; i++) {
            RoundedPanel card = new RoundedPanel(panelRadius);
            card.setPreferredSize(new Dimension(150,80));
            card.setBackground(Color.decode("#842b28"));

            // upper card
            JPanel upperCard = new JPanel();
            upperCard.setOpaque(false);
            upperCard.setLayout(new BorderLayout());

            JLabel number = new JLabel("iPad#" + i);
            number.setFont(introRustStyle);
            number.setForeground(Color.WHITE);

            JPanel numberCont = new JPanel();
            numberCont.setOpaque(false);
            numberCont.setPreferredSize(new Dimension(145, 20));
            numberCont.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
            numberCont.setLayout(new BorderLayout());
            numberCont.add(number, BorderLayout.WEST);

            RoundedPanel statusCircle = new RoundedPanel(20);
            statusCircle.setPreferredSize(new Dimension(14,1));
            statusCircle.setBackground(Color.decode("#00bf63"));
            numberCont.add(statusCircle, BorderLayout.EAST);

            upperCard.add(numberCont, BorderLayout.NORTH);
            card.add(upperCard, BorderLayout.NORTH);

            // center card
            JPanel centerCard = new JPanel();
            centerCard.setOpaque(false);
            centerCard.setLayout(new BorderLayout());

            JLabel patNumber = new JLabel("--");
            patNumber.setFont(poppins10Style);
            patNumber.setForeground(Color.WHITE);

            JPanel patNumberCont = new JPanel();
            patNumberCont.setOpaque(false);
            patNumberCont.setPreferredSize(new Dimension(145, 12));
            patNumberCont.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
            patNumberCont.setLayout(new BorderLayout());
            patNumberCont.add(patNumber, BorderLayout.WEST);

            centerCard.add(patNumberCont, BorderLayout.NORTH);
            card.add(centerCard, BorderLayout.CENTER);

            // bottom

            JPanel bottomCard = new JPanel();
            bottomCard.setOpaque(false);
            bottomCard.setLayout(new BorderLayout());

            JLabel timeElapsed = new JLabel("Time elapsed:");
            timeElapsed.setFont(poppins10Style);
            timeElapsed.setForeground(Color.LIGHT_GRAY);

            JLabel timer = new JLabel("--");
            timer.setFont(poppins10Style);
            timer.setForeground(Color.WHITE);

            JPanel timeElapsedCont = new JPanel();
            timeElapsedCont.setOpaque(false);
            timeElapsedCont.setPreferredSize(new Dimension(145, 12));
            timeElapsedCont.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
            timeElapsedCont.setLayout(new BorderLayout());
            timeElapsedCont.add(timeElapsed, BorderLayout.WEST);

            JPanel timerCont = new JPanel();
            timerCont.setOpaque(false);
            timerCont.setPreferredSize(new Dimension(145, 12));
            timerCont.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
            timerCont.setLayout(new BorderLayout());
            timerCont.add(timer, BorderLayout.WEST);

            bottomCard.add(timeElapsedCont, BorderLayout.NORTH);
            bottomCard.add(timerCont, BorderLayout.SOUTH);
            card.add(bottomCard, BorderLayout.SOUTH);
            
            for (DAOFacilityLogin indivCard : cards) {
				if (i == indivCard.getCardNo()) {
					patNumber.setText(indivCard.getPatronID());
					statusCircle.setBackground(Color.decode("#f8c169"));
					
					
					String startStr = indivCard.getPatronTimeIn();
					// Remove milliseconds if present
					if (startStr.contains(".")) {
					    startStr = startStr.substring(0, startStr.indexOf('.'));
					}

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

					LocalDateTime start = LocalDateTime.parse(startStr, formatter);
					LocalDateTime now   = LocalDateTime.now();

					Duration elapsed = Duration.between(start, now);
					
					
					timer.setText(elapsed.toHours() + "hr/s, " + elapsed.toMinutes() + "min/s");
				}
			}

            cardCont.add(card, gbc);

            gbc.gridx++;

            if (i % 7 == 0) {
                gbc.gridx = 1;
                gbc.gridy++;
            }
        }
	}
	
	public FacilityLoginController(IndividualStudyRoom isr, JPanel cardCont, GridBagConstraints gbc) {
		this.isr = isr;
		List<DAOFacilityLogin> cards = daoLogin.getLoggedIn("ISR");
		
		for (int i = 1; i <= 20; i++) {
            RoundedPanel card = new RoundedPanel(panelRadius);
            card.setPreferredSize(new Dimension(225,80));
            card.setBackground(Color.decode("#842b28"));

            // upper card
            JPanel upperCard = new JPanel();
            upperCard.setOpaque(false);
            upperCard.setLayout(new BorderLayout());

            JLabel number = new JLabel("#" + i);
            number.setFont(introRustStyle);
            number.setForeground(Color.WHITE);

            JPanel numberCont = new JPanel();
            numberCont.setOpaque(false);
            numberCont.setPreferredSize(new Dimension(220, 20));
            numberCont.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
            numberCont.setLayout(new BorderLayout());
            numberCont.add(number, BorderLayout.WEST);

            RoundedPanel statusCircle = new RoundedPanel(20);
            statusCircle.setPreferredSize(new Dimension(14,1));
            statusCircle.setBackground(Color.decode("#00bf63"));
            numberCont.add(statusCircle, BorderLayout.EAST);

            upperCard.add(numberCont, BorderLayout.NORTH);
            card.add(upperCard, BorderLayout.NORTH);

            // center card
            JPanel centerCard = new JPanel();
            centerCard.setOpaque(false);
            centerCard.setLayout(new BorderLayout());

            JLabel patNumber = new JLabel("--");
            patNumber.setFont(poppins10Style);
            patNumber.setForeground(Color.WHITE);

            JPanel patNumberCont = new JPanel();
            patNumberCont.setOpaque(false);
            patNumberCont.setPreferredSize(new Dimension(220, 12));
            patNumberCont.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
            patNumberCont.setLayout(new BorderLayout());
            patNumberCont.add(patNumber, BorderLayout.WEST);

            centerCard.add(patNumberCont, BorderLayout.NORTH);
            card.add(centerCard, BorderLayout.CENTER);

            // bottom

            JPanel bottomCard = new JPanel();
            bottomCard.setOpaque(false);
            bottomCard.setLayout(new BorderLayout());

            JLabel timeElapsed = new JLabel("Time elapsed:");
            timeElapsed.setFont(poppins10Style);
            timeElapsed.setForeground(Color.LIGHT_GRAY);

            JLabel timer = new JLabel("--");
            timer.setFont(poppins10Style);
            timer.setForeground(Color.WHITE);

            JPanel timeElapsedCont = new JPanel();
            timeElapsedCont.setOpaque(false);
            timeElapsedCont.setPreferredSize(new Dimension(220, 12));
            timeElapsedCont.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
            timeElapsedCont.setLayout(new BorderLayout());
            timeElapsedCont.add(timeElapsed, BorderLayout.WEST);

            JPanel timerCont = new JPanel();
            timerCont.setOpaque(false);
            timerCont.setPreferredSize(new Dimension(220, 12));
            timerCont.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
            timerCont.setLayout(new BorderLayout());
            timerCont.add(timer, BorderLayout.WEST);

            bottomCard.add(timeElapsedCont, BorderLayout.NORTH);
            bottomCard.add(timerCont, BorderLayout.SOUTH);
            card.add(bottomCard, BorderLayout.SOUTH);
            
            for (DAOFacilityLogin indivCard : cards) {
				if (i == indivCard.getCardNo()) {
					patNumber.setText(indivCard.getPatronID());
					statusCircle.setBackground(Color.decode("#f8c169"));
					
					
					String startStr = indivCard.getPatronTimeIn();

					// Remove milliseconds if present
					if (startStr.contains(".")) {
					    startStr = startStr.substring(0, startStr.indexOf('.'));
					}

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

					LocalDateTime start = LocalDateTime.parse(startStr, formatter);
					LocalDateTime now   = LocalDateTime.now();

					Duration elapsed = Duration.between(start, now);
					
					
					timer.setText(elapsed.toHours() + "hr/s, " + elapsed.toMinutes() + "min/s");
				}
			}

            cardCont.add(card, gbc);

            gbc.gridx++;

            if (i % 5 == 0) {
                gbc.gridx = 1;
                gbc.gridy++;
            }
        }
	}
	
	public FacilityLoginController(RelaxationRoom relax, JPanel cardCont, GridBagConstraints gbc) {
		this.relax = relax;
		List<DAOFacilityLogin> cards = daoLogin.getLoggedIn("RelaxRoom");
		
		for (int i = 1; i <= 25; i++) {
            RoundedPanel card = new RoundedPanel(panelRadius);
            card.setPreferredSize(new Dimension(150,80));
            card.setBackground(Color.decode("#842b28"));

            // upper card
            JPanel upperCard = new JPanel();
            upperCard.setOpaque(false);
            upperCard.setLayout(new BorderLayout());

            JLabel number = new JLabel("#" + i);
            number.setFont(introRustStyle);
            number.setForeground(Color.WHITE);

            JPanel numberCont = new JPanel();
            numberCont.setOpaque(false);
            numberCont.setPreferredSize(new Dimension(145, 20));
            numberCont.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
            numberCont.setLayout(new BorderLayout());
            numberCont.add(number, BorderLayout.WEST);

            RoundedPanel statusCircle = new RoundedPanel(20);
            statusCircle.setPreferredSize(new Dimension(14,1));
            statusCircle.setBackground(Color.decode("#00bf63"));
            numberCont.add(statusCircle, BorderLayout.EAST);

            upperCard.add(numberCont, BorderLayout.NORTH);
            card.add(upperCard, BorderLayout.NORTH);

            // center card
            JPanel centerCard = new JPanel();
            centerCard.setOpaque(false);
            centerCard.setLayout(new BorderLayout());

            JLabel patNumber = new JLabel("--");
            patNumber.setFont(poppins10Style);
            patNumber.setForeground(Color.WHITE);

            JPanel patNumberCont = new JPanel();
            patNumberCont.setOpaque(false);
            patNumberCont.setPreferredSize(new Dimension(145, 12));
            patNumberCont.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
            patNumberCont.setLayout(new BorderLayout());
            patNumberCont.add(patNumber, BorderLayout.WEST);

            centerCard.add(patNumberCont, BorderLayout.NORTH);
            card.add(centerCard, BorderLayout.CENTER);

            // bottom

            JPanel bottomCard = new JPanel();
            bottomCard.setOpaque(false);
            bottomCard.setLayout(new BorderLayout());

            JLabel timeElapsed = new JLabel("Time elapsed:");
            timeElapsed.setFont(poppins10Style);
            timeElapsed.setForeground(Color.LIGHT_GRAY);

            JLabel timer = new JLabel("--");
            timer.setFont(poppins10Style);
            timer.setForeground(Color.WHITE);

            JPanel timeElapsedCont = new JPanel();
            timeElapsedCont.setOpaque(false);
            timeElapsedCont.setPreferredSize(new Dimension(145, 12));
            timeElapsedCont.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
            timeElapsedCont.setLayout(new BorderLayout());
            timeElapsedCont.add(timeElapsed, BorderLayout.WEST);

            JPanel timerCont = new JPanel();
            timerCont.setOpaque(false);
            timerCont.setPreferredSize(new Dimension(145, 12));
            timerCont.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
            timerCont.setLayout(new BorderLayout());
            timerCont.add(timer, BorderLayout.WEST);

            bottomCard.add(timeElapsedCont, BorderLayout.NORTH);
            bottomCard.add(timerCont, BorderLayout.SOUTH);
            card.add(bottomCard, BorderLayout.SOUTH);
            
            for (DAOFacilityLogin indivCard : cards) {
				if (i == indivCard.getCardNo()) {
					patNumber.setText(indivCard.getPatronID());
					statusCircle.setBackground(Color.decode("#f8c169"));
					
					
					String startStr = indivCard.getPatronTimeIn();
					// Remove milliseconds if present
					if (startStr.contains(".")) {
					    startStr = startStr.substring(0, startStr.indexOf('.'));
					}

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

					LocalDateTime start = LocalDateTime.parse(startStr, formatter);
					LocalDateTime now   = LocalDateTime.now();

					Duration elapsed = Duration.between(start, now);
					
					
					timer.setText(elapsed.toHours() + "hr/s, " + elapsed.toMinutes() + "min/s");
				}
			}

            cardCont.add(card, gbc);

            gbc.gridx++;

            if (i % 7 == 0) {
                gbc.gridx = 1;
                gbc.gridy++;
            }
        }
	
	}
	
	public FacilityLoginController(SmartDeviceZone sdz, JPanel cardCont, GridBagConstraints gbc) {
		this.sdz = sdz;
		List<DAOFacilityLogin> cards = daoLogin.getLoggedIn("SDZ");
		
		for (int i = 1; i <= 192; i++) {
            RoundedPanel card = new RoundedPanel(panelRadius);
            card.setPreferredSize(new Dimension(150,80));
            card.setBackground(Color.decode("#842b28"));

            // upper card
            JPanel upperCard = new JPanel();
            upperCard.setOpaque(false);
            upperCard.setLayout(new BorderLayout());

            JLabel number = new JLabel("PC#" + i);
            number.setFont(introRustStyle);
            number.setForeground(Color.WHITE);

            JPanel numberCont = new JPanel();
            numberCont.setOpaque(false);
            numberCont.setPreferredSize(new Dimension(145, 20));
            numberCont.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
            numberCont.setLayout(new BorderLayout());
            numberCont.add(number, BorderLayout.WEST);

            RoundedPanel statusCircle = new RoundedPanel(20);
            statusCircle.setPreferredSize(new Dimension(14,1));
            statusCircle.setBackground(Color.decode("#00bf63"));
            numberCont.add(statusCircle, BorderLayout.EAST);

            upperCard.add(numberCont, BorderLayout.NORTH);
            card.add(upperCard, BorderLayout.NORTH);

            // center card
            JPanel centerCard = new JPanel();
            centerCard.setOpaque(false);
            centerCard.setLayout(new BorderLayout());

            JLabel patNumber = new JLabel("--");
            patNumber.setFont(poppins10Style);
            patNumber.setForeground(Color.WHITE);

            JPanel patNumberCont = new JPanel();
            patNumberCont.setOpaque(false);
            patNumberCont.setPreferredSize(new Dimension(145, 12));
            patNumberCont.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
            patNumberCont.setLayout(new BorderLayout());
            patNumberCont.add(patNumber, BorderLayout.WEST);

            centerCard.add(patNumberCont, BorderLayout.NORTH);
            card.add(centerCard, BorderLayout.CENTER);

            // bottom

            JPanel bottomCard = new JPanel();
            bottomCard.setOpaque(false);
            bottomCard.setLayout(new BorderLayout());

            JLabel timeElapsed = new JLabel("Time elapsed:");
            timeElapsed.setFont(poppins10Style);
            timeElapsed.setForeground(Color.LIGHT_GRAY);

            JLabel timer = new JLabel("--");
            timer.setFont(poppins10Style);
            timer.setForeground(Color.WHITE);

            JPanel timeElapsedCont = new JPanel();
            timeElapsedCont.setOpaque(false);
            timeElapsedCont.setPreferredSize(new Dimension(145, 12));
            timeElapsedCont.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
            timeElapsedCont.setLayout(new BorderLayout());
            timeElapsedCont.add(timeElapsed, BorderLayout.WEST);

            JPanel timerCont = new JPanel();
            timerCont.setOpaque(false);
            timerCont.setPreferredSize(new Dimension(145, 12));
            timerCont.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
            timerCont.setLayout(new BorderLayout());
            timerCont.add(timer, BorderLayout.WEST);

            bottomCard.add(timeElapsedCont, BorderLayout.NORTH);
            bottomCard.add(timerCont, BorderLayout.SOUTH);
            card.add(bottomCard, BorderLayout.SOUTH);
            
            for (DAOFacilityLogin indivCard : cards) {
				if (i == indivCard.getCardNo()) {
					patNumber.setText(indivCard.getPatronID());
					statusCircle.setBackground(Color.decode("#f8c169"));
					
					
					String startStr = indivCard.getPatronTimeIn();
					// Remove milliseconds if present
					if (startStr.contains(".")) {
					    startStr = startStr.substring(0, startStr.indexOf('.'));
					}

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

					LocalDateTime start = LocalDateTime.parse(startStr, formatter);
					LocalDateTime now   = LocalDateTime.now();

					Duration elapsed = Duration.between(start, now);
					
					
					timer.setText(elapsed.toHours() + "hr/s, " + elapsed.toMinutes() + "min/s");
				}
			}

            cardCont.add(card, gbc);

            gbc.gridx++;

            if (i % 7 == 0) {
                gbc.gridx = 1;
                gbc.gridy++;
            }
        }
	}
}

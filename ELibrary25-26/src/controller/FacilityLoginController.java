package controller;

import java.awt.*;
import java.util.List;
import javax.swing.*;

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

	private JLabel[] patLabels = new JLabel[46];
	private JLabel[] timerLabels = new JLabel[46];
	private RoundedPanel[] statusCircles = new RoundedPanel[46];
	private JLabel[] isrPatLabels = new JLabel[21];
	private JLabel[] isrTimerLabels = new JLabel[21];
	private RoundedPanel[] isrStatusCircles = new RoundedPanel[21];
	private JLabel[] relaxPatLabels = new JLabel[26];
	private JLabel[] relaxTimerLabels = new JLabel[26];
	private RoundedPanel[] relaxStatusCircles = new RoundedPanel[26];
	private JLabel[] sdzPatLabels = new JLabel[193];
	private JLabel[] sdzTimerLabels = new JLabel[193];
	private RoundedPanel[] sdzStatusCircles = new RoundedPanel[193];
	

	private void startIPadTimer() {
		Timer timer = new Timer(1000, e -> reloadIPAD()
		);
		timer.start();
	}
	private void startISRTimer() {
		Timer timer = new Timer(1000, e -> reloadISR()
		);
		timer.start();
	}
	private void startRelaxTimer() {
		Timer timer = new Timer(1000, e -> reloadRelax()
		);
		timer.start();
	}
	private void startSDZTimer() {
		Timer timer = new Timer(1000, e -> reloadSDZ()
		);
		timer.start();
	}

	public void reloadIPAD() {
	    List<DAOFacilityLogin> cards = daoLogin.getLoggedIn("iPAD");

	    // reset all cards
	    for (int i = 1; i <= 45; i++) {
	        patLabels[i].setText("--");
	        timerLabels[i].setText("--");
	        statusCircles[i].setBackground(Color.decode("#00bf63"));
	    }

	    DateTimeFormatter formatter =
	        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	    for (DAOFacilityLogin c : cards) {
	        int i = c.getCardNo();
	        if (i < 1 || i > 45) continue;

	        patLabels[i].setText(c.getPatronID());
	        statusCircles[i].setBackground(Color.decode("#f8c169"));

	        String startStr = c.getPatronTimeIn().split("\\.")[0];
	        LocalDateTime start = LocalDateTime.parse(startStr, formatter);

	        Duration elapsed = Duration.between(start, LocalDateTime.now());

	        long hrs = elapsed.toHours();
	        long mins = elapsed.toMinutes() % 60;
	        long secs = elapsed.toSeconds() % 60;

	        timerLabels[i].setText(hrs + " hr/s, " + mins + " min/s " + secs+"s");
	    }
	}
	
	public void reloadISR() {
	    List<DAOFacilityLogin> cards = daoLogin.getLoggedIn("ISR");

	    for (int i = 1; i <= 20; i++) {
	        isrPatLabels[i].setText("--");
	        isrTimerLabels[i].setText("--");
	        isrStatusCircles[i].setBackground(Color.decode("#00bf63"));
	    }

	    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	    for (DAOFacilityLogin c : cards) {
	        int i = c.getCardNo();
	        if (i < 1 || i > 20) continue;

	        isrPatLabels[i].setText(c.getPatronID());
	        isrStatusCircles[i].setBackground(Color.decode("#f8c169"));

	        LocalDateTime start =
	            LocalDateTime.parse(c.getPatronTimeIn().split("\\.")[0], fmt);

	        long sec = Duration.between(start, LocalDateTime.now()).getSeconds();
	        isrTimerLabels[i].setText(
	            String.format("%02d:%02d:%02d", sec/3600, (sec%3600)/60, sec%60)
	        );
	    }
	}
	
	public void reloadRelax() {
	    List<DAOFacilityLogin> cards = daoLogin.getLoggedIn("RelaxRoom");
	    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	    for (int i = 1; i <= 25; i++) {
	        relaxPatLabels[i].setText("--");
	        relaxTimerLabels[i].setText("--");
	        relaxStatusCircles[i].setBackground(Color.decode("#00bf63"));
	    }

	    for (DAOFacilityLogin c : cards) {
	        int i = c.getCardNo();
	        if (i < 1 || i > 25) continue;

	        relaxPatLabels[i].setText(c.getPatronID());
	        relaxStatusCircles[i].setBackground(Color.decode("#f8c169"));

	        long sec = Duration.between(
	            LocalDateTime.parse(c.getPatronTimeIn().split("\\.")[0], fmt),
	            LocalDateTime.now()
	        ).getSeconds();

	        relaxTimerLabels[i].setText(
	            String.format("%02d:%02d:%02d", sec/3600, (sec%3600)/60, sec%60)
	        );
	    }
	}
	
	public void reloadSDZ() {
	    List<DAOFacilityLogin> cards = daoLogin.getLoggedIn("SDZ");
	    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	    for (int i = 1; i <= 192; i++) {
	        sdzPatLabels[i].setText("--");
	        sdzTimerLabels[i].setText("--");
	        sdzStatusCircles[i].setBackground(Color.decode("#00bf63"));
	    }

	    for (DAOFacilityLogin c : cards) {
	        int i = c.getCardNo();
	        if (i < 1 || i > 192) continue;

	        sdzPatLabels[i].setText(c.getPatronID());
	        sdzStatusCircles[i].setBackground(Color.decode("#f8c169"));

	        long sec = Duration.between(
	            LocalDateTime.parse(c.getPatronTimeIn().split("\\.")[0], fmt),
	            LocalDateTime.now()
	        ).getSeconds();

	        sdzTimerLabels[i].setText(
	            String.format("%02d:%02d:%02d", sec/3600, (sec%3600)/60, sec%60)
	        );
	    }
	}


	public FacilityLoginController(IPadArea iPad, JPanel cardCont, GridBagConstraints gbc) {
		this.iPad = iPad;
		
		
		
		
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
            
         // ✅ VERY IMPORTANT: store references
            patLabels[i] = patNumber;
            timerLabels[i] = timer;
            statusCircles[i] = statusCircle;
            
            

            cardCont.add(card, gbc);

            gbc.gridx++;

            if (i % 7 == 0) {
                gbc.gridx = 1;
                gbc.gridy++;
            }
        }
		startIPadTimer();
	}

	public FacilityLoginController(IndividualStudyRoom isr, JPanel cardCont, GridBagConstraints gbc) {
		this.isr = isr;
		List<DAOFacilityLogin> cards = daoLogin.getLoggedIn("ISR");

		for (int i = 1; i <= 20; i++) {
			RoundedPanel card = new RoundedPanel(panelRadius);
			card.setPreferredSize(new Dimension(225, 80));
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
			statusCircle.setPreferredSize(new Dimension(14, 1));
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

			isrPatLabels[i] = patNumber;
			isrTimerLabels[i] = timer;
			isrStatusCircles[i] = statusCircle;

			cardCont.add(card, gbc);

			gbc.gridx++;

			if (i % 5 == 0) {
				gbc.gridx = 1;
				gbc.gridy++;
			}
		}
		
		startISRTimer();
	}

	public FacilityLoginController(RelaxationRoom relax, JPanel cardCont, GridBagConstraints gbc) {
		this.relax = relax;
		List<DAOFacilityLogin> cards = daoLogin.getLoggedIn("RelaxRoom");

		for (int i = 1; i <= 25; i++) {
			RoundedPanel card = new RoundedPanel(panelRadius);
			card.setPreferredSize(new Dimension(150, 80));
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
			statusCircle.setPreferredSize(new Dimension(14, 1));
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

			relaxStatusCircles[i] = statusCircle;
			relaxTimerLabels[i] = timer;
			relaxPatLabels[i] = patNumber;

			cardCont.add(card, gbc);

			gbc.gridx++;

			if (i % 7 == 0) {
				gbc.gridx = 1;
				gbc.gridy++;
			}
		}
		startRelaxTimer();

	}

	public FacilityLoginController(SmartDeviceZone sdz, JPanel cardCont, GridBagConstraints gbc) {
		this.sdz = sdz;

		for (int i = 1; i <= 192; i++) {
			RoundedPanel card = new RoundedPanel(panelRadius);
			card.setPreferredSize(new Dimension(150, 80));
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
			statusCircle.setPreferredSize(new Dimension(14, 1));
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

			sdzPatLabels[i] = patNumber;
			sdzTimerLabels[i] = timer;
			sdzStatusCircles[i] = statusCircle;

			cardCont.add(card, gbc);

			gbc.gridx++;

			if (i % 7 == 0) {
				gbc.gridx = 1;
				gbc.gridy++;
			}
		}
		startSDZTimer();
	}
}

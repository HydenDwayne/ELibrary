package view.facility_panels;

import view.RoundedComponents.RoundedPanel;
import view.fonts.Fonts;
import java.awt.*;
import javax.swing.*;

public class RelaxationRoom extends JPanel {

    public RelaxationRoom() {
        setOpaque(false);
        int panelRadius = 20;
        JPanel sessionCont = new JPanel();
        sessionCont.setPreferredSize(new Dimension(1200, 400));
        sessionCont.setLayout(new BorderLayout());
        sessionCont.setOpaque(false);

        JLabel sessionText = new JLabel();
        sessionText.setText("Sessions");
        sessionText.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        sessionText.setForeground(Color.decode("#6d2321"));

        Fonts introRust = new Fonts("IntroRust", 18f);
        Font introRustStyle = introRust.getAppliedFont();
        sessionText.setFont(introRustStyle);

        sessionCont.add(sessionText, BorderLayout.NORTH);

        JPanel cardCont = new JPanel();
        cardCont.setOpaque(false);
        cardCont.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.insets = new Insets(5, 5, 5, 5);

        Fonts poppins = new Fonts("Poppins", 12f);
        Font poppins10Style = poppins.getAppliedFont();

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
            statusCircle.setBackground(Color.decode("#f8c169"));
            numberCont.add(statusCircle, BorderLayout.EAST);

            upperCard.add(numberCont, BorderLayout.NORTH);
            card.add(upperCard, BorderLayout.NORTH);

            // center card
            JPanel centerCard = new JPanel();
            centerCard.setOpaque(false);
            centerCard.setLayout(new BorderLayout());

            JLabel patNumber = new JLabel("2024105301");
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

            JLabel timer = new JLabel("1hr, 32mins");
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

            cardCont.add(card, gbc);

            gbc.gridx++;

            if (i % 7 == 0) {
                gbc.gridx = 1;
                gbc.gridy++;
            }
        }

        sessionCont.add(cardCont, BorderLayout.CENTER);

        add(sessionCont);
        setOpaque(false);
    }
}

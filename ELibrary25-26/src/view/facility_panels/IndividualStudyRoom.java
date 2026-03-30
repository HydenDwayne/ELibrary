package view.facility_panels;

import view.fonts.Fonts;
import java.awt.*;
import java.time.*;
import javax.swing.*;

import controller.FacilityLoginController;
import view.RoundedComponents.*;

public class IndividualStudyRoom extends JPanel {

    public IndividualStudyRoom() {
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

        FacilityLoginController comp = new FacilityLoginController(this, cardCont, gbc);

        sessionCont.add(cardCont, BorderLayout.CENTER);

        add(sessionCont);
        setOpaque(false);
    }
}

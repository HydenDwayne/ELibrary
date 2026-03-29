package view.toolbar_tabs;

import view.RoundedComponents.*;
import view.book_panels.*;
import view.facility_panels.*;
import view.fonts.Fonts;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FacilitiesTab extends JPanel implements ActionListener {

    RoundedComboBox<String> dropdownFacility;
    JPanel slctdFaciCont;

    Amphitheater amp = new Amphitheater();
    DiscussionRoom1 dr1 = new DiscussionRoom1();
    DiscussionRoom2 dr2 = new DiscussionRoom2();
    IndividualStudyRoom isr = new IndividualStudyRoom();
    IPadArea ia = new IPadArea();
    MultipurposeHall1 mh1 = new MultipurposeHall1();
    MultipurposeHall2 mh2 = new MultipurposeHall2();
    RelaxationRoom rr = new RelaxationRoom();
    SmartDeviceZone sdz = new SmartDeviceZone();
    SmartLearningRoom1 slr1 = new SmartLearningRoom1();
    SmartLearningRoom2 slr2 = new SmartLearningRoom2();
    TeleconferencingRoom tcr = new TeleconferencingRoom();

    JLabel tabLabel = new JLabel("");

    public FacilitiesTab() {
        int panelRadius = 20;

        JPanel facilitiesTab = new JPanel();
        facilitiesTab.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(40, 10, 10, 10);

        // container of the entire center widget
        RoundedPanel facContainer = new RoundedPanel(panelRadius);
        facContainer.setPreferredSize(new Dimension(1280, 560));
        facContainer.setLayout(new BorderLayout());
        facContainer.setBackground(Color.WHITE);

        setBackground(Color.decode("#c4c4c4"));
        // red part
        RoundedPanel slctFacility = new RoundedPanel(panelRadius);
        slctFacility.setLayout(new GridBagLayout());
        GridBagConstraints gbcRed = new GridBagConstraints();
        gbcRed.gridy = 1;
        gbcRed.gridx = 1;
        gbcRed.insets = new Insets(10, 30, 10, 10);
        slctFacility.setBackground(Color.decode("#842b28"));
        slctFacility.setPreferredSize(new Dimension(450, 80));

        JLabel selectFacility = new JLabel("SELECT FACILITY");
        selectFacility.setForeground(Color.WHITE);

        Fonts introRust = new Fonts("IntroRust", 18f);
        Font introRustStyle = introRust.getAppliedFont();
        selectFacility.setFont(introRustStyle);

        slctFacility.add(selectFacility, gbcRed);

        // combobox
        JPanel cmbbxPanel = new JPanel();
        cmbbxPanel.setOpaque(false);

        String[] facilityItems = { //unfinished == replace with stored procedure that fills the value of this array with facilities from the DB
            "Amphitheater",
            "Discussion Room 1",
            "Discussion Room 2",
            "iPad Area",
            "Individual Study Room",
            "Multipurpose Hall 1",
            "Multipurpose Hall 2",
            "Relaxation Room",
            "Smart Device Zone",
            "Smart Learning Room 1",
            "Smart Learning Room 2",
            "Teleconferencing Room"
        };
        dropdownFacility = new RoundedComboBox<>(facilityItems, panelRadius);
        dropdownFacility.setPlaceholder("none");
        dropdownFacility.setPreferredSize(new Dimension(200, 30));
        dropdownFacility.addActionListener(this);

        gbcRed.gridx++;

        cmbbxPanel.add(dropdownFacility);
        slctFacility.add(cmbbxPanel, gbcRed);

        // container of the red part
        JPanel topWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topWrapper.setOpaque(false);
        topWrapper.add(slctFacility);

        // no red part
        tabLabel.setText("Amphitheather Schedule");
        tabLabel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        tabLabel.setForeground(Color.decode("#6d2321"));

        Fonts introRust22 = new Fonts("IntroRust", 22f);
        Font introRust22Style = introRust22.getAppliedFont();
        tabLabel.setFont(introRust22Style);

        topWrapper.add(tabLabel, BorderLayout.CENTER);

        facContainer.add(topWrapper, BorderLayout.NORTH);

        // selected facility items
        JPanel slctdPadding = new JPanel();
        slctdPadding.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        // slctdPadding.setOpaque(false);
        slctdFaciCont = new JPanel();
        slctdFaciCont.setPreferredSize(new Dimension(1200, 400));
        slctdFaciCont.setOpaque(false);
        slctdPadding.setOpaque(false);

        slctdPadding.add(slctdFaciCont, BorderLayout.CENTER);
        facContainer.add(slctdPadding, BorderLayout.CENTER);

        slctdFaciCont.add(amp);

        dr1.setVisible(false);
        slctdFaciCont.add(dr1);

        dr2.setVisible(false);
        slctdFaciCont.add(dr2);

        isr.setVisible(false);
        slctdFaciCont.add(isr);

        ia.setVisible(false);
        slctdFaciCont.add(ia);

        mh1.setVisible(false);
        slctdFaciCont.add(mh1);

        mh2.setVisible(false);
        slctdFaciCont.add(mh2);

        rr.setVisible(false);
        slctdFaciCont.add(rr);

        sdz.setVisible(false);
        slctdFaciCont.add(sdz);

        slr1.setVisible(false);
        slctdFaciCont.add(slr1);

        slr2.setVisible(false);
        slctdFaciCont.add(slr2);

        tcr.setVisible(false);
        slctdFaciCont.add(tcr);

        // =========================
        facilitiesTab.add(facContainer, gbc);
        facilitiesTab.setOpaque(false);
        add(facilitiesTab);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        amp.setVisible(false);
        dr1.setVisible(false);
        dr2.setVisible(false);
        isr.setVisible(false);
        ia.setVisible(false);
        mh1.setVisible(false);
        mh2.setVisible(false);
        rr.setVisible(false);
        sdz.setVisible(false);
        slr1.setVisible(false);
        slr2.setVisible(false);
        tcr.setVisible(false);

        String selectedFac = (String) dropdownFacility.getSelectedItem();

        switch (selectedFac) {
            case "Amphitheater":
                tabLabel.setText("Amphitheater");
                amp.setVisible(true);
                break;
            case "Discussion Room 1":
                tabLabel.setText("Discussion Room 1");
                dr1.setVisible(true);
                break;
            case "Discussion Room 2":
                tabLabel.setText("Discussion Room 2");
                dr2.setVisible(true);
                break;
            case "iPad Area":
                tabLabel.setText("iPad Area");
                ia.setVisible(true);
                break;
            case "Individual Study Room":
                tabLabel.setText("Individual Study Room");
                isr.setVisible(true);
                break;
            case "Multipurpose Hall 1":
                tabLabel.setText("Multipurpose Hall 1");
                mh1.setVisible(true);
                break;
            case "Multipurpose Hall 2":
                tabLabel.setText("Multipurpose Hall 2");
                mh2.setVisible(true);
                break;
            case "Relaxation Room":
                tabLabel.setText("Relaxation Room");
                rr.setVisible(true);
                break;
            case "Smart Device Zone":
                tabLabel.setText("Smart Device Zone");
                sdz.setVisible(true);
                break;
            case "Smart Learning Room 1":
                tabLabel.setText("Smart Learning Room 1");
                slr1.setVisible(true);
                break;
            case "Smart Learning Room 2":
                tabLabel.setText("Smart Learning Room 2");
                slr2.setVisible(true);
                break;
            case "Teleconferencing Room":
                tabLabel.setText("Teleconferencing Room");
                tcr.setVisible(true);
                break;
            default:
                throw new AssertionError("Unknown facility selected: " + selectedFac);
        }
    }
}

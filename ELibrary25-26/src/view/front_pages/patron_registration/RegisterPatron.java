package view.front_pages.patron_registration;
import javax.swing.*;

import controller.PatronRegistrationController;
import view.RoundedComponents.*;
import view.fonts.*;
import view.front_pages.FilePath;

import java.awt.*;
import java.awt.event.*;
import view.front_pages.*;


public class RegisterPatron extends JFrame {

    CardLayout cardLayout;
    JPanel cardPanel;



    GeneralModal genModal;
    StudentModal studModal;
    EmployeeModal empModal;   // ✅ ADD THIS

    private String campus;

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getCampus() {
        return campus;
    }
    
    
    

    public RegisterPatron(Dashboard frame, LoginWindow lw) {

        BackgroundPanel bgPanel = new BackgroundPanel(
                FilePath.getImgFilePath() + "blurred_bg.jpg"
        );

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setOpaque(false);

        genModal  = new GeneralModal(lw, this);
        studModal = new StudentModal(this, genModal);
        empModal  = new EmployeeModal(this, genModal);   // ✅ CREATE EMPLOYEE MODAL

        cardPanel.add(wrap(genModal), "general");
        cardPanel.add(wrap(studModal), "student");
        cardPanel.add(wrap(empModal), "employee"); // ✅ ADD CARD

        bgPanel.setLayout(new BorderLayout());
        bgPanel.add(cardPanel, BorderLayout.CENTER);
        setContentPane(bgPanel);

        cardLayout.show(cardPanel, "general");

        setTitle("E-Library Management System");
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel wrap(JComponent comp) {
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setOpaque(false);
        wrapper.add(comp);
        return wrapper;
    }

    public void showCard(String name) {
        cardLayout.show(cardPanel, name);
    }
}
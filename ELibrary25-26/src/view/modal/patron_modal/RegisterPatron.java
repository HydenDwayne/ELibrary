package view.modal.patron_modal;

import javax.swing.*;
import java.awt.*;

import view.front_pages.FilePath;

public class RegisterPatron extends JPanel {

    CardLayout cardLayout;
    JPanel cardPanel;

    public GeneralModal genModal;
    public StudentModal studModal;
    public EmployeeModal empModal;

    private String campus;

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getCampus() {
        return campus;
    }

    public RegisterPatron() {

        setOpaque(false);
        setLayout(new BorderLayout());






        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setOpaque(false);

        genModal  = new GeneralModal(this);
        studModal = new StudentModal(this, genModal);
        empModal  = new EmployeeModal(this, genModal);

        cardPanel.add(wrap(genModal), "general");
        cardPanel.add(wrap(studModal), "student");
        cardPanel.add(wrap(empModal), "employee");

        cardLayout.show(cardPanel, "general");


        add(cardPanel, BorderLayout.CENTER);
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

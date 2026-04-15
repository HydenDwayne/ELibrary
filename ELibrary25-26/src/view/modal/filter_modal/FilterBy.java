package view.modal.filter_modal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import view.RoundedComponents.*;
import view.front_pages.FilePath;
import view.toolbar_tabs.BooksTab;
import view.fonts.Fonts;

public class FilterBy extends JPanel {

    static final Color MAROON = new Color(132, 43, 40);
    static final Color LIGHT_PINK = new Color(250, 236, 238);
    static final Color DARK_TEXT = new Color(109, 35, 33);
    static final Color FIELD_BORDER = new Color(146, 76, 74);

    static final int PANEL_RADIUS = 20;
    static final int FIELD_RADIUS = 15;


    public FilterBy(BooksTab bt) {

        setOpaque(false);
        setLayout(new BorderLayout());

        
        Font introRust26 = new Fonts("IntroRust", 36f).getAppliedFont();
        Font introRust24 = new Fonts("IntroRust", 24f).getAppliedFont();
        Font poppins16   = new Fonts("Poppins", 16f).getAppliedFont();
        Font poppins12   = new Fonts("Poppins", 12f).getAppliedFont();
        Font poppins10   = new Fonts("Poppins", 10f).getAppliedFont();

        
        RoundedPanel modal = new RoundedPanel(PANEL_RADIUS);
        modal.setLayout(new BorderLayout());
        modal.setPreferredSize(new Dimension(500, 520));
        modal.setBackground(LIGHT_PINK);

        
        JPanel header = new JPanel();
        header.setBackground(MAROON);
        header.setPreferredSize(new Dimension(500, 100));
        header.setBorder(new EmptyBorder(10, 0, 10, 10));

        JPanel headerWrapper = new JPanel(new BorderLayout());
        headerWrapper.setOpaque(false);
        headerWrapper.setPreferredSize(new Dimension(450, 80));

        ImageIcon icon = new ImageIcon(FilePath.image("elib_logo.png"));
        Image scaled = icon.getImage().getScaledInstance(110, 50, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(scaled));
        logo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel headerLabel = new JLabel("BOOKS");
        headerLabel.setFont(introRust26);
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBorder(new EmptyBorder(0, 70, 0, 0));

        headerWrapper.add(logo, BorderLayout.WEST);
        headerWrapper.add(headerLabel, BorderLayout.CENTER);
        header.add(headerWrapper);

        
        JPanel body = new JPanel(new BorderLayout());
        body.setOpaque(false);
        body.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("SEARCH FILTERS", SwingConstants.CENTER);
        title.setFont(introRust24);
        title.setForeground(DARK_TEXT);
        body.add(title, BorderLayout.NORTH);

        JPanel innerBody = new JPanel(new GridBagLayout());
        innerBody.setOpaque(false);
        innerBody.setBorder(new EmptyBorder(5, 5, 5, 5));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = -1;

        
        RoundedTextField heightRef = new RoundedTextField(19, FIELD_RADIUS);
        int fieldHeight = heightRef.getPreferredSize().height;
        
        

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;

        JPanel classSection = new JPanel();
        classSection.setLayout(new BorderLayout(0, 6));
        classSection.setOpaque(false);

        JLabel classTitle = new JLabel("MAJOR BOOK CLASSIFICATION");
        classTitle.setFont(poppins12);
        classTitle.setForeground(DARK_TEXT);
        classSection.add(classTitle, BorderLayout.NORTH);

        JPanel classGrid = new JPanel(new GridLayout(5, 6, 4, 4));
        classGrid.setOpaque(false);

        ButtonGroup classGroup = new ButtonGroup();
        
        

        for (char c = 'A'; c <= 'Z'; c++) {
            JRadioButton rb = new JRadioButton(String.valueOf(c));
            rb.setActionCommand(String.valueOf(c));
            rb.setFont(poppins10);
            rb.setOpaque(false);
            rb.setForeground(DARK_TEXT);
            classGroup.add(rb);
            classGrid.add(rb);
        }

        classSection.add(classGrid, BorderLayout.CENTER);
        innerBody.add(classSection, gbc);

        gbc.gridwidth = 1; 
        
     
        String savedClass = bt.getFilterClass();
        if (savedClass != null && !savedClass.isEmpty()) {
            for (AbstractButton btn :
                    java.util.Collections.list(classGroup.getElements())) {

                if (savedClass.equals(btn.getActionCommand())) {
                    btn.setSelected(true);
                    break;
                }
            }
        }
        

        

        String[] years = {
            "none", "2015","2016","2017","2018","2019",
            "2020","2021","2022","2023","2024","2025"
        };

        gbc.gridy++;
        gbc.gridx = 0;
        JLabel fromLbl = new JLabel("From Year:");
        fromLbl.setFont(poppins16);
        fromLbl.setForeground(DARK_TEXT);
        innerBody.add(fromLbl, gbc);

        gbc.gridx = 1;
        RoundedComboBox<String> fromYear =
                new RoundedComboBox<>(years, FIELD_RADIUS);
        fromYear.setFont(poppins10);
        fromYear.setBorderColor(FIELD_BORDER);
        fromYear.setBorderThickness(1);
        fromYear.setPreferredSize(new Dimension(200, fieldHeight));
        innerBody.add(fromYear, gbc);
        
     
        String savedStartYear = bt.getFilterStartYear();
        if (savedStartYear != null && !savedStartYear.isEmpty()) {
            fromYear.setSelectedItem(savedStartYear);
        } else {
            fromYear.setSelectedIndex(0); 
        }   

        gbc.gridy++;
        gbc.gridx = 0;
        JLabel toLbl = new JLabel("To Year:");
        toLbl.setFont(poppins16);
        toLbl.setForeground(DARK_TEXT);
        innerBody.add(toLbl, gbc);

        gbc.gridx = 1;
        RoundedComboBox<String> toYear =
                new RoundedComboBox<>(years, FIELD_RADIUS);
        toYear.setFont(poppins10);
        toYear.setBorderColor(FIELD_BORDER);
        toYear.setBorderThickness(1);
        toYear.setPreferredSize(new Dimension(200, fieldHeight));
        innerBody.add(toYear, gbc);
        
     
        String savedEndYear = bt.getFilterEndYear();
        if (savedEndYear != null && !savedEndYear.isEmpty()) {
            toYear.setSelectedItem(savedEndYear);
        } else {
            toYear.setSelectedIndex(0); 
        }

        body.add(innerBody, BorderLayout.CENTER);

        

        JPanel footer = new JPanel(new GridLayout(2, 1, 0, 10));
        footer.setPreferredSize(new Dimension(500, 100));
        footer.setBorder(new EmptyBorder(0, 35, 10, 35));
        RoundedButton applyBtn = new RoundedButton("APPLY FILTERS", FIELD_RADIUS);
        applyBtn.setFont(poppins12);
        applyBtn.setBackground(MAROON);
        applyBtn.setForeground(Color.WHITE);
        applyBtn.addActionListener(e -> {

            String selectedString = "";
            ButtonModel selectedModel = classGroup.getSelection();

            if (selectedModel != null) {
                selectedString = selectedModel.getActionCommand();
            }

            String startYear = "";
            String endYear = "";

            int fromYearIdx = fromYear.getSelectedIndex();
            int toYearIdx = toYear.getSelectedIndex();

            if (fromYearIdx > 0) {
                startYear = fromYear.getItemAt(fromYearIdx);
            }

            if (toYearIdx > 0) {
                endYear = toYear.getItemAt(toYearIdx);
            }

            bt.setAllFilter(selectedString, startYear, endYear);

            Window w = SwingUtilities.getWindowAncestor(this);
            if (w != null) w.dispose();
        });
        footer.add(applyBtn);

        
        JPanel bottomBtns = new JPanel(new GridLayout(1, 2, 10, 0));
        bottomBtns.setOpaque(false);

        
        RoundedButton clearBtn = new RoundedButton("CLEAR ALL", FIELD_RADIUS);
        clearBtn.setFont(poppins12);
        clearBtn.setForeground(MAROON);
        clearBtn.setBorderColor(MAROON);
        clearBtn.setBorderThickness(1);
        clearBtn.addActionListener(e -> {
            classGroup.clearSelection();
            fromYear.setSelectedIndex(0); 
            toYear.setSelectedIndex(0);   
        });
        bottomBtns.add(clearBtn);

        
        RoundedButton cancelBtn = new RoundedButton("CANCEL", FIELD_RADIUS);
        cancelBtn.setFont(poppins12);
        cancelBtn.setForeground(MAROON);
        cancelBtn.setBorderColor(MAROON);
        cancelBtn.setBorderThickness(1);
        cancelBtn.addActionListener(e -> {
            Window w = SwingUtilities.getWindowAncestor(this);
            if (w != null) w.dispose();
        });
        bottomBtns.add(cancelBtn);

        footer.add(bottomBtns);
        footer.setOpaque(false);



        
        modal.add(header, BorderLayout.NORTH);
        modal.add(body, BorderLayout.CENTER);
        modal.add(footer, BorderLayout.SOUTH);

        add(modal, BorderLayout.CENTER);
    }
}
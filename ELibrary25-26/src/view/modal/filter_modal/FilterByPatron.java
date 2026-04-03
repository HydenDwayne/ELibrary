package view.modal.filter_modal;

import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;

import view.RoundedComponents.*;
import view.front_pages.FilePath;
import view.toolbar_tabs.PatronsTab;
import view.fonts.Fonts;

public class FilterByPatron extends JPanel {

	static final Color MAROON = new Color(132, 43, 40);
	static final Color LIGHT_PINK = new Color(250, 236, 238);
	static final Color DARK_TEXT = new Color(109, 35, 33);

	static final int PANEL_RADIUS = 20;
	static final int FIELD_RADIUS = 15;


	public FilterByPatron(PatronsTab pt) {

		setOpaque(false);
		setLayout(new BorderLayout());

		/* ================= FONTS ================= */

		Font introRust26 = new Fonts("IntroRust", 36f).getAppliedFont();
		Font introRust24 = new Fonts("IntroRust", 24f).getAppliedFont();
		Font poppins16 = new Fonts("Poppins", 16f).getAppliedFont();
		Font poppins12 = new Fonts("Poppins", 12f).getAppliedFont();
		Font poppins10 = new Fonts("Poppins", 10f).getAppliedFont();

		/* ================= MODAL ================= */

		RoundedPanel modal = new RoundedPanel(PANEL_RADIUS);
		modal.setLayout(new BorderLayout());
		modal.setPreferredSize(new Dimension(520, 480));
		modal.setBackground(LIGHT_PINK);

		/* ================= HEADER ================= */

		JPanel header = new JPanel();
		header.setBackground(MAROON);
		header.setPreferredSize(new Dimension(520, 100));
		header.setBorder(new EmptyBorder(10, 0, 10, 10));

		JPanel headerWrapper = new JPanel(new BorderLayout());
		headerWrapper.setOpaque(false);
		headerWrapper.setPreferredSize(new Dimension(470, 80));

		ImageIcon icon = new ImageIcon(FilePath.image("elib_logo.png"));
		Image scaled = icon.getImage().getScaledInstance(110, 50, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(scaled));
		logo.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel headerLabel = new JLabel("PATRON");
		headerLabel.setFont(introRust26);
		headerLabel.setForeground(Color.WHITE);
		headerLabel.setBorder(new EmptyBorder(0, 70, 0, 0));

		headerWrapper.add(logo, BorderLayout.WEST);
		headerWrapper.add(headerLabel, BorderLayout.CENTER);
		header.add(headerWrapper);

		/* ================= BODY ================= */

		JPanel body = new JPanel(new BorderLayout());
		body.setOpaque(false);
		body.setBorder(new EmptyBorder(20, 20, 20, 20));

		JLabel title = new JLabel("SEARCH FILTERS", SwingConstants.CENTER);
		title.setFont(introRust24);
		title.setForeground(DARK_TEXT);
		body.add(title, BorderLayout.NORTH);

		JPanel innerBody = new JPanel(new GridBagLayout());
		innerBody.setOpaque(false);
		innerBody.setBorder(new EmptyBorder(40, 40, 40, 40));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(6, 6, 6, 6);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.gridy = -1;

		/* ================= CAMPUS ================= */

		gbc.gridy++;
		gbc.gridx = 0;
		gbc.gridwidth = 2;

		RoundedPanel campusSection = new RoundedPanel(12);
		campusSection.setLayout(new BorderLayout(0, 6));
		campusSection.setBackground(new Color (0,0,0,0));
		campusSection.setOpaque(false);

		JLabel campusTitle = new JLabel("CAMPUS");
		campusTitle.setFont(poppins16);
		campusTitle.setForeground(DARK_TEXT);
		campusSection.add(campusTitle, BorderLayout.NORTH);

		JPanel campusGrid = new JPanel(new GridLayout(2, 3, 8, 6));
		campusGrid.setOpaque(false);

		JCheckBox main = styledCheck("Main", poppins10);
		JCheckBox hagonoy = styledCheck("Hagonoy", poppins10);
		JCheckBox sanRafael = styledCheck("San Rafael", poppins10);
		JCheckBox bustos = styledCheck("Bustos", poppins10);
		JCheckBox meneses = styledCheck("Meneses", poppins10);
		JCheckBox sarmiento = styledCheck("Sarmiento", poppins10);

		campusGrid.add(main);
		campusGrid.add(hagonoy);
		campusGrid.add(sanRafael);
		campusGrid.add(bustos);
		campusGrid.add(meneses);
		campusGrid.add(sarmiento);

		campusSection.add(campusGrid, BorderLayout.CENTER);
		innerBody.add(campusSection, gbc);

		/* ================= PATRON TYPE ================= */

		gbc.gridy++;

		JPanel patronSection = new JPanel();
		patronSection.setLayout(new BorderLayout(0, 6));
		patronSection.setOpaque(false);

		JLabel patronTitle = new JLabel("PATRON TYPE");
		patronTitle.setFont(poppins16);
		patronTitle.setForeground(DARK_TEXT);
		patronSection.add(patronTitle, BorderLayout.NORTH);

		JPanel patronGrid = new JPanel(new GridLayout(1, 2, 14, 0));
		patronGrid.setOpaque(false);

		JCheckBox employee = styledCheck("Employee", poppins10);
		JCheckBox student = styledCheck("Student", poppins10);

		patronGrid.add(employee);
		patronGrid.add(student);

		patronSection.add(patronGrid, BorderLayout.CENTER);
		innerBody.add(patronSection, gbc);

		body.add(innerBody, BorderLayout.CENTER);

		// ===== RESTORE CAMPUS FILTER STATE =====
		main.setSelected(pt.isFilterMain());
		hagonoy.setSelected(pt.isFilterHagonoy());
		sanRafael.setSelected(pt.isFilterSanRafael());
		bustos.setSelected(pt.isFilterBustos());
		meneses.setSelected(pt.isFilterMeneses());
		sarmiento.setSelected(pt.isFilterSarmiento());

		// ===== RESTORE PATRON TYPE FILTER STATE =====
		employee.setSelected(pt.isFilterEmployee());
		student.setSelected(pt.isFilterStudent());

		/* ================= FOOTER ================= */

		JPanel footer = new JPanel(new GridLayout(2, 1, 0, 10));
		footer.setPreferredSize(new Dimension(500, 100));
		footer.setBorder(new EmptyBorder(0, 35, 10, 35));
		footer.setOpaque(false);

		RoundedButton applyBtn = new RoundedButton("APPLY FILTERS", FIELD_RADIUS);
		applyBtn.setFont(poppins12);
		applyBtn.setBackground(MAROON);
		applyBtn.setForeground(Color.WHITE);
		footer.add(applyBtn);
		applyBtn.addActionListener(e -> {

		    pt.setPatronFilters(
		        main.isSelected(),
		        hagonoy.isSelected(),
		        sanRafael.isSelected(),
		        bustos.isSelected(),
		        meneses.isSelected(),
		        sarmiento.isSelected(),
		        employee.isSelected(),
		        student.isSelected()
		    );

		    Window w = SwingUtilities.getWindowAncestor(this);
		    if (w != null) w.dispose();
		});

		JPanel bottomBtns = new JPanel(new GridLayout(1, 2, 10, 0));
		bottomBtns.setOpaque(false);

		RoundedButton clearBtn = new RoundedButton("CLEAR ALL", FIELD_RADIUS);
		clearBtn.setFont(poppins12);
		clearBtn.setForeground(MAROON);
		clearBtn.setBorderColor(MAROON);
		clearBtn.setBorderThickness(1);
		clearBtn.addActionListener(e -> {

		    main.setSelected(false);
		    hagonoy.setSelected(false);
		    sanRafael.setSelected(false);
		    bustos.setSelected(false);
		    meneses.setSelected(false);
		    sarmiento.setSelected(false);
		    employee.setSelected(false);
		    student.setSelected(false);

		    pt.setPatronFilters(
		        false, false, false, false, false, false,
		        false, false
		    );
		});

		RoundedButton cancelBtn = new RoundedButton("CANCEL", FIELD_RADIUS);
		cancelBtn.setFont(poppins12);
		cancelBtn.setForeground(MAROON);
		cancelBtn.setBorderColor(MAROON);
		cancelBtn.setBorderThickness(1);
		cancelBtn.addActionListener(e -> {
			Window w = SwingUtilities.getWindowAncestor(this);
			if (w != null)
				w.dispose();
		});

		bottomBtns.add(clearBtn);
		bottomBtns.add(cancelBtn);
		footer.add(bottomBtns);

		/* ================= ASSEMBLY ================= */

		modal.add(header, BorderLayout.NORTH);
		modal.add(body, BorderLayout.CENTER);
		modal.add(footer, BorderLayout.SOUTH);

		add(modal, BorderLayout.CENTER);
	}

	private JCheckBox styledCheck(String text, Font font) {
		JCheckBox cb = new JCheckBox(text);
		cb.setFont(font);
		cb.setOpaque(false);
		cb.setForeground(DARK_TEXT);
		return cb;
	}
}
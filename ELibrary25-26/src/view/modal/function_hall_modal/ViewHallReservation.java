package view.modal.function_hall_modal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.FunctionHallController;

import java.awt.*;

import view.RoundedComponents.*;
import view.front_pages.FilePath;
import view.fonts.Fonts;

public class ViewHallReservation extends JPanel {

	static final Color MAROON = new Color(132, 43, 40);
	static final Color LIGHT_PINK = new Color(250, 236, 238);
	static final Color WHITE = Color.WHITE;
	static final Color DARK_TEXT = new Color(109, 35, 33);
	static final Color FIELD_BORDER = new Color(146, 76, 74);

	static final int PANEL_RADIUS = 20;
	static final int FIELD_RADIUS = 15;
	public RoundedTextField dateField;
	public RoundedTextField resField;
	public RoundedTextField eventField;
	public RoundedTextField reservedField;
	public RoundedTextField approvedField;
	public RoundedTextField startField;
	public RoundedTextField endField;

	JLabel dateLbl;
	JLabel resLbl;
	JLabel eventLbl;
	JLabel reservedLbl;
	JLabel approvedLbl;
	JLabel startLbl;
	JLabel endLbl;
	
	String hallCode ="";

	public ViewHallReservation(String[] event) {
		this.hallCode = hallCode;
		setOpaque(false);
		setLayout(new BorderLayout());

		

		Font introRust36 = new Fonts("IntroRust", 36f).getAppliedFont();
		Font introRust24 = new Fonts("IntroRust", 24f).getAppliedFont();
		Font poppins16 = new Fonts("Poppins", 16f).getAppliedFont();
		Font poppins12 = new Fonts("Poppins", 12f).getAppliedFont();
		Font poppins10 = new Fonts("Poppins", 10f).getAppliedFont();

		

		RoundedPanel modal = new RoundedPanel(PANEL_RADIUS);
		modal.setLayout(new BorderLayout());
		modal.setPreferredSize(new Dimension(520, 560));
		modal.setBackground(LIGHT_PINK);

		

		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(MAROON);
		header.setPreferredSize(new Dimension(520, 100));
		header.setBorder(new EmptyBorder(10, 0, 10, 10));

		ImageIcon icon = new ImageIcon(FilePath.image("elib_logo.png"));
		Image scaled = icon.getImage().getScaledInstance(110, 50, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(scaled));

		JLabel headerLabel = new JLabel("FACILITIES");
		headerLabel.setFont(introRust36);
		headerLabel.setForeground(WHITE);
		headerLabel.setBorder(new EmptyBorder(0, 70, 0, 0));

		header.add(logo, BorderLayout.WEST);
		header.add(headerLabel, BorderLayout.CENTER);

		

		JPanel body = new JPanel(new BorderLayout());
		body.setOpaque(false);
		body.setBorder(new EmptyBorder(10, 10, 10, 10));

		JLabel bodyTitle = new JLabel("VIEW HALL RESERVATION DETAILS", SwingConstants.CENTER);
		bodyTitle.setFont(introRust24);
		bodyTitle.setForeground(DARK_TEXT);

		body.add(bodyTitle, BorderLayout.NORTH);

		JPanel innerBody = new JPanel(new GridBagLayout());
		innerBody.setOpaque(false);
		innerBody.setBorder(new EmptyBorder(15, 20, 15, 20));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(6, 6, 6, 6);
		gbc.weightx = 0.5;
		gbc.gridy = 0;

		Dimension labelSize = new Dimension(220, 30);

		

		gbc.gridx = 0;
		dateLbl = new JLabel("Chosen Date:");
		dateLbl.setFont(poppins16);
		dateLbl.setForeground(DARK_TEXT);
		dateLbl.setPreferredSize(labelSize);
		innerBody.add(dateLbl, gbc);

		gbc.gridx = 1;
		dateField = new RoundedTextField(19, FIELD_RADIUS);
		dateField.setFont(poppins10);
		dateField.setText("2026-03-13");
		dateField.setEditable(false);
		dateField.setBorderColor(FIELD_BORDER);
		dateField.setBorderThickness(1);
		innerBody.add(dateField, gbc);

		

		gbc.gridy++;
		gbc.gridx = 0;
		resLbl = new JLabel("Reservation No.:");
		resLbl.setFont(poppins16);
		resLbl.setForeground(DARK_TEXT);
		resLbl.setPreferredSize(labelSize);
		innerBody.add(resLbl, gbc);

		gbc.gridx = 1;
		resField = new RoundedTextField(19, FIELD_RADIUS);
		resField.setFont(poppins10);
		resField.setText("2026-0002");
		resField.setEditable(false);
		resField.setBorderColor(FIELD_BORDER);
		resField.setBorderThickness(1);
		innerBody.add(resField, gbc);

		

		gbc.gridy++;
		gbc.gridx = 0;
		eventLbl = new JLabel("Event(s):");
		eventLbl.setFont(poppins16);
		eventLbl.setForeground(DARK_TEXT);
		eventLbl.setPreferredSize(labelSize);
		innerBody.add(eventLbl, gbc);

		gbc.gridx = 1;
		eventField = new RoundedTextField(19, FIELD_RADIUS);
		eventField.setFont(poppins10);
		eventField.setText("Group Study Meetup");
		eventField.setEditable(false);
		eventField.setBorderColor(FIELD_BORDER);
		eventField.setBorderThickness(1);
		innerBody.add(eventField, gbc);

		

		gbc.gridy++;
		gbc.gridx = 0;
		reservedLbl = new JLabel("Reserved By:");
		reservedLbl.setFont(poppins16);
		reservedLbl.setForeground(DARK_TEXT);
		reservedLbl.setPreferredSize(labelSize);
		innerBody.add(reservedLbl, gbc);

		gbc.gridx = 1;
		reservedField = new RoundedTextField(19, FIELD_RADIUS);
		reservedField.setFont(poppins10);
		reservedField.setText("2024100016");
		reservedField.setEditable(false);
		reservedField.setBorderColor(FIELD_BORDER);
		reservedField.setBorderThickness(1);
		innerBody.add(reservedField, gbc);

		

		gbc.gridy++;
		gbc.gridx = 0;
		approvedLbl = new JLabel("Approved By:");
		approvedLbl.setFont(poppins16);
		approvedLbl.setForeground(DARK_TEXT);
		approvedLbl.setPreferredSize(labelSize);
		innerBody.add(approvedLbl, gbc);

		gbc.gridx = 1;
		approvedField = new RoundedTextField(19, FIELD_RADIUS);
		approvedField.setFont(poppins10);
		approvedField.setText("2024100004");
		approvedField.setEditable(false);
		approvedField.setBorderColor(FIELD_BORDER);
		approvedField.setBorderThickness(1);
		innerBody.add(approvedField, gbc);

		

		gbc.gridy++;
		gbc.gridx = 0;
		startLbl = new JLabel("Start Time:");
		startLbl.setFont(poppins16);
		startLbl.setForeground(DARK_TEXT);
		startLbl.setPreferredSize(labelSize);
		innerBody.add(startLbl, gbc);

		gbc.gridx = 1;
		startField = new RoundedTextField(19, FIELD_RADIUS);
		startField.setFont(poppins10);
		startField.setText("13:00");
		startField.setEditable(false);
		startField.setBorderColor(FIELD_BORDER);
		startField.setBorderThickness(1);
		innerBody.add(startField, gbc);

		

		gbc.gridy++;
		gbc.gridx = 0;
		endLbl = new JLabel("End Time:");
		endLbl.setFont(poppins16);
		endLbl.setForeground(DARK_TEXT);
		endLbl.setPreferredSize(labelSize);
		innerBody.add(endLbl, gbc);

		gbc.gridx = 1;
		endField = new RoundedTextField(19, FIELD_RADIUS);
		endField.setFont(poppins10);
		endField.setText("15:00");
		endField.setEditable(false);
		endField.setBorderColor(FIELD_BORDER);
		endField.setBorderThickness(1);
		innerBody.add(endField, gbc);

		body.add(innerBody, BorderLayout.CENTER);

		

        JPanel footer = new JPanel(new GridLayout(1, 2, 10, 0));
        footer.setBorder(new EmptyBorder(10, 35, 10, 35));
        footer.setOpaque(false);

        RoundedButton backBtn = new RoundedButton("< BACK", FIELD_RADIUS);
        backBtn.setFont(poppins12);
        backBtn.setForeground(MAROON);
        backBtn.setBorderColor(MAROON);
        backBtn.setBorderThickness(1);
        backBtn.addActionListener(e -> {
        	Window w = SwingUtilities.getWindowAncestor(this);
			if (w instanceof JDialog) {
				w.dispose();
			}
        });

        RoundedButton cancelBtn = new RoundedButton("CANCEL RESERVATION", FIELD_RADIUS);
        cancelBtn.setFont(poppins12);
        cancelBtn.setBackground(MAROON);
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.addActionListener(e-> {
        	FunctionHallController comp = new FunctionHallController(resField.getRealText());
        	Window w = SwingUtilities.getWindowAncestor(this);
			if (w instanceof JDialog) {
				w.dispose();
			}
        });

        footer.add(backBtn);
        footer.add(cancelBtn);

		

		modal.add(header, BorderLayout.NORTH);
		modal.add(body, BorderLayout.CENTER);
		modal.add(footer, BorderLayout.SOUTH);

		dateField.setText(event[4]);
		resField.setText(event[0]);
		eventField.setText(event[3]);
		reservedField.setText(event[2]);
		approvedField.setText(event[1]);
		startField.setText(event[5]);
		endField.setText(event[6]);

		add(modal, BorderLayout.CENTER);
	}
}
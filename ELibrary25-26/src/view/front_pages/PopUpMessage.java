package view.front_pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import view.RoundedComponents.RoundedButton;
import view.RoundedComponents.RoundedDialog;
import view.RoundedComponents.RoundedPanel;
import view.modal.*;
import view.modal.archive_modal.ArchivedBooks;
import view.modal.books_modal.ViewBooks;

public class PopUpMessage extends JPanel {

	static final Color MAROON = new Color(132, 43, 40); 
	static final Color LIGHT_PINK = new Color(250, 236, 238); 
	static final Color WHITE = Color.WHITE;
	static final Color DARK_TEXT = new Color(109, 35, 33); 
	static final int PANEL_RADIUS = 20;
	static final int FIELD_RADIUS = 15;

	private JTextArea message;
	Window parent;

	public PopUpMessage(String msg, String title) {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(400, 250));
		setBackground(LIGHT_PINK);

		
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(MAROON);
		header.setPreferredSize(new Dimension(400, 80));
		header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

		
		JPanel logoPanel = new JPanel();
		logoPanel.setBackground(MAROON);
		logoPanel.setPreferredSize(new Dimension(80, 80));
		JLabel logoPlaceholder = new JLabel("📚", SwingConstants.CENTER);
		logoPlaceholder.setFont(new Font("Arial", Font.PLAIN, 40));
		logoPlaceholder.setForeground(WHITE);
		logoPanel.add(logoPlaceholder);
		header.add(logoPanel, BorderLayout.WEST);

		
		JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
		titleLabel.setForeground(WHITE);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
		header.add(titleLabel, BorderLayout.CENTER);

		
		JPanel body = new JPanel(new GridBagLayout());
		body.setOpaque(false);
		body.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

		message = new JTextArea(msg);
		message.setForeground(DARK_TEXT);
		message.setFont(new Font("Arial", Font.BOLD, 14));
		message.setOpaque(false);
		message.setEditable(false);
		message.setFocusable(false);
		message.setWrapStyleWord(true);
		message.setLineWrap(true);
		message.setBorder(BorderFactory.createEmptyBorder());
		message.setBackground(new Color(0, 0, 0, 0));

		body.add(message);

		
		JPanel footer = new JPanel();
		footer.setOpaque(false);
		footer.setPreferredSize(new Dimension(400, 50));
		footer.setLayout(new GridLayout(1, 1, 10, 0));

		RoundedButton closeBtn = new RoundedButton("CLOSE", FIELD_RADIUS);
		closeBtn.setForeground(MAROON);
		closeBtn.setBorderColor(MAROON);
		closeBtn.setBorderThickness(1);
		closeBtn.setBackground(LIGHT_PINK);
		closeBtn.setFont(new Font("Arial", Font.BOLD, 14));
		closeBtn.setFocusPainted(false);
		footer.add(closeBtn);

		closeBtn.addActionListener(e -> {
			Window w = SwingUtilities.getWindowAncestor(this);
			if (w != null)
				w.dispose();
		});

		
		add(header, BorderLayout.NORTH);
		add(body, BorderLayout.CENTER);
		add(footer, BorderLayout.SOUTH);

	}

	public void setMessage(String text) {
		message.setText(text);
	}
}

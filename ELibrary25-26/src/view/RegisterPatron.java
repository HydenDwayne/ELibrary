package view;
import javax.swing.*;
import java.awt.*;

public class RegisterPatron extends JFrame{
	
	static String imgFilePath = FilePath.getImgFilePath();
	
	public RegisterPatron(FacilityLogin fl) {
		int panelRadius = 20;
		JPanel panel = new JPanel() {
			Image backgroundImage = new ImageIcon(imgFilePath + "blurred_bg.jpg").getImage();

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
			}
		};
		panel.setLayout(new GridBagLayout());
		
		
		
		
		add(panel);
		
		setTitle("E-Library Management System");
		setContentPane(panel);
		pack();

		if (getWidth() < 1120 || getHeight() < 600) {
			setSize(1120, 600);
		}

		setMinimumSize(new Dimension(1120, 600));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new RegisterPatron(null);
	}
}

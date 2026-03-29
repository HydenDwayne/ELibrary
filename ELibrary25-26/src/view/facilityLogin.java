package view;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.Border;
import view.RoundedComponents.*;

public class facilityLogin extends JFrame {

    public facilityLogin() {
        JPanel panel = new JPanel() {
            Image backgroundImage = new ImageIcon("C:\\Users\\admin\\eclipse-workspace\\ELibrary25-26\\src\\view\\img\\blurred_bg.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new GridBagLayout());

        RoundedPanel loginContainer = new RoundedPanel(30);
        loginContainer.setPreferredSize(new Dimension(350, 400));
        loginContainer.setBackground(new Color(121, 25, 24, 160));
        Border padding = BorderFactory.createEmptyBorder(20, 10, 10, 10);
        loginContainer.setBorder(padding);

        loginContainer.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0); // spacing between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // elib logo
        ImageIcon icon = new ImageIcon("C:\\Users\\admin\\eclipse-workspace\\ELibrary25-26\\src\\view\\img\\elib_logo.png");
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(160, 80, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);
        JLabel elibLogo = new JLabel(icon);
        elibLogo.setHorizontalAlignment(SwingConstants.CENTER);
        loginContainer.add(elibLogo, gbc);

        // welcome message
        gbc.gridy++;
        JLabel welcomeMsg = new JLabel("<html><div style='text-align:center;'>Enter ID Number to Login</div></html>");
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Applications\\VSC\\Workspace\\ELibrary\\UI\\fonts\\ABeeZee-Regular.ttf"))
                    .deriveFont(Font.PLAIN, 18f);
            welcomeMsg.setFont(customFont);
        } catch (FontFormatException | IOException e) {
            System.out.println("Error loading ABeeZee font. Font set to fallback");
        }
        welcomeMsg.setForeground(Color.WHITE);
        welcomeMsg.setHorizontalAlignment(SwingConstants.CENTER);
        loginContainer.add(welcomeMsg, gbc);

        // username field
        gbc.gridy++;
        RoundedTextField username = new RoundedTextField(25, 20);
        username.setPlaceholder("Enter ID");
        loginContainer.add(username, gbc);

        panel.add(loginContainer);
        add(panel);

        gbc.gridy++;
        RoundedButton submitBtn = new RoundedButton("Submit", 20);
        submitBtn.setBackground(Color.decode("#5d1513"));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFocusPainted(false);
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // unfinished ------------------------------------ insert logic for inserting login/logout here
                // need to display user info or display user not found error
                if (username.getText().equalsIgnoreCase("exit")) {
                    loginWindow lw = new loginWindow();
                    lw.setVisible(true);
                    SwingUtilities.getWindowAncestor(submitBtn).setVisible(false);
                } else {
                    username.setText("");
                }
            }
        });
        loginContainer.add(submitBtn, gbc);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });

        addWindowFocusListener(new WindowAdapter() {
            public void windowGainedFocus(WindowEvent e) {
                // Request focus on something else, or a panel
                panel.requestFocusInWindow();
            }
        });

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
        new facilityLogin();
    }
}

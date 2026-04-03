package view.front_pages.patron_registration;

import java.awt.*;
import java.net.URL;

import javax.swing.*;

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(URL url) {
        backgroundImage = new ImageIcon(url).getImage();
        setLayout(new BorderLayout()); // important
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
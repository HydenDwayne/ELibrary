package view.modal;

import javax.swing.*;
import java.awt.*;

public class DimGlassPane extends JComponent {

    private final Color color;

    public DimGlassPane(Color color) {
        this.color = color;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(color);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
    }
}
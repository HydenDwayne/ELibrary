package view.RoundedComponents;
import java.awt.*;
import javax.swing.*;

public class RoundedButton extends JButton {

    private int cornerRadius = 15;

    // Text constructor
    public RoundedButton(String text, int radius) {
        super(text);
        this.cornerRadius = radius;
        init();
    }

    // Icon constructor
    public RoundedButton(Icon icon, int radius) {
        super(icon);
        this.cornerRadius = radius;
        init();
    }

    // Text + Icon constructor
    public RoundedButton(String text, Icon icon, int radius) {
        super(text, icon);
        this.cornerRadius = radius;
        init();
    }

    private void init() {
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);

        // Optional: center icon & text
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.BOTTOM); 
        // Change depending on layout preference
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Fill rounded background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(),
                cornerRadius, cornerRadius);

        g2.dispose();

        // Let JButton handle icon + text painting
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {

    }
}

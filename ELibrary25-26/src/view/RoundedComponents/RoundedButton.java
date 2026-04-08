package view.RoundedComponents;
import java.awt.*;
import javax.swing.*;

public class RoundedButton extends JButton {

    private int cornerRadius = 15;
    private Color borderColor = null;
    private int borderThickness = 1;

    public RoundedButton(String text, int radius) {
        super(text);
        this.cornerRadius = radius;
        init();
    }

    public RoundedButton(Icon icon, int radius) {
        super(icon);
        this.cornerRadius = radius;
        init();
    }

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

        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.BOTTOM);
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }

    public void setBorderThickness(int thickness) {
        this.borderThickness = thickness;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(),
                cornerRadius, cornerRadius);

        g2.dispose();

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        if (borderColor == null) return;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(borderThickness));

        int inset = (int) Math.ceil(borderThickness / 2.0);
        g2.drawRoundRect(
            inset,
            inset,
            getWidth() - 2 * inset,
            getHeight() - 2 * inset,
            cornerRadius,
            cornerRadius
        );
        g2.dispose();
    }
}
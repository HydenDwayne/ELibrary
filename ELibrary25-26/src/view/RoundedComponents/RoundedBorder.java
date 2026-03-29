package view.RoundedComponents;
import java.awt.*;
import javax.swing.border.Border;

public class RoundedBorder implements Border {

    private int cornerRadius = 15;

    public RoundedBorder(int radius) {
        this.cornerRadius = radius;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(cornerRadius + 1, cornerRadius + 1, cornerRadius + 2, cornerRadius + 2);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(c.getForeground());
        g2.drawRoundRect(x, y, width - 1, height - 1, cornerRadius, cornerRadius);

        g2.dispose();
    }
}

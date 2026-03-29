package view.RoundedComponents;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class RoundedScrollbar extends BasicScrollBarUI {

    @Override
    protected void configureScrollBarColors() {
        thumbColor = new Color(150, 150, 150, 180);
        trackColor = new Color(240, 240, 240);
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createZeroButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createZeroButton();
    }

    private JButton createZeroButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(0, 0));
        button.setMinimumSize(new Dimension(0, 0));
        button.setMaximumSize(new Dimension(0, 0));
        return button;
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle bounds) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(thumbColor);

        int arc = Math.min(bounds.width, bounds.height);
        g2.fillRoundRect(bounds.x, bounds.y,
                         bounds.width, bounds.height,
                         arc, arc);

        g2.dispose();
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle bounds) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(trackColor);

        int arc = Math.min(bounds.width, bounds.height);
        g2.fillRoundRect(bounds.x, bounds.y,
                         bounds.width, bounds.height,
                         arc, arc);

        g2.dispose();
    }
}

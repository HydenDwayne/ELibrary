package view.RoundedComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

import view.fonts.Fonts;

public class RoundedSpinner extends JSpinner {

    private int cornerRadius = 15;
    private Color borderColor = null;
    private int borderThickness = 1;

    private Dimension fieldSize;

    public RoundedSpinner(List<String> values, int radius) {
        super(new SpinnerListModel(values));
        this.cornerRadius = radius;

        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder());

        // ✅ Match RoundedTextField height exactly
        RoundedTextField ref = new RoundedTextField(19, radius);
        fieldSize = ref.getPreferredSize();

        setPreferredSize(fieldSize);
        setMinimumSize(fieldSize);
        setMaximumSize(new Dimension(Integer.MAX_VALUE, fieldSize.height));

        // ✅ Style editor like RoundedTextField
        if (getEditor() instanceof DefaultEditor editor) {
            JTextField tf = editor.getTextField();
            tf.setOpaque(false);
            tf.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            tf.setForeground(Color.BLACK);
            tf.setFont(new Fonts("Poppins", 10f).getAppliedFont());
            tf.setPreferredSize(fieldSize);
            tf.setMinimumSize(fieldSize);
        }

        // ✅ Keep arrow buttons inside bounds
        styleSpinnerButtons();
    }

    /* ================= BUTTON STYLING ================= */

    private void styleSpinnerButtons() {
        for (Component c : getComponents()) {
            if (c instanceof JButton btn) {
                btn.setOpaque(false);
                btn.setContentAreaFilled(false);
                btn.setBorder(BorderFactory.createEmptyBorder());
                btn.setFocusPainted(false);

                Dimension btnSize = new Dimension(28, fieldSize.height);
                btn.setPreferredSize(btnSize);
                btn.setMinimumSize(btnSize);
                btn.setMaximumSize(btnSize);
            }
        }
    }

    /* ================= SHAPE ================= */

    private Shape getRoundedShape() {
        return new RoundRectangle2D.Float(
                0, 0,
                getWidth(), getHeight(),
                cornerRadius, cornerRadius
        );
    }

    /* ================= PAINT ================= */

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        // ✅ Clip background
        g2.clip(getRoundedShape());
        g2.setColor(getBackground());
        g2.fillRoundRect(
                0, 0,
                getWidth(), getHeight(),
                cornerRadius, cornerRadius
        );

        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintChildren(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        // ✅ Clip CHILDREN (arrow buttons)
        g2.clip(getRoundedShape());
        super.paintChildren(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        if (borderColor == null) return;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

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

    /* ================= PUBLIC API ================= */

    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }

    public void setBorderThickness(int thickness) {
        this.borderThickness = thickness;
        repaint();
    }

    /* ================= INSETS ================= */

    @Override
    public Insets getInsets() {
        int pad = (int) Math.ceil(borderThickness / 2.0);
        return new Insets(
            2 + pad,
            10 + pad,
            2 + pad,
            10 + pad
        );
    }
}
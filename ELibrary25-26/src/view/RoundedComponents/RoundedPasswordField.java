package view.RoundedComponents;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RoundedPasswordField extends JPasswordField {
    private int cornerRadius = 15;
    private String placeholder = "";
    private Color placeholderColor = Color.GRAY;
    private Color normalColor = Color.BLACK;
    private Color borderColor = null;
    private int borderThickness = 1;

    private char defaultEchoChar;

    public RoundedPasswordField(int columns, int radius) {
        super(columns);
        this.cornerRadius = radius;

        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        setForeground(normalColor);

        defaultEchoChar = getEchoChar();

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (isShowingPlaceholder()) {
                    setText("");
                    setForeground(normalColor);
                    setEchoChar(defaultEchoChar); // restore masking
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getPassword().length == 0) {
                    showPlaceholder();
                }
            }
        });
    }

    private boolean isShowingPlaceholder() {
        return new String(getPassword()).equals(placeholder);
    }

    private void showPlaceholder() {
        setEchoChar((char) 0); // show text instead of masking
        setText(placeholder);
        setForeground(placeholderColor);
    }

    public void setPlaceholder(String text) {
        this.placeholder = text;
        if (getPassword().length == 0 || isShowingPlaceholder()) {
            showPlaceholder();
        }
    }

    public char[] getRealPassword() {
        return isShowingPlaceholder() ? new char[0] : getPassword();
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
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

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

    @Override
    public Insets getInsets() {
        int pad = (int) Math.ceil(borderThickness / 2.0);
        return new Insets(
            5 + pad,
            10 + pad,
            5 + pad,
            10 + pad
        );
    }
}
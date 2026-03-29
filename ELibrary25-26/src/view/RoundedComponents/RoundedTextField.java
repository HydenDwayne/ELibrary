package view.RoundedComponents;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RoundedTextField extends JTextField {

    private int cornerRadius = 15;
    private String placeholder = "";
    private Color placeholderColor = Color.GRAY;
    private Color normalColor = Color.BLACK;

    public RoundedTextField(int columns, int radius) {
        super(columns);
        this.cornerRadius = radius;
        setOpaque(false); // for rounded corners
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // padding
        setForeground(normalColor);

        // Add focus listener for placeholder behavior
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(placeholder)) {
                    setText("");
                    setForeground(normalColor);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(placeholder);
                    setForeground(placeholderColor);
                    
                }
            }
        });
    }

    // Setter for placeholder
    public void setPlaceholder(String text) {
        this.placeholder = text;
        if (getText().isEmpty() || getText().equals(placeholder)) {
            setText(placeholder);
            setForeground(placeholderColor);
        }
    }

    // Returns the actual user input (ignores placeholder)
    public String getRealText() {
        return getText().equals(placeholder) ? "" : getText();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // fill background with rounded rectangle
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {

    }
}

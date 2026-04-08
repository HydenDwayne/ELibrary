package archives;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;


public class RoundedComponent {
    
}


class RoundedPanel extends JPanel {

    private int cornerRadius = 20;

    public RoundedPanel(int radius) {
        super();
        this.cornerRadius = radius;
        setOpaque(false); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        g2.dispose();
        super.paintComponent(g);
    }
}


class RoundedButton extends JButton {

    private int cornerRadius = 15;

    
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

    }
}


class RoundedTextArea extends JTextArea {

    private int cornerRadius = 10;

    public RoundedTextArea(int rows, int cols, int radius) {
        super(rows, cols);
        this.cornerRadius = radius;
        setOpaque(false); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

        g2.dispose();
    }
}


class RoundedTextField extends JTextField {

    private int cornerRadius = 15;
    private String placeholder = "";
    private Color placeholderColor = Color.GRAY;
    private Color normalColor = Color.BLACK;

    public RoundedTextField(int columns, int radius) {
        super(columns);
        this.cornerRadius = radius;
        setOpaque(false); 
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); 
        setForeground(normalColor);

        
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

    
    public void setPlaceholder(String text) {
        this.placeholder = text;
        if (getText().isEmpty() || getText().equals(placeholder)) {
            setText(placeholder);
            setForeground(placeholderColor);
        }
    }

    
    public String getRealText() {
        return getText().equals(placeholder) ? "" : getText();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {

    }
}


class RoundedBorder implements Border {

    private int cornerRadius;

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

package view.RoundedComponents;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.*;

public class RoundedComboBox<E> extends JComboBox<E> {

    private int cornerRadius = 15;
    private String placeholder = "";
    private Color borderColor = null;
    private int borderThickness = 1;

    public RoundedComboBox(E[] items, int radius) {
        super(items);
        this.cornerRadius = radius;

        setOpaque(false);
        setFocusable(false);
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        setBackground(Color.WHITE);

        setUI(new RoundedComboBoxUI());
        setRenderer(new PlaceholderRenderer());
    }

    public void setPlaceholder(String text) {
        this.placeholder = text;
        repaint();
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }

    public void setBorderThickness(int thickness) {
        this.borderThickness = thickness;
        repaint();
    }

    public void clearBorder() {
        this.borderColor = null;
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


    private class RoundedComboBoxUI extends BasicComboBoxUI {

        @Override
        protected JButton createArrowButton() {
            JButton button = new BasicArrowButton(
                    BasicArrowButton.SOUTH,
                    getBackground(),
                    getBackground(),
                    Color.GRAY,
                    getBackground());
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setContentAreaFilled(false);
            return button;
        }

        @Override
        public void paintCurrentValueBackground(Graphics g,
                                                Rectangle bounds,
                                                boolean hasFocus) {

        }

        @Override
        protected ComboPopup createPopup() {
            BasicComboPopup popup = new BasicComboPopup(comboBox) {

            	@Override
            	protected JScrollPane createScroller() {
            	    JScrollPane scrollPane = new JScrollPane(list);
            	    scrollPane.setBorder(BorderFactory.createEmptyBorder());
            	    scrollPane.setOpaque(false);
            	    scrollPane.getViewport().setOpaque(false);

            	    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            	    JScrollBar vertical = scrollPane.getVerticalScrollBar();
            	    vertical.setUI(new RoundedScrollBarUI());
            	    vertical.setPreferredSize(new Dimension(10, Integer.MAX_VALUE));

            	    return scrollPane;
            	}

            	@Override
            	public void show() {

            	    list.setBorder(new EmptyBorder(5, 5, 5, 5));
            	    list.setSelectionBackground(new Color(200, 220, 255));
            	    list.setBackground(Color.WHITE);

            	    list.setFixedCellHeight(-1);

            	    list.setVisibleRowCount(5);

            	    int width = comboBox.getWidth() + 10;

            	    int rowHeight = list.getCellBounds(0, 0).height;
            	    int height = rowHeight * list.getVisibleRowCount();

            	    if (rowHeight <= 0) {
            	        rowHeight = 40;
            	        height = rowHeight * list.getVisibleRowCount();
            	    }

            	    scroller.setPreferredSize(new Dimension(width, height));

            	    super.show();
            	}

			};
            	return popup; 
            	} 
        }
        

    

    private static class RoundedScrollBarUI extends BasicScrollBarUI {

        private final int THUMB_RADIUS = 10;

        @Override
        protected void configureScrollBarColors() {
            thumbColor = new Color(150, 150, 150, 180);
            trackColor = new Color(240, 240, 240);
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(thumbColor);
            g2.fillRoundRect(thumbBounds.x, thumbBounds.y,
                    thumbBounds.width, thumbBounds.height,
                    THUMB_RADIUS, THUMB_RADIUS);
            g2.dispose();
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(trackColor);
            g2.fillRoundRect(trackBounds.x, trackBounds.y,
                    trackBounds.width, trackBounds.height,
                    THUMB_RADIUS, THUMB_RADIUS);
            g2.dispose();
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
        protected Dimension getMinimumThumbSize() {
            return new Dimension(10, 30);
        }
    }

    private class PlaceholderRenderer implements ListCellRenderer<Object> {

        private final JTextArea area;

        public PlaceholderRenderer() {
            area = new JTextArea();
            area.setLineWrap(true);
            area.setWrapStyleWord(true);
            area.setOpaque(true);
            area.setFont(getFont());

            area.setBorder(new EmptyBorder(2, 10, 2, 10));
        }

        @Override
        public Component getListCellRendererComponent(
                JList<?> list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {

            if (value == null && placeholder != null && !placeholder.isEmpty()) {
                area.setText(placeholder);
                area.setForeground(Color.GRAY);
            } else {
                area.setText(value != null ? value.toString() : "");
                area.setForeground(Color.BLACK);
            }
            
            

            area.setFont(list.getFont());

            area.setSize(list.getWidth(), Short.MAX_VALUE);

            if (isSelected) {
                area.setBackground(new Color(200, 220, 255));
            } else {
                area.setBackground(Color.WHITE);
            }

            return area;
        }
    }
}
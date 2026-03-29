package view.RoundedComponents;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.*;

public class RoundedComboBox<E> extends JComboBox<E> {

    private int cornerRadius = 15;
    private String placeholder = "";

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

    // Optional placeholder setter
    public void setPlaceholder(String text) {
        this.placeholder = text;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw rounded background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(),
                cornerRadius, cornerRadius);

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Remove default border
    }

    // --------------------- Rounded ComboBox UI ---------------------
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
            // Do nothing (removes default highlight)
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

                    // Apply custom rounded scrollbar
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
                    list.setFixedCellHeight(30);
                    super.show();
                }
            };
            return popup;
        }
    }

    // --------------------- Rounded ScrollBar UI ---------------------
    private static class RoundedScrollBarUI extends BasicScrollBarUI {

        private final int THUMB_RADIUS = 10;

        @Override
        protected void configureScrollBarColors() {
            thumbColor = new Color(150, 150, 150, 180); // semi-transparent gray
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

    // --------------------- Placeholder Renderer ---------------------
    private class PlaceholderRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list,
                                                      Object value,
                                                      int index,
                                                      boolean isSelected,
                                                      boolean cellHasFocus) {

            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value == null && placeholder != null && !placeholder.isEmpty()) {
                setText(placeholder);
                setForeground(Color.GRAY);
            } else {
                setForeground(Color.BLACK);
                setText(value != null ? value.toString() : "");
            }

            setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

            if (isSelected) {
                setBackground(new Color(200, 220, 255));
            } else {
                setBackground(Color.WHITE);
            }

            return this;
        }
    }
}
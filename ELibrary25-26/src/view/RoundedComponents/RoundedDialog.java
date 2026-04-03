package view.RoundedComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedDialog extends JDialog {

    private int cornerRadius;

    public RoundedDialog(Window parent, int cornerRadius) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.cornerRadius = cornerRadius;

        // ✅ Remove title bar & borders
        setUndecorated(true);

        // ✅ Allow transparency
        setBackground(new Color(0, 0, 0, 0));

        // ✅ Clean disposal
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            applyRoundedShape();
        }
        super.setVisible(visible);
    }

    @Override
    public void pack() {
        super.pack();
        applyRoundedShape();
    }

    private void applyRoundedShape() {
        setShape(new RoundRectangle2D.Double(
            0, 0,
            getWidth(),
            getHeight(),
            cornerRadius,
            cornerRadius
        ));
    }
}

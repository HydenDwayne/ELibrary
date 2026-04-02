package view.modal;

import java.awt.*;
import javax.swing.*;

public class ModalUtils {

    public static void showDim(Window parent, Color color) {
        if (parent instanceof RootPaneContainer rpc) {
            DimGlassPane glass = new DimGlassPane(color);
            rpc.setGlassPane(glass);
            glass.setVisible(true);
        }
    }

    public static void hideDim(Window parent) {
        if (parent instanceof RootPaneContainer rpc) {
            rpc.getGlassPane().setVisible(false);
        }
    }
}
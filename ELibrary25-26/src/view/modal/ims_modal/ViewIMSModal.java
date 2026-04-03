package view.modal.ims_modal;

import view.RoundedComponents.RoundedDialog;
import view.modal.ModalUtils;

import javax.swing.*;
import java.awt.*;

public class ViewIMSModal extends RoundedDialog {

    private final Window parent;

    public ViewIMSModal(Window parent) {
        super(parent, 20); // same radius as other modals
        this.parent = parent;

        // Dim background (same behavior as other modals)
        ModalUtils.showDim(parent, new Color(0, 0, 0, 150));

        // Set panel content
        setContentPane(new ViewIMS());
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    @Override
    public void dispose() {
        ModalUtils.hideDim(parent);
        super.dispose();
    }
}
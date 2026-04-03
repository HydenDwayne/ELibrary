package view.modal.ims_modal;

import view.RoundedComponents.RoundedDialog;
import view.modal.ModalUtils;

import java.awt.*;

public class AddIMSModal extends RoundedDialog {

    private final Window parent;

    public AddIMSModal(Window parent) {
        super(parent, 20); // same radius as other modals
        this.parent = parent;

        // Dim the background
        ModalUtils.showDim(parent, new Color(0, 0, 0, 150));

        // Set panel content
        setContentPane(new AddIMS());
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
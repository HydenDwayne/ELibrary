package view.modal.ims_modal;

import view.RoundedComponents.RoundedDialog;
import view.modal.ModalUtils;

import java.awt.*;

public class AddRequestItemModal extends RoundedDialog {

    private final Window parent;

    public AddRequestItemModal(Window parent) {
        super(parent, 20); // same radius as all other modals
        this.parent = parent;

        // Dim background
        ModalUtils.showDim(parent, new Color(0, 0, 0, 150));

        // Set content panel
        setContentPane(new AddRequestItem());
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

package view.modal.lost_and_found_modal;

import view.RoundedComponents.RoundedDialog;
import view.modal.ModalUtils;

import java.awt.*;

public class AddLostAndFoundModal extends RoundedDialog {

    private final Window parent;

    public AddLostAndFoundModal(Window parent) {
        super(parent, 20); // same radius as other modals
        this.parent = parent;

        // dim background
        ModalUtils.showDim(parent, new Color(0, 0, 0, 150));

        // set panel content
        setContentPane(new AddLostAndFound());
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

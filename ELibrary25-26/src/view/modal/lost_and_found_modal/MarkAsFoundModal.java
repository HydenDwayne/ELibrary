package view.modal.lost_and_found_modal;

import view.RoundedComponents.RoundedDialog;
import view.modal.ModalUtils;

import java.awt.*;

public class MarkAsFoundModal extends RoundedDialog {

    private final Window parent;

    public MarkAsFoundModal(Window parent, String itemNum) {
        super(parent, 20); 
        this.parent = parent;

        
        ModalUtils.showDim(parent, new Color(0, 0, 0, 150));

        
        setContentPane(new MarkAsFound(itemNum));
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

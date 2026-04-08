package view.modal.ims_modal;

import view.RoundedComponents.RoundedDialog;
import view.modal.ModalUtils;

import java.awt.*;

public class AddRequestItemModal extends RoundedDialog {

    private final Window parent;

    public AddRequestItemModal(Window parent) {
        super(parent, 20); 
        this.parent = parent;

        
        ModalUtils.showDim(parent, new Color(0, 0, 0, 150));

        
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

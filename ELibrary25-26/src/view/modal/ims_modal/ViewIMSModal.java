package view.modal.ims_modal;

import view.RoundedComponents.RoundedDialog;
import view.modal.ModalUtils;

import javax.swing.*;
import java.awt.*;

public class ViewIMSModal extends RoundedDialog {

    private final Window parent;

    public ViewIMSModal(Window parent,String serialNumber) {
        super(parent, 20); 
        this.parent = parent;

        
        ModalUtils.showDim(parent, new Color(0, 0, 0, 150));

        
        setContentPane(new ViewIMS(serialNumber));
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
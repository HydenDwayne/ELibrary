package view.modal.patron_modal;

import view.RoundedComponents.RoundedDialog;
import view.modal.ModalUtils;

import java.awt.*;

public class ViewEmployeeModal extends RoundedDialog {

    private final Window parent;

    public ViewEmployeeModal(Window parent) {
        super(parent, 20); // same corner radius as all modals
        this.parent = parent;

        // Dim the background
        ModalUtils.showDim(parent, new Color(0, 0, 0, 150));

        // Set modal content
        setContentPane(new ViewEmployee());
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

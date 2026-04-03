package view.modal.function_hall_modal;

import view.RoundedComponents.RoundedDialog;
import view.modal.ModalUtils;

import java.awt.*;

public class ReserveFunctionHallModal extends RoundedDialog {

    private final Window parent;

    public ReserveFunctionHallModal(Window parent) {
        super(parent, 20); // same radius as all other modals
        this.parent = parent;

        // Dim background
        ModalUtils.showDim(parent, new Color(0, 0, 0, 150));

        // Set content panel
        setContentPane(new ReserveFunctionHall());
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

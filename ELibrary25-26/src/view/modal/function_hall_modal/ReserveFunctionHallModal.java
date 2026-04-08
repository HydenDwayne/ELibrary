package view.modal.function_hall_modal;

import view.RoundedComponents.RoundedDialog;
import view.modal.ModalUtils;

import java.awt.*;

public class ReserveFunctionHallModal extends RoundedDialog {

    private final Window parent;

    public ReserveFunctionHallModal(Window parent, String hallCode, String dateSelected) {
        super(parent, 20); 
        this.parent = parent;

        
        ModalUtils.showDim(parent, new Color(0, 0, 0, 150));

        
        setContentPane(new ReserveFunctionHall(hallCode, dateSelected));
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

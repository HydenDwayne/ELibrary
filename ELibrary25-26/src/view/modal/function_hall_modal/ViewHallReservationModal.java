package view.modal.function_hall_modal;

import view.RoundedComponents.RoundedDialog;
import view.modal.ModalUtils;

import java.awt.*;
import java.util.List;

import model.DAOs.FunctionHall.DAOFuncHall;

public class ViewHallReservationModal extends RoundedDialog {

    private final Window parent;

    public ViewHallReservationModal(Window parent, String[] event) {
        super(parent, 20); 
        this.parent = parent;

        
        ModalUtils.showDim(parent, new Color(0, 0, 0, 150));

        
        setContentPane(new ViewHallReservation(event));
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

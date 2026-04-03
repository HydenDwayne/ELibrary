package view.modal.patron_modal;

import javax.swing.*;

import view.RoundedComponents.RoundedDialog;
import view.modal.ModalUtils;
import view.modal.books_modal.AddBook;

import java.awt.*;

public class RegisterPatronModal extends RoundedDialog {
	
	private final Window parent;

    public RegisterPatronModal(Window parent) {

        super(parent, 20);
        this.parent = parent;
        
        ModalUtils.showDim(parent, new Color(0, 0, 0, 150));
        
        setContentPane(new RegisterPatron());
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

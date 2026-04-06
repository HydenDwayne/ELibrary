package view.modal.archive_modal;

import java.awt.Color;
import java.awt.Window;

import view.RoundedComponents.RoundedDialog;
import view.modal.ModalUtils;
import view.modal.books_modal.ViewBooks;

public class ArchivedPatronModal extends RoundedDialog{
	private final Window parent;

    public ArchivedPatronModal(Window parent) {
        super(parent, 20);
        this.parent = parent;

        ModalUtils.showDim(parent, new Color(0, 0, 0, 150));
        
        setContentPane(new ArchivedPatron());
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

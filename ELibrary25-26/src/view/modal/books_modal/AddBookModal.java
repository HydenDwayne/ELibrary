package view.modal.books_modal;

import view.RoundedComponents.RoundedDialog;
import view.modal.ModalUtils;

import javax.swing.*;
import java.awt.*;

public class AddBookModal extends RoundedDialog {

    private final Window parent;

    public AddBookModal(Window parent) {
        super(parent, 20); // same radius as other modals
        this.parent = parent;

        // Dim the background
        ModalUtils.showDim(parent, new Color(0, 0, 0, 150));

        // Set content
        setContentPane(new AddBook());
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
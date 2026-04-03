package view.modal.books_modal;

import java.awt.*;
import javax.swing.*;

import controller.MainFunctions;
import view.RoundedComponents.RoundedDialog;
import view.modal.ModalUtils;

public class ViewBookModal extends RoundedDialog {

    private final Window parent;

    public ViewBookModal(Window parent, String callNumber) {
        super(parent, 20);
        this.parent = parent;

        ModalUtils.showDim(parent, new Color(0, 0, 0, 150));

        setContentPane(new ViewBooks(callNumber));
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

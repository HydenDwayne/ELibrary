package view.front_pages;

import java.awt.*;
import javax.swing.*;

import controller.MainFunctions;
import view.RoundedComponents.RoundedDialog;
import view.modal.ModalUtils;

public class PopUpMessageModal extends RoundedDialog {

    private final Window parent;

    public PopUpMessageModal(Window parent, String msg) {
        super(parent, 20);
        this.parent = parent;

        ModalUtils.showDim(parent, new Color(0, 0, 0, 150));

        setContentPane(new PopUpMessage(msg, "Error"));
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

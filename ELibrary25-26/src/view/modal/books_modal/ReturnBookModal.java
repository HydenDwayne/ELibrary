package view.modal.books_modal;

import java.awt.*;
import javax.swing.*;

import view.RoundedComponents.RoundedDialog;
import view.modal.ModalUtils;

public class ReturnBookModal extends RoundedDialog {

    private final Window parent;

    public ReturnBookModal(Window parent) {
        super(parent, 20);
        this.parent = parent;

        // ✅ Dim the parent window
        ModalUtils.showDim(parent, new Color(0, 0, 0, 150));

        // ✅ Start at STEP 1
        setStep1();

        setLocationRelativeTo(parent);
        setVisible(true);
    }

    // ✅ STEP 1 (this was missing)
    public void setStep1() {
        setContentPane(new ReturnBook(this));
        refreshAndCenter();
    }

    // ✅ STEP 2
    public void setStep2() {
        setContentPane(new ReturnBookConfirm(this));
        refreshAndCenter();
    }

    private void refreshAndCenter() {
        revalidate();
        repaint();
        pack();
        setLocationRelativeTo(parent);
    }

    @Override
    public void dispose() {
        ModalUtils.hideDim(parent);
        super.dispose();
    }
}
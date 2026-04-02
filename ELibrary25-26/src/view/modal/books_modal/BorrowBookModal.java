package view.modal.books_modal;

import java.awt.*;
import javax.swing.*;

import view.RoundedComponents.RoundedDialog;
import view.modal.ModalUtils;

public class BorrowBookModal extends RoundedDialog {

    private final Window parent;

    public BorrowBookModal(Window parent) {
        super(parent, 20);
        this.parent = parent;

        // ✅ Dim the parent
        ModalUtils.showDim(parent, new Color(0, 0, 0, 150));

        setStep1();

        setLocationRelativeTo(parent); // ✅ initial center
        setVisible(true);
    }

    public void setStep1() {
        setContentPane(new AddBorrowingRequestBook1(this));
        refreshAndCenter();
    }

    public void setStep2() {
        setContentPane(new AddBorrowingRequestBook2(this));
        refreshAndCenter();
    }

    private void refreshAndCenter() {
        revalidate();
        repaint();
        pack();                       // ✅ resize to new content
        setLocationRelativeTo(parent); // ✅ RECENTER (THIS WAS MISSING)
    }

    @Override
    public void dispose() {
        ModalUtils.hideDim(parent);
        super.dispose();
    }
}

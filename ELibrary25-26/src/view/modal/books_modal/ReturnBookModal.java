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

        
        ModalUtils.showDim(parent, new Color(0, 0, 0, 150));

        
        setStep1();

        setLocationRelativeTo(parent);
        setVisible(true);
    }

    
    public void setStep1() {
        setContentPane(new ReturnBook(this));
        refreshAndCenter();
    }

    
    public void setStep2(String[] borrowDetails) {
        setContentPane(new ReturnBookConfirm(this, borrowDetails));
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
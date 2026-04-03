package view.modal.filter_modal;

import view.RoundedComponents.*;
import javax.swing.*;
import java.awt.*;
import view.modal.ModalUtils;
import view.toolbar_tabs.BooksTab;

public class FilterByModal extends RoundedDialog {

    private final Window parent;

    public FilterByModal(Window parent, BooksTab bt) {
        super(parent, 20);
        this.parent = parent;

        ModalUtils.showDim(parent, new Color(0, 0, 0, 150));

        setContentPane(new FilterBy(bt));
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



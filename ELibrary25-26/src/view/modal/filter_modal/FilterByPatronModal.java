package view.modal.filter_modal;

import view.RoundedComponents.RoundedDialog;
import javax.swing.*;
import java.awt.*;

import view.modal.ModalUtils;
import view.toolbar_tabs.PatronsTab;

public class FilterByPatronModal extends RoundedDialog {

    private final Window parent;

    public FilterByPatronModal(Window parent, PatronsTab pt) {
        super(parent, 20);
        this.parent = parent;

        // ✅ Dim parent window
        ModalUtils.showDim(parent, new Color(0, 0, 0, 150));

        // ✅ Set content panel
        setContentPane(new FilterByPatron(pt)); // pass tab/controller
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    @Override
    public void dispose() {
        // ✅ Remove dim when closing
        ModalUtils.hideDim(parent);
        super.dispose();
    }
}
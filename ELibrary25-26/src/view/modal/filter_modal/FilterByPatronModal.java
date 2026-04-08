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

        
        ModalUtils.showDim(parent, new Color(0, 0, 0, 150));

        
        setContentPane(new FilterByPatron(pt)); 
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
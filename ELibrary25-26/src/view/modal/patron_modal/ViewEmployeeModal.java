package view.modal.patron_modal;

import view.RoundedComponents.RoundedDialog;
import view.modal.ModalUtils;
import view.toolbar_tabs.PatronsTab;

import java.awt.*;

public class ViewEmployeeModal extends RoundedDialog {

    private final Window parent;

    public ViewEmployeeModal(Window parent, String patronID, PatronsTab patTab) {
        super(parent, 20); 
        this.parent = parent;

        
        ModalUtils.showDim(parent, new Color(0, 0, 0, 150));

        
        setContentPane(new ViewEmployee(patronID, patTab));
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

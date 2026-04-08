package view.report_panels.Modals;

import view.RoundedComponents.RoundedDialog;
import view.modal.ModalUtils;
import view.report_panels.*;
import view.toolbar_tabs.ReportsTab;

import javax.swing.*;
import java.awt.*;

public class FacilityModal extends RoundedDialog{
	private final Window parent;

    public FacilityModal(Window parent, String tab) {
        super(parent, 20);
        this.parent = parent;

        ModalUtils.showDim(parent, new Color(0, 0, 0, 150));


        setContentPane(new ReportsTab(tab));
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
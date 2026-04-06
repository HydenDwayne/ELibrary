package view.modal.archive_modal;

import java.awt.Color;
import java.awt.Window;

import view.RoundedComponents.RoundedDialog;
import view.modal.ModalUtils;
import view.modal.books_modal.ViewBooks;

public class ArchivedModal extends RoundedDialog{
	private final Window parent;

    public ArchivedModal(Window parent, String reportPage) {
        super(parent, 20);
        this.parent = parent;

        ModalUtils.showDim(parent, new Color(0, 0, 0, 150));
        
        switch (reportPage) {
        case "LibAmphi":
        	setContentPane(new ArchivedFunctionHall(reportPage));
            break;
        case "DiscRoom1":
        	setContentPane(new ArchivedFunctionHall(reportPage));
            break;
        case "DiscRoom2":
        	setContentPane(new ArchivedFunctionHall(reportPage));
            break;
        case "iPAD":
        	setContentPane(new ArchivedFacilityLogin(reportPage));
            break;
        case "ISR":
        	setContentPane(new ArchivedFacilityLogin(reportPage));
            break;
        case "LOGIN":
        	setContentPane(new ArchivedFacilityLogin(reportPage));
            break;
        case "LSect":
        	setContentPane(new ArchivedFacilityLogin(reportPage));
            break;
        case "PWD":
        	setContentPane(new ArchivedFacilityLogin(reportPage));
            break;
        case "MH1":
        	setContentPane(new ArchivedFunctionHall(reportPage));
            break;
        case "MH2":
        	setContentPane(new ArchivedFunctionHall(reportPage));
            break;
        case "RelaxRoom":
        	setContentPane(new ArchivedFacilityLogin(reportPage));
            break;
        case "SDZ":
        	setContentPane(new ArchivedFacilityLogin(reportPage));
            break;
        case "SLR1":
        	setContentPane(new ArchivedFunctionHall(reportPage));
            break;
        case "SLR2":
        	setContentPane(new ArchivedFunctionHall(reportPage));
            break;
        case "TeleconRoom":
        	setContentPane(new ArchivedFunctionHall(reportPage));
            break;
        case "BookLoan":
        	setContentPane(new ArchivedBookLoan());
            break;
        case "ReturnBook":
        	setContentPane(new ArchivedReturnBook());
            break;
        case "Books":
        	setContentPane(new ArchivedBooks());
            break;
        case "IMS":
        	setContentPane(new ArchivedIMS());
            break;
        case "EL":
        	setContentPane(new ArchivedEquipmentLoan());
            break;
        default:
            throw new AssertionError("Unknown report page: " + reportPage);
    }

        
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

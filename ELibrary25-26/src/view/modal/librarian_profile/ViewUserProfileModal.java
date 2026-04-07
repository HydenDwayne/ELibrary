package view.modal.librarian_profile;
import view.RoundedComponents.RoundedDialog;
import view.front_pages.Dashboard;
import view.modal.ModalUtils;

import java.awt.*;
import java.util.List;

import model.DAOs.FunctionHall.DAOFuncHall;

public class ViewUserProfileModal extends RoundedDialog {

    private final Window parent;

    public ViewUserProfileModal(Window parent, String username, String password, Dashboard frame) {
        super(parent, 20); // same radius as all other modals
        this.parent = parent;

        // Dim background
        ModalUtils.showDim(parent, new Color(0, 0, 0, 150));

        // Set content panel
        setContentPane(new ViewUserProfile(username, password, frame));
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

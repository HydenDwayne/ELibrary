import javax.swing.*;
import java.awt.*;
import view.front_pages.Dashboard;
import view.front_pages.LoginWindow;
import view.RoundedComponents.RoundedDialog;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            // Create your custom rounded dialog
            RoundedDialog loadingDialog = new RoundedDialog(null, 30);
            loadingDialog.setLayout(new BorderLayout());
            loadingDialog.setUndecorated(true);

            // Content panel (since dialog is transparent)
            JPanel panel = new JPanel();
            panel.setBackground(Color.WHITE);
            panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
            panel.setLayout(new BorderLayout());

            JLabel label = new JLabel("Loading, please wait...", SwingConstants.CENTER);
            panel.add(label, BorderLayout.CENTER);

            loadingDialog.add(panel);
            loadingDialog.pack();
            loadingDialog.setLocationRelativeTo(null);

            // Show dialog FIRST (non-blocking trick below)
            loadingDialog.setModalityType(Dialog.ModalityType.MODELESS);
            loadingDialog.setVisible(true);

            // Background initialization
            new SwingWorker<Void, Void>() {

                private Dashboard dashboard;
                private LoginWindow login;

                @Override
                protected Void doInBackground() {
                	dashboard = new Dashboard();
                    login = new LoginWindow(dashboard);
                    
                    return null;
                }

                @Override
                protected void done() {
                    // Close loading dialog
                    loadingDialog.dispose();

                    // Show login window
                    login.setVisible(true);

                    // Keep dashboard hidden
                    dashboard.setVisible(false);
                }

            }.execute();

        });
    }
}
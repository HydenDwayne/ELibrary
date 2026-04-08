import javax.swing.*;
import java.awt.*;
import view.front_pages.Dashboard;
import view.front_pages.LoadingPanel;
import view.front_pages.LoginWindow;
import view.RoundedComponents.RoundedDialog;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            
            RoundedDialog loadingDialog = new RoundedDialog(null, 30);
            loadingDialog.setLayout(new BorderLayout());
            loadingDialog.setUndecorated(true);
            
            LoadingPanel panel = new LoadingPanel();

            loadingDialog.add(panel);
            loadingDialog.pack();
            loadingDialog.setLocationRelativeTo(null);

            
            loadingDialog.setModalityType(Dialog.ModalityType.MODELESS);
            loadingDialog.setVisible(true);

            
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
                    
                    loadingDialog.dispose();

                    
                    login.setVisible(true);

                    
                    dashboard.setVisible(false);
                }

            }.execute();

        });
    }
}